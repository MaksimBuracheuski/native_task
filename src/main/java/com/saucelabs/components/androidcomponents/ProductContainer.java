package com.saucelabs.components.androidcomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.ProductContainerBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductContainerBase.class)
public class ProductContainer extends ProductContainerBase {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']//android.widget.ImageView")
    private ExtendedWebElement productImage;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='test-Item title']")
    private ExtendedWebElement productName;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='test-Price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ADD TO CART']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
    private ExtendedWebElement removeButton;

    public ProductContainer(WebDriver driver) {
        super(driver);
    }

    public ProductContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    @Override
    public void clickRemoveButton() {
        removeButton.click();
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return removeButton.isElementPresent();
    }

    @Override
    public String getProductName() {
        return productName.getText();
    }

    @Override
    public String getProductPrice() {
        swipe(productPrice);
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
