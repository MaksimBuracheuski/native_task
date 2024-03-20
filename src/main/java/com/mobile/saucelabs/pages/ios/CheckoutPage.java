package com.mobile.saucelabs.pages.ios;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.mobile.saucelabs.components.ioscomponents.ProductCartContainer;
import com.mobile.saucelabs.pages.common.CheckoutPageBase;
import com.mobile.saucelabs.pages.common.OrderPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CheckoutPageBase.class)
public class CheckoutPage extends CheckoutPageBase implements IMobileUtils {

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name == 'test-Item'`]")
    private List<ProductCartContainer> products;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == 'CHECKOUT: OVERVIEW'`]")
    private ExtendedWebElement title;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[$type == 'XCUIElementTypeStaticText' AND name == 'Payment Information:' $][-1]")
    private ExtendedWebElement paymentInfo;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[$type == 'XCUIElementTypeStaticText' AND name == 'Shipping Information:' $][-1]")
    private ExtendedWebElement shippingInfo;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name CONTAINS[cd] 'Item Total'`]/XCUIElementTypeStaticText[`name CONTAINS[cd] 'Item'`]")
    private ExtendedWebElement itemTotal;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name CONTAINS[cd] 'Item Total'`]/XCUIElementTypeStaticText[`name CONTAINS[cd] 'Tax'`]")
    private ExtendedWebElement itemTax;

    @ExtendedFindBy(iosClassChain = "**/XCUIElementTypeOther[`name CONTAINS[cd] 'Item Total'`]/XCUIElementTypeStaticText[`name CONTAINS[cd] 'Total'`]")
    private ExtendedWebElement total;

    @ExtendedFindBy(iosPredicate = "label == 'CANCEL' AND name == 'test-CANCEL'")
    private ExtendedWebElement cancelButton;

    @ExtendedFindBy(iosPredicate = "label == 'FINISH' AND name == 'test-FINISH'")
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
