package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Homepage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HomepageTest {
    private WebDriver driver;
    private Homepage homepage;

    @Before
    public void setUp() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String chromeDriverPath = properties.getProperty("chromedriverpath");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        this.driver = new ChromeDriver();
        homepage = PageFactory.initElements(driver, Homepage.class);
        driver.get(Homepage.PAGE_URL);
        driver.manage().deleteAllCookies();
    }

    @After
    public void stopDriver(){
        this.driver.quit();
    }

    @Test
    public void testPageTitle(){
        String pageTitle = driver.getTitle();
        Assert.assertEquals("1fichier.com: Cloud Storage", pageTitle);
    }
    @Test
    public void testUpload(){
        homepage.setFileToUpload("E:\\401910.png");
        homepage.submitForm();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(By.className("box_header")).isDisplayed());
    }
}
