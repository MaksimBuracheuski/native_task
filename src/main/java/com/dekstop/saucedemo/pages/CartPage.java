package com.dekstop.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dekstop.saucedemo.SauceDemoBase;
import com.dekstop.saucedemo.components.ProductContainer;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public class CartPage extends SauceDemoBase {

    @FindBy(xpath = "//span[@class='title']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//div[@class='cart_item']")
    private List<ProductContainer> cartProducts;

    @FindBy(xpath = "//button[@data-test='checkout']")
    private ExtendedWebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(title);
    }

    public boolean isCountOfProductCorrect() {
        return cartProducts.size() == getHeaderMenu().getCountOfProductInCart();
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.scrollTo();
        checkoutButton.click();
        return new CheckoutPage(getDriver());
    }
}
