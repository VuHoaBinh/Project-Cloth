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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
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
import components.notification.Notification;
import components.notification.Notification.Type;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietPhieuNhapDAO;
import dao.NhanVienDAO;
import dao.PhieuNhapDAO;
import dao.SanPhamDAO;
import entities.ChiTietPhieuNhap;
import entities.NhanVien;
import entities.PhieuNhap;
import entities.SanPham;
import utils.Utils;

public class XemDanhSachPhieuNhap extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtNguoiLap;
	private JRadioButton rdoNguoiLap;
	private TextField txtNgayLap;
	private DateChooser dateChoose;
	private JRadioButton rdoNgayLap;
	private TextField txtNhaCungCap;
	private JRadioButton rdoNhaCungCap;
	private Button btnTimKiem;
	private Button btnBoChon;
	private ButtonGroup btnGroup;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private JTable tblCTPN;
	private DefaultTableModel tableModelCTPN;
	private SanPhamDAO SanPham_dao;
	private ArrayList<SanPham> dsSP;
	private NhanVienDAO NhanVien_dao;
	private ArrayList<NhanVien> dsNV;
	private PhieuNhapDAO PhieuNhap_dao;
	private ChiTietPhieuNhapDAO ChiTietPhieuNhap_dao;
	public XemDanhSachPhieuNhap(ManHinhChinh main) throws RemoteException, MalformedURLException, NotBoundException {
		this.main= main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		PhieuNhap_dao = (PhieuNhapDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/phieuNhapDao");
		ChiTietPhieuNhap_dao = (ChiTietPhieuNhapDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/chiTietPhieuNhapDao");
		NhanVien_dao = (NhanVienDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/nhanVienDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/sanPhamDao");
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

		JLabel lblTitle = new JLabel("Danh sách phiếu nhập hàng");
		lblTitle.setBounds(450, 10, 500, 40);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);

		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left-150, top+20, 1100, 70);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		
		txtNguoiLap  = new TextField();
		txtNguoiLap.setLabelText("Người lập:");
		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNguoiLap.setBackground(new Color(203, 239, 255));
		txtNguoiLap.setBounds(30, 0, 200, 55);
		rdoNguoiLap = new JRadioButton(); 
		rdoNguoiLap.setBounds(0, 30, 25, 25); 
		rdoNguoiLap.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNguoiLap);
		pnlRow1.add(rdoNguoiLap);

		txtNgayLap = new TextField();
		txtNgayLap.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayLap.setLineColor(new Color(149, 166, 248));
		txtNgayLap.setLabelText("Ngày lập:");
		txtNgayLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayLap.setColumns(10);
		txtNgayLap.setBackground(new Color(203, 239, 255));
		txtNgayLap.setBounds(270, 0, 200, 55);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayLap);
		rdoNgayLap = new JRadioButton(); 
		rdoNgayLap.setBounds(240, 30, 25, 25); 
		rdoNgayLap.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNgayLap);
		pnlRow1.add(rdoNgayLap);
		
		
		txtNhaCungCap  = new TextField();
		txtNhaCungCap.setLabelText("Nhà cung cấp:");
		txtNhaCungCap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhaCungCap.setBackground(new Color(203, 239, 255));
		txtNhaCungCap.setBounds(510, 0, 200, 55);
		rdoNhaCungCap = new JRadioButton(); 
		rdoNhaCungCap.setBounds(480, 30, 25, 25); 
		rdoNhaCungCap.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNhaCungCap);
		pnlRow1.add(rdoNhaCungCap);
		
		
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
	    
	    btnGroup = new ButtonGroup();
	    btnGroup.add(rdoNguoiLap);
	    btnGroup.add(rdoNgayLap);
	    btnGroup.add(rdoNhaCungCap);
	    
	    JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left-330, 180, 1450, 300);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu nhập"));
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

		tableModel = new DefaultTableModel(new String[] { "Mã phiếu lập","Người lập","Ngày lập","Nhà cung cấp"}, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(300);
		tableColumnModel.getColumn(1).setPreferredWidth(400);
		tableColumnModel.getColumn(2).setPreferredWidth(350);
		tableColumnModel.getColumn(3).setPreferredWidth(400);

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
				txtNguoiLap.setText(tbl.getValueAt(row, 1).toString());
				txtNgayLap.setText(tbl.getValueAt(row, 2).toString());
				txtNhaCungCap.setText(tbl.getValueAt(row, 3).toString());
				tableModelCTPN.setRowCount(0);
				try {
					for (ChiTietPhieuNhap ctpn : ChiTietPhieuNhap_dao.getAllChiTietHoaDon(tbl.getValueAt(row, 0).toString())) {
						Object[] data = {ctpn.getSanPham().getMaSanPham(), getTenSP(ctpn.getSanPham().getMaSanPham()),ctpn.getSoLuong()};
						tableModelCTPN.addRow(data);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
		JScrollPane scrCTHD = new JScrollPane();
		scrCTHD.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrCTHD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrCTHD.setBounds(left-330, 500, 1450, 250);
		scrCTHD.setBackground(Utils.primaryColor);
		scrCTHD.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu nhập"));
		scrCTHD.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTHD = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTHD.setScrollbarColor(new Color(203, 203, 203));
		scrCTHD.setVerticalScrollBar(scpCTHD);
		this.add(scrCTHD);

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
		JTableHeader tblHeaderCTHD = tblCTPN.getTableHeader();
		TableColumnModel tableColumnModelCTHD = tblCTPN.getColumnModel();

		tableModelCTPN = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm","Số lượng"}, 0);
		tblCTPN.setModel(tableModelCTPN);
		tblCTPN.setFocusable(false);
		tblHeaderCTHD.setBackground(Utils.primaryColor);
		tblCTPN.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTPN.setBackground(Color.WHITE);
		tblCTPN.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelCTHD.getColumn(0).setPreferredWidth(500);
		tableColumnModelCTHD.getColumn(1).setPreferredWidth(550);
		tableColumnModelCTHD.getColumn(2).setPreferredWidth(400);
		
		tblHeaderCTHD.setPreferredSize(new Dimension((int) tblHeaderCTHD.getPreferredSize().getWidth(), 36));
		tblHeaderCTHD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTPN.setRowHeight(36);
		scrCTHD.setViewportView(tblCTPN);
		
		dsSP = new ArrayList<SanPham>();
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			dsSP.add(sp);
		}
		dsNV = new ArrayList<NhanVien>();
		
		for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
			dsNV.add(nv);
		}
		
		for (PhieuNhap pn : PhieuNhap_dao.getAllPhieuNhap()) {
			Object[] data = {pn.getMaPhieuNhap(),pn.getNhanVien().getTenNhanVien(),pn.getNgayLap(), pn.getNhaCungCap()};
			tableModel.addRow(data);
		}
		btnTimKiem.addActionListener(this);
		btnBoChon.addActionListener(this);
	}
	public String getTenSP(String maSP) {
		for (SanPham sp : dsSP) {
			if(sp.getMaSanPham().equals(maSP))
				return sp.getTenSanPham();
		}
		return null;
	}
	public String getTenNV(String maNV) {
		for (NhanVien nv : dsNV) {
			if(nv.getMaNhanVien().equals(maNV))
				return nv.getTenNhanVien();
		}
		return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnTimKiem)) {
			tableModelCTPN.setRowCount(0);
			if(rdoNguoiLap.isSelected()) {
				tableModel.setRowCount(0);
				String nguoilap = "";
				for (NhanVien nhanVien : dsNV) {
					if(nhanVien.getTenNhanVien().equals(txtNguoiLap.getText()))
							nguoilap = nhanVien.getMaNhanVien();
				}
				try {
					for (PhieuNhap pn : PhieuNhap_dao.getAllPhieuNhap()) {
						if(pn.getNhanVien().getMaNhanVien().equals(nguoilap)) {
							Object[] data = {pn.getMaPhieuNhap(),pn.getNhanVien().getTenNhanVien(),pn.getNgayLap(), pn.getNhaCungCap()};
							tableModel.addRow(data);
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(rdoNgayLap.isSelected()) {
				tableModel.setRowCount(0);
				String ngaylap = txtNgayLap.getText();
				try {
					for (PhieuNhap pn : PhieuNhap_dao.getAllPhieuNhap()) {
						if(pn.getNgayLap().toString().equals(ngaylap)) {
							Object[] data = {pn.getMaPhieuNhap(),pn.getNhanVien().getTenNhanVien(),pn.getNgayLap(), pn.getNhaCungCap()};
							tableModel.addRow(data);
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(rdoNhaCungCap.isSelected()) {
				tableModel.setRowCount(0);
				String nhacungcap = txtNhaCungCap.getText();
				try {
					for (PhieuNhap pn : PhieuNhap_dao.getAllPhieuNhap()) {
						if(pn.getNhaCungCap().equals(nhacungcap)) {
							Object[] data = {pn.getMaPhieuNhap(),pn.getNhanVien().getTenNhanVien(),pn.getNgayLap(), pn.getNhaCungCap()};
							tableModel.addRow(data);
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(src.equals(btnBoChon)) {
			txtNgayLap.setText("");
			txtNguoiLap.setText("");
			txtNhaCungCap.setText("");
			rdoNgayLap.setSelected(false);
			rdoNguoiLap.setSelected(false);
			rdoNhaCungCap.setSelected(false);;
			tbl.clearSelection();
			tableModelCTPN.setRowCount(0);
			try {
				for (PhieuNhap pn : PhieuNhap_dao.getAllPhieuNhap()) {		
					Object[] data = {pn.getMaPhieuNhap(),pn.getNhanVien().getTenNhanVien(),pn.getNgayLap(), pn.getNhaCungCap()};
					tableModel.addRow(data);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
//	private boolean showThongBaoLoi(TextField txt, String message) {
//		new Notification(main, Type.ERROR, message).showNotification();
////		txt.setError(true);
//		txt.selectAll();
//		txt.requestFocus();
//		return false;
//	}
////
//	private boolean validator() {
//		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
//		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";
//
//		String ten = txtNguoiLap.getText().trim();
//		if (ten.length() <= 0)
//			return showThongBaoLoi(txtNhaCungCap, "Vui lòng nhập tên người lập");
//		if (!ten.matches("^[a-zA-ZÀ-ỹ ]*$"))
//			return showThongBaoLoi(txtNhaCungCap, "Tên người lập chỉ chứa các ký tự chữ cái");
//		
//		
//		String ncc = txtNhaCungCap.getText().trim();
//		if (ncc.length() <= 0)
//			return showThongBaoLoi(txtNhaCungCap, "Vui lòng nhập tên nhà cung cấp");
//		if (!ncc.matches("^[a-zA-ZÀ-ỹ ]*$"))
//			return showThongBaoLoi(txtNhaCungCap, "Tên nhà cung cấp chỉ chứa các ký tự chữ cái");
//		
//		return true;
//	}
}
