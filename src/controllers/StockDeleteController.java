package controllers;

import java.sql.*;

import javax.swing.JOptionPane;

import dataaccess.DbUtil;

public class StockDeleteController {
	@SuppressWarnings("unused")
	private Connection connection;
	private PreparedStatement pstmt = null;
	private StockListController listController;
	
	public StockDeleteController() {
		connection = DbUtil.connection;
		listController=new StockListController();
	}

	public void deleteRecord(String stockCode) {
		
		if(listController.getRecordByStock(stockCode)==null) {
			JOptionPane.showMessageDialog(null, "Böyle Bir Stock Yok");
			return;
		}
		try {
			String sql = "delete from stocks where StokCode = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, stockCode);
			pstmt.executeUpdate();
		} catch (SQLException exception) {

			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				pstmt.close();

			} catch (SQLException ex) {
				DbUtil.showErrorMessage(ex);
			}
		}
		JOptionPane.showMessageDialog(null, "Stock Deleted");
	}
}
