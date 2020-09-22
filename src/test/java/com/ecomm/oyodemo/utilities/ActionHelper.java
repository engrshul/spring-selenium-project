package com.ecomm.oyodemo.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// this class manages common actions across webpages

@Component
public class ActionHelper {

    private static final Logger logger = LogManager.getLogger(ActionHelper.class);

    public  void setImplicitWait(WebDriver driver,int seconds) {
        logger.info("setting implicit wait");
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public  void waitUntilElementVisible(WebDriver driver,By by) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public  String getTimeStampString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmm");
        return simpleDateFormat.format(new Date());
    }
    public  void gotoSleep(WebDriver driver,int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




