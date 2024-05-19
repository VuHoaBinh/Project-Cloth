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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

import com.raven.datechooser.DateChooser;
import com.toedter.calendar.JDateChooser;
import components.notification.Notification;
import components.notification.Notification.Type;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietPhieuNhapDAO;
import dao.KichThuocDAO;
import dao.LoaiNhanVienDAO;
import dao.LoaiSanPhamDAO;
import dao.MauSacDAO;
import dao.NhanVienDAO;
import dao.PhieuNhapDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;
import entities.ChiTietPhieuNhap;
import entities.KichThuoc;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhanVien;
import entities.PhieuDatHang;
import entities.PhieuNhap;
import entities.SanPham;
import entities.TaiKhoan;
import utils.Utils;
import java.lang.Math;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;

public class LapPhieuNhap extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtNgayLap;
	private TextField txtNguoiLap;
	private TextField txtNhaCungCap;
	private JTable tblCTPN;
	private DefaultTableModel tableModelCTPN;
	private ControlPanel pnlControlCTPN;
	private TextField txtSoLuong;
	private JTable tblSP;
	private DefaultTableModel tableModelSP;
	private ControlPanel pnlControlSP;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private DateChooser dateChoose;
	private SanPhamDAO SanPham_dao;
	private ArrayList<LoaiSanPham> dsLSP;
	private ArrayList<KichThuoc> dsKT;
	private ArrayList<MauSac> dsMS;
	private MauSacDAO MauSac_dao;
	private KichThuocDAO KichThuoc_dao;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private Button btnThem;
	private Button btnRefresh;
	private Button btnDeleteSP;
	private Button btnAddSP;
	private String maNV;
	private PhieuNhapDAO PhieuNhap_dao;
	private ChiTietPhieuNhapDAO ChiTietPhieuNhap_dao;
	private NumberFormat format;
	public LapPhieuNhap(ManHinhChinh main, String maTK) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		TaiKhoan_dao = (TaiKhoanDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/taiKhoanDao");
		NhanVien_dao = (NhanVienDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/nhanVienDao");
		PhieuNhap_dao = (PhieuNhapDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/phieuNhapDao");
		MauSac_dao = (MauSacDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/mauSacDao");
		KichThuoc_dao = (KichThuocDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/kichThuocDao");
		LoaiSanPham_dao = (LoaiSanPhamDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/loaiSanPhamDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/sanPhamDao");
		ChiTietPhieuNhap_dao = (ChiTietPhieuNhapDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/chiTietPhieuNhapDao");
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
		
		JLabel lblTitle = new JLabel("Thông tin phiếu nhập");
		lblTitle.setBounds(470, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 80);
		top += padding+20;
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		
		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã phiếu nhập:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);
		
		txtNgayLap = new TextField();
		txtNgayLap.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayLap.setLineColor(new Color(149, 166, 248));
		txtNgayLap.setLabelText("Ngày lập:");
		txtNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayLap.setColumns(10);
		txtNgayLap.setBackground(new Color(203, 239, 255));
		txtNgayLap.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtNgayLap);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayLap);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 55);
		top += padding+15;
		add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtNguoiLap = new TextField();
		txtNguoiLap.setLabelText("Người lập:");
		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNguoiLap.setBackground(new Color(203, 239, 255));
		txtNguoiLap.setBounds(0, 0, 371, 55);
		txtNguoiLap.setEditable(false);
		pnlRow2.add(txtNguoiLap);
		
		txtNhaCungCap = new TextField();
		txtNhaCungCap.setLabelText("Nhà cung cấp:");
		txtNhaCungCap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhaCungCap.setBackground(new Color(203, 239, 255));
		txtNhaCungCap.setBounds(421, 0, 371, 55);
		pnlRow2.add(txtNhaCungCap);
		txtNhaCungCap.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tableModelSP.setRowCount(0);
				try {
					for (SanPham sp : SanPham_dao.getAllSanPhamTheoNhaCungCap(txtNhaCungCap.getText())) {
						Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()), sp.getNhaCungCap(),
								sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
						tableModelSP.addRow(data);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tableModelSP.setRowCount(0);
				try {
					for (SanPham sp : SanPham_dao.getAllSanPhamTheoNhaCungCap(txtNhaCungCap.getText())) {
						Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()), sp.getNhaCungCap(),
								sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
						tableModelSP.addRow(data);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, txtNhaCungCap.getText());
			}
		});
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left+70, top, 992, 50);
		top += padding;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnThem = new Button("Tạo phiếu nhập");
		btnThem.setIcon(Utils.getImageIcon("document.png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(80, 0, 220, 40);
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
		btnRefresh.setBounds(420, 0, 150, 40);
		pnlActions.add(btnRefresh);
		
		JScrollPane scrCTKM = new JScrollPane();
		scrCTKM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrCTKM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrCTKM.setBounds(left-300, 300, 750, 150);
		scrCTKM.setBackground(Utils.primaryColor);
		scrCTKM.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTKM = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTKM.setScrollbarColor(new Color(203, 203, 203));
		scrCTKM.setVerticalScrollBar(scpCTKM);
		this.add(scrCTKM);

		tblCTPN = new JTable() {
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
		tblCTPN.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTPN.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderCTKM = tblCTPN.getTableHeader();
		TableColumnModel tableColumnModel = tblCTPN.getColumnModel();

		tableModelCTPN= new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm","Số lượng" }, 0);
		tblCTPN.setModel(tableModelCTPN);
		tblCTPN.setFocusable(false);
		tblHeaderCTKM.setBackground(Utils.primaryColor);
		tblCTPN.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTPN.setBackground(Color.WHITE);
		tblCTPN.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(200);
		tableColumnModel.getColumn(1).setPreferredWidth(350);
		tableColumnModel.getColumn(2).setPreferredWidth(200);
		tblCTPN.addMouseListener(new MouseListener() {
			
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
				int row = tblCTPN.getSelectedRow();
				txtSoLuong.setText(tblCTPN.getValueAt(row, 2).toString());
				moKhoaButtonSP();
			}
		});
		
		tblHeaderCTKM.setPreferredSize(new Dimension((int) tblHeaderCTKM.getPreferredSize().getWidth(), 36));
		tblHeaderCTKM.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderCTKM.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTPN.setRowHeight(36);
		scrCTKM.setViewportView(tblCTPN);
		
//		pnlControlCTPN = new ControlPanel(Utils.getLeft(650),topPnlControl - 270 , main);
//		this.add(pnlControlCTPN);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left+470, 300, 792, 120);
		top += padding+50;
		add(pnlRow3);
		pnlRow3.setLayout(null);
		
		txtSoLuong= new TextField();
		txtSoLuong.setLabelText("Số lượng:");
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBackground(new Color(203, 239, 255));
		txtSoLuong.setBounds(0, 0, 200, 55);
		pnlRow3.add(txtSoLuong);
		
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
		btnDeleteSP.setBounds(230, 65, 220, 40);
		pnlRow3.add(btnDeleteSP);
		
		
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
		pnlRow3.add(btnAddSP);
		
		JScrollPane scrSP = new JScrollPane();
		scrSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrSP.setBounds(left - 300, 500, 1400, 230);
		scrSP.setBackground(Utils.primaryColor);
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
				int row = tblSP.getSelectedRow();
				if(txtNhaCungCap.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhà cung cấp trước khi chọn sản phẩm");
				}
				else
					moKhoaButtonSP();
			}
		});

		tblHeaderSP.setPreferredSize(new Dimension((int) tblHeaderSP.getPreferredSize().getWidth(), 36));
		tblHeaderSP.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderSP.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblSP.setRowHeight(36);
		scrSP.setViewportView(tblSP);
		
