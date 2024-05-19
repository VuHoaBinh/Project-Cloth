package GUI;

//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Label;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.event.AncestorEvent;
//import javax.swing.event.AncestorListener;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumnModel;
//
//import components.button.Button;
//import components.controlPanel.ControlPanel;
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import utils.Utils;
//
//public class ThongKeTongHopKhachHang extends JPanel {
//	private ControlPanel pnlControl;
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	private JLabel lblGio;
//	private JLabel lblNgay;
//	private TextField txtNgay;
//	private TextField txtThang;
//	private TextField txtNam;
//	private Button btnXem;
//	private TextField txtThanhTien;
//	private Button btnThongKe;
//	private TextField txtHoaDon;
//
//
//	public ThongKeTongHopKhachHang(ManHinhChinh main) {
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
//		JLabel lblTitle = new JLabel("Thống Kê Tổng Hợp Khách Hàng");
//		lblTitle.setBounds(420, 10, 400, 40);
//		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		pnlHeader.add(lblTitle);
//
//
//		JPanel pnlRow1 = new JPanel();
//		pnlRow1.setBackground(Utils.secondaryColor);
//		pnlRow1.setBounds(left, top, 1100, 70);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//
//		txtNgay  = new TextField();
//		txtNgay.setLabelText("Ngày:");
//		txtNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNgay.setBackground(new Color(203, 239, 255));
//		txtNgay.setBounds(0, 0, 200, 55);
//		JCheckBox chkNgay = new JCheckBox();
//		chkNgay.setBounds(210, 30, 25, 25);
//		chkNgay.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtNgay);
//		pnlRow1.add(chkNgay);
//
//		txtThang  = new TextField();
//		txtThang.setLabelText("Tháng:");
//		txtThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtThang.setBackground(new Color(203, 239, 255));
//		txtThang.setBounds(260, 0, 200, 55);
//		JCheckBox chkThang = new JCheckBox();
//		chkThang.setBounds(470, 30, 25, 25);
//		chkThang.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtThang);
//		pnlRow1.add(chkThang);
//
//		txtNam  = new TextField();
//		txtNam.setLabelText("Năm:");
//		txtNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNam.setBackground(new Color(203, 239, 255));
//		txtNam.setBounds(520, 0, 200, 55);
//		JCheckBox chkNam = new JCheckBox();
//		chkNam.setBounds(730, 30, 25, 25);
//		chkNam.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtNam);
//		pnlRow1.add(chkNam);
//
//		btnXem = new Button("Xem");
//	    btnXem.setBounds(760, 25, 120, 40);
//
//
//	    btnXem.setIcon(Utils.getImageIcon("vision.png"));
//	    btnXem.setRadius(8);
//	    btnXem.setForeground(Color.WHITE);
//	    btnXem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//	    btnXem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//	    btnXem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//	    btnXem.setColor(Utils.getRGBA(36, 214, 134, 1));
//	    btnXem.setBorderColor(new Color(203, 239, 255));
//	    btnXem.setBorder(new EmptyBorder(0, 0, 0, 0));
//	    pnlRow1.add(btnXem);
//
//		JPanel pnlActions = new JPanel();
//		pnlActions.setBackground(Utils.secondaryColor);
//		pnlActions.setBounds(left, top, 992, 50);
//		top += padding + 55;
//		add(pnlActions);
//		pnlActions.setLayout(null);
//
//		JPanel pnlRow2 = new JPanel();
//		pnlRow2.setBackground(Utils.secondaryColor);
//		pnlRow2.setBounds(left, top+370, 792, 65);
//		top += padding + 5;
//		add(pnlRow2);
//		pnlRow2.setLayout(null);
//
//		txtHoaDon  = new TextField();
//		txtHoaDon.setEditable(false);
//		txtHoaDon.setLabelText("Tổng số hóa đơn:");
//		txtHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtHoaDon.setBackground(new Color(203, 239, 255));
//		txtHoaDon.setBounds(0, 0, 250, 55);
//		pnlRow2.add(txtHoaDon);
//
//		txtThanhTien = new TextField();
//		txtThanhTien.setEditable(false);
//		txtThanhTien.setLabelText("Tổng doanh thu:");
//		txtThanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtThanhTien.setBackground(new Color(203, 239, 255));
//		txtThanhTien.setBounds(250, 0, 250, 55);
//		JLabel vndLabel = new JLabel("VND");
//		vndLabel.setBounds(510, 20, 50, 55);
//		pnlRow2.add(txtThanhTien);
//		pnlRow2.add(vndLabel);
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
//		pnlRow2.add(btnThongKe);
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
//		tableModel = new DefaultTableModel(new String[] { "Tên khách hàng", "Mã khách hàng", "Ngày lập", "Tổng tiền"}, 0);
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
//
////		pnlControl = new ControlPanel(Utils.getLeft(286),topPnlControl - 160 , main);
////		this.add(pnlControl);
//	}
//	}
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
//import javax.swing.JFileChooser;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.filechooser.FileFilter;
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
//import components.scrollbarCustom.ScrollBarCustom;
//import components.textField.TextField;
//import dao.NhanVien_Dao;
//import dao.TaiKhoan_Dao;
//import entity.NhanVien;
//import entity.TaiKhoan;
//import utils.Utils;
//
//public class ThongKeTongHopKhachHang extends JPanel implements ActionListener {
//	private DefaultTableModel tableModel;
//	private JTable tbl;
//	private ManHinhChinh main;
//	private final int widthPnlContainer = 1286;
//	private TextField txtNgay;
//	private TextField txtThang;
//	private TextField txtNam;
//	private TextField txtThanhTien;
//	private Button btnThongKe;
//	private TextField txtHoaDon;
//	private JRadioButton rdoNgay;
//	private JRadioButton rdoThang;
//	private JRadioButton rdoNam;
//	private Button btnXuatFilePDF;
//	private String maTK;
//	private TaiKhoan_Dao TaiKhoan_dao;
//	private NhanVien_Dao NhanVien_dao;
//
//	public ThongKeTongHopKhachHang(ManHinhChinh main, String maTK) {
//		this.main = main;
//		this.maTK = maTK;
//		try {
//			ConnectDB.getInstance().connect();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
//		int top = padding;
//		int left = Utils.getLeft(792);
//
//		setBackground(Utils.secondaryColor);
//		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
//		setLayout(null);
//		TaiKhoan_dao = new TaiKhoan_Dao();
//		NhanVien_dao = new NhanVien_Dao();
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
//		JLabel lblTitle = new JLabel("Thống Kê Tổng Hợp Khách Hàng");
//		lblTitle.setBounds(420, 10, 400, 40);
//		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		pnlHeader.add(lblTitle);
//
//		JPanel pnlRow1 = new JPanel();
//		pnlRow1.setBackground(Utils.secondaryColor);
//		pnlRow1.setBounds(left, top, 1100, 70);
//		top += padding + 5;
//		add(pnlRow1);
//		pnlRow1.setLayout(null);
//
//		txtNgay = new TextField();
//		txtNgay.setLabelText("Ngày:");
//		txtNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNgay.setBackground(new Color(203, 239, 255));
//		txtNgay.setBounds(0, 0, 200, 55);
//		rdoNgay = new JRadioButton();
//		rdoNgay.setBounds(210, 30, 25, 25);
//		rdoNgay.setBackground(new Color(203, 239, 255));
//		pnlRow1.add(txtNgay);
//		pnlRow1.add(rdoNgay);
//
//		txtThang = new TextField();
//		txtThang.setLabelText("Tháng:");
//		txtThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtThang.setBackground(new Color(203, 239, 255));
//		txtThang.setBounds(260, 0, 200, 55);
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
//		JPanel pnlActions = new JPanel();
//		pnlActions.setBackground(Utils.secondaryColor);
//		pnlActions.setBounds(left, top, 992, 50);
//		top += padding + 55;
//		add(pnlActions);
//		pnlActions.setLayout(null);
//
//		JPanel pnlRow2 = new JPanel();
//		pnlRow2.setBackground(Utils.secondaryColor);
//		pnlRow2.setBounds(left, top + 370, 1000, 65);
//		top += padding + 5;
//		add(pnlRow2);
//		pnlRow2.setLayout(null);
//
//		txtHoaDon = new TextField();
//		txtHoaDon.setEditable(false);
//		txtHoaDon.setLabelText("Tổng số hóa đơn:");
//		txtHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtHoaDon.setBackground(new Color(203, 239, 255));
//		txtHoaDon.setBounds(0, 0, 250, 55);
//		pnlRow2.add(txtHoaDon);
//
//		txtThanhTien = new TextField();
//		txtThanhTien.setEditable(false);
//		txtThanhTien.setLabelText("Tổng doanh thu:");
//		txtThanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtThanhTien.setBackground(new Color(203, 239, 255));
//		txtThanhTien.setBounds(250, 0, 250, 55);
//		JLabel vndLabel = new JLabel("VND");
//		vndLabel.setBounds(510, 20, 50, 55);
//		pnlRow2.add(txtThanhTien);
//		pnlRow2.add(vndLabel);
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
//		pnlRow2.add(btnThongKe);
//
//		btnXuatFilePDF = new Button("Xuất File PDF");
//		btnXuatFilePDF.setBounds(750, 25, 200, 40);
//		btnXuatFilePDF.setIcon(Utils.getImageIcon("pdf.png"));
//		btnXuatFilePDF.setRadius(8);
//		btnXuatFilePDF.setForeground(Color.WHITE);
//		btnXuatFilePDF.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnXuatFilePDF.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnXuatFilePDF.setColorClick(Utils.getRGBA(24, 140, 87, 1));
//		btnXuatFilePDF.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnXuatFilePDF.setBorderColor(new Color(203, 239, 255));
//		btnXuatFilePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pnlRow2.add(btnXuatFilePDF);
//
//		JScrollPane scr = new JScrollPane();
//		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scr.setBounds(left - 200, 200, 1100, 360);
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
//		tableModel = new DefaultTableModel(new String[] { "Mã khách hàng", "Tên khách hàng", "Ngày lập", "Tổng tiền" },
//				0);
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
//
//
//		btnThongKe.addActionListener(this);
//		btnXuatFilePDF.addActionListener(this);
//
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
//
//
//	}
//
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object src = e.getSource();
//		if (src.equals(btnThongKe) && rdoNam.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraNam()) {
//				getHD();
//				ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//				double tong = 0;
//				int i = 0, tongHD = 0;
//				int rowcount = tableModel.getRowCount();
//				while (i < rowcount) {
//					int year = layNam(tbl.getValueAt(i, 2).toString());
////					JOptionPane.showMessageDialog(this, year);
//					int txtnam = Integer.parseInt(txtNam.getText());
//					if (year == txtnam) {
//						tong += Double.parseDouble(tbl.getValueAt(i, 3).toString());
//						tongHD++;
//					} else {
//						dsxoa.add(i);
//					}
//					i++;
//				}
//				for (int y = dsxoa.size() - 1; y >= 0; y--) {
//					tableModel.removeRow(dsxoa.get(y));
//				}
//				txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//				txtHoaDon.setText(tongHD + "");
//			}
//
//		} else if (src.equals(btnThongKe) && rdoThang.isSelected()) {
//			tableModel.setRowCount(0);
//			if (ktraNam() && ktraThang()) {
//				getHD();
//				ArrayList<Integer> dsxoa = new ArrayList<Integer>();
//				double tong = 0;
//				int i = 0, tongHD = 0;
//				int rowcount = tableModel.getRowCount();
//				while (i < rowcount) {
//					int year = layNam(tbl.getValueAt(i, 2).toString());
//					int month = layThang(tbl.getValueAt(i, 2).toString());
//					int txtnam = Integer.parseInt(txtNam.getText());
//					int txtthang = Integer.parseInt(txtThang.getText());
//					if (year == txtnam && month == txtthang) {
//						tong += Double.parseDouble(tbl.getValueAt(i, 3).toString());
//						tongHD++;
//					} else {
//						dsxoa.add(i);
//					}
//					i++;
//				}
//				for (int y = dsxoa.size() - 1; y >= 0; y--) {
//					tableModel.removeRow(dsxoa.get(y));
//				}
//				txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//				txtHoaDon.setText(tongHD + "");
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
//						int year = layNam(tbl.getValueAt(i, 2).toString());
//						int month = layThang(tbl.getValueAt(i, 2).toString());
//						int ngay = layTuan(tbl.getValueAt(i, 2).toString());
//						if (year == txtnam && month == txtthang && ngay == txtngay) {
//							tong += Double.parseDouble(tbl.getValueAt(i, 3).toString());
//							tongHD++;
//						} else {
//							dsxoa.add(i);
//						}
//						i++;
//					}
//					for (int y = dsxoa.size() - 1; y >= 0; y--) {
//						tableModel.removeRow(dsxoa.get(y));
//					}
//					txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//					txtHoaDon.setText(tongHD + "");
//				}
//
//			}
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
//			String sql = "SELECT kh.maKhachHang AS MaKhachHang,kh.tenKhachHang AS TenKhachHang,hd.ngayLap AS NgayLap,SUM(cthd.soLuong * cthd.donGia) AS TongTien\r\n"
//					+ "FROM ChiTietHoaDon cthd INNER JOIN HoaDon hd ON cthd.maHoaDon = hd.maHoaDon INNER JOIN KhachHang kh ON hd.khachHang = kh.maKhachHang\r\n"
//					+ "GROUP BY kh.maKhachHang, kh.tenKhachHang, hd.ngayLap";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//
//			while (rs.next()) {
//				Object[] data = { rs.getString(1), rs.getString(2), rs.getString(3), Math.round(rs.getDouble(4))};
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
////                         Workbook wb = new XSSFWorkbook();
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
////
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
//			namefile = "ThongKeKHNgay"+txtNgay.getText()+"-"+txtThang.getText()+"-"+txtNam.getText();
//			tieuDe = "Thống kê khách hàng ngày "+txtNgay.getText()+"-"+txtThang.getText()+"-"+txtNam.getText();
//		}
//		else if(rdoThang.isSelected()) {
//			namefile = "ThongKeKHThang"+txtThang.getText()+"-"+txtNam.getText();
//			tieuDe = "Thống kê khách hàng tháng "+txtThang.getText()+"-"+txtNam.getText();
//
//		}
//		else if(rdoNam.isSelected()) {
//			namefile = "ThongKeKHNam"+txtNam.getText();
//			tieuDe = "Thống kê khách hàng năm "+txtNam.getText();
//		}
//		String path = System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + namefile + ".pdf";
//        com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10); //10 is the size
//        com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 24); //30 is the size
//
//        Document doc = new Document();
//        try {
//        	PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));
//            doc.open();
//            Paragraph ShopName = new Paragraph("       "+tieuDe+"\n", textFont24);
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
//            PdfPTable tbl = new PdfPTable(4);
//            tbl.addCell(new Phrase("Mã khách hàng", textFont));
//            tbl.addCell(new Phrase("Tên khách hàng", textFont));
//            tbl.addCell(new Phrase("Ngày lập", textFont));
//            tbl.addCell(new Phrase("Tổng tiền", textFont));
//            for (int i = 0; i < this.tbl.getRowCount(); i++) {
//                String tenKH = this.tbl.getValueAt(i, 0).toString();
//                String maKH = this.tbl.getValueAt(i, 1).toString();
//                String ngayLap = this.tbl.getValueAt(i, 2).toString();
//                String tongTien = this.tbl.getValueAt(i, 3).toString();
//                tbl.addCell(new Phrase(tenKH, textFont));
//                tbl.addCell(new Phrase(maKH, textFont));
//                tbl.addCell(new Phrase(ngayLap, textFont));
//                tbl.addCell(new Phrase(formatter.format(Double.valueOf(tongTien)), textFont));
//            }
//
//            doc.add(tbl);
//            doc.add(starLine);
//            //Thông tin tổng tiền:
//            Paragraph paragraphTongTienLine1 = new Paragraph("              Tổng số hóa đơn:" + txtHoaDon.getText(), textFont);
//            doc.add(paragraphTongTienLine1);
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
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonGroup;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entities.NhanVien;
import entities.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import url.RmiConfig;
import utils.Utils;

