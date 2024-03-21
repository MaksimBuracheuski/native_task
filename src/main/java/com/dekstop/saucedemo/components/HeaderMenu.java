package com.dekstop.saucedemo.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dekstop.saucedemo.pages.CartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class HeaderMenu extends AbstractUIObject {

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private ExtendedWebElement countOfProduct;

    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public int getCountOfProductInCart() {
        countOfProduct.scrollTo();
        return Integer.parseInt(countOfProduct.getText());
    }

    public boolean isCartEmpty() {
        return countOfProduct.isElementPresent();
    }

    public CartPage openCartPage() {
        cartButton.scrollTo();
        cartButton.click();
        return new CartPage(getDriver());
    }

    public boolean isCartButtonPresent() {
        return cartButton.isElementPresent();
    }
}
