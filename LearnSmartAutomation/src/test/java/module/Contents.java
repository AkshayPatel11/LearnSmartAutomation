package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.ContentsPage;

public class Contents extends BasePage {

	ContentsPage contents = new ContentsPage();

	@Test
	public void fTestCase_1() throws Exception {
		contents.fContentsPage("testCase_1", "ContentsTestSuite");
	}

}
