package utils;

import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecFactory {
    public static final RequestSpecification DEFAULT = new RequestSpecBuilder()
            .setBaseUri(TestConfig.BASE_URI)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();

    public static final RequestSpecification SILENT = new RequestSpecBuilder()
            .setBaseUri(TestConfig.BASE_URI)
            .setContentType(ContentType.JSON)
            .build();
}
