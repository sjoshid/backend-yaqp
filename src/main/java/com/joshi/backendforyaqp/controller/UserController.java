package com.joshi.backendforyaqp.controller;

import com.joshi.backendforyaqp.service.UserProfileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.keycloak.AuthorizationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  // @Service is rarely prototype. Having member variable logger is OK here.
  private final Logger logger = LoggerFactory.getLogger(getClass());

  // No CORS for this. This is only called when Keycloak auth is success.
  @GetMapping("/auth")
  public void auth(
      final Principal me, final HttpServletRequest request, final HttpServletResponse response)
      throws IOException {
    String session = request.getSession(false).getId();
    response.addCookie(new Cookie("JSESSIONID", session));
    response.sendRedirect("https://localhost:9000/#/user");
  }

  @GetMapping("/public")
  public String view(
      final Principal me, final HttpServletRequest request, final HttpServletResponse response) {
    return "Im public";
  }

  @GetMapping("/user/{other}")
  public String viewOther(final Principal me, @PathVariable("other") Long otherId) {
    return me.toString();
  }

  @GetMapping("/user/create")
  public String create(final Principal me) {
    return "{\"key\": \"value\"}";
  }

  @GetMapping("/user/delete")
  public String delete(final Principal me) {
    return me.toString();
  }

  @GetMapping("/alerts")
  public Boolean viewAlerts(
      final OAuth2AuthenticationToken me,
      @RequestAttribute("org.keycloak.AuthorizationContext") AuthorizationContext authzContext) {
    logger.info("authzContext is null? {}", authzContext == null);
    logger.info("If you get here then it means principal {} has permission. ", me.getName());
    return true;
  }
}
