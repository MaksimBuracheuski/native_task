package com.mobile.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.mobile.saucelabs.pages.common.DrawingPageBase;
import com.mobile.saucelabs.pages.common.HamburgerMenuBase;
import com.mobile.saucelabs.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HamburgerMenuBase.class)
public class HamburgerMenu extends HamburgerMenuBase {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ALL ITEMS']")
    private ExtendedWebElement allItemsButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-WEBVIEW']")
    private ExtendedWebElement webViewButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-QR CODE SCANNER']")
    private ExtendedWebElement qrCodeButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-GEO LOCATION']")
    private ExtendedWebElement getLocationButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-DRAWING']")
    private ExtendedWebElement drawingButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ABOUT']")
    private ExtendedWebElement aboutButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGOUT']")
    private ExtendedWebElement logoutButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-RESET APP STATE']")
    private ExtendedWebElement resetAppStateButton;

    public HamburgerMenu(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(allItemsButton);
    }

    @Override
    public LoginPageBase logout() {
        logoutButton.click();
        log.info("User is log outed");
        return initPage(getDriver(), LoginPageBase.class);
    }

    @Override
    public DrawingPageBase openDrawingPage() {
        drawingButton.click();
        return initPage(getDriver(), DrawingPageBase.class);
    }

    @Override
    public boolean isAllItemsButtonPresent() {
        return allItemsButton.isElementPresent();
    }

    @Override
    public boolean isWebViewButtonPresent() {
        return webViewButton.isElementPresent();
    }

    @Override
    public boolean isQRCodeScannerButtonPresent() {
        return qrCodeButton.isElementPresent();
    }

    @Override
    public boolean isGeoLocationButtonPresent() {
        return getLocationButton.isElementPresent();
    }

    @Override
    public boolean isDrawingButtonPresent() {
        return drawingButton.isElementPresent();
    }

    @Override
    public boolean isAboutButtonPresent() {
        return aboutButton.isElementPresent();
    }

    @Override
    public boolean isLogoutButtonPresent() {
        return logoutButton.isElementPresent();
    }

    @Override
    public boolean isResetAppStateButtonPresent() {
        return resetAppStateButton.isElementPresent();
    }
}

