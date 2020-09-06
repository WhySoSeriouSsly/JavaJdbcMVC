package views;

import javax.swing.JInternalFrame;

import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.StockModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class StockListView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	public JTable tblStocks;
	private JTextField txtSearchStock;
	private DefaultTableModel model;
	public JButton btnSearch;

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	public JPopupMenu popupMenu;
	public JMenuItem mntmDelete;
	public JMenuItem mntmCopy;
	private JMenuItem mnýtmNewMenuItem_2;

	public StockListView() {
		super("Document #" + (++openFrameCount), true, true, true, true);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		Init();
	}

	public void populateTable(ArrayList<StockModel> stockss, ArrayList<String> hours1) {

		model = (DefaultTableModel) tblStocks.getModel();
		model.setRowCount(0);
		ArrayList<StockModel> stocks = stockss;
		ArrayList<String> hours = hours1;
		int i = 0;
		for (StockModel stock : stocks) {
			Object[] row = { stock.getStokCode(), stock.getStockName(), stock.getStockType(), stock.getUnit(),
					stock.getBarcode(), stock.getVatType(), stock.getDescription(), stock.getDateOfCreation(),
					hours.get(i) };
			i++;
			model.addRow(row);
		}
		tblStocks.setModel(model);
	}

	public String getData() {
		return txtSearchStock.getText().toString();
	}
	public String selectItem() {
		int row = tblStocks.getSelectedRow();
		if (row == -1) {
			if (tblStocks.getRowCount() == 0) {
				return null;
			} else {
				return null;
			}
		}
		String codeValue = tblStocks.getModel().getValueAt(row, 0).toString();
		return codeValue;
	}

	private void Init() {

		setBounds(100, 100, 817, 494);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblSearchKey = new JLabel("Aranacak Kelime");

		txtSearchStock = new JTextField();
		txtSearchStock.setColumns(10);

		btnSearch = new JButton("Ara");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblSearchKey).addGap(18)
										.addComponent(txtSearchStock, GroupLayout.PREFERRED_SIZE, 157,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(128).addComponent(btnSearch,
								GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(11, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblSearchKey).addComponent(
						txtSearchStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSearch)
				.addContainerGap(165, Short.MAX_VALUE)));

		tblStocks = new JTable();
		tblStocks.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Stok Kodu", "Stok Ad\u0131",
				"Stok Tipi", "Birimi", "Barkod", "KDV", "A\u00E7\u0131klama", "Tarih", "Saat" }));
		tblStocks.getColumnModel().getColumn(8).setResizable(false);
		
		popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		mntmDelete = new JMenuItem("Sil");
		popupMenu.add(mntmDelete);
		
		mntmCopy = new JMenuItem("Kopyala");
		popupMenu.add(mntmCopy);
		
		mnýtmNewMenuItem_2 = new JMenuItem("New menu item");
		popupMenu.add(mnýtmNewMenuItem_2);
		scrollPane.setViewportView(tblStocks);
		getContentPane().setLayout(groupLayout);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
