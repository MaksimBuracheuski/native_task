package com.dekstop.saucedemo.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class ProductContainer extends AbstractUIObject {

    @FindBy(xpath = "./div[@class='inventory_item_img']")
    private ExtendedWebElement productImage;

    @FindBy(xpath = ".//div[@class='inventory_item_name ']")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//div[@class='inventory_item_desc']")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory ']")
    private ExtendedWebElement addToCartButton;

    public ProductContainer(WebDriver driver) {
        super(driver);
    }

    public ProductContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isProductPricePresent() {
        return productPrice.isPresent();
    }

    public boolean isProductImagePresent() {
        return productImage.isPresent();
    }

    public boolean isProductNamePresent() {
        return productName.isPresent();
    }

    public boolean isProductDescriptionPresent() {
        return productDescription.isPresent();
    }

    public void clickAddToBagButton() {
        addToCartButton.click();
    }
}
