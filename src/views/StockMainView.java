package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class StockMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JMenuItem mnýtmStokList;
	public JMenuItem mnýtmStokIslemleri;
	private JDesktopPane desktopPane;

	public StockMainView() {
		Init();
		this.setVisible(true);
	}

	private void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 527);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnStok = new JMenu("Stok");
		menuBar.add(mnStok);

		mnýtmStokList = new JMenuItem("Stok Listesi");
		mnStok.add(mnýtmStokList);

		mnýtmStokIslemleri = new JMenuItem("Stok Ýþlemleri");
		mnStok.add(mnýtmStokIslemleri);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaptionBorder);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public void createFrame(JInternalFrame frame) {
		if (frame.isVisible() == false) {
			frame.setVisible(true);
			desktopPane.add(frame);
			try {
				frame.setSelected(true);
			} catch (java.beans.PropertyVetoException e) {
				System.out.println(e);
			}
			setContentPane(desktopPane);
			desktopPane.putClientProperty("JDesktopPane.dragMode", "outline");
		} else {
			return;
		}
	}
}
