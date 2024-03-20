package com.mobile.saucelabs.components.androidcomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.FooterComponentBase;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = FooterComponentBase.class)
public class FooterComponent extends FooterComponentBase{

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public FooterComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
