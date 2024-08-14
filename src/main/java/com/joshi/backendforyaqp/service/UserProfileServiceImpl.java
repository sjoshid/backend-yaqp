package com.joshi.backendforyaqp.service;

import java.lang.invoke.MethodHandles;
import java.security.Principal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
  private static final Logger logger =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Override
  public String create(Principal me) {
    return null;
  }

  @Override
  public String read(Principal me) {
    logger.info("Im service class method");
    return null;
  }

  @Override
  public String update(Principal me) {
    return null;
  }

  @Override
  public String delete(Principal me) {
    return null;
  }

  @Override
  public String viewOther(Principal me, UUID otherId) {
    return null;
  }
}
