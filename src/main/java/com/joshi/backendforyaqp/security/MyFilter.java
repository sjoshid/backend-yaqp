package com.joshi.backendforyaqp.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class MyFilter extends ServletPolicyEnforcerFilter {

  @Setter private OAuth2AuthorizedClientService authorizedClientService;

  public MyFilter(ConfigurationResolver configResolver) {
    super(configResolver);
  }

  protected String extractBearerToken(HttpServletRequest request) {
    OAuth2AuthenticationToken me =
        (OAuth2AuthenticationToken)
            SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
    if (me == null) {
      throw new AuthenticationCredentialsNotFoundException(
          "An Authentication object was not found in the SecurityContext");
    }
    OAuth2AuthorizedClient authorizedClient =
        authorizedClientService.loadAuthorizedClient(
            me.getAuthorizedClientRegistrationId(), me.getName());

    OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
    return accessToken.getTokenValue();
  }
}
