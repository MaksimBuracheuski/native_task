package com.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.components.FooterComponentBase;
import com.saucelabs.components.TopMainMenuBase;
import com.saucelabs.components.androidcomponents.FooterComponent;
import com.saucelabs.components.androidcomponents.TopMainMenu;
import com.saucelabs.pages.common.SaucePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SaucePageBase.class)
public class SaucePage extends SaucePageBase {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']/parent::android.view.ViewGroup")
    public TopMainMenu header;

    @FindBy(xpath = "//android.widget.TextView[@text='Terms of Service | Privacy Policy']/parent::android.view.ViewGroup")
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

