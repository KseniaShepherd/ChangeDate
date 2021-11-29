package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGeneration;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDeliveryCard {

    @Test
    void shouldOfferRescheduleMeetingTime() {
        DataGeneration data = new DataGeneration();
        data.dataGeneration("ru");
        data.dateGeneration(4);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(data.getCity());
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("input[placeholder='Дата встречи']").setValue(data.getDate());
        $("[data-test-id=name] input").setValue(data.getName());
        $("[data-test-id=phone] input").setValue(data.getPhone());
        $("[data-test-id=agreement]").click();
        $("div.grid-col.grid-col_width_3.grid-col_theme_alfa-on-white." +
                "grid-col_gutter-mobile-s_16.grid-col_gutter-desktop-m_24 > div > button").click();
        $("#root > div > div.notification.notification_status_ok.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white > div.notification__content").shouldBe(Condition.text
                ("Встреча успешно запланирована на ")).shouldBe(Condition.visible, Duration.ofSeconds(120));
        data.dateGeneration(5);
        $("input[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("input[placeholder='Дата встречи']").setValue(data.getDate());
        $("div.grid-col.grid-col_width_3.grid-col_theme_alfa-on-white." +
                "grid-col_gutter-mobile-s_16.grid-col_gutter-desktop-m_24 > div > button").click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible, Duration.ofHours(1));
        $("div.notification__content > button").click();
        $("#root > div > div.notification.notification_status_ok.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white > div.notification__content").shouldBe(Condition.text
                ("Встреча успешно запланирована на ")).shouldBe(Condition.visible, Duration.ofSeconds(120));
    }
}

