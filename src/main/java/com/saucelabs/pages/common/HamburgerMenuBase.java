package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HamburgerMenuBase extends AbstractPage {

    public HamburgerMenuBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public abstract LoginPageBase logout();

    public abstract DrawingPageBase openDrawingPage();

    public abstract boolean isAllItemsButtonPresent();

    public abstract boolean isWebViewButtonPresent();

    public abstract boolean isQRCodeScannerButtonPresent();

    public abstract boolean isGeoLocationButtonPresent();

    public abstract boolean isDrawingButtonPresent();

    public abstract boolean isAboutButtonPresent();

    public abstract boolean isLogoutButtonPresent();

    public abstract boolean isResetAppStateButtonPresent();
}
