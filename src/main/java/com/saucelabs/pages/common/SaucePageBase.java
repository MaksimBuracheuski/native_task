package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.FooterComponentBase;
import com.saucelabs.components.TopMainMenuBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class SaucePageBase extends AbstractPage {

    public SaucePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract FooterComponentBase getFooter();

    public abstract TopMainMenuBase getHeader();
}
