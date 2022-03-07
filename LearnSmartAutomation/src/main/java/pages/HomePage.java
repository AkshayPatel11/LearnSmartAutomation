package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import genericFunctions.CommonFunctions;
import genericFunctions.VariableLibrary;

public class HomePage extends BasePage {

	HomePage homePage;

	By byOpenMenu = By.xpath("//i[contains(@class,' bx-menu')]");
	By byListMainMenu = By.xpath("//span[@lmsnavmenu]");
	By byListSubMenu = By.xpath("//a[contains(@class,'nav-link')]/span");

	public void fSelectMainMenu(String sMainMenu) {

		fwaitForpageLoad();
		if (!driver.findElement(byListMainMenu).isDisplayed())
			javaScriptClick(byOpenMenu);
		CommonFunctions.waitForElement(byListMainMenu, VariableLibrary.LONG_WAIT);
		List<WebElement> list = driver.findElements(byListMainMenu);
		for (WebElement w : list) {
			if (w.getText().trim().equals(sMainMenu)) {
				w.click();
			}
		}
	}

	public void fSelectSubMenu(String sSubMenu) {
		CommonFunctions.waitForElement(byListSubMenu, VariableLibrary.LONG_WAIT);
		List<WebElement> subMenuList = driver.findElements(byListSubMenu);
		for (WebElement w : subMenuList) {
			if (w.getText().equals(sSubMenu)) {
				w.click();
			}
		}
	}
}
