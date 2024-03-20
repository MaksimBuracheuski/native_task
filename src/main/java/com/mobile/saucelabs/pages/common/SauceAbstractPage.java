package com.mobile.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.FooterComponentBase;
import com.mobile.saucelabs.components.TopMainMenuBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class SauceAbstractPage extends AbstractPage {

    public SauceAbstractPage(WebDriver driver) {
        super(driver);
    }

    public FooterComponentBase getFooterMenu() {
        SaucePageBase saucePageBase = initPage(getDriver(), SaucePageBase.class);
        return saucePageBase.getFooter();
    }

    public TopMainMenuBase getHeaderMenu() {
        SaucePageBase saucePageBase = initPage(getDriver(), SaucePageBase.class);
        return saucePageBase.getHeader();
    }
}
