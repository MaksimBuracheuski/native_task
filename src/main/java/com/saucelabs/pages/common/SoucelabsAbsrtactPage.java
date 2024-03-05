package com.saucelabs.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucelabs.components.FooterComponent;
import com.saucelabs.components.TopMainMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class SoucelabsAbsrtactPage extends AbstractPage {
    public SoucelabsAbsrtactPage(WebDriver driver) {
        super(driver);
    }

    public FooterComponent getFooterMenu() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        return basePage.getFooter();
    }

    public TopMainMenu getHeaderMenu() {
        BasePage basePage = initPage(getDriver(), BasePage.class);
        return basePage.getHeader();
    }
}
