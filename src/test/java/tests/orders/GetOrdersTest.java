package tests.orders;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.response.orders.OrdersResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.OrdersSteps;

import java.net.HttpURLConnection;

@Epic("Ez-scooter")
@Feature("Orders")
@Story("[GET] Получение списка заказов")
public class GetOrdersTest {

    @Test
    @DisplayName("Получение списка заказов → 200")
    public void returnListOfOrders(){
        OrdersSteps.getOrders()
                .checkStatusCode(HttpURLConnection.HTTP_OK)
                .checkResponseIsCorrect(OrdersResponseBody.class);
    }
}
