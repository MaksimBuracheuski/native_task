package com.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ProductCartContainer;
import com.saucelabs.constant.TimeConstant;
import com.saucelabs.dto.product.Product;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductCartContainer.class)
public class IOSProductCartContainer extends ProductCartContainer implements IMobileUtils {
    @FindBy(xpath = "./XCUIElementTypeOther[@name='test-Amount']")
    private ExtendedWebElement productQuantity;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='test-Description']/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='test-Description']/XCUIElementTypeStaticText[2]")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='test-Price']/XCUIElementTypeStaticText")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = ".//XCUIElementTypeOther[@name='REMOVE']")
    private ExtendedWebElement removeButton;

    public IOSProductCartContainer(WebDriver driver) {
        super(driver);
    }

    public IOSProductCartContainer(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void validateBaseElements(SoftAssert softAssert) {
        swipe(removeButton);
        softAssert.assertTrue(productQuantity.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Quantity of product isn't presented");
        softAssert.assertTrue(productName.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product name isn't presented");
        softAssert.assertTrue(productDescription.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product description isn't presented");
        softAssert.assertTrue(productPrice.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product price isn't presented");
        softAssert.assertTrue(removeButton.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product price isn't presented");
    }

    @Override
    public void validateProductInCart(Product product, SoftAssert softAssert) {
        swipe(removeButton);
        softAssert.assertTrue(productName.getText().equals(product.getName()), "Product name isn't correct");
        softAssert.assertTrue(productDescription.getText().equals(product.getDescription()), "Product name isn't correct");
        softAssert.assertTrue(productPrice.getText().equals(product.getPrice()), "Product name isn't correct");
    }

    @Override
    public void clickRemoveButton() {
        removeButton.click();
    }
}
