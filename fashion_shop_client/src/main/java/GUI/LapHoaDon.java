package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.datechooser.DateChooser;
import components.notification.Notification;
import components.notification.Notification.Type;

import components.button.Button;
import components.comboBox.ComboBox;
import components.controlPanel.ControlPanel;
import components.radio.RadioButtonCustom;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietDatHangDAO;
import dao.ChiTietHoaDonDAO;
import dao.ChiTietKhuyenMaiDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.KichThuocDAO;
import dao.LoaiSanPhamDAO;
import dao.MauSacDAO;
import dao.NhanVienDAO;
import dao.PhieuDatHangDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;
import entities.ChiTietHoaDon;
import entities.ChiTietKhuyenMai;
import entities.ChiTietPhieuDatHang;
import entities.HoaDon;
import entities.KhachHang;
import entities.KhuyenMai;
import entities.KichThuoc;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhanVien;
import entities.PhieuDatHang;
import entities.SanPham;
import entities.TaiKhoan;
import utils.Utils;

public class LapHoaDon extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private JLabel lblTieuDe;
	public TextField txtSoDienThoai;
	private TextField txtMaHD;
	public TextField txtTen;
	private TextField txtNguoiLap;
	public RadioButtonCustom radNam;
	public RadioButtonCustom radNu;
	private TextField txtNgayLap;
	private DateChooser dateChoose;
	public TextField txtDiaChi;
	public TextField txtEmail;
	public TextField txtTongTien;
	private JTable tblSP;
	private DefaultTableModel tableModelSP;
	private ControlPanel pnlControlSP;
	public JTable tblCTHD;
	public DefaultTableModel tableModelCTHD;
	private ControlPanel pnlControlCTHD;
	private TextField txtSoLuong;
	private ComboBox cboDonDatHang;
	private Button btnSearch;
	private Button btnDeleteSP;
	private Button btnAddSP;
	private Button btnThem;
	private Button btnRefresh;
	private ArrayList<KhachHang> dsKH;
	private KhachHangDAO KhachHang_dao;
	private ButtonGroup btnGroup;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private SanPhamDAO SanPham_dao;
	public double tongTien;
	private ArrayList<Double> dsThanhTien;
	private String maTK;
	private HoaDonDAO HoaDon_dao;
	private ChiTietHoaDonDAO chiTietHoaDon_dao;
	private SanPhamDAO SanPham_Dao;
	private PhieuDatHangDAO PhieuDatHang_dao;
	private ChiTietDatHangDAO ChiTietPhieuDatHang_dao;
	private ArrayList<SanPham> dsSP;
	private ChiTietKhuyenMaiDAO ChiTietKhuyenMai_dao;
	private KhuyenMaiDAO KhuyenMai_Dao;
	private Button btnXem;
	private String maNV;
	public TextField txtTienNhan;
	public TextField txtTienTra;
	private NumberFormat format;
	private ArrayList<LoaiSanPham> dsLSP;
	private ArrayList<KichThuoc> dsKT;
	private ArrayList<MauSac> dsMS;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private KichThuocDAO KichThuoc_dao;
	private MauSacDAO MauSac_dao;
	private Button btnScan;
	private TextField txtMaSP;
	private Button btnTimKiem;
	public LapHoaDon(ManHinhChinh main, String maTK) throws RemoteException, MalformedURLException, NotBoundException {
		this.maTK = maTK;
		this.main = main;
		tongTien = 0.0;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792)-300;
		ChiTietPhieuDatHang_dao = (ChiTietDatHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietDatHangDao");
		ChiTietKhuyenMai_dao = (ChiTietKhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietKhuyenMaiDao");
		KhuyenMai_Dao = (KhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khuyenMaiDao");
		NhanVien_dao = (NhanVienDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/nhanVienDao");
		LoaiSanPham_dao = (LoaiSanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/loaiSanPhamDao");
		TaiKhoan_dao = (TaiKhoanDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/taiKhoanDao");
		KhachHang_dao = (KhachHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khachHangDao");
		KichThuoc_dao = (KichThuocDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/kichThuocDao");
		MauSac_dao = (MauSacDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/mauSacDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/sanPhamDao");
		HoaDon_dao = (HoaDonDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/hoaDonDao");
		chiTietHoaDon_dao = (ChiTietHoaDonDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietHoaDonDao");
		format = NumberFormat.getInstance();

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
		setLayout(null);
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, widthPnlContainer, 80);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		lblTieuDe = new JLabel();
		lblTieuDe.setText("Lập hóa đơn");
		lblTieuDe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblTieuDe.setBackground(new Color(203, 239, 255));
		lblTieuDe.setBounds(550, 30, 371, 55);
		pnlContainer.add(lblTieuDe);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtSoDienThoai = new TextField();
//		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setLabelText("Số điện thoại:");
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setBackground(new Color(203, 239, 255));
		txtSoDienThoai.setBounds(0, 0, 270, 55);
		pnlRow1.add(txtSoDienThoai);
		
		btnSearch = new Button();
		btnSearch.setIcon(Utils.getImageIcon("check-mark.png"));
		btnSearch.setRadius(8);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnSearch.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnSearch.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnSearch.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnSearch.setBorderColor(new Color(203, 239, 255));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setBounds(300, 15, 40, 40);
		pnlRow1.add(btnSearch);
		
		txtMaHD = new TextField();
		txtMaHD.setLabelText("Mã hóa đơn:");
		txtMaHD.setEditable(false);
		txtMaHD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaHD.setBackground(new Color(203, 239, 255));
		txtMaHD.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtMaHD);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 55);
		top += padding + 5;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		txtTen = new TextField();
		txtTen.setLabelText("Tên khách hàng:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtTen);

		txtNguoiLap = new TextField();
		txtNguoiLap.setLabelText("Người lập:");
		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNguoiLap.setBackground(new Color(203, 239, 255));
		txtNguoiLap.setBounds(421, 0, 371, 55);
		txtNguoiLap.setEditable(false);
		pnlRow2.add(txtNguoiLap);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 55);
		top += padding + 5;
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
		radNam.setSelected(true);
		pnlGroupGioiTinh.add(radNam);

		radNu = new RadioButtonCustom("Nữ");
		radNu.setFocusable(false);
		radNu.setBackground(Utils.secondaryColor);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNu.setBounds(79, -2, 59, 21);
		pnlGroupGioiTinh.add(radNu);
		
		btnGroup = new ButtonGroup();
		btnGroup.add(radNam);
		btnGroup.add(radNu);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setForeground(Utils.labelTextField);
		lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNgaySinh.setBounds(0, 0, 100, 65);
		pnlRow3.add(lblNgaySinh);
		
		txtNgayLap = new TextField();
		txtNgayLap.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayLap.setLineColor(new Color(149, 166, 248));
		txtNgayLap.setLabelText("Ngày lập:");
		txtNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayLap.setColumns(10);
		txtNgayLap.setBackground(new Color(203, 239, 255));
		txtNgayLap.setBounds(421, 0, 371, 55);
		pnlRow3.add(txtNgayLap);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayLap);
		
		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 792, 130);
		top += padding + 5;
		add(pnlRow4);
		pnlRow4.setLayout(null);
		
		txtDiaChi = new TextField();
		txtDiaChi.setLabelText("Địa chỉ:");
		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChi.setBackground(new Color(203, 239, 255));
		txtDiaChi.setBounds(0, 0, 371, 55);
		pnlRow4.add(txtDiaChi);
		
		txtEmail = new TextField();
		txtEmail.setLabelText("Email:");
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtEmail.setBackground(new Color(203, 239, 255));
		txtEmail.setBounds(0, 65, 371, 55);
		pnlRow4.add(txtEmail);
		
		txtTongTien = new TextField();
		txtTongTien.setLabelText("Tổng tiền:");
		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTongTien.setBackground(new Color(203, 239, 255));
		txtTongTien.setBounds(421, 0, 371, 55);
		txtTongTien.setEditable(false);
		txtTongTien.setText("0");
		pnlRow4.add(txtTongTien);
		
		txtTienNhan = new TextField();
		txtTienNhan.setLabelText("Tiền nhận:");
		txtTienNhan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTienNhan.setBackground(new Color(203, 239, 255));
		txtTienNhan.setBounds(421, 65, 150, 55);
		pnlRow4.add(txtTienNhan);
		
		txtTienTra = new TextField();
		txtTienTra.setLabelText("Tiền trả khách:");
		txtTienTra.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTienTra.setBackground(new Color(203, 239, 255));
		txtTienTra.setBounds(590, 65, 150, 55);
		txtTienTra.setEditable(false);
		pnlRow4.add(txtTienTra);
		txtTienNhan.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				Double tongtien = chuyenDoiFormat(txtTongTien.getText());
				Double tiennhan = 0.0;
				if(!txtTienNhan.getText().equals("")) 
					tiennhan = Double.valueOf(txtTienNhan.getText());
						
				Double tientra = tiennhan - tongtien;
				txtTienTra.setText(format.format(tientra));
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Double tongtien = chuyenDoiFormat(txtTongTien.getText());
				Double tiennhan = Double.valueOf(txtTienNhan.getText());
				
				Double tientra = tiennhan - tongtien;
				txtTienTra.setText(format.format(tientra));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Double tongtien = chuyenDoiFormat(txtTongTien.getText());
				Double tiennhan = Double.valueOf(txtTienNhan.getText());
				
				Double tientra = tiennhan - tongtien;
				txtTienTra.setText(format.format(tientra));
			}
		});
		
		JScrollPane scrSP = new JScrollPane();
		scrSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrSP.setBounds(left, 630, 1400, 95);
		scrSP.setBackground(Utils.primaryColor);
		scrSP.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
		scrSP.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpSP = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpSP.setScrollbarColor(new Color(203, 203, 203));
		scrSP.setVerticalScrollBar(scpSP);
		this.add(scrSP);

		tblSP = new JTable() {
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
		tblSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblSP.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderSP = tblSP.getTableHeader();
		TableColumnModel tableColumnModelSP = tblSP.getColumnModel();

		tableModelSP = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Nhà cung cấp","Màu sắc", "Kích thước", "Loại sản phẩm", "Đơn vị",
				"Số lượng"}, 0);
		tblSP.setModel(tableModelSP);
		tblSP.setFocusable(false);
		tblHeaderSP.setBackground(Utils.primaryColor);
		tblSP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblSP.setBackground(Color.WHITE);
		tblSP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelSP.getColumn(0).setPreferredWidth(150);
		tableColumnModelSP.getColumn(1).setPreferredWidth(150);
		tableColumnModelSP.getColumn(2).setPreferredWidth(150);
		tableColumnModelSP.getColumn(3).setPreferredWidth(200);
		tableColumnModelSP.getColumn(4).setPreferredWidth(150);
		tableColumnModelSP.getColumn(5).setPreferredWidth(150);
		tableColumnModelSP.getColumn(6).setPreferredWidth(150);
		tableColumnModelSP.getColumn(7).setPreferredWidth(150);
		tableColumnModelSP.getColumn(8).setPreferredWidth(150);
		tblSP.addMouseListener(new MouseListener() {
			
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
				btnAddSP.setEnabled(true);
				btnDeleteSP.setEnabled(true);
			}
		});
		
		tblHeaderSP.setPreferredSize(new Dimension((int) tblHeaderSP.getPreferredSize().getWidth(), 36));
		tblHeaderSP.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblSP.setRowHeight(36);
		scrSP.setViewportView(tblSP);
		
//		pnlControlSP = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControlSP);
		
		JScrollPane scrCTHD = new JScrollPane();
		scrCTHD.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrCTHD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrCTHD.setBounds(left+800, 80, 650, 355);
		scrCTHD.setBackground(Utils.primaryColor);
		scrCTHD.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
		scrCTHD.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTHD = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTHD.setScrollbarColor(new Color(203, 203, 203));
		scrCTHD.setVerticalScrollBar(scpCTHD);
		this.add(scrCTHD);

		tblCTHD = new JTable() {
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
		tblCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTHD.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderCTHD = tblCTHD.getTableHeader();
		TableColumnModel tableColumnModelCTHD = tblCTHD.getColumnModel();

		tableModelCTHD = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm","Số lượng","Đơn giá","Giảm giá","Thành tiền" }, 0);
		tblCTHD.setModel(tableModelCTHD);
		tblCTHD.setFocusable(false);
		tblHeaderCTHD.setBackground(Utils.primaryColor);
		tblCTHD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTHD.setBackground(Color.WHITE);
		tblCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelCTHD.getColumn(0).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(1).setPreferredWidth(120);
		tableColumnModelCTHD.getColumn(2).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(3).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(4).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(5).setPreferredWidth(130);
		tblCTHD.addMouseListener(new MouseListener() {
			
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
				int row = tblCTHD.getSelectedRow();
				txtSoLuong.setText(tblCTHD.getValueAt(row, 2).toString());
				mokhoaButtonSP();
			}
		});
		
		tblHeaderCTHD.setPreferredSize(new Dimension((int) tblHeaderCTHD.getPreferredSize().getWidth(), 36));
		tblHeaderCTHD.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tblHeaderCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTHD.setRowHeight(36);
		scrCTHD.setViewportView(tblCTHD);
		
//		pnlControlCTHD = new ControlPanel(Utils.getLeft(-550),topPnlControl - 445 , main);
//		this.add(pnlControlCTHD);
		
		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left+850, 435, 792, 120);
		top += padding+50;
		add(pnlRow5);
		pnlRow5.setLayout(null);
		
		txtSoLuong= new TextField();
		txtSoLuong.setLabelText("Số lượng:");
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBackground(new Color(203, 239, 255));
		txtSoLuong.setBounds(0, 0, 200, 55);
		pnlRow5.add(txtSoLuong);
		
		btnDeleteSP = new Button("Xóa sản phẩm");
		btnDeleteSP.setIcon(Utils.getImageIcon("delet.png"));
		btnDeleteSP.setRadius(8);
		btnDeleteSP.setForeground(Color.WHITE);
		btnDeleteSP.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnDeleteSP.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDeleteSP.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDeleteSP.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDeleteSP.setBorderColor(new Color(203, 239, 255));
		btnDeleteSP.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDeleteSP.setBounds(300, 65, 220, 40);
		pnlRow5.add(btnDeleteSP);
		
		btnAddSP = new Button("Thêm sản phẩm");
		btnAddSP.setIcon(Utils.getImageIcon("plus.png"));
		btnAddSP.setRadius(8);
		btnAddSP.setForeground(Color.WHITE);
		btnAddSP.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnAddSP.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnAddSP.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnAddSP.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnAddSP.setBorderColor(new Color(203, 239, 255));
		btnAddSP.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnAddSP.setBounds(0, 65, 220, 40);
		pnlRow5.add(btnAddSP);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 1450, 180);
		top += padding;
		add(pnlActions);
		pnlActions.setLayout(null);
		
