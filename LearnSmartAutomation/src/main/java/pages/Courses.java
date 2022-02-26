package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

	public void fAddDetails() throws Exception {
		courses = new Courses();
		fwaitForpageLoad();
		javaScriptClick(byTextAddButton);
		waitForElement(byDropDownCoursetype, VariableLibrary.MEDIUM_WAIT);
		selectByVisibleText(byDropDownCoursetype, "TEST SERIES");
		type(byInputCourseName, oRecordset.getField("courseName"));
		type(byInputDescription, oRecordset.getField("Description"));
		selectByVisibleText(byDropDownCourseAccessMode, oRecordset.getField("courseAccessMode"));
		selectByVisibleText(byDropDownCourseContentType, oRecordset.getField("courseContentType"));
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

	public void fCourses(String sTestCaseId, String sSheetName) throws Exception {
		CommonFunctions.fReadfromExcelSheet(sTestCaseId, sSheetName);

		homePage.fSelectMainMenu(oRecordset.getField("mainMenu"));
		homePage.fSelectSubMenu(oRecordset.getField("subMenu"));

		if (oRecordset.getField("detailsFlag").equalsIgnoreCase("1"))
			fAddDetails();

		if (oRecordset.getField("sectionsFlag").equalsIgnoreCase("1"))
			fAddSections();

		if (oRecordset.getField("quizzesFlag").equalsIgnoreCase("1"))
			fAddQuizzes();

		// click on save button
		javaScriptClick(bySaveButton);
	}

}
