package com.mobile.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.TopMainMenuBase;
import com.mobile.saucelabs.components.ioscomponents.FooterComponent;
import com.mobile.saucelabs.components.ioscomponents.TopMainMenu;
import com.mobile.saucelabs.components.FooterComponentBase;
import com.mobile.saucelabs.pages.common.SaucePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = SaucePageBase.class)
public class SaucePage extends SaucePageBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[$type == 'XCUIElementTypeOther' AND name == 'headerContainer' $][-2]")
    public TopMainMenu header;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`label == \"\uF099 \uF09A \uF0D5 \uF0E1 © 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy\"`]")
    public FooterComponent footer;

    public SaucePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FooterComponentBase getFooter() {
        return footer;
    }

    @Override
    public TopMainMenuBase getHeader() {
        return header;
    }
}
