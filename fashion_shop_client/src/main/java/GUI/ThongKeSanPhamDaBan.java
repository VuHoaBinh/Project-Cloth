package GUI;

//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
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
//import components.comboBox.ComboBox;
//import components.controlPanel.ControlPanel;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import utils.Utils;
//
//public class ThongKeSanPhamDaBan extends JPanel{
//	private ControlPanel pnlControl;
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	
//	private JPanel pnlMain;
//	private TextField txtNgayBatDau;
//	private TextField txtNgayKetThuc;
//	private JComboBox<String> cmbLoc;
//	private Button btnThongKe;
//	private DefaultTableModel modelTKSP;
//	private JTable tblTKSP;
//	
//	public ThongKeSanPhamDaBan(ManHinhChinh main) {
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
//		JLabel lblTitle = new JLabel("Thống Kê Sản Phẫm Đã Bán");
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
//		JPanel pnlRow1 = new JPanel();
//		pnlRow1.setBackground(Utils.secondaryColor);
//		pnlRow1.setBounds(left-150, top+20, 1100, 70);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//		
//		txtNgayBatDau  = new TextField();
//		txtNgayBatDau.setLabelText("Thống kê từ ngày:");
//		txtNgayBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNgayBatDau.setBackground(new Color(203, 239, 255));
//		txtNgayBatDau.setBounds(30, 0, 200, 55);
//		pnlRow1.add(txtNgayBatDau);
//
//
//		txtNgayKetThuc  = new TextField();
//		txtNgayKetThuc.setLabelText("đến ngày:");
//		txtNgayKetThuc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNgayKetThuc.setBackground(new Color(203, 239, 255));
//		txtNgayKetThuc.setBounds(270, 0, 200, 55);	
//		pnlRow1.add(txtNgayKetThuc);
//		
//		
//		JLabel lblLoc = new JLabel("Lọc theo:");
//		lblLoc.setForeground(Utils.labelTextField);
//		lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		lblLoc.setBounds(500, 10, 100, 65);
//		pnlRow1.add(lblLoc);
//		
//		cmbLoc = new ComboBox<>();
//		cmbLoc.setModel(new DefaultComboBoxModel<String>());
//		cmbLoc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		cmbLoc.setBackground(new Color(140, 177, 180));
//		cmbLoc.setBounds(580, 20, 220, 30);
//		pnlRow1.add(cmbLoc);
//		
//		btnThongKe = new Button("Thống kê"); 
//		btnThongKe.setBounds(820, 10, 180, 40);
//		btnThongKe.setIcon(Utils.getImageIcon("tkdoanhthu.png"));
//		btnThongKe.setRadius(8);
//		btnThongKe.setForeground(Color.WHITE);
//		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnThongKe.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnThongKe.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnThongKe.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnThongKe.setBorderColor(new Color(203, 239, 255));
//		btnThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow1.add(btnThongKe);
//		
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 200, 230, 1100, 300);
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
//		tableModel = new DefaultTableModel(new String[] { "Mã sản phẫm", "Tên sản phẫm", "Số lượng", "Giá bán"}, 0);
//		tbl.setModel(tableModel);
//		tbl.setFocusable(false);
//		tblHeader.setBackground(Utils.primaryColor);
//		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setBackground(Color.WHITE);
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModel.getColumn(0).setPreferredWidth(350);
//		tableColumnModel.getColumn(1).setPreferredWidth(250);
//		tableColumnModel.getColumn(2).setPreferredWidth(250);
//		tableColumnModel.getColumn(3).setPreferredWidth(250);
//
//		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
//		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setRowHeight(36);
//		scr.setViewportView(tbl);

