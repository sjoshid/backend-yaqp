package com.joshi.backendforyaqp.controller.metrics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshi.backendforyaqp.pojo.Data;
import com.joshi.backendforyaqp.pojo.InOutMetricResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
public class MetricsController {
    @GetMapping(value = "/metrics/inout")
    public List<InOutMetricResponse> inOutKbps(@RequestParam("id") String routerId)
            throws JsonProcessingException {
        Instant now = Instant.now();
        Data d1 = new Data(now.plus(5, ChronoUnit.MINUTES), 10.);
        Data d2 = new Data(now.plus(5, ChronoUnit.MINUTES), 20.);
        Data d3 = new Data(now.plus(5, ChronoUnit.MINUTES), 30.);
        Data d4 = new Data(now.plus(5, ChronoUnit.MINUTES), 40.);

        List<Data> l = List.of(d1, d2, d3, d4);
        List<List<Data>> ld = List.of(l);

        InOutMetricResponse response1 = new InOutMetricResponse("Max In", "line", "Total", ld);
        InOutMetricResponse response2 = new InOutMetricResponse("Max Out", "line", "Total", ld);
        InOutMetricResponse response3 = new InOutMetricResponse("Avg In", "line", "Total", ld);
        InOutMetricResponse response4 = new InOutMetricResponse("Avg Out", "line", "Total", ld);
        return List.of(response1, response2, response3, response4);
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
