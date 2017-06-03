package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.ContactPage;
import pages.Homepage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ContactTest {
    private WebDriver driver;
    private ContactPage contactPage;

    @Before
    public void setUp() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String chromeDriverPath = properties.getProperty("chromedriverpath");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        this.driver = new ChromeDriver();
        contactPage = PageFactory.initElements(driver, ContactPage.class);
        driver.get(ContactPage.PAGE_URL);
        driver.manage().deleteAllCookies();
    }

    @After
    public void stopDriver(){
        this.driver.quit();
    }

    @Test
    public void testUpload(){
        contactPage.sendMessage("hudi-assignment2@mailinator.com", "Merci pour votre service.");
        Assert.assertTrue(contactPage.dialogSuccess.isDisplayed());
    }
}
