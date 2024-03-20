package com.mobile.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.TopMainMenuBase;
import com.mobile.saucelabs.pages.common.CartPageBase;
import com.mobile.saucelabs.pages.common.HamburgerMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = TopMainMenuBase.class)
public class TopMainMenu extends TopMainMenuBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Cart'`]")
    public ExtendedWebElement cartButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Menu'`]")
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
        return Integer.parseInt(cartButton.getText());
    }

    @Override
    public boolean isCartEmpty() {
        return cartButton.getText().isEmpty();
    }

    @Override
    public CartPageBase openCartPage() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }
}
