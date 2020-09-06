package controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JOptionPane;

import business.Validator;

import java.sql.*;

import dataaccess.DbUtil;
import models.StockModel;
import models.StockRowModel;

public class StockListController {
	private Connection conn;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ArrayList<StockModel> stocks;
	private ResultSet rs;
	private ArrayList<StockRowModel> stockRows = null;

	public StockListController() {
		conn = DbUtil.connection;
	}

	public ArrayList<StockModel> getAllStocks() {
		return searchStock(null, null);
	}

	public ArrayList<String> getAllHours() {
		return getSearchHours(null, null);
	}

	public StockModel getRecordByStock(String code) {

		StockModel stock = null;
		if (Validator.validate(code) == false) {
			JOptionPane.showMessageDialog(null, "Lütfen Geçerli Bir Stok Kodu Giriniz.");
			return stock;
		}
		try {
			pstmt = conn.prepareStatement("select * from stocks where StokCode = ?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				stock = new StockModel(rs.getString("StokCode"), rs.getString("StockName"), rs.getInt("StockType"),
						rs.getString("Unit"), rs.getString("Barcode"), rs.getDouble("VatType"),
						rs.getString("Description"), rs.getDate("DateOfCreation"));
			}
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return stock;
	}

	public Boolean validStock(String code) {
		StockModel stock = getRecordByStock(code);
		if (stock == null) {
			JOptionPane.showMessageDialog(null, "Böyle Bir stok yok");
			return null;
		}
		return true;
	}

	public ArrayList<String> getSearchHours(String searchKey, Object value) {

		ArrayList<String> hours = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			if (searchKey == null || value == null) {
				String sql = "SELECT *,CONVERT(DateOfCreation, TIME) as Saat from stocks";
				rs = stmt.executeQuery(sql);
			} else {
				hours.clear();
				String sql = "SELECT *,CONVERT(DateOfCreation, TIME) as Saat from stocks where " + searchKey
						+ " LIKE '%" + value + "%'";
				rs = stmt.executeQuery(sql);
			}
			while (rs.next()) {
				hours.add(rs.getTime("Saat").toString());
			}

		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return hours;

	}

	public ArrayList<StockModel> searchStock(String searchKey, Object value) {
		ArrayList<StockModel> searchList = new ArrayList<StockModel>();
		try {
			stmt = this.conn.createStatement();
			if (searchKey == null || value == null) {
				rs = stmt.executeQuery("select * from stocks;");
			} else {
				searchList.clear();
				rs = stmt.executeQuery("SELECT * FROM stocks WHERE " + searchKey + " LIKE '%" + value + "%'");
			}

			while (rs.next()) {
				searchList.add(new StockModel(rs.getString("StokCode"), rs.getString("StockName"),
						rs.getInt("StockType"), rs.getString("Unit"), rs.getString("Barcode"), rs.getDouble("VatType"),
						rs.getString("Description"), rs.getDate("DateOfCreation")));
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return searchList;
	}

	public ArrayList<String> allSearchHour(Object value) {
		if (getSearchHours("StockName", value).size() != 0) {
			return getSearchHours("StockName", value);
		} else if (getSearchHours("Barcode", value).size() != 0) {
			return getSearchHours("Barcode", value);
		} else if (getSearchHours("DateOfCreation", value).size() != 0) {
			return getSearchHours("DateOfCreation", value);
		} else if (getSearchHours("Unit", value).size() != 0) {
			return getSearchHours("Unit", value);
		} else {
			return getSearchHours("StockType", value);
		}
	}

	public ArrayList<StockModel> allSearchStock(Object value) {
		if (searchStock("StockName", value).size() != 0) {
			return searchStock("StockName", value);
		} else if (searchStock("Barcode", value).size() != 0) {
			return searchStock("Barcode", value);
		} else if (searchStock("DateOfCreation", value).size() != 0) {
			return searchStock("DateOfCreation", value);
		} else if (searchStock("Unit", value).size() != 0) {
			return searchStock("Unit", value);
		} else {
			return searchStock("StockType", value);
		}
	}
}
