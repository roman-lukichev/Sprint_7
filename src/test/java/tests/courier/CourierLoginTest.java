package tests.courier;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.request.courier.CreateCourierRequest;
import model.request.courier.LoginCourierRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.CourierSteps;
import utils.CleanupExtension;

import java.net.HttpURLConnection;

@Epic("Ez-scooter")
@Feature("Courier")
@Story("[POST] Логин курьера")
public class CourierLoginTest {
    @RegisterExtension
    static CleanupExtension cleanup = new CleanupExtension();

    @Test
    @DisplayName("Логин курьера с корректными данными для входа → 200")
    public void loginCourierWithValidData(){
        CourierSteps.createAndLoginCourier()
                .checkBodyFieldExists("id");
    }

    @ParameterizedTest(name = "{0} → {1}")
    @MethodSource("testdata.courier.LoginCourierData#invalidDataProvider")
    @DisplayName("Логин курьера ")
    public void failsToLoginWithWrongCredentials(String caseName, int expectedStatusCode,
                                                                   CreateCourierRequest createRequest,
                                                                   LoginCourierRequest loginRequest){
        CourierSteps.createCourier(createRequest)
                .checkStatusCode(HttpURLConnection.HTTP_CREATED);
        CourierSteps.loginCourier(loginRequest)
                .checkStatusCode(expectedStatusCode);
    }

}
