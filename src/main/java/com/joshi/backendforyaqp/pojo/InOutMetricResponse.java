package com.joshi.backendforyaqp.pojo;


import java.time.Instant;
import java.util.List;

// DTO
public record InOutMetricResponse(String name, String type, String stack, List<List<Data>> data) {
}
