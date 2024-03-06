package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.constant.TimeConstant;
import com.saucelabs.pages.common.ProductDetailsPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailsPageBase.class)
public class ProductDetailsPage extends ProductDetailsPageBase implements IMobileUtils {

    @ExtendedFindBy(iosPredicate = "label == 'BACK TO PRODUCTS' AND name == 'test-BACK TO PRODUCTS'")
    private ExtendedWebElement backButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Image Container'`]/**/XCUIElementTypeImage")
    private ExtendedWebElement productImage;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Description'`]/XCUIElementTypeStaticText[1]")
    private ExtendedWebElement productName;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Description'`]/XCUIElementTypeStaticText[2]")
    private ExtendedWebElement productDescription;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`name == 'test-Price'`]")
    private ExtendedWebElement productPrice;

    @ExtendedFindBy(iosPredicate = "label == 'ADD TO CART' AND name == 'test-ADD TO CART'")
    private ExtendedWebElement addToBagButton;

    @ExtendedFindBy(iosPredicate = "label == 'REMOVE' AND name == 'test-REMOVE'")
    private ExtendedWebElement removeButton;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(backButton);
    }

    @Override
    public boolean isProductNamePresent() {
        return productName.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isProductDescriptionPresent() {
        return productDescription.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isProductImagePresent() {
        return productImage.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public boolean isProductPricePresent() {
        return swipe(productPrice);
    }

    @Override
    public boolean isATBButtonPresent() {
        return swipe(addToBagButton);
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
    public void clickAddToCartButton() {
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
