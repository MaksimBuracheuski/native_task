package com.mobile.saucelabs.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.ProductCartContainerBase;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class CartPageBase extends AbstractPage {

    public CartPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract <T extends ProductCartContainerBase> List<T> getProducts();

    public abstract boolean isCartEmpty();

    public abstract boolean isTitlePresent();

    public abstract boolean isContinueShoppingButtonPresent();

    public abstract boolean isCheckoutButtonPresent();

    public abstract CheckoutInfoPageBase openCheckoutInfoPage();

    public abstract ProductListPageBase clickContinueShoppingButton();
}
