package PageObject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FromSearch extends BaseClass {
	WebDriver driver;

	public FromSearch(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@placeholder='From Station']")
	WebElement fromsearch;

	@FindBy(xpath = "//div[@title='Delhi Azadpur']")
	WebElement stationname;

	@FindBy(xpath = "//div[@class='autocomplete']//div[@title]")
	List<WebElement> dropdownOptions;

	@FindBy(xpath = "//input[@title='Select Departure date for availability']")
	WebElement selectDate;

	@FindBy(xpath = "//a[@class='icon-right-big']") // XPath for the next month button
	WebElement nextMonthButton;

	public void sendFromSearchStationInFromSearchField() {
		try {
			fromsearch.clear();
			fromsearch.sendKeys("Del");
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectStationFromStationnameDropdown() {
		try {
			stationname.click();
			System.out.println("Selected station: " + stationname.getAttribute("title"));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<String> getDropdownOptions() {
		List<String> optionsText = new ArrayList<>();
		List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[@class='autocomplete']//div[@title]"));
		for (WebElement option : dropdownOptions) {
			String title = option.getAttribute("title");
			if (title != null && !title.isEmpty()) {
				optionsText.add(title.trim());
			}
		}
		return optionsText;
	}

	public void date() {
		try {
			selectDate.click();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectDate() {
		try {
			// Get the current date
			LocalDate currentDate = LocalDate.now();

			LocalDate targetDate = currentDate.plusMonths(1);

			String dayXPath = "(//td[text()='" + targetDate.getDayOfMonth() + "'])[2]";

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXPath)));
			dateElement.click();

		} catch (NoSuchElementException e) {
			System.out.println("Error selecting date: " + e.getMessage());
		}
	}
}