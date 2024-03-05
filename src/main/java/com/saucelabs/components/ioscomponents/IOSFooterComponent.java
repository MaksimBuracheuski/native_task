package com.saucelabs.components.ioscomponents;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.saucelabs.components.FooterComponent;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = FooterComponent.class)

public class IOSFooterComponent extends FooterComponent {
    public IOSFooterComponent(WebDriver driver) {
        super(driver);
    }

    public IOSFooterComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
