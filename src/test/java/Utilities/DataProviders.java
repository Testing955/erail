package Utilities;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1

	@DataProvider(name = "ExpectedStationsname")
	public String[][] getData() throws IOException {
		String path = "/Users/hestabit/eclipse-workspace/ExpectedStationsname.xlsx";

		System.out.println("Excel Path: " + path); // Debugging: Print path

		File excelFile = new File(path);
		if (!excelFile.exists()) {
			throw new IOException("File not found at specified path: " + path);
		}

		ExcelUtility xlutil = new ExcelUtility(path);// creating an object for XLUtility

		int totalrows = xlutil.getRowCount("Stationname");
		int totalcols = xlutil.getCellCount("Stationname", 1);

		String logindata[][] = new String[totalrows][totalcols];// created for two dimension array which can store the
																// data user and password

		for (int i = 1; i <= totalrows; i++) // 1 //read the data from xl storing in two deminsional array
		{
			for (int j = 0; j < totalcols; j++) // 0 i is rows j is col
			{
				logindata[i - 1][j] = xlutil.getCellData("Stationname", i, j); // 1,0
			}
		}
		return logindata;// returning two dimension array

	}

}
