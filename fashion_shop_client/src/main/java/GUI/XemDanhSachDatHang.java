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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietDatHangDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.PhieuDatHangDAO;
import dao.SanPhamDAO;
import entities.ChiTietHoaDon;
import entities.ChiTietPhieuDatHang;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuDatHang;
import entities.SanPham;
import utils.Utils;

public class XemDanhSachDatHang extends JPanel implements ActionListener {
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;

	private TextField txtTenKH;
	private TextField txtNgayLap;
	private TextField txtNguoiLap;
	private Button btnTimKiem;
	private Button btnBoChon;

	private NhanVienDAO NhanVien_Dao;
	private KhachHangDAO KhachHang_dao;
	private double tongTien;
	private DateChooser dateChoose;
	private JTable tblCTPDH;
	private DefaultTableModel tableModelCTPDH;
	private ArrayList<SanPham> dsSP;
	private SanPhamDAO SanPham_dao;
	private PhieuDatHangDAO PhieuDatHang_dao;
	private ChiTietDatHangDAO ChiTietPhieuDatHang_dao;
	private Button btnLapHD;
	private TextField txtSDT;
	private String maTK;
	private NumberFormat format;
	private ArrayList<String> token;

	public XemDanhSachDatHang(ManHinhChinh main, String maTK)
			throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		this.maTK = maTK;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		PhieuDatHang_dao = (PhieuDatHangDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/phieuDatHangDao");
		ChiTietPhieuDatHang_dao = (ChiTietDatHangDAO) Naming
				.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/chiTietDatHangDao");
		SanPham_dao = (SanPhamDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/sanPhamDao");
		NhanVien_Dao = (NhanVienDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/nhanVienDao");
		KhachHang_dao = (KhachHangDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/khachHangDao");
		format = NumberFormat.getInstance();
		token = new ArrayList<String>();
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

		JLabel lblTitle = new JLabel("Danh sách đơn đặt hàng");
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
		pnlRow1.setBounds(left - 250, top + 20, 1450, 70);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtSDT = new TextField();
		txtSDT.setLabelText("Số điện thoại:");
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setBackground(new Color(203, 239, 255));
		txtSDT.setBounds(0, 0, 150, 55);
		txtSDT.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtSDT);

		txtTenKH = new TextField();
		txtTenKH.setLabelText("Tên khách hàng:");
		txtTenKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKH.setBackground(new Color(203, 239, 255));
		txtTenKH.setBounds(180, 0, 180, 55);
		pnlRow1.add(txtTenKH);

		txtNgayLap = new TextField();
		txtNgayLap.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayLap.setLineColor(new Color(149, 166, 248));
		txtNgayLap.setLabelText("Ngày lập:");
		txtNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayLap.setColumns(10);
		txtNgayLap.setBackground(new Color(203, 239, 255));
		txtNgayLap.setBounds(390, 0, 150, 55);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayLap);
		pnlRow1.add(txtNgayLap);

		txtNguoiLap = new TextField();
		txtNguoiLap.setLabelText("Người lập:");
		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNguoiLap.setBackground(new Color(203, 239, 255));
		txtNguoiLap.setBounds(560, 0, 180, 55);
//		rdoNguoiLap = new JRadioButton(); 
//		rdoNguoiLap.setBounds(480, 30, 25, 25); 
//		rdoNguoiLap.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNguoiLap);
//		pnlRow1.add(rdoNguoiLap);

		btnTimKiem = new Button("Tìm");
		btnTimKiem.setBounds(760, 25, 120, 40);
		btnTimKiem.setIcon(Utils.getImageIcon("searching.png"));
		btnTimKiem.setRadius(8);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnTimKiem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnTimKiem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setBorderColor(new Color(203, 239, 255));
		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnTimKiem);

		btnBoChon = new Button("Bỏ chọn");
		btnBoChon.setBounds(890, 25, 160, 40);
		btnBoChon.setIcon(Utils.getImageIcon("cancel.png"));
		btnBoChon.setRadius(8);
		btnBoChon.setForeground(Color.WHITE);
		btnBoChon.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnBoChon.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnBoChon.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnBoChon.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnBoChon.setBorderColor(new Color(203, 239, 255));
		btnBoChon.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnBoChon);

