package com.sqa.jf.helpers;

import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.sqa.jf.helpers.data.*;

public class DataHelperTest {
	@DataProvider(name = "textData")
	public Object[][] getData() {
		Object[][] data;
		data = DataHelper.getTextFileData("src/main/resources/", "data.csv", TextFormat.CSV, true);
		DisplayHelper.multArray(data);
		return data;
	}

	@DataProvider(name = "textDataTyped")
	public Object[][] getDataTyped() {
		Object[][] data;
		data = DataHelper.getTextFileData("src/main/resources/", "data.csv", TextFormat.CSV, true, Integer.TYPE,
				Boolean.TYPE);
		DisplayHelper.multArray(data);
		return data;
	}

	@DataProvider(name = "textDataTypedWithString")
	public Object[][] getDataTypedWithString() {
		Object[][] data;
		data = DataHelper.getTextFileData("src/main/resources/", "data2.csv", TextFormat.CSV, true, String.class,
				Integer.TYPE, Boolean.TYPE);
		DisplayHelper.multArray(data);
		return data;
	}

	@Test(dataProvider = "textData")
	public void testReadingFile(String number, String isPrime) {
		try {
			System.out.println("Number " + number + ", is Prime? (" + isPrime + ")");
			boolean actualResult = isPrime(Integer.parseInt(number));
			Assert.assertEquals(actualResult, Boolean.parseBoolean(isPrime), "Number is not prime based on data set.");

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "textDataTyped")
	public void testReadingFileTyped(int number, boolean isPrime) {
		System.out.println("Number " + number + ", is Prime? (" + isPrime + ")");
		boolean actualResult = isPrime(number);
		Assert.assertEquals(actualResult, isPrime, "Number is not prime based on data set.");
	}

	@Test(dataProvider = "textDataTypedWithString")
	public void testReadingFileTypedWithString(String title, int number, boolean isPrime) {
		System.out.println("Number " + number + ", is Prime? (" + isPrime + ")");
		boolean actualResult = isPrime(number);
		Assert.assertEquals(actualResult, isPrime, "Number is not prime based on data set.");
	}

	private boolean isPrime(int number) {
		boolean isPrime = true;
		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	}
}
