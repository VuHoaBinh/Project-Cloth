package GUI;

//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import javax.swing.ButtonGroup;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumnModel;
//import components.button.Button;
//import components.controlPanel.ControlPanel;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import utils.Utils;
//
//public class ThongKeDoanhThu extends JPanel{
//	private ControlPanel pnlControl;
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	private JRadioButton rdoByDay;
//	private JRadioButton rdoByMonth;
//	private JRadioButton rdoByYear;
//	private JRadioButton rdoByQuarter;
//	private TextField txtByDay;
//	private TextField txtByMonth;
//	private TextField txtByYear;
//	private TextField txtByQuarter;
//	private Button btnTimKiem;
//	private Button btnBack;
//	private TextField txtThanhTien;
//	private Button btnThongKe;
//
//	public ThongKeDoanhThu(ManHinhChinh main) {
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
//		JLabel lblTitle = new JLabel("Thống kê doanh thu");
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
//		pnlRow1.setBounds(left+320, top+20, 1100, 70);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//
//
//		txtByDay  = new TextField();
//		txtByDay.setLabelText("Xem theo ngày:");
//		txtByDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByDay.setBackground(new Color(203, 239, 255));
//		txtByDay.setBounds(0, 0, 150, 55);
//		rdoByDay = new JRadioButton();
//		rdoByDay.setBounds(150, 30, 25, 25);
//		rdoByDay.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByDay);
//		pnlRow1.add(rdoByDay);
//
//		txtByMonth  = new TextField();
//		txtByMonth.setLabelText("Xem theo tháng:");
//		txtByMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByMonth.setBackground(new Color(203, 239, 255));
//		txtByMonth.setBounds(180, 0, 150, 55);
//		rdoByMonth = new JRadioButton();
//		rdoByMonth.setBounds(330, 30, 25, 25);
//		rdoByMonth.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByMonth);
//		pnlRow1.add(rdoByMonth);
//
//		txtByYear  = new TextField();
//		txtByYear.setLabelText("Xem theo năm:");
//		txtByYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByYear.setBackground(new Color(203, 239, 255));
//		txtByYear.setBounds(360, 0, 150, 55);
//		rdoByYear = new JRadioButton();
//		rdoByYear.setBounds(510, 30, 25, 25);
//		rdoByYear.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByYear);
//		pnlRow1.add(rdoByYear);
//
//		txtByQuarter  = new TextField();
//		txtByQuarter.setLabelText("Xem theo quý:");
//		txtByQuarter.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByQuarter.setBackground(new Color(203, 239, 255));
//		txtByQuarter.setBounds(540, 0, 150, 55);
//		rdoByQuarter = new JRadioButton();
//		rdoByQuarter.setBounds(690, 30, 25, 25);
//		rdoByQuarter.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByQuarter);
//		pnlRow1.add(rdoByQuarter);
//
//		ButtonGroup radioGroup = new ButtonGroup();
//	    radioGroup.add(rdoByDay);
//	    radioGroup.add(rdoByMonth);
//	    radioGroup.add(rdoByYear);
//	    radioGroup.add(rdoByQuarter);
//
//	    JPanel pnlRow2 = new JPanel();
//		pnlRow2.setBackground(Utils.secondaryColor);
//		pnlRow2.setBounds(left+320, top+50, 1100, 70);
//		top += padding + 5;
//		add(pnlRow2);
//		pnlRow2.setLayout(null);
//
//
//
//		btnBack = new Button("");
//		btnBack.setIcon(Utils.getImageIcon("back.png"));
//		btnBack.setRadius(8);
//		btnBack.setForeground(Color.WHITE);
//		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnBack.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnBack.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnBack.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnBack.setBorderColor(new Color(203, 239, 255));
//		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnBack.setBounds(0, 0, 150, 55);
//		pnlRow2.add(btnBack);
//
//		btnTimKiem = new Button("");
//		btnTimKiem.setIcon(Utils.getImageIcon("search_34x34.png"));
//		btnTimKiem.setRadius(8);
//		btnTimKiem.setForeground(Color.WHITE);
//		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnTimKiem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnTimKiem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnTimKiem.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnTimKiem.setBorderColor(new Color(203, 239, 255));
//		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnTimKiem.setBounds(180, 0, 150, 55);
//		pnlRow2.add(btnTimKiem);
//
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 300, 110, 600, 300);
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
//		tableModel = new DefaultTableModel(new String[] { "Mã sản phẫm", "Tên sản phẫm", "Số lượng","Doanh thu"}, 0);
//		tbl.setModel(tableModel);
//		tbl.setFocusable(false);
//		tblHeader.setBackground(Utils.primaryColor);
//		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setBackground(Color.WHITE);
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModel.getColumn(0).setPreferredWidth(150);
//		tableColumnModel.getColumn(1).setPreferredWidth(150);
//		tableColumnModel.getColumn(2).setPreferredWidth(150);
//		tableColumnModel.getColumn(3).setPreferredWidth(150);
//
//
//		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
//		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setRowHeight(36);
//		scr.setViewportView(tbl);
//
////		pnlControl = new ControlPanel(Utils.getLeft(1100),topPnlControl - 300 , main);
////		this.add(pnlControl);
//
//
//		JPanel pnlRow3 = new JPanel();
//		pnlRow3.setBackground(Utils.secondaryColor);
//		pnlRow3.setBounds(left-550, top+270, 1100, 65);
//		top += padding + 5;
//		add(pnlRow3);
//		pnlRow3.setLayout(null);
//
//		txtThanhTien = new TextField();
//		txtThanhTien.setEditable(false);
//		txtThanhTien.setLabelText("Tổng doanh thu:");
//		txtThanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtThanhTien.setBackground(new Color(203, 239, 255));
//		txtThanhTien.setBounds(250, 0, 250, 55);
//		JLabel vndLabel = new JLabel("VND");
//		vndLabel.setBounds(510, 20, 50, 55);
//		pnlRow3.add(txtThanhTien);
//		pnlRow3.add(vndLabel);
//
//		btnThongKe = new Button("Thống kê");
//		btnThongKe.setBounds(560, 25, 170, 40);
//		btnThongKe.setIcon(Utils.getImageIcon("tkdoanhthu.png"));
//		btnThongKe.setRadius(8);
//		btnThongKe.setForeground(Color.WHITE);
//		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnThongKe.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnThongKe.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnThongKe.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnThongKe.setBorderColor(new Color(203, 239, 255));
//		btnThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow3.add(btnThongKe);
//	}
//}
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileOutputStream;
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
//import components.controlPanel.ControlPanel;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import dao.ChiTietHoaDon_Dao;
//import dao.HoaDon_Dao;
//import dao.NhanVien_Dao;
//import dao.TaiKhoan_Dao;
//import entity.ChiTietHoaDon;
//import entity.HoaDon;
//import entity.NhanVien;
//import entity.TaiKhoan;
//import utils.Utils;
//
//public class ThongKeDoanhThu extends JPanel implements ActionListener {
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	private JRadioButton rdoByDay;
//	private JRadioButton rdoByMonth;
//	private JRadioButton rdoByYear;
//	private JRadioButton rdoByQuarter;
//	private TextField txtByDay;
//	private TextField txtByMonth;
//	private TextField txtByYear;
//	private Button btnTimKiem;
//	private Button btnBack;
//	private TextField txtThanhTien;
//	private ChiTietHoaDon_Dao ChiTietHoaDon_dao;
//	private HoaDon_Dao HoaDon_dao;
//	private Button btnXuatFilePDF;
//	private TaiKhoan_Dao TaiKhoan_dao;
//	private NhanVien_Dao NhanVien_dao;
//	private String maTK;
//	public ThongKeDoanhThu(ManHinhChinh main, String maTK) {
//		this.main = main;
//		this.maTK = maTK;
//		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
//		int top = padding;
//		int left = Utils.getLeft(792);
//		ChiTietHoaDon_dao = new ChiTietHoaDon_Dao();
//		HoaDon_dao = new HoaDon_Dao();
//		TaiKhoan_dao = new TaiKhoan_Dao();
//		NhanVien_dao = new NhanVien_Dao();
//
//		setBackground(Utils.secondaryColor);
//		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
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
//		JLabel lblTitle = new JLabel("Thống kê doanh thu");
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
//		pnlRow1.setBounds(left + 580, top + 20, 1100, 70);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//
//		txtByDay = new TextField();
//		txtByDay.setLabelText("Xem theo ngày:");
//		txtByDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByDay.setBackground(new Color(203, 239, 255));
//		txtByDay.setBounds(0, 0, 150, 55);
//		rdoByDay = new JRadioButton();
//		rdoByDay.setBounds(150, 30, 25, 25);
//		rdoByDay.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByDay);
//		pnlRow1.add(rdoByDay);
//
//		txtByMonth = new TextField();
//		txtByMonth.setLabelText("Xem theo tháng:");
//		txtByMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByMonth.setBackground(new Color(203, 239, 255));
//		txtByMonth.setBounds(180, 0, 150, 55);
//		rdoByMonth = new JRadioButton();
//		rdoByMonth.setBounds(330, 30, 25, 25);
//		rdoByMonth.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByMonth);
//		pnlRow1.add(rdoByMonth);
//
//		txtByYear = new TextField();
//		txtByYear.setLabelText("Xem theo năm:");
//		txtByYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtByYear.setBackground(new Color(203, 239, 255));
//		txtByYear.setBounds(360, 0, 150, 55);
//		rdoByYear = new JRadioButton();
//		rdoByYear.setBounds(510, 30, 25, 25);
//		rdoByYear.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtByYear);
//		pnlRow1.add(rdoByYear);
//
//		ButtonGroup radioGroup = new ButtonGroup();
//		radioGroup.add(rdoByDay);
//		radioGroup.add(rdoByMonth);
//		radioGroup.add(rdoByYear);
//		radioGroup.add(rdoByQuarter);
//
//		JPanel pnlRow2 = new JPanel();
//		pnlRow2.setBackground(Utils.secondaryColor);
//		pnlRow2.setBounds(left + 580, top + 50, 1100, 140);
//		top += padding + 5;
//		add(pnlRow2);
//		pnlRow2.setLayout(null);
//
//		btnBack = new Button("");
//		btnBack.setIcon(Utils.getImageIcon("back.png"));
//		btnBack.setRadius(8);
//		btnBack.setForeground(Color.WHITE);
//		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnBack.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnBack.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnBack.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnBack.setBorderColor(new Color(203, 239, 255));
//		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnBack.setBounds(100, 0, 150, 55);
//		pnlRow2.add(btnBack);
//
//		btnTimKiem = new Button("");
//		btnTimKiem.setIcon(Utils.getImageIcon("search_34x34.png"));
//		btnTimKiem.setRadius(8);
//		btnTimKiem.setForeground(Color.WHITE);
//		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnTimKiem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnTimKiem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnTimKiem.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnTimKiem.setBorderColor(new Color(203, 239, 255));
//		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnTimKiem.setBounds(320, 0, 150, 55);
//		pnlRow2.add(btnTimKiem);
//
//		btnXuatFilePDF= new Button("Xuất File PDF");
//		btnXuatFilePDF.setIcon(Utils.getImageIcon("pdf.png"));
//		btnXuatFilePDF.setRadius(8);
//		btnXuatFilePDF.setForeground(Color.WHITE);
//		btnXuatFilePDF.setFont(new Font("Segoe UI", Font.PLAIN, 20));
//		btnXuatFilePDF.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnXuatFilePDF.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnXuatFilePDF.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnXuatFilePDF.setBorderColor(new Color(203, 239, 255));
//		btnXuatFilePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnXuatFilePDF.setBounds(100, 70, 150, 55);
//		pnlRow2.add(btnXuatFilePDF);
//
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 300, 110, 850, 300);
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
//				new String[] { "Mã SP", "Tên sản phẫm", "Số lượng", "Đơn giá", "Doanh thu", "Ngày lập" }, 0);
//		tbl.setModel(tableModel);
//		tbl.setFocusable(false);
//		tblHeader.setBackground(Utils.primaryColor);
//		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		tbl.setBackground(Color.WHITE);
//		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//		tableColumnModel.getColumn(0).setPreferredWidth(100);
//		tableColumnModel.getColumn(1).setPreferredWidth(150);
//		tableColumnModel.getColumn(2).setPreferredWidth(150);
//		tableColumnModel.getColumn(3).setPreferredWidth(150);
//		tableColumnModel.getColumn(4).setPreferredWidth(150);
//		tableColumnModel.getColumn(5).setPreferredWidth(170);
//
//		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
//		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
//		tableColumnModel.getColumn(3).setCellRenderer(dtcr);
//		tableColumnModel.getColumn(4).setCellRenderer(dtcr);
//
//		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
//		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
//		tbl.setRowHeight(36);
//		scr.setViewportView(tbl);
//
//		JPanel pnlRow3 = new JPanel();
//		pnlRow3.setBackground(Utils.secondaryColor);
//		pnlRow3.setBounds(left - 550, top + 270, 1100, 65);
//		top += padding + 5;
//		add(pnlRow3);
//		pnlRow3.setLayout(null);
//
//		txtThanhTien = new TextField();
//		txtThanhTien.setEditable(false);
//		txtThanhTien.setLabelText("Tổng doanh thu:");
//		txtThanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		txtThanhTien.setBackground(new Color(203, 239, 255));
//		txtThanhTien.setBounds(250, 0, 250, 55);
//		JLabel vndLabel = new JLabel("VND");
//		vndLabel.setBounds(510, 20, 50, 55);
//		pnlRow3.add(txtThanhTien);
//		pnlRow3.add(vndLabel);
//
//		btnTimKiem.addActionListener(this);
//		btnBack.addActionListener(this);
//		btnXuatFilePDF.addActionListener(this);
//
//		rdoByDay.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				txtByDay.setEditable(true);
//				txtByMonth.setEditable(true);
//				txtByYear.setEditable(true);
//			}
//		});
//
//		rdoByMonth.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				txtByDay.setEditable(true);
//				txtByMonth.setEditable(true);
//				txtByYear.setEditable(true);
//			}
//		});
//		rdoByYear.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				txtByDay.setEditable(true);
//				txtByMonth.setEditable(true);
//				txtByYear.setEditable(true);
//			}
//		});
//
//		getHD();
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		Object src = e.getSource();
//		if (src.equals(btnTimKiem) && rdoByYear.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraNam()) {
//				getHD();
//				ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//				double tong = 0;
//				int i = 0;
//				int rowcount = tableModel.getRowCount();
//				while (i < rowcount) {
//					int year = layNam(tbl.getValueAt(i, 5).toString());
////					JOptionPane.showMessageDialog(this, year);
//					int txtnam = Integer.parseInt(txtByYear.getText());
//					if (year == txtnam) {
//						tong += Double.parseDouble(tbl.getValueAt(i, 4).toString());
//					} else {
//						dsxoa.add(i);
//					}
//					i++;
//				}
//				for (int y = dsxoa.size() - 1; y >= 0; y--) {
//					tableModel.removeRow(dsxoa.get(y));
//				}
//				txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//			}
//
//		} else if (src.equals(btnTimKiem) && rdoByMonth.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraThang() && ktraNam()) {
//				getHD();
//				ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//				double tong = 0;
//				int i = 0;
//				int rowcount = tableModel.getRowCount();
//				while (i < rowcount) {
//					int year = layNam(tbl.getValueAt(i, 5).toString());
//					int month = layThang(tbl.getValueAt(i, 5).toString());
//					int txtnam = Integer.parseInt(txtByYear.getText());
//					int txtthang = Integer.parseInt(txtByMonth.getText());
//					if (year == txtnam && month == txtthang) {
//						tong += Double.parseDouble(tbl.getValueAt(i, 4).toString());
//					} else {
//						dsxoa.add(i);
//					}
//					i++;
//				}
//				for (int y = dsxoa.size() - 1; y >= 0; y--) {
//					tableModel.removeRow(dsxoa.get(y));
//				}
//				txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//			}
//		} else if (src.equals(btnTimKiem) && rdoByDay.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraNam() && ktraThang()) {
//				getHD();
//				int txtnam = Integer.parseInt(txtByYear.getText());
//				int txtthang = Integer.parseInt(txtByMonth.getText());
//
//				if(ktraNgay(txtnam, txtthang)) {
//					int txtngay = Integer.parseInt(txtByDay.getText());
//					ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//					double tong = 0;
//					int i = 0;
//					int rowcount = tableModel.getRowCount();
//					while (i < rowcount) {
//						int year = layNam(tbl.getValueAt(i, 5).toString());
//						int month = layThang(tbl.getValueAt(i, 5).toString());
//						int ngay = layTuan(tbl.getValueAt(i, 5).toString());
//
//						if (year == txtnam && month == txtthang && ngay == txtngay) {
//							tong += Double.parseDouble(tbl.getValueAt(i, 4).toString());
//						} else {
//							dsxoa.add(i);
//						}
//						i++;
//					}
//					for (int y = dsxoa.size() - 1; y >= 0; y--) {
//						tableModel.removeRow(dsxoa.get(y));
//					}
//					txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//				}
//			}
//		}
//
//		else if (src.equals(btnBack)) {
//			tableModel.setRowCount(0);
//			getHD();
//			txtThanhTien.setText("");
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
//			String sql = "select cthd.maSanPham, sp.tenSanPham, "
//					+ "cthd.soLuong, sp.donGia, (cthd.soLuong*sp.donGia) as 'ThanhTien',"
//					+ " hd.ngayLap from ChiTietHoaDon cthd join HoaDon hd on cthd.maHoaDon = hd.maHoaDon"
//					+ " join SanPham sp on sp.maSanPham = cthd.maSanPham";
//
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//
//			while (rs.next()) {
//				Object[] data = { rs.getString(1), rs.getString(2), rs.getInt(3),Math.round(rs.getDouble(4)), Math.round(rs.getDouble(5)),
//						rs.getString(6) };
//				tableModel.addRow(data);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public boolean ktraNam() {
//		LocalDateTime time = LocalDateTime.now();
//		try {
//			int namNhap = Integer.parseInt(txtByYear.getText());
//			if (namNhap > 2021) {
//				return true;
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "Năm phải lớn hơn năm 2021");
//				txtByYear.requestFocus();
//				return false;
//			}
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Năm phải là số nguyên dương!");
//			txtByYear.requestFocus();
//			return false;
//		}
//	}
//
////	public boolean ktraThang() {
////		LocalDateTime time = LocalDateTime.now();
////		try {
////			int namNhap = Integer.parseInt(txtByYear.getText());
////			int thangNhap = Integer.parseInt(txtByMonth.getText());
////			if (namNhap == time.getYear()) {
////				if (thangNhap > time.getMonthValue()) {
////					JOptionPane.showMessageDialog(this, "Phải nhỏ hơn tháng hiện tại");
////					txtByMonth.requestFocus();
////					return false;
////				}
////				return true;
////			} else
////				return true;
////		} catch (NumberFormatException ex) {
////			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
////			txtByYear.requestFocus();
////			return false;
////		}
////	}
//
//	public boolean ktraThang() {
//	LocalDateTime time = LocalDateTime.now();
//	try {
//		int thangNhap = Integer.parseInt(txtByMonth.getText());
//			if (thangNhap >=1 && thangNhap <= 12) {
//				return true;
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "tháng không hợp lệ");
//				txtByMonth.requestFocus();
//				return false;
//			}
//	} catch (NumberFormatException ex) {
//		JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
//		txtByMonth.requestFocus();
//		return false;
//	}
//}
//	public boolean ktraNgay(int nam, int thang) {
//		try {
//			int ngaynhap = Integer.parseInt(txtByDay.getText());
//			if ((nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0) {
//				if(thang == 2 && ngaynhap > 29) {
//					JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
//					txtByDay.requestFocus();
//					return false;
//				}
//				else {
//					if(thang == 2 && ngaynhap > 28) {
//						JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
//						txtByDay.requestFocus();
//						return false;
//					}
//				}
//			}
//			else {
//				if(ngaynhap < 1 || ngaynhap > 31){
//					JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
//					txtByDay.requestFocus();
//					return false;
//				}
//			}
//			return true;
//
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "ngày phải là số nguyên dương!");
//			txtByDay.requestFocus();
//			return false;
//		}
//	}
//
//	public int layNam(String ngay) {
//		String year = ngay.substring(0, 4);
//		return Integer.parseInt(year);
//	}
//
//	public int layThang(String ngay) {
//		String thang = ngay.substring(5, 7);
//		return Integer.parseInt(thang);
//	}
//
//	public int layTuan(String ngay) {
//		String thang = ngay.substring(8, 10);
//		return Integer.parseInt(thang);
//	}
//	public void xuatFile() {
////		System.setProperty("file.encoding", "utf-8");
//		String namefile = "ThongKeDoanhThu";
//		String tieuDe = "Thống kê doanh thu";
//		if(rdoByDay.isSelected()) {
//			namefile = "ThongKeDoanhThuNgay"+txtByDay.getText()+"-"+txtByMonth.getText()+"-"+txtByYear.getText();
//			tieuDe = "Thống kê doanh thu ngày "+txtByDay.getText()+"-"+txtByMonth.getText()+"-"+txtByYear.getText();
//		}
//		else if(rdoByMonth.isSelected()) {
//			namefile = "ThongKeDoanhThuThang"+txtByMonth.getText()+"-"+txtByYear.getText();
//			tieuDe = "Thống kê doanh thu tháng "+txtByMonth.getText()+"-"+txtByYear.getText();
//
//		}
//		else if(rdoByYear.isSelected()) {
//			namefile = "ThongKeDoanhThuNam"+txtByYear.getText();
//			tieuDe = "Thống kê doanh thu năm "+txtByYear.getText();
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
//            Paragraph paragraphTongTienLine2 = new Paragraph("              Tổng doanh thu:" + txtThanhTien.getText(), textFont);
//            doc.add(paragraphTongTienLine2);
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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entities.NhanVien;
import entities.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import url.RmiConfig;
import utils.Utils;

