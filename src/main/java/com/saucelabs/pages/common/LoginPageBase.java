package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucelabs.dto.identity.User;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

    public LoginPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract ProductListPageBase login(User user);

    public abstract ProductListPageBase loginByAutofill(User user);

    public abstract void typeUserData(User user);

    public abstract void typeUserName(User user);

    public abstract void typeUserPassword(User user);

    public abstract void clickLoginButton();

    public abstract boolean isLockedOutErrorPresent();
}
