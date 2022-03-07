package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.CoursesPages;

public class Course extends BasePage {

	CoursesPages courses = new CoursesPages();

	@Test(enabled = false)
	public void fTestCase_1() throws Exception {
		courses.fCourses("testCase_1", "DemoTestCaseSheet");
	}

	@Test
	public void fTestCase_2() throws Exception {
		courses.fCourses("testCase_2", "DemoTestCaseSheet");
	}

}
