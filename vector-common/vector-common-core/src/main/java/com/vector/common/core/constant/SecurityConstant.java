package com.vector.common.core.constant;

public interface SecurityConstant {

    String LOGIN_USER_KEY = "login_user_key";

    String LOGIN_USER_KEY_PREFIX = "login_user_keys:";

    String TOKEN_HEADER = "Authorization";

    String TOKEN_PREFIX = "Bearer ";

    String BASIC_PREFIX = "Basic ";

    long TOKEN_EXPIRE_IN = 3600L;

    String TOKEN_SECRET = "b0d51c58c8b9c1f458fadf16c7d375630ef51da4df81915893b05c0fa4ed8bc6";

    String TOKEN_INFO = "TokenInfo";

    String TOKEN_INFO_USERNAME = "username";

    String TOKEN_INFO_USER_ID = "userId";

    String TOKEN_INFO_DEPT_ID = "deptId";

    String TOKEN_INFO_DATA_SCOPE = "dataScope";

    String TOKEN_INFO_AUTHORITIES = "authorities";

    String TOKEN_BLACKLIST_PREFIX = "token:blacklist:";


    /** 角色前缀 */
    String ROLE_PREFIX = "ROLE_";

    /** 管理员角色编码 */
    String ROLE_KEY_ADMIN = "admin";

    /** 管理员权限标识 */
    String PERMISSION_ADMIN = "*:*:*";

    Long ID_ADMIN = 1L;

    /** JWT存储权限属性 */
    String JWT_AUTHORITIES_KEY = "authorities";

    long MINUTES_TEN = 600L;

    long HOURS_ONE = 3600L;

    String DEFAULT_PASSWORD = "123456";

    String JWK_SET_KEY = "jwk_set";
}