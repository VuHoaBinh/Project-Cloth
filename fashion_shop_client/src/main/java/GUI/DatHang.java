package GUI;

//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumnModel;
//
//import components.button.Button;
//import components.controlPanel.ControlPanel;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import dao.NhanVien_Dao;
//import dao.TaiKhoan_Dao;
//import entity.NhanVien;
//import entity.TaiKhoan;
//import utils.Utils;
//
//public class DatHang extends JPanel{
//	private ControlPanel pnlControl;
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	
//	private JPanel pnlMain;
//	private DefaultTableModel modelDSHD;
//	private JTable tblDSHD;
//	private JPanel pnlTTKH;
//	private JLabel lblTenKH;
//	private TextField txtTenKH;
//	private JLabel lblSDT;
//	private TextField txtSDT;
//	private JLabel lblDiaChi;
//	private TextField txtDiaChi;
//	private JLabel lblEmail;
//	private TextField txtEmail;
//	private JLabel lblTienShip;
//	private TextField txtTienShip;
//	private JLabel lblTongTien;
//	private TextField txtTongTien;
//	private Button btnDatHang;
//	private JTable tblSP;
//	private ControlPanel pnlControlSP;
//	private DefaultTableModel tableModelSP;
//	private Button btnXoa;
//	private TaiKhoan_Dao TaiKhoan_dao;
//	private NhanVien_Dao NhanVien_dao;
//	
//	public DatHang(ManHinhChinh main, String maTK) {
//		this.main = main;
//		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
//		int top = padding;
//		int left = Utils.getLeft(792);
//
//		setBackground(Utils.secondaryColor);
//		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
//		setLayout(null);
//		
//		int topPnlControl = Utils.getBodyHeight() - 80;
//		
//		JPanel pnlContainer = new JPanel();
//		pnlContainer.setBackground(Utils.secondaryColor);
//		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, widthPnlContainer, 80);
//		pnlContainer.setLayout(null);
//		this.add(pnlContainer);
//
//		JPanel pnlHeader = new JPanel();
//		pnlHeader.setBackground(Utils.secondaryColor);
//		pnlHeader.setBounds(16, 18, 1054, 70);
//		pnlContainer.add(pnlHeader);
//		pnlHeader.setLayout(null);
//
//		JLabel lblTitle = new JLabel("Đặt hàng");
//		lblTitle.setBounds(450, 10, 400, 40);
//		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		pnlHeader.add(lblTitle);
//		
//		JPanel pnlThoiGian = new JPanel();
//		pnlThoiGian.setBackground(Utils.secondaryColor);
//		pnlThoiGian.setBounds(770, -4, 295, 85);
//		pnlHeader.add(pnlThoiGian);
//		pnlThoiGian.setLayout(null);
//
//		
//		JPanel pnlCol1 = new JPanel();
//		pnlCol1.setBackground(Utils.secondaryColor);
//		pnlCol1.setBounds(left+650, top+20, 350, 390);
//		top += padding + 5;
//		pnlCol1.setBorder(BorderFactory.createTitledBorder("Thông tin đơn đặt hàng"));
//		add(pnlCol1);
//		pnlCol1.setLayout(null);
//		
//		
//		txtTenKH  = new TextField();
//		txtTenKH.setLabelText("Tên khách hàng:");
//		txtTenKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtTenKH.setBackground(new Color(203, 239, 255));
//		txtTenKH.setBounds(15, 15, 300, 55);
//		pnlCol1.add(txtTenKH);
//		
//		txtSDT  = new TextField();
//		txtSDT.setLabelText("SDT:");
//		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtSDT.setBackground(new Color(203, 239, 255));
//		txtSDT.setBounds(15, 65, 300, 55);
//		pnlCol1.add(txtSDT);
//		
//		txtDiaChi  = new TextField();
//		txtDiaChi.setLabelText("Địa chỉ:");
//		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtDiaChi.setBackground(new Color(203, 239, 255));
//		txtDiaChi.setBounds(15, 115, 300, 55);
//		pnlCol1.add(txtDiaChi);
//		
//		txtEmail  = new TextField();
//		txtEmail.setLabelText("Email:");
//		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtEmail.setBackground(new Color(203, 239, 255));
//		txtEmail.setBounds(15, 165, 300, 55);
//		pnlCol1.add(txtEmail);
//		
//		txtTienShip  = new TextField();
//		txtTienShip.setLabelText("Tiền ship:");
//		txtTienShip.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtTienShip.setBackground(new Color(203, 239, 255));
//		txtTienShip.setBounds(15, 215, 300, 55);
//		pnlCol1.add(txtTienShip);
//		
//		txtTongTien  = new TextField();
//		txtTongTien.setEditable(false);
//		txtTongTien.setLabelText("Tổng tiền hàng:");
//		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtTongTien.setBackground(new Color(203, 239, 255));
//		txtTongTien.setBounds(15, 265, 300, 55);
//		pnlCol1.add(txtTongTien);
//		
//		btnDatHang = new Button("Đặt hàng"); 
//		btnDatHang.setBounds(70, 330, 190, 50);
//		btnDatHang.setIcon(Utils.getImageIcon("buy.png"));
//		btnDatHang.setRadius(8);
//		btnDatHang.setForeground(Color.WHITE);
//		btnDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnDatHang.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnDatHang.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnDatHang.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnDatHang.setBorderColor(new Color(203, 239, 255));
//		btnDatHang.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlCol1.add(btnDatHang);
//		
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 300, 110, 940, 300);
//		scr.setBackground(Utils.primaryColor);
//		scr.getViewport().setBackground(Color.WHITE);
//
//		ScrollBarCustom scp = new ScrollBarCustom();
////		Set color scrollbar thumb
//		scp.setScrollbarColor(new Color(203, 203, 203));
//		scr.setVerticalScrollBar(scp);
//		this.add(scr);
//
//		tbl = new JTable() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//
//			@Override
//			/**
//			 * Set màu từng dòng cho Table
//			 */
//			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//				Component c = super.prepareRenderer(renderer, row, column);
//				if (isRowSelected(row))
//					c.setBackground(Utils.getRGBA(96, 96, 96, 0.5f));
//				else
//					c.setBackground(Color.WHITE);
//				return c;
//			}
//		};
//		tbl.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setAutoCreateRowSorter(true);
//		JTableHeader tblHeader = tbl.getTableHeader();
//		TableColumnModel tableColumnModel = tbl.getColumnModel();
//
//		tableModel = new DefaultTableModel(new String[] {"Mã sản phẫm", "Tên sản phẫm", "Số lượng","Đơn giá","Giảm giá","Thành tiền"}, 0);
//		tbl.setModel(tableModel);
//		tbl.setFocusable(false);
//		tblHeader.setBackground(Utils.primaryColor);
//		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setBackground(Color.WHITE);
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModel.getColumn(0).setPreferredWidth(150);
//		tableColumnModel.getColumn(1).setPreferredWidth(200);
//		tableColumnModel.getColumn(2).setPreferredWidth(150);
//		tableColumnModel.getColumn(3).setPreferredWidth(150);
//		tableColumnModel.getColumn(4).setPreferredWidth(140);
//		tableColumnModel.getColumn(5).setPreferredWidth(150);
//		
//
//		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
//		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setRowHeight(36);
//		scr.setViewportView(tbl);
//		
//		pnlControl = new ControlPanel(Utils.getLeft(700),topPnlControl - 300 , main);
//		this.add(pnlControl);
//		
//		
//		JPanel pnlRow1 = new JPanel();
//		pnlRow1.setBackground(Utils.secondaryColor);
//		pnlRow1.setBounds(left-300, 420, 1100, 50);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//		
//		btnXoa = new Button("Xóa"); 
//		btnXoa.setBounds(0, 0, 170, 40);
//		btnXoa.setIcon(Utils.getImageIcon("delet.png"));
//		btnXoa.setRadius(8);
//		btnXoa.setForeground(Color.WHITE);
//		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnXoa.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnXoa.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnXoa.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnXoa.setBorderColor(new Color(203, 239, 255));
//		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow1.add(btnXoa);
//		
//	
//		JScrollPane scrSP = new JScrollPane();
//		scrSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrSP.setBounds(left - 300, 480, 1300, 250);
//		scrSP.setBackground(Utils.primaryColor);
//		scrSP.getViewport().setBackground(Color.WHITE);
//
//		ScrollBarCustom scpSP = new ScrollBarCustom();
////		Set color scrollbar thumb
//		scpSP.setScrollbarColor(new Color(203, 203, 203));
//		scrSP.setVerticalScrollBar(scpSP);
//		this.add(scrSP);
//
//		tblSP = new JTable() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//
//			@Override
//			/**
//			 * Set màu từng dòng cho Table
//			 */
//			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//				Component c = super.prepareRenderer(renderer, row, column);
//				if (isRowSelected(row))
//					c.setBackground(Utils.getRGBA(96, 96, 96, 0.5f));
//				else
//					c.setBackground(Color.WHITE);
//				return c;
//			}
//		};
//		tblSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tblSP.setAutoCreateRowSorter(true);
//		JTableHeader tblHeaderSP = tblSP.getTableHeader();
//		TableColumnModel tableColumnModelSP = tblSP.getColumnModel();
//
//		tableModelSP = new DefaultTableModel(new String[] { "Mã sản phẫm", "Tên sản phẫm", "Loại","Đơn vị","Nhà cung cấp","Màu sắc","Kích thước"}, 0);
//		tblSP.setModel(tableModelSP);
//		tblSP.setFocusable(false);
//		tblHeaderSP.setBackground(Utils.primaryColor);
//		tblSP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tblSP.setBackground(Color.WHITE);
//		tblSP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModelSP.getColumn(0).setPreferredWidth(200);
//		tableColumnModelSP.getColumn(1).setPreferredWidth(200);
//		tableColumnModelSP.getColumn(2).setPreferredWidth(150);
//		tableColumnModelSP.getColumn(3).setPreferredWidth(200);
//		tableColumnModelSP.getColumn(4).setPreferredWidth(200);
//		tableColumnModelSP.getColumn(5).setPreferredWidth(150);
//		tableColumnModelSP.getColumn(6).setPreferredWidth(200);
//		
//
//		tblHeaderSP.setPreferredSize(new Dimension((int) tblHeaderSP.getPreferredSize().getWidth(), 36));
//		tblHeaderSP.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeaderSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tblSP.setRowHeight(36);
//		scrSP.setViewportView(tblSP);
//		
//		pnlControlSP = new ControlPanel(Utils.getLeft(700),topPnlControl +20 , main);
//		this.add(pnlControlSP);
//		String maNV = "";
//		TaiKhoan_dao = new TaiKhoan_Dao();
//		for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
//			if(maTK.equals(tk.getMaTaiKhoan())) {
//				maNV = tk.getNhanVien().getMaNhanVien();
//				break;
//			}		
//		}
//		NhanVien_dao = new NhanVien_Dao();
//		for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
//			if(maNV.equals(nv.getMaNhanVien())) {
//				txtNguoiLap.setText(nv.getTenNhanVien());
//			}
//		}
//	}
//}
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumnModel;
//
//import com.raven.datechooser.DateChooser;
//
//import components.button.Button;
//import components.controlPanel.ControlPanel;
//import components.radio.RadioButtonCustom;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import utils.Utils;
//
//public class DatHang extends JPanel{
//	private ControlPanel pnlControl;
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	
//	private JPanel pnlMain;
//	private DefaultTableModel modelDSHD;
//	private JTable tblDSHD;
//	private JPanel pnlTTKH;
//	private JLabel lblTenKH;
//	private TextField txtTenKH;
//	private JLabel lblSDT;
//	private TextField txtSDT;
//	private JLabel lblDiaChi;
//	private TextField txtDiaChi;
//	private JLabel lblEmail;
//	private TextField txtEmail;
//	private JLabel lblTienShip;
//	private TextField txtTienShip;
//	private JLabel lblTongTien;
//	private TextField txtTongTien;
//	private Button btnDatHang;
//	private JTable tblSP;
//	private ControlPanel pnlControlSP;
//	private DefaultTableModel tableModelSP;
//	private Button btnXoa;
//	private RadioButtonCustom radNam;
//	private Component radNu;
//	private TextField txtNguoiLap;
//	private TextField txtNgayLap;
//	private DateChooser dateChoose;
//	private TextField txtSoLuong;
//	public DatHang(ManHinhChinh main, String maTK) {
//		
//		this.main = main;
//		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
//		int top = padding;
//		int left = Utils.getLeft(792);
//
//		setBackground(Utils.secondaryColor);
//		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
//		setLayout(null);
//		
//		int topPnlControl = Utils.getBodyHeight() - 80;
//		
//		JPanel pnlContainer = new JPanel();
//		pnlContainer.setBackground(Utils.secondaryColor);
//		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, widthPnlContainer, 80);
//		pnlContainer.setLayout(null);
//		this.add(pnlContainer);
//
//		JPanel pnlHeader = new JPanel();
//		pnlHeader.setBackground(Utils.secondaryColor);
//		pnlHeader.setBounds(16, 18, 1054, 70);
//		pnlContainer.add(pnlHeader);
//		pnlHeader.setLayout(null);
//
//		JLabel lblTitle = new JLabel("Đặt hàng");
//		lblTitle.setBounds(450, 10, 400, 40);
//		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		pnlHeader.add(lblTitle);
//		
//
//
//		
//		JPanel pnlCol1 = new JPanel();
//		pnlCol1.setBackground(Utils.secondaryColor);
//		pnlCol1.setBounds(left+800, top+20, 300, 570);
//		top += padding + 5;
//		pnlCol1.setBorder(BorderFactory.createTitledBorder("Thông tin đơn đặt hàng"));
//		add(pnlCol1);
//		pnlCol1.setLayout(null);
//		
//		
//		txtSDT  = new TextField();
//		txtSDT.setLabelText("Số điện thoại:");
//		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtSDT.setBackground(new Color(203, 239, 255));
//		txtSDT.setBounds(15, 15, 250, 55);
//		pnlCol1.add(txtSDT);
//		
//		txtTenKH  = new TextField();
//		txtTenKH.setLabelText("Tên khách hàng:");
//		txtTenKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtTenKH.setBackground(new Color(203, 239, 255));
//		txtTenKH.setBounds(15, 65, 250, 55);
//		pnlCol1.add(txtTenKH);
//		
//		txtDiaChi  = new TextField();
//		txtDiaChi.setLabelText("Địa chỉ:");
//		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtDiaChi.setBackground(new Color(203, 239, 255));
//		txtDiaChi.setBounds(15, 175, 250, 55);
//		pnlCol1.add(txtDiaChi);
//		
//		txtEmail  = new TextField();
//		txtEmail.setLabelText("Email:");
//		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtEmail.setBackground(new Color(203, 239, 255));
//		txtEmail.setBounds(15, 225, 250, 55);
//		pnlCol1.add(txtEmail);
//		
//		
//		JLabel lblGioiTinh = new JLabel("Giới tính:");
//		lblGioiTinh.setForeground(Utils.labelTextField);
//		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		lblGioiTinh.setBounds(17, 130, 250, 19);
//		pnlCol1.add(lblGioiTinh);
//		
//		JPanel pnlGroupGioiTinh = new JPanel();
//		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
//		pnlGroupGioiTinh.setBounds(15, 156, 250, 55);
//		pnlCol1.add(pnlGroupGioiTinh);
//		pnlGroupGioiTinh.setLayout(null);
//				
//		radNam = new RadioButtonCustom("Nam");
//		radNam.setFocusable(false);
//		radNam.setBackground(Utils.secondaryColor);
//		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		radNam.setBounds(0, -2, 59, 21);
//		radNam.setSelected(true);
//		pnlGroupGioiTinh.add(radNam);
//
//		radNu = new RadioButtonCustom("Nữ");
//		radNu.setFocusable(false);
//		radNu.setBackground(Utils.secondaryColor);
//		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		radNu.setBounds(79, -2, 59, 21);
//		pnlGroupGioiTinh.add(radNu);
//		
//		txtSoLuong  = new TextField();
//		txtSoLuong.setLabelText("Số lượng:");
//		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtSoLuong.setBackground(new Color(203, 239, 255));
//		txtSoLuong.setBounds(15, 375, 250, 55);
//		pnlCol1.add(txtSoLuong);
//		
//		txtTongTien  = new TextField();
//		txtTongTien.setEditable(false);
//		txtTongTien.setLabelText("Tổng tiền hàng:");
//		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtTongTien.setBackground(new Color(203, 239, 255));
//		txtTongTien.setBounds(15, 435, 250, 55);
//		pnlCol1.add(txtTongTien);
//		
//		btnDatHang = new Button("Đặt hàng"); 
//		btnDatHang.setBounds(40, 500, 190, 50);
//		btnDatHang.setIcon(Utils.getImageIcon("buy.png"));
//		btnDatHang.setRadius(8);
//		btnDatHang.setForeground(Color.WHITE);
//		btnDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnDatHang.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnDatHang.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnDatHang.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnDatHang.setBorderColor(new Color(203, 239, 255));
//		btnDatHang.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlCol1.add(btnDatHang);
//		
//		
//		txtNguoiLap = new TextField();
//		txtNguoiLap.setLabelText("Người lập:");
//		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNguoiLap.setBackground(new Color(203, 239, 255));
//		txtNguoiLap.setBounds(15, 265, 250, 55);
//		pnlCol1.add(txtNguoiLap);
//		
//		txtNgayLap = new TextField();
//		txtNgayLap.setIcon(Utils.getImageIcon("add-event 2.png"));
//		txtNgayLap.setLineColor(new Color(149, 166, 248));
//		txtNgayLap.setLabelText("Ngày lập:");
//		txtNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNgayLap.setColumns(10);
//		txtNgayLap.setBackground(new Color(203, 239, 255));
//		txtNgayLap.setBounds(15, 320, 250, 55);
//		pnlCol1.add(txtNgayLap);
//		dateChoose = new DateChooser();
//		dateChoose.setDateFormat("yyyy-MM-dd");
//		dateChoose.setTextRefernce(txtNgayLap);
//		
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 300, 110, 1080, 300);
//		scr.setBackground(Utils.primaryColor);
//		scr.getViewport().setBackground(Color.WHITE);
//
//		ScrollBarCustom scp = new ScrollBarCustom();
////		Set color scrollbar thumb
//		scp.setScrollbarColor(new Color(203, 203, 203));
//		scr.setVerticalScrollBar(scp);
//		this.add(scr);
//
//		tbl = new JTable() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//
//			@Override
//			/**
//			 * Set màu từng dòng cho Table
//			 */
//			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//				Component c = super.prepareRenderer(renderer, row, column);
//				if (isRowSelected(row))
//					c.setBackground(Utils.getRGBA(96, 96, 96, 0.5f));
//				else
//					c.setBackground(Color.WHITE);
//				return c;
//			}
//		};
//		tbl.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setAutoCreateRowSorter(true);
//		JTableHeader tblHeader = tbl.getTableHeader();
//		TableColumnModel tableColumnModel = tbl.getColumnModel();
//
//		tableModel = new DefaultTableModel(new String[] {"Mã sản phẫm", "Tên sản phẫm", "Số lượng","Đơn giá","Giảm giá","Thành tiền"}, 0);
//		tbl.setModel(tableModel);
//		tbl.setFocusable(false);
//		tblHeader.setBackground(Utils.primaryColor);
//		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setBackground(Color.WHITE);
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModel.getColumn(0).setPreferredWidth(200);
//		tableColumnModel.getColumn(1).setPreferredWidth(200);
//		tableColumnModel.getColumn(2).setPreferredWidth(150);
//		tableColumnModel.getColumn(3).setPreferredWidth(150);
//		tableColumnModel.getColumn(4).setPreferredWidth(200);
//		tableColumnModel.getColumn(5).setPreferredWidth(200);
//		
//
//		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
//		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setRowHeight(36);
//		scr.setViewportView(tbl);
//		
////		pnlControl = new ControlPanel(Utils.getLeft(700),topPnlControl - 300 , main);
////		this.add(pnlControl);
//		
//		
//		JPanel pnlRow1 = new JPanel();
//		pnlRow1.setBackground(Utils.secondaryColor);
//		pnlRow1.setBounds(left-300, 420, 1100, 50);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//		
//		btnXoa = new Button("Xóa"); 
//		btnXoa.setBounds(0, 0, 170, 40);
//		btnXoa.setIcon(Utils.getImageIcon("delet.png"));
//		btnXoa.setRadius(8);
//		btnXoa.setForeground(Color.WHITE);
//		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnXoa.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnXoa.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnXoa.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnXoa.setBorderColor(new Color(203, 239, 255));
//		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow1.add(btnXoa);
//		
//	
//		JScrollPane scrSP = new JScrollPane();
//		scrSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrSP.setBounds(left - 300, 480, 1080, 250);
//		scrSP.setBackground(Utils.primaryColor);
//		scrSP.getViewport().setBackground(Color.WHITE);
//
//		ScrollBarCustom scpSP = new ScrollBarCustom();
////		Set color scrollbar thumb
//		scpSP.setScrollbarColor(new Color(203, 203, 203));
//		scrSP.setVerticalScrollBar(scpSP);
//		this.add(scrSP);
//
//		tblSP = new JTable() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//
//			@Override
//			/**
//			 * Set màu từng dòng cho Table
//			 */
//			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//				Component c = super.prepareRenderer(renderer, row, column);
//				if (isRowSelected(row))
//					c.setBackground(Utils.getRGBA(96, 96, 96, 0.5f));
//				else
//					c.setBackground(Color.WHITE);
//				return c;
//			}
//		};
//		tblSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tblSP.setAutoCreateRowSorter(true);
//		JTableHeader tblHeaderSP = tblSP.getTableHeader();
//		TableColumnModel tableColumnModelSP = tblSP.getColumnModel();
//
//		tableModelSP = new DefaultTableModel(new String[] { "Mã sản phẫm", "Tên sản phẫm", "Loại","Đơn vị","Nhà cung cấp","Màu sắc","Kích thước","Số lượng"}, 0);
//		tblSP.setModel(tableModelSP);
//		tblSP.setFocusable(false);
//		tblHeaderSP.setBackground(Utils.primaryColor);
//		tblSP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tblSP.setBackground(Color.WHITE);
//		tblSP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModelSP.getColumn(0).setPreferredWidth(150);
//		tableColumnModelSP.getColumn(1).setPreferredWidth(150);
//		tableColumnModelSP.getColumn(2).setPreferredWidth(100);
//		tableColumnModelSP.getColumn(3).setPreferredWidth(100);
//		tableColumnModelSP.getColumn(4).setPreferredWidth(180);
//		tableColumnModelSP.getColumn(5).setPreferredWidth(150);
//		tableColumnModelSP.getColumn(6).setPreferredWidth(150);
//		tableColumnModelSP.getColumn(7).setPreferredWidth(100);
//
//		tblHeaderSP.setPreferredSize(new Dimension((int) tblHeaderSP.getPreferredSize().getWidth(), 36));
//		tblHeaderSP.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeaderSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tblSP.setRowHeight(36);
//		scrSP.setViewportView(tblSP);
//		
////		pnlControlSP = new ControlPanel(Utils.getLeft(700),topPnlControl +20 , main);
////		this.add(pnlControlSP);
//	}
//}
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.raven.datechooser.DateChooser;
import components.notification.Notification;
import components.notification.Notification.Type;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.radio.RadioButtonCustom;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietDatHangDAO;
import dao.ChiTietKhuyenMaiDAO;
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

