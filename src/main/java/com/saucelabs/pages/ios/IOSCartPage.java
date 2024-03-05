package com.saucelabs.pages.ios;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ioscomponents.IOSProductCartContainer;
import com.saucelabs.constant.TimeConstant;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.CheckoutBasePage;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class IOSCartPage extends CartPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'YOUR CART'`]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosPredicate = "label == 'CONTINUE SHOPPING' AND name == 'test-CONTINUE SHOPPING'")
    private ExtendedWebElement continueShoppingButton;

    @ExtendedFindBy(iosPredicate = "label == 'CHECKOUT' AND name == 'test-CHECKOUT'")
    private ExtendedWebElement checkoutButton;
    @FindBy(xpath = "//XCUIElementTypeOther[@name='test-Item']")
    private List<IOSProductCartContainer> products;


    public IOSCartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public void validateCartElements(SoftAssert softAssert) {
        softAssert.assertTrue(title.isElementPresent(TimeConstant.PAGE_OPENED_TO), "Title isn't presented on Cart page");
        softAssert.assertTrue(swipe(continueShoppingButton), "Continue shopping button isn't presented on Cart page");
        softAssert.assertTrue(swipe(checkoutButton), "Checkout button isn't presented on Cart page");
    }

    @Override
    public CheckoutBasePage openCheckout() {
        checkoutButton.click();
        return initPage(getDriver(), CheckoutBasePage.class);
    }

    @Override
    public ProductListPageBase clickContinueShoppingButton() {
        continueShoppingButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public List<IOSProductCartContainer> getProducts() {
        return products;
    }
}
