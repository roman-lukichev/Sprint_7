package testdata.courier;

import com.github.javafaker.Faker;
import model.request.courier.CreateCourierRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class CreateCourierData {
    private static final Faker faker = new Faker();

    public static CreateCourierRequest valid() {
        return CreateCourierRequest.builder()
                .login(faker.name().username() + faker.number().randomNumber())
                .password("123123")
                .firstName(faker.name().firstName())
                .build();
    }
    public static CreateCourierRequest withoutPassword() {
        return valid().toBuilder().password(null).build();
    }
    public static CreateCourierRequest withoutLogin() {
        return valid().toBuilder().login(null).build();
    }
    public static CreateCourierRequest withoutFirstName() {
        return valid().toBuilder().firstName(null).build();
    }

    public static Stream<Arguments> invalidDataProvider() {
        return Stream.of(
                Arguments.of(
                        "password",
                        withoutPassword()
                ),
                Arguments.of(
                        "login",
                        withoutLogin()
                ),
                Arguments.of(
                        "firstName",
                        withoutFirstName()
                )
        );
    }

}
