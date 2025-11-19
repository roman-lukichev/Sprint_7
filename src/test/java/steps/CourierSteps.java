package steps;

import constants.CourierEndpoints;
import model.response.BaseResponse;
import testdata.courier.CreateCourierData;
import testdata.courier.LoginCourierData;
import utils.SpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.courier.CreateCourierRequest;
import model.request.courier.LoginCourierRequest;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static utils.CleanupExtension.addCourierToDeletionList;

public class CourierSteps {
    //Шаги, которые попадают в Allure
    @Step("Отправить POST запрос на создание курьера c логином {body.login}")
    public static model.response.BaseResponse createCourier(CreateCourierRequest body) {
        return doCreateCourier(body, SpecFactory.DEFAULT);
    }
    @Step("Отправить POST запрос на логин курьера в сервисе")
    public static model.response.BaseResponse loginCourier(LoginCourierRequest body) {
        return doLoginCourier(body, SpecFactory.DEFAULT);
    }
    @Step("Отправить DELETE запрос удаление курьера с id = {id}")
    public static model.response.BaseResponse deleteCourier(String id) {
        return doDeleteCourier(id, SpecFactory.DEFAULT);
    }
    @Step("Cоздать курьера и выполнить логин")
    public static model.response.BaseResponse createAndLoginCourier(){
        CreateCourierRequest request = CreateCourierData.valid();
        createCourier(request)
                .checkStatusCode(HttpURLConnection.HTTP_CREATED);
        return loginCourier(LoginCourierData.createFrom(request))
                .checkStatusCode(HttpURLConnection.HTTP_OK);
    }

    //Шаги, которые не попадают в Allure
    public static model.response.BaseResponse loginCourierSilently(LoginCourierRequest body) {
        return doLoginCourier(body, SpecFactory.SILENT);
    }

    public static BaseResponse deleteCourierSilently(String id) {
        return doDeleteCourier(id, SpecFactory.SILENT);
    }

    //Методы для вызова API
    private static BaseResponse doCreateCourier(CreateCourierRequest body, RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .body(body)
                .post(CourierEndpoints.CREATE);
        addCourierToDeletionList(response.getStatusCode(), body.getLogin(),  body.getPassword());
        return new BaseResponse(response);
    }

    private static BaseResponse doLoginCourier(LoginCourierRequest body, RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .body(body)
                .post(CourierEndpoints.LOGIN);
        return new BaseResponse(response);
    }

    private static BaseResponse doDeleteCourier(String id, RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .delete(CourierEndpoints.delete(id));
        return new BaseResponse(response);
    }
}
