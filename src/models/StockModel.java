package models;

import java.sql.Time;
import java.util.Date;

public class StockModel {
	
		private String StokCode;
		private String StockName;
		private int StockType;
		private String Unit;
		private String Barcode;
		private double VatType;
		private String Description;
		private Date DateOfCreation;

		public String getStokCode() {
			return StokCode;
		}

		public void setStokCode(String stokCode) {
			StokCode = stokCode;
		}

		public String getStockName() {
			return StockName;
		}

		public void setStockName(String stockName) {
			StockName = stockName;
		}

		public int getStockType() {
			return StockType;
		}

		public void setStockType(int stockType) {
			StockType = stockType;
		}

		public String getUnit() {
			return Unit;
		}

		public void setUnit(String unit) {
			Unit = unit;
		}

		public String getBarcode() {
			return Barcode;
		}

		public void setBarcode(String barcode) {
			Barcode = barcode;
		}

		public double getVatType() {
			return VatType;
		}

		public void setVatType(double vatType) {
			VatType = vatType;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}

		public Date getDateOfCreation() {

			return DateOfCreation;
		}

		public String getDateOfCreationFor() {

			return DateOfCreation.toString();
		}

		public void setDateOfCreation(Date value) {
			DateOfCreation = value;
		}
		public String stockModel() {
			return getStokCode().toString()+" "+getStockName().toString()+" "+
					String.valueOf(getStockType())+" "+getUnit().toString()+" "+
					getBarcode().toString()+" "+String.valueOf(getVatType())+" "+
					getDescription().toString()+" "+getDateOfCreation().toString();
		}
		public StockModel(String StokCode, String StockName, int StockType, String Unit, String Barcode, double VatType,
				String Description, Date date, Time localTime) {
			this.StokCode = StokCode;
			this.StockName = StockName;
			this.StockType = StockType;
			this.Unit = Unit;
			this.Barcode = Barcode;
			this.VatType = VatType;
			this.Description = Description;
			this.DateOfCreation = date;
			this.DateOfCreation = localTime;
		}

		public StockModel(String StokCode, String StockName, int StockType, String Unit, String Barcode, double VatType,
				String Description, Date date) {
			this.StokCode = StokCode;
			this.StockName = StockName;
			this.StockType = StockType;
			this.Unit = Unit;
			this.Barcode = Barcode;
			this.VatType = VatType;
			this.Description = Description;
			this.DateOfCreation = date;
		}
		public StockModel() {
			
		}

	}
