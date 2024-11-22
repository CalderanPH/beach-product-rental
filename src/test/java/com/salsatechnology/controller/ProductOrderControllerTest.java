package com.salsatechnology.controller;

import com.salsatechnology.configuration.IntegrationTestConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

class ProductOrderControllerTest extends IntegrationTestConfig {

    @BeforeEach
    protected void setupTestData() {
        basePath = "/orders";
    }

    @Test
    void shouldReturnCreatedWhenCreateProductOrder() {
        given()
                .body(getContentFromResource())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/create")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void shouldReturnOkWhenGetProductOrderByUsername() {
        given()
                .queryParam("username", "Paulo Calderan")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .log().all()
                .body("[0].productType", is("SUNSHADE"))
                .body("[0].timeHour", is(16))
                .body("[0].productValue", is(4000))
                .body("[0].productTotal", is(64000))
                .body("[0].userAmount", is(6592))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturnNotFoundWhenGetProductOrderByNotExistingUsername() {
        given()
                .queryParam("username", "USERNAME")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .log().all()
                .body("size()", is(1))
                .body("$", hasKey("message"))
                .body("message", is("No product order was found for the provided username."))
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}