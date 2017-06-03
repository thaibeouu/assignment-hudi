package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContactPage extends BasePage{

	public static final String PAGE_URL = "https://1fichier.com/contact.html";
	@FindBy(how = How.XPATH, using = "//input[@name='contact_email']")
	WebElement fieldEmail;

	@FindBy(how = How.XPATH, using = "//textarea[@name='contact_message']")
    WebElement fieldMessage;

	@FindBy(how = How.ID, using = "sub")
	WebElement buttonSubmit;

	@FindBy(how = How.ID, using = "messDiv")
	public WebElement dialogSuccess;

	public ContactPage(WebDriver driver)
	{
		super(driver);
	}
	private void setEmail(String text){
		sendKeysElement(fieldEmail, text);
	}

	private void setPassword(String text){
		sendKeysElement(fieldMessage, text);
	}

	private void submitMessage(){
	    buttonSubmit.click();
	}
	public void sendMessage(String email, String message){
		this.setEmail(email);
		this.setPassword(message);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)", "");
		this.submitMessage();
	}
}
