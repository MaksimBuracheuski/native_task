package com.mobile.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.dto.identity.User;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public abstract class CheckoutInfoPageBase extends SauceAbstractPage {

    public CheckoutInfoPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract CheckoutPageBase clickContinueButton();

    public abstract ProductListPageBase clickCancelButton();

    public abstract void typeZipData(User user);

    public abstract void typeUserFirstNameData(User user);

    public abstract void typeUserLastNameData(User user);

    public abstract void typeUserData(User user);

    public abstract boolean isContinueButtonPresent();

    public abstract boolean isCancelButtonPresent();

    public abstract boolean isFirstNameFieldPresent();

    public abstract boolean isLastNameFieldPresent();

    public abstract boolean isZipFieldPresent();

    public abstract String getFirstName();
}
