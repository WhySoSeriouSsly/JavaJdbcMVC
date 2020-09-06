package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import business.Validator;
import dataaccess.DbUtil;
import models.StockModel;

public class StockSaveController {
	@SuppressWarnings("unused")
	private Connection connection;
	private PreparedStatement pstmt = null;
	private StockListController listController;
	public StockSaveController() {
		connection = DbUtil.connection;
		listController=new StockListController();
	}
	public void insertStock(StockModel stock) {
		if(Validator.validate(stock)==false) {
			JOptionPane.showMessageDialog(null, "Alanlarý Doðru Giriniz.");
			return;
		}
		if(Validator.sizeValidation(stock)==false) {
			JOptionPane.showMessageDialog(null, "Alanlarýn Uzunluðuna Dikkat Ediniz.");
			return;
		}
		try {
			pstmt = connection.prepareStatement(
					"insert into stocks (StokCode,StockName,StockType,Unit,Barcode,VatType,Description,DateOfCreation) values(?,?,?,?,?,?,?,?)");
			String date=DateTimeNow();
			pstmt.setString(1, stock.getStokCode());
			pstmt.setString(2, stock.getStockName());
			pstmt.setInt(3, stock.getStockType());// ++
			pstmt.setString(4, stock.getUnit());
			pstmt.setString(5, stock.getBarcode());
			pstmt.setDouble(6, stock.getVatType());
			pstmt.setString(7, stock.getDescription());
			pstmt.setString(8, date);
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				pstmt.close();
				//connection.close();
			} catch (SQLException ex) {
			}
		}
		JOptionPane.showMessageDialog(null, "Stock Added");
	}
	public void updateStock(StockModel stock) {
		if(Validator.validate(stock)==false) {
			JOptionPane.showMessageDialog(null, "Alanlarý Doðru Giriniz.");
			return;
		}
		if(Validator.sizeValidation(stock)==false) {
			JOptionPane.showMessageDialog(null, "Alanlarýn Uzunluðuna Dikkat Ediniz.");
			return;
		}
		try {
			String sql = "update stocks set StockName=?,StockType=?,Unit=?,Barcode=?,VatType=?,Description=?,DateOfCreation=? where StokCode = ?";
			pstmt = connection.prepareStatement(sql);
			String date=DateTimeNow();
			pstmt.setString(1, stock.getStockName());
			pstmt.setInt(2, stock.getStockType());
			pstmt.setString(3, stock.getUnit());
			pstmt.setString(4, stock.getBarcode());
			pstmt.setDouble(5, stock.getVatType());
			pstmt.setString(6, stock.getDescription());
			pstmt.setString(7, date);
			pstmt.setString(8, stock.getStokCode());
			pstmt.executeUpdate();
		}
		
		catch (SQLException exception) {
			//Message(Messages.ProductNotUpdated);
			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		}
		JOptionPane.showMessageDialog(null, "Stock Updated");
	}
	
	public void copyStock(String oldStockCode,String newStockCode) {
		StockModel stock=listController.getRecordByStock(oldStockCode);
		if(listController.getRecordByStock(newStockCode)==null) {
			stock.setStokCode(newStockCode);
			insertStock(stock);
			return;
		}
		else {
			JOptionPane.showMessageDialog(null, "Bu stok zaten var");
			return;
		}
	}
	private String DateTimeNow() {
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		strDate = dateFormat.format(date);
		return strDate.toString();
	}
	
}
