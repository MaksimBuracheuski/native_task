package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;

import com.saucelabs.pages.common.OrderPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = OrderPageBase.class)
public class OrderPage extends OrderPageBase {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'CHECKOUT: COMPLETE!'`]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosPredicate = "label == 'BACK HOME' AND name == 'test-BACK HOME'")
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
