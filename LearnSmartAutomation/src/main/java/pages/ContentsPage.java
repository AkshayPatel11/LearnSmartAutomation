package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import genericFunctions.CommonFunctions;

public class ContentsPage extends BasePage {

	HomePage homePage = new HomePage();

	By byAddContent = By.xpath("//div[@title='Add Content']");
	By byContentType = By.xpath("//form[@id='addContentModal']//select");
	By byContentName = By.xpath("//input[@id='lessonName']");
	By byContentDetails = By.xpath("//input[@id='contentText']");
	By byDescription = By.xpath("//textarea");
	By bySaveButton = By.xpath("//span[text()='Save']");
	By bySaveMsg = By.xpath("//span[text()='Content saved successfully']");

	public void fContentsPage(String sTestCaseId, String sSheetName) {
		try {
			CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);
			logStatus("info", "Executing contents Page");
			homePage.fSelectMainMenu(oRecordset.getField("mainMenu"));
			homePage.fSelectSubMenu(oRecordset.getField("subMenu"));

			fwaitForpageLoad();

			// click on add button
			javaScriptClick(byAddContent);

			// Enter content type
			waitForElement(byContentType, LONG_WAIT);
			selectByVisibleText(byContentType, oRecordset.getField("contentType"));

			// Content Name
			type(byContentName, oRecordset.getField("contentName"));

			// Contents Details
			type(byContentDetails, oRecordset.getField("contentsDetails"));

			// Enter Desc
			type(byDescription, oRecordset.getField("Description"));

			Thread.sleep(1000);
			clearInput(byDescription);
			javaScriptClick(bySaveButton);

			// verify msg
			waitForElement(bySaveMsg, LONG_WAIT);
			Assert.assertEquals(driver.findElement(bySaveMsg).isDisplayed(), true, "Contents not saved successfully");
			logStatus("info", "Execution completed for Contents Module");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logStatus("fail", "fContentsPage");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			logStatus("fail", "fContentsPage");
		}
	}

}
