package com.saucelabs.components.androidcomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.TopMainMenuBase;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.HamburgerMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = TopMainMenuBase.class)
public class TopMainMenu extends TopMainMenuBase {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
    public ExtendedWebElement cartButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView")
    public ExtendedWebElement cartQuantityButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']")
    public ExtendedWebElement hamburgerMenuButton;

    public TopMainMenu(WebDriver driver) {
        super(driver);
    }

    public TopMainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public HamburgerMenuBase openHamburgerMenu() {
        hamburgerMenuButton.click();
        return initPage(getDriver(), HamburgerMenuBase.class);
    }

    @Override
    public int getCountOfProductInCart() {
        return Integer.parseInt(cartQuantityButton.getText());
    }

    @Override
    public boolean isCartEmpty() {
        return !cartQuantityButton.isElementPresent();
    }

    @Override
    public CartPageBase openCartPage() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }
}

