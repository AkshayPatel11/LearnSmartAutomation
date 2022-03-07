package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.TopicsPage;

public class Topics extends BasePage {

	TopicsPage topics = new TopicsPage();

	@Test
	public void fTestCase_1() throws Exception {
		topics.fTopicsPage("testCase_1", "TopicsTestSuite");
	}
}
