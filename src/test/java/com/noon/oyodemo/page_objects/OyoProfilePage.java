package com.noon.oyodemo.page_objects;

import com.noon.oyodemo.DriverBase;
import com.noon.oyodemo.utilities.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OyoProfilePage {

    @Autowired
    ActionHelper helper;

    By editProfile = By.xpath("//div[@class='c-1ufrzn0']//span[2]/*[1]");
    By usernameTextBox = By.id("user_name");
    By welcomeUserName = By.className("sideMenuAuthButton__text");
    By update = By.className("c-1eb234r");

    public OyoProfilePage() throws Exception {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public String editProfileUsername(String updatedUsername) throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        helper.waitUntilElementVisible(driver,editProfile);
        helper.gotoSleep(driver,5000);
        driver.findElement(editProfile).click();
        driver.findElement(usernameTextBox).clear();
        driver.findElement(usernameTextBox).sendKeys(updatedUsername);
        driver.findElement(update).click();
        helper.gotoSleep(driver,5000);
        String sideMenuAuthButton__text = driver.findElement(By.className("sideMenuAuthButton__text")).getText();
        return sideMenuAuthButton__text;
    }
    public String getProfilePageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

}
