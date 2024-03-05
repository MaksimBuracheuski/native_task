package com.saucelabs.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.ProductContainer;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;


public abstract class ProductListPageBase extends SoucelabsAbsrtactPage {
    public ProductListPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract <T extends ProductContainer> List<T> getProducts();

    public abstract ProductContainer getRandomProductContainer();

    public abstract void addProductToCartByDD(ProductContainer productContainer);

    public abstract ProductDetailsPage openPDP(ProductContainer productContainer);

}
