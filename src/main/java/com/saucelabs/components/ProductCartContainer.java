package com.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.saucelabs.dto.product.Product;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class ProductCartContainer extends AbstractUIObject implements IMobileUtils {
    public ProductCartContainer(WebDriver driver) {
        super(driver);
    }

    public ProductCartContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void validateBaseElements(SoftAssert softAssert);

    public abstract void validateProductInCart(Product product, SoftAssert softAssert);

    public abstract void clickRemoveButton();
}
