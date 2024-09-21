package abhishek.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import abhishek.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser") :  prop.getProperty("browser");

		if (browsername.contains("chrome")) {
					
			ChromeOptions co = new ChromeOptions();
			
			if(browsername.contains("headless")) co.addArguments("headless");
			
			driver = new ChromeDriver(co);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen

		} else if (browsername.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}

		else if (browsername.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}

		else {

			driver = new ChromeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to HashMap - Jackson Databind

		return new ObjectMapper().readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(
				"C:\\Users\\2107883\\OneDrive - Cognizant\\Desktop\\Selenium\\Screenshot\\" + testCaseName + ".png"));
		return "C:\\Users\\2107883\\OneDrive - Cognizant\\Desktop\\Selenium\\Screenshot\\" + testCaseName + ".png";

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.close();
	}
}
