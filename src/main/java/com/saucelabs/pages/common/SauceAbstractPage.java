package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.FooterComponentBase;
import com.saucelabs.components.TopMainMenuBase;
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
