package utils;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class DataGeneration {

    private String city;
    private String date;
    private String name;
    private String phone;

    public void dataGeneration(String locale) {

        Faker faker = new Faker(new Locale(locale));
        this.city = faker.address().city();
        this.name = faker.name().fullName();
        this.phone = faker.phoneNumber().phoneNumber();
    }

    public void dateGeneration(int numbesDays) {
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        LocalDate today = LocalDate.now(zoneId).plusDays(numbesDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.date = today.format(formatter);
    }

}
