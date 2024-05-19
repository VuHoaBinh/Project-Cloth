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
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietKhuyenMaiDAO;
import dao.KhuyenMaiDAO;
import dao.SanPhamDAO;
import entities.ChiTietKhuyenMai;
import entities.KhuyenMai;
import entities.NhanVien;
import entities.PhieuNhap;
import entities.SanPham;
import utils.Utils;

public class XemDanhSachKhuyenMai extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtTenKM;
	private JRadioButton rdoTenKM;
	private TextField txtNgayBD;
	private DateChooser dateChoose;
	private JRadioButton rdoNgayBD;
	private TextField txtNgayKT;
	private JRadioButton rdoNgayKT;
	private Button btnTimKiem;
	private Button btnBoChon;
	private ButtonGroup btnGroup;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private JTable tblCTKM;
	private DefaultTableModel tableModelCTKM;
	private KhuyenMaiDAO KhuyenMai_dao;
	private ChiTietKhuyenMaiDAO ChiTietKhuyenMai_dao;
	private SanPhamDAO SanPham_dao;
	
	public XemDanhSachKhuyenMai(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		this.main= main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		KhuyenMai_dao = (KhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khuyenMaiDao");
		ChiTietKhuyenMai_dao = (ChiTietKhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietKhuyenMaiDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/sanPhamDao");

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

		JLabel lblTitle = new JLabel("Danh sách khuyến mãi");
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
		
		
		txtTenKM  = new TextField();
		txtTenKM.setLabelText("Tên khuyến mãi:");
		txtTenKM.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKM.setBackground(new Color(203, 239, 255));
		txtTenKM.setBounds(30, 0, 200, 55);
		rdoTenKM = new JRadioButton(); 
		rdoTenKM.setBounds(0, 30, 25, 25); 
		rdoTenKM.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtTenKM);
		pnlRow1.add(rdoTenKM);

		txtNgayBD = new TextField();
		txtNgayBD.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayBD.setLineColor(new Color(149, 166, 248));
		txtNgayBD.setLabelText("Ngày bắt đầu:");
		txtNgayBD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayBD.setColumns(10);
		txtNgayBD.setBackground(new Color(203, 239, 255));
		txtNgayBD.setBounds(270, 0, 200, 55);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayBD);
		rdoNgayBD = new JRadioButton(); 
		rdoNgayBD.setBounds(240, 30, 25, 25); 
		rdoNgayBD.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNgayBD);
		pnlRow1.add(rdoNgayBD);
		
		
		
		
		txtNgayKT = new TextField();
		txtNgayKT.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgayKT.setLineColor(new Color(149, 166, 248));
		txtNgayKT.setLabelText("Ngày kết thúc:");
		txtNgayKT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgayKT.setColumns(10);
		txtNgayKT.setBackground(new Color(203, 239, 255));
		txtNgayKT.setBounds(510, 0, 200, 55);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgayKT);
		rdoNgayKT = new JRadioButton(); 
		rdoNgayKT.setBounds(480, 30, 25, 25); 
		rdoNgayKT.setBackground(new Color(203, 239, 255));
		pnlRow1.add(txtNgayKT);
		pnlRow1.add(rdoNgayKT);
		
		
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
	    btnGroup.add(rdoTenKM);
	    btnGroup.add(rdoNgayBD);
	    btnGroup.add(rdoNgayKT);
	    
	    JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left-330, 180, 1450, 300);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách khuyến mãi"));
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

		tableModel = new DefaultTableModel(new String[] { "Mã khuyến mãi","Tên khuyến mãi","Ngày bắt đầu","Ngày kết thúc"}, 0);
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
				txtTenKM.setText(tbl.getValueAt(row, 1).toString());
				txtNgayBD.setText(tbl.getValueAt(row, 2).toString());
				txtNgayKT.setText(tbl.getValueAt(row, 3).toString());
				tableModelCTKM.setRowCount(0);
				try {
					for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai(tbl.getValueAt(row, 0).toString())) {
						Object[] data = {ctkm.getSanPham().getMaSanPham(), getTenSP(ctkm.getSanPham().getMaSanPham()),
								ctkm.getPhanTramKhuyenMai()};
						tableModelCTKM.addRow(data);
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
		scrCTHD.setBorder(BorderFactory.createTitledBorder("Chi tiết khuyến mãi"));
		scrCTHD.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTHD = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTHD.setScrollbarColor(new Color(203, 203, 203));
		scrCTHD.setVerticalScrollBar(scpCTHD);
		this.add(scrCTHD);

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
		JTableHeader tblHeaderCTHD = tblCTKM.getTableHeader();
		TableColumnModel tableColumnModelCTHD = tblCTKM.getColumnModel();

		tableModelCTKM = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm","Phần trăm khuyến mãi"}, 0);
		tblCTKM.setModel(tableModelCTKM);
		tblCTKM.setFocusable(false);
		tblHeaderCTHD.setBackground(Utils.primaryColor);
		tblCTKM.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTKM.setBackground(Color.WHITE);
		tblCTKM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelCTHD.getColumn(0).setPreferredWidth(500);
		tableColumnModelCTHD.getColumn(1).setPreferredWidth(550);
		tableColumnModelCTHD.getColumn(2).setPreferredWidth(400);
		
		tblHeaderCTHD.setPreferredSize(new Dimension((int) tblHeaderCTHD.getPreferredSize().getWidth(), 36));
		tblHeaderCTHD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTKM.setRowHeight(36);
		scrCTHD.setViewportView(tblCTKM);
		
		btnTimKiem.addActionListener(this);
		btnBoChon.addActionListener(this);
		
		for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
			Object[] data = {km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayKetThuc()};
			tableModel.addRow(data);
		}
		
	}
	public String getTenSP(String ma) throws RemoteException {
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			if(sp.getMaSanPham().equals(ma))
				return sp.getTenSanPham();
		}
		return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnTimKiem)) {
			tableModelCTKM.setRowCount(0);
			 if(rdoNgayBD.isSelected()) {
					tableModel.setRowCount(0);
					String ngayBD = txtNgayBD.getText();
					try {
						for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
							if(km.getNgayBatDau().toString().equals(ngayBD)) {
								Object[] data = {km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayKetThuc()};
								tableModel.addRow(data);
							}
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			else if(rdoNgayKT.isSelected()) {
				tableModel.setRowCount(0);
				String ngayKT = txtNgayKT.getText();
				try {
					for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
						if(km.getNgayKetThuc().toString().equals(ngayKT)) {
							Object[] data = {km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayKetThuc()};
							tableModel.addRow(data);
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(rdoTenKM.isSelected()) {
				tableModel.setRowCount(0);
				String tenKM = txtTenKM.getText();
				try {
					for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
						if(km.getTenKhuyenMai().equals(tenKM)) {
							Object[] data = {km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayKetThuc()};
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
			txtNgayBD.setText("");
			txtNgayKT.setText("");
			txtTenKM.setText("");
			rdoNgayBD.setSelected(false);
			rdoNgayKT.setSelected(false);
			rdoTenKM.setSelected(false);;
			tbl.clearSelection();
			tableModel.setRowCount(0);
			tableModelCTKM.setRowCount(0);
			try {
				for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
					Object[] data = {km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayKetThuc()};
					tableModel.addRow(data);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
