package com.mobile.saucelabs.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.ProductCartContainerBase;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public abstract class CheckoutPageBase extends SauceAbstractPage {

    public CheckoutPageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract <T extends ProductCartContainerBase> List<T> getProducts();

    public abstract boolean isPaymentInformationPresent();

    public abstract String getPaymentInformation();

    public abstract boolean isPaymentShippingPresent();

    public abstract boolean isItemTotalPresent();

    public abstract boolean isTotalPresent();

    public abstract boolean isTaxPresent();

    public abstract String getItemTotal();

    public abstract ProductListPageBase clickCancel();

    public abstract OrderPageBase clickFinishButton();
}
