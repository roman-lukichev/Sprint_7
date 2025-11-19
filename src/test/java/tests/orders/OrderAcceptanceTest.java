package tests.orders;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.CourierSteps;
import steps.OrdersSteps;
import testdata.orders.CreateOrdersData;
import utils.CleanupExtension;

import java.net.HttpURLConnection;

@Epic("Ez-scooter")
@Feature("Orders")
@Story("[PUT] Принятие заказ курьером")
public class OrderAcceptanceTest {
    @RegisterExtension
    static CleanupExtension cleanup = new CleanupExtension();

    @Test
    @DisplayName("Успешный запрос на принятие заказа курьером → 200")
    public void acceptsOrderWithValidData(){
        int orderId = OrdersSteps.createAndGetOrder(CreateOrdersData.valid()).getIntField("order.id");
        int courierId = CourierSteps.createAndLoginCourier().getIntField("id");
        OrdersSteps.acceptOrder(orderId, courierId)
                .checkStatusCode(HttpURLConnection.HTTP_OK)
                .checkBodyField("ok", true);
    }
    @ParameterizedTest(name = "{0} → {1}")
    @MethodSource("testdata.orders.OrderAcceptanceData#inValidOrderIdDataProvider")
    @DisplayName("Получение заказа ")
    public void failsToAcceptOrderWithInvalidOrderId(String caseName, Integer statusCode, Integer orderId){
        int courierId = CourierSteps.createAndLoginCourier().getIntField("id");
        OrdersSteps.acceptOrder(orderId, courierId)
                .checkStatusCode(statusCode);
    }
    @ParameterizedTest(name = "{0} → {1}")
    @MethodSource("testdata.orders.OrderAcceptanceData#inValidCourierIdDataProvider")
    @DisplayName("Получение заказа ")
    public void failsToAcceptOrderWithInvalidCourierId(String caseName, Integer statusCode, Integer courierId){
        int orderId = OrdersSteps.createAndGetOrder(CreateOrdersData.valid()).getIntField("order.id");
        OrdersSteps.acceptOrder(orderId, courierId)
                .checkStatusCode(statusCode);
    }
}
