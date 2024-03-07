package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.constant.TimeConstant;
import com.saucelabs.dto.identity.User;
import com.saucelabs.pages.common.CheckoutInfoPageBase;
import com.saucelabs.pages.common.CheckoutPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CheckoutInfoPageBase.class)
public class CheckoutInfoPage extends CheckoutInfoPageBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'CHECKOUT: INFORMATION'`]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeTextField[`name == 'test-First Name'`]")
    private ExtendedWebElement firstNameField;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeTextField[`name == 'test-Last Name'`]")
    private ExtendedWebElement lastNameField;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeTextField[`name == 'test-Zip/Postal Code'`]")
    private ExtendedWebElement zipField;

    @ExtendedFindBy(iosPredicate = "label == 'CANCEL' AND name == 'test-CANCEL'")
    private ExtendedWebElement cancelButton;

    @ExtendedFindBy(iosPredicate = "label == 'CONTINUE' AND name == 'test-CONTINUE'")
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
        return continueButton.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isCancelButtonPresent() {
        return cancelButton.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isFirstNameFieldPresent() {
        return firstNameField.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isLastNameFieldPresent() {
        return lastNameField.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isZipFieldPresent() {
        return zipField.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public String getFirstName() {
        return firstNameField.getText();
    }
}
