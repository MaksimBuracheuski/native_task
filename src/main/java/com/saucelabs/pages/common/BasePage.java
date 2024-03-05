package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.FooterComponent;
import com.saucelabs.components.TopMainMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class BasePage extends AbstractPage {
    public BasePage(WebDriver driver) {
        super(driver);
    }

    public abstract FooterComponent getFooter();

    public abstract TopMainMenu getHeader();

}
