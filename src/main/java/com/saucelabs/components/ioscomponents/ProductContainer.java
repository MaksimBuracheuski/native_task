package com.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.saucelabs.components.ProductContainerBase;
import com.saucelabs.constant.TimeConstant;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductContainerBase.class)
public class ProductContainer extends ProductContainerBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == \"\uDB81\uDF41\"`]/**/XCUIElementTypeImage")
    private ExtendedWebElement productImage;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name == 'test-Item title'`]")
    private ExtendedWebElement productName;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name == 'test-Price'`]")
    private ExtendedWebElement productPrice;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'ADD TO CART'`]/XCUIElementTypeOther")
    private ExtendedWebElement addToCartButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'REMOVE'`]/XCUIElementTypeOther")
    private ExtendedWebElement removeButton;

    public ProductContainer(WebDriver driver) {
        super(driver);
    }

    public ProductContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
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
    public void clickOnImage() {
        productImage.click();
    }

    @Override
    public boolean isATBButtonPresent() {
        return swipe(addToCartButton);
    }

    @Override
    public boolean isProductPricePresent() {
        return swipe(productPrice);
    }

    @Override
    public boolean isProductNamePresent() {
        return swipe(productName);
    }

    @Override
    public boolean isProductImagePresent() {
        return swipe(productImage);
    }
}
