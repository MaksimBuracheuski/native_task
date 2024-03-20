package com.mobile.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public abstract class DrawingPageBase extends SauceAbstractPage{

    public DrawingPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract void drawElement();

    public abstract boolean isDrawnImagePresent();

    public abstract void clickClearButton();
}
