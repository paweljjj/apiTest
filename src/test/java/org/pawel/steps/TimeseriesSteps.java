package org.pawel.steps;


import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.pawel.pojo.TimeseriesResponse;
import org.pawel.service.ApiLayerService;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class TimeseriesSteps {

    private final ApiLayerService apiLayerService;
    private final ObjectMapper objectMapper;
    private String timeseriesUrl = "/exchangerates_data/timeseries";
    private String invalidTimeseriesUrl = "/timeseries/";

    @Given("set api layer base uri")
    public void setApiLayerBaseUri() {
        apiLayerService.setRequestSpecBuilder();
    }

    @Given("set valid authorization header")
    public void setAuthorizationHeader() {
        apiLayerService.addAuthorizationHeader();
    }

    @Given("set start date to: {string}")
    public void setStartDate(String startDate) {
        apiLayerService.addParam("start_date", startDate);
    }

    @Given("set end date to: {string}")
    public void setEndDate(String startDate) {
        apiLayerService.addParam("end_date", startDate);
    }

    @Given("set base currency to: {string}")
    public void setBaseCurrency(String base) {
        apiLayerService.addParam("base", base);
    }

    @Given("set symbols to: {string}")
    public void setSymbols(String symbols) {
        apiLayerService.addParam("symbols", symbols);
    }

    @When("make rest call GET timeseries")
    public void getTimeseries() {
        apiLayerService.setResponse(
                given()
                        .spec(apiLayerService.getRequestBuilder().build())
                        .when()
                        .get(timeseriesUrl));
    }

    @When("make rest call GET invalid timeseries url")
    public void get() {
        apiLayerService.setResponse(
                given()
                        .spec(apiLayerService.getRequestBuilder().build())
                        .when()
                        .get(invalidTimeseriesUrl));
    }

    @When("make rest call POST timeseries")
    public void postTimeseries() {
        apiLayerService.setResponse(
                given()
                        .spec(apiLayerService.getRequestBuilder().build())
                        .when()
                        .post(timeseriesUrl));
    }

    @Then("status code is {int}")
    public void assertStatusCodeIs(Integer statusCode) {
        assertEquals(statusCode, apiLayerService.getResponse().statusCode());
    }

    @Then("response contains rates for given date")
    public void assertResponseContains(DataTable dataTable) throws JsonProcessingException {
        TimeseriesResponse timeseriesResponse = mapTimeseriesResponse();
        assertRateOccurrencesInResponse(dataTable, 1, timeseriesResponse, "Rate not found in the response");
    }

    @Then("response does not contains rates for given date")
    public void assertResponseDoesNotContains(DataTable dataTable) throws JsonProcessingException {
        TimeseriesResponse timeseriesResponse = mapTimeseriesResponse();
        assertRateOccurrencesInResponse(dataTable, 0, timeseriesResponse, "Rate found in the response");
    }

    private TimeseriesResponse mapTimeseriesResponse() throws JsonProcessingException {
        return objectMapper.readValue(apiLayerService.getResponse().getBody().asString(), TimeseriesResponse.class);
    }

    private static void assertRateOccurrencesInResponse(DataTable dataTable, int expected, TimeseriesResponse timeseriesResponse, String message) {
        for (List<String> columns : dataTable.asLists(String.class)) {
            assertEquals(expected, timeseriesResponse.getRates().findValues(columns.get(0)).size(), message);
        }
    }

}
