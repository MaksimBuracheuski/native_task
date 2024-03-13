package com.saucelabs.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.saucelabs.constant.TimeConstant;
import com.saucelabs.pages.common.DrawingPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = DrawingPageBase.class)
public class DrawingPage extends DrawingPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'DRAWING'`]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosPredicate = "label == 'CLEAR' AND name == 'test-CLEAR'")
    private ExtendedWebElement clearButton;

    @ExtendedFindBy(iosPredicate = "label == 'SAVE' AND name == 'test-SAVE'")
    private ExtendedWebElement saveButton;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'Signature Pad demo'`]")
    private ExtendedWebElement signaturePadDemo;

    @ExtendedFindBy(image = "images/triangle.png")
    private ExtendedWebElement triangleImage;

    public DrawingPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public void drawElement() {
        signaturePadDemo.isElementPresent(TimeConstant.PAGE_OPENED_TO);
        Actions actions = new Actions(getDriver());
        for (int i = 0; i < 53; i++) {
            actions.moveToElement(signaturePadDemo.getElement(), 50, -300)
                    .clickAndHold()
                    .moveByOffset(100 - i, 0 + i)
                    .moveByOffset(-50 + i, 86 - i)
                    .moveByOffset(-54 + i, -90 + i)
                    .release()
                    .perform();
        }
    }

    @Override
    public boolean isDrawnImagePresent() {
        HasSettings driver = (HasSettings) getDriver();
        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.5);
        return triangleImage.isElementPresent(TimeConstant.PAGE_OPENED_TO);
    }

    @Override
    public void clickClearButton() {
        clearButton.click();
    }
}
