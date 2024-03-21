package com.dekstop.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dekstop.saucedemo.components.HeaderMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class SauceDemoBase extends AbstractPage {

    @FindBy(xpath = "//div[@class='header_label']")
    private HeaderMenu header;

    public SauceDemoBase(WebDriver driver) {
        super(driver);
    }

    public HeaderMenu getHeaderMenu() {
        return header;
    }
}
