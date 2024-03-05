package com.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class ProductContainer extends AbstractUIObject implements IMobileUtils {
    public ProductContainer(WebDriver driver) {
        super(driver);
    }

    public ProductContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void validateProductContainerElements(SoftAssert softAssert);

    public abstract void clickATBButton();

    public abstract void clickRemoveButton();

    public abstract boolean isRemoveButtonPresent();

    public abstract String getProductName();

    public abstract String getProductPrice();

    public abstract ExtendedWebElement getHandleATBButton();

    public abstract void clickOnImage();
}
