package org.pawel;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public interface TestThread {

    ThreadLocal<RequestSpecBuilder> requestSpecBuilder = new ThreadLocal<>();
    ThreadLocal<Response> response = new ThreadLocal<>();

}
