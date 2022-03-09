package module;

import org.testng.annotations.Test;

import pages.UserPage;
import pages.BasePage;

public class UserModule extends BasePage {

	UserPage admin = new UserPage();

	@Test
	public void fTestCase_1() throws Exception {
		admin.adminPage("testCase_1", "AdminModule");
	}

}