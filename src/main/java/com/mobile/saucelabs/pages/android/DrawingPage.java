package com.mobile.saucelabs.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.mobile.saucelabs.pages.common.DrawingPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = DrawingPageBase.class)
public class DrawingPage extends DrawingPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.widget.TextView[@text='DRAWING']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CLEAR']")
    private ExtendedWebElement clearButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-SAVE']")
    private ExtendedWebElement saveButton;

    @FindBy(xpath = "//android.webkit.WebView")
    private ExtendedWebElement signaturePadDemo;

    @ExtendedFindBy(image = "images/t.png")
    private ExtendedWebElement triangleImage;

    public DrawingPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public void drawElement() {
        signaturePadDemo.isElementPresent();
        Actions actions = new Actions(getDriver());
        for (int i = 0; i < 53; i++) {
            actions.moveToElement(signaturePadDemo.getElement(), 50, -700)
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
        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.7);
        return triangleImage.isElementPresent();
    }

    @Override
    public void clickClearButton() {
        clearButton.click();
    }
}
