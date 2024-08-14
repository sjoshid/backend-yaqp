package com.joshi.backendforyaqp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurity {

  private static final String GROUPS = "groups";
  private static final String REALM_ACCESS_CLAIM = "webapp";
  private static final String ROLES_CLAIM = "webapp";

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/static/**")
                    .permitAll()
                    .requestMatchers("/admin/{id:\\\\d+}/**")
                    .hasAnyRole("sysAdmin", "compAdmin", "grpAdmin")
                    .requestMatchers("/admin/**")
                    .hasRole("sysAdmin")
                    .anyRequest()
                    .authenticated())
        .oauth2Login(withDefaults())
        .oauth2ResourceServer(oauth -> oauth.jwt(withDefaults()));

    return http.build();
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
