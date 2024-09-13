package com.joshi.backendforyaqp.controller.metrics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {
  @GetMapping("/metrics/inout")
  public String inOutKbps(@RequestParam("routerId") @NonNull String routerId)
      throws JsonProcessingException {
    var res = "";
    if (routerId.equals("xyz")) {
      Map.Entry<String, List<Double>> e1 = Map.entry("maxIn", List.of(1., 2., 3., 4., 4.5));
      Map.Entry<String, List<Double>> e2 = Map.entry("maxOut", List.of(1., 2., 3., 4., 4.5));
      Map.Entry<String, List<Double>> e3 = Map.entry("avgIn", List.of(1., 2., 3., 4., 4.5));
      Map.Entry<String, List<Double>> e4 = Map.entry("avgOut", List.of(1., 2., 3., 4., 4.5));
      res = new ObjectMapper().writeValueAsString(Map.ofEntries(e1, e2, e3, e4));
    } else if (routerId.equals("abc")) {
      Map.Entry<String, List<Double>> e1 = Map.entry("maxIn", List.of(40., 50., 60., 70.));
      Map.Entry<String, List<Double>> e2 = Map.entry("maxOut", List.of(40., 50., 60., 70.));
      Map.Entry<String, List<Double>> e3 = Map.entry("avgIn", List.of(40., 50., 60., 70.));
      Map.Entry<String, List<Double>> e4 = Map.entry("avgOut", List.of(40., 50., 60., 70.));
      res = new ObjectMapper().writeValueAsString(Map.ofEntries(e1, e2, e3, e4));
    }

    return res;
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
