package com.saucelabs.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ProductCartContainer;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class CartPageBase extends AbstractPage {
    public CartPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract <T extends ProductCartContainer> List<T> getProducts();

    public abstract void validateCartElements(SoftAssert softAssert);

    public abstract CheckoutBasePage openCheckout();

    public abstract ProductListPageBase clickContinueShoppingButton();
}
