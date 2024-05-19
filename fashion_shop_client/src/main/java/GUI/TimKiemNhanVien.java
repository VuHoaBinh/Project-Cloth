package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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

import com.raven.datechooser.DateChooser;
import com.toedter.calendar.JDateChooser;

import components.button.Button;
import components.comboBox.ComboBox;
import components.controlPanel.ControlPanel;
import components.radio.RadioButtonCustom;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import java.lang.Math;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;

import dao.CaLamDAO;
import dao.LoaiNhanVienDAO;
import dao.NhanVienDAO;
import entities.CaLam;
import entities.LoaiNhanVien;
import entities.NhanVien;
import utils.Utils;

public class TimKiemNhanVien extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtDiaChi;
	private TextField txtTen;
	private TextField txtSoDienThoai;
	private TextField txtNgaySinh;
	private ComboBox cboQuanLy;
	private TextField txtLoaiNhanVien;
	private TextField txtLuong;
	private ComboBox cboCaLam;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private ControlPanel pnlControl;
	private DateChooser dateChoose;
	private LoaiNhanVienDAO LoaiNhanVien_dao;
	private CaLamDAO CaLam_dao;
	private NhanVienDAO NhanVien_dao;
	private ArrayList<NhanVien> dsNV;
	private ComboBox cboLoaiNhanVien;
	private RadioButtonCustom radNam;
	private RadioButtonCustom radNu;
	private Button btnTimKiem;
	private Button btnRefresh;
	private ArrayList<LoaiNhanVien> dsLNV;
	private ArrayList<CaLam> dsCL;
	private ArrayList<NhanVien> dsQL;
	private NumberFormat format;
	private ArrayList<String> token;
	public TimKiemNhanVien(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		CaLam_dao = (CaLamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/caLamDao");
		NhanVien_dao = (NhanVienDAO) Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/nhanVienDao");
		LoaiNhanVien_dao = (LoaiNhanVienDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/loaiNhanVienDao");
		token = new ArrayList<String>();
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
		setLayout(null);
		format = NumberFormat.getInstance();
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, widthPnlContainer, 80);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.secondaryColor);
		pnlHeader.setBounds(16, 18, 1054, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblTitle = new JLabel("Thông tin nhân viên");
		lblTitle.setBounds(470, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding+5;
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		
		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã nhân viên:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);
		
		txtDiaChi= new TextField();
		txtDiaChi.setLabelText("Địa chỉ:");
		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChi.setBackground(new Color(203, 239, 255));
		txtDiaChi.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtDiaChi);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 55);
		top += padding+5;
		add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtTen = new TextField();
		txtTen.setLabelText("Tên nhân viên");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtTen);
		
		txtSoDienThoai = new TextField();
		txtSoDienThoai.setLabelText("Số điện thoại:");
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setBackground(new Color(203, 239, 255));
		txtSoDienThoai.setBounds(421, 0, 371, 55);
		pnlRow2.add(txtSoDienThoai);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 80);
		top += padding+5;
		add(pnlRow3);
		pnlRow3.setLayout(null);
		
		txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ngày sinh:");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(0, 0, 371, 55);
		pnlRow3.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgaySinh);
		txtNgaySinh.setText("");
		
		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(421, 0, 371, 55);
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
		radNam.setSelected(true);
		pnlGroupGioiTinh.add(radNam);

		radNu = new RadioButtonCustom("Nữ");
		radNu.setFocusable(false);
		radNu.setBackground(Utils.secondaryColor);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNu.setBounds(79, -2, 59, 21);
		pnlGroupGioiTinh.add(radNu);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(radNam);
		btnGroup.add(radNu);
		
		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 792, 80);
		top += padding+15;
		add(pnlRow4);
		pnlRow4.setLayout(null);
		
		JLabel lblQuanLy = new JLabel("Quản lý bởi:");
		lblQuanLy.setForeground(Utils.labelTextField);
		lblQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblQuanLy.setBounds(0, 0, 100, 65);
		pnlRow4.add(lblQuanLy);
		
		cboQuanLy = new ComboBox<>();
		cboQuanLy.setModel(new DefaultComboBoxModel<String>());
		cboQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboQuanLy.setBackground(new Color(140, 177, 180));
		cboQuanLy.setBounds(0, 46, 371, 36);
		cboQuanLy.addItem("");
		pnlRow4.add(cboQuanLy);
		
		JLabel lblLoaiNhanVien = new JLabel("Loại nhân viên:");
		lblLoaiNhanVien.setForeground(Utils.labelTextField);
		lblLoaiNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLoaiNhanVien.setBounds(421, 0, 130, 65);
		pnlRow4.add(lblLoaiNhanVien);
		
		cboLoaiNhanVien = new ComboBox<>();
		cboLoaiNhanVien.setModel(new DefaultComboBoxModel<String>());
		cboLoaiNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboLoaiNhanVien.setBackground(new Color(140, 177, 180));
		cboLoaiNhanVien.setBounds(421, 46, 371, 36);
		cboLoaiNhanVien.addItem("");
		pnlRow4.add(cboLoaiNhanVien);
		
		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 792, 80);
		top += padding+30;
		add(pnlRow5);
		pnlRow5.setLayout(null);
		
		txtLuong = new TextField();
		txtLuong.setLabelText("Lương:");
		txtLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLuong.setBackground(new Color(203, 239, 255));
		txtLuong.setBounds(0, 0, 371, 55);
		pnlRow5.add(txtLuong);
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setForeground(Utils.labelTextField);
		lblCaLam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCaLam.setBounds(421, 0, 100, 65);
		pnlRow5.add(lblCaLam);
		
		cboCaLam = new ComboBox<>();
		cboCaLam.setModel(new DefaultComboBoxModel<String>());
		cboCaLam.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboCaLam.setBackground(new Color(140, 177, 180));
		cboCaLam.setBounds(421, 46, 371, 36);
		cboCaLam.addItem("");
		pnlRow5.add(cboCaLam);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 992, 50);
		top += padding+20;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnTimKiem = new Button("Tìm kiếm");
		btnTimKiem.setIcon(Utils.getImageIcon("user_searching.png"));
		btnTimKiem.setRadius(8);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnTimKiem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnTimKiem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setBorderColor(new Color(203, 239, 255));
		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTimKiem.setBounds(150, 0, 170, 40);
		pnlActions.add(btnTimKiem);
		
			
		btnRefresh = new Button("Làm mới");
		btnRefresh.setIcon(Utils.getImageIcon("refresh.png"));
		btnRefresh.setRadius(8);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnRefresh.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnRefresh.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setBorderColor(new Color(203, 239, 255));
		btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRefresh.setBounds(515, 0, 150, 40);
		pnlActions.add(btnRefresh);
		
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(0, 480, 1550, 250);
		scr.setBackground(Utils.primaryColor);
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

		tableModel = new DefaultTableModel(new String[] { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính","Địa chỉ", "Số điện thoại", "Loại nhân viên", "Quản lý bởi","Lương","Ca làm" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(150);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(150);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(150);
		tableColumnModel.getColumn(5).setPreferredWidth(150);
		tableColumnModel.getColumn(6).setPreferredWidth(150);
		tableColumnModel.getColumn(7).setPreferredWidth(150);
		tableColumnModel.getColumn(8).setPreferredWidth(150);
		tableColumnModel.getColumn(9).setPreferredWidth(150);
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
				txtNgaySinh.setText(tbl.getValueAt(row, 2).toString());
				String gt = tbl.getValueAt(row, 3).toString();
				if(gt.equals("nam")) {
					radNam.setSelected(true);
					radNu.setSelected(false);
				}			
				else {
					radNam.setSelected(false);
					radNu.setSelected(true);
				}
				txtDiaChi.setText(tbl.getValueAt(row, 4).toString());
				txtSoDienThoai.setText(tbl.getValueAt(row, 5).toString());
				cboLoaiNhanVien.setSelectedItem(tbl.getValueAt(row, 6));
				cboQuanLy.setSelectedItem(tbl.getValueAt(row, 7));
				txtLuong.setText(String.valueOf(Math.round(chuyenDoiFormat(tbl.getValueAt(row, 8).toString()))));
				cboCaLam.setSelectedItem(tbl.getValueAt(row, 9));		
			}
		});
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
//		pnlControl = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControl);
		
		btnTimKiem.addActionListener(this);
		btnRefresh.addActionListener(this);
		
		dsLNV = new ArrayList<LoaiNhanVien>();
		dsCL = new ArrayList<CaLam>();
		dsQL = new ArrayList<NhanVien>();
		
		cboQuanLy.addItem("");
		
		for (LoaiNhanVien lnv : LoaiNhanVien_dao.getAllLoaiNhanVien()) {
			dsLNV.add(lnv);
			cboLoaiNhanVien.addItem(lnv.getTenLoaiNhanVien());
		}
		
		for (CaLam cl : CaLam_dao.getAllCaLam()) {
			dsCL.add(cl);
			cboCaLam.addItem(cl.getBuoi());
		}
		
		for (NhanVien nv : NhanVien_dao.getAllNhanVienQuanLy()) {
			dsQL.add(nv);
			cboQuanLy.addItem(nv.getTenNhanVien());
		}
		dsNV = new ArrayList<NhanVien>();
		for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
			Object[] data = { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getNgaySinh(),
					nv.isGioiTinh() ? "nam" : "nữ", nv.getDiaChi(), nv.getSoDienThoai(),
					nv.getLoaiNhanVien().getTenLoaiNhanVien(),
					getTenQL(nv.getQuanLy() == null ? "" : nv.getQuanLy().getMaNhanVien()),
					format.format(nv.getLuong()), nv.getCaLam().getBuoi() };
			tableModel.addRow(data);
		}
	}
	public void refresh() throws RemoteException {
		txtMa.setText("");
		txtTen.setText("");
		txtSoDienThoai.setText("");
		txtDiaChi.setText("");
		txtNgaySinh.setText("");
		txtLuong.setText("");
		tbl.clearSelection();
		radNam.setSelected(true);
		cboQuanLy.setSelectedIndex(0);
		cboCaLam.setSelectedIndex(0);
		cboLoaiNhanVien.setSelectedIndex(0);
		tableModel.setRowCount(0);
		for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
			Object[] data = { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getNgaySinh(),
					nv.isGioiTinh() ? "nam" : "nữ", nv.getDiaChi(), nv.getSoDienThoai(),
					nv.getLoaiNhanVien().getTenLoaiNhanVien(),
					getTenQL(nv.getQuanLy() == null ? "" : nv.getQuanLy().getMaNhanVien()),
					format.format(nv.getLuong()), nv.getCaLam().getBuoi() };
			tableModel.addRow(data);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnTimKiem)) {
			tableModel.setRowCount(0);
			String sql = ChecktaoSQL();
//			System.out.println(sql);
//			System.out.println(token);
			try {
				for (NhanVien nv : NhanVien_dao.Find(sql, token)) {
					Object[] data = { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getNgaySinh(),
							nv.isGioiTinh() ? "nam" : "nữ", nv.getDiaChi(), nv.getSoDienThoai(),
							nv.getLoaiNhanVien().getTenLoaiNhanVien(),
							getTenQL(nv.getQuanLy() == null ? "" : nv.getQuanLy().getMaNhanVien()),
							format.format(nv.getLuong()), nv.getCaLam().getBuoi() };
					tableModel.addRow(data);
				}
				token.clear();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			JOptionPane.showMessageDialog(this, sql);
		}
		else if(src.equals(btnRefresh)) {
			try {
				refresh();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public String ChecktaoSQL() {
		String sql = "select * from NhanVien where";
		if(!txtMa.getText().isEmpty()) {
			token.add(txtMa.getText());
			sql +=" maNhanVien = :a"+(token.size()-1)+"";
		}
		if(!txtTen.getText().isEmpty()) {
			token.add(txtTen.getText());
			if(sql.contains("maNhanVien"))
				sql += " and tenNhanVien = :a"+(token.size()-1)+"";
			else
				sql +=" tenNhanVien = :a"+(token.size()-1)+"";
		}
		if(!txtSoDienThoai.getText().isEmpty()) {
			token.add(txtSoDienThoai.getText());
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien"))
				sql += " and soDienThoai = :a"+(token.size()-1)+"";
			else
				sql += " soDienThoai = :a"+(token.size()-1)+"";
		}
		if(!txtLuong.getText().isEmpty()) {
			token.add(txtLuong.getText());
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai"))
				sql += " and luong = :a"+(token.size()-1)+"";
			else
				sql += " luong = :a"+(token.size()-1)+"";
		}
		if(!txtNgaySinh.getText().isEmpty()) {
			token.add(txtNgaySinh.getText());
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai") || sql.contains("luong"))
				sql += " and ngaySinh = :a"+(token.size()-1)+"";
			else
				sql += " ngaySinh = :a"+(token.size()-1)+"";
		}
		if(!txtDiaChi.getText().isEmpty()) {
			token.add(txtDiaChi.getText());
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai")|| sql.contains("luong")|| sql.contains("ngaySinh"))
				sql += " and diaChi = :a"+(token.size()-1)+"";
			else
				sql += " diaChi = :a"+(token.size()-1)+"";
		}
		if(!cboLoaiNhanVien.getSelectedItem().toString().isEmpty()) {
			token.add(getMaLNV(cboLoaiNhanVien.getSelectedItem().toString()));
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai")|| sql.contains("luong")|| sql.contains("ngaySinh")
					|| sql.contains("diaChi"))
				sql += " and maLoaiNhanVien = :a"+(token.size()-1)+"";
			else
				sql += " maLoaiNhanVien = :a"+(token.size()-1)+"";
		}
		if(radNam.isSelected() && !radNu.isSelected()) {
			token.add("1");
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai")|| sql.contains("luong")|| sql.contains("ngaySinh")
					|| sql.contains("diaChi")|| sql.contains("maLoaiNhanVien"))
				sql += " and gioiTinh = :a"+(token.size()-1)+"";
			else
				sql += " gioiTinh = :a"+(token.size()-1)+"";
		}
		if(!radNam.isSelected() && radNu.isSelected()) {
			token.add("0");
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai")|| sql.contains("luong")|| sql.contains("ngaySinh")
					|| sql.contains("diaChi")|| sql.contains("maLoaiNhanVien"))
				sql += " and gioiTinh = :a"+(token.size()-1)+"";
			else
				sql += " gioiTinh = :a"+(token.size()-1)+"";
		}
		if(!cboCaLam.getSelectedItem().toString().isEmpty()) {
			token.add(getMaCL(cboCaLam.getSelectedItem().toString()));
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai")|| sql.contains("luong")|| sql.contains("ngaySinh")
					|| sql.contains("diaChi")|| sql.contains("maLoaiNhanVien")|| sql.contains("gioiTinh"))
				sql += " and caLam = :a"+(token.size()-1)+"";
			else
				sql += " caLam = :a"+(token.size()-1)+"";
		}
		if(!cboQuanLy.getSelectedItem().toString().isEmpty()) {
			token.add(getMaQL(cboQuanLy.getSelectedItem().toString()));
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("soDienThoai")|| sql.contains("luong")|| sql.contains("ngaySinh")
					|| sql.contains("diaChi")|| sql.contains("maLoaiNhanVien")|| sql.contains("gioiTinh")|| sql.contains("caLam"))
				sql += " and quanLy = :a"+(token.size()-1)+"";
			else
				sql += " quanLy = :a"+(token.size()-1)+"";
		}
		return sql;
	}
	public String getTenLNV(String ma) {
		for (LoaiNhanVien lnv : dsLNV) {
			if(lnv.getMaLoaiNhanVien().equals(ma))
				return lnv.getTenLoaiNhanVien();
		}
		return null;
	}
	public String getTenCL(String ma) {
		for (CaLam cl : dsCL) {
			if(cl.getMaCaLam().equals(ma))
				return cl.getBuoi();
		}
		return null;
	}
	public String getTenQL(String ma) {
		for (NhanVien nv : dsQL) {
			if(nv.getMaNhanVien().equals(ma))
				return nv.getTenNhanVien();
		}
		return null;
	}
	public String getMaLNV(String ten) {
		for (LoaiNhanVien lnv : dsLNV) {
			if(lnv.getTenLoaiNhanVien().equals(ten))
				return lnv.getMaLoaiNhanVien();
		}
		return null;
	}
	public String getMaCL(String ten) {
		for (CaLam cl : dsCL) {
			if(cl.getBuoi().equals(ten))
				return cl.getMaCaLam();
		}
		return null;
	}
	public String getMaQL(String ten) {
		for (NhanVien nv : dsQL) {
			if(nv.getTenNhanVien().equals(ten))
				return nv.getMaNhanVien();
		}
		return null;
	}
	public Double chuyenDoiFormat(String s) {
		Double n = 0.0;
		try {
			n = format.parse(s).doubleValue();
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return n;
	}
}
