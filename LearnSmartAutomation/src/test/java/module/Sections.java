package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.SectionPage;

public class Sections extends BasePage {

	SectionPage section = new SectionPage();

	@Test
	public void fTestCase_1() throws Exception {
		section.fSections("testCase_1", "SectionTestSuite");
	}

}
