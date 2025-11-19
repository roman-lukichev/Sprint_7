package utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateCreator {
    private static final Faker faker = new Faker();

    public static String randomFutureDate (){
        return LocalDate.now(ZoneId.systemDefault())
                .plusDays(faker.number().numberBetween(1, 180))
                .format(DateTimeFormatter.ISO_DATE);
    }
}
