package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import genericFunctions.CommonFunctions;

public class UserPage extends BasePage {

	HomePage homePage = new HomePage();

	By byAddUser = By.xpath("//div[@title='Add User']");

	By byfirstName = By.xpath("//input[@id='firstName']");
	By bylastName = By.xpath("//input[@id='lastName']");
	By byEmail = By.xpath("//input[@id='email']");
	By byPhoneNumber = By.xpath("//input[@id='phoneNumber']");
	By byButtonSave = By.xpath("//button[@form='addUserModal']");
	By bySaveMsg = By.xpath("//span[text()='User created successfully.']");

	public void adminPage(String sTestCaseId, String sSheetName) {
		try {
			logStatus("info", "Executing User Module");
			CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

			homePage.fSelectMainMenu(oRecordset.getField("module"));
			homePage.fSelectSubMenu(oRecordset.getField("subModule"));

			javaScriptClick(byAddUser);

			// enter first name
			waitForElement(byfirstName, LONG_WAIT);
			type(byfirstName, oRecordset.getField("firstName"));

			// enter last name
			type(bylastName, oRecordset.getField("lastName"));

			// enter email
			type(byEmail, oRecordset.getField("email"));

			// enter phone number
			type(byPhoneNumber, oRecordset.getField("phoneNumber"));

			// click on nsave button
			javaScriptClick(byButtonSave);
			waitForElement(bySaveMsg, LONG_WAIT);
			isElementPresent(bySaveMsg, "User Created");

			logStatus("info", "Execution completed for User Module");

		} catch (Exception e) {
			logStatus("fail", "*******Admin Page**********");
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
