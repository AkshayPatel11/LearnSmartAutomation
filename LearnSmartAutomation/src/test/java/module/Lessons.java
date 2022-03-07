package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.LessonsPage;

public class Lessons extends BasePage {
	LessonsPage lesson = new LessonsPage();

	@Test
	public void fTestCase_1() throws Exception {
		lesson.fLessonPage("testCase_1", "LessonsTestSuite");
	}
}