//		pnlControlSP = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControlSP);
		khoaButtonSP();
		
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
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),format.format(sp.getDonGia()), sp.getNhaCungCap(),
					sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
			tableModelSP.addRow(data);
		}
		
		
		btnAddSP.addActionListener(this);
		btnDeleteSP.addActionListener(this);
		btnThem.addActionListener(this);
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
			if(txtSoLuong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng");
			}
			else {
				if(validateProductInput()) {
					int row = tblSP.getSelectedRow();
					int totalRow = tblCTPN.getRowCount();
					String masp = tblSP.getValueAt(row, 0).toString();
					String tensp = tblSP.getValueAt(row, 1).toString();
					boolean tontai = false;
					for(int i = 0;i < totalRow;i++) {
						if(tblCTPN.getValueAt(i, 0).toString().equals(masp)) {
							tontai = true;
							int sl = Integer.valueOf(txtSoLuong.getText()) + Integer.valueOf(tblCTPN.getValueAt(i, 2).toString());
							tblCTPN.setValueAt(sl, i, 2);
							txtSoLuong.setText("");
							khoaButtonSP();
							tblSP.clearSelection();
						}
					}
					if(tontai == false) {
						Object[] data = {masp,tensp,txtSoLuong.getText()};
						tableModelCTPN.addRow(data);
						txtSoLuong.setText("");
						khoaButtonSP();
						tblSP.clearSelection();
					}
				}		
			}	
		}
		else if(src.equals(btnRefresh)) {
			tblSP.clearSelection();
			tblCTPN.clearSelection();
			khoaButtonSP();
			txtSoLuong.setText("");
			txtNhaCungCap.setText("");
			tableModelCTPN.setRowCount(0);
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
		}
		else if(src.equals(btnDeleteSP)) {
			int row = tblCTPN.getSelectedRow();
			if(row != -1) {
				tableModelCTPN.removeRow(row);
				khoaButtonSP();
				tblCTPN.clearSelection();
				txtSoLuong.setText("");
			}
			else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
			}
		}
		else if(src.equals(btnThem)) {
			if(tableModelCTPN.getRowCount() != 0) {
				if(validator()) {				
					try {
						int row = tableModelCTPN.getRowCount();
						String mapn = taoMaPhieuNhap();
						String ngaylap = txtNgayLap.getText();
						String nhaCungCap = txtNhaCungCap.getText();
						String[] date = ngaylap.split("-");
						NhanVien nv = NhanVien_dao.getNhanVien(maNV);
						PhieuNhap pn = new PhieuNhap(mapn, LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2])), nv, nhaCungCap);
						if(PhieuNhap_dao.create(pn)) {
							for(int i = 0;i < row;i++) {
								String masp = tblCTPN.getValueAt(i, 0).toString();
								SanPham sp = SanPham_dao.getSanPham(masp);
								int soLuong = Integer.valueOf(tblCTPN.getValueAt(i, 2).toString());
								SanPham_dao.UpdateNhaCungCap(masp, nhaCungCap);
								ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(pn, sp, soLuong);
								int soLuongCapNhat=0;
								for (SanPham sp1 : SanPham_dao.getAllSanPham()) {
									if(sp1.getMaSanPham().equals(masp))
										soLuongCapNhat = sp1.getSoLuong()+ soLuong;
								}
								if(ChiTietPhieuNhap_dao.create(ctpn)) {
									
									SanPham_dao.UpdateSoLuong(masp, soLuongCapNhat);
								}
							}
							JOptionPane.showMessageDialog(this, "Lập phiếu nhập thành công");
							refresh();
						}
					} catch (NumberFormatException | HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
			else
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng của sản phẩm");
//			JOptionPane.showMessageDialog(this, taoMaPhieuNhap());
		}
	}
	public void refresh() throws RemoteException {
		txtMa.setText("");
		txtNhaCungCap.setText("");
		txtSoLuong.setText("");
		tableModelCTPN.setRowCount(0);
		tableModelSP.setRowCount(0);
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			Object[] data = {sp.getMaSanPham(),sp.getTenSanPham(),Math.round(sp.getDonGia()), sp.getNhaCungCap(),
					sp.getMauSac().getTenMauSac(), sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),sp.getSoLuong()};
			tableModelSP.addRow(data);
		}
	}
	public String taoMaPhieuNhap() throws RemoteException {
		ArrayList<String> dsmaPN = new ArrayList<String>();
		for (PhieuNhap pn : PhieuNhap_dao.getAllPhieuNhap()) {
			dsmaPN.add(pn.getMaPhieuNhap());
		}
		for(int i = 1;i <= 1000;i++) {
			String ma = "";
			if(i<10)
				ma = "PN000"+i;
			else if(i<100)
				ma = "PN00"+i;
			else if(i<1000)
				ma = "PN0"+i;
			else
				ma = "PN1000";
			if(dsmaPN.contains(ma) == false)
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
	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";

		String ncc = txtNhaCungCap.getText().trim();
		if (ncc.length() <= 0)
			return showThongBaoLoi(txtNhaCungCap, "Vui lòng nhập nhà cung cấp");
		if (!ncc.matches("^[a-zA-ZÀ-ỹ ]*$"))
			return showThongBaoLoi(txtNhaCungCap, "Nhà cung cấp chỉ chứa các ký tự chữ cái");

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
}
