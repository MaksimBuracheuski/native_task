package com.mobile.saucelabs.pages.android;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.mobile.saucelabs.components.ProductContainerBase;
import com.mobile.saucelabs.components.androidcomponents.ProductContainer;
import com.mobile.saucelabs.pages.common.ProductDetailsPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductListPageBase.class)
public class ProductListPage extends ProductListPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
    private List<ProductContainer> products;

    @FindBy(xpath = "//android.widget.TextView[@text='%s']/parent::android.view.ViewGroup/android.view.ViewGroup[@content-desc='test-Drag Handle']")
    private ExtendedWebElement drugHandleButton;

    @FindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private ExtendedWebElement productField;

    public ProductListPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(productField);
    }

    @Override
    public ProductContainerBase getRandomProductContainer() {
        ProductContainerBase productContainer = products.get(new Random().nextInt(products.size()));
        swipe(productContainer);
        return productContainer;
    }

    @Override
    public void addProductToCartByDD(ProductContainerBase productContainer) {
        dragAndDrop(drugHandleButton.format(productContainer.getProductName()), productField);
        drugHandleButton.format(productContainer.getProductName()).isElementPresent();
    }

    @Override
    public ProductDetailsPageBase openPDP(ProductContainerBase productContainer) {
        productContainer.clickOnImage();
        return initPage(getDriver(), ProductDetailsPageBase.class);
    }

    @Override
    public List<ProductContainer> getProducts() {
        return products;
    }
}