//		pnlControl = new ControlPanel(Utils.getLeft(400),topPnlControl - 160 , main);
//		this.add(pnlControl);
//	}
//}
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Desktop;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.NumberFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//import javax.swing.ButtonGroup;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JComboBox;
//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileFilter;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumnModel;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import ConnectDB.ConnectDB;
//import components.button.Button;
//import components.comboBox.ComboBox;
//import components.controlPanel.ControlPanel;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import dao.NhanVien_Dao;
//import dao.SanPham_Dao;
//import dao.TaiKhoan_Dao;
//import entity.NhanVien;
//import entity.SanPham;
//import entity.TaiKhoan;
//import utils.Utils;
//
//public class ThongKeSanPhamDaBan extends JPanel implements ActionListener {
//	private ControlPanel pnlControl;
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//
//	private JPanel pnlMain;
//	private TextField txtNgayBatDau;
//	private TextField txtNgayKetThuc;
//	private JComboBox<String> cmbLoc;
//	private Button btnThongKe;
//	private DefaultTableModel modelTKSP;
//	private JTable tblTKSP;
//	private ArrayList<SanPham> dsSP;
//	private SanPham_Dao sanPham_DAO = new SanPham_Dao();
//
//	private JRadioButton rdoNgay;
//	private JRadioButton rdoThang;
//	private JRadioButton rdoNam;
//	private TextField txtNgay;
//	private TextField txtThang;
//	private TextField txtNam;
//	private Button btnBack;
//	private String maTK;
//	private TaiKhoan_Dao TaiKhoan_dao;
//	private NhanVien_Dao NhanVien_dao;
//	private Button btnXuatFilePDF;
//	public ThongKeSanPhamDaBan(ManHinhChinh main, String maTK) {
//		try {
//			ConnectDB.getInstance().connect();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		this.main = main;
//		this.maTK = maTK;
//		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
//		int top = padding;
//		int left = Utils.getLeft(792);
//		
//		setBackground(Utils.secondaryColor);
//		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
//		setLayout(null);
//		TaiKhoan_dao = new TaiKhoan_Dao();
//		NhanVien_dao = new NhanVien_Dao();
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
//		JLabel lblTitle = new JLabel("Thống Kê Quần Áo Đã Bán");
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
//		JPanel pnlRow1 = new JPanel();
//		pnlRow1.setBackground(Utils.secondaryColor);
//		pnlRow1.setBounds(left - 150, top + 20, 1400, 120);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//
//		txtNgay = new TextField();
//		txtNgay.setLabelText("Ngày:");
//		txtNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNgay.setBackground(new Color(203, 239, 255));
//		txtNgay.setBounds(30, 0, 200, 55);
//		rdoNgay = new JRadioButton();
//		rdoNgay.setBounds(230, 30, 25, 25);
//		rdoNgay.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtNgay);
//		pnlRow1.add(rdoNgay);
//
//		txtThang = new TextField();
//		txtThang.setLabelText("Tháng:");
//		txtThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtThang.setBackground(new Color(203, 239, 255));
//		txtThang.setBounds(270, 0, 200, 55);
//		rdoThang = new JRadioButton();
//		rdoThang.setBounds(470, 30, 25, 25);
//		rdoThang.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtThang);
//		pnlRow1.add(rdoThang);
//
//		txtNam = new TextField();
//		txtNam.setLabelText("Năm:");
//		txtNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNam.setBackground(new Color(203, 239, 255));
//		txtNam.setBounds(520, 0, 200, 55);
//		rdoNam = new JRadioButton();
//		rdoNam.setBounds(730, 30, 25, 25);
//		rdoNam.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtNam);
//		pnlRow1.add(rdoNam);
//
////		JLabel lblLoc = new JLabel("Lọc theo:");
////		lblLoc.setForeground(Utils.labelTextField);
////		lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
////		lblLoc.setBounds(500, 10, 100, 65);
////		pnlRow1.add(lblLoc);
////		
////		cmbLoc = new ComboBox<>();
////		cmbLoc.setModel(new DefaultComboBoxModel<String>());
////		cmbLoc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
////		cmbLoc.setBackground(new Color(140, 177, 180));
////		cmbLoc.setBounds(580, 20, 220, 30);
////		pnlRow1.add(cmbLoc);
//
//		btnThongKe = new Button("Thống kê");
//		btnThongKe.setBounds(820, 10, 180, 40);
//		btnThongKe.setIcon(Utils.getImageIcon("tkdoanhthu.png"));
//		btnThongKe.setRadius(8);
//		btnThongKe.setForeground(Color.WHITE);
//		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnThongKe.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnThongKe.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnThongKe.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnThongKe.setBorderColor(new Color(203, 239, 255));
//		btnThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow1.add(btnThongKe);
//
//		btnBack = new Button("");
//		btnBack.setBounds(1020, 10, 80, 40);
//		btnBack.setIcon(Utils.getImageIcon("back.png"));
//		btnBack.setRadius(8);
//		btnBack.setForeground(Color.WHITE);
//		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnBack.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnBack.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnBack.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnBack.setBorderColor(new Color(203, 239, 255));
//		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow1.add(btnBack);
//		
//		btnXuatFilePDF = new Button("Xuất File PDF");
//		btnXuatFilePDF.setBounds(820, 55, 150, 40);
//		btnXuatFilePDF.setIcon(Utils.getImageIcon("pdf.png"));
//		btnXuatFilePDF.setRadius(8);
//		btnXuatFilePDF.setForeground(Color.WHITE);
//		btnXuatFilePDF.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		btnXuatFilePDF.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnXuatFilePDF.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnXuatFilePDF.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnXuatFilePDF.setBorderColor(new Color(203, 239, 255));
//		btnXuatFilePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow1.add(btnXuatFilePDF);
//
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 200, 230, 1250, 500);
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
//		tableModel = new DefaultTableModel(
//				new String[] { "Mã sản phẫm", "Tên sản phẫm", "Số lượng đã bán", "Giá bán", "Thành tiền","Ngày lập" }, 0);
//		tbl.setModel(tableModel);
//		tbl.setFocusable(false);
//		tblHeader.setBackground(Utils.primaryColor);
//		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setBackground(Color.WHITE);
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
////		dsSP = new ArrayList<SanPham>();
////		for (SanPham sp : sanPham_DAO.getAllSanPham()) {
////			Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getSoLuong() , sp.getDonGia()};
////			tableModel.addRow(data);
////		}
//		getHD();
//
//		tableColumnModel.getColumn(0).setPreferredWidth(200);
//		tableColumnModel.getColumn(1).setPreferredWidth(250);
//		tableColumnModel.getColumn(2).setPreferredWidth(200);
//		tableColumnModel.getColumn(3).setPreferredWidth(200);
//		tableColumnModel.getColumn(4).setPreferredWidth(200);
//		tableColumnModel.getColumn(5).setPreferredWidth(200);
//
//		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
//		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setRowHeight(50);
//		scr.setViewportView(tbl);
//
//		btnThongKe.addActionListener(this);
//		btnBack.addActionListener(this);
//		btnXuatFilePDF.addActionListener(this);
//		rdoNgay.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				txtNgay.setEditable(true);
//				txtThang.setEditable(true);
//				txtNam.setEditable(true);
//			}
//		});
//
//		rdoThang.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				txtNgay.setEditable(false);
//				txtThang.setEditable(true);
//				txtNam.setEditable(true);
//			}
//		});
//		rdoNam.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				txtNgay.setEditable(false);
//				txtThang.setEditable(false);
//				txtNam.setEditable(true);
//			}
//		});
//		ButtonGroup buttonGroup = new ButtonGroup();
//		buttonGroup.add(rdoNgay);
//		buttonGroup.add(rdoThang);
//		buttonGroup.add(rdoNam);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		Object src = e.getSource();
//		if (src.equals(btnThongKe) && rdoNam.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraNam()) {
//				getHD();
//				ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//				int i = 0;
//				int rowcount = tableModel.getRowCount();
//				while (i < rowcount) {
//					int year = layNam(tbl.getValueAt(i, 5).toString());
////					JOptionPane.showMessageDialog(this, year);
//					int txtnam = Integer.parseInt(txtNam.getText());
//					if (year != txtnam) {
//						dsxoa.add(i);
//					}
//					i++;
//				}
//				for (int y = dsxoa.size() - 1; y >= 0; y--) {
//					tableModel.removeRow(dsxoa.get(y));
//				}
//			}
//		} else if (src.equals(btnThongKe) && rdoThang.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraThang() && ktraNam()) {
//				getHD();
//				ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//				int i = 0;
//				int rowcount = tableModel.getRowCount();
//				while (i < rowcount) {
//					int year = layNam(tbl.getValueAt(i, 5).toString());
//					int month = layThang(tbl.getValueAt(i, 5).toString());
////					int txtnam = Integer.parseInt(txtNam.getText());
//					int txtthang = Integer.parseInt(txtThang.getText());
//					if (month != txtthang) {
//						dsxoa.add(i);
//					}
//					i++;
//				}
//				for (int y = dsxoa.size() - 1; y >= 0; y--) {
//					tableModel.removeRow(dsxoa.get(y));
//				}
//			}
//		} else if (src.equals(btnThongKe) && rdoNgay.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraNam() && ktraThang()) {
//				getHD();
//				int txtnam = Integer.parseInt(txtNam.getText());
//				int txtthang = Integer.parseInt(txtThang.getText());
//				if(ktraNgay(txtnam, txtthang)) {
//					int txtngay = Integer.parseInt(txtNgay.getText());
//					ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//					double tong = 0;
//					int i = 0, tongHD = 0;
//					int rowcount = tableModel.getRowCount();
//					while (i < rowcount) {
//						int year = layNam(tbl.getValueAt(i, 5).toString());
//						int month = layThang(tbl.getValueAt(i, 5).toString());
//						int ngay = layTuan(tbl.getValueAt(i, 5).toString());	
//						if (ngay != txtngay) {
//							dsxoa.add(i);
//						}
//						i++;
//					}
//					for (int y = dsxoa.size() - 1; y >= 0; y--) {
//						tableModel.removeRow(dsxoa.get(y));
//					}
//				}		
//			}
//		} else if (src.equals(btnBack)) {
//			tableModel.setRowCount(0);
//			getHD();
//		}
//		else if(src.equals(btnXuatFilePDF)) {
//			int row = tableModel.getRowCount();
//			if(row == 0)
//				JOptionPane.showMessageDialog(this, "Không có dữ liệu để xuất file");
//			else
//				xuatFile();
//		}
//	}
//
//	public void getHD() {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		try {
//			String sql = "select cthd.maSanPham, sp.tenSanPham, sum(cthd.soLuong), sp.donGia, (sum(cthd.soLuong)*sp.donGia) as 'ThanhTien', hd.ngayLap from ChiTietHoaDon cthd join HoaDon hd on cthd.maHoaDon = hd.maHoaDon join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
//					+ "group by cthd.maSanPham, sp.tenSanPham, sp.donGia, hd.ngayLap";
////						+ "FROM  HoaDon hd, SanPham sp";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//
//			while (rs.next()) {
//				Object[] data = { rs.getString(1), rs.getString(2), rs.getInt(3), Math.round(rs.getDouble(4)), Math.round(rs.getDouble(5)), rs.getString(6) };
//				tableModel.addRow(data);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
////	public boolean ktraNam() {
////		LocalDateTime time = LocalDateTime.now();
////		try {
////			int namNhap = Integer.parseInt(txtNam.getText());
////			if (namNhap > time.getYear()) {
////				JOptionPane.showMessageDialog(this, "Phải nhỏ hơn năm hiện tại");
////				txtNam.requestFocus();
////				return false;
////			}
////			return true;
////		} catch (NumberFormatException ex) {
////			JOptionPane.showMessageDialog(this, "Năm phải là số nguyên dương!");
////			txtNam.requestFocus();
////			return false;
////		}
////	}
////
////	public boolean ktraThang() {
////		LocalDateTime time = LocalDateTime.now();
////		try {
////			int namNhap = Integer.parseInt(txtNam.getText());
////			int thangNhap = Integer.parseInt(txtThang.getText());
////			if (namNhap == time.getYear()) {
////				if (thangNhap > time.getMonthValue()) {
////					JOptionPane.showMessageDialog(this, "Phải nhỏ hơn tháng hiện tại");
////					txtThang.requestFocus();
////					return false;
////				}
////				return true;
////			} else
////				return true;
////		} catch (NumberFormatException ex) {
////			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
////			txtNam.requestFocus();
////			return false;
////		}
////	}
//	public boolean ktraNam() {
//		LocalDateTime time = LocalDateTime.now();
//		try {
//			int namNhap = Integer.parseInt(txtNam.getText());
//			if (namNhap > 2021) {
//				return true;	
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "Năm phải lớn hơn năm 2021");
//				txtNam.requestFocus();
//				return false;
//			}
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Năm phải là số nguyên dương!");
//			txtNam.requestFocus();
//			return false;
//		}
//	}
//	public boolean ktraThang() {
//		LocalDateTime time = LocalDateTime.now();
//		try {
//			int thangNhap = Integer.parseInt(txtThang.getText());
//				if (thangNhap >=1 && thangNhap <= 12) {
//					return true;	
//				}
//				else {
//					JOptionPane.showMessageDialog(this, "tháng không hợp lệ");
//					txtThang.requestFocus();
//					return false;
//				}
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
//			txtThang.requestFocus();
//			return false;
//		}
//	}
//		public boolean ktraNgay(int nam, int thang) {
//			try {
//				int ngaynhap = Integer.parseInt(txtNgay.getText());
//				if ((nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0) {
//					if(thang == 2 && ngaynhap > 29) {
//						JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
//						txtNgay.requestFocus();
//						return false;
//					}
//					else {
//						if(thang == 2 && ngaynhap > 28) {
//							JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
//							txtNgay.requestFocus();
//							return false;
//						}
//					}
//				}
//				else {
//					if(ngaynhap < 1 || ngaynhap > 31){
//						JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
//						txtNgay.requestFocus();
//						return false;
//					}	
//				}
//				return true;
//				
//			} catch (NumberFormatException ex) {
//				JOptionPane.showMessageDialog(this, "ngày phải là số nguyên dương!");
//				txtNgay.requestFocus();
//				return false;
//			}
//		}
//
//	public int layNam(String ngay) {
//		String year = ngay.substring(0, 4);
//		return Integer.valueOf(year);
//	}
//
//	public int layThang(String ngay) {
//		String thang = ngay.substring(5, 7);
//		return Integer.valueOf(thang);
//	}
//
//	public int layTuan(String ngay) {
//		String thang = ngay.substring(8, 10);
//		return Integer.valueOf(thang);
//	}
////	public void xuatExcellAllTable(JTable tableXuatExcel){
////        int rowCount = tableXuatExcel.getRowCount();
////		if(rowCount == 0) {
////			JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất thống kê!");
////			return;
////		}
////       try{
////           
////                       JFileChooser jFileChooser = new JFileChooser();
////                        File saveFile = jFileChooser.getSelectedFile();
////                        jFileChooser.addChoosableFileFilter(new FileFilter() {
////			@Override
////			public String getDescription() {
////				// TODO Auto-generated method stub
////				return "Excell file (*.xls, *xlsx)";
////			}
////			@Override
////			public boolean accept(File f) {
////				if (f.isDirectory()) {
////					return true;
////				} else {
////					return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xlsx");
////				}
////			}
////
////		});
////         
////         int is = jFileChooser.showSaveDialog(this);
////         if (is == 0) {
////			String path = jFileChooser.getSelectedFile().getAbsolutePath();
////			if (!path.matches("(.)+(\\.xls|\\.xlsx)$")) {
////				path += ".xlsx";
////			}
////			
////                         if(path != null){
////                         saveFile = new File(path.toString()+".xlsx");
////                         Workbook wb = new SXSSFWorkbook();
////                         org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("Sheet1");
////
////                         Row rowCol = sheet.createRow(0);
////                         for(int i=0;i<tableXuatExcel.getColumnCount();i++){
////                             Cell cell = rowCol.createCell(i);
////                             cell.setCellValue(tableXuatExcel.getColumnName(i));
////                         }
////
////                         for(int j=0;j<tableXuatExcel.getRowCount();j++){
////                             Row row = sheet.createRow(j+1);
////                             for(int k=0;k<tableXuatExcel.getColumnCount();k++){
////                                 Cell cell = row.createCell(k);
////                                 if(tableXuatExcel.getValueAt(j, k)!=null){
////                                     cell.setCellValue(tableXuatExcel.getValueAt(j, k).toString());
////                                 }
////                             }
////                         }
////                         FileOutputStream out = new FileOutputStream(new File(path.toString()));
////                          wb.write(out);
////                          wb.close();
////                          out.close();
////                          JOptionPane.showMessageDialog(null,"success");
////                          openFile(path.toString());
////                     }else{
////                         JOptionPane.showMessageDialog(null,"ERROR");
////                     }
////
////			
////		}
////         
////         
////       }catch(FileNotFoundException e){
////           System.out.println(e);
////       }catch(IOException io){
////           System.out.println(io);
////       }
////    } 
//	
////    public void openFile(String file){
////        try{
////            File path = new File(file);
////            Desktop.getDesktop().open(path);
////        }catch(IOException ioe){
////            System.out.println(ioe);
////        }
////    }
//	public void xuatFile() {
////		System.setProperty("file.encoding", "utf-8");
//		String namefile = "";
//		String tieuDe = "";
//		if(rdoNgay.isSelected()) {
//			namefile = "ThongKeSanPhamNgay"+txtNgay.getText()+"-"+txtThang.getText()+"-"+txtNam.getText();
//			tieuDe = "Thống kê sản phẩm ngày "+txtNgay.getText()+"-"+txtThang.getText()+"-"+txtNam.getText();
//		}
//		else if(rdoThang.isSelected()) {
//			namefile = "ThongKeSanPhamThang"+txtThang.getText()+"-"+txtNam.getText();
//			tieuDe = "Thống kê sản phẩm tháng "+txtThang.getText()+"-"+txtNam.getText();
//
//		}
//		else if(rdoNam.isSelected()) {
//			namefile = "ThongKeSanPhamNam"+txtNam.getText();
//			tieuDe = "Thống kê sản phẩm năm "+txtNam.getText();
//		}
//		String path = System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + namefile + ".pdf";
//        com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10); //10 is the size
//        com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 24); //30 is the size
//
//        Document doc = new Document();
//        try {
//        	PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));
//            doc.open();
//            Paragraph ShopName = new Paragraph("      "+tieuDe+"\n", textFont24);
//            doc.add(ShopName);
//            Paragraph DiaChi = new Paragraph("                               Địa chỉ: 4 Nguyễn Văn Bảo. Quận Gò Vấp, Thành phố Hồ Chí Minh\n", textFont);
//            doc.add(DiaChi);
//            Paragraph SDT = new Paragraph("                                                          Số điện thoại: 0901234567\n", textFont);
//            doc.add(SDT);
//            Paragraph starLine = new Paragraph("==================================================================================", textFont);
//            doc.add(starLine);
//
////            Thông tin nhân viên
//            String maNV = "";
//            String tenNV = "";
//            for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
//				if(tk.getMaTaiKhoan().equals(maTK)) {
//					maNV = tk.getNhanVien().getMaNhanVien();
//					break;
//				}
//					
//					
//			}
//            for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
//				if(nv.getMaNhanVien().equals(maNV)) {
//					tenNV = nv.getTenNhanVien();
//					break;
//				}
//					
//			}
////            String[] ngaylap = txtNgayLap.getText().split("-");
////            LocalDate date = LocalDate.of(Integer.valueOf(ngaylap[0]), Integer.valueOf(ngaylap[1]), Integer.valueOf(ngaylap[2]));
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            Paragraph paragraphMaNhanVienLine1 = new Paragraph("               Mã nhân viên:" + maNV + "                                             Ngày xuất thống kê: " + dtf.format(LocalDate.now()) + "\n", textFont);
//            doc.add(paragraphMaNhanVienLine1);
//            Paragraph paragraphTenNhanVienLine2 = new Paragraph("              Tên nhân viên: " + tenNV, textFont);
//            doc.add(paragraphTenNhanVienLine2);
//   
//
////            Chi tiết thống kê
//            NumberFormat formatter = NumberFormat.getInstance();
//            Paragraph paragraph4 = new Paragraph("              Chi tiết thống kê", textFont);
//            doc.add(paragraph4);
//            Paragraph paragraph5 = new Paragraph("\n");
//            doc.add(paragraph5);
//            PdfPTable tbl = new PdfPTable(6);
//            tbl.addCell(new Phrase("Mã sản phẩm", textFont));
//            tbl.addCell(new Phrase("Tên sản phẩm", textFont));
//            tbl.addCell(new Phrase("Số lượng", textFont));
//            tbl.addCell(new Phrase("Đơn giá", textFont));
//            tbl.addCell(new Phrase("Doanh thu", textFont));
//            tbl.addCell(new Phrase("Ngày lập", textFont));
//            for (int i = 0; i < this.tbl.getRowCount(); i++) {
//                String tenSP = this.tbl.getValueAt(i, 1).toString();
//                String maSP = this.tbl.getValueAt(i, 0).toString();
//                String sl = this.tbl.getValueAt(i, 2).toString();
//                String donGia = this.tbl.getValueAt(i, 3).toString();
//                String tongTien = this.tbl.getValueAt(i, 4).toString();
//                String ngayLap = this.tbl.getValueAt(i, 5).toString();
//                String[] date = ngayLap.split("-");
//                
//                tbl.addCell(new Phrase(maSP, textFont));
//                tbl.addCell(new Phrase(tenSP, textFont));
//                tbl.addCell(new Phrase(sl, textFont));
//                tbl.addCell(new Phrase(formatter.format(Double.valueOf(donGia)), textFont));
//                tbl.addCell(new Phrase(formatter.format(Double.valueOf(tongTien)), textFont));
//                tbl.addCell(new Phrase(dtf.format(LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]))), textFont));
//            }
//           
//            doc.add(tbl);
//            doc.add(starLine);
//            //Thông tin tổng tiền:
////            Paragraph paragraphTongTienLine1 = new Paragraph("              Tổng số hóa đơn:" + txtHoaDon.getText(), textFont);
////            doc.add(paragraphTongTienLine1);
////            Paragraph paragraphTongTienLine2 = new Paragraph("              Tổng doanh thu:" + txtThanhTien.getText(), textFont);
////            doc.add(paragraphTongTienLine2);
//            doc.close();
//            //open pdf
//            open(namefile);
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//	}
//	public void open(String path) {
//        try {
//            if((new File(System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + path + ".pdf")).exists()){
//                Process p = Runtime
//                        .getRuntime()
//                        .exec("rundll32 url.dll, FileProtocolHandler " + System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + path + ".pdf");
//            } else {
//                JOptionPane.showMessageDialog(null, "File is not Exists");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
//}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
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

