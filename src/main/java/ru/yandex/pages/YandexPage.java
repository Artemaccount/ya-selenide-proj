package ru.yandex.pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.*;

public class YandexPage extends BasePage {

    private SelenideElement marketButton = $x("//a[@data-id='market']");
    private ElementsCollection servicesButtons = $$x("//li[@class='services-new__list-item']");
    private ElementsCollection menu = $$x("//div[@class='services-new__item-title']");

    @Step("Перейти в Яндекс.{item}")
    public <T extends BasePage> T openFromMenu(String item, Class<T> typeNextPage) {
        menu.shouldBe(CollectionCondition.sizeNotEqual(0)).findBy(Condition.text(item)).click();
        switchTo().window(1);
        return typeNextPage.cast(page(typeNextPage));
    }
}
