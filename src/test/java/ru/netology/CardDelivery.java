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
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue("Перегудова Екатерина");
        $("[name=phone]").setValue("+79091231212");
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + newDate));

    }
    @Test
    void shouldDate() {
        open("http://localhost:9999");
        $$("[type=text]").first().setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String newDate = plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue("Перегудова Екатерина");
        $("[name=phone]").setValue("+79091231212");
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $("fieldset > div:nth-child(2) > span > span > span > span > span.input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }
@Test
void shouldName() {
    open("http://localhost:9999");
    $$("[type=text]").first().setValue("Москва");
    $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
    String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    $("[placeholder='Дата встречи']").setValue(newDate);
    $("[data-test-id=name] [type=text]").setValue("Peregudova Ekaterina");
    $("[name=phone]").setValue("+79091231212");
    $(".checkbox__box").click();
    $("div.form-field>[type=button]").submit();
    $("div:nth-child(3) > span > span > span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

}
    @Test
    void shouldTel() {
        open("http://localhost:9999");
        $$("[type=text]").first().setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue("Перегудова Екатерина");
        $("[name=phone]").setValue("+790912312120");
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $("div:nth-child(4) > span > span > span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
    @Test
    void shouldNotCheckbox() {
        open("http://localhost:9999");
        $$("[type=text]").first().setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String newDate = plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(newDate);
        $("[data-test-id=name] [type=text]").setValue("Перегудова Екатерина");
        $("[name=phone]").setValue("+790912312120");
        $("div.form-field>[type=button]").submit();
        $(".checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }



}
