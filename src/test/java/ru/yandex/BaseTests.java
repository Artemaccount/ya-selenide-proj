package ru.yandex;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {

    @BeforeEach
    public void beforeEachTest(){
        Configuration.browser = "firefox";
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = "none";
        Configuration.timeout = 20000;
    }
}
