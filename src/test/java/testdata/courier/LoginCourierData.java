package testdata.courier;

import com.github.javafaker.Faker;
import model.request.courier.CreateCourierRequest;
import model.request.courier.LoginCourierRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.net.HttpURLConnection;
import java.util.stream.Stream;

public class LoginCourierData {
    private static final Faker faker = new Faker();

    public static LoginCourierRequest create(String login, String password) {
        return LoginCourierRequest.builder()
                .login(login)
                .password(password)
                .build();
    }

    public static LoginCourierRequest createFrom(CreateCourierRequest body) {
        return create(body.getLogin(), body.getPassword());
    }

    public static LoginCourierRequest withWrongPasswordFrom(CreateCourierRequest body) {
        return createFrom(body).toBuilder().password(faker.internet().password()).build();
    }

    public static LoginCourierRequest withWrongLoginFrom (CreateCourierRequest body) {
        return createFrom(body).toBuilder().login(faker.name().username()).build();
    }

    public static LoginCourierRequest withoutPasswordFrom(CreateCourierRequest body) {
        return createFrom(body).toBuilder().password(null).build();
    }

    public static LoginCourierRequest withoutLoginFrom(CreateCourierRequest body) {
        return createFrom(body).toBuilder().login(null).build();
    }

    public static Stream<Arguments> invalidDataProvider() {
        CreateCourierRequest createCourierRequest = CreateCourierData.valid();
        return Stream.of(
                Arguments.of(
                        "c неверным полем [password]",
                        HttpURLConnection.HTTP_NOT_FOUND,
                        createCourierRequest,
                        withWrongPasswordFrom(createCourierRequest)
                ),
                Arguments.of(
                        "c неверным полем [login]",
                        HttpURLConnection.HTTP_NOT_FOUND,
                        createCourierRequest,
                        withWrongLoginFrom(createCourierRequest)
                ),
                Arguments.of(
                        "без поля [password]",
                        HttpURLConnection.HTTP_BAD_REQUEST,
                        createCourierRequest,
                        withoutPasswordFrom(createCourierRequest)
                ),
                Arguments.of(
                        "без поля [login]",
                        HttpURLConnection.HTTP_BAD_REQUEST,
                        createCourierRequest,
                        withoutLoginFrom(createCourierRequest)
                )
        );
    }
}
