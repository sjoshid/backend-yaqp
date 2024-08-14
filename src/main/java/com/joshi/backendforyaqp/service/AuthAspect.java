package com.joshi.backendforyaqp.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {
  private static final Logger logger = LoggerFactory.getLogger(AuthAspect.class);

  // sj_todo this will be com.tnsi
  @Before("execution(* com.joshi.backendforyaqp.service..*(..))")
  public void checkAuth() {
    logger.info("Im called before service class method");
  }
}
