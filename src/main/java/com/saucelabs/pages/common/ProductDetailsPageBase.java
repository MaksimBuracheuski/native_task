package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public abstract class ProductDetailsPageBase extends SauceAbstractPage {

    public ProductDetailsPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract boolean isProductNamePresent();

    public abstract boolean isProductDescriptionPresent();

    public abstract boolean isProductImagePresent();

    public abstract boolean isProductPricePresent();

    public abstract boolean isATBButtonPresent();

    public abstract String getProductName();

    public abstract String getProductDescription();

    public abstract String getProductPrice();

    public abstract boolean isRemoveButtonPresent();

    public abstract void clickATBButton();

    public abstract void clickRemoveButton();

    public abstract ProductListPageBase clickBackButton();


}
