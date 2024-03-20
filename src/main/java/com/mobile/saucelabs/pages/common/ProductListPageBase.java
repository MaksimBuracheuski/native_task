package com.mobile.saucelabs.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.ProductContainerBase;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;


public abstract class ProductListPageBase extends SauceAbstractPage {

    public ProductListPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract <T extends ProductContainerBase> List<T> getProducts();

    public abstract ProductContainerBase getRandomProductContainer();

    public abstract void addProductToCartByDD(ProductContainerBase productContainer);

    public abstract ProductDetailsPageBase openPDP(ProductContainerBase productContainer);
}
