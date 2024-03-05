package com.saucelabs.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.HamburgerMenuBase;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class TopMainMenu extends AbstractUIObject {
    public TopMainMenu(WebDriver driver) {
        super(driver);
    }

    public TopMainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract HamburgerMenuBase openHamburgerMenu();

    public abstract int getCountOfProductInCart();

    public abstract boolean isCartEmpty();

    public abstract CartPageBase openCartPage();

}
