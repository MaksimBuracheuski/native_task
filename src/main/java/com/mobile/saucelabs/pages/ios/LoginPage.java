package com.mobile.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.dto.identity.User;
import com.mobile.saucelabs.pages.common.LoginPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils {

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

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == '%s'`]")
    public ExtendedWebElement userButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public ProductListPageBase login(User user) {
        typeUserData(user);
        clickLoginButton();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public ProductListPageBase loginByAutofill(User user) {
        ExtendedWebElement userBtn = userButton.format(user.getUsername());
        swipe(userBtn);
        userBtn.click();
        clickLoginButton();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public void typeUserData(User user) {
        typeUserName(user);
        typeUserPassword(user);
    }

    @Override
    public void typeUserName(User user) {
        usernameField.type(user.getUsername());
    }

    @Override
    public void typeUserPassword(User user) {
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