public class ThongKeDoanhThu extends JPanel implements ActionListener {
	private EntityManager entityManager;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private JRadioButton rdoByDay;
	private JRadioButton rdoByMonth;
	private JRadioButton rdoByYear;
	private JRadioButton rdoByQuarter;
	private JComboBox<String> cbByDay;
	private JComboBox<String> cbByMonth;
	private JComboBox<String> cbByYear;
	private Button btnTimKiem;
	private Button btnBack;
	private TextField txtThanhTien;
	private ChiTietHoaDonDAO ChiTietHoaDon_dao;
	private HoaDonDAO HoaDon_dao;
	private Button btnXuatFilePDF;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private String maTK;
	private TextField txtTimSP;
	private JRadioButton rdoTimSP;
	private Button btnDoanhThu;
	private JComboBox<String> cbByWeek;
	private JRadioButton rdoByWeek;
	private TextField txtTimNhanVien;
	private TextField txtTimKH;
	private Button btnThongKe;
	private ButtonGroup radioGroup;
	private DecimalFormat formatTienTe;
	private DecimalFormat format;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	private NumberFormat formatter;

	public ThongKeDoanhThu(ManHinhChinh main, String maTK)
			throws MalformedURLException, NotBoundException, RemoteException {
		this.entityManager = Persistence.createEntityManagerFactory("JPA_ORM_MSSQL").createEntityManager();
		dataset = new DefaultCategoryDataset();
		formatter = NumberFormat.getInstance();
		this.main = main;
		this.maTK = maTK;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		ChiTietHoaDon_dao = (ChiTietHoaDonDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/chiTietHoaDonDao");
		HoaDon_dao = (HoaDonDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/hoaDonDao");
		TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/taiKhoanDao");
		NhanVien_dao = (NhanVienDAO) Naming.lookup("rmi://" + RmiConfig.RMI_URL + ":2008/nhanVienDao");
		formatTienTe = new DecimalFormat("#,##0₫");
		format = new DecimalFormat("#,##0");

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
		pnlHeader.setBounds(16, 18, 1054, 70);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Thống kê doanh thu");
		lblTitle.setBounds(450, 10, 400, 40);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);

		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left + 580, top + 20, 1100, 70);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		cbByDay = new JComboBox<String>();
//		txtByDay.setLabelText("Ngày:");
		cbByDay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbByDay.setBackground(new Color(203, 239, 255));
		cbByDay.setBounds(0, 0, 150, 40);
		rdoByDay = new JRadioButton();
		rdoByDay.setBounds(150, 11, 25, 25);
		rdoByDay.setBackground(new Color(203, 239, 255));
		pnlRow1.add(cbByDay);
		pnlRow1.add(rdoByDay);
		cbByDay.addItem("Ngày");
		for (int i = 1; i <= 31; i++) {
			cbByDay.addItem(i + "");
		}

		cbByMonth = new JComboBox<String>();
//		txtByMonth.setLabelText("Tháng:");
		cbByMonth.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbByMonth.setBackground(new Color(203, 239, 255));
		cbByMonth.setBounds(180, 0, 150, 40);
		rdoByMonth = new JRadioButton();
		rdoByMonth.setBounds(330, 11, 25, 25);
		rdoByMonth.setBackground(new Color(203, 239, 255));
		pnlRow1.add(cbByMonth);
		pnlRow1.add(rdoByMonth);
		cbByMonth.addItem("Tháng");
		for (int i = 1; i <= 12; i++) {
			cbByMonth.addItem(i + "");
		}

		cbByYear = new JComboBox<String>();
//		txtByYear.setLabelText("Năm:");
		cbByYear.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbByYear.setBackground(new Color(203, 239, 255));
		cbByYear.setBounds(360, 0, 150, 40);
		rdoByYear = new JRadioButton();
		rdoByYear.setBounds(510, 11, 25, 25);
		rdoByYear.setBackground(new Color(203, 239, 255));
		pnlRow1.add(cbByYear);
		pnlRow1.add(rdoByYear);
		cbByYear.addItem("Năm");
		for (int i = 2021; i <= LocalDate.now().getYear(); i++) {
			cbByYear.addItem(i + "");
		}

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left + 580, top, 1100, 150);
		top += padding + 5;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		cbByWeek = new JComboBox<String>();
//		txtByWeek.setLabelText("Tuần:");
		cbByWeek.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cbByWeek.setBackground(new Color(203, 239, 255));
		cbByWeek.setBounds(0, 25, 150, 40);
		rdoByWeek = new JRadioButton();
		rdoByWeek.setBounds(150, 33, 25, 25);
		rdoByWeek.setBackground(new Color(203, 239, 255));
		pnlRow2.add(cbByWeek);
		pnlRow2.add(rdoByWeek);
		cbByWeek.addItem("Tuần");
		for (int i = 1; i <= 5; i++) {
			cbByWeek.addItem(i + "");
		}

