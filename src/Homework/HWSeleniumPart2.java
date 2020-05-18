package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.beans.Transient;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HWSeleniumPart2 {

    WebDriver driver;
    WebDriverWait wait;
    By registration = By.cssSelector("[class='header-topline__user-link link-dashed']");
    By bottomRegistration = By.cssSelector("[class='auth-modal__register-link']");
    By nameRegCell = By.cssSelector("[formcontrolname='name']");
    By mailRegCell = By.cssSelector("[formcontrolname='username']");
    By passwordRegCell = By.cssSelector("[type='password']");
    By tupToBottomReg = By.cssSelector("[type='submit']");
    By nameError = By.cssSelector("[class='form__row js-name validation_type_error']");


    @BeforeMethod
    public void beforeMethod () {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/");    //go to rozetka site

    }

    //<input _size_medium="" formcontrolname="name" type="text" class="ng-untouched ng-pristine ng-invalid"> - start cell value
    //<input _size_medium="" formcontrolname="name" type="text" class="ng-pristine ng-invalid ng-touched"> - error cell value

    @Test
    public void registrationWithoutAnything() {
        WebElement tupToRegistrationBottom = driver.findElement(registration);

        wait.until(ExpectedConditions.elementToBeClickable(tupToRegistrationBottom)).click();   // click to check-in
        WebElement tupToBottomRegistration = driver.findElement(bottomRegistration);

        wait.until(ExpectedConditions.elementToBeClickable(tupToBottomRegistration)).click();    // click to registration bottom
        WebElement tupToFirstRegistrationCell = driver.findElement(nameRegCell);

        //First test point
        wait.until(ExpectedConditions.elementToBeClickable(nameRegCell)).click();   // tup to cell for a name input
        WebElement tupToSecondRegistrationCell = driver.findElement(mailRegCell);
        //String expectedNameStatus = driver.findElement(By.cssSelector("[class='ng-pristine ng-invalid ng-touched']"));
        //assertTrue(actualNameStatus.contains(expectedNameStatus));
        String expectedNameStatus = driver.getCssValue("border-color");

        //Second Test Point
        wait.until(ExpectedConditions.elementToBeClickable(mailRegCell)).click();   // tup to cell for a mail input
        WebElement tupToThirdRegistrationSell = driver.findElement(passwordRegCell);

       // <input _size_medium="" formcontrolname="username" type="text" class="ng-untouched ng-pristine ng-invalid">
        // class="ng-pristine ng-invalid ng-touched" - failed cell mail

        //Third Test Point
        wait.until(ExpectedConditions.elementToBeClickable(passwordRegCell)).click();   //tup to cell for a password input
        WebElement tupToReg = driver.findElement(tupToBottomReg);
        wait.until(ExpectedConditions.elementToBeClickable(tupToBottomReg)).click();    // tup to registration bottom

    }



    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
