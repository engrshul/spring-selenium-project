package com.ecomm.oyodemo.page_objects;

import com.ecomm.oyodemo.DriverBase;
import com.ecomm.oyodemo.utilities.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OyoHomePage {

    @Autowired
    ActionHelper helper;

    By loginSignup = By.className("sideMenuAuthButton__text");
    By searchTextBox = By.id("autoComplete__home");
    By searchButton = By.className("searchButton");

    public OyoHomePage() throws Exception {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public OyoHomePage enterSearchTerm(String searchTerm) {
        WebDriver driver=DriverBase.getDriver();
        driver.findElement(searchTextBox).sendKeys(searchTerm);
        driver.findElement(searchTextBox).sendKeys("  ");
        helper.gotoSleep(driver,5000);
        driver.findElement(searchTextBox).sendKeys(Keys.DOWN);
        driver.findElement(searchTextBox).sendKeys(Keys.ENTER);
        return this;
    }
    public OyoHomePage submitSearch() throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        driver.findElement(searchButton).click();
        return this;
    }

    public OyoHomePage goToLoginSignUpPage() {

        WebDriver driver=DriverBase.getDriver();
        driver.findElement(loginSignup).click();
        return this;
    }
    public String getTitle(WebDriver driver) throws InterruptedException {
        return driver.getTitle();
    }
}
