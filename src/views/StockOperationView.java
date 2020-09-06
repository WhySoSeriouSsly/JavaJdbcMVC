package views;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.LayoutStyle.ComponentPlacement;

import models.StockModel;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class StockOperationView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtStokKodu;

	private JTextField txtStokAdi;
	private JTextField txtStokBarkodu;
	private JComboBox cbxKdvTipi;
	private JComboBox cbxStokBirimi;
	private JComboBox cbxStokTipi;
	private java.util.Date date;
	private JTextArea txAaciklama;
	private JFormattedTextField ftxOlusturulmaTarihi;
	public JButton btnSave;
	public JButton btnDelete;
	public JButton btnGet;

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;

	public StockOperationView() {
		super("Document #" + (++openFrameCount), true, true, true, true);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		setTitle("Stok \u0130\u015Flemleri");
		Init();
	}

	public void setData(StockModel stok) {
		txtStokKodu.setText(stok.getStokCode());
		txtStokAdi.setText(stok.getStockName());
		txtStokBarkodu.setText(stok.getBarcode());
		cbxStokTipi.setSelectedIndex(stok.getStockType());
		cbxStokBirimi.setSelectedItem(stok.getUnit());
		txtStokBarkodu.setText(stok.getBarcode());
		cbxKdvTipi.setSelectedItem(String.valueOf(stok.getVatType()));
		txAaciklama.setText(stok.getDescription());
		ftxOlusturulmaTarihi.setText(stok.getDateOfCreationFor());
	}
	public String getTxtStokKodu() {
		if(txtStokKodu.getText().trim().isEmpty()) {
			return "";
		}
		return txtStokKodu.getText().toString();
	}

	private void SetFieldEmpty() {
		cbxKdvTipi.setSelectedIndex(0);
		cbxStokBirimi.setSelectedIndex(0);
		cbxStokTipi.setSelectedIndex(0);
		txtStokKodu.setText("");
		txtStokAdi.setText("");
		txtStokBarkodu.setText("");
		txAaciklama.setText("");
		ftxOlusturulmaTarihi.setText(dateStr());
	}
	@SuppressWarnings("unused")
	public StockModel getData() {
		Double kdv;
		int tip;
		String birim;
		Date now = new java.util.Date();
		if (cbxKdvTipi.getSelectedIndex()==0) {
			kdv = 0.0;
		} else {
			kdv = Double.parseDouble(cbxKdvTipi.getSelectedItem().toString());
		}
		if(cbxStokTipi.getSelectedIndex()==0) {
			tip=0;
		}
		else {
			tip=cbxStokTipi.getSelectedIndex();
		}
		if(cbxStokBirimi.getSelectedIndex()==0) {
			birim="";
		}
		else {
			birim=cbxStokBirimi.getSelectedItem().toString();
		}
		return new StockModel(txtStokKodu.getText(), txtStokAdi.getText(), tip,
				birim, txtStokBarkodu.getText(), kdv,
				txAaciklama.getText().toString(), now);
	}

	private Date DateTimeNow() {

		java.util.Date now = new java.util.Date();
		return now;
	}
	private String dateStr() {

		Date strDate=DateTimeNow();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(strDate);
		dateStr=dateFormat.format(strDate);
		return dateStr;
	}

	private void Init() {

		setBounds(100, 100, 397, 551);

		JLabel lblStokIslemleri = new JLabel("Stok Ýþlemleri");
		lblStokIslemleri.setFont(new Font("Tahoma", Font.BOLD, 23));

		JLabel lblStokKodu = new JLabel("Stok Kodu");

		JLabel lblStokAdi = new JLabel("Stok Ad\u0131");

		JLabel lblNewLabel = new JLabel("Stok Tipi");

		JLabel lblNewLabel_1_1 = new JLabel("Barkod");

		JLabel lblNewLabel_1_2 = new JLabel("KDV Tipi");

		JLabel lblNewLabel_1_3 = new JLabel("A\u00E7\u0131klama");

		JLabel lblNewLabel_1_3_1 = new JLabel("Olusturulma Tarihi");

		JLabel lblStokBirimi = new JLabel("Stok Birimi");

		txtStokKodu = new JTextField();
		txtStokKodu.setColumns(10);

		txtStokAdi = new JTextField();
		txtStokAdi.setColumns(10);

		cbxStokTipi = new JComboBox();
		cbxStokTipi.setModel(new DefaultComboBoxModel(new String[] {"Se\u00E7iniz", "1.Nolu Stok", "2.Nolu Stok", "3.Nolu Stok", "4.Nolu Stok", "5.Nolu Stok", "6.Nolu Stok", "7.Nolu Stok", "8.Nolu Stok", "9.Nolu Stok", "10.Nolu Stok", "11.Nolu Stok", "12.Nolu Stok", "13.Nolu Stok", "14.Nolu Stok"}));

		cbxStokBirimi = new JComboBox();
		cbxStokBirimi.setModel(new DefaultComboBoxModel(new String[] {"Se\u00E7iniz", "Sebze", "Meyve", "Elektronik", "Beyaz E\u015Fya", "Kisisel", "Makyaj", "Bilgisayar", "Telefon", "Ev", "Tamir", "Bitki"}));

		txtStokBarkodu = new JTextField();
		txtStokBarkodu.setColumns(10);

		cbxKdvTipi = new JComboBox();
		cbxKdvTipi.setModel(new DefaultComboBoxModel(new String[] {"Seciniz", "3.0", "8.0", "13.0", "18.0", "21.0", "24.0", "30.0", "33.0", "41.0"}));

		txAaciklama = new JTextArea();
		String defaultDate=dateStr();
		ftxOlusturulmaTarihi = new JFormattedTextField();
		ftxOlusturulmaTarihi.setEnabled(false);
		ftxOlusturulmaTarihi.setText(defaultDate);
		btnSave = new JButton("Kaydet");

		btnDelete = new JButton("Sil");

		btnGet = new JButton("Getir");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(101).addComponent(lblStokIslemleri))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(
								groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_1_3)
										.addComponent(lblNewLabel_1_3_1).addComponent(lblNewLabel_1_2)
										.addComponent(lblNewLabel_1_1).addComponent(lblNewLabel).addComponent(
												lblStokAdi)
										.addComponent(lblStokKodu).addComponent(lblStokBirimi))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(ftxOlusturulmaTarihi, GroupLayout.PREFERRED_SIZE, 172,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txAaciklama, GroupLayout.PREFERRED_SIZE, 169,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cbxKdvTipi, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStokBarkodu, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(txtStokAdi, GroupLayout.DEFAULT_SIZE, 167,
																Short.MAX_VALUE)
														.addComponent(txtStokKodu, GroupLayout.DEFAULT_SIZE, 167,
																Short.MAX_VALUE)
														.addComponent(cbxStokTipi, 0, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
												.addGap(10).addComponent(btnGet))
										.addComponent(cbxStokBirimi, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(78, Short.MAX_VALUE)
						.addComponent(btnSave).addGap(63).addComponent(btnDelete).addGap(93)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(36).addComponent(lblStokIslemleri).addGap(41)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblStokKodu)
						.addComponent(txtStokKodu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGet))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblStokAdi).addComponent(
						txtStokAdi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						cbxStokTipi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lblStokBirimi).addComponent(cbxStokBirimi,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addComponent(lblNewLabel_1_1).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1_2)
								.addComponent(cbxKdvTipi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1_3)
								.addComponent(txAaciklama, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1_3_1)
								.addComponent(ftxOlusturulmaTarihi, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtStokBarkodu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnSave).addComponent(btnDelete))
				.addGap(47)));
		getContentPane().setLayout(groupLayout);
	}
}
