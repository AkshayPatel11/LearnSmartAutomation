package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import genericFunctions.CommonFunctions;

public class TopicsPage extends BasePage {

	HomePage homePage = new HomePage();

	By byTextAddSection = By.xpath("//div[@title='Add Topic']");

	// Details Tab
	By byTextSectionName = By.xpath("//input[@id='topicName']");
	By byInputDescription = By.xpath("//textarea");

	// Contents Tab
	By byTextTopicsTab = By.xpath("//form[@id='addTopicModal']//span[text()='Contents']");
	By byListTopicSection = By.xpath("//div[@id='addContentListModal']//tr/td[2]");
	By byButtonTopicPopUpSave = By.xpath("//div[@id='addContentListModal']/div//button[text()='Save']");
	By byPopUpTopicsSection = By.xpath("//div[@id='addContentListModal']/div");

	// Quizz Tab
	By byTextQuizzesTab = By.xpath("//form[@id='addTopicModal']//span[text()='Quizzes']");
	By byListQuizSection = By.xpath("//div[@id='addQuizListModal']//tr/td[2]");
	By byPopUpQuizz = By.xpath("//div[@id='addQuizListModal']/div");
	By byButtonQuizzPopUpSave = By.xpath("//div[@id='addQuizListModal']/div//button[text()='Save']");

	By byTextAddOrDelete = By.xpath("//div[@id='sub-header']//button[1]");
	By bySaveButton = By.xpath("//button[@form='addTopicModal']");

	public void fAddQuizzes() {
		try {
			int i = 1;

			// click on quizzes tab
			javaScriptClick(byTextQuizzesTab);

			// click on add/delete button
			waitForElement(byTextAddOrDelete, LONG_WAIT);
			Thread.sleep(1000);
			javaScriptClick(byTextAddOrDelete);

			Thread.sleep(1000);
			waitForElement(byPopUpQuizz, MEDIUM_WAIT);

			Thread.sleep(1000);
			// Select section
			List<WebElement> list = driver.findElements(byListQuizSection);
			for (WebElement w : list) {
				if (w.getText().equalsIgnoreCase(oRecordset.getField("quizeName"))) {
					driver.findElement(By.xpath("//div[@id='addQuizListModal']//tr[" + i + "]/td[1]")).click();
					break;
				}
				i++;
			}

			javaScriptClick(byButtonQuizzPopUpSave);

		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	public void fAddContents() {
		try {
			int i = 1;

			// click on tab button
			javaScriptClick(byTextTopicsTab);

			// click on add/delete biutton
			waitForElement(byTextAddOrDelete, LONG_WAIT);
			javaScriptClick(byTextAddOrDelete);

			waitForElement(byPopUpTopicsSection, LONG_WAIT);

			// Select topics
			List<WebElement> list = driver.findElements(byListTopicSection);
			for (WebElement w : list) {
				if (w.getText().equalsIgnoreCase(oRecordset.getField("contentsName"))) {
					driver.findElement(By.xpath("//div[@id='addContentListModal']//tr[" + i + "]/td[1]")).click();
					break;
				}
				i++;
			}
			Thread.sleep(1000);
			javaScriptClick(byButtonTopicPopUpSave);
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	public void fAddDetails() {
		try {
			// enter section name
			waitForElement(byTextSectionName, LONG_WAIT);

			Thread.sleep(1000);
			type(byTextSectionName, oRecordset.getField("topicName"));

			// enter desc
			clearInput(byInputDescription);
			type(byInputDescription, oRecordset.getField("Description"));
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}

	}

	public void fTopicsPage(String sTestCaseId, String sSheetName) {
		try {
			CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

			homePage.fSelectMainMenu(oRecordset.getField("mainMenu"));
			homePage.fSelectSubMenu(oRecordset.getField("subMenu"));

			boolean bNewFlag = false;
			if (oRecordset.getField("createNewFlag").equalsIgnoreCase("1")) {
				fwaitForpageLoad();
				javaScriptClick(byTextAddSection);

				fAddDetails();
				Thread.sleep(1000);
				fAddContents();

				Thread.sleep(1000);
				fAddQuizzes();
			} else if (oRecordset.getField("editFlag").equalsIgnoreCase("1")) {

			}
			Thread.sleep(1000);
			javaScriptClick(bySaveButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
}
