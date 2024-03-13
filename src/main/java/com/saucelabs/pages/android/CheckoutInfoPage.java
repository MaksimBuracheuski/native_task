package com.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.dto.identity.User;
import com.saucelabs.pages.common.CheckoutInfoPageBase;
import com.saucelabs.pages.common.CheckoutPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CheckoutInfoPageBase.class)
public class CheckoutInfoPage extends CheckoutInfoPageBase {

    @FindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: INFORMATION']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-First Name']")
    private ExtendedWebElement firstNameField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-Last Name']")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='test-Zip/Postal Code']")
    private ExtendedWebElement zipField;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CANCEL']")
    private ExtendedWebElement cancelButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CONTINUE']")
    private ExtendedWebElement continueButton;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public CheckoutPageBase clickContinueButton() {
        continueButton.click();
        return initPage(getDriver(), CheckoutPageBase.class);
    }

    @Override
    public ProductListPageBase clickCancelButton() {
        cancelButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public void typeZipData(User user) {
        zipField.type(user.getZipCode());
    }

    @Override
    public void typeUserFirstNameData(User user) {
        firstNameField.type(user.getUsername());
    }

    @Override
    public void typeUserLastNameData(User user) {
        lastNameField.type(user.getLastName());
    }

    @Override
    public void typeUserData(User user) {
        typeUserFirstNameData(user);
        typeUserLastNameData(user);
        typeZipData(user);
    }

    @Override
    public boolean isContinueButtonPresent() {
        return continueButton.isElementPresent();
    }

    @Override
    public boolean isCancelButtonPresent() {
        return cancelButton.isElementPresent();
    }

    @Override
    public boolean isFirstNameFieldPresent() {
        return firstNameField.isElementPresent();
    }

    @Override
    public boolean isLastNameFieldPresent() {
        return lastNameField.isElementPresent();
    }

    @Override
    public boolean isZipFieldPresent() {
        return zipField.isElementPresent();
    }

    @Override
    public String getFirstName() {
        return firstNameField.getText();
    }
}
