package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.pages.YandexMarketPage;
import ru.yandex.pages.YandexPage;


import static com.codeborne.selenide.Selenide.*;

public class Tests extends BaseTests {

    @Feature("Проверка Яндекс.Маркет")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @Test
    public void marketTest() {
        open("https://yandex.ru/", YandexPage.class)
                .openFromMenu("Маркет", YandexMarketPage.class)
                .getMenuItem("Компьютеры")
                .getSubMenuItem("Ноутбуки")
                .setPrice("10000", "30000")
                .setProducer("HP")
                .setProducer("Lenovo")
                .setVisible("Показывать по 12")
                .checkTwelveVisible(12)
                .getFirstElement()
                .findFirstElement()
                .checkValueFromSearch();
    }
}
