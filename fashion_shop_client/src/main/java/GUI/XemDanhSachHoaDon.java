package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.controlPanel.ControlPanel;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import entities.SanPham;
import utils.Utils;

public class XemDanhSachHoaDon extends JPanel implements ActionListener{
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;

	private TextField txtTenKH;
	private TextField txtNgayLap;
	private TextField txtNguoiLap;
	private JRadioButton rdoTenKH;
	private JRadioButton rdoNgayLap;
	private JRadioButton rdoNguoiLap;
	private Button btnTimKiem;
	private Button btnBoChon;
	private HoaDonDAO HoaDon_dao;
	private NhanVienDAO NhanVien_Dao;
	private KhachHangDAO KhachHang_dao;
	private ChiTietHoaDonDAO ChiTietHoaDon_dao;
	private ButtonGroup btnGroup;
	private double tongTien;
	private DateChooser dateChoose;
	private JTable tblCTHD;
	private DefaultTableModel tableModelCTHD;
	private ArrayList<SanPham> dsSP;
	private SanPhamDAO SanPham_dao;
	private Button btnInHD;
	private TextField txtSDT;
	private NumberFormat format;
	private ArrayList<String> token;
	public XemDanhSachHoaDon(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		HoaDon_dao = (HoaDonDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/hoaDonDao");
		ChiTietHoaDon_dao = (ChiTietHoaDonDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietHoaDonDao");
		NhanVien_Dao = (NhanVienDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/nhanVienDao");
		KhachHang_dao = (KhachHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khachHangDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/sanPhamDao");
		format = NumberFormat.getInstance();
		token = new ArrayList<String>();
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

		JLabel lblTitle = new JLabel("Danh sách hóa đơn");
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
		pnlRow1.setBounds(left-250, top+20, 1450, 70);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		
		txtSDT  = new TextField();
		txtSDT.setLabelText("Số điện thoại:");
		txtSDT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSDT.setBackground(new Color(203, 239, 255));
		txtSDT.setBounds(0, 0, 150, 55);
		pnlRow1.add(txtSDT);
		
		txtTenKH  = new TextField();
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
		
		txtNguoiLap  = new TextField();
		txtNguoiLap.setLabelText("Người lập:");
		txtNguoiLap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNguoiLap.setBackground(new Color(203, 239, 255));
		txtNguoiLap.setBounds(560, 0, 180, 55);
		pnlRow1.add(txtNguoiLap);
		
		
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
	    
	    btnInHD = new Button("In hóa đơn"); 
	    btnInHD.setBounds(1060, 25, 160, 40); 
	    btnInHD.setIcon(Utils.getImageIcon("printer.png"));
	    btnInHD.setRadius(8);
	    btnInHD.setForeground(Color.WHITE);
	    btnInHD.setFont(new Font("Segoe UI", Font.PLAIN, 26));
	    btnInHD.setColorOver(Utils.getRGBA(36, 214, 134, 1));
	    btnInHD.setColorClick(Utils.getRGBA(24, 140, 87, 1));
	    btnInHD.setColor(Utils.getRGBA(36, 214, 134, 1));
	    btnInHD.setBorderColor(new Color(203, 239, 255));
	    btnInHD.setBorder(new EmptyBorder(0, 0, 0, 0));
	    pnlRow1.add(btnInHD);
	   
	    
	    JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left -330, 180, 1450, 300);
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

		tableModel = new DefaultTableModel(new String[] { "Mã hoá đơn","Ngày lập","Người lập","Khách hàng", "Số điện thoại", "Tổng tiền"}, 0);
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
				tableModelCTHD.setRowCount(0);
				try {
					for (ChiTietHoaDon cthd : ChiTietHoaDon_dao.getAllChiTietHoaDon(tbl.getValueAt(row, 0).toString())) {
						Double donGiaGoc = getDonGiaSP(cthd.getSanPham().getMaSanPham());
						Double phanTram = ((donGiaGoc*120/100)-cthd.getDonGia())/(donGiaGoc*120/100)*100;
						Object[] data = {cthd.getSanPham().getMaSanPham(),getTenSP(cthd.getSanPham().getMaSanPham()), cthd.getSoLuong(),
								format.format(cthd.getDonGia()),Math.round(phanTram),
								format.format(Double.valueOf(cthd.getSoLuong())*Double.valueOf(cthd.getDonGia()))};
						tableModelCTHD.addRow(data);
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
		scrCTHD.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
		scrCTHD.getViewport().setBackground(Color.WHITE);

		ScrollBarCustom scpCTHD = new ScrollBarCustom();
//		Set color scrollbar thumb
		scpCTHD.setScrollbarColor(new Color(203, 203, 203));
		scrCTHD.setVerticalScrollBar(scpCTHD);
		this.add(scrCTHD);

		tblCTHD = new JTable() {
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
		tblCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTHD.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeaderCTHD = tblCTHD.getTableHeader();
		TableColumnModel tableColumnModelCTHD = tblCTHD.getColumnModel();

		tableModelCTHD = new DefaultTableModel(new String[] { "Mã sản phẩm", "Tên sản phẩm","Số lượng","Đơn giá","Giảm giá","Thành tiền" }, 0);
		tblCTHD.setModel(tableModelCTHD);
		tblCTHD.setFocusable(false);
		tblHeaderCTHD.setBackground(Utils.primaryColor);
		tblCTHD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tblCTHD.setBackground(Color.WHITE);
		tblCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModelCTHD.getColumn(0).setPreferredWidth(300);
		tableColumnModelCTHD.getColumn(1).setPreferredWidth(300);
		tableColumnModelCTHD.getColumn(2).setPreferredWidth(200);
		tableColumnModelCTHD.getColumn(3).setPreferredWidth(200);
		tableColumnModelCTHD.getColumn(4).setPreferredWidth(200);
		tableColumnModelCTHD.getColumn(5).setPreferredWidth(250);
		
		tblHeaderCTHD.setPreferredSize(new Dimension((int) tblHeaderCTHD.getPreferredSize().getWidth(), 36));
		tblHeaderCTHD.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeaderCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTHD.setRowHeight(36);
		scrCTHD.setViewportView(tblCTHD);
		
//		pnlControl = new ControlPanel(Utils.getLeft(400),topPnlControl - 160 , main);
//		this.add(pnlControl);
		
		btnTimKiem.addActionListener(this);
		btnBoChon.addActionListener(this);
		btnInHD.addActionListener(this);
	
		
//		String nguoilap = "";
//		String khachhang = "";
//		Double tongTien = 0.0;
//		for (HoaDon hd : HoaDon_dao.getAllHoaDon()) {
//			for (NhanVien nv : NhanVien_Dao.getAllNhanVien()) {
//				if(nv.getMaNhanVien().equals(hd.getNguoiLap().getMaNhanVien()))
//					nguoilap = nv.getTenNhanVien();
//			}
//			for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
//				if(kh.getMaKhachHang().equals(hd.getKhachHang().getMaKhachHang()))
//					khachhang = kh.getTenKhachHang();
//			}
//			for (ChiTietHoaDon cthd : ChiTietHoaDon_dao.getAllChiTietHoaDon(hd.getMaHoaDon())) {
//					tongTien += cthd.getSoLuong()*cthd.getDonGia();
//			}
//			Object[] data = {hd.getMaHoaDon(), hd.getNgayLap(), nguoilap,
//					khachhang, Math.round(tongTien)};
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
		if(src.equals(btnTimKiem)) {
			tableModel.setRowCount(0);
			try {
				findDuLieu(ChecktaoSQL(),token);
				token.clear();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(src.equals(btnBoChon)) {
			tableModel.setRowCount(0);
			tableModelCTHD.setRowCount(0);
			txtNgayLap.setText("");
			txtNguoiLap.setText("");
			txtTenKH.setText("");
			txtSDT.setText("");
			try {
				loadDuLieu();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(src.equals(btnInHD)) {
			int row = tbl.getSelectedRow();
			if(row != -1) {
				inHoaDon(tbl.getValueAt(row, 0).toString());
//				openBill(tbl.getValueAt(row, 0).toString());
			}
				
			else
				JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn để in");
		}
	}
	public String getTenSP(String maSP) {
		for (SanPham sanPham : dsSP) {
			if(sanPham.getMaSanPham().equals(maSP))
				return sanPham.getTenSanPham();
		}
		return null;
	}
	public void openBill(String path) {
        try {
            if((new File(System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatHoaDon\\" + path + ".pdf")).exists()){
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll, FileProtocolHandler " + System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatHoaDon\\" + path + ".pdf");
            } else {
                JOptionPane.showMessageDialog(null, "File is not Exists");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
	public void inHoaDon(String maHD) {
//		System.setProperty("file.encoding", "utf-8");
		int row = tbl.getSelectedRow();
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\exportFile\\xuatHoaDon\\" + maHD + ".pdf";
        com.itextpdf.text.Font textFont = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10); //10 is the size
        com.itextpdf.text.Font textFont24 = FontFactory.getFont("font/SVN-Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 24); //30 is the size

        Document doc = new Document();
        try {
        	PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));
            doc.open();
            Paragraph ShopName = new Paragraph("                         CLOTHING SHOP\n", textFont24);
            doc.add(ShopName);
            Paragraph DiaChi = new Paragraph("                               Địa chỉ: 4 Nguyễn Văn Bảo. Quận Gò Vấp, Thành phố Hồ Chí Minh\n", textFont);
            doc.add(DiaChi);
            Paragraph SDT = new Paragraph("                                                          Số điện thoại: 0901234567\n", textFont);
            doc.add(SDT);
            Paragraph starLine = new Paragraph("==================================================================================", textFont);
            doc.add(starLine);

//            Thông tin nhân viên
            String[] ngaylap = txtNgayLap.getText().split("-");
            LocalDate date = LocalDate.of(Integer.valueOf(ngaylap[0]), Integer.valueOf(ngaylap[1]), Integer.valueOf(ngaylap[2]));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            Paragraph paragraphMaNhanVienLine1 = new Paragraph("               Mã nhân viên:" + getMaNV(tbl.getValueAt(row, 2).toString()) + "                                             Ngày lập hóa đơn: " + dtf.format(date) + "\n", textFont);
            doc.add(paragraphMaNhanVienLine1);
            Paragraph paragraphTenNhanVienLine2 = new Paragraph("              Tên nhân viên: " + tbl.getValueAt(row, 2).toString(), textFont);
            doc.add(paragraphTenNhanVienLine2);
            doc.add(starLine);
//
            //Thông tin hóa đơn
            
            Paragraph paragraphMaHoaDonLine1 = new Paragraph("              Mã hóa đơn:" + maHD + "                                          Mã khách hàng: " + getMaKH(tbl.getValueAt(row, 3).toString()) + "\n", textFont);
            doc.add(paragraphMaHoaDonLine1);
            Paragraph paragraphMaHoaDonLine2 = new Paragraph("              Tên khách hàng: " + tbl.getValueAt(row, 3).toString() + "\n", textFont);
            doc.add(paragraphMaHoaDonLine2);
//
            Paragraph paragraphMaHoaDonLine3 = new Paragraph("              Số điện thoại: " + getSDTKhachHang(tbl.getValueAt(row, 3).toString()) + "\n", textFont);
            doc.add(paragraphMaHoaDonLine3);
            doc.add(starLine);

//            Chi tiết hóa đơn
            NumberFormat formatter = NumberFormat.getInstance();
            Paragraph paragraph4 = new Paragraph("              Chi tiết hóa đơn", textFont);
            doc.add(paragraph4);
            Paragraph paragraph5 = new Paragraph("\n");
            doc.add(paragraph5);
            PdfPTable tbl = new PdfPTable(4);
            tbl.addCell(new Phrase("Tên sản phẩm", textFont));
            tbl.addCell(new Phrase("Đơn giá", textFont));
            tbl.addCell(new Phrase("Số lượng", textFont));
            tbl.addCell(new Phrase("Tổng tiền", textFont));
            for (int i = 0; i < tableModelCTHD.getRowCount(); i++) {
                String tenSP = tblCTHD.getValueAt(i, 1).toString();
                Double donGia = chuyenDoiFormat(tblCTHD.getValueAt(i, 3).toString());
                String sl = tblCTHD.getValueAt(i, 2).toString();
                Double tongTien = chuyenDoiFormat(tblCTHD.getValueAt(i, 4).toString());
                tbl.addCell(new Phrase(tenSP, textFont));
                tbl.addCell(new Phrase(formatter.format(Double.valueOf(donGia)), textFont));
                tbl.addCell(new Phrase(sl, textFont));
                tbl.addCell(new Phrase(formatter.format(Double.valueOf(tongTien)), textFont));
            }
            doc.add(tbl);
            doc.add(starLine);
            //Thông tin tổng tiền:
            Paragraph paragraphTongTienLine1 = new Paragraph("              Tổng tiền hóa đơn:" + this.tbl.getValueAt(row, 4).toString(), textFont);
            doc.add(paragraphTongTienLine1);
            doc.add(starLine);
            Paragraph thanskMsg = new Paragraph("               Xin cám ơn quí khách đã mua hàng tại cửa hàng của chúng tôi, mong bạn sẽ quay lại vào những lần tới ^^", textFont);
            doc.add(thanskMsg);
            doc.close();
            //open pdf
            openBill(maHD);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
	}
	public String getMaKH(String ten) throws RemoteException {
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			if(kh.getTenKhachHang().equals(ten))
				return kh.getMaKhachHang();
		}
		return null;
	}
	public String getMaNV(String ten) throws RemoteException {
		for (NhanVien nv : NhanVien_Dao.getAllNhanVien()) {
			if(nv.getTenNhanVien().equals(ten))
				return nv.getMaNhanVien();
		}
		return null;
	}
	public String getSDTKhachHang(String ten) throws RemoteException {
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			if(kh.getTenKhachHang().equals(ten))
				return kh.getSoDienThoai();
		}
		return null;
	}
	public Double getDonGiaSP(String ma) throws RemoteException {
		for (SanPham sanPham : SanPham_dao.getAllSanPham()) {
			if(sanPham.getMaSanPham().equals(ma))
				return sanPham.getDonGia();
		}
		return 0.0;
	}
	public void loadDuLieu() throws RemoteException {
		List<Object[]> dsHD = HoaDon_dao.loadDuLieuHoaDon();
		for (Object[] objects : dsHD) {
			Object[] data = { objects[0], objects[1], objects[2], objects[3], objects[4],
					format.format(Double.valueOf(objects[5].toString()))};
			tableModel.addRow(data);
		}
	}
	public void findDuLieu(String sql, ArrayList<String> token) throws RemoteException {
		List<Object[]> dsHD = HoaDon_dao.findHoaDon(sql, token);
		for (Object[] objects : dsHD) {
//			System.out.println(objects[0] + " " + objects[1] + " " + objects[2] + " " + objects[3] + " " + objects[4] + " " + objects[5]);
			Object[] data = { objects[0], objects[1], objects[2], objects[3], objects[4],
					format.format(Double.valueOf(objects[5].toString())) };
			tableModel.addRow(data);
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
	public String ChecktaoSQL() {
		token.clear();
		String sql = "select hd.maHoaDon, hd.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai, sum(cthd.donGia*cthd.soLuong) from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
				+ "join NhanVien nv on nv.maNhanVien = hd.nguoiLap\r\n"
				+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
				+ "group by hd.maHoaDon, hd.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai\r\n"
				+ "having";
		String having = "";
		if(!txtSDT.getText().equals("")) {
			token.add(txtSDT.getText());
			having +=" kh.soDienThoai = :a" + (token.size()-1)+"";
		}
		if(!txtNgayLap.getText().equals("")) {
			token.add(txtNgayLap.getText());
			if(having.contains("kh.soDienThoai"))
				having += " and hd.ngayLap = :a" + (token.size()-1)+"";
			else
				having +=" hd.ngayLap = :a" + (token.size()-1)+"";
		}
		if(!txtNguoiLap.getText().equals("")) {
			token.add(txtNguoiLap.getText());
			if(having.contains("and") || having.contains("kh.soDienThoai") || having.contains("hd.ngayLap"))
				having += " and nv.tenNhanVien = :a" + (token.size()-1)+"";
			else
				having += " nv.tenNhanVien = :a" + (token.size()-1)+"";
		}
		if(!txtTenKH.getText().equals("")) {
			token.add(txtTenKH.getText());
			if(having.contains("and") || having.contains("kh.soDienThoai") || having.contains("hd.ngayLap")
					|| having.contains("nv.tenNhanVien"))
				having += " and kh.tenKhachHang = :a" +(token.size()-1)+"";
			else
				having += " kh.tenKhachHang = :a" + (token.size()-1)+"";
		}
		
		return sql+having;
	}
}
