package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.saucelabs.constant.TimeConstant;
import com.saucelabs.dto.product.Product;
import com.saucelabs.pages.common.ProductDetailsPage;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailsPage.class)
public class IOSProductDetailsPage extends ProductDetailsPage implements IMobileUtils {

    @ExtendedFindBy(iosPredicate = "label == 'BACK TO PRODUCTS' AND name == 'test-BACK TO PRODUCTS'")
    private ExtendedWebElement backButton;

    @FindBy(xpath = "//XCUIElementTypeOther[@name='test-Image Container']/XCUIElementTypeOther/XCUIElementTypeImage")
    private ExtendedWebElement productImage;

    @FindBy(xpath = "//XCUIElementTypeOther[@name='test-Description']/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement productName;

    @FindBy(xpath = "//XCUIElementTypeOther[@name='test-Description']/XCUIElementTypeStaticText[2]")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='test-Price']")
    private ExtendedWebElement productPrice;

    @ExtendedFindBy(iosPredicate = "label == 'ADD TO CART' AND name == 'test-ADD TO CART'")
    private ExtendedWebElement addToBagButton;

    @ExtendedFindBy(iosPredicate = "label == 'REMOVE' AND name == 'test-REMOVE'")
    private ExtendedWebElement removeButton;

    public IOSProductDetailsPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(backButton);
    }

    @Override
    public void validateBaseElementsOnPDP(SoftAssert softAssert) {
        softAssert.assertTrue(productName.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product name isn't presented on PDP");
        softAssert.assertTrue(productDescription.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product description isn't presented on PDP");
        softAssert.assertTrue(productImage.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Product image isn't presented on PDP");
        softAssert.assertTrue(swipe(productPrice), "Product price isn't presented on PDP");
        softAssert.assertTrue(swipe(addToBagButton), "ATB button isn't presented on PDP");
    }

    @Override
    public void validateProductData(Product product, SoftAssert softAssert) {
        softAssert.assertTrue(productName.getText().equals(product.getName()), "Product name isn't correct on PDP");
        softAssert.assertTrue(productPrice.getText().equals(product.getPrice()), "Product price isn't correct on PDP");
    }

    @Override
    public String getProductName() {
        return productName.getText();
    }

    @Override
    public String getProductDescription() {
        return productDescription.getText();
    }

    @Override
    public String getProductPrice() {
        swipe(productPrice);
        return productPrice.getText();
    }

    @Override
    public boolean isRemoveButtonPresent() {
        return swipe(removeButton);
    }

    @Override
    public void clickATBButton() {
        swipe(addToBagButton);
        addToBagButton.click();
    }

    @Override
    public void clickRemoveButton() {
        swipe(removeButton);
        removeButton.click();
    }

    @Override
    public ProductListPageBase clickBackButton() {
        swipe(backButton);
        backButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }
}
