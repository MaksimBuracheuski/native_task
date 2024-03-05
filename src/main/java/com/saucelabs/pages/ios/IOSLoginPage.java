package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.dto.identity.User;
import com.saucelabs.pages.common.LoginPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class IOSLoginPage extends LoginPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'Username Password LOGIN'`]/XCUIElementTypeOther[1]/XCUIElementTypeImage")
    public ExtendedWebElement title;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeTextField[`name == 'test-Username'`]")
    public ExtendedWebElement usernameField;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeSecureTextField[`name == 'test-Password'`]")
    public ExtendedWebElement passwordField;

    @ExtendedFindBy(iosPredicate = "label == 'LOGIN' AND name == 'test-LOGIN'")
    public ExtendedWebElement loginButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'Sorry, this user has been locked out.'`]")
    public ExtendedWebElement lockedOutErrorMessage;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'standard_user'`]")
    public ExtendedWebElement standardUserButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'problem_user'`]")
    public ExtendedWebElement problemUserButton;

    public IOSLoginPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public ProductListPageBase login(User user) {
        usernameField.type(user.getUsername());
        passwordField.type(user.getPassword());
        loginButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public ProductListPageBase loginByAutofill(User user) {
        if (user.getUsername().equals(standardUserButton.getText())) {
            swipe(standardUserButton);
            standardUserButton.click();
        } else {
            swipe(problemUserButton);
            problemUserButton.click();
        }
        loginButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public void typeUserData(User user) {
        usernameField.type(user.getUsername());
        passwordField.type(user.getPassword());
    }

    @Override
    public void clickLoginButton() {
        loginButton.click();
    }

    @Override
    public boolean isLockedOutErrorPresent() {
        return lockedOutErrorMessage.isElementPresent();
    }
}
