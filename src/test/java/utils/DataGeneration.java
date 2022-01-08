package utils;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGeneration {


    private DataGeneration() {
    }

    @Value
    public static class UserInfoGeneration {
        private String city;
        private String name;
        private String phone;
    }

    public static UserInfoGeneration generateUserInfo(String locale) {
        Faker faker = new Faker(new Locale(locale));
        var city = faker.address().city();
        var name = faker.name().fullName();
        var phone = faker.phoneNumber().phoneNumber();
        return new UserInfoGeneration(city, name, phone);

    }

    @Value
    public static class DateGeneration {
        private String date;
    }

    public static DateGeneration generatDate(int numbesDays) {
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        LocalDate today = LocalDate.now(zoneId).plusDays(numbesDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var date = today.format(formatter);
        return new DateGeneration(date);
    }

}
