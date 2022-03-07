package genericFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import config.Configuration;
import drivers.ApplicationDriver;
import pages.AccountLoginPage;
import reports.ExtentTestManager;

public class CommonFunctions extends VariableLibrary {

	public static WebDriver driver;

	static {
		try {
			Configuration.loadConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public static void fOpenBrowser() throws InterruptedException {
		driver = ApplicationDriver.createDriver(Configuration.getBrowser());
		AccountLoginPage.fLogin();
	}

	@AfterSuite
	public void killInstance() {
		driver.quit();
	}

	public List<WebElement> getElementList(By by) {
		return driver.findElements(by);
	}

	public void printInExtentReport(String sMessage) {
		ExtentTestManager.getTest().log(Status.INFO, sMessage);
	}

	public WebElement getElement(By by) {
		return driver.findElement(by);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public static void selectByVisibleText(By by, String value) {
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(value);
	}

	public static void selectByVisibleIndex(By by, int value) {
		Select select = new Select(driver.findElement(by));
		select.selectByIndex(value);
	}

	public static void WaitBeforeClick(By by) {
		// wait(1);
		waitForElement(by, VariableLibrary.LONG_WAIT);
		driver.findElement(by).click();
	}

	public static boolean isElementPresent(By by, String sMessage) {
		try {
			driver.findElement(by);
			ExtentTestManager.getTest().log(Status.PASS, sMessage);
			return true;
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, sMessage);
			Assert.fail(e.getMessage());
			return false;
		}
	}

	/**
	 * Description: Generate Random String
	 *
	 * @param iStringLength: String to be generated for which length of characters.
	 * @return String : Base on Input length String is returned
	 * @author Akshay Patel
	 * @since
	 */
	public String fRandomString(int iStringLength) {
		StringBuilder sStringBuffer = new StringBuilder();
		for (int x = 0; x < iStringLength; x++) {
			sStringBuffer.append((char) ((int) (Math.random() * 26) + 97));
		}
		return sStringBuffer.toString();
	}

	/**
	 * Description: Generate Random integer
	 *
	 * @param iLength: String to be generated for which length of characters.
	 * @return int : Base on Input length integer value is returned
	 * @author Akshay Patel
	 * @since
	 */
	public int fRandomInteger(int iLength) {
		int random = (int) (Math.random() * iLength + 1);
		return random;
	}

	public static void type(By locator, String msg) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(msg);
	}

	public static void javaScriptClick(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(by));
	}

	public static void scrollIntoView(By bylocator) throws InterruptedException {
		WebElement element = driver.findElement(bylocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public static boolean waitForElement(By by, Integer timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void waitElementToBeClickable(By by, int seconds) {
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static String getDate(int i, String format) {
		LocalDate localDate = LocalDate.now();
		String sDate = null;

		switch (i) {
		// today's date yyyy-MM-dd
		case 1:
			sDate = localDate.format(DateTimeFormatter.ofPattern(format));
			// System.out.println("today's date " + sDate);
			break;

		// yesterday's date
		case 2:
			sDate = localDate.minusDays(1).format(DateTimeFormatter.ofPattern("MM/d/yyyy"));
			// System.out.println("yesterday date:- "+sDate);
			break;
		case 3:
			sDate = java.util.Calendar.getInstance().getTime().toString().replaceAll("\\s+", "-").replaceAll(":", "-");
			break;
		}
		return sDate;
	}

	public void fwaitForpageLoad() {
		while (true) {
			if ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0")) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * while (true) { String page_status = (String) ((JavascriptExecutor)
		 * driver).executeScript("return document.readyState"); if
		 * (page_status.equals("complete")) break; }
		 */
	}

	public void waitForPresent() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someid")));
	}

	public static void wait(WebDriver driver, int seconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(pageLoadCondition);
	}

	public static String[] freadpropertiesfile(String sPropertyKey) throws Exception {
		String Creds;
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream("./config.properties");
		prop.load(ip);
		Creds = prop.getProperty(sPropertyKey);

		String[] arrOfStr = Creds.split(",");

		return arrOfStr;

	}

	public static void fReadfromExcelSheet(String sTestCaseID, String sSheetName) throws FilloException {
		// Generate Excel connection with given Path
		Fillo fillo = new Fillo();
		Connection oConnection = fillo.getConnection("TestCaseData1.xlsx");
		VariableLibrary.oConnection = oConnection;
		// Generate Query for which we need Data to be retrieved
		String strQuery = "Select * from " + sSheetName + " where testCaseID='" + sTestCaseID + "' ";
		// Execute query against generated connection
		VariableLibrary.oRecordset = oConnection.executeQuery(strQuery);
		VariableLibrary.oRecordset.moveFirst();
	}

	public static String printException(Throwable e, String s) {

		String traceInfo = null;
		StackTraceElement[] stackTrace;

		if (e != null)
			stackTrace = e.getStackTrace();
		else
			stackTrace = e.getStackTrace();

		System.out.println(stackTrace);

		for (StackTraceElement trace : stackTrace) {
			if (trace.getMethodName().equalsIgnoreCase(s)) {
				traceInfo = trace.getClassName() + "." + trace.getMethodName() + ":" + trace.getLineNumber() + "("
						+ trace.getFileName() + ")";
				System.out.println("Failure Class name: " + trace.getClassName() + " Method name: "
						+ trace.getMethodName() + " Line no " + trace.getLineNumber());
				break;
			}
		}
		return traceInfo;
	}

	public static String getPropertiesValue(String enterStringValue) throws IOException {
		InputStream inputStream = null;
		String result = "";
		try {

			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = CommonFunctions.class.getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// get the property value and print it out
			result = prop.getProperty(enterStringValue);
			// System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;

	}

	public static void clearInput(By by) {
		driver.findElement(by).clear();
	}

	public static void logStatus(String sStatus, String sMsg) {
		switch (sStatus.toLowerCase()) {
		case "info":
			ExtentTestManager.getTest().log(Status.INFO, sMsg);
			break;

		case "pass":
			ExtentTestManager.getTest().log(Status.PASS, sMsg);
			break;

		case "fail":
			ExtentTestManager.getTest().log(Status.FAIL, sMsg);
			break;
		}

	}
}
