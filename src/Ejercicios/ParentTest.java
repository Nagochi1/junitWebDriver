package Ejercicios;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParentTest {
	
	WebDriver driver = null;
	public static final String MOVIE = "It";
	public static final String YEAR = "1990";
	private static final String CHROME_DRIVER_NAME = "webdriver.chrome.driver";
	private static final String GENERIC_DRIVER_LOCATION = "C:\\testautomation\\libs\\drivers\\";
	private static final String CHROME_DRIVER_LOCATION = GENERIC_DRIVER_LOCATION + "chromedriver.exe";
	private static final int MEDIUM_TIMEOUT = 15;
	private static final String IMDB_URL = "https://www.imdb.com/";
	public WebDriverWait wait;

	@Before
	public void setup() {
		ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
		System.setProperty(CHROME_DRIVER_NAME, CHROME_DRIVER_LOCATION);
	    driver = new ChromeDriver(ops);
	    wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
	    driver.get(IMDB_URL);
	}
	
	@Test	
	protected void searchMovie(String movie) {
		WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q"))); //driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys(movie);
		WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("navbar-submit-button"))); //driver.findElement(By.id("navbar-submit-button"));
		searchBtn.click();
	}
	
	@Test
	protected void validateMovie(String movie, String year) {
		By by = By.className("result_text");
		WebElement correctElement = getCorrectElement(by, movie, year);
		
		if (correctElement != null) {
			correctElement.findElement(By.linkText(movie));
			System.out.println(movie + " from " + year + " found");
		}else{
			System.out.println("Test FAILED - " + movie + " from " + year + " NOT found");
		}
	}

	@Test
	protected void openMovie(String movie, String year) {
		By by = By.className("result_text");
		WebElement correctElement = getCorrectElement(by, movie, year);
		
		if (correctElement != null) {
			correctElement.findElement(By.linkText(movie)).click();
			System.out.println(movie + " from " + year + " found");
		}else{
			System.out.println("Test FAILED - " + movie + " from " + year + " NOT found");
		}		
	}
	
	@Test
	protected void playTrailer() {
		String xPath = "//a[contains(@class, 'slate_button')]";
		WebElement trailer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
		
		String timeXPath = "//a[contains(@class, 'slate_button')]/following-sibling::div[@class='caption']";		
		WebElement captionTime = driver.findElement(By.xpath(timeXPath));
		String text = captionTime.getText();
		int time = getTime(text);		
		
		trailer.click();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Wait interrupted");
			e.printStackTrace();
		}
		
	}
	
	@Test
	private int getTime(String text) {
		String time = text.split(" ")[0];
		String minutesStr = time.split(":")[0];
		String secondsStr = time.split(":")[1];
		int mins = Integer.parseInt(minutesStr);
		int secs = Integer.parseInt(secondsStr);
		int totalMiliSeconds = ((mins * 60) + (secs)) * 1000;
		return totalMiliSeconds;
	}
	
	@Test
	public WebElement getCorrectElement (By by, String textToFind, String textToFind2) {
		List<WebElement> elements = driver.findElements(by);
		for (WebElement element : elements) {
			if (element.getText().contains(textToFind) && element.getText().contains(textToFind2))
				return element;
		}
		return null;		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}