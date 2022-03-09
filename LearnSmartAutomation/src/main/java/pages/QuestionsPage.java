package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import genericFunctions.CommonFunctions;

public class QuestionsPage extends BasePage {
	HomePage homePage = new HomePage();

	By byAddQue = By.xpath("//div[@title='Add Question']");

	By byDropDownQuesType = By.xpath(
			"//form[@id='addQuestionModal']//div[contains(text(),'Question Type:')]//following-sibling::div/select");

	By byDropDownDifficultyLevel = By.xpath(
			"//form[@id='addQuestionModal']//div[contains(text(),'Difficulty Level:')]//following-sibling::div/select");

	By byTextName = By.xpath("//form[@id='addQuestionModal']//input");
	By byTextArea = By.xpath("//form[@id='addQuestionModal']//textarea");

	By bySaveButton = By.xpath("//button[@form='addQuestionModal']");

	By bySaveMsg = By.xpath("//span[text()='Question added successfully']");

	public void questionPage(String sTestCaseId, String sSheetName) {
		try {

			logStatus("info", "Exceuting Questions Module");
			CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

			homePage.fSelectMainMenu(oRecordset.getField("module"));
			homePage.fSelectSubMenu(oRecordset.getField("subModule"));

			// click on add button
			javaScriptClick(byAddQue);

			// select question type
			waitForElement(byDropDownQuesType, LONG_WAIT);
			selectByVisibleText(byDropDownQuesType, oRecordset.getField("questionsType"));

			// enter ques name
			type(byTextName, oRecordset.getField("questionName"));

			// enter difficulty
			selectByVisibleText(byDropDownDifficultyLevel, oRecordset.getField("difficultyLevel"));

			// enter desc
			type(byTextArea, oRecordset.getField("questionText"));
			logStatus("info", "Execution completed for Questions Module");

			javaScriptClick(bySaveButton);

			waitForElement(bySaveMsg, LONG_WAIT);
			isElementPresent(bySaveMsg, "Question Added");

		} catch (Exception e) {
			logStatus("fail", "*******Questions Page**********");
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
