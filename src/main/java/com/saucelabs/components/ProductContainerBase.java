package com.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class ProductContainerBase extends AbstractUIObject implements IMobileUtils {

    public ProductContainerBase(WebDriver driver) {
        super(driver);
    }

    public ProductContainerBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void clickAddToCartButton();

    public abstract void clickRemoveButton();

    public abstract boolean isRemoveButtonPresent();

    public abstract String getProductName();

    public abstract String getProductPrice();

    public abstract void clickOnImage();

    public abstract boolean isATBButtonPresent();

    public abstract boolean isProductPricePresent();

    public abstract boolean isProductNamePresent();

    public abstract boolean isProductImagePresent();
}
