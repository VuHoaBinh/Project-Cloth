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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import components.notification.Notification;
import components.notification.Notification.Type;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietKhuyenMaiDAO;
import dao.KhuyenMaiDAO;
import dao.KichThuocDAO;
import dao.LoaiSanPhamDAO;
import dao.MauSacDAO;
import dao.SanPhamDAO;
import entities.ChiTietKhuyenMai;
import entities.KhuyenMai;
import entities.KichThuoc;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.PhieuDatHang;
import entities.SanPham;
import utils.Utils;

public class QuanLyKhuyenMai extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtTen;
	private TextField txtNgayBatDau;
	private TextField txtNgayKetThuc;
	private JTable tblCTKM;
	private DefaultTableModel tableModelCTKM;
	private Component tblHeaderCTKM;
	private ControlPanel pnlControlCTKM;
	private TextField txtPhanTramKhuyenMai;
	private JTable tblSP;
	private DefaultTableModel tableModelSP;
	private ControlPanel pnlControlSP;
	private DateChooser dateChoose;
	private ArrayList<LoaiSanPham> dsLSP;
	private ArrayList<KichThuoc> dsKT;
	private ArrayList<MauSac> dsMS;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private KichThuocDAO KichThuoc_dao;
	private MauSacDAO MauSac_dao;
	private SanPhamDAO SanPham_dao;
	private Button btnThem;
	private Button btnUpdate;
	private Button btnDelete;
	private Button btnRefresh;
	private Button btnDeleteSP;
	private Button btnAddSP;
	private JTable tblKM;
	private DefaultTableModel tableModelKM;
	private KhuyenMaiDAO KhuyenMai_dao;
	private ChiTietKhuyenMaiDAO ChiTietKhuyenMai_dao;
	private int soluongCTKM = 0;
	private NumberFormat format;
	public QuanLyKhuyenMai(ManHinhChinh main) throws RemoteException, MalformedURLException, NotBoundException {
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		KhuyenMai_dao = (KhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khuyenMaiDao");
		ChiTietKhuyenMai_dao = (ChiTietKhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietKhuyenMaiDao");
		KichThuoc_dao = (KichThuocDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/kichThuocDao");
		LoaiSanPham_dao = (LoaiSanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/loaiSanPhamDao");
		MauSac_dao = (MauSacDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/mauSacDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/sanPhamDao");
		
		format = NumberFormat.getInstance();
		
		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
		setLayout(null);
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JScrollPane scrKM = new JScrollPane();
		scrKM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrKM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrKM.setBounds(left-300, top, 850, 200);
		scrKM.setBackground(Utils.primaryColor);
		scrKM.setBorder(BorderFactory.createTitledBorder("Danh sách khuyến mãi"));
		scrKM.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpKM = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpKM.setScrollbarColor(new Color(203, 203, 203));
		scrKM.setVerticalScrollBar(scpKM);
		this.add(scrKM);

		tblKM = new JTable() {
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
		tblKM.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblKM.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderKM = tblKM.getTableHeader();
		TableColumnModel tableColumnModelKM = tblKM.getColumnModel();

		tableModelKM = new DefaultTableModel(new String[] { "Mã khuyến mãi", "Tên khuyến mãi","Ngày bắt đầu","Ngày kết thúc" }, 0);
		tblKM.setModel(tableModelKM);
		tblKM.setFocusable(false);
		tblHeaderKM.setBackground(Utils.primaryColor);
		tblKM.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblKM.setBackground(Color.WHITE);
		tblKM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelKM.getColumn(0).setPreferredWidth(200);
		tableColumnModelKM.getColumn(1).setPreferredWidth(250);
		tableColumnModelKM.getColumn(2).setPreferredWidth(200);
		tableColumnModelKM.getColumn(3).setPreferredWidth(200);
		tblKM.addMouseListener(new MouseListener() {
			
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
				int row = tblKM.getSelectedRow();
				txtMa.setText(tblKM.getValueAt(row, 0).toString());
				txtTen.setText(tblKM.getValueAt(row, 1).toString());
				txtNgayBatDau.setText(tblKM.getValueAt(row, 2).toString());
				txtNgayKetThuc.setText(tblKM.getValueAt(row, 3).toString());
				tableModelCTKM.setRowCount(0);
				soluongCTKM = 0;
				try {
					for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai(tblKM.getValueAt(row, 0).toString())) {
						Object[] data = {ctkm.getSanPham().getMaSanPham(),getTenSP(ctkm.getSanPham().getMaSanPham()),
								ctkm.getPhanTramKhuyenMai()};
						tableModelCTKM.addRow(data);
						soluongCTKM +=1;
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		tblHeaderKM.setPreferredSize(new Dimension((int) tblHeaderKM.getPreferredSize().getWidth(), 36));
		tblHeaderKM.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderKM.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblKM.setRowHeight(36);
		scrKM.setViewportView(tblKM);
		
		
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
		
		JLabel lblTitle = new JLabel("Quản lý khuyến mãi");
		lblTitle.setBounds(470, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlcol1 = new JPanel();
		pnlcol1.setBackground(Utils.secondaryColor);
		pnlcol1.setBounds(left+550, top+5, 280, 300);
		pnlcol1.setBorder(BorderFactory.createTitledBorder("Thông tin khuyến mãi"));
		add(pnlcol1);
		pnlcol1.setLayout(null);
		
		
		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã khuyến mãi:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(5, 15, 250, 55);
		pnlcol1.add(txtMa);
		
		txtTen= new TextField();
		txtTen.setLabelText("Tên khuyến mãi:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(5, 80, 250, 55);
		pnlcol1.add(txtTen);
		
		txtNgayBatDau = new TextField();
		txtNgayBatDau.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayBatDau.setLineColor(new Color(149, 166, 248));
		txtNgayBatDau.setLabelText("Ngày bắt đầu:");
		txtNgayBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayBatDau.setColumns(10);
		txtNgayBatDau.setBackground(new Color(203, 239, 255));
		txtNgayBatDau.setBounds(5, 145, 250, 55);
		pnlcol1.add(txtNgayBatDau);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayBatDau);
		
		txtNgayKetThuc = new TextField();
		txtNgayKetThuc.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayKetThuc.setLineColor(new Color(149, 166, 248));
		txtNgayKetThuc.setLabelText("Ngày kết thúc:");
		txtNgayKetThuc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayKetThuc.setColumns(10);
		txtNgayKetThuc.setBackground(new Color(203, 239, 255));
		txtNgayKetThuc.setBounds(5, 210, 250, 55);
		pnlcol1.add(txtNgayKetThuc);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayKetThuc);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left+550, 390, 400, 100);
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnThem = new Button("Thêm");
		btnThem.setIcon(Utils.getImageIcon("plus.png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
		btnUpdate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnUpdate.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnUpdate.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnUpdate.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnUpdate.setBorderColor(new Color(203, 239, 255));
		btnUpdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUpdate.setBounds(0, 50, 150, 40);
		pnlActions.add(btnUpdate);
		
		btnDelete = new Button("Xóa");
		btnDelete.setIcon(Utils.getImageIcon("delet.png"));
		btnDelete.setRadius(8);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDelete.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDelete.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setBorderColor(new Color(203, 239, 255));
		btnDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDelete.setBounds(170, 0, 150, 40);
		pnlActions.add(btnDelete);
		
		btnRefresh = new Button("Làm mới");
		btnRefresh.setIcon(Utils.getImageIcon("refresh.png"));
		btnRefresh.setRadius(8);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRefresh.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnRefresh.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setBorderColor(new Color(203, 239, 255));
		btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRefresh.setBounds(170, 50, 150, 40);
		pnlActions.add(btnRefresh);
		
		JScrollPane scrCTKM = new JScrollPane();
		scrCTKM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrCTKM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrCTKM.setBounds(left-300, 280, 550, 200);
		scrCTKM.setBackground(Utils.primaryColor);
		scrCTKM.setBorder(BorderFactory.createTitledBorder("Chi tiết khuyến mãi"));
		scrCTKM.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTKM = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTKM.setScrollbarColor(new Color(203, 203, 203));
		scrCTKM.setVerticalScrollBar(scpCTKM);
		this.add(scrCTKM);

		tblCTKM = new JTable() {
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
		tblCTKM.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTKM.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderCTKM = tblCTKM.getTableHeader();
		TableColumnModel tableColumnModel = tblCTKM.getColumnModel();

		tableModelCTKM = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm","Phần trăm khuyến mãi" }, 0);
		tblCTKM.setModel(tableModelCTKM);
		tblCTKM.setFocusable(false);
		tblHeaderCTKM.setBackground(Utils.primaryColor);
		tblCTKM.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTKM.setBackground(Color.WHITE);
		tblCTKM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(150);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(250);
		tblCTKM.addMouseListener(new MouseListener() {
			
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
				int row = tblCTKM.getSelectedRow();
				txtPhanTramKhuyenMai.setText(tblCTKM.getValueAt(row, 2).toString());
				moKhoaButtonSP();
			}
		});
		
		tblHeaderCTKM.setPreferredSize(new Dimension((int) tblHeaderCTKM.getPreferredSize().getWidth(), 36));
		tblHeaderCTKM.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderCTKM.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTKM.setRowHeight(36);
		scrCTKM.setViewportView(tblCTKM);
		
//		pnlControlCTKM = new ControlPanel(Utils.getLeft(850),topPnlControl - 270 , main);
//		this.add(pnlControlCTKM);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left+250, 300, 550, 180);
		top += padding+50;
		add(pnlRow3);
		pnlRow3.setLayout(null);
		
		txtPhanTramKhuyenMai= new TextField();
		txtPhanTramKhuyenMai.setLabelText("Phần trăm khuyến mãi:");
		txtPhanTramKhuyenMai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPhanTramKhuyenMai.setBackground(new Color(203, 239, 255));
		txtPhanTramKhuyenMai.setBounds(0, 0, 200, 55);
		pnlRow3.add(txtPhanTramKhuyenMai);
		
		btnDeleteSP = new Button("Xóa sản phẩm");
		btnDeleteSP.setIcon(Utils.getImageIcon("delet.png"));
		btnDeleteSP.setRadius(8);
		btnDeleteSP.setForeground(Color.WHITE);
		btnDeleteSP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDeleteSP.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDeleteSP.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDeleteSP.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDeleteSP.setBorderColor(new Color(203, 239, 255));
		btnDeleteSP.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDeleteSP.setBounds(0, 115, 200, 40);
		pnlRow3.add(btnDeleteSP);
		
		btnAddSP = new Button("Thêm sản phẩm");
		btnAddSP.setIcon(Utils.getImageIcon("plus.png"));
		btnAddSP.setRadius(8);
		btnAddSP.setForeground(Color.WHITE);
		btnAddSP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAddSP.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnAddSP.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnAddSP.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnAddSP.setBorderColor(new Color(203, 239, 255));
		btnAddSP.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnAddSP.setBounds(0, 65, 200, 40);
		pnlRow3.add(btnAddSP);
		
		JScrollPane scrSP = new JScrollPane();
		scrSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrSP.setBounds(left - 300, 500, 1400, 230);
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
				moKhoaButtonSP();
				txtPhanTramKhuyenMai.setText("");
				tblCTKM.clearSelection();
			}
		});
		
		tblHeaderSP.setPreferredSize(new Dimension((int) tblHeaderSP.getPreferredSize().getWidth(), 36));
		tblHeaderSP.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblSP.setRowHeight(36);
		scrSP.setViewportView(tblSP);
		
//		pnlControlSP = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControlSP);
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
		for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
			Object[] data = {km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayKetThuc()};
			tableModelKM.addRow(data);
		}
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()), sp.getNhaCungCap(),
					sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
			tableModelSP.addRow(data);
		}
		khoaButtonSP();
		btnAddSP.addActionListener(this);
		btnDeleteSP.addActionListener(this);
		btnThem.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRefresh.addActionListener(this);
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
	public String getTenSP(String ma) throws RemoteException {
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			if(sp.getMaSanPham().equals(ma))
				return sp.getTenSanPham();
		}
		return null;
	}
	public void khoaButtonSP() {
		btnAddSP.setEnabled(false);
		btnDeleteSP.setEnabled(false);
	}
	public void moKhoaButtonSP() {
		btnAddSP.setEnabled(true);
		btnDeleteSP.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnAddSP)) {
			if(validatorInput()){
				int row = tblSP.getSelectedRow();
				int totalRow = tblCTKM.getRowCount();
				String masp = tblSP.getValueAt(row, 0).toString();
				String tensp = tblSP.getValueAt(row, 1).toString();
				try {
					if(kiemTraSPTonTaiKM(masp) == true) {
						JOptionPane.showMessageDialog(this, "Sản phẩm này đã có trong một chương trình khuyến mãi, vui lòng chọn sản phẩm khác");
						tblSP.clearSelection();
						txtPhanTramKhuyenMai.setText("");
						khoaButtonSP();
					}
						
					else {
						Boolean tontai = false;
						for(int i = 0;i < totalRow;i++) {
							if(tblCTKM.getValueAt(i, 0).toString().equals(masp)) {
								tontai = true;
//							Double phantram = Double.valueOf(tblCTKM.getValueAt(i, 2).toString()) + Double.valueOf(txtPhanTramKhuyenMai.getText());					
//							tblCTKM.setValueAt(phantram, i, 2);
//							break;
								JOptionPane.showMessageDialog(this, "Sản phẩm này đã tồn tại trong khuyến mãi này!. Vui lòng chọn sản phẩm khác");
								tblSP.clearSelection();
								txtPhanTramKhuyenMai.setText("");
								khoaButtonSP();
							}
						}
						if(tontai == false) {
							Object[] data = {masp, tensp, txtPhanTramKhuyenMai.getText()};
							tableModelCTKM.addRow(data);
							khoaButtonSP();
							tblSP.clearSelection();
							txtPhanTramKhuyenMai.setText("");
						}
					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		}
		else if(src.equals(btnDeleteSP)) {
			int row = tblCTKM.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
			}
			else {
				tableModelCTKM.removeRow(row);
				txtPhanTramKhuyenMai.setText("");
				khoaButtonSP();
			}
		}
		else if(src.equals(btnThem)){
			if(txtTen.getText().equals("") || txtNgayBatDau.getText().equals("")|| txtNgayKetThuc.getText().equals("")
					|| tableModelCTKM.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ");
			}
			else {
				if(validator()) {
					String maKM = null;
					try {
						maKM = taoMaKhuyenMai();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String tenKM = txtTen.getText();
					String[] ngayBD = txtNgayBatDau.getText().split("-");
					String[] ngayKT = txtNgayKetThuc.getText().split("-");
					int row = tableModelCTKM.getRowCount();
					KhuyenMai km = new KhuyenMai(maKM, tenKM,
							LocalDate.of(Integer.valueOf(ngayBD[0]), Integer.valueOf(ngayBD[1]), Integer.valueOf(ngayBD[2])),
							LocalDate.of(Integer.valueOf(ngayKT[0]), Integer.valueOf(ngayKT[1]), Integer.valueOf(ngayKT[2])));
					if(kiemTraNgayHopLe(LocalDate.of(Integer.valueOf(ngayBD[0]), Integer.valueOf(ngayBD[1]), Integer.valueOf(ngayBD[2])),
							LocalDate.of(Integer.valueOf(ngayKT[0]), Integer.valueOf(ngayKT[1]), Integer.valueOf(ngayKT[2])))) {
						try {
							if(KhuyenMai_dao.create(km)) {
								for(int i = 0; i<row;i++) {
									String masp = tblCTKM.getValueAt(i, 0).toString();
									SanPham sp = SanPham_dao.getSanPham(masp);
									String tensp = tblCTKM.getValueAt(i, 1).toString();
									Double phantram = Double.valueOf(tblCTKM.getValueAt(i, 2).toString());
									ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai(km,
											sp,
											phantram);
									try {
										ChiTietKhuyenMai_dao.create(ctkm);
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								JOptionPane.showMessageDialog(this, "Tạo khuyến mãi thành công");
								refresh();
								tableModelSP.setRowCount(0);
								try {
									for (SanPham sp : SanPham_dao.getAllSanPham()) {
										Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()), sp.getNhaCungCap(),
												sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
										tableModelSP.addRow(data);
									}
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								tableModelKM.setRowCount(0);
								try {
									for (KhuyenMai khuyenmai : KhuyenMai_dao.getAllKhuyenMai()) {
										Object[] data = {khuyenmai.getMaKhuyenMai(),khuyenmai.getTenKhuyenMai(),khuyenmai.getNgayBatDau(),khuyenmai.getNgayKetThuc()};
										tableModelKM.addRow(data);
									}
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						} catch (NumberFormatException | HeadlessException | RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
						showThongBaoLoi(txtNgayBatDau, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu");
				}
				

//					JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu");
			}
//			JOptionPane.showMessageDialog(this, taoMaKhuyenMai());
		}
		else if(src.equals(btnDelete)) {
			int row = tblKM.getSelectedRow();
			if(row != -1) {
				String maKM = tblKM.getValueAt(row, 0).toString();
				int tb = JOptionPane.showConfirmDialog(this, "Có chắc muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if(tb == JOptionPane.YES_OPTION) {
					try {
						KhuyenMai_dao.Delete(maKM);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableModelKM.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
					refresh();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi cần xóa");
			}
		}
		else if(src.equals(btnUpdate)) {
			int row = tblKM.getSelectedRow();
			int toTalrowCTKM = tableModelCTKM.getRowCount();
			String maKM = txtMa.getText();
			String tenKM = txtTen.getText();
			String[] ngayBD = txtNgayBatDau.getText().split("-");
			String[] ngayKT = txtNgayKetThuc.getText().split("-");
			if(maKM.equals(""))
				JOptionPane.showMessageDialog(this, "Chưa chọn chương trình khuyến mãi cần cập nhật");
			else {
				KhuyenMai km = new KhuyenMai(maKM, tenKM,
						LocalDate.of(Integer.valueOf(ngayBD[0]), Integer.valueOf(ngayBD[1]), Integer.valueOf(ngayBD[2])),
						LocalDate.of(Integer.valueOf(ngayKT[0]), Integer.valueOf(ngayKT[1]), Integer.valueOf(ngayKT[2])));
				if(kiemTraNgayHopLe(LocalDate.of(Integer.valueOf(ngayBD[0]), Integer.valueOf(ngayBD[1]), Integer.valueOf(ngayBD[2])),
						LocalDate.of(Integer.valueOf(ngayKT[0]), Integer.valueOf(ngayKT[1]), Integer.valueOf(ngayKT[2])))) {
					try {
						if(KhuyenMai_dao.Update(km)) {
							Object[] data = {km.getMaKhuyenMai(), km.getTenKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc()};
							tableModelKM.insertRow(row, data);
							tableModelKM.removeRow(row + 1);
							ChiTietKhuyenMai_dao.Delete(maKM);
							for(int i = 0; i<toTalrowCTKM;i++) {
								String masp = tblCTKM.getValueAt(i, 0).toString();
								SanPham sp = SanPham_dao.getSanPham(masp);
								String tensp = tblCTKM.getValueAt(i, 1).toString();
								Double phantram = Double.valueOf(tblCTKM.getValueAt(i, 2).toString());
								ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai(km,
										sp,
										phantram);
								ChiTietKhuyenMai_dao.create(ctkm);
							}
							JOptionPane.showMessageDialog(this, "Cập nhật thành công");
							refresh();
							tblKM.clearSelection();
						}
					} catch (NumberFormatException | HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					showThongBaoLoi(txtNgayBatDau, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu");
//					JOptionPane.showMessageDialog(this, );	
			}		
		}
		else if(src.equals(btnRefresh)) {
			refresh();
			tblKM.clearSelection();
			khoaButtonSP();
		}
	}
	public void refresh() {
		txtMa.setText("");
		txtTen.setText("");
		txtNgayBatDau.setText("");
		txtNgayKetThuc.setText("");
		tableModelCTKM.setRowCount(0);
		tblSP.clearSelection();
	}
	public void demSoLuongCTKM(String maKM, int soluong) throws RemoteException {
		for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai(maKM)) {
			soluong += 1;
		}
	}
	public String taoMaKhuyenMai() throws RemoteException {
		ArrayList<String> dsmaKM = new ArrayList<String>();
		for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
			dsmaKM.add(km.getMaKhuyenMai());
		}
		for(int i = 1;i <= 1000;i++) {
			String ma = "";
			if(i<10)
				ma = "KM000"+i;
			else if(i<100)
				ma = "KM00"+i;
			else if(i<1000)
				ma = "KM0"+i;
			else
				ma = "KM1000";
			if(dsmaKM.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}
	public boolean kiemTraSPTonTaiKM(String maSP) throws RemoteException {
		for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai()) {
			if(ctkm.getSanPham().getMaSanPham().equals(maSP))
				return true;
		}
		return false;
	}
	public boolean kiemTraNgayHopLe(LocalDate ngayBD, LocalDate ngayKT) {
		if(ngayBD.isBefore(ngayKT) || ngayBD.isEqual(ngayKT))
			return true;
		else return false;
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

		String ten = txtTen.getText().trim();

		if (ten.length() <= 0)
			return showThongBaoLoi(txtTen, "Vui lòng nhập tên khuyến mãi");

		if (!ten.matches("^[a-zA-ZÀ-ỹ ]*$"))
			return showThongBaoLoi(txtTen, "tên chỉ chứa các ký tự chữ cái");
		
		return true;
	}
	private boolean validatorInput() {
		String ptkm = txtPhanTramKhuyenMai.getText().trim();

		if (ptkm.length() <= 0) {
			return showThongBaoLoi(txtPhanTramKhuyenMai, "Vui lòng nhập phần trăm khuyến mãi");
		}

		try {
			double phanTram = Double.parseDouble(ptkm);

			if (phanTram < 0 || phanTram > 100) {
				return showThongBaoLoi(txtPhanTramKhuyenMai, "Phần trăm khuyến mãi phải từ 0 đến 100");
			}
		} catch (NumberFormatException e) {
			return showThongBaoLoi(txtPhanTramKhuyenMai, "Phần trăm khuyến mãi không hợp lệ");
		}
		return true;
	}
}
