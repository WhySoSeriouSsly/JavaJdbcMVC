package tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.StockModel;

public class Test {
	public static void main(String[] args) throws ParseException {

		java.util.Date date = new java.util.Date();

		StockModel stock = new StockModel("", "", 0, "", "", 0.0, "", date);

//		if(stock.getStokCode().trim().isEmpty()==true) {
//			
//			System.out.println("Validation Exception");
//			return;
//		}
		String now = dateStr();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=format.parse(now);
		System.out.println(date2);

	}

	public static String DateTimeNow(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		strDate = dateFormat.format(date);
		return strDate.toString();
	}

	private  static Date DateTimeNow2() {

		java.util.Date now = new java.util.Date();
		return now;
	}

	private static String dateStr() {

		Date strDate = DateTimeNow2();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(strDate);
		dateStr = dateFormat.format(strDate);
		return dateStr;
	}

}
