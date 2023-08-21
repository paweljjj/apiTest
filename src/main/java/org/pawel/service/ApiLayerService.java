package org.pawel.service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.pawel.TestThread;
import org.pawel.config.EnvironmentConfig;
import org.pawel.exception.ApiTestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiLayerService implements TestThread {

    private final EnvironmentConfig environmentConfig;

    public void setRequestSpecBuilder() {
        requestSpecBuilder.set(new RequestSpecBuilder().setBaseUri(environmentConfig.getApilayerBaseUri()));
    }

    public RequestSpecBuilder getRequestBuilder() {
        return requestSpecBuilder.get();
    }

    public Response getResponse() {
        return this.response.get();
    }

    public void setResponse(Response response) {
        this.response.set(response);
    }

    public void addAuthorizationHeader() {
        addHeader("apikey", environmentConfig.getApilayerKey().stream().filter(StringUtils::isNotEmpty).findFirst().orElseThrow(() -> new ApiTestException("ApiLayer key must be not empty")));
    }

    public void addHeader(String headerName, String headerValue) {
        requestSpecBuilder.get().addHeader(headerName, headerValue);
    }

    public void addParam(String paramName, String paramValue) {
        requestSpecBuilder.get().addParam(paramName, paramValue);
    }

}