//		JLabel lblDonDatHang = new JLabel("Đơn đặt hàng:");
//		lblDonDatHang.setForeground(Utils.labelTextField);
//		lblDonDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		lblDonDatHang.setBounds(0, -20, 100, 65);
//		pnlActions.add(lblDonDatHang);
		
//		cboDonDatHang = new ComboBox<>();
//		cboDonDatHang.setModel(new DefaultComboBoxModel<String>());
//		cboDonDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		cboDonDatHang.setBackground(new Color(140, 177, 180));
//		cboDonDatHang.setBounds(120, 0, 200, 36);
//		pnlActions.add(cboDonDatHang);
		
		btnXem = new Button("Xem danh sách đặt hàng");
		btnXem.setIcon(Utils.getImageIcon("searching.png"));
		btnXem.setRadius(8);
		btnXem.setForeground(Color.WHITE);
		btnXem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnXem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXem.setBorderColor(new Color(203, 239, 255));
		btnXem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXem.setBounds(0, 0, 350, 40);
		pnlActions.add(btnXem);
		
		btnThem = new Button("Lập hóa đơn");
		btnThem.setIcon(Utils.getImageIcon("bill (1).png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(400, 0, 180, 40);
		pnlActions.add(btnThem);
		
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
		btnRefresh.setBounds(400, 60, 150, 40);
		pnlActions.add(btnRefresh);
		
		btnScan = new Button("Quét mã sản phẩm");
		btnScan.setIcon(Utils.getImageIcon("qr-code-scan.png"));
		btnScan.setRadius(8);
		btnScan.setForeground(Color.WHITE);
		btnScan.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnScan.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnScan.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnScan.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnScan.setBorderColor(new Color(203, 239, 255));
		btnScan.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnScan.setBounds(0, 60, 280, 40);
		pnlActions.add(btnScan);
		
		txtMaSP= new TextField();
		txtMaSP.setLabelText("Mã sản phẩm:");
		txtMaSP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaSP.setBackground(new Color(203, 239, 255));
		txtMaSP.setBounds(0, 100, 250, 55);
		pnlActions.add(txtMaSP);
		
		btnTimKiem = new Button("Tìm kiếm");
		btnTimKiem.setIcon(Utils.getImageIcon("searching.png"));
		btnTimKiem.setRadius(8);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnTimKiem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnTimKiem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setBorderColor(new Color(203, 239, 255));
		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTimKiem.setBounds(400, 120, 150, 40);
		pnlActions.add(btnTimKiem);
		
		btnAddSP.addActionListener(this);
		btnDeleteSP.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnSearch.addActionListener(this);
		btnThem.addActionListener(this);
		btnXem.addActionListener(this);
		btnScan.addActionListener(this);
		btnTimKiem.addActionListener(this);
		dsKH = new ArrayList<KhachHang>();
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			dsKH.add(kh);
		}
		maNV = "";
		for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
			if(maTK.equals(tk.getMaTaiKhoan())) {
				maNV = tk.getNhanVien().getMaNhanVien();
				break;
			}		
		}
		for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
			if(maNV.equals(nv.getMaNhanVien())) {
				txtNguoiLap.setText(nv.getTenNhanVien());
				break;
			}
		}	
		dsLSP = new ArrayList<LoaiSanPham>();
		dsKT = new ArrayList<KichThuoc>();
		dsMS = new ArrayList<MauSac>();
		
		for (LoaiSanPham lsv : LoaiSanPham_dao.getAllLoaiSanPham()) {
			dsLSP.add(lsv);		
		}

		for (KichThuoc kt : KichThuoc_dao.getAllKichThuoc()) {
			dsKT.add(kt);
		
		}
		
		for (MauSac ms : MauSac_dao.getAllMauSac()) {
			dsMS.add(ms);
		
		}

