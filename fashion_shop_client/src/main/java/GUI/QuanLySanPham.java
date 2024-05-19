package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import components.button.Button;
import components.comboBox.ComboBox;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import components.notification.Notification;
import components.notification.Notification.Type;
import dao.KichThuocDAO;
import dao.LoaiSanPhamDAO;
import dao.MauSacDAO;
import dao.SanPhamDAO;
import entities.KichThuoc;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.PhieuDatHang;
import entities.SanPham;
import utils.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class QuanLySanPham extends JPanel implements ActionListener {
	private ControlPanel pnlControl;
	private Thread clock;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtMau;
	private TextField txtTen;
	private JComboBox<String> cboSize;
	private JComboBox<String> cboColor;
//	private TextField txtNhaCungCap;
	private TextField txtDonVi;
	private TextField txtDonGia;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private ComboBox cboLoaiSanPham;
	private KichThuocDAO KichThuoc_dao;
	private MauSacDAO MauSac_dao;
//	private TextField txtSoLuong;
	private ArrayList<LoaiSanPham> dsLSP;
	private ArrayList<KichThuoc> dsKT;
	private ArrayList<MauSac> dsMS;
	private SanPhamDAO SanPham_dao;
	private Button btnThem;
	private Button btnUpdate;
	private Button btnDelete;
	private Button btnRefresh;
	private Button btnThemLSP;
	private Button btnThemMS;
	private Button btnThemSize;
	private NumberFormat format;

	/**
	 * Create the frame.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public QuanLySanPham(ManHinhChinh main) throws RemoteException, MalformedURLException, NotBoundException {
		this.main = main;

		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		LoaiSanPham_dao = (LoaiSanPhamDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/loaiSanPhamDao");
		KichThuoc_dao = (KichThuocDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/kichThuocDao");
		MauSac_dao = (MauSacDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/mauSacDao");
		SanPham_dao = (SanPhamDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/sanPhamDao");
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
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
		pnlHeader.setBounds(16, 18, 1054, 70);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Thông tin sản phẩm");
		lblTitle.setBounds(470, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);

		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã sản phẩm:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);

		txtTen = new TextField();
		txtTen.setLabelText("Tên sản phẩm:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtTen);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 80);
		top += padding + 20;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		txtDonGia = new TextField();
		txtDonGia.setLabelText("Đơn giá:");
		txtDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDonGia.setBackground(new Color(203, 239, 255));
		txtDonGia.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtDonGia);

		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		lblLoaiSanPham.setForeground(Utils.labelTextField);
		lblLoaiSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLoaiSanPham.setBounds(421, 0, 120, 65);
		pnlRow2.add(lblLoaiSanPham);

		cboLoaiSanPham = new ComboBox<>();
		cboLoaiSanPham.setModel(new DefaultComboBoxModel<String>());
		cboLoaiSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboLoaiSanPham.setBackground(new Color(140, 177, 180));
		cboLoaiSanPham.setBounds(421, 46, 315, 36);
		pnlRow2.add(cboLoaiSanPham);

		btnThemLSP = new Button();
		btnThemLSP.setIcon(Utils.getImageIcon("plus.png"));
		btnThemLSP.setRadius(8);
		btnThemLSP.setForeground(Color.WHITE);
		btnThemLSP.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThemLSP.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThemLSP.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThemLSP.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThemLSP.setBorderColor(new Color(203, 239, 255));
		btnThemLSP.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThemLSP.setBounds(750, 42, 40, 40);
		pnlRow2.add(btnThemLSP);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 80);
		top += padding;
		add(pnlRow3);
		pnlRow3.setLayout(null);

//		txtNhaCungCap = new TextField();
//		txtNhaCungCap.setEditable(false);
//		txtNhaCungCap.setLabelText("Nhà cung cấp:");
//		txtNhaCungCap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtNhaCungCap.setBackground(new Color(203, 239, 255));
//		txtNhaCungCap.setBounds(0, 0, 371, 55);
//		pnlRow3.add(txtNhaCungCap);

		txtDonVi = new TextField();
		txtDonVi.setLabelText("Đơn vị:");
		txtDonVi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDonVi.setBackground(new Color(203, 239, 255));
		txtDonVi.setBounds(0, 0, 371, 55);
		pnlRow3.add(txtDonVi);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top+18, 792, 80);
		top += padding + 20;
		add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblColor = new JLabel("Màu sắc:");
		lblColor.setForeground(Utils.labelTextField);
		lblColor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblColor.setBounds(421, 0, 100, 65);
		pnlRow3.add(lblColor);

		cboColor = new ComboBox<>();
		cboColor.setModel(new DefaultComboBoxModel<String>());
		cboColor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboColor.setBackground(new Color(140, 177, 180));
		cboColor.setBounds(421, 46, 315, 36);
		pnlRow3.add(cboColor);

		btnThemMS = new Button("");
		btnThemMS.setIcon(Utils.getImageIcon("plus.png"));
		btnThemMS.setRadius(8);
		btnThemMS.setForeground(Color.WHITE);
		btnThemMS.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThemMS.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThemMS.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThemMS.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThemMS.setBorderColor(new Color(203, 239, 255));
		btnThemMS.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThemMS.setBounds(750, 42, 40, 40);
		pnlRow3.add(btnThemMS);

		JLabel lblSize = new JLabel("Kích thước:");
		lblSize.setForeground(Utils.labelTextField);
		lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSize.setBounds(421, 0, 100, 65);
		pnlRow4.add(lblSize);

		cboSize = new ComboBox<>();
		cboSize.setModel(new DefaultComboBoxModel<String>());
		cboSize.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboSize.setBackground(new Color(140, 177, 180));
		cboSize.setBounds(421, 46, 315, 36);
		pnlRow4.add(cboSize);

		btnThemSize = new Button("");
		btnThemSize.setIcon(Utils.getImageIcon("plus.png"));
		btnThemSize.setRadius(8);
		btnThemSize.setForeground(Color.WHITE);
		btnThemSize.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThemSize.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThemSize.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThemSize.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThemSize.setBorderColor(new Color(203, 239, 255));
		btnThemSize.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThemSize.setBounds(750, 42, 40, 40);
		pnlRow4.add(btnThemSize);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 792, 80);
		top += padding + 20;
		add(pnlRow5);
		pnlRow5.setLayout(null);

//		txtSoLuong = new TextField();
//		txtSoLuong.setEditable(false);
//		txtSoLuong.setLabelText("Số lượng:");
//		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtSoLuong.setBackground(new Color(203, 239, 255));
//		txtSoLuong.setBounds(0, 0, 371, 55);
//		pnlRow5.add(txtSoLuong);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 992, 50);
		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);

		btnThem = new Button("Thêm");
		btnThem.setIcon(Utils.getImageIcon("add 1.png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(0, 0, 150, 40);
		pnlActions.add(btnThem);

		btnUpdate = new Button("Cập nhật");
		btnUpdate.setIcon(Utils.getImageIcon("update 1.png"));
		btnUpdate.setRadius(8);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnUpdate.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnUpdate.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnUpdate.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnUpdate.setBorderColor(new Color(203, 239, 255));
		btnUpdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUpdate.setBounds(165, 0, 170, 40);
		pnlActions.add(btnUpdate);

		btnDelete = new Button("Xóa");
		btnDelete.setIcon(Utils.getImageIcon("delet.png"));
		btnDelete.setRadius(8);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnDelete.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDelete.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setBorderColor(new Color(203, 239, 255));
		btnDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDelete.setBounds(350, 0, 150, 40);
		pnlActions.add(btnDelete);

		btnRefresh = new Button("Làm mới");
		btnRefresh.setIcon(Utils.getImageIcon("refresh.png"));
		btnRefresh.setRadius(8);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnRefresh.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColorClick(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setBorderColor(new Color(203, 239, 255));
		btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRefresh.setBounds(515, 0, 150, 40);
		pnlActions.add(btnRefresh);
		
//		
//		Button btnLapPhieuNhap = new Button("Phiếu nhập");
//		btnLapPhieuNhap.setIcon(Utils.getImageIcon("phieuNhap.png"));
//		btnLapPhieuNhap.setRadius(8);
//		btnLapPhieuNhap.setForeground(Color.WHITE);
//		btnLapPhieuNhap.setFont(new Font("Segoe UI", Font.PLAIN, 26));
//		btnLapPhieuNhap.setColorOver(Utils.getRGBA(36, 214, 134, 1));
//		btnLapPhieuNhap.setColorClick(Utils.getRGBA(36, 214, 134, 1));
//		btnLapPhieuNhap.setColor(Utils.getRGBA(36, 214, 134, 1));
//		btnLapPhieuNhap.setBorderColor(new Color(203, 239, 255));
//		btnLapPhieuNhap.setBorder(new EmptyBorder(0, 0, 0, 0));
//		btnLapPhieuNhap.setBounds(685, 0, 200, 40);
//		pnlActions.add(btnLapPhieuNhap);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left - 325, 480, 1450, 250);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
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
		tbl.setAutoCreateRowSorter(true); // tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tableModel = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Đơn giá bán",
				"Nhà cung cấp", "Màu sắc", "Kích thước", "Loại sản phẩm", "Đơn vị", "Số lượng" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(150);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(150);
		tableColumnModel.getColumn(3).setPreferredWidth(150);
		tableColumnModel.getColumn(4).setPreferredWidth(200);
		tableColumnModel.getColumn(5).setPreferredWidth(120);
		tableColumnModel.getColumn(6).setPreferredWidth(120);
		tableColumnModel.getColumn(7).setPreferredWidth(160);
		tableColumnModel.getColumn(8).setPreferredWidth(120);
		tableColumnModel.getColumn(9).setPreferredWidth(125);
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
				txtDonVi.setText(tbl.getValueAt(row, 8).toString());
//				if (tbl.getValueAt(row, 3) == null)
//					txtNhaCungCap.setText("");
//				else
//					txtNhaCungCap.setText(tbl.getValueAt(row, 4).toString());
				txtDonGia.setText(String.valueOf(Math.round(chuyenDoiFormat(tbl.getValueAt(row, 2).toString()))));
//				if (tbl.getValueAt(row, 8) == null)
//					txtSoLuong.setText("0");
//				else
//					txtSoLuong.setText(tbl.getValueAt(row, 8).toString());
				cboColor.setSelectedItem(tbl.getValueAt(row, 5));
				cboSize.setSelectedItem(tbl.getValueAt(row, 6));
				cboLoaiSanPham.setSelectedItem(tbl.getValueAt(row, 7));
			}
		});

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		btnThem.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnThemLSP.addActionListener(this);
		btnThemMS.addActionListener(this);
		btnThemSize.addActionListener(this);

//		pnlControl = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControl);
		dsLSP = new ArrayList<LoaiSanPham>();
		dsKT = new ArrayList<KichThuoc>();
		dsMS = new ArrayList<MauSac>();

		for (LoaiSanPham lsv : LoaiSanPham_dao.getAllLoaiSanPham()) {
			dsLSP.add(lsv);
			cboLoaiSanPham.addItem(lsv.getTenLoaiSanPham());
		}
		for (KichThuoc kt : KichThuoc_dao.getAllKichThuoc()) {
			dsKT.add(kt);
			cboSize.addItem(kt.getTenKichThuoc());
		}
		for (MauSac ms : MauSac_dao.getAllMauSac()) {
			dsMS.add(ms);
			cboColor.addItem(ms.getTenMauSac());
		}
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), format.format(sp.getDonGia()),
					format.format(sp.getDonGia() * 120 / 100), sp.getNhaCungCap(), sp.getMauSac().getTenMauSac(),
					sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),
					sp.getSoLuong() };
			tableModel.addRow(data);
		}
	}

	public String getMaLSP(String ten) {
		for (LoaiSanPham lsp : dsLSP) {
			if (lsp.getTenLoaiSanPham().equals(ten))
				return lsp.getMaLoaiSanPham();
		}
		return null;
	}

	public String getMaKT(String ten) {
		for (KichThuoc kt : dsKT) {
			if (kt.getTenKichThuoc().equals(ten))
				return kt.getMaKichThuoc();
		}
		return null;
	}

	public String getMaMS(String ten) {
		for (MauSac ms : dsMS) {
			if (ms.getTenMauSac().equals(ten))
				return ms.getMaMauSac();
		}
		return null;
	}

	public String getTenLSP(String ma) {
		for (LoaiSanPham lsp : dsLSP) {
			if (lsp.getMaLoaiSanPham().equals(ma))
				return lsp.getTenLoaiSanPham();
		}
		return null;
	}

	public String getTenKT(String ma) {
		for (KichThuoc kt : dsKT) {
			if (kt.getMaKichThuoc().equals(ma))
				return kt.getTenKichThuoc();
		}
		return null;
	}

	public String getTenMS(String ma) {
		for (MauSac ms : dsMS) {
			if (ms.getMaMauSac().equals(ma))
				return ms.getTenMauSac();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnThem)) {
//			JOptionPane.showMessageDialog(this, taoMaSanPham());
			if (validator()) {

				try {
					String maSanPham = taoMaSanPham();
					String tenSanPham = txtTen.getText();
					Double donGia = Double.valueOf(txtDonGia.getText());
					Double donGiaBan = donGia * 120 / 100;
					String loaiSanPham = cboLoaiSanPham.getSelectedItem().toString();
//					String nhaCungCap = txtNhaCungCap.getText();
					String donVi = txtDonVi.getText();
					String mauSac = cboColor.getSelectedItem().toString();
					String kichThuoc = cboSize.getSelectedItem().toString();
					LoaiSanPham lsp = LoaiSanPham_dao.getLoaiSanPhamById(getMaLSP(loaiSanPham));
					KichThuoc kt = KichThuoc_dao.getKichThuocById(getMaKT(kichThuoc));
					MauSac ms = MauSac_dao.getMauSacById(getMaMS(mauSac));
					Integer soLuong = 0;
					String hinhAnh = "";
					SanPham sp = new SanPham(maSanPham, tenSanPham, donVi, "", hinhAnh, ms, kt, soLuong, lsp,
							donGia);
					if (SanPham_dao.create(sp)) {
						Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), format.format(sp.getDonGia()),
								format.format(sp.getDonGia() * 120 / 100), sp.getNhaCungCap(),
								sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(),
								sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(), sp.getSoLuong() };
						tableModel.addRow(data);
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						taoQRCode(maSanPham, tenSanPham, donGiaBan.toString());
						int tb = JOptionPane.showConfirmDialog(this, "Có muốn in QRcode của sản phẩm không?", "Hỏi",
								JOptionPane.YES_NO_OPTION);
						if (tb == JOptionPane.YES_OPTION) {
							openFile(maSanPham);
						}
						refresh();
					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (src.equals(btnDelete)) {
			int row = tbl.getSelectedRow();
			if (row != -1) {
				String ma = tbl.getValueAt(row, 0).toString();
				int tb = JOptionPane.showConfirmDialog(this, "Có chắc muốn xóa?", "Chú Ý", JOptionPane.YES_NO_OPTION);
				if (tb == JOptionPane.YES_OPTION) {
					try {
						SanPham_dao.Delete(ma);
						tableModel.removeRow(row);
						xoaQRCode(ma);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
						refresh();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng cần xóa!");
			}
		} else if (src.equals(btnUpdate)) {
			int row = tbl.getSelectedRow();
			if (row != -1) {
				if (validator()) {
					try {
						String maSanPham = txtMa.getText();
						String tenSanPham = txtTen.getText();
						Double donGia = Double.valueOf(txtDonGia.getText());
						Double donGiaBan = donGia * 120 / 100;
						String loaiSanPham = cboLoaiSanPham.getSelectedItem().toString();
//						String nhaCungCap = txtNhaCungCap.getText();
						String donVi = txtDonVi.getText();
						String mauSac = cboColor.getSelectedItem().toString();
						String kichThuoc = cboSize.getSelectedItem().toString();
						Integer soLuong = 0;
						String hinhAnh = "";
						LoaiSanPham lsp = LoaiSanPham_dao.getLoaiSanPhamById(getMaLSP(loaiSanPham));
						KichThuoc kt = KichThuoc_dao.getKichThuocById(getMaKT(kichThuoc));
						MauSac ms = MauSac_dao.getMauSacById(getMaMS(mauSac));
						SanPham sp = new SanPham(maSanPham, tenSanPham, donVi, "", hinhAnh, ms, kt, soLuong,
								lsp, donGia);
						if (SanPham_dao.Update(sp)) {
							Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), format.format(sp.getDonGia()),
									format.format(sp.getDonGia() * 120 / 100), sp.getNhaCungCap(),
									sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(),
									sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(), sp.getSoLuong() };
							tableModel.insertRow(row, data);
							tableModel.removeRow(row + 1);
							xoaQRCode(maSanPham);
							taoQRCode(maSanPham, tenSanPham, donGiaBan.toString());
							JOptionPane.showMessageDialog(this, "Cập nhật thành công");
							refresh();
						}
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để cập nhật thông tin");
		} else if (src.equals(btnRefresh)) {
			refresh();
		} else if (src.equals(btnThemLSP)) {
			Form_ThemLoaiSanPham formThemLSP;
			try {
				formThemLSP = new Form_ThemLoaiSanPham(this);
				formThemLSP.setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (src.equals(btnThemMS)) {
			Form_ThemMauSac formThemMS;
			try {
				formThemMS = new Form_ThemMauSac(this);
				formThemMS.setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		} else if (src.equals(btnThemSize)) {
			Form_ThemKichThuoc formThemKT;
			try {
				formThemKT = new Form_ThemKichThuoc(this);
				formThemKT.setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	public void refresh() {
		txtMa.setText("");
		txtTen.setText("");
		txtDonGia.setText("");
		cboLoaiSanPham.setSelectedIndex(0);
//		txtNhaCungCap.setText("");
		txtDonVi.setText("");
		cboColor.setSelectedIndex(0);
		cboSize.setSelectedIndex(0);
//		txtSoLuong.setText("");
		tbl.clearSelection();
	}

	public String taoMaSanPham() throws RemoteException {
		ArrayList<String> dsmaSP = new ArrayList<String>();
		try {
			for (SanPham sp : SanPham_dao.getAllSanPham()) {
				dsmaSP.add(sp.getMaSanPham());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i <= 1000; i++) {
			String ma = "";
			if (i < 10)
				ma = "SP000" + i;
			else if (i < 100)
				ma = "SP00" + i;
			else if (i < 1000)
				ma = "SP0" + i;
			else
				ma = "SP1000";
			if (dsmaSP.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void resetcboLSP() throws RemoteException {
		cboLoaiSanPham.removeAllItems();
//		cboLoaiSanPham.addItem("");
		for (LoaiSanPham lsp : LoaiSanPham_dao.getAllLoaiSanPham()) {
			cboLoaiSanPham.addItem(lsp.getTenLoaiSanPham());
		}
	}

	public void resetcboMS() throws RemoteException {
		cboColor.removeAllItems();
//		cboColor.addItem("");
		for (MauSac ms : MauSac_dao.getAllMauSac()) {
			cboColor.addItem(ms.getTenMauSac());
		}
	}

	public void resetcboKT() throws RemoteException {
		cboSize.removeAllItems();
//		cboSize.addItem("");
		for (KichThuoc kt : KichThuoc_dao.getAllKichThuoc()) {
			cboSize.addItem(kt.getTenKichThuoc());
		}
	}

	/**
	 * Hiển thị thông báo lỗi và focus các JTextField
	 *
	 * @param txt     JtextField cần focus
	 * @param message thông báo lỗi
	 * @return false
	 */
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
//		String ma = txtMa.getText().trim();
//
//		if (ma.length() <= 0)
//			return showThongBaoLoi(txtMa, "Vui lòng nhập mã sản phẩm");
//
//		if (!Pattern.matches("^SP(0[1-9]|[1-9][0-9])$", ma))
//			return showThongBaoLoi(txtMa, "Mã sản phẩm phải theo định dạng SPXX với XX là 2 chữ số");

		String ten = txtTen.getText().trim();

		if (ten.length() <= 0)
			return showThongBaoLoi(txtTen, "Vui lòng nhập tên sản phẩm");
		if (!ten.matches("^[a-zA-ZÀ-ỹ\\s]*$"))
			return showThongBaoLoi(txtTen, "tên chỉ chứa các ký tự chữ cái");

		String donGia = txtDonGia.getText().trim();

		if (donGia.length() <= 0)
			return showThongBaoLoi(txtDonGia, "Vui lòng nhập đơn giá");
		try {
			double donGiaNumber = Double.parseDouble(donGia);
			if (donGiaNumber <= 0)
				return showThongBaoLoi(txtDonGia, "Đơn giá phải lớn hơn 0");
		} catch (Exception e) {
			return showThongBaoLoi(txtDonGia, "Đơn giá phải là 1 số nguyên");
		}

//		String nhaCungCap = txtNhaCungCap.getText().trim();
//
//		if (nhaCungCap.length() <= 0)
//			return showThongBaoLoi(txtNhaCungCap, "Vui lòng nhập nhà cung cấp");
//		if (Pattern.matches(String.format(".*[^%s%s ].*", vietNamese, vietNameseLower), nhaCungCap))
//			return showThongBaoLoi(txtNhaCungCap, "nhà cung cấp chỉ chứa các ký tự chữ cái");

		String donVi = txtDonVi.getText().trim();

		if (donVi.length() <= 0)
			return showThongBaoLoi(txtDonVi, "Vui lòng nhập đơn vị");
		if (!donVi.matches("^[a-zA-ZÀ-ỹ\\s]*$"))
			return showThongBaoLoi(txtDonVi, "đơn vị chỉ chứa các ký tự chữ cái");

//		String soLuong = txtSoLuong.getText().trim();
//
//		if (soLuong.length() <= 0)
//			return showThongBaoLoi(txtSoLuong, "Vui lòng nhập số lượng");

//		double soLuongNumber = Double.parseDouble(soLuong);
//
//		if (soLuongNumber <= 0)
//			return showThongBaoLoi(txtSoLuong, "số lượng phải lớn hơn 0");

		return true;
	}

	public void taoQRCode(String maSP, String tenSP, String donGia) {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		String data = maSP + "/" + tenSP + "/" + donGia; // dữ liệu muốn mã hóa
		int width = 300; // chiều rộng của QR code
		int height = 300; // chiều cao của QR code
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\QRCodeSP\\QRCode_" + maSP + ".png"; // đường
																													// dẫn
																													// tệp
																													// tin
																													// để
																													// lưu
																													// QR
																													// code

		try {
			BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
			Path path = Paths.get(filePath);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		} catch (WriterException e) {
			System.err.println("Lỗi: Không thể tạo QR code. " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Lỗi: Không thể lưu QR code vào tệp tin. " + e.getMessage());
		}
	}

	public void xoaQRCode(String maSP) {
		Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\QRCodeSP\\QRCode_" + maSP + ".png"); // đường
																														// dẫn
																														// tới
																														// tệp
																														// tin
		try {
			Files.deleteIfExists(path);
//            System.out.println("Tệp tin đã được xóa.");
		} catch (IOException e) {
			System.err.println("Lỗi: Không thể xóa tệp tin. " + e.getMessage());
		}
	}

	public void openFile(String maSP) {
		try {
			if ((new File(System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\QRCodeSP\\QRCode_" + maSP + ".png"))
					.exists()) {
				Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "
						+ System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\QRCodeSP\\QRCode_" + maSP + ".png");
			} else {
				JOptionPane.showMessageDialog(null, "File is not Exists");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
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
