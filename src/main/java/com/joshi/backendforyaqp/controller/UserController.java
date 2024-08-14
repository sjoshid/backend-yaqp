package com.joshi.backendforyaqp.controller;

import com.joshi.backendforyaqp.service.UserProfileService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserProfileService userProfileService;

  @GetMapping("/public")
  @CrossOrigin("http://localhost:9000")
  public String view(final Principal me) {
    userProfileService.read(me);
    return "Yayy";
  }

  @GetMapping("/user/{other}")
  @CrossOrigin("http://localhost:9000")
  public String viewOther(final Principal me, @PathVariable("other") Long otherId) {
    return me.toString();
  }

  @GetMapping("/user/create")
  @CrossOrigin("http://localhost:9000")
  public String create(final Principal me) {
    return me.toString();
  }

  @GetMapping("/user/delete")
  @CrossOrigin("http://localhost:9000")
  public String delete(final Principal me) {
    return me.toString();
  }
}