//		for (SanPham sp : SanPham_dao.getAllSanPham()) {
////			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),(Math.round(sp.getDonGia())*120/100), sp.getNhaCungCap(),
////					getTenMS(sp.getMauSac()), getTenKT(sp.getKichThuoc()), getTenLSP(sp.getLoaiSanPham()), sp.getDonVi(),sp.getSoLuong()};
////			tableModelSP.addRow(data);
//			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format((sp.getDonGia())*120/100), sp.getNhaCungCap(),
//					sp.getMauSac().getTenMauSac(),sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
//			
//			tableModelSP.addRow(data);
//		}
		
		
//		SanPham_dao = new SanPham_Dao();
//		for (SanPham sp : SanPham_dao.getAllSanPham()) {
//			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),Math.round(sp.getDonGia()), sp.getNhaCungCap(),
//					sp.getMauSac(), sp.getKichThuoc(), sp.getLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
//			tableModelSP.addRow(data);
//		}
		btnAddSP.setEnabled(false);
		btnDeleteSP.setEnabled(false);
//		cboDonDatHang.addItem("");
//		
//		PhieuDatHang_dao = new PhieuDatHang_Dao();
//		for (PhieuDatHang pdh : PhieuDatHang_dao.getAllPhieuDatHang()) {
//			cboDonDatHang.addItem(pdh.getMaPhieuDatHang());
//		}
		dsSP = new ArrayList<SanPham>();
		for (SanPham sanPham : SanPham_dao.getAllSanPham()) {
			dsSP.add(sanPham);
		}
