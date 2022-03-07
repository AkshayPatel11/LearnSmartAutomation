package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.QuizzesPage;

public class Quizzes extends BasePage {

	QuizzesPage quizzes = new QuizzesPage();

	@Test
	public void fTestCase_1() throws Exception {
		quizzes.fAddQuizzesPage("testCase_1", "QuizzesTestSuite");
	}

}
