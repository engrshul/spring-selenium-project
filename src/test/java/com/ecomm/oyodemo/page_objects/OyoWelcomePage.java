package com.ecomm.oyodemo.page_objects;

import com.ecomm.oyodemo.DriverBase;
import com.ecomm.oyodemo.utilities.ActionHelper;
import com.ecomm.oyodemo.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OyoWelcomePage {

    @Autowired
    ActionHelper helper;

    private static final Logger logger = LogManager.getLogger(OyoWelcomePage.class);

    By sortByDropdown = By.className("dropdown");
    By dropDownItems = By.className("dropdown__item");
    By hotelNames = By.className("listingHotelDescription__hotelName");
    By finalPrices = By.className("listingPrice__finalPrice");
    By welcomeLink = By.className("sideMenuAuthButton__text");


    public OyoWelcomePage() throws Exception {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public OyoWelcomePage goToProfilePage() throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        helper.gotoSleep(driver,5000);
        helper.waitUntilElementVisible(driver,welcomeLink);
        driver.get(Constants.PROFILE_SECTION);
        return this;
    }
    public OyoWelcomePage selectDrodownElement(int index) throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        WebElement element =driver.findElement(sortByDropdown);
        element.click();
        List<WebElement> options=element.findElements(dropDownItems);
        options.get(index).click();
        return this;
    }
    public Map printAndReturnHotelNameWithPrice(int noOfElementsToPrint) throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();
        Map hotelPriceMap = new HashMap<>();
        List<WebElement> hotelNamesList=driver.findElements( hotelNames);
        List<WebElement> finalPricesList =driver.findElements(finalPrices);

            for(int i=0;i< noOfElementsToPrint;i++) {
                String hotelName = hotelNamesList.get(i).getAttribute("innerHTML");
                String finalPrice = finalPricesList.get(i).getAttribute("innerHTML");
                hotelPriceMap.put(hotelName,finalPrice);
                logger.info("Hotel Name is "+hotelName+" and Final Price is "+finalPrice);
            }
        return hotelPriceMap;
    }

    public void validatePriceLowToHighFilter(int noOfElementsToPrint) throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();

        List<WebElement> finalPricesList =driver.findElements(finalPrices);

            int firstHotelPrice = Integer.parseInt(finalPricesList.get(0).getAttribute("innerHTML").substring(1));
            int secondHotelPrice =Integer.parseInt(finalPricesList.get(0).getAttribute("innerHTML").substring(1));
            if(firstHotelPrice > secondHotelPrice) {
                Assert.fail();
            }
    }

    public void validatePriceHighToLowFilter(int noOfElementsToPrint) throws InterruptedException {
        WebDriver driver=DriverBase.getDriver();

        List<WebElement> finalPricesList =driver.findElements(finalPrices);

        int firstHotelPrice = Integer.parseInt(finalPricesList.get(0).getAttribute("innerHTML").substring(1));
        int secondHotelPrice =Integer.parseInt(finalPricesList.get(0).getAttribute("innerHTML").substring(1));
        if(firstHotelPrice < secondHotelPrice) {
            Assert.fail();
        }
    }

    public String getTitle(WebDriver driver) throws InterruptedException {
      return driver.getTitle();

    }
}
