package com.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ProductContainer;
import com.saucelabs.constant.TimeConstant;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductContainer.class)
public class IOSProductContainer extends ProductContainer {
    @FindBy(xpath = ".//XCUIElementTypeImage")
    private ExtendedWebElement productImage;

    @FindBy(xpath = ".//XCUIElementTypeStaticText[@name='test-Item title']")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//XCUIElementTypeStaticText[@name='test-Price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='ADD TO CART']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='test-REMOVE']")
    private ExtendedWebElement removeButton;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='test-Drag Handle']")
    private ExtendedWebElement handleATBButton;

    public IOSProductContainer(WebDriver driver) {
        super(driver);
    }

    public IOSProductContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void validateProductContainerElements(SoftAssert softAssert) {
        softAssert.assertTrue(swipe(addToCartButton), "ATB button isn't presented in product container");
        softAssert.assertTrue(swipe(productPrice), "Product price button isn't presented in product container");
        softAssert.assertTrue(swipe(productImage), "Product image button isn't presented in product container");
        softAssert.assertTrue(swipe(productName), "Product name button isn't presented in product container");

    }

    @Override
    public void clickATBButton() {
        addToCartButton.click();
    }

    @Override
    public void clickRemoveButton() {
        removeButton.click();
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return removeButton.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public String getProductName() {
        return productName.getText();
    }

    @Override
    public String getProductPrice() {
        return productPrice.getText();
    }

    @Override
    public ExtendedWebElement getHandleATBButton() {
        return handleATBButton;
    }

    @Override
    public void clickOnImage() {
        productImage.click();
    }
}