public class ThongKeTongHopKhachHang extends JPanel implements ActionListener {
    private EntityManager entityManager;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtTuan;
	private TextField txtThang;
	private TextField txtNam;
	private TextField txtThanhTien;
	private Button btnThongKe;
	private TextField txtHoaDon;
	private JRadioButton rdoTuan;
	private JRadioButton rdoThang;
	private JRadioButton rdoNam;
	private TextField txtTimKH;
	private TextField txtTimSDT;
	private Button btnXuatFilePDF;
	private String maTK;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private Button btnLamMoi;
	private DecimalFormat formatTienTe;
	private DecimalFormat format;

	public ThongKeTongHopKhachHang(ManHinhChinh main, String maTK) throws MalformedURLException, NotBoundException, RemoteException {
		this.entityManager = Persistence.createEntityManagerFactory("JPA_ORM_MSSQL").createEntityManager();

		this.main = main;
		this.maTK = maTK;


        TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://"+ RmiConfig.RMI_URL+":2008/taiKhoanDao");
        NhanVien_dao = (NhanVienDAO) Naming.lookup("rmi://"+ RmiConfig.RMI_URL+":2008/nhanVienDao");
		formatTienTe = new DecimalFormat("#,##0₫");
		format = new DecimalFormat("#,##0");

		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);

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

