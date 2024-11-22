package com.salsatechnology.configuration;

import com.salsatechnology.BeachProductRentalApplication;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Sql("/dataset.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = BeachProductRentalApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationTestConfig {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
    }

    protected String getContentFromResource() {
        try {
            InputStream stream = ResourceUtils.class.getResourceAsStream("/json/create-order.json");
            return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        } catch (IOException ignored) {
            return null;
        }
    }

}