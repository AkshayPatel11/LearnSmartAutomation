package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import genericFunctions.CommonFunctions;

public class LessonsPage extends BasePage {
	HomePage homePage = new HomePage();
	By byTextAddSection = By.xpath("//div[@title='Add Lesson']");

	// Details Tab
	By byTextSectionName = By.xpath("//input[@id='lessonName']");
	By byInputDescription = By.xpath("//textarea");

	// Topics Tab
	By byTextTopicsTab = By.xpath("//form[@id='addLessonModal']//span[text()='Topics']");
	By byListTopicSection = By.xpath("//div[@id='addTopicListModal']//tr/td[2]");
	By byButtonTopicPopUpSave = By.xpath("//div[@id='addTopicListModal']/div//button[text()='Save']");
	By byPopUpTopicsSection = By.xpath("//div[@id='addTopicListModal']/div");

	// Quizz Tab
	By byTextQuizzesTab = By.xpath("//form[@id='addLessonModal']//span[text()='Quizzes']");
	By byListQuizSection = By.xpath("//div[@id='addQuizListModal']//tr/td[2]");
	By byPopUpQuizz = By.xpath("//div[@id='addQuizListModal']/div");
	By byButtonQuizzPopUpSave = By.xpath("//div[@id='addQuizListModal']/div//button[text()='Save']");

	By byTextAddOrDelete = By.xpath("//div[@id='sub-header']//button[1]");
	By bySaveButton = By.xpath("//button[@form='addLessonModal']");

	public void fAddQuizzes() {
		try {
			int i = 1;
			// click on quizzes tab
			javaScriptClick(byTextQuizzesTab);

			// click on add/delete button
			waitForElement(byTextAddOrDelete, MEDIUM_WAIT);
			javaScriptClick(byTextAddOrDelete);

			waitForElement(byPopUpQuizz, MEDIUM_WAIT);

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

	public void fAddTopics() {
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
				if (w.getText().equalsIgnoreCase(oRecordset.getField("topicsName"))) {
					driver.findElement(By.xpath("//div[@id='addTopicListModal']//tr[" + i + "]/td[1]")).click();
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
			type(byTextSectionName, oRecordset.getField("lessionName"));

			// enter desc
			clearInput(byInputDescription);
			type(byInputDescription, oRecordset.getField("Description"));
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}

	}

	public void fLessonPage(String sTestCaseId, String sSheetName) {
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
				fAddTopics();
				Thread.sleep(1000);
				fAddQuizzes();
			} else if (oRecordset.getField("editFlag").equalsIgnoreCase("1")) {

			}
			javaScriptClick(bySaveButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