		JLabel lblTitle = new JLabel("Thống Kê Tổng Hợp Khách Hàng");
		lblTitle.setBounds(420, 10, 400, 40);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 1100, 70);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtTuan = new TextField();
		txtTuan.setLabelText("Tuần:");
		txtTuan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTuan.setBackground(new Color(203, 239, 255));
		txtTuan.setBounds(0, 0, 200, 55);
		rdoTuan = new JRadioButton();
		rdoTuan.setBounds(210, 30, 25, 25);
		rdoTuan.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtTuan);
		pnlRow1.add(rdoTuan);

		txtThang = new TextField();
		txtThang.setLabelText("Tháng:");
		txtThang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtThang.setBackground(new Color(203, 239, 255));
		txtThang.setBounds(260, 0, 200, 55);
		rdoThang = new JRadioButton();
		rdoThang.setBounds(470, 30, 25, 25);
		rdoThang.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtThang);
		pnlRow1.add(rdoThang);

		txtNam = new TextField();
		txtNam.setLabelText("Năm:");
		txtNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNam.setBackground(new Color(203, 239, 255));
		txtNam.setBounds(520, 0, 200, 55);
		rdoNam = new JRadioButton();
		rdoNam.setBounds(730, 30, 25, 25);
		rdoNam.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNam);
		pnlRow1.add(rdoNam);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 992, 50);
		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left - 200, top + 370, 1300, 65);
		top += padding + 5;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		txtTimKH = new TextField();
		txtTimKH.setLabelText("Tìm khách hàng:");
		txtTimKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTimKH.setBackground(new Color(203, 239, 255));
		txtTimKH.setBounds(0, 0, 215, 55);
		pnlRow2.add(txtTimKH);

		txtTimSDT = new TextField();
		txtTimSDT.setLabelText("Số điện thoại:");
		txtTimSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTimSDT.setBackground(new Color(203, 239, 255));
		txtTimSDT.setBounds(225, 0, 215, 55);
		pnlRow2.add(txtTimSDT);

		txtHoaDon = new TextField();
		txtHoaDon.setEditable(false);
		txtHoaDon.setLabelText("Tổng số hóa đơn:");
		txtHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtHoaDon.setBackground(new Color(203, 239, 255));
		txtHoaDon.setBounds(450, 0, 170, 55);
		pnlRow2.add(txtHoaDon);

		txtThanhTien = new TextField();
		txtThanhTien.setEditable(false);
		txtThanhTien.setLabelText("Tổng doanh thu:");
		txtThanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtThanhTien.setBackground(new Color(203, 239, 255));
		txtThanhTien.setBounds(630, 0, 250, 55);
		JLabel vndLabel = new JLabel("VND");
		vndLabel.setBounds(880, 20, 50, 55);
		pnlRow2.add(txtThanhTien);
		pnlRow2.add(vndLabel);

		btnThongKe = new Button("Thống kê");
		btnThongKe.setBounds(930, 25, 170, 40);
		btnThongKe.setIcon(Utils.getImageIcon("tkdoanhthu.png"));
		btnThongKe.setRadius(8);
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThongKe.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThongKe.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThongKe.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThongKe.setBorderColor(new Color(203, 239, 255));
		btnThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow2.add(btnThongKe);

		btnXuatFilePDF = new Button("Xuất File PDF");
		btnXuatFilePDF.setBounds(1100, 25, 200, 40);
		btnXuatFilePDF.setIcon(Utils.getImageIcon("pdf.png"));
		btnXuatFilePDF.setRadius(8);
		btnXuatFilePDF.setForeground(Color.WHITE);
		btnXuatFilePDF.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnXuatFilePDF.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXuatFilePDF.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXuatFilePDF.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXuatFilePDF.setBorderColor(new Color(203, 239, 255));
		btnXuatFilePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow2.add(btnXuatFilePDF);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left - 200, top + 350, 1300, 65);
		top += padding + 5;
		add(pnlRow3);
		pnlRow3.setLayout(null);

		btnLamMoi = new Button("Làm mới");
		btnLamMoi.setIcon(Utils.getImageIcon("refresh.png"));
		btnLamMoi.setBounds(930, 25, 170, 40);
		btnLamMoi.setRadius(8);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnLamMoi.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnLamMoi.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnLamMoi.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnLamMoi.setBorderColor(new Color(203, 239, 255));
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow3.add(btnLamMoi);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left - 320, 200, 1450, 400);
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
				new String[] { "Tên khách hàng", "Mã khách hàng", "Số điện thoại", "Số hóa đơn", "Tổng tiền" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(300);
		tableColumnModel.getColumn(1).setPreferredWidth(250);
		tableColumnModel.getColumn(2).setPreferredWidth(300);
		tableColumnModel.getColumn(3).setPreferredWidth(300);
		tableColumnModel.getColumn(4).setPreferredWidth(300);

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

		btnThongKe.addActionListener(this);
		btnXuatFilePDF.addActionListener(this);
		btnLamMoi.addActionListener(this);
		rdoTuan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTuan.setEditable(true);
				txtThang.setEditable(true);
				txtNam.setEditable(true);
			}
		});

		rdoThang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTuan.setEditable(false);
				txtThang.setEditable(true);
				txtNam.setEditable(true);
			}
		});
		rdoNam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTuan.setEditable(false);
				txtThang.setEditable(false);
				txtNam.setEditable(true);
			}
		});
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdoTuan);
		buttonGroup.add(rdoThang);
		buttonGroup.add(rdoNam);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(btnThongKe) && rdoNam.isSelected()) {
			String sdt = "", tenKH = "";
			if (ktraNam()) {
				if(!txtTimSDT.getText().equals("") && txtTimKH.getText().equals("")) {
					int nam = Integer.valueOf(txtNam.getText());
					sdt = txtTimSDT.getText();
					tableModel.setRowCount(0);
					getDuLieutheoNam(nam, sdt, tenKH);
				}
				else if(txtTimSDT.getText().equals("") && !txtTimKH.getText().equals("")) {
					int nam = Integer.valueOf(txtNam.getText());
					tenKH = txtTimKH.getText();
					tableModel.setRowCount(0);
					getDuLieutheoNam(nam, sdt, tenKH);
				}
				else if(!txtTimSDT.getText().equals("") && !txtTimKH.getText().equals("")) {
					int nam = Integer.valueOf(txtNam.getText());
					tenKH = txtTimKH.getText();
					sdt = txtTimSDT.getText();
					tableModel.setRowCount(0);
					getDuLieutheoNam(nam, sdt, tenKH);
				}
				else if(txtTimSDT.getText().equals("") && txtTimKH.getText().equals("")) {
					int nam = Integer.valueOf(txtNam.getText());
					tableModel.setRowCount(0);
					getDuLieutheoNam(nam, sdt, tenKH);
				}
			}

		} else if (src.equals(btnThongKe) && rdoThang.isSelected()) {
			String sdt = "", tenKH = "";
			if (ktraNam() && ktraThang()) {
				if(!txtTimSDT.getText().equals("") && txtTimKH.getText().equals("")) {
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					sdt = txtTimSDT.getText();
					tableModel.setRowCount(0);
					getDuLieutheoThang(thang, nam, sdt, tenKH);
				}
				else if(txtTimSDT.getText().equals("") && !txtTimKH.getText().equals("")) {
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					tenKH = txtTimKH.getText();
					tableModel.setRowCount(0);
					getDuLieutheoThang(thang, nam, sdt, tenKH);
				}
				else if(!txtTimSDT.getText().equals("") && !txtTimKH.getText().equals("")) {
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					tenKH = txtTimKH.getText();
					sdt = txtTimSDT.getText();
					tableModel.setRowCount(0);
					getDuLieutheoThang(thang, nam, sdt, tenKH);
				}
				else if(txtTimSDT.getText().equals("") && txtTimKH.getText().equals("")) {
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					tableModel.setRowCount(0);
					getDuLieutheoThang(thang, nam, sdt, tenKH);
				}
			}
		} else if (src.equals(btnThongKe) && rdoTuan.isSelected()) {
			String sdt = "", tenKH = "";
			if (ktraNam() && ktraThang()) {
				if(!txtTimSDT.getText().equals("") && txtTimKH.getText().equals("")) {
					int tuan = Integer.valueOf(txtTuan.getText());
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					sdt = txtTimSDT.getText();
					tableModel.setRowCount(0);
					getDuLieutheoTuan(tuan, thang, nam, sdt, tenKH);
				}
				else if(txtTimSDT.getText().equals("") && !txtTimKH.getText().equals("")) {
					int tuan = Integer.valueOf(txtTuan.getText());
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					tenKH = txtTimKH.getText();
					tableModel.setRowCount(0);
					getDuLieutheoTuan(tuan, thang, nam, sdt, tenKH);
				}
				else if(!txtTimSDT.getText().equals("") && !txtTimKH.getText().equals("")) {
					int tuan = Integer.valueOf(txtTuan.getText());
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					tenKH = txtTimKH.getText();
					sdt = txtTimSDT.getText();
					tableModel.setRowCount(0);
					getDuLieutheoTuan(tuan, thang, nam, sdt, tenKH);
				}
				else if(txtTimSDT.getText().equals("") && txtTimKH.getText().equals("")) {
					int tuan = Integer.valueOf(txtTuan.getText());
					int thang = Integer.valueOf(txtThang.getText());
					int nam = Integer.valueOf(txtNam.getText());
					tableModel.setRowCount(0);
					getDuLieutheoTuan(tuan, thang, nam, sdt, tenKH);
				}
			}
		} else if (src.equals(btnXuatFilePDF)) {
			int row = tableModel.getRowCount();
			if (row == 0)
				JOptionPane.showMessageDialog(this, "Không có dữ liệu để xuất file");
			else
				xuatFile();
		} else if (src.equals(btnLamMoi)) {
			refresh();
		}
	}

	public boolean ktraNam() {
		LocalDateTime time = LocalDateTime.now();
		try {
			int namNhap = Integer.parseInt(txtNam.getText());
			if (namNhap > time.getYear()) {
				JOptionPane.showMessageDialog(this, "Phải nhỏ hơn năm hiện tại");
				txtNam.requestFocus();
				return false;
			}
			return true;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Năm phải là số nguyên dương!");
			txtNam.requestFocus();
			return false;
		}
	}

	public boolean ktraThang() {
		LocalDateTime time = LocalDateTime.now();
		try {
			int namNhap = Integer.parseInt(txtNam.getText());
			int thangNhap = Integer.parseInt(txtThang.getText());
			if (namNhap == time.getYear()) {
				if (thangNhap > time.getMonthValue()) {
					JOptionPane.showMessageDialog(this, "Phải nhỏ hơn tháng hiện tại");
					txtThang.requestFocus();
					return false;
				}
				return true;
			} else
				return true;
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Tháng phải là số nguyên dương!");
			txtNam.requestFocus();
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

	public int layNgay(String ngay) {
		String thang = ngay.substring(8, 10);
		return Integer.parseInt(thang);
	}

	public int layTuan(String day, String month, String year) {
		int IntYear = Integer.parseInt(year);
		int IntMonth = Integer.parseInt(month);
		int IntDay = Integer.parseInt(day);
		LocalDate ngaymua = LocalDate.of(IntYear, IntMonth, IntDay);
		int week = ngaymua.get(WeekFields.of(Locale.KOREA).weekOfMonth());

		return week;
	}

	public void refresh() {
		txtTuan.setText("");
		txtThang.setText("");
		txtNam.setText("");
		txtTimKH.setText("");
		txtTimSDT.setText("");
		txtHoaDon.setText("");
		txtThanhTien.setText("");
		tableModel.setRowCount(0);
	}


	public void getDuLieutheoTuan(int tuan, int thang, int nam, String sdt, String tenKH) {
		Double tongtien = 0.0;
		int sohoadon = 0;

		try {
			String sql = "";
            Query query = null;
			if(!sdt.equals("") && !tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "and ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.tenKhachHang = ? and kh.soDienThoai = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, tuan);
                query.setParameter(4, tenKH);
                query.setParameter(5, sdt);

			}

			else if(sdt.equals("") && !tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "and ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.tenKhachHang = ?";
			    query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, tuan);
                query.setParameter(4, tenKH);

			}
			else if(!sdt.equals("") && tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "and ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.soDienThoai = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, tuan);
                query.setParameter(4, sdt);
			}
			else if(sdt.equals("") && tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "and ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, tuan);
			}
			ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();

            for (Object[] objects : list) {
                Object[] data = { objects[0], objects[1], objects[2], objects[3], format.format(objects[4]) };
                tableModel.addRow(data);
                sohoadon+= Integer.valueOf(objects[3].toString());
                tongtien += Double.parseDouble(objects[4].toString());
            }

//			while (rs.next()) {
//				Object[] data = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), format.format(rs.getDouble(5)) };
//				tableModel.addRow(data);
//				sohoadon+= Integer.valueOf(rs.getString(4));
//				tongtien += rs.getDouble(5);
//			}
			txtHoaDon.setText(String.valueOf(sohoadon));
			txtThanhTien.setText(String.valueOf(formatTienTe.format(tongtien)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getDuLieutheoThang(int thang, int nam, String sdt, String tenKH) {
		Double tongtien = 0.0;
		int sohoadon = 0;

		try {
			String sql = "";
            Query query = null;
			if(!sdt.equals("") && !tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.tenKhachHang = ? and kh.soDienThoai = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, tenKH);
                query.setParameter(4, sdt);
			}

			else if(sdt.equals("") && !tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.tenKhachHang = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, tenKH);
			}
			else if(!sdt.equals("") && tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.soDienThoai = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
                query.setParameter(3, sdt);
			}
			else if(sdt.equals("") && tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, thang);
                query.setParameter(2, nam);
			}

            ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();

            for (Object[] objects : list) {
                Object[] data = { objects[0], objects[1], objects[2], objects[3], format.format(objects[4]) };
                tableModel.addRow(data);
                sohoadon+= Integer.valueOf(objects[3].toString());
                tongtien += Double.parseDouble(objects[4].toString());
            }


			txtHoaDon.setText(String.valueOf(sohoadon));
			txtThanhTien.setText(String.valueOf(formatTienTe.format(tongtien)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getDuLieutheoNam(int nam, String sdt, String tenKH) {
		Double tongtien = 0.0;
		int sohoadon = 0;

		try {
			String sql = "";
            Query query = null;
			if(!sdt.equals("") && !tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.tenKhachHang = ? and kh.soDienThoai = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, nam);
                query.setParameter(2, tenKH);
                query.setParameter(3, sdt);
			}

			else if(sdt.equals("") && !tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.tenKhachHang = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, nam);
                query.setParameter(2, tenKH);
			}
			else if(!sdt.equals("") && tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n"
						+ "having kh.soDienThoai = ?";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, nam);
                query.setParameter(2, sdt);
			}
			else if(sdt.equals("") && tenKH.equals("")) {
				sql = "select kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai,count(DISTINCT hd.maHoaDon), sum(soLuong*donGia)\r\n"
						+ "from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where YEAR(hd.ngayLap) = ?\r\n"
						+ "group by kh.tenKhachHang, kh.maKhachHang, kh.soDienThoai\r\n";
				query = entityManager.createNativeQuery(sql);
                query.setParameter(1, nam);
			}

                ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();

                for (Object[] objects : list) {
                    Object[] data = { objects[0], objects[1], objects[2], objects[3], format.format(objects[4]) };
                    tableModel.addRow(data);
                    sohoadon+= Integer.valueOf(objects[3].toString());
                    tongtien += Double.parseDouble(objects[4].toString());
                }


			txtHoaDon.setText(String.valueOf(sohoadon));
			txtThanhTien.setText(String.valueOf(formatTienTe.format(tongtien)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	private void filterTable(String tenKH, String sdt) {
//		tableModel.setRowCount(0);
//		if (!txtTimKH.getText().isEmpty() && !txtTimSDT.getText().isEmpty()) {
//			GetKHVaSDT(tenKH, sdt);
//		} else if (!txtTimKH.getText().isEmpty()) {
//			GetKH(tenKH);
//		} else if (!txtTimSDT.getText().isEmpty()) {
//			GetSDT(sdt);
//		} else {
//			GetHD();
//		}
//
//		ArrayList<Integer> dsxoa = new ArrayList<>();
//		double tong = 0;
//		int i = 0, tongHD = 0;
//		int rowcount = tableModel.getRowCount();
//		if (rdoTuan.isSelected()) {
//			while (i < rowcount) {
//				int year = layNam(tbl.getValueAt(i, 3).toString());
//				int month = layThang(tbl.getValueAt(i, 3).toString());
//				int ngay = layTuan(tbl.getValueAt(i, 3).toString());
//				int txtnam = Integer.parseInt(txtNam.getText());
//				int txtthang = Integer.parseInt(txtThang.getText());
//				int txttuan = Integer.parseInt(txtTuan.getText());
//				if (year == txtnam && month == txtthang && ngay == txttuan) {
//					tong += Double.parseDouble(tbl.getValueAt(i, 4).toString());
//					tongHD++;
//				} else {
//					dsxoa.add(i);
//				}
//				i++;
//			}
//		} else if (rdoThang.isSelected()) {
//			while (i < rowcount) {
//				int year = layNam(tbl.getValueAt(i, 3).toString());
//				int month = layThang(tbl.getValueAt(i, 3).toString());
//				int txtnam = Integer.parseInt(txtNam.getText());
//				int txtthang = Integer.parseInt(txtThang.getText());
//				if (year == txtnam && month == txtthang) {
//					tong += Double.parseDouble(tbl.getValueAt(i, 4).toString());
//					tongHD++;
//				} else {
//					dsxoa.add(i);
//				}
//				i++;
//			}
//		} else if (rdoNam.isSelected()) {
//			while (i < rowcount) {
//				int year = layNam(tbl.getValueAt(i, 3).toString());
//				int txtnam = Integer.parseInt(txtNam.getText());
//				if (year == txtnam) {
//					tong += Double.parseDouble(tbl.getValueAt(i, 4).toString());
//					tongHD++;
//				} else {
//					dsxoa.add(i);
//				}
//				i++;
//			}
//		}
//
//		for (int y = dsxoa.size() - 1; y >= 0; y--) {
//			tableModel.removeRow(dsxoa.get(y));
//		}
//
//		txtThanhTien.setText(Utils.formatTienTe(tong) + "");
//		txtHoaDon.setText(tongHD + "");
//	}
	public void xuatFile() {
		String namefile = "";
		String tieuDe = "";
		if (rdoTuan.isSelected()) {
			namefile = "ThongKeKHNgay" + rdoTuan.getText() + "-" + txtThang.getText() + "-" + txtNam.getText();
			tieuDe = "Thống kê khách hàng ngày " + rdoTuan.getText() + "-" + txtThang.getText() + "-"
					+ txtNam.getText();
		} else if (rdoThang.isSelected()) {
			namefile = "ThongKeKHThang" + txtThang.getText() + "-" + txtNam.getText();
			tieuDe = "Thống kê khách hàng tháng " + txtThang.getText() + "-" + txtNam.getText();

		} else if (rdoNam.isSelected()) {
			namefile = "ThongKeKHNam" + txtNam.getText();
			tieuDe = "Thống kê khách hàng năm " + txtNam.getText();
		}
		String path = System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + namefile + ".pdf";
		com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED, 10); // 10 is the size
		com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED, 24); // 30 is the size

		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));
			doc.open();
			Paragraph ShopName = new Paragraph("       " + tieuDe + "\n", textFont24);
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

//    Thông tin nhân viên
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
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Paragraph paragraphMaNhanVienLine1 = new Paragraph("               Mã nhân viên:" + maNV
					+ "                                             Ngày xuất thống kê: " + dtf.format(LocalDate.now())
					+ "\n", textFont);
			doc.add(paragraphMaNhanVienLine1);
			Paragraph paragraphTenNhanVienLine2 = new Paragraph("              Tên nhân viên: " + tenNV, textFont);
			doc.add(paragraphTenNhanVienLine2);

//    Chi tiết thống kê
			NumberFormat formatter = NumberFormat.getInstance();
			Paragraph paragraph4 = new Paragraph("              Chi tiết thống kê", textFont);
			doc.add(paragraph4);
			Paragraph paragraph5 = new Paragraph("\n");
			doc.add(paragraph5);
			PdfPTable tbl = new PdfPTable(5);
			tbl.addCell(new Phrase("Mã khách hàng", textFont));
			tbl.addCell(new Phrase("Tên khách hàng", textFont));
			tbl.addCell(new Phrase("Số điện thoại", textFont));
			tbl.addCell(new Phrase("Số hóa đơn", textFont));
			tbl.addCell(new Phrase("Tổng tiền", textFont));
			for (int i = 0; i < this.tbl.getRowCount(); i++) {
				String tenKH = this.tbl.getValueAt(i, 0).toString();
				String maKH = this.tbl.getValueAt(i, 1).toString();
				String sdt = this.tbl.getValueAt(i, 2).toString();
				String sohoadon = this.tbl.getValueAt(i, 3).toString();
				String tongTien = this.tbl.getValueAt(i, 4).toString();
				tbl.addCell(new Phrase(tenKH, textFont));
				tbl.addCell(new Phrase(maKH, textFont));
				tbl.addCell(new Phrase(sdt, textFont));
				tbl.addCell(new Phrase(sohoadon, textFont));
				tbl.addCell(new Phrase(tongTien, textFont));
			}

			doc.add(tbl);
			doc.add(starLine);
			// Thông tin tổng tiền:
			Paragraph paragraphTongTienLine1 = new Paragraph("              Tổng số hóa đơn:" + txtHoaDon.getText(),
					textFont);
			doc.add(paragraphTongTienLine1);
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
			if ((new File(System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + path + ".pdf"))
					.exists()) {
				Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "
						+ System.getProperty("user.dir") + "\\src\\exportFile\\xuatThongKe\\" + path + ".pdf");
			} else {
				JOptionPane.showMessageDialog(null, "File is not Exists");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}