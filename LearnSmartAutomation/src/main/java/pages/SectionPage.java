package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import genericFunctions.CommonFunctions;

public class SectionPage extends BasePage {

	HomePage homePage = new HomePage();

	//
	By byTextAddSection = By.xpath("//div[@title='Add Section']");

	// Details Tab
	By byTextSectionName = By.xpath("//input[@id='sectionName']");
	By byInputDescription = By.xpath("//textarea");

	// Lessons Tab
	By byLessonTab = By.xpath("//form//span[text()='Lessons']");
	By byListLessonSection = By.xpath("//div[@id='addLessonListModal']//tr/td[2]");

	// Quizz Tab
	By byTextQuizzesTab = By.xpath("//span[text()='Quizzes']");
	By byListQuizSection = By.xpath("//div[@id='addQuizListModal']//tr/td[2]");

	By byTextAddOrDelete = By.xpath("//div[@id='sub-header']//button[1]");
	By byButtonPopUpSave = By.xpath("//button[text()='Save']");
	By byPopUpSection = By.xpath("//div[@id='addLessonListModal']/div");

	By bySaveButton = By.xpath("//button[@form='addSectionModal']");

	public void fAddDetails() throws Exception {
		fwaitForpageLoad();

		javaScriptClick(byTextAddSection);

		// enter section name
		waitForElement(byTextSectionName, LONG_WAIT);
		System.out.println(oRecordset.getField("sectionName"));
		Thread.sleep(1000);
		type(byTextSectionName, oRecordset.getField("sectionName"));

		// enter desc
		clearInput(byInputDescription);
		type(byInputDescription, oRecordset.getField("Description"));
	}

	public void fAddLesson() throws Exception {
		int i = 1;

		// click on tab button
		javaScriptClick(byLessonTab);

		// click on add/delete biutton
		javaScriptClick(byTextAddOrDelete);

		waitForElement(byPopUpSection, LONG_WAIT);

		// Select section
		List<WebElement> list = driver.findElements(byListLessonSection);
		for (WebElement w : list) {
			if (w.getText().equalsIgnoreCase(oRecordset.getField("lessonName"))) {
				driver.findElement(By.xpath("//div[@id='addLessonListModal']//tr[" + i + "]/td[1]")).click();
				break;
			}
			i++;
		}

		javaScriptClick(byButtonPopUpSave);

	}

	public void fAddQuizzes() throws Exception {
		int i = 1;
		// click on quizzes tab
		javaScriptClick(byTextQuizzesTab);

		// click on add/delete button
		javaScriptClick(byTextAddOrDelete);

		waitForElement(byPopUpSection, LONG_WAIT);

		// Select section
		List<WebElement> list = driver.findElements(byListQuizSection);
		for (WebElement w : list) {
			if (w.getText().equalsIgnoreCase(oRecordset.getField("quizeName"))) {
				driver.findElement(By.xpath("//div[@id='addLessonListModal']//tr[" + i + "]/td[1]")).click();
				break;
			}
			i++;
		}

		javaScriptClick(byButtonPopUpSave);

	}

	public void fSections(String sTestCaseId, String sSheetName) throws Exception {
		CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

		homePage.fSelectMainMenu(oRecordset.getField("mainMenu"));
		homePage.fSelectSubMenu(oRecordset.getField("subMenu"));
		boolean bNewFlag = false;
		if (oRecordset.getField("createNewFlag").equalsIgnoreCase("1")) {
			fAddDetails();
			fAddLesson();
			fAddQuizzes();
		} else if (oRecordset.getField("editFlag").equalsIgnoreCase("1")) {

		}
		javaScriptClick(bySaveButton);
	}

}
