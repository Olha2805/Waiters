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

        //First test point
        WebElement tupToFirstRegistrationCell = driver.findElement(nameRegCell);
        wait.until(ExpectedConditions.elementToBeClickable(nameRegCell)).click();   // tup to cell for a name input

        //Second Test Point
        WebElement tupToSecondRegistrationCell = driver.findElement(mailRegCell);
        wait.until(ExpectedConditions.elementToBeClickable(mailRegCell)).click();   // tup to cell for a mail input

        //Third Test Point
        WebElement tupToThirdRegistrationSell = driver.findElement(passwordRegCell);
        wait.until(ExpectedConditions.elementToBeClickable(passwordRegCell)).click();   //tup to cell for a password input
        WebElement tupToReg = driver.findElement(tupToBottomReg);

        wait.until(ExpectedConditions.elementToBeClickable(tupToBottomReg)).click();    // tup to registration bottom

        boolean expectedName = driver.getPageSource().contains(" Введите свое имя на кириллице ");
        boolean expectedMail = driver.getPageSource().contains(" Введите свою эл. почту ");
        boolean expectedPassword = driver.getPageSource().contains(" Пароль должен быть не менее 6 символов, содержать цифры и латинские буквы, в том числе заглавные, и не должен совпадать с именем и эл. почтой ");
        assertTrue(expectedName&&expectedMail&&expectedPassword, "Name or(and) email or(and) password were entered");
    }


    @Test
    public void registrationWithoutOneParametr() {
        WebElement tupToRegistrationBottom1 = driver.findElement(registration);

        wait.until(ExpectedConditions.elementToBeClickable(tupToRegistrationBottom1)).click();   // click to check-in
        WebElement tupToBottomRegistration1 = driver.findElement(bottomRegistration);

        wait.until(ExpectedConditions.elementToBeClickable(tupToBottomRegistration1)).click();    // click to registration bottom

        //First test point
        WebElement tupToFirstRegistrationCell1 = driver.findElement(nameRegCell);
        wait.until(ExpectedConditions.elementToBeClickable(nameRegCell)).click();   // tup to cell for a name input
        tupToFirstRegistrationCell1.sendKeys("блаблабла");

        //Second Test Point
        WebElement tupToSecondRegistrationCell1 = driver.findElement(mailRegCell);
        wait.until(ExpectedConditions.elementToBeClickable(mailRegCell)).click();   // tup to cell for a mail input

        //Third Test Point
        WebElement tupToThirdRegistrationSell1 = driver.findElement(passwordRegCell);
        wait.until(ExpectedConditions.elementToBeClickable(passwordRegCell)).click();   //tup to cell for a password input
        WebElement tupToReg1 = driver.findElement(tupToBottomReg);

        wait.until(ExpectedConditions.elementToBeClickable(tupToBottomReg)).click();    // tup to registration bottom

        String actua1lName = tupToFirstRegistrationCell1.getCssValue("class");
        String expected1Name = "ng-dirty ng-valid ng-touched";
        String actual1Mail = tupToSecondRegistrationCell1.getCssValue("class");
        String expected1Mail = "ng-pristine ng-invalid ng-touched";
        String actual1Password = tupToThirdRegistrationSell1.getCssValue("class");
        String expected1Password = "ng-pristine ng-invalid ng-touched";
        assertTrue(expected1Name.contains(actua1lName) &&
                        expected1Mail.contains(actual1Mail)&&
                        expected1Password.contains(actual1Password),
                "You should to enter text just in one cell");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
