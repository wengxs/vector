# 自定义授权模式

## 定义授权模式
```java
public class CustomAuthorizationGrantType {

    /** 密码模式 */
    public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");

    /** 短信验证码模式 */
    public static final AuthorizationGrantType SMS_CODE = new AuthorizationGrantType("sms_code");
}
```
## 定义授权模式身份验证令牌
```java
/**
 * 短信验证码授权模式身份验证令牌
 */
@Getter
public class SmsCodeAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    /** 手机号 */
    private final String mobile;

    /** 短信验证码 */
    private final String code;

    /** 授权模式 */
    private static final AuthorizationGrantType grantType = CustomAuthorizationGrantType.SMS_CODE;

    public SmsCodeAuthenticationToken(String mobile,
                                      String code,
                                      Authentication clientPrincipal,
                                      @Nullable Map<String, Object> additionalParameters) {
        super(grantType, clientPrincipal, additionalParameters);
        this.mobile = mobile;
        this.code = code;
    }
}
```
## 定义授权模式参数解析器
```java
/**
 * 短信验证码授权模式参数解析器
 */
public class SmsCodeAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getFormParameters(request);

        // grant_type (REQUIRED)
        String grantType = parameters.getFirst(OAuth2ParameterNames.GRANT_TYPE);
        if (!CustomAuthorizationGrantType.SMS_CODE.getValue().equals(grantType)) {
            return null;
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        // mobile (REQUIRED)
        String mobile = parameters.getFirst("mobile");
        if (!StringUtils.hasText(mobile)) {
            throw new OAuth2AuthenticationException("手机号不能为空");
        }

        // code (REQUIRED)
        String code = parameters.getFirst(OAuth2ParameterNames.CODE);
        if (!StringUtils.hasText(code)) {
            throw new OAuth2AuthenticationException("验证码不能为空");
        }

        Map<String, Object> additionalParameters = OAuth2EndpointUtils
                .getParametersIfMatchesAuthorizationCodeGrantRequest(
                        request,
                        OAuth2ParameterNames.GRANT_TYPE,
                        OAuth2ParameterNames.CLIENT_ID
                );

        return new SmsCodeAuthenticationToken(mobile, code, clientPrincipal, additionalParameters);
    }
}
```
## 定义检索用户接口
```java
public interface CustomUserDetailsService {

    UserDetails retrieveUser(String keyword) throws UsernameNotFoundException;
}

@Component
public class AppUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private AppUserApi appUserApi;

    @Override
    public UserDetails retrieveUser(String keyword) throws UsernameNotFoundException {
        R<UserAuthInfo> r = appUserApi.loadUser(keyword);
        BizAssert.isTrue(r.isOk(), r.getMessage());
        UserAuthInfo user = r.getData();
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setEnabled(user.getEnabled());
        return loginUser;
    }
}
```
## 定义授权模式认证处理器
```java
/**
 * 短信验证码授权模式认证处理器
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private final CustomUserDetailsService userDetailsService;
    private final RedisTemplate<?, ?> redisTemplate;

    public SmsCodeAuthenticationProvider(OAuth2AuthorizationService authorizationService,
                                         OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator,
                                         CustomUserDetailsService userDetailsService,
                                         RedisTemplate<?, ?> redisTemplate) {
        this.tokenGenerator = tokenGenerator;
        this.authorizationService = authorizationService;
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        OAuth2ClientAuthenticationToken clientPrincipal = OAuth2AuthenticationProviderUtils
                .getAuthenticatedClientElseThrowInvalidClient(authenticationToken);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        // 验证客户端是否支持密码授权模式
        assert registeredClient != null;
        if (!registeredClient.getAuthorizationGrantTypes().contains(authenticationToken.getGrantType())) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        String mobile = authenticationToken.getMobile();
        String code = authenticationToken.getCode();

        // 验证短信验证码
        if (!"121380".equals(code)) { // 测试
            String key = SecurityConstant.LOGIN_SMS_CODE_PREFIX + mobile;
            String redisCode = (String) redisTemplate.opsForValue().get(key);
            if (redisCode == null) {
                throw new OAuth2AuthenticationException("验证码已失效");
            }
            if (!redisCode.equals(code)) {
                throw new OAuth2AuthenticationException("验证码错误");
            }
        }

        // 创建授权令牌
        Authentication authenticationResult;
        try {
            // 检索用户信息
            UserDetails userDetails = userDetailsService.retrieveUser(mobile);
            authenticationResult = UsernamePasswordAuthenticationToken.authenticated(userDetails,
                    userDetails.getPassword(), userDetails.getAuthorities());
        } catch (Exception e) {
            throw new OAuth2AuthenticationException(e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        }

        // 访问令牌构造器
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authenticationResult)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(registeredClient.getScopes())
                .authorizationGrantType(CustomAuthorizationGrantType.SMS_CODE)
                .authorizationGrant(authenticationToken);

        // 生成访问令牌
        OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            throw new OAuth2AuthenticationException("The token generator failed to generate the access token.");
        }
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(authenticationToken.getName())
                .authorizationGrantType(authenticationToken.getGrantType());
        if (generatedAccessToken instanceof ClaimAccessor) {
            authorizationBuilder.token(accessToken, (metadata) ->
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
        } else {
            authorizationBuilder.accessToken(accessToken);
        }

        // 生成刷新令牌
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                // Do not issue refresh token to public client
                !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
            if (generatedRefreshToken instanceof OAuth2RefreshToken oAuth2RefreshToken) {
                refreshToken = oAuth2RefreshToken;
                authorizationBuilder.refreshToken(refreshToken);
            } else {
                throw new OAuth2AuthenticationException("The token generator failed to generate the refresh token.");
            }
        }

        // 持久化令牌
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);

        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient, clientPrincipal, accessToken, refreshToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
```
## 添加“授权模式参数解析器、授权模式认证处理器”到认证服务配置
```java
    @Autowired
    private OAuth2TokenGenerator<?> tokenGenerator;
    @Autowired
    private CustomUserDetailsService appUserDetailsService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
                                                                      OAuth2AuthorizationService authorizationService,
                                                                      AuthenticationManager authenticationManager)
            throws Exception {
        List<AuthenticationConverter> converters = List.of(
                new PasswordAuthenticationConverter(),
                new SmsCodeAuthenticationConverter()
        );
        List<AuthenticationProvider> providers = List.of(
                new PasswordAuthenticationProvider(authorizationService, authenticationManager, tokenGenerator),
                new SmsCodeAuthenticationProvider(authorizationService, tokenGenerator, appUserDetailsService, redisTemplate)
        );
    
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                        .accessTokenRequestConverters(authenticationConverters ->
                                authenticationConverters.addAll(converters))
                        .authenticationProviders(authenticationProviders ->
                                authenticationProviders.addAll(providers))
                        .accessTokenResponseHandler(loginSuccessHandler)
                        .errorResponseHandler(loginFailureHandler)
                );
    
        http
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                        httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()))
        ;
        return http.build();
    }
```