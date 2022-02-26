package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{

	By byGetLogo = By.xpath("//img[@class='logo_image']");
	By byBtnStudent = By.xpath("//span[text()='Student']");
	
	public void fVerifyLogo() {
		printInExtentReport("sMessage");
		fwaitForpageLoad();
		javaScriptClick(byGetLogo);
		printInExtentReport("Click on Student Button");
		javaScriptClick(byBtnStudent);
	}
}
