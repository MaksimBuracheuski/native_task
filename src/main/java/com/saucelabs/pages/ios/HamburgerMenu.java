package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.pages.common.HamburgerMenuBase;
import com.saucelabs.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HamburgerMenuBase.class)
public class HamburgerMenu extends HamburgerMenuBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'ALL ITEMS'`]")
    private ExtendedWebElement allItemsButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'WEBVIEW'`]")
    private ExtendedWebElement webViewButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'QR CODE SCANNER'`]")
    private ExtendedWebElement qrCodeButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'GEO LOCATION'`]")
    private ExtendedWebElement getLocationButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'DRAWING'`]")
    private ExtendedWebElement drawingButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'ABOUT'`]")
    private ExtendedWebElement aboutButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'LOGOUT'`]")
    private ExtendedWebElement logoutButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == 'RESET APP STATE'`]")
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
}
