package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.QuestionsPage;

public class Questions extends BasePage {

	QuestionsPage quesPage = new QuestionsPage();

	@Test
	public void fTestCase_1() throws Exception {
		quesPage.questionPage("testCase_1", "QuestionsTestSuite");
	}

}
