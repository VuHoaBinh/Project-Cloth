package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.drawer.Drawer;
import components.drawer.DrawerController;
import components.drawer.DrawerItem;
import components.jDialog.JDialogCustom;
import components.menu.EventMenuSelected;
import components.menu.Menu;
import components.menu.MenuItem;
import components.menu.ModelMenuItem;
import components.notification.Notification;
import dao.KhuyenMaiDAO;
import dao.TaiKhoanDAO;

import javax.swing.JOptionPane;

import entities.KhuyenMai;
import entities.PanelUI;
import entities.TaiKhoan;
import utils.StackPanel;
import utils.Utils;

public class ManHinhChinh extends JFrame {
	private static final long serialVersionUID = 1L;

	private ManHinhChinh _this;
	private Button btnBack;
	private DrawerController drawer;
	private Menu footer;
	private JLabel lblTitle;
	private Menu menu;
	private JPanel pnlBody;
	private JPanel pnlContent;
	private JPanel pnlHeader;

	private String maTK;

	private Menu menuNV;

	private JPanel menus;

	private ModelMenuItem mniNV;

	private ModelMenuItem mnuTimKiemNV;

	private ModelMenuItem mniSP;

	private ModelMenuItem mniHD;

	private ModelMenuItem mniKH;

	private ModelMenuItem mniKM;

	private ModelMenuItem mniTTCN;

	private ModelMenuItem mniTrangChu;

	private JLabel lblGio;

	private JLabel lblNgay;

	private TaiKhoanDAO TaiKhoan_dao;
	
	private KhuyenMaiDAO KhuyenMai_dao;

	private ModelMenuItem mniTK;

