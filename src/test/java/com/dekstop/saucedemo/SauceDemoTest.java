package com.dekstop.saucedemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class SauceDemoTest extends AbstractTest {

    @Test
    @MethodOwner(owner = "mburacheuski")
    public void checkoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date;
        try {
            date = sdf.parse("2024-03-20T16:30:28.000Z");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Cookie cookies = new Cookie.Builder("session-username", "standard_user").path("/").domain("www.saucedemo.com").expiresOn(date).build();

        getDriver().manage().addCookie(cookies);

        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened());

    }
}
