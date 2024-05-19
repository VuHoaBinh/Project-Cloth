package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
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

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import components.button.Button;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.ChiTietKhuyenMaiDAO;
import dao.KhuyenMaiDAO;
import dao.SanPhamDAO;
import entities.ChiTietKhuyenMai;
import entities.KhuyenMai;
import entities.SanPham;
import utils.Utils;

public class Form_ScanDatHang extends JDialog implements Runnable, ThreadFactory, ActionListener{
	private DatHang dh;
	private JTable tblCTHD;
	private DefaultTableModel tableModelCTHD;
	private Button btnHuy;
	private Button btnXacNhan;
	
	private WebcamPanel panel = null;
	private Webcam webcam = null;
	private Executor executor = Executors.newSingleThreadExecutor(this);
	private KhuyenMaiDAO KhuyenMai_dao;
	private ChiTietKhuyenMaiDAO ChiTietKhuyenMai_dao;
	private SanPhamDAO SanPham_dao;
	private Button btnXoa;
	private TextField txtTongTien;
	private Double tongTien = 0.0;
	private NumberFormat format;
	
	
	public Form_ScanDatHang(JPanel parent) throws MalformedURLException, RemoteException, NotBoundException {
		setSize(1200, 450);
		setLocationRelativeTo(null);
		setLayout(null);
		dh = (DatHang)parent;
		KhuyenMai_dao = (KhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khuyenMaiDao");
		ChiTietKhuyenMai_dao = (ChiTietKhuyenMaiDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/chiTietKhuyenMaiDao");
		SanPham_dao = (SanPhamDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/sanPhamDao");
		format = NumberFormat.getInstance();
		
//		webcam
		Dimension size = WebcamResolution.QVGA.getSize();
		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);
		
		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);
		panel.setBounds(0, 0, 470, 300);
		add(panel);
		executor.execute(this);
		JScrollPane scrCTHD = new JScrollPane();
		scrCTHD.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrCTHD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrCTHD.setBounds(520, 10, 650, 300);
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

		tableColumnModelCTHD.getColumn(0).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(1).setPreferredWidth(120);
		tableColumnModelCTHD.getColumn(2).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(3).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(4).setPreferredWidth(100);
		tableColumnModelCTHD.getColumn(5).setPreferredWidth(130);
		
		tblHeaderCTHD.setPreferredSize(new Dimension((int) tblHeaderCTHD.getPreferredSize().getWidth(), 36));
		tblHeaderCTHD.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tblHeaderCTHD.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tblCTHD.setRowHeight(36);
		scrCTHD.setViewportView(tblCTHD);
		
		txtTongTien = new TextField();
		txtTongTien.setLabelText("Tổng tiền:");
		txtTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//		txtTongTien.setBackground(new Color(203, 239, 255));
		txtTongTien.setBounds(750, 310, 371, 55);
		txtTongTien.setEditable(false);
		add(txtTongTien);
		
		btnXoa = new Button("Xóa");
		btnXoa.setIcon(Utils.getImageIcon("delet.png"));
		btnXoa.setRadius(8);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnXoa.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXoa.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXoa.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXoa.setBorderColor(new Color(203, 239, 255));
		btnXoa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXoa.setBounds(550, 310, 180, 40);
		add(btnXoa);
		
		JPanel pnlActions = new JPanel();
//		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(0, 365, 1450, 80);
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnXacNhan = new Button("Xác nhận");
		btnXacNhan.setIcon(Utils.getImageIcon("check-mark.png"));
		btnXacNhan.setRadius(8);
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnXacNhan.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnXacNhan.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnXacNhan.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnXacNhan.setBorderColor(new Color(203, 239, 255));
		btnXacNhan.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnXacNhan.setBounds(300, 0, 180, 40);
		pnlActions.add(btnXacNhan);
		
		btnHuy= new Button("Hủy");
		btnHuy.setIcon(Utils.getImageIcon("close_16x16.png"));
		btnHuy.setRadius(8);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnHuy.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnHuy.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnHuy.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnHuy.setBorderColor(new Color(203, 239, 255));
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(650, 0, 180, 40);
		pnlActions.add(btnHuy);
		
		btnHuy.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnXoa.addActionListener(this);
	}
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r, "My thread");
		t.setDaemon(true);
		return t;
	}
	@Override
	public void run() {
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			Result result = null;
			BufferedImage image = null;
			if(webcam.isOpen()) {
				if((image = webcam.getImage()) == null) {
					continue;
				}
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			
			try {
				result = new MultiFormatReader().decode(bitmap);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(result != null) {
//				txtResult.setText(result.getText());
				String[] col = result.getText().split("/");
				int soluongtonkho = 0;
				try {
					soluongtonkho = getSoLuongSP(col[0]);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double phantram = 0.0;
				String soluong = JOptionPane.showInputDialog(this, "Số lượng:",1);
				if(Integer.valueOf(soluong) > soluongtonkho) {
					JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ cho đơn hàng!");
					soluong = String.valueOf(soluongtonkho);
				}
				try {
					for (ChiTietKhuyenMai ctkm : ChiTietKhuyenMai_dao.getAllChiTietKhuyenMai()) {
						if(ctkm.getSanPham().getMaSanPham().equals(col[0])) {
							for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
								if(km.getMaKhuyenMai().equals(ctkm.getKhuyenMai().getMaKhuyenMai()))
									if((km.getNgayBatDau().isBefore(LocalDate.now()) || km.getNgayBatDau().isEqual(LocalDate.now()))  
											&& (km.getNgayKetThuc().isAfter(LocalDate.now()) || km.getNgayKetThuc().isEqual(LocalDate.now()))) {
										phantram = ctkm.getPhanTramKhuyenMai();
										break;
									}			
							}		
							break;
						}		
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double dongia = Double.valueOf(col[2]);
				if(phantram != 0)
					dongia -= dongia*phantram/100;
				Double thanhTien = dongia*Integer.valueOf(soluong);
				tongTien += thanhTien;
				Object[] data = {col[0],col[1],soluong, format.format(dongia),phantram,format.format(thanhTien)};
				tableModelCTHD.addRow(data);
				txtTongTien.setText(format.format(tongTien));
				result = null;
			}
		} while (true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnHuy)) {
			webcam.close();
			setVisible(false);	
		}
		else if(src.equals(btnXoa)) {
			int row = tblCTHD.getSelectedRow();
			if(row != -1) {
				tongTien -= chuyenDoiFormat(tblCTHD.getValueAt(row, 5).toString());
				txtTongTien.setText(format.format(tongTien));
				tableModelCTHD.removeRow(row);
			}
			else
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
		}
		else if(src.equals(btnXacNhan)) {
			int toTalRow = tableModelCTHD.getRowCount();
			for(int i = 0;i < toTalRow;i++) {
				Object[] data = {tblCTHD.getValueAt(i, 0),tblCTHD.getValueAt(i, 1), tblCTHD.getValueAt(i, 2),
						tblCTHD.getValueAt(i, 3), tblCTHD.getValueAt(i, 4), tblCTHD.getValueAt(i, 5)};
				dh.tableModel.addRow(data);
				dh.txtTongTien.setText(txtTongTien.getText());
			}
			webcam.close();
			setVisible(false);
		}
	}
	public int getSoLuongSP(String ma) throws RemoteException {
		try {
			for (SanPham sp : SanPham_dao.getAllSanPham()) {
				if(sp.getMaSanPham().equals(ma))
					return sp.getSoLuong();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
