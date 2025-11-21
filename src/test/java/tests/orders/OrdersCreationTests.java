package tests.orders;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.request.orders.CreateOrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.OrdersSteps;

import java.net.HttpURLConnection;

@Epic("Ez-scooter")
@Feature("Orders")
@Story("[POST] Создание заказа")
public class OrdersCreationTests {

    @ParameterizedTest(name = "[{0}] → 201")
    @MethodSource("testdata.orders.CreateOrdersData#validDataProvider")
    @DisplayName("Создание заказа с валидными данными, где color = ")
    public void createsOrderWithValidData(String caseName,
                                                                 CreateOrderRequest createRequest){
        OrdersSteps.createOrder(createRequest)
                .checkStatusCode(HttpURLConnection.HTTP_CREATED)
                .checkBodyFieldExists("track");
    }
}
