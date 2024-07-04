package TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import PageObject.FromSearch;
import TestBase.BaseTest;
import Utilities.ExcelUtility;

public class TC_001_Fromsearch extends BaseTest {
	@Test(groups = { "smoke", "sanity", "regression" })
	public void setup_fromsearchstation() throws IOException, InterruptedException {
		FromSearch fs = new FromSearch(driver);
		fs.sendFromSearchStationInFromSearchField();
		fs.selectStationFromStationnameDropdown();
		List<String> dropdownOptions = fs.getDropdownOptions();
		String excelPath = "/Users/hestabit/eclipse-workspace/ExpectedStationsname.xlsx";
		ExcelUtility excel = new ExcelUtility(excelPath);
		int rowCount = excel.getRowCount("Stationsname");
		List<String> expectedStations = new ArrayList<>();
		for (int i = 1; i <= rowCount; i++) {
			String cellData = excel.getCellData("Stationsname", i, 0);
			if (cellData != null && !cellData.isEmpty()) {
				expectedStations.add(cellData.trim());
			}
		}
		boolean allMatched = true;
		for (String expectedStation : expectedStations) {
			if (!dropdownOptions.contains(expectedStation)) {
				System.out.println("Not Matched: " + expectedStation);
				allMatched = false;
			} else {
				System.out.println("Matched: " + expectedStation);
			}
		}
		if (allMatched) 
		{
			System.out.println("All data matched.");
		} else {
			System.out.println("Some data did not match.");
		}
		fs.date();
		fs.selectDate();
	}
}
