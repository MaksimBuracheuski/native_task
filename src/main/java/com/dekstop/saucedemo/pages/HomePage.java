package com.dekstop.saucedemo.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dekstop.saucedemo.SauceDemoBase;
import com.dekstop.saucedemo.components.ProductContainer;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

public class HomePage extends SauceDemoBase {

    @FindBy(xpath = "//span[@class='title']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<ProductContainer> products;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://www.saucedemo.com/inventory.html");
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(title);
    }

    public ProductContainer getRandomProducts() {
        ProductContainer productContainer = products.get(new Random().nextInt(products.size()));
        productContainer.scrollTo();
        return productContainer;
    }
}
