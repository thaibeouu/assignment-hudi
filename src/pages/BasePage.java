package pages;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

	WebDriver driver;
	private String PAGE_URL;
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}

	public void clickElement(WebElement webElement){
		webElement.click();
	}

	public void sendKeysElement(WebElement webElement, String text){
		webElement.clear();
		webElement.sendKeys(text);
		Assert.assertEquals(webElement.getAttribute("value"), text);
	}

	public String getPAGE_URL(){
		return PAGE_URL;
	}
}