//		cboDonDatHang.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
////				if(cboDonDatHang.getSelectedItem().toString().equals("")==false)
////					new FormPhieuDatHang(cboDonDatHang.getSelectedItem().toString()).setVisible(true);;
//				tongTien = 0.0;
//				Double phantram = 0.0;
//				tableModelCTHD.setRowCount(0);
//				String maPDH = cboDonDatHang.getSelectedItem().toString();
//				for (ChiTietPhieuDatHang ctpdh : ChiTietPhieuDatHang_dao.getAllChiTietPhieuDatHang(maPDH)) {
//					phantram = 0.0;
//					for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai()) {
//						if(ctkm.getSanPham().getMaSanPham().equals(ctpdh.getSanPham().getMaSanPham())) {
//							for (KhuyenMai km : KhuyenMai_Dao.getAllKhuyenMai()) {
//								if(km.getMaKhuyenMai().equals(ctkm.getKhuyenMai().getMaKhuyenMai()))
//									if((km.getNgayBatDau().isBefore(LocalDate.now()) || km.getNgayBatDau().isEqual(LocalDate.now()))  
//											&& (km.getNgayKetThuc().isAfter(LocalDate.now()) || km.getNgayKetThuc().isEqual(LocalDate.now()))) {
//										phantram = ctkm.getPhanTramKhuyenMai();
//										break;
//									}			
//							}	
//							break;
//						}		
//					}
//					Object[] data = {ctpdh.getSanPham().getMaSanPham(),getTenSP(ctpdh.getSanPham().getMaSanPham()), ctpdh.getSoLuong(),
//							Math.round(ctpdh.getDonGia()),phantram,
//							Math.round(Double.valueOf(ctpdh.getSoLuong())*(Double.valueOf(ctpdh.getDonGia())-Double.valueOf(ctpdh.getDonGia())*phantram/100))};
//					tongTien += Math.round(Double.valueOf(ctpdh.getSoLuong())*(Double.valueOf(ctpdh.getDonGia())-Double.valueOf(ctpdh.getDonGia())*phantram/100));
//					tableModelCTHD.addRow(data);
//				}
////				for (ChiTietPhieuDatHang ctpdh : ChiTietPhieuDatHang_dao.getAllChiTietPhieuDatHang(maPDH)) {
////					tongTien += ctpdh.getSoLuong()*(ctpdh.getDonGia()-ctpdh.getDonGia()*phantram);
////				}
//				txtTongTien.setText(tongTien.toString());
//				String maKH = "";
//				for (PhieuDatHang pdh : PhieuDatHang_dao.getAllPhieuDatHang()) {
//					if(pdh.getMaPhieuDatHang().equals(maPDH)) {
//						maKH = pdh.getKhachHang().getMaKhachHang();
//						break;
//					}
//						
//				}
//				for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
//					if(kh.getMaKhachHang().equals(maKH)) {
//						txtSoDienThoai.setText(kh.getSoDienThoai());
//						txtTen.setText(kh.getTenKhachHang());
//						if(kh.isGioiTinh()) {
//							radNam.setSelected(true);
//							radNu.setSelected(false);
//						}
//						else {
//							radNam.setSelected(false);
//							radNu.setSelected(true);
//						}
//						txtDiaChi.setText(kh.getDiaChi());
//						if(kh.getEmail() == null)
//							txtEmail.setText("");
//						else
//							txtEmail.setText(kh.getEmail());
//						break;
//					}				
//				}
//			}
//		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnSearch)) {
			String sdt = txtSoDienThoai.getText();
			if(sdt.equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
			else {
				boolean kq_tt = false;
				for (KhachHang kh : dsKH) {
					if(sdt.equals(kh.getSoDienThoai())) {
						kq_tt = true;
						txtTen.setText(kh.getTenKhachHang());
						if(kh.isGioiTinh()) {
							radNam.setSelected(true);
							radNu.setSelected(false);
						}
						else {
							radNam.setSelected(false);
							radNu.setSelected(true);
						}
						if(kh.getDiaChi() == null)
							txtDiaChi.setText("");
						else
							txtDiaChi.setText(kh.getDiaChi());
						if(kh.getEmail() == null)
							txtEmail.setText("");
						else
							txtEmail.setText(kh.getEmail());
					}
				}
				if(kq_tt ==false) {
					JOptionPane.showMessageDialog(this, "Khách hàng này chưa có trong danh sách khách hàng, vui lòng thêm thông tin khách hàng vào hệ thống");
					String title = "Quản lý khách hàng";
					QuanLyKhachHang qlkh;
					try {
						qlkh = new QuanLyKhachHang(main, maTK);
						qlkh.txtPhone.setText(sdt);
//						JPanel pnl = new QuanLyKhachHang(main);
						main.addPnlBody(qlkh, title, 2, 1);
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
				}
			}
			
		}
		else if(src.equals(btnAddSP)) {
			String soluong = txtSoLuong.getText();
			if(validateProductInput()){
				int row = tblSP.getSelectedRow();
				int totalRow = tblCTHD.getRowCount();
				if(Integer.valueOf(tblSP.getValueAt(row, 8).toString()) < Integer.valueOf(soluong)) {
					JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
					txtSoLuong.setText("");
					txtSoLuong.requestFocus();
				}
				else {
					Boolean tontai = false;
					int slmoi = 0;
					Double phantram = 0.0;
//					lấy phần trăm giảm giá
					try {
						for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai()) {
							if(ctkm.getSanPham().getMaSanPham().equals(tblSP.getValueAt(row, 0).toString())) {
								for (KhuyenMai km : KhuyenMai_Dao.getAllKhuyenMai()) {
									if(km.getMaKhuyenMai().equals(ctkm.getKhuyenMai().getMaKhuyenMai()))
										if((km.getNgayBatDau().isBefore(LocalDate.now()) || km.getNgayBatDau().isEqual(LocalDate.now()))  
												&& (km.getNgayKetThuc().isAfter(LocalDate.now()) || km.getNgayKetThuc().isEqual(LocalDate.now()))) {
											phantram = ctkm.getPhanTramKhuyenMai();
											
											break;
										}			
								}		
								break;
							}		
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					Double dongia = 0.0;
//					try {
//						dongia = format.parse(tblSP.getValueAt(row, 2).toString()).doubleValue();
//					} catch (ParseException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					double dongia = chuyenDoiFormat(tblSP.getValueAt(row, 2).toString());
					if(phantram != 0.0) {
						dongia -= dongia*phantram/100;
					}
					double thanhTien = Integer.valueOf(soluong)* dongia;
//					Thêm cùng sản phẩm => tăng số lượng sản phẩm
					for(int i = 0;i < totalRow;i++) {
						if(tblCTHD.getValueAt(i, 0).toString().equals(tblSP.getValueAt(row, 0).toString())) {
							tontai = true;
							slmoi = Integer.valueOf(soluong)+Integer.valueOf(tblCTHD.getValueAt(i, 2).toString());
							tblCTHD.setValueAt(slmoi, i, 2);
							thanhTien = slmoi*(dongia);
							tongTien += Double.valueOf(soluong)*dongia;
							tblCTHD.setValueAt(format.format(thanhTien), i, 5);
							txtSoLuong.setText("");
							tblSP.clearSelection();
							khoaButtonSP();
							break;
						}
					}	
					if(tontai == false) {
						tongTien+= thanhTien;
						Object[] data = {tblSP.getValueAt(row, 0),tblSP.getValueAt(row, 1),
								soluong,format.format(dongia),phantram,format.format(thanhTien)};
						tableModelCTHD.addRow(data);
						txtSoLuong.setText("");
						tblSP.clearSelection();
						khoaButtonSP();
					}
					txtTongTien.setText(format.format(tongTien));
					txtTienNhan.setText("");
					txtTienTra.setText("");
				}		
			}		
		}
		else if(src.equals(btnDeleteSP)) {
			int row = tblCTHD.getSelectedRow();
			if(row!= -1) {
				Double tt = chuyenDoiFormat(tblCTHD.getValueAt(row, 5).toString());
				tableModelCTHD.removeRow(row);
				tongTien-= tt;
				txtTongTien.setText(format.format(tongTien));
				txtSoLuong.setText("");
				txtTienNhan.setText("");
				txtTienTra.setText("");
				tblSP.clearSelection();
				khoaButtonSP();
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm trong hóa đơn cần xóa!");
			}
		}
		else if(src.equals(btnRefresh)) {
			refresh();
		}
		else if(src.equals(btnThem)) {
			if(tableModelCTHD.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần mua!");
			}
			else {
				if(validator()) {				
					try {
						int row = tableModelCTHD.getRowCount();
						String sdt = txtSoDienThoai.getText();
						String mahd = null;
						try {
							mahd = taoMaHoaDon();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String khachhang = "";
						String nguoilap = "";
						for (KhachHang kh : dsKH) {
							if(sdt.equals(kh.getSoDienThoai())) {
								khachhang = kh.getMaKhachHang();
								break;
							}				
						}
						for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
							if(maTK.equals(tk.getMaTaiKhoan())) {
								nguoilap = tk.getNhanVien().getMaNhanVien();
								break;
							}		
						}
						NhanVien nv = NhanVien_dao.getNhanVien(nguoilap);
						KhachHang kh = KhachHang_dao.getKhachHang(khachhang);
						String date[] = txtNgayLap.getText().split("-");
						HoaDon hd = new HoaDon(mahd, LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2])),
								nv, kh);
						if(HoaDon_dao.create(hd)) {
							for(int i = 0; i < row;i++) {
								String masp = tblCTHD.getValueAt(i, 0).toString();
								SanPham sp = SanPham_dao.getSanPham(masp);
								int soluong = Integer.valueOf(tblCTHD.getValueAt(i, 2).toString());
								Double dongia = chuyenDoiFormat(tblCTHD.getValueAt(i, 3).toString());
								ChiTietHoaDon cthd = new ChiTietHoaDon(hd,
										sp, soluong, dongia);
								int soLuongCapNhat=0;
								for (SanPham sp1 : SanPham_dao.getAllSanPham()) {
									if(sp1.getMaSanPham().equals(masp))
										soLuongCapNhat = sp1.getSoLuong()- soluong;
								}
								if(chiTietHoaDon_dao.create(cthd)) {
									
									SanPham_dao.UpdateSoLuong(masp, soLuongCapNhat);
								}
								
							}
							JOptionPane.showMessageDialog(this, "Lập hóa đơn thành công");
							int tb = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?", "Chú ý", JOptionPane.YES_NO_OPTION);
							if(tb == JOptionPane.YES_OPTION)
								inHoaDon(mahd);
//							if(cboDonDatHang.getSelectedItem().equals("")==false) {
//								PhieuDatHang_dao.Delete(cboDonDatHang.getSelectedItem().toString());
//								cboDonDatHang.removeItem(cboDonDatHang.getSelectedItem().toString());
//							}
							tongTien = 0.0;
							refresh();
							tableModelSP.setRowCount(0);
//							for (SanPham sp : SanPham_dao.getAllSanPham()) {
//								Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format((sp.getDonGia())*120/100), sp.getNhaCungCap(),
//										sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
//								tableModelSP.addRow(data);
//							}
						}
					} catch (NumberFormatException | HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}	
		}
		else if(src.equals(btnXem)) {
//			if(cboDonDatHang.getSelectedItem().toString().equals("") == false)
//				new FormPhieuDatHang(cboDonDatHang.getSelectedItem().toString()).setVisible(true);
//			else
//				JOptionPane.showMessageDialog(this, "Bạn chưa chọn đơn đặt hàng để xem"); 
			String title = "Xem danh sách đặt hàng";
			XemDanhSachDatHang dsDH;
			try {
				dsDH = new XemDanhSachDatHang(main, maTK);
				main.addPnlBody(dsDH, title, 2, 1);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
//			JPanel pnl = new QuanLyKhachHang(main);
			
		}
		else if(src.equals(btnScan)) {
			Form_Scan FScan;
			try {
				FScan = new Form_Scan(this);
				FScan.setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(src.equals(btnTimKiem)) {
			tableModelSP.setRowCount(0);
			String maSP = txtMaSP.getText();
			if (maSP.equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm");
			else {
				boolean kq_tt = false;
				for (SanPham sp : dsSP) {
					if (maSP.equals(sp.getMaSanPham())) {
						kq_tt = true;
						Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(),
								format.format((sp.getDonGia()) * 120 / 100), sp.getNhaCungCap(),
								sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(),
								sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(), sp.getSoLuong() };
						tableModelSP.addRow(data);
					}
				}
				if (kq_tt == false) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
				}
			}
		}
	}
	public void refresh() {
		txtSoDienThoai.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		radNam.setSelected(true);
		radNu.setSelected(false);
		txtMaHD.setText("");
		txtSoLuong.setText("");
		txtTongTien.setText("0.0");
		txtTienNhan.setText("");
		txtTienTra.setText("");;
		txtMaSP.setText("");
		tongTien = 0.0;
		tableModelSP.setRowCount(0);
		tableModelCTHD.setRowCount(0);
		tblSP.clearSelection();
		btnAddSP.setEnabled(false);
		btnDeleteSP.setEnabled(false);
	}
	public String getTenSP(String maSP) {
		for (SanPham sanPham : dsSP) {
			if(sanPham.getMaSanPham().equals(maSP))
				return sanPham.getTenSanPham();
		}
		return null;
	}
//	public String taoMaHoaDon() {
//		int dem = 0;
//		for (HoaDon hd: HoaDon_dao.getAllHoaDon()) {
//			dem+=1;
//		}
//		dem +=1;
//		if(dem < 10) {
//			return "HD000"+dem;
//		}
//		else if(dem < 100) {
//			return "HD00"+dem;
//		}
//		else if(dem < 1000) {
//			return "HD0"+dem;
//		}
//		else
//			return "HD1000";
//	}
	public String taoMaHoaDon() throws RemoteException {
		ArrayList<String> dsmaHD = new ArrayList<String>();
		for (HoaDon hd : HoaDon_dao.getAllHoaDon()) {
			dsmaHD.add(hd.getMaHoaDon());
		}
		for(int i = 1;i <= 1000;i++) {
			String ma = "";
			if(i<10)
				ma = "HD000"+i;
			else if(i<100)
				ma = "HD00"+i;
			else if(i<1000)
				ma = "HD0"+i;
			else
				ma = "HD1000";
			if(dsmaHD.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}
	public void khoaButtonSP() {
		btnAddSP.setEnabled(false);
		btnDeleteSP.setEnabled(false);
	}
	public void mokhoaButtonSP() {
		btnAddSP.setEnabled(true);
		btnDeleteSP.setEnabled(true);
	}
	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
//		txt.setError(true);
		txt.selectAll();
		txt.requestFocus();
		return false;
	}

	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";

		String soDienThoai = txtSoDienThoai.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtSoDienThoai, "Vui lòng nhập số điện thoại");
		if(!soDienThoai.matches("^[0-9]{10}$"))
			return showThongBaoLoi(txtSoDienThoai, "Số điện thoại gồm 10 ký tự số");

		String tiennhan = txtTienNhan.getText().trim();
		String tienTra = txtTienTra.getText().trim();
		double soTienTra = chuyenDoiFormat(tienTra);

		if (tiennhan.length() <= 0) {
			return showThongBaoLoi(txtTienNhan, "Vui lòng nhập tiền nhận từ khách");
		}
		try {
			Double tiennhanDouble = Double.parseDouble(tiennhan);
			if (tiennhanDouble <= 0) {
				return showThongBaoLoi(txtTienNhan, "Vui lòng nhập tiền nhận nguyên dương");
			}
		} catch (NumberFormatException e) {
			return showThongBaoLoi(txtTienNhan, "Vui lòng nhập tiền nhận nguyên dương hợp lệ");
		}
		if (soTienTra < 0) {
			return showThongBaoLoi(txtTienTra, "Chưa nhập đủ tiền nhận để thanh toán!");
		}
		return true;
	}
//	
	private boolean validateProductInput() {
		
		String soLuong = txtSoLuong.getText().trim();

		if (soLuong.length() <= 0) {
		    return showThongBaoLoi(txtSoLuong, "Vui lòng nhập số lượng");
		}
		try {
		    int soLuongInt = Integer.parseInt(soLuong);
		    if (soLuongInt <= 0) {
		        return showThongBaoLoi(txtSoLuong, "Vui lòng nhập số lượng nguyên dương");
		    }
		} catch (NumberFormatException e) {
		    return showThongBaoLoi(txtSoLuong, "Vui lòng nhập số lượng nguyên dương hợp lệ");
		}
		return true;
	}
	public void openBill(String path) {
        try {
            if((new File(System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatHoaDon\\" + path + ".pdf")).exists()){
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll, FileProtocolHandler " + System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatHoaDon\\" + path + ".pdf");
            } else {
                JOptionPane.showMessageDialog(null, "File is not Exists");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
	public void inHoaDon(String maHD) {
//		System.setProperty("file.encoding", "utf-8");
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatHoaDon\\" + maHD + ".pdf";
        com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10); //10 is the size
        com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 24); //30 is the size

        Document doc = new Document();
        try {
        	PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));
            doc.open();
            Paragraph ShopName = new Paragraph("                         CLOTHING SHOP\n", textFont24);
            doc.add(ShopName);
            Paragraph DiaChi = new Paragraph("                               Địa chỉ: 4 Nguyễn Văn Bảo. Quận Gò Vấp, Thành phố Hồ Chí Minh\n", textFont);
            doc.add(DiaChi);
            Paragraph SDT = new Paragraph("                                                          Số điện thoại: 0901234567\n", textFont);
            doc.add(SDT);
            Paragraph starLine = new Paragraph("==================================================================================", textFont);
            doc.add(starLine);

//            Thông tin nhân viên
            String[] ngaylap = txtNgayLap.getText().split("-");
            LocalDate date = LocalDate.of(Integer.valueOf(ngaylap[0]), Integer.valueOf(ngaylap[1]), Integer.valueOf(ngaylap[2]));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Paragraph paragraphMaNhanVienLine1 = new Paragraph("               Mã nhân viên:" + maNV + "                                             Ngày lập hóa đơn: " + dtf.format(date) + "\n", textFont);
            doc.add(paragraphMaNhanVienLine1);
            Paragraph paragraphTenNhanVienLine2 = new Paragraph("              Tên nhân viên: " + txtNguoiLap.getText(), textFont);
            doc.add(paragraphTenNhanVienLine2);
            doc.add(starLine);
//
            //Thông tin hóa đơn
            
            Paragraph paragraphMaHoaDonLine1 = new Paragraph("              Mã hóa đơn:" + maHD + "                                          Mã khách hàng: " + getMaKH(txtTen.getText()) + "\n", textFont);
            doc.add(paragraphMaHoaDonLine1);
            Paragraph paragraphMaHoaDonLine2 = new Paragraph("              Tên khách hàng: " + txtTen.getText() + "\n", textFont);
            doc.add(paragraphMaHoaDonLine2);
//
            Paragraph paragraphMaHoaDonLine3 = new Paragraph("              Số điện thoại: " + txtSoDienThoai.getText() + "\n", textFont);
            doc.add(paragraphMaHoaDonLine3);
            doc.add(starLine);

//            Chi tiết hóa đơn
            NumberFormat formatter = NumberFormat.getInstance();
            Paragraph paragraph4 = new Paragraph("              Chi tiết hóa đơn", textFont);
            doc.add(paragraph4);
            Paragraph paragraph5 = new Paragraph("\n");
            doc.add(paragraph5);
            PdfPTable tbl = new PdfPTable(4);
            tbl.addCell(new Phrase("Tên sản phẩm", textFont));
            tbl.addCell(new Phrase("Đơn giá", textFont));
            tbl.addCell(new Phrase("Số lượng", textFont));
            tbl.addCell(new Phrase("Tổng tiền", textFont));
            for (int i = 0; i < tableModelCTHD.getRowCount(); i++) {
                String tenSP = tblCTHD.getValueAt(i, 1).toString();
                Double donGia = chuyenDoiFormat(tblCTHD.getValueAt(i, 3).toString());
                String sl = tblCTHD.getValueAt(i, 2).toString();
                Double tongTien = chuyenDoiFormat(tblCTHD.getValueAt(i, 5).toString());
                tbl.addCell(new Phrase(tenSP, textFont));
                tbl.addCell(new Phrase(formatter.format(Double.valueOf(donGia)), textFont));
                tbl.addCell(new Phrase(sl, textFont));
                tbl.addCell(new Phrase(formatter.format(Double.valueOf(tongTien)), textFont));
            }
            doc.add(tbl);
            doc.add(starLine);
            //Thông tin tổng tiền:
            Paragraph paragraphTongTienLine1 = new Paragraph("              Tổng tiền hóa đơn:" + txtTongTien.getText(), textFont);
            doc.add(paragraphTongTienLine1);
            Paragraph paragraphTongTienLine2 = new Paragraph("              Tiền nhận từ khách:" + formatter.format(Double.valueOf(txtTienNhan.getText())), textFont);
            doc.add(paragraphTongTienLine2);
            Paragraph paragraphTongTienLine3 = new Paragraph("              Tiền trả khách:" + txtTienTra.getText(), textFont);
            doc.add(paragraphTongTienLine3);
            doc.add(starLine);
            Paragraph thanskMsg = new Paragraph("               Xin cám ơn quí khách đã mua hàng tại cửa hàng của chúng tôi, mong bạn sẽ quay lại vào những lần tới ^^", textFont);
            doc.add(thanskMsg);
            doc.close();
            //open pdf
            openBill(maHD);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
	}
	public String getMaKH(String ten) throws RemoteException {
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			if(kh.getTenKhachHang().equals(ten))
				return kh.getMaKhachHang();
		}
		return null;
	}
	public String getMaLSP(String ten) {
		for (LoaiSanPham lsp : dsLSP) {
			if(lsp.getTenLoaiSanPham().equals(ten))
				return lsp.getMaLoaiSanPham();
		}
		return null;
	}
	public String getMaKT(String ten) {
		for (KichThuoc kt : dsKT) {
			if(kt.getTenKichThuoc().equals(ten))
				return kt.getMaKichThuoc();
		}
		return null;
	}
	public String getMaMS(String ten) {
		for (MauSac ms : dsMS) {
			if(ms.getTenMauSac().equals(ten))
				return ms.getMaMauSac();
		}
		return null;
	}
	public String getTenLSP(String ma) {
		for (LoaiSanPham lsp : dsLSP) {
			if(lsp.getMaLoaiSanPham().equals(ma))
				return lsp.getTenLoaiSanPham();
		}
		return null;
	}
	public String getTenKT(String ma) {
		for (KichThuoc kt : dsKT) {
			if(kt.getMaKichThuoc().equals(ma))
				return kt.getTenKichThuoc();
		}
		return null;
	}
	public String getTenMS(String ma) {
		for (MauSac ms : dsMS) {
			if(ms.getMaMauSac().equals(ma))
				return ms.getTenMauSac();
		}
		return null;
	}
	public double chuyenDoiFormat(String s) {
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
