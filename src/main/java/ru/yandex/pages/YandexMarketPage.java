package ru.yandex.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;

public class YandexMarketPage extends BasePage {

    private ElementsCollection menu =
            $$x("//div[@data-zone-name='category-link']");
    private ElementsCollection subMenu =
            $$x("//li//div[@data-zone-name='link']");
    private SelenideElement priceFrom =
            $x("//input[@id='glpricefrom']");
    private SelenideElement priceTo =
            $x("//input[@id='glpriceto']");
    private ElementsCollection producerMenu =
            $$x("//li[@class='_1-l0X']//span");
    private SelenideElement twelveButton =
            $x("//button[@id='dropdown-control-1630682931643']");
    private SelenideElement dropDownMenu =
            $x("//div[@data-apiary-widget-name='@MarketNode/SearchPager']/div/div[2]");
    private SelenideElement showFortyEightItems =
            $x("//div[@data-apiary-widget-name='@MarketNode/SearchPager']" +
                    "//span[contains(text(),'Показывать по 48')]");
    private SelenideElement showTwelveItems =
            $x("//div[@data-apiary-widget-name='@MarketNode/SearchPager']" +
                    "//button[contains(text(),'Показывать по 12')]");
    private ElementsCollection itemsCollection =
            $$x("//article[@data-autotest-id='product-snippet']");
    private ElementsCollection itemsCollectionTitles =
            $$x("//h3[@data-zone-name='title']/a");
    private SelenideElement firstValue =
            $x("//article[@data-autotest-id='product-snippet'][1]//h3[@data-zone-name='title']");
    private SelenideElement searchForm =
            $x("//input[@id='header-search']");
    private SelenideElement searchButton =
            $x("//button[@data-r='search-button']");
    private String firstValueText;

    @Step("Выбрать раздел {item}")
    public YandexMarketPage getMenuItem(String item) {
        menu.shouldBe(CollectionCondition.sizeNotEqual(0)).findBy(Condition.text(item)).click();
        return this;
    }

    @Step("Выбрать подраздел {item}")
    public YandexMarketPage getSubMenuItem(String item) {
        subMenu.shouldBe(CollectionCondition.sizeNotEqual(0)).findBy(Condition.text(item)).click();
        return this;
    }

    @Step("Задать параметр цена от {priceValueFrom} до {priceValueTo}")
    public YandexMarketPage setPrice(String priceValueFrom, String priceValueTo) {
        priceFrom.sendKeys(priceValueFrom);
        priceTo.sendKeys(priceValueTo);
        return this;
    }

    @Step("Выбрать производителя {producer}")
    public YandexMarketPage setProducer(String producer) {
        producerMenu.shouldBe(CollectionCondition.sizeNotEqual(0)).findBy(Condition.text(producer)).click();
        return this;
    }

    @Step("Установить количество показываемых элементов: {visible}")
    public YandexMarketPage setVisible(String visible) {
        dropDownMenu.shouldBe(Condition.visible).click();
        $x("//div[@data-apiary-widget-name='@MarketNode/SearchPager']" +
                "//button[contains(text(),'" + visible + "')]").shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Проверить, что на странице отобразилось {size} элементов")
    public YandexMarketPage checkTwelveVisible(int size) {
        itemsCollection.shouldBe(CollectionCondition.size(size));
        return this;
    }

    @Step("Запомнить наименование первого значения в списке")
    public YandexMarketPage getFirstElement() {
        firstValueText = itemsCollectionTitles.get(0).getAttribute("title");
        return this;
    }

    @Step("Ввести в поисковую строку ввести запомненное значение")
    public YandexMarketPage findFirstElement() {
        searchForm.shouldBe(Condition.visible).sendKeys(firstValueText);
        searchButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Проверить, что наименование товара соответствует запомненному значению")
    public YandexMarketPage checkValueFromSearch() {
        System.out.println(firstValueText);
        itemsCollectionTitles.shouldHave(CollectionCondition.sizeLessThan(12))
                .forEach(s -> System.out.println(s.getAttribute("title")));
        Assertions.assertTrue(
                itemsCollectionTitles.shouldHave(CollectionCondition.sizeLessThan(12))
                        .stream()
                        .anyMatch(s -> s.getAttribute("title").equals(firstValueText)));
        return this;
    }
}
