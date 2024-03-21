package com.dekstop.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dekstop.saucedemo.SauceDemoBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public class CheckoutPage extends SauceDemoBase {

    @FindBy(xpath = "//span[@class='title']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//input[@data-test='firstName']")
    private ExtendedWebElement firstNameField;

    @FindBy(xpath = "//input[@data-test='lastName']")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = "//input[@data-test='postalCode']")
    private ExtendedWebElement zipCodeField;

    @FindBy(xpath = "//input[@data-test='continue']")
    private ExtendedWebElement continueButton;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(title);
    }

    public void typeFirstName(String name) {
        firstNameField.type(name);
    }

    public void typeLastName(String lastName) {
        lastNameField.type(lastName);
    }

    public void typeZipCode(String zipCode) {
        zipCodeField.type(zipCode);
    }

    public CheckoutOverviewPage clickContinueButton() {
        continueButton.click();
        return new CheckoutOverviewPage(getDriver());
    }
}
