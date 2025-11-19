package testdata.orders;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.net.HttpURLConnection;
import java.util.stream.Stream;

public class OrderAcceptanceData {
    private static final Faker faker = new Faker();

    public static Stream<Arguments> inValidCourierIdDataProvider() {
        return Stream.of(
                Arguments.of(
                        "без указания id курьера",
                        HttpURLConnection.HTTP_BAD_REQUEST,
                        null
                ),
                Arguments.of(
                        "с несуществующим id курьера",
                        HttpURLConnection.HTTP_NOT_FOUND,
                        faker.number().numberBetween(90000000, 100000000)
                )
        );
    }

    public static Stream<Arguments> inValidOrderIdDataProvider() {
        return Stream.of(
                Arguments.of(
                        "без указания id заказа",
                        HttpURLConnection.HTTP_BAD_REQUEST,
                        null
                ),
                Arguments.of(
                        "с несуществующим id заказа",
                        HttpURLConnection.HTTP_NOT_FOUND,
                        faker.number().numberBetween(90000000, 100000000)
                )
        );
    }
}