import components.button.Button;
import components.comboBox.ComboBox;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;

import dao.LoaiSanPhamDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;

import dao.TaiKhoanDAO;
import entities.LoaiSanPham;
import entities.NhanVien;
import entities.SanPham;

import entities.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import services.EntityManagerFactoryUtils;
import url.RmiConfig;
import utils.Utils;

public class ThongKeSanPhamDaBan extends JPanel implements ActionListener {
	private EntityManager entityManager;
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1010;

	private JPanel pnlMain;
	private TextField txtNgayBatDau;
	private TextField txtNgayKetThuc;
	private JComboBox<String> cmbLoc;
	private Button btnThongKe;
	private DefaultTableModel modelTKSP;
	private JTable tblTKSP;
	private ArrayList<SanPham> dsSP;
	private SanPhamDAO sanPham_DAO;

	private JRadioButton rdoNgay;
	private JRadioButton rdoThang;
	private JRadioButton rdoNam;
	private JComboBox<String> cbNgay;
	private JComboBox<String> cbThang;
	private JComboBox<String> cbNam;
	private Button btnBack;
	private String maTK;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private Button btnXuatFilePDF;
	private JComboBox<String> cmbLoaiSanPham;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private TextField txtTongSoLuong;
	private TextField txtTongTien;
	private DecimalFormat formatTienTe;
	private DecimalFormat format;
	private ButtonGroup btnGrpSP;
	private ButtonGroup buttonGroup;
	private ChartPanel chartPanel1;
	private ChartPanel chartPanel2;
	private DefaultCategoryDataset dataset1;
	private DefaultCategoryDataset dataset2;
	private JFreeChart chart1;
	private JFreeChart chart2;
	private NumberFormat formatter;

