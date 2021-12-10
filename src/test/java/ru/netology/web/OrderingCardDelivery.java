package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderingCardDelivery {

    @Test
    public void shouldOrderingCardDelivery() {
        String dataOne = DataGenerator.Data(5);
        String dataTwo = DataGenerator.Data(6);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(DataGenerator.City());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataOne);
        $("[data-test-id='name'] input").setValue(DataGenerator.Name());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Phone());
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $("[class='notification__content']").shouldHave(exactText("Встреча успешно запланирована на " + dataOne), Duration.ofSeconds(16));

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataTwo);
        $("[class='button__content']").click();
        $("[data-test-id='replan-notification'] .notification__content").shouldBe(visible).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(".notification_status_error [class='button__text']").click();
        $(".notification_status_ok [class='notification__title']").shouldHave(exactText("Успешно!"));
    }
}
