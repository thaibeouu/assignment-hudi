package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class IndexPage extends BasePage{

	public static final String PAGE_URL = "https://1fichier.com/console/index.pl";
	@FindBy(how = How.CLASS_NAME, using = "fancybox-overlay")
	public WebElement fancybox;

	@FindBy(how = How.XPATH, using = "//a[@href='https://1fichier.com/logout.pl']")
    public WebElement buttonLogout;

	@FindBy(how = How.CLASS_NAME, using = "file")
	public List<WebElement> fileElements;

	@FindBy(how = How.CLASS_NAME, using = "file_link")
	public WebElement linkDownload;

	@FindBy(how = How.CLASS_NAME, using = "fancybox-close")
	public WebElement buttonCloseDownloadDialog;

	public IndexPage(WebDriver driver)
	{
		super(driver);
	}
}
