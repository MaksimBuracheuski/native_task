package com.soucelabs;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucelabs.components.ProductContainer;
import com.saucelabs.constant.TimeConstant;
import com.saucelabs.dto.identity.UserBuilder;
import com.saucelabs.dto.product.Product;
import com.saucelabs.pages.common.CartPageBase;
import com.saucelabs.pages.common.LoginPageBase;
import com.saucelabs.pages.common.ProductDetailsPage;
import com.saucelabs.pages.common.ProductListPageBase;

public class SoucelabsTest extends BaseTest {

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
        ProductContainer productContainer = productListPage.getRandomProductContainer();

        //Verify base elements in product container
        productContainer.validateProductContainerElements(softAssert);

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
        ProductContainer productContainer = productListPage.getRandomProductContainer();
        Product product = new Product(productContainer.getProductName(), productContainer.getProductPrice());
        ProductDetailsPage productDetailsPage = productListPage.openPDP(productContainer);
        softAssert.assertTrue(productDetailsPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product Details Page isn't opened");
        productDetailsPage.validateBaseElementsOnPDP(softAssert);
        productDetailsPage.validateProductData(product, softAssert);

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
        ProductDetailsPage productDetailsPage = productListPage.openPDP(productListPage.getRandomProductContainer());

        //Verify product data on PDP and Cart page
        softAssert.assertTrue(productDetailsPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product Details Page isn't opened");
        Product product = new Product(productDetailsPage.getProductName(), productDetailsPage.getProductPrice(), productDetailsPage.getProductDescription());
        productDetailsPage.clickATBButton();
        CartPageBase cartPage = productDetailsPage.getHeaderMenu().openCartPage();
        cartPage.getProducts().stream().forEach(item -> {
            item.validateBaseElements(softAssert);
            item.validateProductInCart(product, softAssert);
        });

        //Verify Cart page elements
        cartPage.validateCartElements(softAssert);
        productListPage = cartPage.clickContinueShoppingButton();
        softAssert.assertTrue(productListPage.isPageOpened(TimeConstant.PAGE_OPENED_TO), "Product List Page isn't opened");
        softAssert.assertAll();
    }
}
