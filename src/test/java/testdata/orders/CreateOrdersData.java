package testdata.orders;

import com.github.javafaker.Faker;
import model.request.orders.CreateOrderRequest;
import org.junit.jupiter.params.provider.Arguments;
import utils.DateCreator;

import java.util.stream.Stream;

public class CreateOrdersData {
    private static final Faker faker = new Faker();

    public static CreateOrderRequest valid() {
        return CreateOrderRequest.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .address(faker.address().fullAddress())
                .metroStation(faker.number().numberBetween(1, 5))
                .phone(faker.phoneNumber().phoneNumber())
                .rentTime(faker.number().numberBetween(1, 10))
                .deliveryDate(DateCreator.randomFutureDate())
                .comment(faker.lorem().sentence())
                .color(ScooterColorArrays.Black)
                .build();
    }

    public static CreateOrderRequest validWithBlackColor () {
        return valid().toBuilder().color(ScooterColorArrays.Black).build();
    }
    public static CreateOrderRequest validWithGreyColor () {
        return valid().toBuilder().color(ScooterColorArrays.Grey).build();
    }
    public static CreateOrderRequest validWithGreyAndBlackColor () {
        return valid().toBuilder().color(ScooterColorArrays.BlackAndGrey).build();
    }
    public static CreateOrderRequest validWithoutColor () {
        return valid().toBuilder().color(ScooterColorArrays.EmptyColorArray).build();
    }

    public static Stream<Arguments> validDataProvider() {
        return Stream.of(
                Arguments.of(
                        "BLACK",
                        validWithBlackColor()
                ),
                Arguments.of(
                        "GREY",
                        validWithGreyColor()
                ),
                Arguments.of(
                        "BLACK, GREY",
                        validWithGreyAndBlackColor()
                ),
                Arguments.of(
                        "",
                        validWithoutColor()
                )
        );
    }

}
