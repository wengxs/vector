/*
 * Copyright 2020-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vector.auth.oauth2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.PkceParameterNames;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * Utility methods for the OAuth 2.0 Protocol Endpoints.
 *
 * @author Joe Grandja
 * @author Greg Li
 * @since 0.1.2
 */
public final class OAuth2EndpointUtils {
	public static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

	private OAuth2EndpointUtils() {
	}

	public static MultiValueMap<String, String> getFormParameters(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameterMap.forEach((key, values) -> {
			String queryString = StringUtils.hasText(request.getQueryString()) ? request.getQueryString() : "";
			// If not query parameter then it's a form parameter
			if (!queryString.contains(key) && values.length > 0) {
				for (String value : values) {
					parameters.add(key, value);
				}
			}
		});
		return parameters;
	}

	public static MultiValueMap<String, String> getQueryParameters(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameterMap.forEach((key, values) -> {
			String queryString = StringUtils.hasText(request.getQueryString()) ? request.getQueryString() : "";
			if (queryString.contains(key) && values.length > 0) {
				for (String value : values) {
					parameters.add(key, value);
				}
			}
		});
		return parameters;
	}

	public static Map<String, Object> getParametersIfMatchesAuthorizationCodeGrantRequest(HttpServletRequest request, String... exclusions) {
		if (!matchesAuthorizationCodeGrantRequest(request)) {
			return Collections.emptyMap();
		}
		MultiValueMap<String, String> multiValueParameters =
				"GET".equals(request.getMethod()) ?
						getQueryParameters(request) :
						getFormParameters(request);
		for (String exclusion : exclusions) {
			multiValueParameters.remove(exclusion);
		}

		Map<String, Object> parameters = new HashMap<>();
		multiValueParameters.forEach((key, value) ->
				parameters.put(key, (value.size() == 1) ? value.get(0) : value.toArray(new String[0])));

		return parameters;
	}

	static boolean matchesAuthorizationCodeGrantRequest(HttpServletRequest request) {
		return AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(
				request.getParameter(OAuth2ParameterNames.GRANT_TYPE)) &&
				request.getParameter(OAuth2ParameterNames.CODE) != null;
	}

	static boolean matchesPkceTokenRequest(HttpServletRequest request) {
		return matchesAuthorizationCodeGrantRequest(request) &&
				request.getParameter(PkceParameterNames.CODE_VERIFIER) != null;
	}

	public static void throwError(String errorCode, String parameterName, String errorUri) {
		OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
		throw new OAuth2AuthenticationException(error);
	}

	public static String normalizeUserCode(String userCode) {
		Assert.hasText(userCode, "userCode cannot be empty");
		StringBuilder sb = new StringBuilder(userCode.toUpperCase().replaceAll("[^A-Z\\d]+", ""));
		Assert.isTrue(sb.length() == 8, "userCode must be exactly 8 alpha/numeric characters");
		sb.insert(4, '-');
		return sb.toString();
	}

	public static boolean validateUserCode(String userCode) {
		return (userCode != null && userCode.toUpperCase().replaceAll("[^A-Z\\d]+", "").length() == 8);
	}
}
