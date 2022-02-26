package module;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.Courses;

public class Course extends BasePage {

	Courses courses = new Courses();

	@Test
	public void fTestCase_1() throws Exception {
		courses.fCourses("testCase_1", "DemoTestCaseSheet");

	}

}
