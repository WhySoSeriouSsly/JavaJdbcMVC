package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dataaccess.DbUtil;
import views.CopyView;
import views.LoginView;
import views.StockListView;
import views.StockMainView;
import views.StockOperationView;

public class StockMainController implements ActionListener, MouseListener {
	private StockMainView stockMainView;
	private DbUtil dbUtil;
	private StockDeleteController deleteController;
	private StockListController listController;
	private StockSaveController saveController;
	private StockListView listView;
	private StockOperationView operationView;
	private LoginView loginView;
	private LoginController loginController;
	private CopyView copyView;

	public StockMainController() throws SQLException {
		dbUtil = new DbUtil();
		dbUtil.baglanti();
		stockMainView = new StockMainView();
		deleteController = new StockDeleteController();
		listController = new StockListController();
		saveController = new StockSaveController();
		loginController = new LoginController();
		loginView = new LoginView();
		listView = new StockListView();
		operationView = new StockOperationView();
		copyView = new CopyView();
		btnActionListener();
		refreshTable();
	}

	private void refreshTable() {
		listView.populateTable(listController.getAllStocks(), listController.getAllHours());
		return;
	}

	public void btnActionListener() {
		loginView.btnLogin.addActionListener(this);
		stockMainView.mnýtmStokList.addActionListener(this);
		stockMainView.mnýtmStokIslemleri.addActionListener(this);
		listView.btnSearch.addActionListener(this);
		operationView.btnGet.addActionListener(this);
		operationView.btnSave.addActionListener(this);
		operationView.btnDelete.addActionListener(this);
		listView.tblStocks.addMouseListener(this);
		listView.mntmDelete.addActionListener(this);
		listView.mntmCopy.addActionListener(this);
		copyView.btnCopyButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginView.btnLogin) {
			if (loginController.login(loginView.getData()) == true) {
				stockMainView.setVisible(true);
				loginView.dispose();
			}
			return;
		} else if (e.getSource() == stockMainView.mnýtmStokList) {
			stockMainView.createFrame(listView);
		} else if (e.getSource() == stockMainView.mnýtmStokIslemleri) {
			stockMainView.createFrame(operationView);
		} else if (e.getSource() == operationView.btnSave) {// ÝNSERT delete
			if (listController.getRecordByStock(operationView.getTxtStokKodu()) == null) {
				saveController.insertStock(operationView.getData());
				refreshTable();
				return;
			}
			saveController.updateStock(operationView.getData());
			refreshTable();
			return;
		} else if (e.getSource() == operationView.btnDelete) {
			deleteController.deleteRecord(operationView.getTxtStokKodu());
			refreshTable();
			return;
		} else if (e.getSource() == listView.btnSearch) {// search
			listView.populateTable(listController.allSearchStock(listView.getData()),
					listController.allSearchHour(listView.getData()));
		} else if (e.getSource() == operationView.btnGet) {
			if (listController.validStock(operationView.getTxtStokKodu()) == null) {
				return;
			}
			operationView.setData(listController.getRecordByStock(operationView.getTxtStokKodu()));
		} else if (e.getSource() == listView.mntmDelete) {
			System.out.println(listView.selectItem());
			deleteController.deleteRecord(listView.selectItem());
		} else if (e.getSource() == listView.mntmCopy) {
			stockMainView.createFrame(copyView);
			return;
		}
		else if(e.getSource()==copyView.btnCopyButton) {
			saveController.copyStock(listView.selectItem(),copyView.getData());
			refreshTable();
			copyView.dispose();
			return;
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == java.awt.event.MouseEvent.BUTTON3) {
			listView.popupMenu.show(listView.tblStocks, arg0.getX(), arg0.getY());
			return;
		}

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
