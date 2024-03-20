package com.mobile.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.mobile.saucelabs.pages.common.OrderPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = OrderPageBase.class)
public class OrderPage extends OrderPageBase {

    @FindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-BACK HOME']")
    private ExtendedWebElement backHomeButton;

    public OrderPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public ProductListPageBase clickBackHomeButton() {
        backHomeButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }
}
