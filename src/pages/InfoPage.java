package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class InfoPage extends BasePage{

	public static final String PAGE_URL = "https://1fichier.com/console/details.pl";

	@FindBy(how = How.XPATH, using = "//input[@type='radio'][@value='1']")
	WebElement radioTitleChoice;

	@FindBy(how = How.XPATH, using = "//input[@name='nom']")
    WebElement fieldFirstName;

	@FindBy(how = How.CLASS_NAME, using = "valider")
	WebElement buttonValidate;

	@FindBy(how = How.XPATH, using = "//select[@name='pays']")
	WebElement dropdownCountries;

	@FindBy(how = How.XPATH, using = "//option")
	public List<WebElement> optionsCountries;

	public InfoPage(WebDriver driver)
	{
		super(driver);
	}

	private void setFirstName(String text){
		fieldFirstName.clear();
		sendKeysElement(fieldFirstName, text);
	}

	private void clickRadioTitle(){
	    clickElement(radioTitleChoice);
	}
	public void submitPersonalInfoForm(String firstName, Integer countryIndex){
		clickRadioTitle();
		this.setFirstName(firstName);
		clickElement(dropdownCountries);
		clickElement(optionsCountries.get(countryIndex));
		clickElement(buttonValidate);
	 }
}
