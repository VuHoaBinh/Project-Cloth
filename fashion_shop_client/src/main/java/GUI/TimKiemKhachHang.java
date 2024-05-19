package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
import components.controlPanel.ControlPanel;
import components.notification.Notification;
import components.notification.Notification.Type;
import components.radio.RadioButtonCustom;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.KhachHangDAO;
import entities.KhachHang;
import utils.Utils;

public class TimKiemKhachHang extends JPanel implements ActionListener{
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1200;
	private JLabel lblTieuDe;
	private TextField txtMa;
	private TextField txtTen;
	private RadioButtonCustom radNam, radNu;
	private TextField txtAddress;
	private TextField txtEmail;
	private TextField txtPhone;
	private JLabel lblTable;
	private Button btnSearch;
	private Button btnLamMoi;
	private KhachHangDAO KhachHang_dao;
	private ArrayList<String> token;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public TimKiemKhachHang(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;

		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(1200);
		KhachHang_dao = (KhachHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khachHangDao");
		token = new ArrayList<String>();
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
		setLayout(null);
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, 1200, 90);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		lblTieuDe = new JLabel();
		lblTieuDe.setText("Thông tin khách hàng");
		lblTieuDe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblTieuDe.setBackground(new Color(203, 239, 255));
		lblTieuDe.setBounds(0, 30, 371, 55);
		pnlContainer.add(lblTieuDe);
		
		lblTable = new JLabel();
		lblTable.setText("Danh sách khách hàng");
		lblTable.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblTable.setBackground(new Color(203, 239, 255));
		lblTable.setBounds(750, 30, 371, 55);
		pnlContainer.add(lblTable);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã khách hàng:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtTen = new TextField();
		txtTen.setLabelText("Tên khách hàng:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtTen);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow3);
		pnlRow3.setLayout(null);
		
		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(0, 0, 371, 55);
		pnlRow3.add(pnlGioiTinh);
		pnlGioiTinh.setLayout(null);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(2, 0, 371, 19);
		pnlGioiTinh.add(lblGioiTinh);

		JPanel pnlGroupGioiTinh = new JPanel();
		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
		pnlGroupGioiTinh.setBounds(2, 30, 138, 16);
		pnlGioiTinh.add(pnlGroupGioiTinh);
		pnlGroupGioiTinh.setLayout(null);

		radNam = new RadioButtonCustom("Nam");
		radNam.setFocusable(false);
		radNam.setBackground(Utils.secondaryColor);
		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNam.setBounds(0, -2, 59, 21);

		pnlGroupGioiTinh.add(radNam);

		radNu = new RadioButtonCustom("Nữ");
		radNu.setFocusable(false);
		radNu.setBackground(Utils.secondaryColor);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNu.setBounds(79, -2, 59, 21);
		pnlGroupGioiTinh.add(radNu);
		radNam.setSelected(true);

		ButtonGroup buttonGroupGioiTinh = new ButtonGroup();
		buttonGroupGioiTinh.add(radNam);
		buttonGroupGioiTinh.add(radNu);
		
		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow4);
		pnlRow4.setLayout(null);
		
		txtAddress = new TextField();
		txtAddress.setLabelText("Địa chỉ:");
		txtAddress.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtAddress.setBackground(new Color(203, 239, 255));
		txtAddress.setBounds(0, 0, 371, 55);
		pnlRow4.add(txtAddress);
		
		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow5);
		pnlRow5.setLayout(null);
		
		txtPhone = new TextField();
		txtPhone.setLabelText("Số điện thoại:");
		txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPhone.setBackground(new Color(203, 239, 255));
		txtPhone.setBounds(0, 0, 371, 55);
		pnlRow5.add(txtPhone);
		
		JPanel pnlRow6 = new JPanel();
		pnlRow6.setBackground(Utils.secondaryColor);
		pnlRow6.setBounds(left, top, 400, 55);
		top += padding + 25;
		add(pnlRow6);
		pnlRow6.setLayout(null);
		
		txtEmail = new TextField();
		txtEmail.setLabelText("Email:");
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtEmail.setBackground(new Color(203, 239, 255));
		txtEmail.setBounds(0, 0, 371, 55);
		pnlRow6.add(txtEmail);
		
