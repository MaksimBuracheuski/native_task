package com.dekstop.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dekstop.saucedemo.SauceDemoBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public class CheckoutCompletePage extends SauceDemoBase {

    @FindBy(xpath = "//span[@class='title']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    private ExtendedWebElement thanksTitle;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(title);
    }

    public boolean isThanksTitlePresent() {
        return thanksTitle.isElementPresent();
    }

}
