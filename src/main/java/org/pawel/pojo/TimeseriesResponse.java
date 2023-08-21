package org.pawel.pojo;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeseriesResponse {

    @JsonProperty("success")
    Boolean success;
    @JsonProperty("timeseries")
    Boolean timeseries;
    @JsonProperty("start_date")
    String startDate;
    @JsonProperty("end_date")
    String endDate;
    @JsonProperty("base")
    String base;
    @JsonProperty("rates")
    JsonNode rates;

}
