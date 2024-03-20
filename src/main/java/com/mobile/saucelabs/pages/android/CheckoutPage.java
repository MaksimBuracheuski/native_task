package com.mobile.saucelabs.pages.android;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.mobile.saucelabs.components.androidcomponents.ProductCartContainer;
import com.mobile.saucelabs.pages.common.CheckoutPageBase;
import com.mobile.saucelabs.pages.common.OrderPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CheckoutPageBase.class)
public class CheckoutPage extends CheckoutPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
    private List<ProductCartContainer> products;

    @FindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']")
    private ExtendedWebElement title;

    @FindBy(xpath = "//android.widget.TextView[@text='Payment Information:']/following-sibling::android.widget.TextView[1]")
    private ExtendedWebElement paymentInfo;

    @FindBy(xpath = "//android.widget.TextView[@text='Shipping Information:']/following-sibling::android.widget.TextView[1]")
    private ExtendedWebElement shippingInfo;

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'Item total:')]")
    private ExtendedWebElement itemTotal;

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'Tax:')]")
    private ExtendedWebElement itemTax;

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'Total:')]")
    private ExtendedWebElement total;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CANCEL']")
    private ExtendedWebElement cancelButton;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-FINISH']")
    private ExtendedWebElement finishButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public List<ProductCartContainer> getProducts() {
        return products;
    }

    @Override
    public boolean isPaymentInformationPresent() {
        return swipe(paymentInfo);
    }

    @Override
    public boolean isPaymentShippingPresent() {
        return swipe(shippingInfo);
    }

    @Override
    public boolean isItemTotalPresent() {
        return swipe(itemTotal);
    }

    @Override
    public boolean isTotalPresent() {
        return swipe(total);
    }

    @Override
    public boolean isTaxPresent() {
        return swipe(itemTax);
    }

    @Override
    public String getItemTotal() {
        swipe(itemTotal);
        return itemTotal.getText();
    }

    @Override
    public ProductListPageBase clickCancel() {
        swipe(cancelButton);
        cancelButton.click();
        return initPage(getDriver(), ProductListPageBase.class);
    }

    @Override
    public OrderPageBase clickFinishButton() {
        swipe(finishButton);
        finishButton.click();
        return initPage(getDriver(), OrderPageBase.class);
    }

    @Override
    public String getPaymentInformation() {
        return paymentInfo.getText();
    }
}
