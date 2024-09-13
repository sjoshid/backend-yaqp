package com.joshi.backendforyaqp.controller.metrics;

import com.joshi.backendforyaqp.pojo.Router;
import java.util.Set;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/inv/router")
public class InvController {

  @GetMapping
  public Set<String> list() {
    return Set.of();
  }

  @PatchMapping
  public void update(
      @RequestParam("id") String routerId, @RequestBody Router routerContainingUpdates) {}
}
