package models;

import java.util.Date;

public class StockRowModel {
	private String stokCode;
	private String stockName;
	private String barcode;
	private Date dateOfCreation;
	public StockRowModel() {
		
	}
	public StockRowModel(String stockCode,String stockName,String barcode,Date dateOfCreation) {
		this.stokCode=stockCode;
		this.stockName=stockName;
		this.barcode=barcode;
		this.dateOfCreation=dateOfCreation;	
	}

	public String getStokCode() {
		return stokCode;
	}
	public void setStokCode(String stokCode) {
		this.stokCode = stokCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	

}
