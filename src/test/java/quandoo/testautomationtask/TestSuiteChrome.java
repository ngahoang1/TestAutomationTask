package quandoo.testautomationtask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import junit.framework.TestCase;

public class TestSuiteChrome extends TestCase {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLoginSuccess() throws Exception {
		driver.get("http://the-internet.herokuapp.com/login");
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::i[1]"))
				.click();

		assertTrue(driver.findElement(By.id("flash"))
				.getText().contains("You logged into a secure area is present"));
		
	}

	@Test
	public void testLoginFailure1() throws Exception {
		driver.get("http://the-internet.herokuapp.com/login");
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("daniel");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("noname");
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::i[1]"))
				.click();
		assertTrue(driver.findElement(By.id("flash")).getText().contains("Your username is invalid"));
	}

	@Test
	public void testLoginFailure2() throws Exception {
		driver.get("http://the-internet.herokuapp.com/login");
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("noname");
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::i[1]"))
				.click();
		assertTrue(driver.findElement(By.id("flash")).getText().contains("Your password is invalid"));
	}

	@Test
	public void testHover1() throws Exception {
		driver.get("http://the-internet.herokuapp.com/hovers");

		WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
		Actions action = new Actions(driver);
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5")).getText().isEmpty());
		action.moveToElement(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5")).getText().contains("user1"));
		
	}
	@Test
	public void testHover2() throws Exception {
		driver.get("http://the-internet.herokuapp.com/hovers");

		WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img"));
		Actions action = new Actions(driver);
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h5")).getText().isEmpty());
		action.moveToElement(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h5")).getText().contains("user2"));
	}
	@Test
	public void testHover3() throws Exception {
		driver.get("http://the-internet.herokuapp.com/hovers");

		WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/img"));
		Actions action = new Actions(driver);
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/h5")).getText().isEmpty());
		action.moveToElement(element).build().perform();
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/h5")).getText().contains("user3"));
		
	}
	@Test
	public void testSortableDataTable() throws Exception {
		driver.get("http://the-internet.herokuapp.com/tables");
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Example 2'])[1]/following::span[1]"))
				.click();

		assertTrue(isAscendingSortedList(getLastNameItems()));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Example 2'])[1]/following::span[1]"))
				.click();
		getLastNameItems();
		assertTrue(isDescendingSortedList(getLastNameItems()));
	}

	/**
	 * get last name items from table2
	 * @return list of last name from the top to bottom
	 */
	private List<String> getLastNameItems() {
		WebElement table_element = driver.findElement(By.id("table2"));
		List<WebElement> tr_collection = table_element.findElements(By.xpath("id('table2')/tbody/tr"));

		List<String> lastnames = new ArrayList<String>();
		int index = 0;
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));

			for (WebElement tdElement : td_collection) {
				if (tdElement.getAttribute("class").contentEquals("last-name")) {
					lastnames.add(index, tdElement.getText());
				}

			}
			index++;
		}
		return lastnames;
	}

	/**
	 * check if list of string is sorted in ascending order
	 * @param list
	 * @return boolean
	 */
	public static boolean isAscendingSortedList(List<String> list) {
		if (list == null || list.isEmpty())
			return true;

		if (list.size() == 1)
			return true;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(list.get(i - 1)) < 0)
				return false;
		}

		return true;
	}

	/**
	 * check if list of string is sorted in decending order
	 * @param list
	 * @return boolean
	 */
	public static boolean isDescendingSortedList(List<String> list) {
		if (list == null || list.isEmpty())
			return true;

		if (list.size() == 1)
			return true;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).compareTo(list.get(i - 1)) > 0)
				return false;
		}

		return true;
	}


	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}

