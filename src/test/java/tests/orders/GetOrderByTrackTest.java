package tests.orders;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.response.orders.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.OrdersSteps;
import testdata.orders.CreateOrdersData;

@Epic("Ez-scooter")
@Feature("Orders")
@Story("[GET] Получение заказа по трек номеру")
public class GetOrderByTrackTest {
    @Test
    @DisplayName("Успешное получение заказа по трек номеру → 200")
    public void getOrderByTrackReturnsCreatedOrder(){
        OrdersSteps.createAndGetOrder(CreateOrdersData.valid())
                .checkResponseIsCorrect(Order.class);
    }

    @ParameterizedTest(name = "{0} → {1}")
    @MethodSource("testdata.orders.GetOrderByTrackData#inValidDataProvider")
    @DisplayName("Получение заказа ")
    public void failsToGetOrderByInvalidTrack(String caseName, int statusCode, Integer track){
        OrdersSteps.getOrderByTrack(track)
                .checkStatusCode(statusCode);
    }
}
