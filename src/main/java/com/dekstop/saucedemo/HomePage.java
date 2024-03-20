package com.dekstop.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//span[@class='title']")
    private ExtendedWebElement title;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(title);
    }
}
