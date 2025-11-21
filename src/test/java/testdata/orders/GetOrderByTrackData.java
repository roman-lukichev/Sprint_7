package testdata.orders;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.net.HttpURLConnection;
import java.util.stream.Stream;

public class GetOrderByTrackData {
    private static final Faker faker = new Faker();

    public static Stream<Arguments> inValidDataProvider() {
        return Stream.of(
                Arguments.of(
                        "без трек номера",
                        HttpURLConnection.HTTP_BAD_REQUEST,
                        null
                ),
                Arguments.of(
                        "с несуществующим трек номером",
                        HttpURLConnection.HTTP_NOT_FOUND,
                        faker.number().numberBetween(9000000, 10000000)
                )
        );
    }
}
