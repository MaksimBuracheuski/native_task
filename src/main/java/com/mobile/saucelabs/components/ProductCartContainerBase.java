package com.mobile.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class ProductCartContainerBase extends AbstractUIObject implements IMobileUtils {

    public ProductCartContainerBase(WebDriver driver) {
        super(driver);
    }

    public ProductCartContainerBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract boolean isProductQuantityPresent();

    public abstract boolean isProductNamePresent();

    public abstract boolean isProductDescriptionPresent();

    public abstract boolean isProductPricePresent();

    public abstract boolean isRemoveButtonPresent();

    public abstract void swipeToProductCart();

    public abstract String getProductName();

    public abstract String getProductDescription();

    public abstract String getProductPrice();

    public abstract void clickRemoveButton();
}