		radioGroup = new ButtonGroup();
		radioGroup.add(rdoByDay);
		radioGroup.add(rdoByMonth);
		radioGroup.add(rdoByYear);
		radioGroup.add(rdoByWeek);
		txtTimNhanVien = new TextField();
		txtTimNhanVien.setLabelText("Tìm nhân viên:");
		txtTimNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTimNhanVien.setBackground(new Color(203, 239, 255));
		txtTimNhanVien.setBounds(190, 15, 150, 55);
		pnlRow2.add(txtTimNhanVien);

		txtTimKH = new TextField();
		txtTimKH.setLabelText("Tìm khách hàng:");
		txtTimKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTimKH.setBackground(new Color(203, 239, 255));
		txtTimKH.setBounds(360, 15, 150, 55);
		pnlRow2.add(txtTimKH);

		btnBack = new Button("Làm mới");
		btnBack.setIcon(Utils.getImageIcon("back.png"));
		btnBack.setRadius(8);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnBack.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnBack.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnBack.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnBack.setBorderColor(new Color(203, 239, 255));
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBounds(0, 90, 150, 50);
		pnlRow2.add(btnBack);

		btnThongKe = new Button("Thống kê");
		btnThongKe.setIcon(Utils.getImageIcon("search_34x34.png"));
		btnThongKe.setRadius(8);
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThongKe.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThongKe.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThongKe.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThongKe.setBorderColor(new Color(203, 239, 255));
		btnThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThongKe.setBounds(190, 90, 150, 50);
		pnlRow2.add(btnThongKe);

