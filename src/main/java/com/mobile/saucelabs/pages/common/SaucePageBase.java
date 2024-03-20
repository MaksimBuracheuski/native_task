package com.mobile.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.TopMainMenuBase;
import com.mobile.saucelabs.components.FooterComponentBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class SaucePageBase extends AbstractPage {

    public SaucePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract FooterComponentBase getFooter();

    public abstract TopMainMenuBase getHeader();
}
