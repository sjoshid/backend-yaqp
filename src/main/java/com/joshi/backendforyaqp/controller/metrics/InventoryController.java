package com.joshi.backendforyaqp.controller.metrics;

import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(method = RequestMethod.GET, value = "/inv")
public class InventoryController {

  @GetMapping("/router")
  public Set<String> list() {

  }
}
