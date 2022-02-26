package drivers;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ApplicationDriver {
	public static WebDriver createDriver(String browser) {
		WebDriver driver = null;
		
		switch (browser.toLowerCase()) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--test-type");
				option.addArguments("--disable-popup-bloacking");
				option.setCapability(ChromeOptions.CAPABILITY, option);
				option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(option);
				break;
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10000));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(Configuration.getBaseURL());
		return driver;
	}
}

