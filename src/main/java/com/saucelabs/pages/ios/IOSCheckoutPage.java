package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.pages.common.CheckoutBasePage;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CheckoutBasePage.class)
public class IOSCheckoutPage extends CheckoutBasePage {
    public IOSCheckoutPage(WebDriver driver) {
        super(driver);
    }
}
