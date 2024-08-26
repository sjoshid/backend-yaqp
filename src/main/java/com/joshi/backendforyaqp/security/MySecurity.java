package com.joshi.backendforyaqp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class MySecurity {

  private static final String GROUPS = "groups";
  private static final String REALM_ACCESS_CLAIM = "webapp";
  private static final String ROLES_CLAIM = "webapp";

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http,
      MyFilter policyEnforcer,
      OAuth2AuthorizedClientService authorizedClientService)
      throws Exception {
    policyEnforcer.setAuthorizedClientService(authorizedClientService);
    http.authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/public/**")
                    .permitAll()
                    .requestMatchers("/admin/**")
                    .hasAnyRole("sys_admin_group", "company-admin-group")
                    .anyRequest()
                    .authenticated())
        .cors(withDefaults()) // important. Presence of CorsConfigurationSource bean is not enough.
        .oauth2Login(withDefaults())
        .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()))
        .addFilterAfter(policyEnforcer, AuthorizationFilter.class);

    return http.build();
  }

  @Bean
  public MyFilter policyEnforcer() {
    try {
      PolicyEnforcerConfig config =
          JsonSerialization.readValue(
              getClass().getResourceAsStream("/keycloak.json"), PolicyEnforcerConfig.class);
      return new MyFilter(request -> config);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("https://localhost:9000/"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    // configuration.setAllowedHeaders(List.of("Cookies"));
    // configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
    return (authorities) -> {
      Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

      authorities.forEach(
          (authority) -> {
            GrantedAuthority mappedAuthority;

            if (authority instanceof OidcUserAuthority userAuthority) {
              mappedAuthority =
                  new OidcUserAuthority(
                      "OIDC_USER", userAuthority.getIdToken(), userAuthority.getUserInfo());
            } else {
              mappedAuthority = authority;
            }

            mappedAuthorities.add(mappedAuthority);
          });

      return mappedAuthorities;
    };
  }

  Collection<GrantedAuthority> generateAuthoritiesFromClaim(Collection<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
