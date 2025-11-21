package steps;

import constants.OrdersEndpoints;
import model.request.orders.CreateOrderRequest;
import model.response.BaseResponse;
import utils.SpecFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class OrdersSteps {
    //Шаги, которые попадают в Allure
    @Step("Отправить POST запрос на создание заказа")
    public static BaseResponse createOrder(CreateOrderRequest body) {
        return doCreateOrder(body, SpecFactory.DEFAULT);
    }
    @Step("Отправить GET запрос на получение списка заказов")
    public static BaseResponse getOrders() {
        return doGetOrders(SpecFactory.DEFAULT);
    }
    @Step("Отправить GET запрос на получение заказа по трек номеру")
    public static BaseResponse getOrderByTrack(Integer track) {
        return doGetOrderByTrack(track, SpecFactory.DEFAULT);
    }
    @Step("Отправить PUT запрос на принятие заказа курьером")
    public static BaseResponse acceptOrder(Integer orderId, Integer courierId) {
        return doAcceptOrder(orderId, courierId, SpecFactory.DEFAULT);
    }
    @Step("Cоздать и получить заказ")
    public static BaseResponse createAndGetOrder(CreateOrderRequest body) {
        int track = createOrder(body)
                .checkStatusCode(HttpURLConnection.HTTP_CREATED)
                .getIntField("track");
        return getOrderByTrack(track)
                .checkStatusCode(HttpURLConnection.HTTP_OK);
    }

    //Шаги, которые не попадают в Allure
    //пока нет

    //Методы для вызова API
    private static BaseResponse doCreateOrder(CreateOrderRequest body, RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .body(body)
                .post(OrdersEndpoints.CREATE);
        return new BaseResponse(response);
    }

    private static BaseResponse doGetOrders(RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .get(OrdersEndpoints.GET_ORDERS);
        return new BaseResponse(response);
    }

    private static BaseResponse doGetOrderByTrack(Integer track, RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .queryParam("t", track)
                .get(OrdersEndpoints.GET_ORDERS_BY_TRACK);
        return new BaseResponse(response);
    }

    private static BaseResponse doAcceptOrder(Integer orderId, Integer courierId, RequestSpecification spec) {
        Response response = given()
                .spec(spec)
                .queryParam("courierId", courierId)
                .put(OrdersEndpoints.acceptOrder(orderId));
        return new BaseResponse(response);
    }

}
