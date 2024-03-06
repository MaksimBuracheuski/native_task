package com.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class FooterComponentBase extends AbstractUIObject {

    public FooterComponentBase(WebDriver driver) {
        super(driver);
    }

    public FooterComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
