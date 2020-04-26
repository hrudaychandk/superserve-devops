package org.hruday;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class WebAppTest {

    private WebDriver driver;

    @BeforeClass
    private void setup() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\hkatakam\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
        // Opens a website hosted on below machine
        driver.get("http://localhost:3333");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    private void testHomePage() throws Exception{
        assertEquals("superServe", driver.getTitle());
        driver.findElement(By.xpath("/html/body/p/a")).click();
        Thread.sleep(1000);
        assertEquals("superServe Greetings", driver.getTitle());
        String greetingsBody = driver.findElement(By.xpath("/html/body/p")).getText();
        assertEquals("Hello, World!", greetingsBody);
    }

    @AfterClass
    public void tearOff() {
        driver.quit();
    }
}
