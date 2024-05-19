package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import dao.KhachHangDAO;
import utils.Utils;

public class Form_DanhSachKhachHang extends JDialog implements ActionListener{
	private JTable tbl;
	private DefaultTableModel tableModel;
	private Button btnChon;
	private Button btnHuy;
	private Form_Mail fm;
	private KhachHangDAO KhachHang_dao;

	public Form_DanhSachKhachHang(JPanel parent) throws MalformedURLException, RemoteException, NotBoundException {
		setSize(950, 650);
		setTitle("Thêm kích thước");
		setLocationRelativeTo(null);
		setLayout(null);
		fm = (Form_Mail)parent;
		KhachHang_dao = (KhachHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khachHangDao");
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(50, 50, 850, 450);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		scr.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scp = new ScrollBarCustom();
//		Set color scrollbar thumb
		scp.setScrollbarColor(new Color(203, 203, 203));
		scr.setVerticalScrollBar(scp);
		this.add(scr);

		tbl = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			/**
			 * Set màu từng dòng cho Table
			 */
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (isRowSelected(row))
					c.setBackground(Utils.getRGBA(96, 96, 96, 0.5f));
				else
					c.setBackground(Color.WHITE);
				return c;
			}
		};
		tbl.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tableModel = new DefaultTableModel(new String[] { "Tên khách hàng", "Số điện thoại", "Email", "Mã hóa đơn","Ngày lập"}, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tableColumnModel.getColumn(0).setPreferredWidth(200);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(200);
		tableColumnModel.getColumn(3).setPreferredWidth(150);
		tableColumnModel.getColumn(4).setPreferredWidth(150);

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
		btnChon = new Button("Chọn");
		btnChon.setIcon(Utils.getImageIcon("check-mark.png"));
		btnChon.setRadius(8);
		btnChon.setForeground(Color.WHITE);
		btnChon.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnChon.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnChon.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnChon.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnChon.setBorderColor(new Color(203, 239, 255));
		btnChon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnChon.setBounds(300, 500, 150, 40);
		add(btnChon);
		
		btnHuy = new Button("Hủy");
		btnHuy.setIcon(Utils.getImageIcon("close_16x16.png"));
		btnHuy.setRadius(8);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnHuy.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnHuy.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnHuy.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnHuy.setBorderColor(new Color(203, 239, 255));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(550, 500, 150, 40);
		add(btnHuy);
		getDuLieu();
		btnChon.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnChon)) {
			int row = tbl.getSelectedRow();
			if(tbl.getValueAt(row, 2).toString().equals(""))
				JOptionPane.showMessageDialog(this, "Khách hành này chưa có email");
			else {
				fm.txtTo.setText(tbl.getValueAt(row, 2).toString());
				setVisible(false);
			}	
		}
		else if(src.equals(btnHuy)) {
			setVisible(false);
		}
	}
	public void getDuLieu() throws RemoteException {
		List<Object[]> duLieu = KhachHang_dao.loadDuLieuKhachHang();
		for (Object[] objects : duLieu) {
			tableModel.addRow(objects);
		}
	}
}
