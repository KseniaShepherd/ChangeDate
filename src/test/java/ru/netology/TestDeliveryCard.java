package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGeneration;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDeliveryCard {
    @BeforeAll
    static void setUpAll()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll()
    {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldOfferRescheduleMeetingTime() {
        DataGeneration.generatDate(4);
        DataGeneration.UserInfoGeneration userInfoGeneration = DataGeneration.generateUserInfo("ru");
        DataGeneration.DateGeneration dateGeneration = DataGeneration.generatDate(4);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(userInfoGeneration.getCity());
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("input[placeholder='Дата встречи']").setValue(dateGeneration.getDate());
        $("[data-test-id=name] input").setValue(userInfoGeneration.getName());
        $("[data-test-id=phone] input").setValue(userInfoGeneration.getPhone());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();

        $("[data-test-id ='success-notification'] [class ='notification__content']").shouldHave(text
                ("Встреча успешно запланирована на " + dateGeneration.getDate())).shouldBe(visible, Duration.ofSeconds(60));

        DataGeneration.DateGeneration modifiedВateGeneration = DataGeneration.generatDate(5);
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("input[placeholder='Дата встречи']").setValue(modifiedВateGeneration.getDate());
        $(withText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible, Duration.ofHours(1));
        $(withText("Перепланировать")).click();

        $("[data-test-id ='success-notification'] [class ='notification__content']").shouldHave(text
                ("Встреча успешно запланирована на " + modifiedВateGeneration.getDate())).shouldBe(visible, Duration.ofSeconds(60));
    }
}

