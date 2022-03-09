package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import genericFunctions.CommonFunctions;

public class QuizzesPage extends BasePage {

	HomePage homePage = new HomePage();

	By byAddQuizzes = By.xpath("//div[@title='Add Quiz']");

	// details
	By byDetailsTab = By.xpath("//form//span[text()='Details']");
	By byQuizzType = By.xpath("//form[@id='addQuizModal']//select");
	By byQuizName = By.xpath("//form[@id='addQuizModal']//input[@id='quizName']");
	By byDescription = By.xpath("//textarea");
	By byDurationInMin = By.xpath("//form[@id='addQuizModal']//input[@id='duration']");
	By byPassingPercn = By.xpath("//form[@id='addQuizModal']//input[@id='passingPercentage']");
	By byQuizPrize = By.xpath("//form[@id='addQuizModal']//input[@id='quizPrice']");
	By byAutoSumbitTimerCheckBox = By.xpath("//form[@id='addQuizModal']//input[@type='checkbox']");
	By byClickOnSaveButton = By.xpath("//button[@form='addQuizModal']");

	// Questions
	By byQuesTab = By.xpath("//form//span[text()='Questions']");
	By byAddOrDeleteQuiz = By.xpath("//div[@id='sub-header']//button[1]");
	By byAddButton = By.xpath("//div[@id='addQuestionListModal']//button[text()='Save']");
	By byPopUp = By.xpath("//div[@id='addQuestionListModal']/div");
	By byListTopicSection = By.xpath("//div[@id='addQuestionListModal']//tr/td[2]");

	By bySequence = By.xpath("");

	By bySaveButton = By.xpath("//button[@form='addQuizModal']");

	public void fAddDetails() {
		try {
			logStatus("info", "Adding Details");
			fwaitForpageLoad();

			javaScriptClick(byDetailsTab);

			// enter quiz type
			waitForElement(byQuizzType, LONG_WAIT);
			selectByVisibleText(byQuizzType, oRecordset.getField("quizType"));

			// enter quiz name

			type(byQuizName, oRecordset.getField("quizName"));

			// enter quiz desc
			type(byDescription, oRecordset.getField("Description"));

			// enter quiz duration
			type(byDurationInMin, oRecordset.getField("durationInMin"));

			// enter passing perc
			type(byPassingPercn, oRecordset.getField("passingPercn"));

			// auto submit check box
			if (oRecordset.getField("autoSubmitFlag").equals("Yes"))
				javaScriptClick(byAutoSumbitTimerCheckBox);

			// enter price
			type(byQuizPrize, oRecordset.getField("quizPrice"));
			logStatus("info", "Details Added");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logStatus("fail", e.getMessage());
			Assert.fail("Details not added in Quizzess");
		}
	}

	public void fAddQuestions() {
		try {
			logStatus("info", "Adding questions");
			int i = 1;
			javaScriptClick(byQuesTab);
			waitForElement(byAddOrDeleteQuiz, LONG_WAIT);

			// Add question
			javaScriptClick(byAddOrDeleteQuiz);

			waitForElement(byPopUp, LONG_WAIT);

			// Select topics
			List<WebElement> list = driver.findElements(byListTopicSection);
			for (WebElement w : list) {
				if (w.getText().equalsIgnoreCase(oRecordset.getField("questionsBankName"))) {
					driver.findElement(By.xpath("//div[@id='addQuestionListModal']//tr[" + i + "]/td[1]")).click();
					break;
				}
				i++;
			}
			Thread.sleep(1000);
			javaScriptClick(byAddButton);

			waitForElement(By.xpath("//form//tbody//tr[1]//td[2]//input[1]"), LONG_WAIT);

			// Add Sequence
			driver.findElement(By.xpath("//form//tbody//tr[1]//td[2]//input[1]"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a") + oRecordset.getField("sequence"));

			// Add Marks
			driver.findElement(By.xpath("//form//tbody//tr[1]//td[3]//input[1]"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a") + oRecordset.getField("marks"));

			logStatus("info", "Questions Added");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logStatus("fail", e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	public void fAddQuizzesPage(String sTestCaseId, String sSheetName) {
		try {
			logStatus("info", "Exceuting Quizzez Page");
			CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

			homePage.fSelectMainMenu(oRecordset.getField("mainMenu"));
			homePage.fSelectSubMenu(oRecordset.getField("subMenu"));

			fwaitForpageLoad();

			// click on add
			waitForElement(byAddQuizzes, LONG_WAIT);
			javaScriptClick(byAddQuizzes);

			Thread.sleep(1000);
			fAddDetails();

			Thread.sleep(1000);
			fAddQuestions();

			Thread.sleep(1000);
			javaScriptClick(bySaveButton);

			Thread.sleep(1000);
			waitForElement(By.xpath("//span[text()='Quiz created successfully']"), LONG_WAIT);
			// Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Quiz Created
			// successfully']")).(), true);
			isElementPresent(By.xpath("//span[text()='Quiz created successfully']"), "Quiz not created");
			logStatus("info", "Exceution completed for  Quizzez Page");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logStatus("fail", "Exceution completed for  Quizzez Page" + e.getMessage());
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			logStatus("fail", "Exceution completed for  Quizzez Page" + e.getMessage());
			Assert.fail(e.getMessage());
		}

	}
}
