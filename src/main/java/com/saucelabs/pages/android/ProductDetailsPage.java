package com.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.pages.common.ProductDetailsPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailsPageBase.class)
public class ProductDetailsPage extends ProductDetailsPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-BACK TO PRODUCTS']")
    private ExtendedWebElement backButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Image Container']/android.widget.ImageView")
    private ExtendedWebElement productImage;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
    private ExtendedWebElement productName;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[2]")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='test-Price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ADD TO CART']")
    private ExtendedWebElement addToBagButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
    private ExtendedWebElement removeButton;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(backButton);
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
    public boolean isProductImagePresent() {
        return productImage.isElementPresent();
    }

    @Override
    public boolean isProductPricePresent() {
        return swipe(productPrice);
    }

    @Override
    public boolean isATBButtonPresent() {
        return swipe(addToBagButton);
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
        swipe(productPrice);
        return productPrice.getText();
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return swipe(removeButton);
    }

    @Override
    public void clickAddToCartButton() {
        swipe(addToBagButton);
        addToBagButton.click();
    }

    @Override
    public void clickRemoveButton() {
        swipe(removeButton);
        removeButton.click();
    }

    @Override
    public ProductListPageBase clickBackButton() {
        swipe(backButton);
        backButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }
}