	public ThongKeSanPhamDaBan(ManHinhChinh main, String maTK)
			throws MalformedURLException, NotBoundException, RemoteException {
//		try {
//			ConnectDB.getInstance().connect();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		this.entityManager = Persistence.createEntityManagerFactory("JPA_ORM_MSSQL").createEntityManager();

		this.main = main;
		this.maTK = maTK;
		LoaiSanPham_dao = (LoaiSanPhamDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/loaiSanPhamDao");
		TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/taiKhoanDao");
		NhanVien_dao = (NhanVienDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/nhanVienDao");
		sanPham_DAO = (SanPhamDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/sanPhamDao");
		formatTienTe = new DecimalFormat("#,##0₫");
		format = new DecimalFormat("#,##0");
		formatter = NumberFormat.getInstance();
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

		int topPnlControl = Utils.getBodyHeight() - 80;

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, widthPnlContainer, 80);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.secondaryColor);
		pnlHeader.setBounds(16, 18, 750, 70);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Thống Kê Sản Phẩm Đã Bán");
		lblTitle.setBounds(250, 10, 400, 40);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);

		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left - 330, top + 20, 1000, 150);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		cbNgay = new JComboBox<String>();
//		txtNgay.setLabelText("Ngày:");
		cbNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbNgay.setBackground(new Color(203, 239, 255));
		cbNgay.setBounds(30, 0, 200, 40);
		rdoNgay = new JRadioButton();
		rdoNgay.setBounds(230, 11, 25, 25);
		rdoNgay.setBackground(new Color(203, 239, 255));
		pnlRow1.add(cbNgay);
		pnlRow1.add(rdoNgay);
		cbNgay.addItem("Ngày");
		for (int i = 1; i <= 31; i++) {
			cbNgay.addItem(String.valueOf(i));
		}

		cbThang = new JComboBox<String>();
//		txtThang.setLabelText("Tháng:");
		cbThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbThang.setBackground(new Color(203, 239, 255));
		cbThang.setBounds(270, 0, 200, 40);
		rdoThang = new JRadioButton();
		rdoThang.setBounds(470, 11, 25, 25);
		rdoThang.setBackground(new Color(203, 239, 255));
		pnlRow1.add(cbThang);
		pnlRow1.add(rdoThang);
		cbThang.addItem("Tháng");
		for (int i = 1; i <= 12; i++) {
			cbThang.addItem(String.valueOf(i));
		}

