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

import components.button.Button;
import components.comboBox.ComboBox;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.KichThuocDAO;
import dao.LoaiSanPhamDAO;
import dao.MauSacDAO;
import dao.SanPhamDAO;
import entities.KichThuoc;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.SanPham;
import utils.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import java.util.ArrayList;

import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class TimKiemSanPham extends JPanel implements ActionListener{
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtTen;
	private JComboBox<String> cboSize;
	private JComboBox<String> cboColor;
	private TextField txtNhaCungCap;
	private TextField txtDonVi;
	private JLabel lblTieuDe;
	private SanPhamDAO SanPham_dao;
	private Button btnLamMoi;
	private Button btnSearch;
	private TextField txtSoLuong;
	private LoaiSanPhamDAO LoaiSanPham_dao;
	private KichThuocDAO KichThuoc_dao;
	private MauSacDAO MauSac_dao;
	private ComboBox cboLoaiSanPham;
	private TextField txtDonGia;
	private ArrayList<LoaiSanPham> dsLSP;
	private ArrayList<KichThuoc> dsKT;
	private ArrayList<MauSac> dsMS;
	private NumberFormat format;
	private ArrayList<String> token;
	/**
	 * Create the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public TimKiemSanPham(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;

		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		LoaiSanPham_dao = (LoaiSanPhamDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/loaiSanPhamDao");
		KichThuoc_dao = (KichThuocDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/kichThuocDao");
		MauSac_dao = (MauSacDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/mauSacDao");
		SanPham_dao = (SanPhamDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/sanPhamDao");
		token = new ArrayList<String>();
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
		
		lblTieuDe = new JLabel();
		lblTieuDe.setText("Thông tin sản phẩm");
		lblTieuDe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblTieuDe.setBackground(new Color(203, 239, 255));
		lblTieuDe.setBounds(550, 30, 371, 55);
		pnlContainer.add(lblTieuDe);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMa = new TextField();
//		txtMa.setEditable(false);
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
		top += padding+20;
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
		cboLoaiSanPham.setBounds(421, 46, 371, 36);
		cboLoaiSanPham.addItem("");
		pnlRow2.add(cboLoaiSanPham);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 55);
		top += padding;
		add(pnlRow3);
		pnlRow3.setLayout(null);

		txtNhaCungCap = new TextField();
		txtNhaCungCap.setLabelText("Nhà cung cấp:");
		txtNhaCungCap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhaCungCap.setBackground(new Color(203, 239, 255));
		txtNhaCungCap.setBounds(0, 0, 371, 55);
		pnlRow3.add(txtNhaCungCap);

		txtDonVi = new TextField();
		txtDonVi.setLabelText("Đơn vị:");
		txtDonVi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDonVi.setBackground(new Color(203, 239, 255));
		txtDonVi.setBounds(421, 0, 371, 55);
		pnlRow3.add(txtDonVi);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 792, 80);
		top += padding+20;
		add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblColor = new JLabel("Màu sắc:");
		lblColor.setForeground(Utils.labelTextField);
		lblColor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblColor.setBounds(0, 0, 100, 65);
		pnlRow4.add(lblColor);

		cboColor = new ComboBox<>();
		cboColor.setModel(new DefaultComboBoxModel<String>());
		cboColor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboColor.setBackground(new Color(140, 177, 180));
		cboColor.setBounds(0, 46, 371, 36);
		cboColor.addItem("");
		pnlRow4.add(cboColor);

		JLabel lblSize = new JLabel("Kích thước:");
		lblSize.setForeground(Utils.labelTextField);
		lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSize.setBounds(421, 0, 100, 65);
		pnlRow4.add(lblSize);

		cboSize = new ComboBox<>();
		cboSize.setModel(new DefaultComboBoxModel<String>());
		cboSize.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboSize.setBackground(new Color(140, 177, 180));
		cboSize.setBounds(421, 46, 371, 36);
		cboSize.addItem("");
		pnlRow4.add(cboSize);
		
		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 792, 80);
		top += padding + 20;
		add(pnlRow5);
		pnlRow5.setLayout(null);
		
		txtSoLuong = new TextField();
		txtSoLuong.setLabelText("Số lượng:");
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBackground(new Color(203, 239, 255));
		txtSoLuong.setBounds(0, 0, 371, 55);
		pnlRow5.add(txtSoLuong);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 992, 50);
		top += padding + 20;
		add(pnlActions);
		pnlActions.setLayout(null);

		btnSearch = new Button("Tìm");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(Utils.getImageIcon("searching.png"));
		btnSearch.setRadius(4);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setColor(new Color(134, 229, 138));
		btnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearch.setBounds(904, -2, 150, 40);
		btnSearch.setBorderColor(Utils.secondaryColor);
		btnSearch.setColorOver(new Color(134, 229, 138));
		btnSearch.setColorClick(new Color(59, 238, 66));
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setBounds(250, 0, 150, 40);

		pnlActions.add(btnSearch);
		
		
		btnLamMoi = new Button("Làm mới");
		btnLamMoi.setIcon(Utils.getImageIcon("refresh.png"));
		btnLamMoi.setRadius(4);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnLamMoi.setColorOver(Utils.primaryColor);
		btnLamMoi.setColorClick(new Color(161, 184, 186));
		btnLamMoi.setColor(Utils.primaryColor);
		btnLamMoi.setBorderColor(Utils.secondaryColor);
		btnLamMoi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLamMoi.setBounds(450, -2, 150, 40);
		pnlActions.add(btnLamMoi);
		
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
		tbl.setAutoCreateRowSorter(true);
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tableModel = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Đơn giá bán","Nhà cung cấp","Màu sắc", "Kích thước", "Loại sản phẩm", "Đơn vị",
				"Số lượng"}, 0);
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
				if(tbl.getValueAt(row, 4) == null)
					txtNhaCungCap.setText("");
				else
					txtNhaCungCap.setText(tbl.getValueAt(row, 4).toString());
				txtDonGia.setText(String.valueOf(Math.round(chuyenDoiFormat(tbl.getValueAt(row, 2).toString()))));
				if(tbl.getValueAt(row, 9) == null)
					txtSoLuong.setText("");
				else
					txtSoLuong.setText(tbl.getValueAt(row, 9).toString());
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
		
//		pnlControl = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControl);
		
		btnLamMoi.addActionListener(this);
		btnSearch.addActionListener(this);
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnSearch)) {
			String sql = ChecktaoSQL();
			tableModel.setRowCount(0);
			try {
				for (SanPham sp : SanPham_dao.Find(sql, token)) {
					Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), format.format(sp.getDonGia()),
							format.format(sp.getDonGia() * 120 / 100), sp.getNhaCungCap(), sp.getMauSac().getTenMauSac(),
							sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),
							sp.getSoLuong() };
					tableModel.addRow(data);
				}
				token.clear();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			JOptionPane.showMessageDialog(this, sql);
			
		}
		else if(src.equals(btnLamMoi)) {
			try {
				refresh();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void refresh() throws RemoteException {
		txtMa.setText("");
		txtTen.setText("");
		txtDonVi.setText("");
		txtDonGia.setText("");
		txtNhaCungCap.setText("");
		cboLoaiSanPham.setSelectedIndex(0);
		txtSoLuong.setText("");
		cboColor.setSelectedIndex(0);
		cboSize.setSelectedIndex(0);
		tableModel.setRowCount(0);
		tbl.clearSelection();
		for (SanPham sp : SanPham_dao.getAllSanPham()) {
			Object[] data = { sp.getMaSanPham(), sp.getTenSanPham(), format.format(sp.getDonGia()),
					format.format(sp.getDonGia() * 120 / 100), sp.getNhaCungCap(), sp.getMauSac().getTenMauSac(),
					sp.getKichThuoc().getTenKichThuoc(), sp.getLoaiSanPham().getTenLoaiSanPham(), sp.getDonVi(),
					sp.getSoLuong() };
			tableModel.addRow(data);
		}
	}
	public String ChecktaoSQL() {
		String sql = "select * from SanPham where";
		if(!txtMa.getText().isEmpty()) {
			token.add(txtMa.getText());
			sql +=" maSanPham = :a" + (token.size()-1)+"";
		}
		if(!txtTen.getText().isEmpty()) {
			token.add(txtTen.getText());
			if(sql.contains("maSanPham"))
				sql += " and tenSanPham = :a" + (token.size()-1)+"";
			else
				sql +=" tenSanPham = :a" + (token.size()-1)+"";
		}
		if(!txtDonVi.getText().isEmpty()) {
			token.add(txtDonVi.getText());
			if(sql.contains("and") || sql.contains("maSanPham") || sql.contains("tenSanPham"))
				sql += " and donVi = :a" + (token.size()-1)+"";
			else
				sql += " donVi = :a" + (token.size()-1)+"";
		}
		if(!txtNhaCungCap.getText().isEmpty()) {
			token.add(txtNhaCungCap.getText());
			if(sql.contains("and") || sql.contains("maNhanVien") || sql.contains("tenNhanVien")
					|| sql.contains("donVi"))
				sql += " and nhaCungCap = :a" + (token.size()-1)+"";
			else
				sql += " nhaCungCap = :a" + (token.size()-1)+"";
		}
		if(!cboColor.getSelectedItem().toString().isEmpty()) {
			token.add(getMaMS(cboColor.getSelectedItem().toString()));
			if(sql.contains("and") || sql.contains("maSanPham") || sql.contains("tenSanPham")
					|| sql.contains("donVi") || sql.contains("nhaCungCap"))
				sql += " and mauSac = :a" + (token.size()-1)+"";
			else
				sql += " mauSac = :a" + (token.size()-1)+"";
		}
		if(!cboSize.getSelectedItem().toString().isEmpty()) {
			token.add(getMaKT(cboSize.getSelectedItem().toString()));
			if(sql.contains("and") || sql.contains("maSanPham") || sql.contains("tenSanPham")
					|| sql.contains("donVi") || sql.contains("nhaCungCap") || sql.contains("mauSac"))
				sql += " and kichThuoc = :a"+(token.size()-1)+"";
			else
				sql += " kichThuoc = :a"+(token.size()-1)+"";
		}
		if(!cboLoaiSanPham.getSelectedItem().toString().isEmpty()) {
			token.add(getMaLSP(cboLoaiSanPham.getSelectedItem().toString()));
			if(sql.contains("and") || sql.contains("maSanPham") || sql.contains("tenSanPham")
					|| sql.contains("donVi") || sql.contains("nhaCungCap") || sql.contains("mauSac")
					|| sql.contains("kichThuoc"))
				sql += " and maLoaiSanPham = :a"+(token.size()-1)+"";
			else
				sql += " maLoaiSanPham = :a"+(token.size()-1)+"";
		}
		if(!txtSoLuong.getText().isEmpty()) {
			token.add(txtSoLuong.getText());
			if(sql.contains("and") || sql.contains("maSanPham") || sql.contains("tenSanPham")
					|| sql.contains("donVi") || sql.contains("nhaCungCap") || sql.contains("mauSac")
					|| sql.contains("kichThuoc")|| sql.contains("maLoaiSanPham"))
				sql += " and soLuong = :a" + (token.size()-1)+"";
			else
				sql += " soLuong = :a" + (token.size()-1)+"";
		}
		if(!txtDonGia.getText().isEmpty()) {
			token.add(txtDonGia.getText());
			if(sql.contains("and") || sql.contains("maSanPham") || sql.contains("tenSanPham")
					|| sql.contains("donVi") || sql.contains("nhaCungCap") || sql.contains("mauSac")
					|| sql.contains("kichThuoc")|| sql.contains("maLoaiSanPham")||sql.contains("soLuong"))
				sql += " and donGia = :a" + (token.size()-1)+"";
			else
				sql += " donGia = :a" + (token.size()-1)+"";
		}
		return sql;
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