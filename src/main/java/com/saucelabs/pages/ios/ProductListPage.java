package com.saucelabs.pages.ios;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.ProductContainerBase;
import com.saucelabs.components.ioscomponents.ProductContainer;
import com.saucelabs.pages.common.ProductDetailsPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductListPageBase.class)
public class ProductListPage extends ProductListPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'PRODUCTS'`]")
    private ExtendedWebElement productsTitle;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Item'`]")
    private List<ProductContainer> products;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Drag Handle'`][%s]")
    private ExtendedWebElement drugHandleButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'PRODUCTS'`]")
    private ExtendedWebElement productField;

    public ProductListPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(productsTitle);
    }

    @Override
    public ProductContainerBase getRandomProductContainer() {
        ProductContainerBase productContainer = products.get(new Random().nextInt(products.size()));
        swipe(productContainer);
        return productContainer;
    }

    @Override
    public void addProductToCartByDD(ProductContainerBase productContainer) {
        dragAndDrop(drugHandleButton.format(products.stream().map(item -> item.getProductName()).collect(Collectors.toList()).indexOf(productContainer.getProductName()) + 1), productField);
    }

    @Override
    public ProductDetailsPageBase openPDP(ProductContainerBase productContainer) {
        productContainer.clickOnImage();
        return initPage(getDriver(), ProductDetailsPageBase.class);
    }

    @Override
    public List<ProductContainer> getProducts() {
        return products;
    }
}