		// table
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left + 450, 90, 880, 370);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
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
		tbl.setAutoCreateRowSorter(true);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tableModel = new DefaultTableModel(new String[] { "Mã KH", "Tên khách hàng", "Giới tính", "Địa chỉ","Số điện thoại", "Email" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(180);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(150);
		tableColumnModel.getColumn(5).setPreferredWidth(150);
		tbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tbl.getSelectedRow();
				txtMa.setText(tbl.getValueAt(row, 0).toString());
				txtTen.setText(tbl.getValueAt(row, 1).toString());
				String gioiTinh = tbl.getValueAt(row, 2).toString();
				if(gioiTinh.equals("nam")) {
					radNam.setSelected(true);
					radNu.setSelected(false);
				}
				else {
					radNam.setSelected(false);
					radNu.setSelected(true);
				}
				if(tbl.getValueAt(row, 3) == null)
					txtAddress.setText("");
				else
					txtAddress.setText(tbl.getValueAt(row, 3).toString());
				txtPhone.setText(tbl.getValueAt(row, 4).toString());
				if(tbl.getValueAt(row, 5) == null)
					txtEmail.setText("");
				else
					txtEmail.setText(tbl.getValueAt(row, 5).toString());
			}
		});
		
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
//		pnlControl = new ControlPanel(900,470 , main);
//		this.add(pnlControl);
		// end table
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top + 50, 992, 50);
//		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnSearch = new Button("Tìm");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(Utils.getImageIcon("searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setColor(new Color(134, 229, 138));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(904, -2, 150, 40);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setColorOver(new Color(134, 229, 138));
		btnSearch.setColorClick(new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setBounds(350, 0, 150, 40);

		pnlActions.add(btnSearch);
		
		
		btnLamMoi = new Button("Làm mới");
		btnLamMoi.setIcon(Utils.getImageIcon("refresh.png"));
		btnLamMoi.setRadius(4);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnLamMoi.setColorOver(Utils.primaryColor);
		btnLamMoi.setColorClick(new Color(161, 184, 186));
		btnLamMoi.setColor(Utils.primaryColor);
		btnLamMoi.setBorderColor(Utils.secondaryColor);
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setBounds(550, -2, 150, 40);
		pnlActions.add(btnLamMoi);
		
		btnSearch.addActionListener(this);
		btnLamMoi.addActionListener(this);
		
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			Object[] data = {kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh()==true?"nam":"nữ",
					kh.getDiaChi(), kh.getSoDienThoai(), kh.getEmail()};
			tableModel.addRow(data);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnSearch)) {
				String sql = ChecktaoSQL();
				tableModel.setRowCount(0);
				try {
					for (KhachHang kh : KhachHang_dao.Find(sql,token)) {
						Object[] data = {kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh()==true?"nam":"nữ",
								kh.getDiaChi(), kh.getSoDienThoai(), kh.getEmail()};
						tableModel.addRow(data);
					}
					token.clear();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
//			JOptionPane.showMessageDialog(this, sql);
		}
		else if(src.equals(btnLamMoi))
			try {
				refresh();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	public void refresh() throws RemoteException {
		txtMa.setText("");
		txtTen.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		tableModel.setRowCount(0);
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			Object[] data = {kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh()==true?"nam":"nữ",
					kh.getDiaChi(), kh.getSoDienThoai(), kh.getEmail()};
			tableModel.addRow(data);
		}
	}
	public String ChecktaoSQL() {
		String sql = "select * from KhachHang where";
		if(!txtMa.getText().isEmpty()) {
			token.add(txtMa.getText());
			sql +=" maKhachHang = :a" + (token.size()-1)+"";
		}
		if(!txtTen.getText().isEmpty()) {
			token.add(txtTen.getText());
			if(sql.contains("maKhachHang"))
				sql += " and tenKhachHang = :a" + (token.size()-1)+"";
			else
				sql +=" tenKhachHang = :a" + (token.size()-1)+"";
		}
		if(!txtPhone.getText().isEmpty()) {
			token.add(txtPhone.getText());
			if(sql.contains("and") || sql.contains("maKhachHang") || sql.contains("tenKhachHang"))
				sql += " and soDienThoai = :a" + (token.size()-1)+"";
			else
				sql += " soDienThoai = :a" + (token.size()-1)+"";
		}
		if(!txtAddress.getText().isEmpty()) {
			token.add(txtAddress.getText());
			if(sql.contains("and") || sql.contains("maKhachHang") || sql.contains("tenKhachHang")
					|| sql.contains("soDienThoai"))
				sql += " and diaChi = :a" + (token.size()-1)+"";
			else
				sql += " diaChi = :a" + (token.size()-1)+"";
		}
		if(!txtEmail.getText().isEmpty()) {
			token.add(txtEmail.getText());
			if(sql.contains("and") || sql.contains("maKhachHang") || sql.contains("tenKhachHang")
					|| sql.contains("soDienThoai") || sql.contains("diaChi"))
				sql += " and email = :a" + (token.size()-1)+"";
			else
				sql += " email = :a" + (token.size()-1)+"";
		}
		if(radNam.isSelected() && !radNu.isSelected()) {
			token.add("1");
			if(sql.contains("and") || sql.contains("maKhachHang") || sql.contains("tenKhachHang")
					|| sql.contains("soDienThoai")|| sql.contains("diaChi")|| sql.contains("email"))
				sql += " and gioiTinh = :a"+(token.size()-1)+"";
			else
				sql += " gioiTinh = :a"+(token.size()-1)+"";
		}
		if(!radNam.isSelected() && radNu.isSelected()) {
			token.add("0");
			if(sql.contains("and") || sql.contains("maKhachHang") || sql.contains("tenKhachHang")
					|| sql.contains("soDienThoai")|| sql.contains("diaChi")|| sql.contains("email"))
				sql += " and gioiTinh = :a"+(token.size()-1)+"";
			else
				sql += " gioiTinh = :a"+(token.size()-1)+"";
		}
		return sql;
	}
//	private boolean showThongBaoLoi(TextField txt, String message) {
//		new Notification(main, Type.ERROR, message).showNotification();
////		txt.setError(true);
//		txt.selectAll();
//		txt.requestFocus();
//		return false;
//	}
//
//	private boolean validator() {
//		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
//		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";
////		String ma = txtMa.getText().trim();
//
////		if (ma.length() <= 0)
////			return showThongBaoLoi(txtMa, "Vui lòng nhập mã khách hàng");
//
////		if (!Pattern.matches("^SP(0[1-9]|[1-9][0-9])$", ma))
////			return showThongBaoLoi(txtMa, "Mã khách hàng phải theo định dạng KHXX với XX là 2 chữ số");
//
//		String ten = txtTen.getText().trim();
//
//		if (ten.length() <= 0)
//			return showThongBaoLoi(txtTen, "Vui lòng nhập tên khách hàng");
//
//		if (!ten.matches("^[a-zA-ZÀ-ỹ ]*$"))
//			return showThongBaoLoi(txtTen, "tên chỉ chứa các ký tự chữ cái");
//
//		String diaChi = txtAddress.getText().trim();
//
//		if (diaChi.length() <= 0)
//			return showThongBaoLoi(txtAddress, "Vui lòng nhập địa chỉ");
//		if (!diaChi.matches("^[0-9a-zA-ZÀ-ỹ \\,-]+$"))
//			return showThongBaoLoi(txtAddress, "địa chỉ chỉ chứa các ký tự chữ cái");
//
//		String soDienThoai = txtPhone.getText().trim();
//
//		if (soDienThoai.length() <= 0)
//			return showThongBaoLoi(txtPhone, "Vui lòng nhập số điện thoại");
//		if(!soDienThoai.matches("^[0-9]{10}$"))
//			return showThongBaoLoi(txtPhone, "Số điện thoại gồm 10 ký tự số");
//
//		
//		String email = txtEmail.getText().trim();
//		
//		if (email.length() > 0)
//			if(!email.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*/$"))
//				return showThongBaoLoi(txtEmail, "Email không hợp lệ");
//		
//
//		return true;
//	}
}
