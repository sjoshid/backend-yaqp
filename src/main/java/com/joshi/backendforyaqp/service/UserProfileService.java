package com.joshi.backendforyaqp.service;

import java.security.Principal;
import java.util.UUID;

public interface UserProfileService {

  String create(final Principal me);

  String viewOther(final Principal me, UUID otherId);

  String read(final Principal me);

  String update(final Principal me);

  String delete(final Principal me);
}
