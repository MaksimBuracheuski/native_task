package com.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.dto.identity.User;
import com.saucelabs.pages.common.LoginPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc='test-Login']//android.widget.ImageView")
    public ExtendedWebElement title;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    public ExtendedWebElement usernameField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    public ExtendedWebElement passwordField;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
    public ExtendedWebElement loginButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']")
    public ExtendedWebElement lockedOutErrorMessage;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-%s']")
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
        userBtn.isElementPresent();
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
