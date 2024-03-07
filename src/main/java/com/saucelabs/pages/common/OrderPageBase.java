package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public abstract class OrderPageBase extends SauceAbstractPage {

    public OrderPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract ProductListPageBase clickBackHomeButton();
}
