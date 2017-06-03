package tests;

import com.google.common.base.Predicate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.IndexPage;
import pages.InfoPage;
import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class UserRelatedTest {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private LoginPage loginPage;
    private IndexPage indexPage;
    private InfoPage infoPage;
    private String emailLogin;
    private String passwordLogin;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        emailLogin = properties.getProperty("email");
        passwordLogin = properties.getProperty("password");
        String chromeDriverPath = properties.getProperty("chromedriverpath");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        this.driver = new ChromeDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        indexPage = PageFactory.initElements(driver, IndexPage.class);
        infoPage = PageFactory.initElements(driver, InfoPage.class);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void stopDriver(){
        this.driver.quit();
    }

    @Test
    public void mainTest(){
        testLogin();
        testCookiesManipulation();
        testDownloadMultipleFiles();
        testPersonalInfo();
        testLogout();
    }
    private void testLogin(){
        driver.get(LoginPage.PAGE_URL);
        driver.manage().deleteAllCookies();
        loginPage.login(emailLogin, passwordLogin);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), IndexPage.PAGE_URL);
    }

    private void testLogout(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fancybox-overlay")));
        indexPage.buttonLogout.click();
    }

    private void testCookiesManipulation(){
        Cookie cookieSid = driver.manage().getCookieNamed("SID");
        Cookie newCookie = new Cookie(cookieSid.getName(), cookieSid.getValue(), cookieSid.getDomain(), cookieSid.getPath(), cookieSid.getExpiry(), true);
        driver.manage().deleteCookieNamed("SID");
        driver.manage().addCookie(newCookie);
        Assert.assertTrue(driver.manage().getCookieNamed("SID").isSecure());
    }

    private void testDownloadMultipleFiles(){
        if (indexPage.fileElements.size() > 0)
        for (WebElement fileElement: indexPage.fileElements){
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fancybox-overlay-fixed")));
            fileElement.click();
            indexPage.linkDownload.click();
            //fluent wait
            new FluentWait<>(driver).
                    withTimeout(3, TimeUnit.SECONDS).
                    pollingEvery(100, TimeUnit.MILLISECONDS).
                    ignoring(NoSuchElementException.class).
                    until((Predicate<WebDriver>) d -> {
                        WebElement urlElement = d.findElement(By.xpath("//a[starts-with(.,'https://1fichier.com/?')]"));
                        urlElement.click();
                        return urlElement.isDisplayed();
                    });
            indexPage.buttonCloseDownloadDialog.click();
        }
    }

    private void testPersonalInfo(){
        driver.get(InfoPage.PAGE_URL);
        //random data
        String uuid = UUID.randomUUID().toString();
        int randomNumber = ThreadLocalRandom.current().nextInt(0, infoPage.optionsCountries.size());
        infoPage.submitPersonalInfoForm(uuid, randomNumber);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Update completed successfully");
        alert.accept();
    }
}
