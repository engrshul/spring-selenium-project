package com.noon.oyodemo.page_objects;

import com.noon.oyodemo.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;


@Component
public class OyoLoginSignUpPage {

    By changeLogin = By.className("loginCard__changeLogin");
    By userNameTextBox = By.className("textTelInput__input");
    By passwordTextBox = By.className("textInput__input");
    By verifyButton = By.className("loginCard__button");
    By errorMsg=By.className("loginCard__errorSpan");


    public OyoLoginSignUpPage() throws Exception {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public OyoLoginSignUpPage changeLogin() throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        driver.findElement(changeLogin).click();
        return this;
    }

    public OyoLoginSignUpPage provideCredentials(String userName,String password) throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        driver.findElement(userNameTextBox).sendKeys(userName);
        driver.findElement(passwordTextBox).sendKeys(password);
        return this;
    }

    public OyoLoginSignUpPage verifyNumber() throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        driver.findElement(verifyButton).click();
        return this;
    }
    public String getErrorText() {
        WebDriver driver=DriverBase.getDriver();
        return driver.findElement(errorMsg).getText();
    }
}
