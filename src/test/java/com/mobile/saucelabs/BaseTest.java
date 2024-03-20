package com.mobile.saucelabs;

import com.mobile.saucelabs.services.IdentityService;
import com.zebrunner.carina.core.AbstractTest;

public class BaseTest extends AbstractTest {

    IdentityService identityService = new IdentityService();
}
