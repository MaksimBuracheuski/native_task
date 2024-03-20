package com.mobile.saucelabs.services;

import org.testng.Assert;

import com.mobile.saucelabs.dto.identity.UserBuilder;
import com.mobile.saucelabs.pages.common.LoginPageBase;
import com.mobile.saucelabs.pages.common.ProductListPageBase;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;

public class IdentityService implements ICustomTypePageFactory {

    public ProductListPageBase loginAsStandardUser() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");
        ProductListPageBase homePage = loginPage.login(UserBuilder.newInstance().standardUser().build());
        return homePage;
    }

    public ProductListPageBase loginAsProblemUser() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");
        ProductListPageBase homePage = loginPage.login(UserBuilder.newInstance().problemUser().build());
        return homePage;
    }

    public ProductListPageBase loginAsStandardUserByAutofill() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");
        ProductListPageBase homePage = loginPage.loginByAutofill(UserBuilder.newInstance().standardUser().build());
        return homePage;
    }

    public ProductListPageBase loginAsProblemUserByAutofill() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");
        ProductListPageBase homePage = loginPage.loginByAutofill(UserBuilder.newInstance().problemUser().build());
        return homePage;
    }
}
