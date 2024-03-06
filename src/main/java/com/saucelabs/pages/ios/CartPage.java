package com.saucelabs.pages.ios;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.ioscomponents.ProductCartContainer;
import com.saucelabs.constant.TimeConstant;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.CheckoutPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'YOUR CART'`]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosPredicate = "label == 'CONTINUE SHOPPING' AND name == 'test-CONTINUE SHOPPING'")
    private ExtendedWebElement continueShoppingButton;

    @ExtendedFindBy(iosPredicate = "label == 'CHECKOUT' AND name == 'test-CHECKOUT'")
    private ExtendedWebElement checkoutButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Item'`]")
    private List<ProductCartContainer> products;


    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public boolean isTitlePresent() {
        return title.isElementPresent(TimeConstant.PAGE_OPENED_TO);
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
    public CheckoutPageBase openCheckout() {
        checkoutButton.click();
        return initPage(getDriver(), CheckoutPageBase.class);
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