		btnXuatFilePDF = new Button("Xuất File PDF");
		btnXuatFilePDF.setIcon(Utils.getImageIcon("pdf.png"));
		btnXuatFilePDF.setRadius(8);
		btnXuatFilePDF.setForeground(Color.WHITE);
		btnXuatFilePDF.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXuatFilePDF.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXuatFilePDF.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXuatFilePDF.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXuatFilePDF.setBorderColor(new Color(203, 239, 255));
		btnXuatFilePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXuatFilePDF.setBounds(360, 90, 150, 50);
		pnlRow2.add(btnXuatFilePDF);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left - 300, 110, 850, 475);
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
				new String[] { "Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày lập", "Tổng tiền" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(150);
		tableColumnModel.getColumn(1).setPreferredWidth(200);
		tableColumnModel.getColumn(2).setPreferredWidth(200);
		tableColumnModel.getColumn(3).setPreferredWidth(150);
		tableColumnModel.getColumn(4).setPreferredWidth(150);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		tableColumnModel.getColumn(3).setCellRenderer(dtcr);
		tableColumnModel.getColumn(4).setCellRenderer(dtcr);

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left - 550, top + 400, 1100, 65);
		top += padding + 5;
		add(pnlRow3);
		pnlRow3.setLayout(null);

		txtThanhTien = new TextField();
		txtThanhTien.setEditable(false);
		txtThanhTien.setLabelText("Tổng doanh thu:");
		txtThanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtThanhTien.setBackground(new Color(203, 239, 255));
		txtThanhTien.setBounds(250, 0, 250, 55);
		JLabel vndLabel = new JLabel("VND");
		vndLabel.setBounds(510, 20, 50, 55);
		pnlRow3.add(txtThanhTien);
		pnlRow3.add(vndLabel);

		chartPanel = new ChartPanel(null);
		chartPanel.setBounds(left + 555, 285, 600, 300);
		add(chartPanel);

		btnThongKe.addActionListener(this);
		btnBack.addActionListener(this);
		btnXuatFilePDF.addActionListener(this);
		cbByDay.setEditable(false);
		cbByMonth.setEditable(false);
		cbByYear.setEditable(false);
		cbByWeek.setEditable(false);
		rdoByDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				cbByWeek.setEditable(false);
				cbByDay.setEditable(true);
				cbByMonth.setEditable(true);
				cbByYear.setEditable(true);
			}
		});

		rdoByMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbByWeek.setEditable(false);
				cbByDay.setEditable(false);
				cbByMonth.setEditable(true);
				cbByYear.setEditable(true);
			}
		});
		rdoByYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbByWeek.setEditable(false);
				cbByDay.setEditable(false);
				cbByMonth.setEditable(false);
				cbByYear.setEditable(true);
			}
		});
		rdoByWeek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbByWeek.setEditable(true);
				cbByDay.setEditable(false);
				cbByMonth.setEditable(true);
				cbByYear.setEditable(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnThongKe) && rdoByYear.isSelected()) {
			dataset = new DefaultCategoryDataset();
			tableModel.setRowCount(0);
			if (ktraNam()) {
				if (txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoNam(nam, "", "");
				} else if (!txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoNam(nam, "", txtTimKH.getText());
				} else if (txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoNam(nam, txtTimNhanVien.getText(), "");
				} else if (!txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoNam(nam, txtTimNhanVien.getText(), txtTimKH.getText());
				}
			}

		} else if (src.equals(btnThongKe) && rdoByMonth.isSelected()) {
			dataset = new DefaultCategoryDataset();
			tableModel.setRowCount(0);
			if (ktraThang() && ktraNam()) {
				if (txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoThang(thang, nam, "", "");
				} else if (!txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
//					JOptionPane.showMessageDialog(this, "AAA");
					getDuLieuTheoThang(thang, nam, "", txtTimKH.getText());
				} else if (txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoThang(thang, nam, txtTimNhanVien.getText(), "");
				} else if (!txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					getDuLieuTheoThang(thang, nam, txtTimNhanVien.getText(), txtTimKH.getText());
				}
			}
		} else if (src.equals(btnThongKe) && rdoByDay.isSelected()) {
			dataset = new DefaultCategoryDataset();
			tableModel.setRowCount(0);
			if (ktraNam() && ktraThang()) {
				if (txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					if (ktraNgay(nam, thang)) {
						int ngay = Integer.valueOf(cbByDay.getSelectedItem().toString());
						getDuLieuTheoNgay(ngay, thang, nam, "", "");
					}
				} else if (!txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					if (ktraNgay(nam, thang)) {
						int ngay = Integer.valueOf(cbByDay.getSelectedItem().toString());
						getDuLieuTheoNgay(ngay, thang, nam, "", txtTimKH.getText());
					}
				} else if (txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					if (ktraNgay(nam, thang)) {
						int ngay = Integer.valueOf(cbByDay.getSelectedItem().toString());
						getDuLieuTheoNgay(ngay, thang, nam, txtTimNhanVien.getText(), "");
					}
				} else if (!txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					if (ktraNgay(nam, thang)) {
						int ngay = Integer.valueOf(cbByDay.getSelectedItem().toString());
						getDuLieuTheoNgay(ngay, thang, nam, txtTimNhanVien.getText(), txtTimKH.getText());
					}
				}
			}
		} else if (src.equals(btnThongKe) && rdoByWeek.isSelected()) {
			dataset = new DefaultCategoryDataset();
			tableModel.setRowCount(0);
			if (ktraNam() && ktraThang()) {
				if (txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					int tuan = Integer.valueOf(cbByWeek.getSelectedItem().toString());
					getDuLieuTheoTuan(tuan, thang, nam, "", "");
				} else if (!txtTimKH.getText().equals("") && txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					int tuan = Integer.valueOf(cbByWeek.getSelectedItem().toString());
					getDuLieuTheoTuan(tuan, thang, nam, "", txtTimKH.getText());
				} else if (txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					int tuan = Integer.valueOf(cbByWeek.getSelectedItem().toString());
					getDuLieuTheoTuan(tuan, thang, nam, txtTimNhanVien.getText(), "");
				} else if (!txtTimKH.getText().equals("") && !txtTimNhanVien.getText().equals("")) {
					chartPanel.setChart(null);
					int thang = Integer.valueOf(cbByMonth.getSelectedItem().toString());
					int nam = Integer.valueOf(cbByYear.getSelectedItem().toString());
					int tuan = Integer.valueOf(cbByWeek.getSelectedItem().toString());
					getDuLieuTheoTuan(tuan, thang, nam, txtTimNhanVien.getText(), txtTimKH.getText());
				}
			}
		}

		else if (src.equals(btnBack)) {
			tableModel.setRowCount(0);
			cbByDay.setSelectedIndex(0);
			cbByMonth.setSelectedIndex(0);
			cbByYear.setSelectedIndex(0);
			cbByWeek.setSelectedIndex(0);
			txtTimKH.setText("");
			txtTimNhanVien.setText("");
			txtThanhTien.setText("");
			radioGroup.clearSelection();
			chartPanel.setChart(null);
			cbByDay.setEditable(false);
			cbByMonth.setEditable(false);
			cbByYear.setEditable(false);
			cbByWeek.setEditable(false);
		} else if (src.equals(btnXuatFilePDF)) {
			int row = tableModel.getRowCount();
			if (row == 0)
				JOptionPane.showMessageDialog(this, "Không có dữ liệu để xuất file");
			else
				xuatFile();
		}
	}

	public void getDoanhThu() {

		try {
			Query query = entityManager.createNativeQuery(
					"SELECT TOP 5 cthd.maSanPham, sp.tenSanPham, (cthd.soLuong) AS SoLuong, sp.donGia, (SUM(cthd.soLuong) * sp.donGia) AS ThanhTien, hd.ngayLap\r\n"
							+ " FROM ChiTietHoaDon cthd JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon\r\n"
							+ " JOIN SanPham sp ON sp.maSanPham = cthd.maSanPham\r\n"
							+ " GROUP BY cthd.maSanPham, sp.tenSanPham, sp.donGia, cthd.soLuong, hd.ngayLap\r\n"
							+ " ORDER BY ThanhTien DESC");

			List<Object[]> list = query.getResultList();
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], obj[4], obj[5] };
				tableModel.addRow(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getHD() {

		try {
			Query query = entityManager.createNativeQuery("select cthd.maSanPham, sp.tenSanPham, "
					+ "cthd.soLuong, sp.donGia, (cthd.soLuong*sp.donGia) as 'ThanhTien',"
					+ " hd.ngayLap from ChiTietHoaDon cthd join HoaDon hd on cthd.maHoaDon = hd.maHoaDon"
					+ " join SanPham sp on sp.maSanPham = cthd.maSanPham");
			List<Object[]> list = query.getResultList();
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], obj[4], obj[5] };
				tableModel.addRow(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDuLieuTheoTuan(int tuan, int thang, int nam, String tenNV, String tenKH) {
		Double tongtien = 0.0;
		List<Object[]> list;
		try {
			list = HoaDon_dao.thongKeDoanhThuTheoTuan(tuan, thang, nam, tenNV, tenKH);
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], formatter.format(obj[4]) };
				dataset.addValue(Double.valueOf(obj[4].toString()), "Doanh thu", obj[3].toString());
				tongtien += Double.valueOf(obj[4].toString());
				tableModel.addRow(data);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			ResultSet rs = stmt.executeQuery();

//			while (rs.next()) {
//				Object[] data = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), format.format(rs.getDouble(5))};
//				tongtien += rs.getDouble(5);
//				tableModel.addRow(data);
//			}
		chart = ChartFactory.createBarChart("Doanh thu theo tuần thứ " + tuan + "trong tháng " + thang + "/" + nam,
				"Mã hóa đơn", "Doanh thu", dataset, PlotOrientation.VERTICAL, false, true, false);
		chartPanel.setChart(chart);

		txtThanhTien.setText(formatTienTe.format(tongtien));
	}

	public void getDuLieuTheoNgay(int ngay, int thang, int nam, String tenNV, String tenKH) {
		Double tongtien = 0.0;

		List<Object[]> list;
		try {
			list = HoaDon_dao.thongKeDoanhThuTheoNgay(ngay, thang, nam, tenNV, tenKH);
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], formatter.format(obj[4]) };
				tongtien += Double.valueOf(obj[4].toString());
				dataset.addValue(Double.valueOf(obj[4].toString()), "Doanh thu", obj[3].toString());
				tableModel.addRow(data);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chart = ChartFactory.createBarChart("Doanh thu theo ngày " + ngay + "/" + thang + "/" + nam, "Ngày",
				"Doanh thu", dataset, PlotOrientation.VERTICAL, true, true, false);
		chartPanel.setChart(chart);
		txtThanhTien.setText(formatTienTe.format(tongtien));
	}

	@SuppressWarnings("unchecked")
	public void getDuLieuTheoThang(int thang, int nam, String tenNV, String tenKH) {
		Double tongtien = 0.0;

		List<Object[]> list;
		try {
			list = HoaDon_dao.thongKeDoanhThuTheoThang(thang, nam, tenNV, tenKH);
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], formatter.format(obj[4]) };
				dataset.addValue(Double.valueOf(obj[4].toString()), "Doanh thu", obj[3].toString());
				tongtien += Double.valueOf(obj[4].toString());
				tableModel.addRow(data);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chart = ChartFactory.createBarChart("Biểu đồ doanh thu theo tháng " + thang, "Thời gian", "Doanh thu", dataset,
				PlotOrientation.VERTICAL, false, true, false);
		chartPanel.setChart(chart);
		txtThanhTien.setText(formatTienTe.format(tongtien));
	}

	@SuppressWarnings("unchecked")
	public void getDuLieuTheoNam(int nam, String tenNV, String tenKH) {
		Double tongtien = 0.0;

		List<Object[]> list;
		List<Object[]> list2;
		try {
			list = HoaDon_dao.thongKeDoanhThuTheoNam(nam, tenNV, tenKH);
			for (Object[] obj : list) {
				Object[] data = { obj[0], obj[1], obj[2], obj[3], formatter.format(obj[4]) };
				tongtien += Double.valueOf(obj[4].toString());
				tableModel.addRow(data);
			}
			list2 = HoaDon_dao.thongKeDoanhThuTheoNamChart(nam, tenNV, tenKH);
			for (Object[] obj : list2) {
				dataset.addValue(Double.valueOf(obj[1].toString()), "Doanh thu", obj[0].toString());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chart = ChartFactory.createBarChart("Biểu đồ doanh thu theo năm " + nam, "Thời gian", "Doanh thu", dataset,
				PlotOrientation.VERTICAL, false, true, false);
		chartPanel.setChart(chart);
		txtThanhTien.setText(formatTienTe.format(tongtien));
	}

	public boolean ktraNam() {
		LocalDateTime time = LocalDateTime.now();
		try {
			int namNhap = Integer.parseInt(cbByYear.getSelectedItem().toString());
			if (namNhap > 2021) {
				return true;
			} else {
				JOptionPane.showMessageDialog(this, "Năm phải lớn hơn năm 2021");
				cbByYear.requestFocus();
				return false;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Năm phải là số nguyên dương!");
			cbByYear.requestFocus();
			return false;
		}
	}

//	public boolean ktraThang() {
//		LocalDateTime time = LocalDateTime.now();
//		try {
//			int namNhap = Integer.parseInt(txtByYear.getText());
//			int thangNhap = Integer.parseInt(txtByMonth.getText());
//			if (namNhap == time.getYear()) {
//				if (thangNhap > time.getMonthValue()) {
//					JOptionPane.showMessageDialog(this, "Phải nhỏ hơn tháng hiện tại");
//					txtByMonth.requestFocus();
//					return false;
//				}
//				return true;
//			} else
//				return true;
//		} catch (NumberFormatException ex) {
//			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
//			txtByYear.requestFocus();
//			return false;
//		}
//	}

	public boolean ktraThang() {
		LocalDateTime time = LocalDateTime.now();
		try {
			int thangNhap = Integer.parseInt(cbByMonth.getSelectedItem().toString());
			if (thangNhap >= 1 && thangNhap <= 12) {
				return true;
			} else {
				JOptionPane.showMessageDialog(this, "tháng không hợp lệ");
				cbByMonth.requestFocus();
				return false;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
			cbByMonth.requestFocus();
			return false;
		}
	}

	public boolean ktraNgay(int nam, int thang) {
		try {
			int ngaynhap = Integer.parseInt(cbByDay.getSelectedItem().toString());
			LocalDate date = LocalDate.of(nam, thang, 1);
			if (ngaynhap < 1 || ngaynhap > date.lengthOfMonth()) {
				JOptionPane.showMessageDialog(this, "ngày không hợp lệ");
				cbByDay.requestFocus();
				return false;	
			}
			return true;

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ngày phải là số nguyên dương!");
			cbByDay.requestFocus();
			return false;
		}
	}

	public int layNam(String ngay) {
		String year = ngay.substring(0, 4);
		return Integer.parseInt(year);
	}

	public int layThang(String ngay) {
		String thang = ngay.substring(5, 7);
		return Integer.parseInt(thang);
	}

	public int layTuan(String ngay) {
		String thang = ngay.substring(8, 10);
		return Integer.parseInt(thang);
	}

	public void xuatFile() {
//		System.setProperty("file.encoding", "utf-8");
		String namefile = "ThongKeDoanhThu";
		String tieuDe = "Thống kê doanh thu";
		if (rdoByDay.isSelected()) {
			namefile = "ThongKeDoanhThuNgay" + cbByDay.getSelectedItem().toString() + "-" + cbByMonth.getSelectedItem().toString() + "-"
					+ cbByYear.getSelectedItem().toString();
			tieuDe = "Thống kê doanh thu ngày " + cbByDay.getSelectedItem().toString() + "-" + cbByMonth.getSelectedItem().toString() + "-"
					+ cbByYear.getSelectedItem().toString();
		} else if (rdoByMonth.isSelected()) {
			namefile = "ThongKeDoanhThuThang" + cbByMonth.getSelectedItem().toString() + "-" + cbByYear.getSelectedItem().toString();
			tieuDe = "Thống kê doanh thu tháng " + cbByMonth.getSelectedItem().toString() + "-" + cbByYear.getSelectedItem().toString();

		} else if (rdoByYear.isSelected()) {
			namefile = "ThongKeDoanhThuNam" + cbByYear.getSelectedItem().toString();
			tieuDe = "Thống kê doanh thu năm " + cbByYear.getSelectedItem().toString();
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
			tbl.addCell(new Phrase("Mã hóa đơn", textFont));
			tbl.addCell(new Phrase("Tên nhân viên", textFont));
			tbl.addCell(new Phrase("Tên khách hàng", textFont));
			tbl.addCell(new Phrase("Ngày lập", textFont));
			tbl.addCell(new Phrase("Tổng tiền", textFont));
			for (int i = 0; i < this.tbl.getRowCount(); i++) {
				String mahd = this.tbl.getValueAt(i, 0).toString();
				String tennv = this.tbl.getValueAt(i, 1).toString();
				String tenKH = this.tbl.getValueAt(i, 2).toString();
				String ngayLap = this.tbl.getValueAt(i, 3).toString();
				String tongTien = this.tbl.getValueAt(i, 4).toString();
				String[] date = ngayLap.split("-");

				tbl.addCell(new Phrase(mahd, textFont));
				tbl.addCell(new Phrase(tennv, textFont));
				tbl.addCell(new Phrase(tenKH, textFont));
				tbl.addCell(new Phrase(dtf.format(
						LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]))),
						textFont));
				tbl.addCell(new Phrase(tongTien, textFont));

			}

			doc.add(tbl);
			doc.add(starLine);
			// Thông tin tổng tiền:
//            Paragraph paragraphTongTienLine1 = new Paragraph("              Tổng số hóa đơn:" + txtHoaDon.getText(), textFont);
//            doc.add(paragraphTongTienLine1);
			Paragraph paragraphTongTienLine2 = new Paragraph("              Tổng doanh thu:" + txtThanhTien.getText(),
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
}