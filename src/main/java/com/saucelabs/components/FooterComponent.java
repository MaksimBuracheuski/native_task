package com.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class FooterComponent extends AbstractUIObject {
    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public FooterComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
