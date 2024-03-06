package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.pages.common.CheckoutPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CheckoutPageBase.class)
public class CheckoutPage extends CheckoutPageBase {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
}
