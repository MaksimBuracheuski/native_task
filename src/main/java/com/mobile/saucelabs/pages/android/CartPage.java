package com.mobile.saucelabs.pages.android;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.mobile.saucelabs.components.androidcomponents.ProductCartContainer;
import com.mobile.saucelabs.pages.common.CartPageBase;
import com.mobile.saucelabs.pages.common.CheckoutInfoPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.widget.TextView[@text='YOUR CART']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.widget.TextView[@text='CONTINUE SHOPPING']")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(xpath = "//android.widget.TextView[@text='CHECKOUT']")
    private ExtendedWebElement checkoutButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
    private List<ProductCartContainer> products;

    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public boolean isCartEmpty() {
        return products.isEmpty();
    }

    @Override
    public boolean isTitlePresent() {
        return title.isElementPresent();
    }

    @Override
    public boolean isContinueShoppingButtonPresent() {
        return swipe(continueShoppingButton);
    }

    @Override
    public boolean isCheckoutButtonPresent() {
        return swipe(checkoutButton);
    }

    @Override
    public CheckoutInfoPageBase openCheckoutInfoPage() {
        checkoutButton.click();
        return initPage(getDriver(), CheckoutInfoPageBase.class);
    }

    @Override
    public ProductListPageBase clickContinueShoppingButton() {
        continueShoppingButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public List<ProductCartContainer> getProducts() {
        return products;
    }
}