		cbNam = new JComboBox<String>();
//		cbNam.setLabelText("Năm:");
		cbNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbNam.setBackground(new Color(203, 239, 255));
		cbNam.setBounds(520, 0, 200, 40);
		rdoNam = new JRadioButton();
		rdoNam.setBounds(730, 11, 25, 25);
		rdoNam.setBackground(new Color(203, 239, 255));
		pnlRow1.add(cbNam);
		pnlRow1.add(rdoNam);
		cbNam.addItem("Năm");
		for (int i = 2021; i <= LocalDate.now().getYear(); i++) {
			cbNam.addItem(String.valueOf(i));
		}
//		JLabel lblLoc = new JLabel("Lọc theo:");
//		lblLoc.setForeground(Utils.labelTextField);
//		lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		lblLoc.setBounds(500, 10, 100, 65);
//		pnlRow1.add(lblLoc);
//		
//		cmbLoc = new ComboBox<>();
//		cmbLoc.setModel(new DefaultComboBoxModel<String>());
//		cmbLoc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		cmbLoc.setBackground(new Color(140, 177, 180));
//		cmbLoc.setBounds(580, 20, 220, 30);
//		pnlRow1.add(cmbLoc);
		cbNgay.setEnabled(false);
		cbThang.setEnabled(false);
		cbNam.setEnabled(false);

		btnThongKe = new Button("Thống kê");
		btnThongKe.setBounds(820, 10, 180, 40);
		btnThongKe.setIcon(Utils.getImageIcon("tkdoanhthu.png"));
		btnThongKe.setRadius(8);
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThongKe.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThongKe.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThongKe.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThongKe.setBorderColor(new Color(203, 239, 255));
		btnThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnThongKe);

		btnBack = new Button("Làm mới");
		btnBack.setBounds(820, 100, 180, 40);
		btnBack.setIcon(Utils.getImageIcon("back.png"));
		btnBack.setRadius(8);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnBack.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnBack.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnBack.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnBack.setBorderColor(new Color(203, 239, 255));
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnBack);

//		barDataset.setValue( "IPhone 5s" , new Double( 20 ) );
//		barDataset.setValue( "SamSung Grand" , new Double( 20 ) );
//		barDataset.setValue( "MotoG" , new Double( 40 ) );
//		barDataset.setValue( "Nokia Lumia" , new Double( 10 ) );

		cmbLoaiSanPham = new ComboBox<String>();
		cmbLoaiSanPham.setFocusable(false);
		cmbLoaiSanPham.setBackground(Utils.getRGBA(36, 214, 134, 1));
		cmbLoaiSanPham.setForeground(Color.WHITE);
		cmbLoaiSanPham.setFont(new Font("Segoe UI", Font.BOLD, 20));
		cmbLoaiSanPham.setBounds(30, 70, 200, 30);
		cmbLoaiSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cmbLoaiSanPham.setBorder(new EmptyBorder(0, 0, 0, 0));
		cmbLoaiSanPham.addItem("Loại sản phẩm");
		for (LoaiSanPham lsp : LoaiSanPham_dao.getAllLoaiSanPham()) {
			cmbLoaiSanPham.addItem(lsp.getTenLoaiSanPham());
		}
		pnlRow1.add(cmbLoaiSanPham);

		btnXuatFilePDF = new Button("Xuất File PDF");
		btnXuatFilePDF.setBounds(820, 55, 180, 40);
		btnXuatFilePDF.setIcon(Utils.getImageIcon("pdf.png"));
		btnXuatFilePDF.setRadius(8);
		btnXuatFilePDF.setForeground(Color.WHITE);
		btnXuatFilePDF.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXuatFilePDF.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXuatFilePDF.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXuatFilePDF.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXuatFilePDF.setBorderColor(new Color(203, 239, 255));
		btnXuatFilePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnXuatFilePDF);

		txtTongSoLuong = new TextField();
		txtTongSoLuong.setLabelText("Tổng số lượng:");
		txtTongSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTongSoLuong.setBackground(new Color(203, 239, 255));
		txtTongSoLuong.setBounds(left, 710, 200, 45);
		txtTongSoLuong.setEditable(false);
		add(txtTongSoLuong);

		txtTongTien = new TextField();
		txtTongTien.setLabelText("Tổng tiền:");
		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTongTien.setBackground(new Color(203, 239, 255));
		txtTongTien.setBounds(left + 300, 710, 200, 45);
		txtTongTien.setEditable(false);
		add(txtTongTien);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left - 320, 255, 1000, 450);
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
		tbl.setAutoCreateRowSorter(true);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tableModel = new DefaultTableModel(
				new String[] { "Mã sản phẫm", "Tên sản phẫm", "SL", "Giá bán", "Thành tiền" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//		dsSP = new ArrayList<SanPham>();
//		for (SanPham sp : sanPham_DAO.getAllSanPham()) {
//			Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), sp.getSoLuong() , sp.getDonGia()};
//			tableModel.addRow(data);
//		}
//		getHD();

		tableColumnModel.getColumn(0).setPreferredWidth(200);
		tableColumnModel.getColumn(1).setPreferredWidth(300);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(200);

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

		chartPanel1 = new ChartPanel(null);
		chartPanel1.setBounds(1060, 80, 480, 300);
		add(chartPanel1);
		chartPanel2 = new ChartPanel(null);
		chartPanel2.setBounds(1060, 400, 480, 300);
		add(chartPanel2);

		btnThongKe.addActionListener(this);
		btnBack.addActionListener(this);
		btnXuatFilePDF.addActionListener(this);
		rdoNgay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbNgay.setEnabled(true);
				cbThang.setEnabled(true);
				cbNam.setEnabled(true);
			}
		});

		rdoThang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbNgay.setEnabled(false);
				cbThang.setEnabled(true);
				cbNam.setEnabled(true);
			}
		});
		rdoNam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbNgay.setEnabled(false);
				cbThang.setEnabled(false);
				cbNam.setEnabled(true);
			}
		});

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdoNgay);
		buttonGroup.add(rdoThang);
		buttonGroup.add(rdoNam);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnThongKe)) {
			if (rdoNgay.isSelected() == false && rdoThang.isSelected() == false && rdoNam.isSelected() == false)
				JOptionPane.showMessageDialog(this, "Vui lòng thêm thông tin thời gian để thống kê");
			else {
				if (rdoNgay.isSelected() && cmbLoaiSanPham.getSelectedIndex() == 0) {
					tableModel.setRowCount(0);
					int thang = Integer.valueOf(cbThang.getSelectedItem().toString());
					int nam = Integer.valueOf(cbNam.getSelectedItem().toString());
					if (ktraNgay(nam, thang)) {
						int ngay = Integer.valueOf(cbNgay.getSelectedItem().toString());
						getDuLieuTheoNgay(ngay, thang, nam, "");
					}
				} else if (rdoNgay.isSelected() && cmbLoaiSanPham.getSelectedIndex() != 0) {
					chartPanel1.setChart(null);
					chartPanel2.setChart(null);
					tableModel.setRowCount(0);
					int thang = Integer.valueOf(cbThang.getSelectedItem().toString());
					int nam = Integer.valueOf(cbNam.getSelectedItem().toString());
					if (ktraNgay(nam, thang)) {
						int ngay = Integer.valueOf(cbNgay.getSelectedItem().toString());
						getDuLieuTheoNgay(ngay, thang, nam, cmbLoaiSanPham.getSelectedItem().toString());
					}
				}
				if (rdoThang.isSelected() && cmbLoaiSanPham.getSelectedIndex() == 0) {
					tableModel.setRowCount(0);
					int thang = Integer.valueOf(cbThang.getSelectedItem().toString());
					int nam = Integer.valueOf(cbNam.getSelectedItem().toString());
					getDuLieuTheoThang(thang, nam, "");
				} else if (rdoThang.isSelected() && cmbLoaiSanPham.getSelectedIndex() != 0) {
					chartPanel1.setChart(null);
					chartPanel2.setChart(null);
					tableModel.setRowCount(0);
					int thang = Integer.valueOf(cbThang.getSelectedItem().toString());
					int nam = Integer.valueOf(cbNam.getSelectedItem().toString());
					getDuLieuTheoThang(thang, nam, cmbLoaiSanPham.getSelectedItem().toString());
				}
			}
			if (rdoNam.isSelected() && cmbLoaiSanPham.getSelectedIndex() == 0) {
				tableModel.setRowCount(0);
				int nam = Integer.valueOf(cbNam.getSelectedItem().toString());
				getDuLieuTheoNam(nam, "");
//					createChart();
			} else if (rdoNam.isSelected() && cmbLoaiSanPham.getSelectedIndex() != 0) {
				chartPanel1.setChart(null);
				chartPanel2.setChart(null);
				tableModel.setRowCount(0);
				int nam = Integer.valueOf(cbNam.getSelectedItem().toString());
				getDuLieuTheoNam(nam, cmbLoaiSanPham.getSelectedItem().toString());
//					createChart();
			}
		} else if (src.equals(btnXuatFilePDF)) {

			int row = tableModel.getRowCount();
			if (row == 0)
				JOptionPane.showMessageDialog(this, "Không có dữ liệu để xuất file");
			else
				xuatFile();
		} else if (src.equals(btnBack)) {
			tableModel.setRowCount(0);
			cbNgay.setSelectedIndex(0);
			cbThang.setSelectedIndex(0);
			cbNam.setSelectedIndex(0);
			cbNgay.setEnabled(false);
			cbThang.setEnabled(false);
			cbNam.setEnabled(false);
			buttonGroup.clearSelection();
			cmbLoaiSanPham.setSelectedIndex(0);
			chartPanel1.setChart(null);
			chartPanel2.setChart(null);
		}
	}

	public void getHD() {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
		try {
			Query query = entityManager.createNativeQuery(
					"SELECT cthd.maSanPham as maSanPham, sp.tenSanPham as tenSanPham, sum(cthd.soLuong) as soLuong, sp.donGia as donGia, sum(cthd.soLuong)*sp.donGia as thanhTien, hd.ngayLap as ngayLap FROM ChiTietHoaDon cthd JOIN cthd.hoaDon hd JOIN cthd.sanPham sp GROUP BY cthd.maSanPham, sp.tenSanPham, sp.donGia, hd.ngayLap");
			List<Object[]> list = query.getResultList();
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], obj[4], obj[5] };
				tableModel.addRow(data);
			}

