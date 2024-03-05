package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.FooterComponent;
import com.saucelabs.components.TopMainMenu;
import com.saucelabs.components.ioscomponents.IOSFooterComponent;
import com.saucelabs.components.ioscomponents.IOSTopMainMenu;
import com.saucelabs.pages.common.BasePage;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = BasePage.class)
public class IOSBasePage extends BasePage {
    @FindBy(xpath = "//XCUIElementTypeOther[@name='headerContainer']/parent::XCUIElementTypeOther")
    public IOSTopMainMenu header;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Terms of Service | Privacy Policy']/parent::XCUIElementTypeOther")
    public IOSFooterComponent footer;

    public IOSBasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FooterComponent getFooter() {
        return footer;
    }

    @Override
    public TopMainMenu getHeader() {
        return header;
    }
}
