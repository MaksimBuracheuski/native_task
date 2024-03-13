package com.saucelabs.components.androidcomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.ProductCartContainerBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductCartContainerBase.class)
public class ProductCartContainer extends ProductCartContainerBase implements IMobileUtils {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Amount']")
    private ExtendedWebElement productQuantity;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
    private ExtendedWebElement productName;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[2]")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
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
