package com.mobile.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.ProductCartContainerBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductCartContainerBase.class)
public class ProductCartContainer extends ProductCartContainerBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Amount'`]")
    private ExtendedWebElement productQuantity;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Description'`]/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement productName;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Description'`]/XCUIElementTypeStaticText[2]")
    private ExtendedWebElement productDescription;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Price'`]/XCUIElementTypeStaticText")
    private ExtendedWebElement productPrice;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'REMOVE'`]")
    private ExtendedWebElement removeButton;

    public ProductCartContainer(WebDriver driver) {
        super(driver);
    }

    public ProductCartContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public boolean isProductQuantityPresent() {
        return productQuantity.isElementPresent();
    }

    @Override
    public boolean isProductNamePresent() {
        return productName.isElementPresent();
    }

    @Override
    public boolean isProductDescriptionPresent() {
        return productDescription.isElementPresent();
    }

    @Override
    public boolean isProductPricePresent() {
        return productPrice.isElementPresent();
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return removeButton.isElementPresent();
    }

    @Override
    public void swipeToProductCart() {
        swipe(removeButton);
    }

    @Override
    public String getProductName() {
        return productName.getText();
    }

    @Override
    public String getProductDescription() {
        return productDescription.getText();
    }

    @Override
    public String getProductPrice() {
        return productPrice.getText();
    }

    @Override
    public void clickRemoveButton() {
        removeButton.click();
    }
}
