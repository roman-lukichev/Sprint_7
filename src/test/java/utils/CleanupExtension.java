package utils;

import model.request.courier.LoginCourierRequest;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import steps.CourierSteps;
import testdata.courier.LoginCourierData;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;

public class CleanupExtension implements AfterEachCallback {
    private static final Set<LoginCourierRequest> couriers = new HashSet<>();

    public static void addCourierToDeletionList(int statusCode, String login, String password) {
        if(HttpURLConnection.HTTP_CREATED == statusCode){
            couriers.add(LoginCourierData.create(login, password));
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        couriers.forEach(cred -> {
            String id = CourierSteps.loginCourierSilently(cred).getStringField("id");
            if (id != null) {CourierSteps.deleteCourierSilently(id);}
        });
        couriers.clear();
    }
}
