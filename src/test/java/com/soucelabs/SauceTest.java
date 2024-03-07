package com.soucelabs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ProductContainerBase;
import com.saucelabs.constant.ProjectConstant;
import com.saucelabs.constant.TimeConstant;
import com.saucelabs.dto.identity.User;
import com.saucelabs.dto.identity.UserBuilder;
import com.saucelabs.dto.product.Product;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.CheckoutInfoPageBase;
import com.saucelabs.pages.common.CheckoutPageBase;
import com.saucelabs.pages.common.DrawingPageBase;
import com.saucelabs.pages.common.HamburgerMenuBase;
import com.saucelabs.pages.common.LoginPageBase;
import com.saucelabs.pages.common.OrderPageBase;
import com.saucelabs.pages.common.ProductDetailsPageBase;
import com.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class SauceTest extends BaseTest implements ProjectConstant {

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void testLoginByUserData() {

        //Login standard user with data
        ProductListPageBase productListPage = identityService.loginAsStandardUser();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        LoginPageBase loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        Assert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged out");

        //Login problem user with data
        productListPage = identityService.loginAsProblemUser();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged in");
        loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        Assert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged out");

        //Login locked out user with data and verify that we get error message
        loginPage.typeUserData(UserBuilder.newInstance().lockedOutUser().build());
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLockedOutErrorPresent(), "Locked Out Error message isn't presented");
    }

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void testLoginByAutofill() {

        //Login standard user by autofill
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        LoginPageBase loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        Assert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged out");

        //Login problem user by autofill
        productListPage = identityService.loginAsProblemUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged in");
        loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        Assert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged out");
    }

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void testHamburgerMenu() {
        SoftAssert softAssert = new SoftAssert();

        //Login standard user by autofill
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");

        //Verify hamburger menu
        HamburgerMenuBase hamburgerMenu = productListPage.getHeaderMenu().openHamburgerMenu();
        Assert.assertTrue(hamburgerMenu.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Hamburger menu isn't opened");
        softAssert.assertTrue(hamburgerMenu.isAllItemsButtonPresent(), "All items button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isWebViewButtonPresent(), "WebView button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isQRCodeScannerButtonPresent(), "QR Code Scanner button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isGeoLocationButtonPresent(), "Geo Location button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isDrawingButtonPresent(), "Drawing button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isAboutButtonPresent(), "About button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isLogoutButtonPresent(), "Logout button isn't presented");
        softAssert.assertTrue(hamburgerMenu.isResetAppStateButtonPresent(), "Reset App State button isn't presented");
        LoginPageBase loginPage = hamburgerMenu.logout();
        Assert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged out");
    }

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void testPLP() {
        SoftAssert softAssert = new SoftAssert();
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(), "Standard user isn't logged in");
        ProductContainerBase productContainer = productListPage.getRandomProductContainer();

        //Verify base elements in product container
        softAssert.assertTrue(productContainer.isATBButtonPresent(), "ATB button isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductPricePresent(), "Product price button isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductImagePresent(), "Product image button isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductNamePresent(), "Product name button isn't presented in product container");

        //Verify working ATB button, Remove buttons, ATB using DragAndDrop
        productContainer.clickAddToCartButton();
        Assert.assertTrue(productContainer.isRemoveButtonPresent(), "ATB button wasn't clicked");
        Assert.assertTrue(productListPage.getHeaderMenu().getCountOfProductInCart() == 1, "ATB button wasn't clicked");
        productContainer.clickRemoveButton();
        Assert.assertTrue(productListPage.getHeaderMenu().isCartEmpty(), "Cart is empty");
        productListPage.addProductToCartByDD(productContainer);
        Assert.assertTrue(productListPage.getHeaderMenu().getCountOfProductInCart() == 1, "ATB button wasn't clicked");
        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void testPDP() {
        SoftAssert softAssert = new SoftAssert();
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");

        //Verify product data on PLP and PDP
        ProductContainerBase productContainer = productListPage.getRandomProductContainer();
        Product product = new Product(productContainer.getProductName(), productContainer.getProductPrice());
        ProductDetailsPageBase productDetailsPage = productListPage.openPDP(productContainer);
        softAssert.assertTrue(productDetailsPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product Details Page isn't opened");
        softAssert.assertTrue(productDetailsPage.isProductNamePresent(), "Product name isn't presented on PDP");
        softAssert.assertTrue(productDetailsPage.isProductDescriptionPresent(), "Product description isn't presented on PDP");
        softAssert.assertTrue(productDetailsPage.isProductImagePresent(), "Product image isn't presented on PDP");
        softAssert.assertTrue(productDetailsPage.isProductPricePresent(), "Product price isn't presented on PDP");
        softAssert.assertTrue(productDetailsPage.isATBButtonPresent(), "ATB button isn't presented on PDP");
        Assert.assertTrue(productDetailsPage.getProductName().equals(product.getName()), "Product name isn't correct on PDP");
        Assert.assertTrue(productDetailsPage.getProductPrice().equals(product.getPrice()), "Product price isn't correct on PDP");

        //Verify working ATB button, Remove buttons, Back button
        productDetailsPage.clickAddToCartButton();
        Assert.assertTrue(productDetailsPage.isRemoveButtonPresent(), "ATB button wasn't clicked");
        Assert.assertTrue(productDetailsPage.getHeaderMenu().getCountOfProductInCart() == 1, "ATB button wasn't clicked");
        productDetailsPage.clickRemoveButton();
        Assert.assertTrue(productDetailsPage.getHeaderMenu().isCartEmpty(), "ATB button wasn't clicked");
        productListPage = productDetailsPage.clickBackButton();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product List Page isn't opened");
        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void testCart() {
        SoftAssert softAssert = new SoftAssert();
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        ProductDetailsPageBase productDetailsPage = productListPage.openPDP(productListPage.getRandomProductContainer());

        //Verify product data on PDP and Cart page
        Assert.assertTrue(productDetailsPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product Details Page isn't opened");
        Product product = new Product(productDetailsPage.getProductName(), productDetailsPage.getProductPrice(), productDetailsPage.getProductDescription());
        productDetailsPage.clickAddToCartButton();
        CartPageBase cartPage = productDetailsPage.getHeaderMenu().openCartPage();
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is empty");
        cartPage.getProducts().stream().forEach(item -> {
            item.swipeToProductCart();
            softAssert.assertTrue(item.isProductQuantityPresent(), "Quantity of product isn't presented");
            softAssert.assertTrue(item.isProductNamePresent(), "Product name isn't presented");
            softAssert.assertTrue(item.isProductDescriptionPresent(), "Product description isn't presented");
            softAssert.assertTrue(item.isProductPricePresent(), "Product price isn't presented");
            softAssert.assertTrue(item.isRemoveButtonPresent(), "Remove button isn't presented");
            softAssert.assertTrue(item.getProductName().equals(product.getName()), "Product name isn't correct");
            softAssert.assertTrue(item.getProductDescription().equals(product.getDescription()), "Product name isn't correct");
            softAssert.assertTrue(item.getProductPrice().equals(product.getPrice()), "Product price isn't correct");
        });

        //Verify Cart page elements
        Assert.assertTrue(cartPage.isTitlePresent(), "Title isn't presented on Cart page");
        Assert.assertTrue(cartPage.isContinueShoppingButtonPresent(), "Continue shopping button isn't presented on Cart page");
        Assert.assertTrue(cartPage.isCheckoutButtonPresent(), "Checkout button isn't presented on Cart page");
        productListPage = cartPage.clickContinueShoppingButton();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product List Page isn't opened");
        softAssert.assertAll();
    }

    @DataProvider(parallel = true, name = "UserType")
    public Object[][] getUserType() {
        return new Object[][]{
                {"TUID:01: ", STANDARD_USER},
                {"TUID:02:", PROBLEM_USER}};
    }

    @Test(dataProvider = "UserType")
    @MethodOwner(owner = "mburacheuski")
    public void testCheckoutInfoPage(String TUID, String userType) {
        SoftAssert softAssert = new SoftAssert();

        User user = userType.equals(STANDARD_USER) ? UserBuilder.newInstance().standardUser().build() : UserBuilder.newInstance().problemUser().build();
        ProductListPageBase productListPage = userType.equals(STANDARD_USER) ? identityService.loginAsStandardUserByAutofill() : identityService.loginAsProblemUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        productListPage.addProductToCartByDD(productListPage.getProducts().get(0));

        CartPageBase cartPage = productListPage.getHeaderMenu().openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Cart page isn't opened");
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is empty");

        CheckoutInfoPageBase checkoutInfoPage = cartPage.openCheckoutInfoPage();
        Assert.assertTrue(checkoutInfoPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Cart page isn't opened");
        softAssert.assertTrue(checkoutInfoPage.isFirstNameFieldPresent(), "First name field isn't presented");
        softAssert.assertTrue(checkoutInfoPage.isLastNameFieldPresent(), "Last name field isn't presented");
        softAssert.assertTrue(checkoutInfoPage.isZipFieldPresent(), "Zip field isn't presented");
        softAssert.assertTrue(checkoutInfoPage.isCancelButtonPresent(), "Cancel button isn't presented");
        softAssert.assertTrue(checkoutInfoPage.isContinueButtonPresent(), "Continue button isn't presented");
        checkoutInfoPage.typeUserData(user);
        if (userType.equals(STANDARD_USER)) {
            Assert.assertEquals(checkoutInfoPage.getFirstName(), user.username, "First name field isn't filled correctly");
        } else {
            Assert.assertNotEquals(checkoutInfoPage.getFirstName(), user.username, "First name field is filled correctly");
        }
        productListPage = checkoutInfoPage.clickCancelButton();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "mburacheuski")
    public void testCheckoutPage() {
        SoftAssert softAssert = new SoftAssert();
        User user = UserBuilder.newInstance().standardUser().build();
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");

        ProductDetailsPageBase productDetailsPage = productListPage.openPDP(productListPage.getRandomProductContainer());
        Assert.assertTrue(productDetailsPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product Details Page isn't opened");
        Product product = new Product(productDetailsPage.getProductName(), productDetailsPage.getProductPrice(), productDetailsPage.getProductDescription());
        productDetailsPage.clickAddToCartButton();

        CartPageBase cartPage = productDetailsPage.getHeaderMenu().openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Cart page isn't opened");
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is empty");

        CheckoutInfoPageBase checkoutInfoPage = cartPage.openCheckoutInfoPage();
        Assert.assertTrue(checkoutInfoPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Cart page isn't opened");
        checkoutInfoPage.typeUserData(user);
        CheckoutPageBase checkoutPage = checkoutInfoPage.clickContinueButton();
        checkoutPage.getProducts().stream().forEach(item -> {
            softAssert.assertTrue(item.isProductQuantityPresent(), "Quantity of product isn't presented");
            softAssert.assertTrue(item.isProductNamePresent(), "Product name isn't presented");
            softAssert.assertTrue(item.isProductDescriptionPresent(), "Product description isn't presented");
            softAssert.assertTrue(item.isProductPricePresent(), "Product price isn't presented");
            softAssert.assertTrue(item.getProductName().equals(product.getName()), "Product name isn't correct");
            softAssert.assertTrue(item.getProductDescription().equals(product.getDescription()), "Product name isn't correct");
            softAssert.assertTrue(item.getProductPrice().equals(product.getPrice()), "Product price isn't correct");
        });
        softAssert.assertTrue(checkoutPage.isPaymentInformationPresent(), "Payment information isn't presented");
        softAssert.assertTrue(checkoutPage.isPaymentShippingPresent(), "Shipping information isn't presented");
        softAssert.assertTrue(checkoutPage.isItemTotalPresent(), "Item total isn't presented");
        softAssert.assertTrue(checkoutPage.isTaxPresent(), "Tax isn't presented");
        softAssert.assertTrue(checkoutPage.isTotalPresent(), "Total isn't presented");
        Assert.assertTrue(checkoutPage.getPaymentInformation().contains(user.getPaymentCard()), "Payment information isn't presented");
        Assert.assertEquals(checkoutPage.getItemTotal().split("\\$")[1], product.getPrice().split("\\$")[1], "Product price isn't correct");
        productListPage = checkoutPage.clickCancel();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        softAssert.assertAll();
    }

    @Test(dataProvider = "UserType")
    @MethodOwner(owner = "mburacheuski")
    public void testMakeAnOrder(String TUID, String userType) {
        User user = userType.equals(STANDARD_USER) ? UserBuilder.newInstance().standardUser().build() : UserBuilder.newInstance().problemUser().build();
        ProductListPageBase productListPage = userType.equals(STANDARD_USER) ? identityService.loginAsStandardUserByAutofill() : identityService.loginAsProblemUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        productListPage.addProductToCartByDD(productListPage.getProducts().get(0));

        CartPageBase cartPage = productListPage.getHeaderMenu().openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Cart page isn't opened");
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is empty");

        CheckoutInfoPageBase checkoutInfoPage = cartPage.openCheckoutInfoPage();
        Assert.assertTrue(checkoutInfoPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Checkout info page isn't opened");
        checkoutInfoPage.typeUserData(user);
        CheckoutPageBase checkoutPage = checkoutInfoPage.clickContinueButton();
        Assert.assertTrue(checkoutPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Checkout page isn't opened");

        OrderPageBase orderPage = checkoutPage.clickFinishButton();
        Assert.assertTrue(orderPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Order page isn't opened");
        if (userType.equals(STANDARD_USER)) {
            Assert.assertTrue(productListPage.getHeaderMenu().isCartEmpty(), "Standard user order isn't completed");
        } else {
            Assert.assertTrue(orderPage.getHeaderMenu().getCountOfProductInCart() == 1, "Problem user order is completed");
        }
        productListPage = orderPage.clickBackHomeButton();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product list page isn't opened");
    }

    @Test()
    @MethodOwner(owner = "mburacheuski")
    public void testDrawingPage() {
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        Assert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        DrawingPageBase drawingPage = productListPage.getHeaderMenu().openHamburgerMenu().openDrawingPage();
        Assert.assertTrue(drawingPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Drawing page isn't opened");
        drawingPage.drawElement();
        Assert.assertTrue(drawingPage.isDrawnImagePresent(), "Drown image is presented");
        drawingPage.clickClearButton();
        Assert.assertFalse(drawingPage.isDrawnImagePresent(), "Drown image isn't deleted");
        Assert.assertTrue(drawingPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Drawing page isn't opened");
    }
}