//			String sql = "select cthd.maSanPham, sp.tenSanPham, sum(cthd.soLuong), sp.donGia, (sum(cthd.soLuong)*sp.donGia) as 'ThanhTien', hd.ngayLap from ChiTietHoaDon cthd join HoaDon hd on cthd.maHoaDon = hd.maHoaDon join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
//					+ "group by cthd.maSanPham, sp.tenSanPham, sp.donGia, hd.ngayLap";
////						+ "FROM  HoaDon hd, SanPham sp";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);

//			while (rs.next()) {
//				Object[] data = { rs.getString(1), rs.getString(2), rs.getInt(3), Math.round(rs.getDouble(4)),
//						Math.round(rs.getDouble(5)), rs.getString(6) };
//				tableModel.addRow(data);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDuLieuTheoNgay(int ngay, int thang, int nam, String loaiSP) {
		int soLuongSP = 0;
		Double tongTien = 0.0;

		List<Object[]> list;
		List<Object[]> list2;
		List<Object[]> list3;
		if(loaiSP.equals("")) {
			try {
				list = sanPham_DAO.thongKeSanPhamTheoNgay(ngay, thang, nam, loaiSP);
				list2 = sanPham_DAO.thongKeSanPhamTheoDTLSPTheoNgay(ngay, thang, nam);
				list3 = sanPham_DAO.thongKeSanPhamTheoSLLSPTheoNgay(ngay, thang, nam);
				for (Object[] obj : list) {
					Object[] data = { obj[0], obj[1], obj[2], formatter.format(Double.valueOf(obj[3].toString())), formatter.format(Double.valueOf(obj[4].toString())) };
					soLuongSP += Integer.valueOf(obj[2].toString());
					tongTien += Double.valueOf(obj[4].toString());
					tableModel.addRow(data);
				}
				dataset1 = new DefaultCategoryDataset();
				for (Object[] objects : list2) {
					dataset1.addValue(Double.valueOf(objects[1].toString()), "Doanh thu", objects[0].toString());
				}
				dataset2 = new DefaultCategoryDataset();
				for (Object[] objects : list3) {
					dataset2.addValue(Double.valueOf(objects[1].toString()), "Số lượng", objects[0].toString());
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chart1 = ChartFactory.createBarChart("Doanh thu theo loại sản phẩm", "Loại sản phẩm", "Doanh thu", dataset1);
			chart2 = ChartFactory.createBarChart("Số lượng sản phẩm theo loại sản phẩm", "Loại sản phẩm", "Số lượng",
					dataset2);
			CategoryPlot plot = chart1.getCategoryPlot();
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			for (int i = 0; i < dataset1.getRowCount(); i++) {
				renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000)));
			}
			for (int i = 0; i < dataset2.getRowCount(); i++) {
				renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000)));
			}
			chartPanel1.setChart(chart1);
			chartPanel2.setChart(chart2);
		}
		else {
			try {
				list = sanPham_DAO.thongKeSanPhamTheoNgay(ngay, thang, nam, loaiSP);
				for (Object[] obj : list) {
					Object[] data = { obj[0], obj[1], obj[2], formatter.format(Double.valueOf(obj[3].toString())), formatter.format(Double.valueOf(obj[4].toString())) };
					soLuongSP += Integer.valueOf(obj[2].toString());
					tongTien += Double.valueOf(obj[4].toString());
					tableModel.addRow(data);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		txtTongSoLuong.setText(String.valueOf(soLuongSP));
		txtTongTien.setText(formatTienTe.format(tongTien));
	}

	public void getDuLieuTheoThang(int thang, int nam, String loaiSP) {
		int soLuongSP = 0;
		Double tongTien = 0.0;
		List<Object[]> list;
		List<Object[]> list2;
		List<Object[]> list3;
		if(loaiSP.equals("")){
			try {
				list = sanPham_DAO.thongKeSanPhamTheoThang(thang, nam, loaiSP);
				list2 = sanPham_DAO.thongKeSanPhamTheoDTLSPTheoThang(thang, nam);
				list3 = sanPham_DAO.thongKeSanPhamTheoSLLSPTheoThang(thang, nam);
				for (Object[] obj : list) {
					Object[] data = { obj[0], obj[1], obj[2], formatter.format(Double.valueOf(obj[3].toString())), formatter.format(Double.valueOf(obj[4].toString())) };
					soLuongSP += Integer.valueOf(obj[2].toString());
					tongTien += Double.valueOf(obj[4].toString());
					tableModel.addRow(data);
				}
				dataset1 = new DefaultCategoryDataset();
				for (Object[] objects : list2) {
					dataset1.addValue(Double.valueOf(objects[1].toString()), "Doanh thu", objects[0].toString());
				}
				dataset2 = new DefaultCategoryDataset();
				for (Object[] objects : list3) {
					dataset2.addValue(Double.valueOf(objects[1].toString()), "Số lượng", objects[0].toString());
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chart1 = ChartFactory.createBarChart("Doanh thu theo loại sản phẩm", "Loại sản phẩm", "Doanh thu", dataset1);
			chart2 = ChartFactory.createBarChart("Số lượng sản phẩm theo loại sản phẩm", "Loại sản phẩm", "Số lượng",
					dataset2);
			CategoryPlot plot = chart1.getCategoryPlot();
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			for (int i = 0; i < dataset1.getRowCount(); i++) {
				renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000)));
			}
			for (int i = 0; i < dataset2.getRowCount(); i++) {
				renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000)));
			}
			chartPanel1.setChart(chart1);
			chartPanel2.setChart(chart2);
		}
		else {
			try {
				list = sanPham_DAO.thongKeSanPhamTheoThang(thang, nam, loaiSP);
				for (Object[] obj : list) {
					Object[] data = { obj[0], obj[1], obj[2], formatter.format(Double.valueOf(obj[3].toString())), formatter.format(Double.valueOf(obj[4].toString())) };
					soLuongSP += Integer.valueOf(obj[2].toString());
					tongTien += Double.valueOf(obj[4].toString());
					tableModel.addRow(data);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		txtTongSoLuong.setText(String.valueOf(soLuongSP));
		txtTongTien.setText(formatTienTe.format(tongTien));
	}

	public void getDuLieuTheoNam(int nam, String loaiSP) {
		int soLuongSP = 0;
		Double tongTien = 0.0;
		List<Object[]> list;
		List<Object[]> list2;
		List<Object[]> list3;
		if(loaiSP.equals("")) {
			try {
				list = sanPham_DAO.thongKeSanPhamTheoNam(nam, loaiSP);
				list2 = sanPham_DAO.thongKeSanPhamTheoDTLSPTheoNam(nam);
				list3 = sanPham_DAO.thongKeSanPhamTheoSLLSPTheoNam(nam);
				for (Object[] obj : list) {
					Object[] data = { obj[0], obj[1], obj[2], formatter.format(Double.valueOf(obj[3].toString())), formatter.format(Double.valueOf(obj[4].toString())) };
					soLuongSP += Integer.valueOf(obj[2].toString());
					tongTien += Double.valueOf(obj[4].toString());
					tableModel.addRow(data);
				}
				dataset1 = new DefaultCategoryDataset();
				for (Object[] objects : list2) {
					dataset1.addValue(Double.valueOf(objects[1].toString()), "Doanh thu", objects[0].toString());
				}
				dataset2 = new DefaultCategoryDataset();
				for (Object[] objects : list3) {
					dataset2.addValue(Double.valueOf(objects[1].toString()), "Số lượng", objects[0].toString());
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chart1 = ChartFactory.createBarChart("Doanh thu theo loại sản phẩm", "Loại sản phẩm", "Doanh thu", dataset1);
			chart2 = ChartFactory.createBarChart("Số lượng sản phẩm theo loại sản phẩm", "Loại sản phẩm", "Số lượng",
					dataset2);
			CategoryPlot plot = chart1.getCategoryPlot();
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			for (int i = 0; i < dataset1.getRowCount(); i++) {
				renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000)));
			}
			for (int i = 0; i < dataset2.getRowCount(); i++) {
				renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000)));
			}
			chartPanel1.setChart(chart1);
			chartPanel2.setChart(chart2);
		}
		else {
			try {
				list = sanPham_DAO.thongKeSanPhamTheoNam(nam, loaiSP);
				for (Object[] obj : list) {
					Object[] data = { obj[0], obj[1], obj[2], formatter.format(Double.valueOf(obj[3].toString())), formatter.format(Double.valueOf(obj[4].toString())) };
					soLuongSP += Integer.valueOf(obj[2].toString());
					tongTien += Double.valueOf(obj[4].toString());
					tableModel.addRow(data);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		txtTongSoLuong.setText(String.valueOf(soLuongSP));
		txtTongTien.setText(formatTienTe.format(tongTien));

	}

//	public boolean ktraNam() {
//		LocalDateTime time = LocalDateTime.now();
//		try {
//			int namNhap = Integer.parseInt(txtNam.getText());
//			if (namNhap > 2021) {
//				return true;
//			} else {
//				JOptionPane.showMessageDialog(this, "Năm phải lớn hơn năm 2021");
//				txtNam.requestFocus();
//				return false;
//			}
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Năm phải là số nguyên dương!");
//			txtNam.requestFocus();
//			return false;
//		}
//	}
//
//	public boolean ktraThang() {
//		LocalDateTime time = LocalDateTime.now();
//		try {
//			int thangNhap = Integer.parseInt(txtThang.getText());
//			if (thangNhap >= 1 && thangNhap <= 12) {
//				return true;
//			} else {
//				JOptionPane.showMessageDialog(this, "tháng không hợp lệ");
//				txtThang.requestFocus();
//				return false;
//			}
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
//			txtThang.requestFocus();
//			return false;
//		}
//	}

	public boolean ktraNgay(int nam, int thang) {
		try {
			int ngaynhap = Integer.parseInt(cbNgay.getSelectedItem().toString());
			LocalDate date = LocalDate.of(nam, thang, 1);
			if (ngaynhap < 1 || ngaynhap > date.lengthOfMonth()) {
				JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
				cbNgay.requestFocus();
				return false;
			}
			return true;

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ngày phải là số nguyên dương!");
			cbNgay.requestFocus();
			return false;
		}
	}

	public int layNam(String ngay) {
		String year = ngay.substring(0, 4);
		return Integer.valueOf(year);
	}

	public int layThang(String ngay) {
		String thang = ngay.substring(5, 7);
		return Integer.valueOf(thang);
	}

	public int layTuan(String ngay) {
		String thang = ngay.substring(8, 10);
		return Integer.valueOf(thang);
	}
//	public void xuatExcellAllTable(JTable tableXuatExcel){
//        int rowCount = tableXuatExcel.getRowCount();
//		if(rowCount == 0) {
//			JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất thống kê!");
//			return;
//		}
//       try{
//           
//                       JFileChooser jFileChooser = new JFileChooser();
//                        File saveFile = jFileChooser.getSelectedFile();
//                        jFileChooser.addChoosableFileFilter(new FileFilter() {
//			@Override
//			public String getDescription() {
//				// TODO Auto-generated method stub
//				return "Excell file (*.xls, *xlsx)";
//			}
//			@Override
//			public boolean accept(File f) {
//				if (f.isDirectory()) {
//					return true;
//				} else {
//					return f.getName().toLowerCase().endsWith(".xls") || f.getName().toLowerCase().endsWith(".xlsx");
//				}
//			}
//
//		});
//         
//         int is = jFileChooser.showSaveDialog(this);
//         if (is == 0) {
//			String path = jFileChooser.getSelectedFile().getAbsolutePath();
//			if (!path.matches("(.)+(\\.xls|\\.xlsx)$")) {
//				path += ".xlsx";
//			}
//			
//                         if(path != null){
//                         saveFile = new File(path.toString()+".xlsx");
//                         Workbook wb = new SXSSFWorkbook();
//                         org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("Sheet1");
//
//                         Row rowCol = sheet.createRow(0);
//                         for(int i=0;i<tableXuatExcel.getColumnCount();i++){
//                             Cell cell = rowCol.createCell(i);
//                             cell.setCellValue(tableXuatExcel.getColumnName(i));
//                         }
//
//                         for(int j=0;j<tableXuatExcel.getRowCount();j++){
//                             Row row = sheet.createRow(j+1);
//                             for(int k=0;k<tableXuatExcel.getColumnCount();k++){
//                                 Cell cell = row.createCell(k);
//                                 if(tableXuatExcel.getValueAt(j, k)!=null){
//                                     cell.setCellValue(tableXuatExcel.getValueAt(j, k).toString());
//                                 }
//                             }
//                         }
//                         FileOutputStream out = new FileOutputStream(new File(path.toString()));
//                          wb.write(out);
//                          wb.close();
//                          out.close();
//                          JOptionPane.showMessageDialog(null,"success");
//                          openFile(path.toString());
//                     }else{
//                         JOptionPane.showMessageDialog(null,"ERROR");
//                     }
//
//			
//		}
//         
//         
//       }catch(FileNotFoundException e){
//           System.out.println(e);
//       }catch(IOException io){
//           System.out.println(io);
//       }
//    } 

//    public void openFile(String file){
//        try{
//            File path = new File(file);
//            Desktop.getDesktop().open(path);
//        }catch(IOException ioe){
//            System.out.println(ioe);
//        }
//    }
	public void xuatFile() {
//		System.setProperty("file.encoding", "utf-8");
		String namefile = "";
		String tieuDe = "";
		if (rdoNgay.isSelected()) {
			namefile = "ThongKeSanPhamNgay" + cbNgay.getSelectedItem().toString() + "-"
					+ cbThang.getSelectedItem().toString() + "-" + cbNam.getSelectedItem().toString();
			tieuDe = "Thống kê sản phẩm ngày " + cbNgay.getSelectedItem().toString() + "-"
					+ cbThang.getSelectedItem().toString() + "-" + cbNam.getSelectedItem().toString();
		} else if (rdoThang.isSelected()) {
			namefile = "ThongKeSanPhamThang" + cbThang.getSelectedItem().toString() + "-"
					+ cbNam.getSelectedItem().toString();
			tieuDe = "Thống kê sản phẩm tháng " + cbThang.getSelectedItem().toString() + "-"
					+ cbNam.getSelectedItem().toString();

		} else if (rdoNam.isSelected()) {
			namefile = "ThongKeSanPhamNam" + cbNam.getSelectedItem().toString();
			tieuDe = "Thống kê sản phẩm năm " + cbNam.getSelectedItem().toString();
		}
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatThongKe\\" + namefile + ".pdf";
		com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED, 10); // 10 is the size
		com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED, 24); // 30 is the size

		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));
			doc.open();
			Paragraph ShopName = new Paragraph("      " + tieuDe + "\n", textFont24);
			doc.add(ShopName);
			Paragraph DiaChi = new Paragraph(
					"                               Địa chỉ: 4 Nguyễn Văn Bảo. Quận Gò Vấp, Thành phố Hồ Chí Minh\n",
					textFont);
			doc.add(DiaChi);
			Paragraph SDT = new Paragraph(
					"                                                          Số điện thoại: 0901234567\n", textFont);
			doc.add(SDT);
			Paragraph starLine = new Paragraph(
					"==================================================================================", textFont);
			doc.add(starLine);

