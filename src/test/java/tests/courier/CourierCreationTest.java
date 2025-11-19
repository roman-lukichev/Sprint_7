package tests.courier;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.request.courier.CreateCourierRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.CourierSteps;
import testdata.courier.CreateCourierData;
import utils.CleanupExtension;

import java.net.HttpURLConnection;

@Epic("Ez-scooter")
@Feature("Courier")
@Story("[POST] Создание курьера")
public class CourierCreationTest {
    @RegisterExtension
    static CleanupExtension cleanup = new CleanupExtension();

    @Test
    @DisplayName("Создание курьера с валидными данными → 201")
    public void createCourierWithValidData(){
        CourierSteps.createCourier(CreateCourierData.valid())
                .checkStatusCode(HttpURLConnection.HTTP_CREATED)
                .checkBodyField("ok", true);
    }

    @Test
    @DisplayName("Создание курьера с повторяющимся логином → 409")
    public void failsToCreateCourierWithTheSameLogin(){
        CreateCourierRequest request = CreateCourierData.valid();
        CourierSteps.createCourier(request)
                .checkStatusCode(HttpURLConnection.HTTP_CREATED);
        CourierSteps.createCourier(request)
                .checkStatusCode(HttpURLConnection.HTTP_CONFLICT);
    }

    @ParameterizedTest(name = "[{0}] → 400")
    @MethodSource("testdata.courier.CreateCourierData#invalidDataProvider")
    @DisplayName("Создание курьера c без поля ")
    public void failsToCreateCourierWithInvalidData(String caseName, CreateCourierRequest request){
        CourierSteps.createCourier(request)
                .checkStatusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
