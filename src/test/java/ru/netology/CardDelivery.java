package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.Duration;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;



public class CardDelivery {
    private LocalDate plusDays(int n) {
        LocalDate date = LocalDate.now();
        date = date.plusDays(n);

        return date;
    }

    @Test
    void shouldSendForm() {
        open("http://localhost:9999");
        $$("[type=text]").first().setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(CONTROL + "a", DELETE);
        String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue("Перегудова Екатерина");
        $("[name=phone]").setValue("+79091231212");
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + newDate));

    }

}
