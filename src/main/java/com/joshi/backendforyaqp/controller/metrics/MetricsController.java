package com.joshi.backendforyaqp.controller.metrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
  @GetMapping("/inout")
  public String inOutKbps(@RequestParam("routerId") String routerId) {
    return "";
  }

  @GetMapping("/ruptime")
  public String routerUptime(@RequestParam("routerId") String routerId) {
    return "";
  }

  @GetMapping("/rsaturation")
  public String routerSaturation(@RequestParam("routerId") String routerId) {
    return "";
  }

  @GetMapping("/rmemused")
  public String routerMemUsed(@RequestParam("routerId") String routerId) {
    return "";
  }

  @GetMapping("/rcpuload")
  public String routerCpuLoad(@RequestParam("routerId") String routerId) {
    return "";
  }

  @GetMapping("/rmemutil")
  public String routerMemUtil(@RequestParam("routerId") String routerId) {
    return "";
  }
}