		btnLapHD = new Button("Lập hóa đơn");
		btnLapHD.setBounds(1060, 25, 200, 40);
		btnLapHD.setIcon(Utils.getImageIcon("bill (1).png"));
		btnLapHD.setRadius(8);
		btnLapHD.setForeground(Color.WHITE);
		btnLapHD.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnLapHD.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnLapHD.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnLapHD.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnLapHD.setBorderColor(new Color(203, 239, 255));
		btnLapHD.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlRow1.add(btnLapHD);
		btnLapHD.setEnabled(false);

		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left - 330, 180, 1450, 300);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
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

		tableModel = new DefaultTableModel(new String[] { "Mã phiếu đặt hàng", "Ngày lập", "Người lập", "Khách hàng",
				"Số điện thoại", "Tổng tiền" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(250);
		tableColumnModel.getColumn(1).setPreferredWidth(200);
		tableColumnModel.getColumn(2).setPreferredWidth(250);
		tableColumnModel.getColumn(3).setPreferredWidth(250);
		tableColumnModel.getColumn(4).setPreferredWidth(200);
		tableColumnModel.getColumn(5).setPreferredWidth(300);
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
				txtNguoiLap.setText(tbl.getValueAt(row, 2).toString());
				txtNgayLap.setText(tbl.getValueAt(row, 1).toString());
				txtTenKH.setText(tbl.getValueAt(row, 3).toString());
				txtSDT.setText(tbl.getValueAt(row, 4).toString());
				tableModelCTPDH.setRowCount(0);
				try {
					for (ChiTietPhieuDatHang ctpdh : ChiTietPhieuDatHang_dao
							.getAllChiTietPhieuDatHang(tbl.getValueAt(row, 0).toString())) {
						Double donGiaGoc = getDonGia(ctpdh.getSanPham().getMaSanPham());
						Double phanTram = ((donGiaGoc * 120 / 100) - ctpdh.getDonGia()) / (donGiaGoc * 120 / 100) * 100;
						Object[] data = { ctpdh.getSanPham().getMaSanPham(),
								getTenSP(ctpdh.getSanPham().getMaSanPham()), ctpdh.getSoLuong(),
								format.format(ctpdh.getDonGia()), Math.round(phanTram), format.format(
										Integer.valueOf(ctpdh.getSoLuong()) * Double.valueOf(ctpdh.getDonGia())) };
						tableModelCTPDH.addRow(data);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnLapHD.setEnabled(true);
			}
		});
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);

		JScrollPane scrCTPDH = new JScrollPane();
		scrCTPDH.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrCTPDH.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrCTPDH.setBounds(left - 330, 500, 1450, 250);
		scrCTPDH.setBackground(Utils.primaryColor);
		scrCTPDH.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu đặt hàng"));
		scrCTPDH.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTPDH = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTPDH.setScrollbarColor(new Color(203, 203, 203));
		scrCTPDH.setVerticalScrollBar(scpCTPDH);
		this.add(scrCTPDH);

		tblCTPDH = new JTable() {
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
		tblCTPDH.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTPDH.setAutoCreateRowSorter(true); // tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderCTPDH = tblCTPDH.getTableHeader();
		TableColumnModel tableColumnModelCTHD = tblCTPDH.getColumnModel();

		tableModelCTPDH = new DefaultTableModel(
				new String[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền" }, 0);
		tblCTPDH.setModel(tableModelCTPDH);
		tblCTPDH.setFocusable(false);
		tblHeaderCTPDH.setBackground(Utils.primaryColor);
		tblCTPDH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTPDH.setBackground(Color.WHITE);
		tblCTPDH.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelCTHD.getColumn(0).setPreferredWidth(200);
		tableColumnModelCTHD.getColumn(1).setPreferredWidth(250);
		tableColumnModelCTHD.getColumn(2).setPreferredWidth(250);
		tableColumnModelCTHD.getColumn(3).setPreferredWidth(250);
		tableColumnModelCTHD.getColumn(4).setPreferredWidth(250);
		tableColumnModelCTHD.getColumn(5).setPreferredWidth(250);

		tblHeaderCTPDH.setPreferredSize(new Dimension((int) tblHeaderCTPDH.getPreferredSize().getWidth(), 36));
		tblHeaderCTPDH.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderCTPDH.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTPDH.setRowHeight(36);
		scrCTPDH.setViewportView(tblCTPDH);

//		pnlControl = new ControlPanel(Utils.getLeft(400),topPnlControl - 160 , main);
//		this.add(pnlControl);

		btnTimKiem.addActionListener(this);
		btnBoChon.addActionListener(this);
		btnLapHD.addActionListener(this);
//		String nguoilap = "";
//		String khachhang = "";
//		String sdt = "";
//		Double tongTien = 0.0;
//		for (PhieuDatHang pdh : PhieuDatHang_dao.getAllPhieuDatHang()) {
//			for (NhanVien nv : NhanVien_Dao.getAllNhanVien()) {
//				if(nv.getMaNhanVien().equals(pdh.getNguoiLap().getMaNhanVien()))
//					nguoilap = nv.getTenNhanVien();
//			}
//			for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
//				if(kh.getMaKhachHang().equals(pdh.getKhachHang().getMaKhachHang())) {
//					khachhang = kh.getTenKhachHang();
//					sdt = kh.getSoDienThoai();
//				}
//					
//			}
//			for (ChiTietPhieuDatHang ctpdh : ChiTietPhieuDatHang_dao.getAllChiTietPhieuDatHang(pdh.getMaPhieuDatHang())) {
//					tongTien += ctpdh.getSoLuong()*ctpdh.getDonGia();
//			}
//			Object[] data = {pdh.getMaPhieuDatHang(), pdh.getNgayLap(), nguoilap,
//					khachhang, sdt, tongTien.toString()};
//			tableModel.addRow(data);
//			tongTien = 0.0;
//		}
		loadDuLieu();
		dsSP = new ArrayList<SanPham>();

		for (SanPham sanPham : SanPham_dao.getAllSanPham()) {
			dsSP.add(sanPham);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnTimKiem)) {
			tableModel.setRowCount(0);
			try {
				findDuLieu(ChecktaoSQL(), token);
				token.clear();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			JOptionPane.showMessageDialog(this, ChecktaoSQL());
		} else if (src.equals(btnBoChon)) {
			tableModel.setRowCount(0);
			tableModelCTPDH.setRowCount(0);
			txtNgayLap.setText("");
			txtNguoiLap.setText("");
			txtTenKH.setText("");
			txtSDT.setText("");
			btnLapHD.setEnabled(false);
			try {
				loadDuLieu();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (src.equals(btnLapHD)) {
			int row = tbl.getSelectedRow();
			int toTalRowCHPDH = tableModelCTPDH.getRowCount();
			String title = "Lập hóa đơn";
			LapHoaDon lhd;
			try {
				lhd = new LapHoaDon(main, maTK);
				for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
					if (kh.getSoDienThoai().equals(tbl.getValueAt(row, 4).toString())) {
						lhd.txtSoDienThoai.setText(kh.getSoDienThoai());
						lhd.txtTen.setText(kh.getTenKhachHang());
						lhd.txtDiaChi.setText(kh.getDiaChi());
						if (kh.getEmail() == null)
							lhd.txtEmail.setText("");
						else
							lhd.txtEmail.setText(kh.getEmail());
						if (kh.isGioiTinh() == true) {
							lhd.radNam.setSelected(true);
							lhd.radNu.setSelected(false);
						} else {
							lhd.radNam.setSelected(false);
							lhd.radNu.setSelected(true);
						}
						break;
					}
				}
				double tongTien = chuyenDoiFormat(tbl.getValueAt(row, 5).toString());
				lhd.tongTien = tongTien;
				
				lhd.txtTongTien.setText(tbl.getValueAt(row, 5).toString());
				for (int i = 0; i < toTalRowCHPDH; i++) {
					Object[] data = { tblCTPDH.getValueAt(i, 0), tblCTPDH.getValueAt(i, 1), tblCTPDH.getValueAt(i, 2),
							tblCTPDH.getValueAt(i, 3), Double.valueOf(tblCTPDH.getValueAt(i, 4).toString()),tblCTPDH.getValueAt(i, 5) };
					lhd.tableModelCTHD.addRow(data);
				}
				main.addPnlBody(lhd, title, 2, 1);
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public String getTenSP(String maSP) {
		for (SanPham sanPham : dsSP) {
			if (sanPham.getMaSanPham().equals(maSP))
				return sanPham.getTenSanPham();
		}
		return null;
	}

	public Double getDonGia(String ma) throws RemoteException {
		try {
			for (SanPham sanPham : SanPham_dao.getAllSanPham()) {
				if (sanPham.getMaSanPham().equals(ma))
					return sanPham.getDonGia();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
	}

	public void loadDuLieu() throws RemoteException {
		List<Object[]> dsPDH = PhieuDatHang_dao.loadDuLieuPhieuDatHang();
		for (Object[] objects : dsPDH) {
			Object[] data = { objects[0], objects[1], objects[2], objects[3], objects[4],
					format.format(Double.valueOf(objects[5].toString())) };
			tableModel.addRow(data);
		}
	}

	public void findDuLieu(String sql, ArrayList<String> token) throws RemoteException {
		List<Object[]> ds = PhieuDatHang_dao.findPhieuDatHang(sql, token);
		for (Object[] objects : ds) {
			Object[] data = { objects[0], objects[1], objects[2], objects[3], objects[4],
					format.format(Double.valueOf(objects[5].toString())) };
			tableModel.addRow(data);
		}
	}

	public String ChecktaoSQL() {
		token.clear();
		String sql = "select pdh.maPhieuDatHang,pdh.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai, sum(ctpdh.donGia*ctpdh.soLuong) from PhieuDatHang pdh\r\n"
				+ "join KhachHang kh on pdh.khachHang = kh.maKhachHang\r\n"
				+ "join NhanVien nv on pdh.nguoiLap = nv.maNhanVien\r\n"
				+ "join ChiTietPhieuDatHang ctpdh on pdh.maPhieuDatHang = ctpdh.maPhieuDatHang\r\n"
				+ "group by pdh.maPhieuDatHang,pdh.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai\r\n"
				+ "having";
		String having = "";
		if (!txtSDT.getText().isEmpty()) {
			token.add(txtSDT.getText());
			having += " kh.soDienThoai = :a" + (token.size() - 1) + "";
		}
		if (!txtNgayLap.getText().isEmpty()) {
			token.add(txtNgayLap.getText());
			if (having.contains("kh.soDienThoai"))
				having += " and pdh.ngayLap = :a" + (token.size() - 1) + "";
			else
				having += " pdh.ngayLap = :a" + (token.size() - 1) + "";
		}
		if (!txtNguoiLap.getText().isEmpty()) {
			token.add(txtNguoiLap.getText());
			if (having.contains("and") || having.contains("kh.soDienThoai") || having.contains("pdh.ngayLap"))
				having += " and nv.tenNhanVien = :a" + (token.size() - 1) + "";
			else
				having += " nv.tenNhanVien = :a" + (token.size() - 1) + "";
		}
		if (!txtTenKH.getText().isEmpty()) {
			token.add(txtTenKH.getText());
			if (having.contains("and") || having.contains("kh.soDienThoai") || having.contains("pdh.ngayLap")
					|| having.contains("nv.tenNhanVien"))
				having += " and kh.tenKhachHang = :a" + (token.size() - 1) + "";
			else
				having += " kh.tenKhachHang = :a" + (token.size() - 1) + "";
		}

		return sql + having;
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
