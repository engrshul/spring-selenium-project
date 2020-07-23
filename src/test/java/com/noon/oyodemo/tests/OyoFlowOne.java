package com.noon.oyodemo.tests;

import com.noon.oyodemo.DriverBase;
import com.noon.oyodemo.page_objects.OyoHomePage;
import com.noon.oyodemo.page_objects.OyoWelcomePage;
import com.noon.oyodemo.utilities.ActionHelper;
import com.noon.oyodemo.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class OyoFlowOne extends DriverBase {       // all testclass extend DriverBase class

    @Autowired
    OyoHomePage homePage;

    @Autowired
    OyoWelcomePage welcomePage;

    @Autowired
    ActionHelper helper;

    @Test(priority = 0)
    public void returnHotelsWithPrices() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        helper.setImplicitWait(driver,20);
        driver.get(Constants.COMPANY_URL);
        homePage.enterSearchTerm(Constants.SEARCH_TERM);
        homePage.submitSearch();
        welcomePage.selectDrodownElement(1);
        Map map=welcomePage.printAndReturnHotelNameWithPrice(5);
        Assert.assertEquals(map.size(),5);
    }


    @Test(enabled = true,priority = 1)
    public void returnHotelsWithPricesWith20Hotels() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        helper.setImplicitWait(driver,20);
        driver.get(Constants.COMPANY_URL);
        homePage.enterSearchTerm(Constants.SEARCH_TERM);
        homePage.submitSearch();
        welcomePage.selectDrodownElement(1);
        Map map=welcomePage.printAndReturnHotelNameWithPrice(20);
        Assert.assertEquals(map.size(),20);
    }

    @Test(priority = 2)
    public void sortByPriceLowToHigh() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        helper.setImplicitWait(driver,20);
        driver.get(Constants.COMPANY_URL);
        homePage.enterSearchTerm(Constants.SEARCH_TERM);
        homePage.submitSearch();
        welcomePage.selectDrodownElement(2);
        welcomePage.validatePriceLowToHighFilter(2);
    }

    @Test(priority = 3)
    public void sortByPriceHighToLow() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        helper.setImplicitWait(driver,20);
        driver.get(Constants.COMPANY_URL);
        homePage.enterSearchTerm(Constants.SEARCH_TERM);
        homePage.submitSearch();
        welcomePage.selectDrodownElement(3);
        welcomePage.validatePriceLowToHighFilter(2);
    }
}

