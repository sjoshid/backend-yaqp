package com.joshi.backendforyaqp.config;

import com.joshi.backendforyaqp.security.MyFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

@Configuration
@ConfigurationProperties("rest.client")
public class RestClientBuilderHelper {
  private final Map<String, Builder> builder = new HashMap<>();

  @Bean
  public Map<String, RestClient> restClients() {
    return builder.entrySet().stream()
        .map(e -> Map.entry(e.getKey(), e.getValue().build()))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
