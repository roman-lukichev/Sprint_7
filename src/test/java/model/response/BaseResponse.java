package model.response;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseResponse {
    private final Response response;

    public BaseResponse(Response response) {
        this.response = response;
    }

    public String getStringField(String fieldName){
        return response.jsonPath().getString(fieldName);
    }

    public int getIntField(String fieldName){
        return response.jsonPath().getInt(fieldName);
    }

    @Step("Проверить, что поле {field} = {expectedValue}")
    public BaseResponse checkBodyField(String field, Object expectedValue){
        response.then().body(field, equalTo(expectedValue));
        return this;
    }

    @Step("Проверить, что поле {field} существует")
    public BaseResponse checkBodyFieldExists(String field){
        response.then().body(field, notNullValue());
        return this;
    }

    @Step("Проверить, что статус код = {expectedStatusCode}")
    public BaseResponse checkStatusCode(int expectedStatusCode){
        response.then().statusCode(expectedStatusCode);
        return this;
    }

    @Step("Проверить, что тело ответа соответствует ожидаемой структуре")
    public BaseResponse checkResponseIsCorrect(Class<?> structure) {
        this.getResponse().body().as(structure);
        return this;
    }

    public Response getResponse() {
        return response;
    }
}
