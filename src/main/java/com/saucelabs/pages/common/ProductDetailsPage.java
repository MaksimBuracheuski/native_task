package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.saucelabs.dto.product.Product;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public abstract class ProductDetailsPage extends SoucelabsAbsrtactPage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract void validateBaseElementsOnPDP(SoftAssert softAssert);

    public abstract void validateProductData(Product product, SoftAssert softAssert);

    public abstract String getProductName();

    public abstract String getProductDescription();

    public abstract String getProductPrice();

    public abstract boolean isRemoveButtonPresent();

    public abstract void clickATBButton();

    public abstract void clickRemoveButton();

    public abstract ProductListPageBase clickBackButton();


}