	/**
	 * Create the frame.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public ManHinhChinh(String maTK) throws RemoteException, MalformedURLException, NotBoundException {
		this.maTK = maTK;
		_this = this;
		JDialogCustom jDialogCustom = new JDialogCustom(_this);

		jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
//		TaiKhoan_dao = new TaiKhoan_Dao();
		TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/taiKhoanDao");
		KhuyenMai_dao = (KhuyenMaiDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/khuyenMaiDao");
		pnlContent = new JPanel();
		pnlContent.setForeground(Color.GRAY);
		pnlContent.setBackground(Utils.secondaryColor);
		setContentPane(pnlContent);
		pnlContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlContent.setLayout(null);

		JPanel pnlHeaderWrapper = new JPanel();
		pnlHeaderWrapper.setBounds(0, 0, Utils.getScreenWidth(), Utils.getHeaderHeight());
		pnlHeaderWrapper.setBackground(Utils.primaryColor);
		pnlContent.add(pnlHeaderWrapper);
		pnlHeaderWrapper.setLayout(null);

		pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.primaryColor);
		pnlHeader.setBounds(Utils.getLeft(1054), 0, 1054, Utils.getHeaderHeight() + 20);
		pnlHeaderWrapper.add(pnlHeader);
		pnlHeader.setLayout(null);

		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.primaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);

		JLabel lnlIcon = new JLabel("");
		lnlIcon.setIcon(Utils.getImageIcon("clock.png"));
		lnlIcon.setBounds(0, 0, 64, 85);
		pnlThoiGian.add(lnlIcon);

		lblGio = new JLabel("20:17");
		lblGio.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		lblGio.setHorizontalAlignment(SwingConstants.CENTER);
		lblGio.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblGio.setBounds(64, 10, 136, 21);
		pnlThoiGian.add(lblGio);

		lblNgay = new JLabel("19-10-2023");
		lblNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgay.setForeground(new Color(0, 0, 0, 140));
		lblNgay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNgay.setBounds(64, 35, 136, 21);
		pnlThoiGian.add(lblNgay);

		Button btnMenu = new Button();
		btnMenu.setIcon(Utils.getImageIcon("bar.png"));
		btnMenu.setFocusable(false);
		btnMenu.setBounds(-2, 14, 42, 42);
		btnMenu.setForeground(Utils.primaryColor);
		btnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBorderColor(Utils.primaryColor);
		btnMenu.setRadius(8);
		btnMenu.setFocusable(false);
		pnlHeader.add(btnMenu);

		lblTitle = new JLabel("TRANG CHỦ");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(53, 17, 948, 32);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		pnlHeader.add(lblTitle);

		btnBack = new Button();
		btnBack.setFocusable(false);
		btnBack.setIcon(Utils.getImageIcon("back 1.png"));
		btnBack.setColor(Utils.primaryColor);
		btnBack.setColorOver(Utils.primaryColor);
		btnBack.setColorClick(Utils.primaryColor);
		btnBack.setBorderColor(Utils.primaryColor);
		btnBack.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBack.setBounds(992, 1, 62, 62);
		pnlHeader.add(btnBack);
//		End Default Layout

		pnlBody = new JPanel();
		pnlBody.setLayout(null);
		pnlBody.setBounds(0, Utils.getHeaderHeight(), 7100, 1500);
		pnlContent.add(pnlBody);
		String quyenhan = "";
		for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
			if (tk.getMaTaiKhoan().equals(maTK))
				quyenhan = tk.getQuyenHan();
		}
//		Code menu
		menu = new Menu();

		footer = new Menu();
		drawer = Drawer.newDrawer(this).addChild(menu).addFooter(footer).build();
		menu.setDrawer(drawer);
//		
		String[] dsmniNVQL = { Utils.quanLyNhanVienMenuItem, Utils.timKiemNhanVienMenuItem, Utils.quanLyTaiKhoanMenuItem,
				Utils.lapPhieuNhapMenuItem, Utils.xemDanhSachPhieuNhapMenuItem, Utils.datHangMenuItem,
				Utils.XemDanhSachDatHangMenuItem, Utils.guiEmailMenuItem };
		String[] dsmniNV = {Utils.datHangMenuItem, Utils.XemDanhSachDatHangMenuItem, Utils.guiEmailMenuItem };
		String[] dsmniKH = { Utils.quanLyKhachHangMenuItem, Utils.timKiemKhachHangMenuItem };
		String[] dsmniSPQL = { Utils.quanLySanPhamMenuItem, Utils.timKiemSanPhamMenuItem };
		String[] dsmniSP = {Utils.timKiemSanPhamMenuItem };
		String[] dsmniTK = { Utils.thongKeSanPhamMenuItem, Utils.thongKeDoanhThuMenuItem };
		String[] dsmniKM = { Utils.quanLyKhuyenMaiMenuItem, Utils.xemDanhSachKhuyenMaiMenuItem };
		String[] dsmniHD = { Utils.lapHoaDonMenuItem, Utils.xemDanhSachHoaDonMenuItem };
		String[] dsmniTTCN = { Utils.xemThongTinCaNhanMenuItem };
//		mnuTimKiemNV = new ModelMenuItem(Utils.getImageIcon("user_searching.png"), Utils.timKiemNhanVienMenuItem);
		mniTrangChu = new ModelMenuItem(Utils.getImageIcon("homeIcon.png"), "Trang chủ");
		if (quyenhan.equals("nhan vien")) {
			mniNV = new ModelMenuItem(Utils.getImageIcon("manager.png"), "Nhân viên", dsmniNV);
			mniSP = new ModelMenuItem(Utils.getImageIcon("Shop-Clothes.png"), "Sản phẩm", dsmniSP);
		}
			
		else {
			mniNV = new ModelMenuItem(Utils.getImageIcon("manager.png"), "Nhân viên", dsmniNVQL);
			mniSP = new ModelMenuItem(Utils.getImageIcon("Shop-Clothes.png"), "Sản phẩm", dsmniSPQL);
			mniKM = new ModelMenuItem(Utils.getImageIcon("megaphone.png"), "Khuyến mãi", dsmniKM);
			mniTK = new ModelMenuItem(Utils.getImageIcon("analytics32.png"), "Thống kê", dsmniTK);
		}		
		mniHD = new ModelMenuItem(Utils.getImageIcon("bill.png"), "Hóa đơn", dsmniHD);
		mniKH = new ModelMenuItem(Utils.getImageIcon("rating.png"), "Khách hàng", dsmniKH);
		mniTTCN = new ModelMenuItem(Utils.getImageIcon("user.png"), "Thông tin cá nhân", dsmniTTCN);
		
		if(quyenhan.equals("nhan vien")){
			menu.addMenuItem(mniTrangChu);
			menu.addMenuItem(mniNV);
			menu.addMenuItem(mniSP);
			menu.addMenuItem(mniKH);
			menu.addMenuItem(mniHD);
			menu.addMenuItem(mniTTCN);
		}
		else {
			menu.addMenuItem(mniTrangChu);
			menu.addMenuItem(mniNV);
			menu.addMenuItem(mniSP);
			menu.addMenuItem(mniKH);
			menu.addMenuItem(mniHD);
			menu.addMenuItem(mniKM);
			menu.addMenuItem(mniTK);
			menu.addMenuItem(mniTTCN);
		}
//		menu.addMenuItem(mniTrangChu);
//		menu.addMenuItem(mniNV);
//		menu.addMenuItem(mniSP);
//		menu.addMenuItem(mniKH);
//		menu.addMenuItem(mniHD);
//		menu.addMenuItem(mniKM);
//		menu.addMenuItem(mniTK);
//		menu.addMenuItem(mniTTCN);
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("homeIcon.png"), Utils.trangChuMenuItem));
//		menu.addMenuItem(mnuQuanLyNV);
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("Shop-Clothes.png"), Utils.quanLySanPhamMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("manager.png"), Utils.quanLyNhanVienMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user_searching.png"), Utils.timKiemNhanVienMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("add-user.png"), Utils.taoTaiKhoanMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("megaphone.png"), Utils.quanLyKhuyenMaiMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user.png"), Utils.xemThongTinCaNhanMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("bill.png"), Utils.lapPhieuNhapMenuItem));
//		menu.addMenuItem(new ModelMenuItem(Utils.getImageIcon("user.png"), Utils.troGiupItem));
		menu.setPreferredSize(new Dimension(getPreferredSize().width, 503));
//		}
		String tenNV = "";
		for(TaiKhoan tk:TaiKhoan_dao.getAllTaiKhoan()) {
			if(tk.getMaTaiKhoan().equals(maTK))
				tenNV = tk.getNhanVien().getTenNhanVien();
		}
		footer.setDrawer(drawer);
		if(quyenhan.equals("nhan vien")) {
			footer.addTitle("Nhân viên");
			footer.addTitle(tenNV);
		}
		else {
			footer.addTitle("Quản lý");
			footer.addTitle(tenNV);
		}
		
		footer.addMenuItem(new ModelMenuItem(Utils.getImageIcon("logout.png"), Utils.dangXuatMenuItem));
		footer.addMenuItem(new ModelMenuItem(Utils.getImageIcon("power.png"), Utils.thoatMenuItem));
		footer.setPreferredSize(new Dimension(getPreferredSize().width, 150));
		menus = new JPanel();
		int soluongKM = 0;
		for (KhuyenMai km : KhuyenMai_dao.getAllKhuyenMai()) {
			if (km.getNgayKetThuc().isBefore(LocalDate.now()))
				soluongKM++;
		}
		components.notification.Notification.Type type = components.notification.Notification.Type.ERROR;
		if (soluongKM > 0)
			new Notification(_this, type, "Có "+ soluongKM +" chương trình khuyến mãi đã hết hạn. Vui lòng xóa!").showNotification();		
//		Show/Hide menu
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawer.show();
			}
		});

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelUI pnl = StackPanel.pop();
				boolean isEmpty = StackPanel.empty();

				if (pnl.getTitle().equals(Utils.trangChuMenuItem))
					while (!StackPanel.empty())
						StackPanel.pop();

				StackPanel.push(pnl);

				if (isEmpty)
					jDialogCustom.showMessage("Đóng ứng dụng", "Bạn có muốn đóng ứng dụng không?");
				else
					backPanel();
			}
		});

		xuLySuKienMenu();
		addPnlBody(new TrangChu(), "Trang chủ", 0, 0);
	}

	/**
	 * Thay đổi phần container UI
	 *
	 * @param pnl          panel cần thay đổi
	 * @param title        title của trang
	 * @param index        index menu
	 * @param indexSubmenu index submenu
	 */
	public void addPnlBody(JPanel pnl, String title, int index, int indexSubmenu) {
		PanelUI panelUI = new PanelUI(pnl, title, index, indexSubmenu);
		menu.setSelectedMenu(index, indexSubmenu);
		addPnlBody(panelUI);
		StackPanel.push(panelUI);
	}

