package com.saucelabs.pages.ios;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.ProductContainer;
import com.saucelabs.components.ioscomponents.IOSProductContainer;
import com.saucelabs.pages.common.ProductDetailsPage;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductListPageBase.class)
public class IOSProductListPage extends ProductListPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'PRODUCTS'`]")
    private ExtendedWebElement productsTitle;

    @FindBy(xpath = "//XCUIElementTypeOther[@name='test-Item']")
    private List<IOSProductContainer> products;

    @FindBy(xpath = "//XCUIElementTypeOther[@name='PRODUCTS']")
    private ExtendedWebElement productField;


    public IOSProductListPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(productsTitle);
    }

    @Override
    public ProductContainer getRandomProductContainer() {
        ProductContainer productContainer = products.get(new Random().nextInt(products.size()));
        swipe(productContainer);
        return productContainer;
    }

    @Override
    public void addProductToCartByDD(ProductContainer productContainer) {
        dragAndDrop(productContainer.getHandleATBButton(), productField);
    }

    @Override
    public ProductDetailsPage openPDP(ProductContainer productContainer) {
        productContainer.clickOnImage();
        return initPage(getDriver(), ProductDetailsPage.class);
    }

    @Override
    public List<IOSProductContainer> getProducts() {
        return products;
    }
}
