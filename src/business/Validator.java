package business;

import models.StockModel;

public class Validator {

	public static Boolean validate(StockModel stock) {

		if (stock.getStokCode().trim().isEmpty() || stock.getStockName().trim().isEmpty() || stock.getStockType() == 0
				|| stock.getUnit().trim().isEmpty() || stock.getBarcode().trim().isEmpty() || stock.getVatType() == 0.0
				|| stock.getDescription().trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static Boolean validate(String code) {
		if(code.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	public static Boolean sizeValidation(StockModel stock) {
		if(stock.getStokCode().length()>50||
				stock.getStockName().length()>100||
				stock.getUnit().length()>10||
				stock.getBarcode().length()>30||
				stock.getDescription().length()>200) {
			return false;
		}
		return true;
	}
}
