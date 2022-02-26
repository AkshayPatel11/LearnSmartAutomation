package pages;

import org.openqa.selenium.By;

import config.Configuration;
import genericFunctions.VariableLibrary;

public class AccountLoginPage extends BasePage{

	AccountLoginPage accountLoginPage = new AccountLoginPage();
	//Sign In PopUp Xpath
	public static By byTextLogin = By.xpath("//span[text()='Log In']");
	public static By byTextEmail = By.xpath("//input[@id='txtuserName']");
	public static By byButtonSendOTP = By.xpath("//input[@id='sendOtp']");
	public static By byButtonSubmit = By.xpath("//button[text()='Log in']");
	public static By byInputBoxOTP = By.xpath("//input[@id='Input_Otp']");
	
	/**
	 * Description: To Login Into Application
	 *
	 * @author: akshay.patel
	 */
	
	public static void fLogin() throws InterruptedException {
		WaitBeforeClick(byTextLogin);
		type(byTextEmail, Configuration.getUserName());
		javaScriptClick(byButtonSendOTP);
		Thread.sleep(VariableLibrary.SHORT_WAIT);
		type(byInputBoxOTP, Configuration.getOTP());
		javaScriptClick(byButtonSubmit);
	}
}
