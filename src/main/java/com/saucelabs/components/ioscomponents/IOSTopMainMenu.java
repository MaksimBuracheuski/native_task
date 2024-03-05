package com.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.TopMainMenu;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.HamburgerMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = TopMainMenu.class)
public class IOSTopMainMenu extends TopMainMenu {
    @FindBy(xpath = "./descendant::XCUIElementTypeOther[@name='test-Cart']")
    public ExtendedWebElement cartButton;

    @FindBy(xpath = "./descendant::XCUIElementTypeOther[@name='test-Menu']")
    public ExtendedWebElement hamburgerMenuButton;

    public IOSTopMainMenu(WebDriver driver) {
        super(driver);
    }

    public IOSTopMainMenu(WebDriver driver, SearchContext searchContext) {
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
