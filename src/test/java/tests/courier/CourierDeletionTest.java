package tests.courier;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import steps.CourierSteps;
import utils.CleanupExtension;

import java.net.HttpURLConnection;

@Epic("Ez-scooter")
@Feature("Courier")
@Story("[DELETE] Удаление курьера")
public class CourierDeletionTest {
    @RegisterExtension
    static CleanupExtension cleanup = new CleanupExtension();

    @Test
    @DisplayName("Успешное удаление курьера → 200")
    public void deletesCourierSuccessfully(){
        String id = CourierSteps.createAndLoginCourier().getStringField("id");
        CourierSteps.deleteCourier(id)
                .checkStatusCode(HttpURLConnection.HTTP_OK)
                .checkBodyField("ok", true);
    }

    @Test
    @DisplayName("Удаление курьера без указания id → 400")
    public void failsToDeleteCourierWithoutId(){
        CourierSteps.deleteCourier(null)
                .checkStatusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test
    @DisplayName("Удаление курьера с указанием несуществующего id → 404")
    public void failsToDeleteCourierWithNotExistentId(){
        String id = CourierSteps.createAndLoginCourier().getStringField("id");
        CourierSteps.deleteCourier(id)
                .checkStatusCode(HttpURLConnection.HTTP_OK);
        CourierSteps.deleteCourier(id)
                .checkStatusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }
}
