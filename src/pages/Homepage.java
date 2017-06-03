package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Homepage extends BasePage{

	public static final String PAGE_URL = "https://1fichier.com";
	@FindBy(how = How.CLASS_NAME, using = "file-handler")
	WebElement fieldUpload;

	@FindBy(how = How.ID, using = "sub")
    public WebElement buttonSubmit;

	@FindBy(how = How.ID, using = "files")
	WebElement formUpload;

	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	public void setFileToUpload(String location){
		fieldUpload.sendKeys(location);
	}
	public void submitForm(){
		formUpload.submit();
	}
}
