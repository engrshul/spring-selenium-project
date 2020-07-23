package com.noon.oyodemo.tests;

import com.noon.oyodemo.DriverBase;
import com.noon.oyodemo.page_objects.OyoHomePage;
import com.noon.oyodemo.page_objects.OyoLoginSignUpPage;
import com.noon.oyodemo.page_objects.OyoProfilePage;
import com.noon.oyodemo.page_objects.OyoWelcomePage;
import com.noon.oyodemo.utilities.ActionHelper;
import com.noon.oyodemo.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class OyoFlowTwo extends DriverBase {

    @Autowired
    OyoHomePage homePage;

    @Autowired
    OyoLoginSignUpPage loginSignUpPage;

    @Autowired
    OyoProfilePage profilePage;

    @Autowired
    OyoWelcomePage welcomePage;

    @Autowired
    ActionHelper helper;

    private static final Logger logger = LogManager.getLogger(OyoFlowTwo.class);


    @Test(enabled = true,priority = 0)
    public void loginAndUpdateUserName() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(Constants.COMPANY_URL);
        homePage.goToLoginSignUpPage();
        loginSignUpPage.changeLogin();
        loginSignUpPage.provideCredentials(Constants.USERNAME, Constants.PASSWORD);
        loginSignUpPage.verifyNumber();
        welcomePage.goToProfilePage();
        String timestampString = helper.getTimeStampString();
        String updatedUserName = profilePage.editProfileUsername(timestampString);
        logger.info("updatedUserName is" + updatedUserName);
        logger.info("timestampString is" + timestampString);
        System.out.println(updatedUserName + "***" + timestampString);
        if (!updatedUserName.contains(timestampString)) {

            System.out.println("failure ****" + "updatedUserName is " + updatedUserName + "timestampString is " + timestampString);
            Assert.fail("userName not updated successfully");
        }

    }

    @Test(enabled = true,priority = 1)
    public void verifyTitle() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(Constants.COMPANY_URL);
        Assert.assertEquals(welcomePage.getTitle(driver),"Hotel Booking India, Branded hotels, Affordable Stays - OYO");
    }

    @Test(enabled = true,priority = 2)
    public void loginWithBlankPassword() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(Constants.COMPANY_URL);
        homePage.goToLoginSignUpPage();
        loginSignUpPage.changeLogin();
        loginSignUpPage.provideCredentials(Constants.USERNAME," ");
        loginSignUpPage.verifyNumber();
        Assert.assertEquals(loginSignUpPage.getErrorText(),"phone or password left blank");
    }

    @Test(enabled = true,priority =3)
    public void loginWithBankUserName() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(Constants.COMPANY_URL);
        homePage.goToLoginSignUpPage();
        loginSignUpPage.changeLogin();
        loginSignUpPage.provideCredentials(" ", Constants.PASSWORD);
        loginSignUpPage.verifyNumber();
        Assert.assertEquals(loginSignUpPage.getErrorText(),"phone or password left blank");
    }
}