public class DatHang extends JPanel implements ActionListener{
	private ControlPanel pnlControl;
	public DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	
	private JPanel pnlMain;
	private DefaultTableModel modelDSHD;
	private JTable tblDSHD;
	private JPanel pnlTTKH;
	private JLabel lblTenKH;
	private TextField txtTenKH;
	private JLabel lblSDT;
	private TextField txtSDT;
	private JLabel lblDiaChi;
	private TextField txtDiaChi;
	private JLabel lblEmail;
	private TextField txtEmail;
	private JLabel lblTienShip;
	private TextField txtTienShip;
	private JLabel lblTongTien;
	public TextField txtTongTien;
	private Button btnDatHang;
	private JTable tblSP;
	private ControlPanel pnlControlSP;
	private DefaultTableModel tableModelSP;
	private Button btnXoa;
	private Button btnSDT;
	private RadioButtonCustom radNam;
	private RadioButtonCustom radNu;
	private TextField txtSoLuong;
	private TextField txtNguoiLap;
	private TextField txtNgayLap;
	private DateChooser dateChoose;
	private Button btnThem;
	private String maTK;
	private ArrayList<KhachHang> dsKH;
	private KhachHangDAO KhachHang_dao;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private ArrayList<LoaiSanPham> dsLSP;
	private ArrayList<KichThuoc> dsKT;
	private ArrayList<MauSac> dsMS;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private KichThuocDAO KichThuoc_dao;
	private MauSacDAO MauSac_dao;
	private SanPhamDAO SanPham_dao;
	private PhieuDatHangDAO PhieuDatHang_dao;
	private ChiTietDatHangDAO ChiTietPhieuDatHang_dao;
	private Double tongtien;
	private ChiTietKhuyenMaiDAO ChiTietKhuyenMai_dao;
	private KhuyenMaiDAO KhuyenMai_Dao;
	private Button btnScan;
	private NumberFormat format;
	public DatHang(ManHinhChinh main, String maTK) throws RemoteException, MalformedURLException, NotBoundException {
		this.main = main;
		this.maTK = maTK;
		tongtien = 0.0;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		PhieuDatHang_dao = (PhieuDatHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/phieuDatHangDao");
		ChiTietPhieuDatHang_dao = (ChiTietDatHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietDatHangDao");
		ChiTietKhuyenMai_dao = (ChiTietKhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietKhuyenMaiDao");
		KhuyenMai_Dao = (KhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khuyenMaiDao");
		LoaiSanPham_dao = (LoaiSanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/loaiSanPhamDao");
		KichThuoc_dao = (KichThuocDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/kichThuocDao");
		MauSac_dao = (MauSacDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/mauSacDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/sanPhamDao");
		TaiKhoan_dao = (TaiKhoanDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/taiKhoanDao");
		NhanVien_dao = (NhanVienDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/nhanVienDao");
		KhachHang_dao = (KhachHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khachHangDao");
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

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.secondaryColor);
		pnlHeader.setBounds(16, 18, 1054, 70);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Đặt hàng");
		lblTitle.setBounds(450, 10, 400, 40);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);
		
		JPanel pnlCol1 = new JPanel();
		pnlCol1.setBackground(Utils.secondaryColor);
		pnlCol1.setBounds(left+500, top+20, 600, 350);
		top += padding + 5;
		pnlCol1.setBorder(BorderFactory.createTitledBorder("Thông tin đơn đặt hàng"));
		add(pnlCol1);
		pnlCol1.setLayout(null);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(5, 15, 550, 55);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);
		pnlCol1.add(pnlRow1);
		
		txtSDT  = new TextField();
		txtSDT.setLabelText("Số điện thoại:");
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setBackground(new Color(203, 239, 255));
		txtSDT.setBounds(0, 0, 205, 55);
		pnlRow1.add(txtSDT);
		
		btnSDT = new Button(""); 
		btnSDT.setBounds(220, 15, 40, 40);
		btnSDT.setIcon(Utils.getImageIcon("sucessNotification.png"));
		btnSDT.setRadius(8);
		btnSDT.setForeground(Color.WHITE);
		btnSDT.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnSDT.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnSDT.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnSDT.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnSDT.setBorderColor(new Color(203, 239, 255));
		btnSDT.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnSDT);
		
		txtTenKH  = new TextField();
		txtTenKH.setEditable(false);
		txtTenKH.setLabelText("Tên khách hàng:");
		txtTenKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKH.setBackground(new Color(203, 239, 255));
		txtTenKH.setBounds(300, 0, 205, 55);
		pnlRow1.add(txtTenKH);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(5, 75, 550, 55);
		top += padding + 5;
		add(pnlRow2);
		pnlRow2.setLayout(null);
		pnlCol1.add(pnlRow2);
		
		txtDiaChi  = new TextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setLabelText("Địa chỉ:");
		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChi.setBackground(new Color(203, 239, 255));
		txtDiaChi.setBounds(0, 0, 205, 55);
		pnlRow2.add(txtDiaChi);
		
		txtEmail  = new TextField();
		txtEmail.setEditable(false);
		txtEmail.setLabelText("Email:");
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtEmail.setBackground(new Color(203, 239, 255));
		txtEmail.setBounds(300, 0, 205, 55);
		pnlRow2.add(txtEmail);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(5, 140, 550, 55);
		top += padding + 5;
		add(pnlRow3);
		pnlRow3.setLayout(null);
		pnlCol1.add(pnlRow3);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(0, 0, 150, 55);
		pnlRow3.add(lblGioiTinh);
		
		JPanel pnlGroupGioiTinh = new JPanel();
		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
		pnlGroupGioiTinh.setBounds(90, 0, 250, 55);
		pnlRow3.add(pnlGroupGioiTinh);
		pnlGroupGioiTinh.setLayout(null);
				
		radNam = new RadioButtonCustom("Nam");
		radNam.setFocusable(false);
		radNam.setBackground(Utils.secondaryColor);
		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNam.setBounds(0, 15, 59, 21);
		radNam.setSelected(true);
		pnlGroupGioiTinh.add(radNam);

		radNu = new RadioButtonCustom("Nữ");
		radNu.setFocusable(false);
		radNu.setBackground(Utils.secondaryColor);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		radNu.setBounds(79, 15, 59, 21);
		pnlGroupGioiTinh.add(radNu);
		
		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(5, 185, 550, 55);
		top += padding + 5;
		add(pnlRow4);
		pnlRow4.setLayout(null);
		pnlCol1.add(pnlRow4);
			

		
		txtNguoiLap = new TextField();
		txtNguoiLap.setLabelText("Người lập:");
		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNguoiLap.setBackground(new Color(203, 239, 255));
		txtNguoiLap.setBounds(0, 0, 205, 55);
		pnlRow4.add(txtNguoiLap);
		
		
		txtNgayLap = new TextField();
		txtNgayLap.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayLap.setLineColor(new Color(149, 166, 248));
		txtNgayLap.setLabelText("Ngày lập:");
		txtNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayLap.setColumns(10);
		txtNgayLap.setBackground(new Color(203, 239, 255));
		txtNgayLap.setBounds(300, 0, 205, 55);
		pnlRow4.add(txtNgayLap);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayLap);
		
		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(5, 250, 550, 75);
		top += padding + 5;
		add(pnlRow5);
		pnlRow5.setLayout(null);
		pnlCol1.add(pnlRow5);
		
		txtTongTien  = new TextField();
		txtTongTien.setEditable(false);
		txtTongTien.setLabelText("Tổng tiền hàng:");
		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTongTien.setBackground(new Color(203, 239, 255));
		txtTongTien.setBounds(0, 0, 205, 55);
		pnlRow5.add(txtTongTien);
		
		btnDatHang = new Button("Đặt hàng"); 
		btnDatHang.setBounds(300, 10, 190, 50);
		btnDatHang.setIcon(Utils.getImageIcon("buy.png"));
		btnDatHang.setRadius(8);
		btnDatHang.setForeground(Color.WHITE);
		btnDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnDatHang.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDatHang.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDatHang.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDatHang.setBorderColor(new Color(203, 239, 255));
		btnDatHang.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow5.add(btnDatHang);
		
		
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left - 300, 110, 790, 250);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu đặt hàng"));
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

		tableModel = new DefaultTableModel(new String[] {"Mã sản phẫm", "Tên sản phẫm", "Số lượng","Đơn giá","Giảm giá","Thành tiền"}, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(150);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(100);
		tableColumnModel.getColumn(5).setPreferredWidth(200);
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
				txtSoLuong.setText(tbl.getValueAt(row, 2).toString());
				moKhoaButtonSP();
			}
		});
		

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
//		pnlControl = new ControlPanel(Utils.getLeft(700),topPnlControl - 300 , main);
//		this.add(pnlControl);
		
		
		JPanel pnlRowBtn = new JPanel();
		pnlRowBtn.setBackground(Utils.secondaryColor);
		pnlRowBtn.setBounds(left-300, 360, 1100, 55);
		top += padding + 5;
		add(pnlRowBtn);
		pnlRowBtn.setLayout(null);
		
		btnThem = new Button("Thêm"); 
		btnThem.setBounds(150, 15, 140, 40);
		btnThem.setIcon(Utils.getImageIcon("add 1.png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRowBtn.add(btnThem);
		
		btnXoa = new Button("Xóa"); 
		btnXoa.setBounds(300, 15, 140, 40);
		btnXoa.setIcon(Utils.getImageIcon("delet.png"));
		btnXoa.setRadius(8);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnXoa.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXoa.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXoa.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXoa.setBorderColor(new Color(203, 239, 255));
		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRowBtn.add(btnXoa);
		
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
		btnScan.setBounds(450, 15, 280, 40);
		pnlRowBtn.add(btnScan);
		
		txtSoLuong  = new TextField();
		txtSoLuong.setLabelText("Số lượng:");
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBackground(new Color(203, 239, 255));
		txtSoLuong.setBounds(0, 0, 150, 55);
		pnlRowBtn.add(txtSoLuong);
	
		JScrollPane scrSP = new JScrollPane();
		scrSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrSP.setBounds(left - 300, 440, 1400, 280);
		scrSP.setBackground(Utils.primaryColor);
		scrSP.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
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
		tblSP.setAutoCreateRowSorter(true);
		JTableHeader tblHeaderSP = tblSP.getTableHeader();
		TableColumnModel tableColumnModelSP = tblSP.getColumnModel();

		tableModelSP = new DefaultTableModel(new String[] { "Mã sản phẫm", "Tên sản phẫm", "Đơn giá","Nhà cung cấp","Màu sắc","Kích thước","Loại sản phẩm","Đơn vị",
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
				moKhoaButtonSP();
				txtSoLuong.setText("");
			}
		});
		
		tblHeaderSP.setPreferredSize(new Dimension((int) tblHeaderSP.getPreferredSize().getWidth(), 36));
		tblHeaderSP.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblSP.setRowHeight(36);
		scrSP.setViewportView(tblSP);
		
//		pnlControlSP = new ControlPanel(Utils.getLeft(700),topPnlControl +20 , main);
//		this.add(pnlControlSP);
		dsKH = new ArrayList<KhachHang>();
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			dsKH.add(kh);
		}
		String maNV = "";
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
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()*120/100), sp.getNhaCungCap(),
					sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
			tableModelSP.addRow(data);
		}
		btnDatHang.addActionListener(this);
		btnSDT.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnScan.addActionListener(this);
		khoaButtonSP();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnSDT)) {
			String sdt = txtSDT.getText();
			if(sdt.equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
			else {
					boolean kq_tt = false;
					for (KhachHang kh : dsKH) {
						if(sdt.equals(kh.getSoDienThoai())) {
							kq_tt = true;
							txtTenKH.setText(kh.getTenKhachHang());
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
//						JOptionPane.showMessageDialog(this, "Khách hàng này chưa có trong danh sách khách hàng, vui lòng thêm thông tin khách hàng vào hệ thống");
//						String title = "Quản lý khách hàng";
//						QuanLyKhachHang qlkh = new QuanLyKhachHang(main);
//						qlkh.txtPhone.setText(sdt);
//						main.addPnlBody(qlkh, title, 2, 1);
					}	
			}
			
		}
		else if(src.equals(btnThem)) {
			String soluong = txtSoLuong.getText();
			if(validateProductInput()){
				int row = tblSP.getSelectedRow();
				int totalRow = tbl.getRowCount();
				if(Integer.valueOf(tblSP.getValueAt(row, 8).toString()) < Integer.valueOf(soluong)) {
					JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
					txtSoLuong.setText("");
					txtSoLuong.requestFocus();
				}
				else {
					Boolean tontai = false;
					int slmoi = 0;
					Double phantram = 0.0;
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
					Double dongia = chuyenDoiFormat(tblSP.getValueAt(row, 2).toString());
					if(phantram != 0)
						dongia -= dongia*phantram/100;
					Double thanhTien = Integer.valueOf(soluong)* (dongia);
					for(int i = 0;i < totalRow;i++) {
						if(tbl.getValueAt(i, 0).toString().equals(tblSP.getValueAt(row, 0).toString())) {
							tontai = true;
							slmoi = Integer.valueOf(soluong)+Integer.valueOf(tbl.getValueAt(i, 2).toString());
							tbl.setValueAt(slmoi, i, 2);
							thanhTien = slmoi*dongia;
							tongtien += Integer.valueOf(soluong)*dongia;
							tbl.setValueAt(format.format(thanhTien), i, 5);
							txtSoLuong.setText("");
							tblSP.clearSelection();
							khoaButtonSP();
							break;
						}
					}	
					if(tontai == false) {
						tongtien+= thanhTien;
						Object[] data = {tblSP.getValueAt(row, 0),tblSP.getValueAt(row, 1),
								soluong,format.format(dongia),phantram, format.format(thanhTien)};
						tableModel.addRow(data);
						txtSoLuong.setText("");
						tblSP.clearSelection();
						khoaButtonSP();
					}
					txtTongTien.setText(format.format(tongtien));
				}		
			}	
//			if(txtSoLuong.getText().equals("")) {
//				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ");
//				txtSoLuong.requestFocus();
//			}
//			else {
//				int row = tblSP.getSelectedRow();
//				String masp = tblSP.getValueAt(row, 0).toString();
//				String tensp = tblSP.getValueAt(row, 1).toString();
//				int sl = Integer.valueOf(txtSoLuong.getText());
//				Double dongia =Double.valueOf(tblSP.getValueAt(row, 2).toString())*120/100;
//				Double thanhTien = sl*dongia;
//				tongtien += thanhTien;
//				Object[] data = {masp,tensp,sl, dongia,0,thanhTien};
//				tableModel.addRow(data);
//				txtTongTien.setText(tongtien.toString());
//				txtSoLuong.setText("");
//				khoaButtonSP();
//				tblSP.clearSelection();
//			}
		}
		else if(src.equals(btnXoa)) {
			int row = tbl.getSelectedRow();
			if(row != -1) {
				Double tt = chuyenDoiFormat(tbl.getValueAt(row, 5).toString());
				tableModel.removeRow(row);
				tongtien-= tt;
				txtTongTien.setText(format.format(tongtien));
				txtSoLuong.setText("");
				khoaButtonSP();
				tbl.clearSelection();
			}
			else
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
		}
		else if(src.equals(btnDatHang)) {
			if(tableModel.getRowCount() == 0 || txtSDT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "vui lòng nhập thông tin đầy đủ");
			}
			else {
				int totalRow = tableModel.getRowCount();
				String sdt = txtSDT.getText();
				String mapdh = "";
				try {
					mapdh = taoMaPhieuDatHang();
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
				try {
					for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
						if(maTK.equals(tk.getMaTaiKhoan())) {
							nguoilap = tk.getNhanVien().getMaNhanVien();
							break;
						}		
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					String date[] = txtNgayLap.getText().split("-");
					NhanVien nv = NhanVien_dao.getNhanVien(nguoilap);
					KhachHang kh = KhachHang_dao.getKhachHang(khachhang);
					PhieuDatHang pdh = new PhieuDatHang(mapdh, LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]))
							,nv, kh);
					if(PhieuDatHang_dao.create(pdh)) {
						for(int i = 0; i < totalRow;i++) {
							String masp = tbl.getValueAt(i, 0).toString();
							SanPham sp = SanPham_dao.getSanPham(masp);
							int soluong = Integer.valueOf(tbl.getValueAt(i, 2).toString());
							Double dongia = chuyenDoiFormat(tbl.getValueAt(i, 3).toString());
							ChiTietPhieuDatHang ctpdh = new ChiTietPhieuDatHang(pdh,
									sp, soluong, dongia);
//						int soLuongCapNhat=0;
//						for (SanPham sp : SanPham_dao.getAllSanPham()) {
//							if(sp.getMaSanPham().equals(masp))
//								soLuongCapNhat = sp.getSoLuong()- soluong;
//						}
							ChiTietPhieuDatHang_dao.create(ctpdh);
								
//							SanPham_dao.UpdateSoLuong(masp, soLuongCapNhat);
								
						}
						JOptionPane.showMessageDialog(this, "Lập phiếu đạt hàng thành công");
						refresh();
						tableModelSP.setRowCount(0);
						for (SanPham sp : SanPham_dao.getAllSanPham()) {
							Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()*120/100), sp.getNhaCungCap(),
									sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
							tableModelSP.addRow(data);
						}
					}
				} catch (NumberFormatException | HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
//			JOptionPane.showMessageDialog(this, taoMaPhieuDatHang());
		}
		else if(src.equals(btnScan)) {
			Form_ScanDatHang FScan = null;
			try {
				FScan = new Form_ScanDatHang(this);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FScan.setVisible(true);
		}
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
	public void khoaButtonSP() {
		btnThem.setEnabled(false);
		btnXoa.setEnabled(false);
	}
	public void moKhoaButtonSP() {
		btnThem.setEnabled(true);
		btnXoa.setEnabled(true);
	}
	public void refresh() {
		txtSDT.setText("");
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		radNam.setSelected(false);
		radNu.setSelected(false);
		txtNgayLap.setText(LocalDate.now().toString());
		txtTongTien.setText("");
		txtSoLuong.setText("");
		khoaButtonSP();
		tableModel.setRowCount(0);
		tblSP.clearSelection();
	}
	public String taoMaPhieuDatHang() throws RemoteException {
		ArrayList<String> dsmaPDH = new ArrayList<String>();
		for (PhieuDatHang pdh : PhieuDatHang_dao.getAllPhieuDatHang()) {
			dsmaPDH.add(pdh.getMaPhieuDatHang());
		}
		for(int i = 1;i <= 1000;i++) {
			String ma = "";
			if(i<10)
				ma = "DH000"+i;
			else if(i<100)
				ma = "DH00"+i;
			else if(i<1000)
				ma = "DH0"+i;
			else
				ma = "DH1000";
			if(dsmaPDH.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}
	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
//		txt.setError(true);
		txt.selectAll();
		txt.requestFocus();
		return false;
	}
//
//	private boolean validator() {
//		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
//		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";
//
//		String ten = txtTenKH.getText().trim();
//		if (ten.length() <= 0)
//			return showThongBaoLoi(txtTenKH, "Vui lòng nhập tên khách hàng");
//
//		if (Pattern.matches(String.format(".*[^%s%s ].*", vietNamese, vietNameseLower), ten))
//			return showThongBaoLoi(txtTenKH, "tên chỉ chứa các ký tự chữ cái");
//
//		String diaChi = txtDiaChi.getText().trim();
//		if (diaChi.length() <= 0)
//			return showThongBaoLoi(txtDiaChi, "Vui lòng nhập địa chỉ");
//		if (Pattern.matches(String.format(".*[^%s%s ].*", vietNamese, vietNameseLower), diaChi))
//			return showThongBaoLoi(txtDiaChi, "địa chỉ chỉ chứa các ký tự chữ cái");
//
//		String email = txtEmail.getText().trim();
//		if (email.length() <= 0)
//			return showThongBaoLoi(txtEmail, "Vui lòng nhập email");
//		
//		String nguoiLap = txtNguoiLap.getText().trim();
//		if (nguoiLap.length() <= 0)
//			return showThongBaoLoi(txtNguoiLap, "Vui lòng nhập người lập");
//		
//		return true;
//	}
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
