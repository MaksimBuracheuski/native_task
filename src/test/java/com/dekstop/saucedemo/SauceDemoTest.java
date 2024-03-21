package com.dekstop.saucedemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.constant.ProjectConstant;
import com.dekstop.saucedemo.components.HeaderMenu;
import com.dekstop.saucedemo.components.ProductContainer;
import com.dekstop.saucedemo.pages.CartPage;
import com.dekstop.saucedemo.pages.CheckoutCompletePage;
import com.dekstop.saucedemo.pages.CheckoutOverviewPage;
import com.dekstop.saucedemo.pages.CheckoutPage;
import com.dekstop.saucedemo.pages.HomePage;
import com.dekstop.saucedemo.pages.LoginPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class SauceDemoTest extends AbstractTest implements ProjectConstant {

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void checkoutTest() {
        SoftAssert softAssert = new SoftAssert();
        Date date;
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime newTime = currentTime.plusMinutes(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT);

        try {
            date = sdf.parse(newTime.format(formatter));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Cookie cookies = new Cookie.Builder("session-username", "standard_user").path("/").domain("www.saucedemo.com").expiresOn(date).build();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");

        getDriver().manage().addCookie(cookies);

        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page isn't opened");

        ProductContainer productContainer = homePage.getRandomProducts();
        softAssert.assertTrue(productContainer.isProductPricePresent(), "Product price isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductImagePresent(), "Product image isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductNamePresent(), "Product name isn't presented in product container");
        softAssert.assertTrue(productContainer.isProductDescriptionPresent(), "Product description isn't presented in product container");

        HeaderMenu headerMenu = homePage.getHeaderMenu();
        softAssert.assertTrue(headerMenu.isCartButtonPresent(), "Cart button isn't present");
        softAssert.assertFalse(headerMenu.isCartEmpty(), "Cart is empty");
        productContainer.clickAddToBagButton();
        softAssert.assertTrue(headerMenu.getCountOfProductInCart() == 1, "Product wasn't added");

        CartPage cartPage = headerMenu.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page isn't opened");
        softAssert.assertTrue(cartPage.isCountOfProductCorrect(), "Count of product isn't correct");

        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        Assert.assertTrue(checkoutPage.isPageOpened(), "Checkout page isn't opened");
        checkoutPage.typeFirstName(FIRST_NAME);
        checkoutPage.typeLastName(LAST_NAME);
        checkoutPage.typeZipCode(ZIP_CODE);

        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.clickContinueButton();
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page isn't opened");
        softAssert.assertTrue(checkoutOverviewPage.isCountOfProductCorrect(), "Count of product isn't correct");

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        Assert.assertTrue(checkoutCompletePage.isPageOpened(), "Checkout Complete page isn't opened");
        Assert.assertTrue(checkoutCompletePage.isThanksTitlePresent(), "Thanks title isn't presented");
        softAssert.assertAll();

    }
}