	/**
	 * Thay đổi phần container UI
	 *
	 * @param panelUI panel UI
	 */
	public void addPnlBody(PanelUI panelUI) {
		pnlBody.removeAll();
		pnlBody.add(panelUI.getjPanel());
		pnlBody.repaint();
		setTitle(panelUI.getTitle());
	}

	public void backPanel() {
		StackPanel.pop();
		PanelUI panelUI = StackPanel.peek();
		if (panelUI.getTitle().equals("Trang chủ")) {
			while (!StackPanel.empty())
				StackPanel.pop();
			StackPanel.push(panelUI);
		}
		addPnlBody(panelUI);
		menu.setSelectedMenu(panelUI.getIndex(), panelUI.getIndexSubmenu());
		repaint();
	}

	public Menu getMenu() {
		return menu;
	}

	@Override
	public void repaint() {
		pnlBody.repaint();
		pnlBody.revalidate();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		lblTitle.setText(title.toUpperCase());
	}

	public void setWidthHeader(int width) {
		pnlHeader.setBounds(Utils.getLeft(width), 0, width, Utils.getHeaderHeight());
		btnBack.setBounds(width - 62, 1, 62, 62);
		repaint();
	}

	/**
	 * Xử lý sự kiện khi nhấn vào menu item
	 */
	private void xuLySuKienMenu() {
		menu.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int index, int indexSubMenu) {
				JPanel pnl = null;
				String title;

				List<String> list = menu.getMenu().get(index);
				String titleMenu = list.get(indexSubMenu);

				switch (titleMenu) {
				case Utils.quanLySanPhamMenuItem:
					title = "Quản lý sản phẩm";
					try {
						pnl = new QuanLySanPham(_this);
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.timKiemSanPhamMenuItem:
					title = "Tìm kiếm sản phẩm";
					try {
						pnl = new TimKiemSanPham(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.quanLyNhanVienMenuItem:
					title = "Quản lý nhân viên";
					try {
						pnl = new QuanLyNhanVien(_this);
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.timKiemNhanVienMenuItem:
					title = "Tìm kiếm nhân viên";
					try {
						pnl = new TimKiemNhanVien(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.quanLyTaiKhoanMenuItem:
					title = "Quản lý tài khoản";
					try {
						pnl = new QuanLyTaiKhoan(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.quanLyKhuyenMaiMenuItem:
					title = "Quản lý khuyến mãi";
					try {
						pnl = new QuanLyKhuyenMai(_this);
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.xemDanhSachKhuyenMaiMenuItem:
					title = "Xem danh sách khuyến mãi";
					try {
						pnl = new XemDanhSachKhuyenMai(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.xemThongTinCaNhanMenuItem:
					title = "Xem thông tin cá nhân";
					try {
						pnl = new XemThongTinCaNhan(_this, maTK);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.lapPhieuNhapMenuItem:
					title = "Lập phiếu nhập";
					try {
						pnl = new LapPhieuNhap(_this, maTK);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.xemDanhSachPhieuNhapMenuItem:
					title = "Xem danh sách phiếu nhập";
					try {
						pnl = new XemDanhSachPhieuNhap(_this);
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
//				case Utils.thongKeKhachHangMenuItem:
//					title = "Thống kê tổng hợp khách hàng";
//					try {
//						pnl = new ThongKeTongHopKhachHang(_this, maTK);
//					} catch (MalformedURLException | RemoteException | NotBoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					break;
				case Utils.thongKeSanPhamMenuItem:
					title = "Thống kê sản phẩm đã bán";
					try {
						pnl = new ThongKeSanPhamDaBan(_this, maTK);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.thongKeDoanhThuMenuItem:
					title = "Thống kê doanh thu";
					try {
						pnl = new ThongKeDoanhThu(_this, maTK);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.datHangMenuItem:
					title = "Đặt hàng";
					try {
						pnl = new DatHang(_this, maTK);
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.XemDanhSachDatHangMenuItem:
					title = "Xem danh sách đặt hàng";
					try {
						pnl = new XemDanhSachDatHang(_this, maTK);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.lapHoaDonMenuItem:
					title = "Lập hóa đơn";
					try {
						pnl = new LapHoaDon(_this, maTK);
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.xemDanhSachHoaDonMenuItem:
					title = "Danh sách hóa đơn";
					try {
						pnl = new XemDanhSachHoaDon(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.quanLyKhachHangMenuItem:
					title = "Quản lý khách hàng";
					try {
						pnl = new QuanLyKhachHang(_this, maTK);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.timKiemKhachHangMenuItem:
					title = "Tìm kiếm khách hàng";
					try {
						pnl = new TimKiemKhachHang(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Utils.guiEmailMenuItem:
					title = "Gửi Email";
					try {
						pnl = new Form_Mail(_this);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					title = "Trang chủ";
					pnl = new TrangChu();
				}
//				JOptionPane.showMessageDialog(this, index+"và"+indexSubMenu);
				addPnlBody(pnl, title, index, indexSubMenu);
			}
		});

		footer.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int index, int indexSubMenu) {
				footer.clearSelected();
				JDialogCustom jDialogCustom = new JDialogCustom(_this);
				if (index == 1 && indexSubMenu == 0) {
					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							System.exit(0);
						}
					});

					jDialogCustom.showMessage("Thoát ứng dụng", "Bạn có chắc chắn muốn thoát ứng dụng không?");
				} else {
					jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
//							utils.NhanVien.setNhanVien(null);
							try {
								new DangNhap().setVisible(true);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (NotBoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							setVisible(false);
						}
					});

					jDialogCustom.showMessage("Đăng xuất", "Bạn có chắc chắn muốn đăng xuất không?");
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			private Thread clock;

			@Override
			public void windowActivated(WindowEvent e) {
				clock = new Thread() {

					@Override
					public void run() {
						for (;;) {
							try {
								LocalDateTime currTime = LocalDateTime.now();
								int day = currTime.getDayOfMonth();
								int month = currTime.getMonthValue();
								int year = currTime.getYear();
								int hour = currTime.getHour();
								int minute = currTime.getMinute();
								lblGio.setText(Utils.convertLocalTimeToString(LocalTime.of(hour, minute)));
								LocalDate date = LocalDate.now();
								lblNgay.setText(String.format("%s-%s-%d", day < 10 ? "0" + day : day,
										month < 10 ? "0" + month : month, year));
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};

				clock.start();
			}

//			@SuppressWarnings("deprecation")
//			@Override
//			public void windowClosed(WindowEvent e) {
//				clock.stop();
//			}
		});
	}

}
