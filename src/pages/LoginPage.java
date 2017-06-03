package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{

	public static final String PAGE_URL = "https://1fichier.com/login.pl";
	@FindBy(how = How.XPATH, using = "//input[@name='mail']")
	WebElement fieldEmail;

	@FindBy(how = How.XPATH, using = "//input[@name='pass']")
    WebElement fieldPassword;

	@FindBy(how = How.ID, using = "sub")
	WebElement buttonLogin;

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	private void setEmail(String text){
		sendKeysElement(fieldEmail, text);
	}

	private void setPassword(String text){
		sendKeysElement(fieldPassword, text);
	}

	private void clickLogin(){
	    clickElement(buttonLogin);
	}
	 public void login(String email, String password){
		this.setEmail(email);
		this.setPassword(password);
		this.clickLogin();
	 }
}