//            Thông tin nhân viên
			String maNV = "";
			String tenNV = "";
			for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
				if (tk.getMaTaiKhoan().equals(maTK)) {
					maNV = tk.getNhanVien().getMaNhanVien();
					break;
				}

			}
			for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
				if (nv.getMaNhanVien().equals(maNV)) {
					tenNV = nv.getTenNhanVien();
					break;
				}

			}
//            String[] ngaylap = txtNgayLap.getText().split("-");
//            LocalDate date = LocalDate.of(Integer.valueOf(ngaylap[0]), Integer.valueOf(ngaylap[1]), Integer.valueOf(ngaylap[2]));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Paragraph paragraphMaNhanVienLine1 = new Paragraph("               Mã nhân viên:" + maNV
					+ "                                             Ngày xuất thống kê: " + dtf.format(LocalDate.now())
					+ "\n", textFont);
			doc.add(paragraphMaNhanVienLine1);
			Paragraph paragraphTenNhanVienLine2 = new Paragraph("              Tên nhân viên: " + tenNV, textFont);
			doc.add(paragraphTenNhanVienLine2);

//            Chi tiết thống kê
			NumberFormat formatter = NumberFormat.getInstance();
			Paragraph paragraph4 = new Paragraph("              Chi tiết thống kê", textFont);
			doc.add(paragraph4);
			Paragraph paragraph5 = new Paragraph("\n");
			doc.add(paragraph5);
			PdfPTable tbl = new PdfPTable(5);
			tbl.addCell(new Phrase("Mã sản phẩm", textFont));
			tbl.addCell(new Phrase("Tên sản phẩm", textFont));
			tbl.addCell(new Phrase("Số lượng đã bán", textFont));
			tbl.addCell(new Phrase("Đơn giá", textFont));
			tbl.addCell(new Phrase("Thành tiền", textFont));
			for (int i = 0; i < this.tbl.getRowCount(); i++) {
				String tenSP = this.tbl.getValueAt(i, 1).toString();
				String maSP = this.tbl.getValueAt(i, 0).toString();
				String sl = this.tbl.getValueAt(i, 2).toString();
				String donGia = this.tbl.getValueAt(i, 3).toString();
				String tongTien = this.tbl.getValueAt(i, 4).toString();

				tbl.addCell(new Phrase(maSP, textFont));
				tbl.addCell(new Phrase(tenSP, textFont));
				tbl.addCell(new Phrase(sl, textFont));
				tbl.addCell(new Phrase(donGia, textFont));
				tbl.addCell(new Phrase(tongTien, textFont));
			}

			doc.add(tbl);
			doc.add(starLine);
