package com.soucelabs;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ProductContainerBase;
import com.saucelabs.constant.TimeConstant;
import com.saucelabs.dto.identity.UserBuilder;
import com.saucelabs.dto.product.Product;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.LoginPageBase;
import com.saucelabs.pages.common.ProductDetailsPageBase;
import com.saucelabs.pages.common.ProductListPageBase;

public class SauceTest extends BaseTest {

    @Test
    public void LoginByUserDataTest() {
        SoftAssert softAssert = new SoftAssert();

        //Login standard user with data
        ProductListPageBase productListPage = identityService.loginAsStandardUser();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        LoginPageBase loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        softAssert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged out");

        //Login problem user with data
        productListPage = identityService.loginAsProblemUser();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged in");
        loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        softAssert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged out");

        //Login locked out user with data and verify that we get error message
        loginPage.typeUserData(UserBuilder.newInstance().lockedOutUser().build());
        loginPage.clickLoginButton();
        softAssert.assertTrue(loginPage.isLockedOutErrorPresent(), "Locked Out Error message isn't presented");
        softAssert.assertAll();
    }

    @Test
    public void LoginByAutofillTest() {
        SoftAssert softAssert = new SoftAssert();

        //Login standard user by autofill
        LoginPageBase loginPageBase = initPage(getDriver(), LoginPageBase.class);
        ProductListPageBase productListPage = loginPageBase.loginByAutofill(UserBuilder.newInstance().standardUser().build());
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        LoginPageBase loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        softAssert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged out");

        //Login problem user by autofill
        productListPage = loginPageBase.loginByAutofill(UserBuilder.newInstance().problemUser().build());
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged in");
        loginPage = productListPage.getHeaderMenu().openHamburgerMenu().logout();
        softAssert.assertTrue(loginPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Problem user isn't logged out");

        softAssert.assertAll();
    }

    @Test
    public void PLPTest() {
        SoftAssert softAssert = new SoftAssert();
        ProductListPageBase productListPage = identityService.loginAsStandardUserByAutofill();
        softAssert.assertTrue(productListPage.isPageOpened(), "Standard user isn't logged in");
        ProductContainerBase productContainer = productListPage.getRandomProductContainer();

        //Verify base elements in product container
        softAssert.assertTrue(productContainer.isATBButtonPresent(), "ATB button isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductPricePresent(), "Product price button isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductImagePresent(), "Product image button isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductNamePresent(), "Product name button isn't presented in product container");

        //Verify working ATB button, Remove buttons, ATB using DragAndDrop
        productContainer.clickATBButton();
        softAssert.assertTrue(productContainer.isRemoveButtonPresent(), "ATB button wasn't clicked");
        softAssert.assertTrue(productListPage.getHeaderMenu().getCountOfProductInCart() == 1, "ATB button wasn't clicked");
        productContainer.clickRemoveButton();
        softAssert.assertTrue(productListPage.getHeaderMenu().isCartEmpty(), "ATB button wasn't clicked");
        productListPage.addProductToCartByDD(productContainer);
        softAssert.assertTrue(productListPage.getHeaderMenu().getCountOfProductInCart() == 1, "ATB button wasn't clicked");
        softAssert.assertAll();
    }

    @Test
    public void PDPTest() {
        SoftAssert softAssert = new SoftAssert();
        ProductListPageBase productListPage = identityService.loginAsProblemUserByAutofill();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");

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
        softAssert.assertTrue(productDetailsPage.getProductName().equals(product.getName()), "Product name isn't correct on PDP");
        softAssert.assertTrue(productDetailsPage.getProductPrice().equals(product.getPrice()), "Product price isn't correct on PDP");

        //Verify working ATB button, Remove buttons, Back button
        productDetailsPage.clickATBButton();
        softAssert.assertTrue(productDetailsPage.isRemoveButtonPresent(), "ATB button wasn't clicked");
        softAssert.assertTrue(productDetailsPage.getHeaderMenu().getCountOfProductInCart() == 1, "ATB button wasn't clicked");
        productDetailsPage.clickRemoveButton();
        softAssert.assertTrue(productDetailsPage.getHeaderMenu().isCartEmpty(), "ATB button wasn't clicked");
        productListPage = productDetailsPage.clickBackButton();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product List Page isn't opened");
        softAssert.assertAll();
    }

    @Test
    public void CartTest() {
        SoftAssert softAssert = new SoftAssert();
        ProductListPageBase productListPage = identityService.loginAsProblemUserByAutofill();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Standard user isn't logged in");
        ProductDetailsPageBase productDetailsPage = productListPage.openPDP(productListPage.getRandomProductContainer());

        //Verify product data on PDP and Cart page
        softAssert.assertTrue(productDetailsPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product Details Page isn't opened");
        Product product = new Product(productDetailsPage.getProductName(), productDetailsPage.getProductPrice(), productDetailsPage.getProductDescription());
        productDetailsPage.clickATBButton();
        CartPageBase cartPage = productDetailsPage.getHeaderMenu().openCartPage();
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
        softAssert.assertTrue(cartPage.isTitlePresent(), "Title isn't presented on Cart page");
        softAssert.assertTrue(cartPage.isContinueShoppingButtonPresent(), "Continue shopping button isn't presented on Cart page");
        softAssert.assertTrue(cartPage.isCheckoutButtonPresent(), "Checkout button isn't presented on Cart page");
        productListPage = cartPage.clickContinueShoppingButton();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product List Page isn't opened");
        softAssert.assertAll();
    }
}
