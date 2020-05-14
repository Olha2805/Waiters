package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium1 {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");

        By search = By.cssSelector("[name='search']");
        WebElement searchEl = driver.findElement(search);
        Thread.sleep (5000);
        searchEl.sendKeys("iPhone");
        Thread.sleep (5000);
        String name = driver.findElement(By.cssSelector("[class='header-topline__user-libk link-dashed']")).getText();
        System.out.println(name);
        driver.quit();

    }
}