//			 Thông tin tổng tiền:
			Paragraph paragraphTongTienLine1 = new Paragraph(
					"              Tổng số hóa đơn:" + txtTongSoLuong.getText(), textFont);
			doc.add(paragraphTongTienLine1);
			Paragraph paragraphTongTienLine2 = new Paragraph("              Tổng doanh thu:" + txtTongTien.getText(),
					textFont);
			doc.add(paragraphTongTienLine2);
			doc.close();
			// open pdf
			open(namefile);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void open(String path) {
		try {
			if ((new File(System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatThongKe\\" + path + ".pdf"))
					.exists()) {
				Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "
						+ System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatThongKe\\" + path + ".pdf");
			} else {
				JOptionPane.showMessageDialog(null, "File is not Exists");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

//	public void createChart() {
//		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
//		int top = padding;
//		// chart
//		DefaultPieDataset barDataset = new DefaultPieDataset();
//		for (Map.Entry<String, Double> entry : getDuLieuBieuDoTheoNam(Integer.valueOf(txtNam.getText()),
//				rdoBanChay.isSelected(), rdoBancham.isSelected(), cmbLoaiSanPham.getSelectedItem().toString())
//				.entrySet()) {
//			barDataset.setValue(entry.getKey(), entry.getValue());
//		}
//
//		// create chart
//		JFreeChart piechart = ChartFactory.createPieChart("Clothes sold", barDataset, true, true, false);// explain
//
//		PiePlot piePlot = (PiePlot) piechart.getPlot();
//
//		// changing pie chart blocks colors
//
////		piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
////		piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
////		piePlot.setSectionPaint("MotoG", new Color(255,102,153));
////		piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
//		// change size
//		piePlot.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
//
//		piePlot.setBackgroundPaint(Color.white);
//
//		// create chartPanel to display chart(graph)
//		JPanel panelBarChart = new JPanel();
//		ChartPanel barChartPanel = new ChartPanel(piechart);
//		panelBarChart.removeAll();
//		panelBarChart.add(barChartPanel, BorderLayout.CENTER);
//		panelBarChart.validate();
//		this.add(panelBarChart);
//		panelBarChart.setBounds(1100, top - 20, 420, 400);
//
//		// chart
//	}
}
