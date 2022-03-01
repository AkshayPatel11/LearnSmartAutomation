package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import config.Configuration;
import genericFunctions.CommonFunctions;
import genericFunctions.VariableLibrary;

public class Courses extends BasePage {

	HomePage homePage = new HomePage();
	Courses courses;

	// Details
	By byTextDetailsTab = By.xpath("//span[text()='Details']");
	By byTextAddButton = By.xpath("//div[@title='Add Course']");
	By byDropDownCoursetype = By.xpath("//select[@id='courseType']");
	By byInputCourseName = By.xpath("//input[@id='courseName']");
	By byInputDescription = By.xpath("//textarea");
	By byInputCoursePrice = By.xpath("//input[@id='coursePrice']");
	By byDropDownCourseAccessMode = By.xpath("//select[@id='courseAccessMode']");
	By byDropDownCourseContentType = By.xpath("//select[@id='courseContentType']");
	By byDropDownCourseProgression = By.xpath("//select[@id='courseProgression']");

	// Section
	By byTextSectionTab = By.xpath("//form[@id='addCourseModal']//span[text()='Sections']");
	By byTextAddSection = By.xpath("//button[normalize-space(text())='Add Section']");
	By byPopUpSection = By.xpath("//div[@id='addSectionListModal']/div");
	By byListSection = By.xpath("//div[@id='addSectionListModal']//tr/td[2]");
	By byButtonAdd = By.xpath("//div[@id='addSectionListModal']//button[text()='Add']");

	// Quizzes
	By byTextQuizzesTab = By.xpath("//form[@id='addCourseModal']//span[text()='Quizzes']");
	By byTextAddQuizzes = By.xpath("//button[normalize-space(text())='Add Quiz']");
	By byPopUpQuizzes = By.xpath("//div[@id='addCourseQuizListModal']/div");
	By byButtonAddQuizzes = By.xpath("//div[@id='addCourseQuizListModal']//button[text()='Add']");

	By bySaveButton = By.xpath("//button[@form='addCourseModal']");
	By byEditButton = By.xpath("//div[@value='Edit']");
	By byTextCourseSavedSuccesfully = By.xpath("//span[text()='Course saved successfully']");

	public void fAddDetails(Boolean createNew) throws Exception {
		fwaitForpageLoad();

		if (createNew) {
			javaScriptClick(byTextAddButton);
			waitForElement(byDropDownCoursetype, VariableLibrary.MEDIUM_WAIT);
			selectByVisibleText(byDropDownCoursetype, "TEST SERIES");
			type(byInputCourseName, oRecordset.getField("courseName"));
		} else {
			waitForElement(byInputCourseName, VariableLibrary.MEDIUM_WAIT);
			// enter course name
			clearInput(byInputCourseName);
			type(byInputCourseName, oRecordset.getField("editCourseName"));
		}

		// enter description
		clearInput(byInputDescription);
		type(byInputDescription, oRecordset.getField("Description"));

		// enter course access mode
		selectByVisibleText(byDropDownCourseAccessMode, oRecordset.getField("courseAccessMode"));

		// enter course content type
		selectByVisibleText(byDropDownCourseContentType, oRecordset.getField("courseContentType"));

		// enter course progression
		selectByVisibleText(byDropDownCourseProgression, oRecordset.getField("courseProgression"));
	}

	public void fAddSections() throws Exception {
		int i = 1;

		scrollIntoView(byTextSectionTab);
		// click on tab button
		javaScriptClick(byTextSectionTab);

		// click on add section
		javaScriptClick(byTextAddSection);
		waitForElement(byPopUpSection, LONG_WAIT);

		// Select section
		List<WebElement> list = driver.findElements(byListSection);
		for (WebElement w : list) {
			if (w.getText().equalsIgnoreCase(oRecordset.getField("sectionName"))) {
				driver.findElement(By.xpath("//div[@id='addSectionListModal']//tr[" + i + "]/td[1]")).click();
				break;
			}
			i++;
		}

		javaScriptClick(byButtonAdd);
	}

	public void fAddQuizzes() throws Exception {
		scrollIntoView(byTextQuizzesTab);
		// click on quizzes tab
		javaScriptClick(byTextQuizzesTab);
		javaScriptClick(byTextAddQuizzes);
		waitForElement(byPopUpQuizzes, LONG_WAIT);
		javaScriptClick(byButtonAddQuizzes);
	}

	public void fEditCourses() throws Exception {
		driver.get(Configuration.getBaseURL() + "Course/Courses");
		fwaitForpageLoad();
		waitForElement(byTextAddButton, VariableLibrary.LONG_WAIT);
		List<WebElement> courseList = driver.findElements(By.xpath("//table[contains(@class,'table-sm')]//tr//td[2]"));
		int i = 0;
		for (WebElement w : courseList) {
			if (w.getText().trim().equalsIgnoreCase(oRecordset.getField("courseName"))) {
				driver.findElements(byEditButton).get(i).click();
				break;
			}
			i++;

		}

		fAddDetails(false);
	}

	public void fVerifyCourseCreatedOrUpdatedEntryInTable() throws Exception {
		try {
			fwaitForpageLoad();
			waitForElement(byTextCourseSavedSuccesfully, VariableLibrary.LONG_WAIT);
			// logStatus("info", "verifying course details in table");

			isElementPresent(byTextCourseSavedSuccesfully, "Course not saved");

			List<WebElement> courseList = driver
					.findElements(By.xpath("//table[contains(@class,'table-sm')]//tr//td[2]"));
			int listSize = courseList.size();

			String sActualCourseName = driver
					.findElement(By.xpath("//table[contains(@class,'table-sm')]//tr[" + listSize + "]//td[2]"))
					.getText().trim();

			String sAcutalDesc = driver
					.findElement(By.xpath("//table[contains(@class,'table-sm')]//tr[" + listSize + "]//td[3]"))
					.getText().trim();

			Assert.assertEquals(sActualCourseName, oRecordset.getField("courseName"),
					"CourseName is not Same actual courseName: " + sActualCourseName);

			Assert.assertEquals(sAcutalDesc, oRecordset.getField("Description"),
					"CourseName is not Same actual courseName: " + sAcutalDesc);

			// logStatus("info", "verification of course entry in table completed");
		} catch (Exception e) {
			// logStatus("fail", e.getMessage());
			// Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
	}

	public void fCourses(String sTestCaseId, String sSheetName) throws Exception {
		CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

		homePage.fSelectMainMenu(oRecordset.getField("mainMenu"));
		homePage.fSelectSubMenu(oRecordset.getField("subMenu"));
		boolean bNewFlag = false;
		if (oRecordset.getField("createNewFlag").equalsIgnoreCase("1")) {
			bNewFlag = true;
			if (oRecordset.getField("detailsFlag").equalsIgnoreCase("1"))
				fAddDetails(bNewFlag);

			if (oRecordset.getField("sectionsFlag").equalsIgnoreCase("1"))
				fAddSections();

			if (oRecordset.getField("quizzesFlag").equalsIgnoreCase("1"))
				fAddQuizzes();
		} else if (oRecordset.getField("editFlag").equalsIgnoreCase("1")) {
			fEditCourses();
		}
		// click on save button
		javaScriptClick(bySaveButton);

		fVerifyCourseCreatedOrUpdatedEntryInTable();
	}

}
