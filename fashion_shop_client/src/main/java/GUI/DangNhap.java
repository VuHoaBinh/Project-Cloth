package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;



import components.button.Button;
import components.jDialog.JDialogCustom;
import components.notification.Notification;
import components.panelRound.PanelRound;
import components.passwordField.PasswordField;
import components.textField.TextField;
import dao.TaiKhoanDAO;
import entities.TaiKhoan;
import keeptoo.KGradientPanel;
import url.RmiConfig;
import utils.Utils;



public class DangNhap extends JFrame{
	private static final String URL = RmiConfig.RMI_URL;
	private DangNhap _this;
	private JPanel contentPane;
	private TaiKhoanDAO taiKhoan_DAO;
	private TextField txtTaiKhoan;
	private PasswordField txtMatKhau;
	private ArrayList<TaiKhoan> dsTK;
	/**
	 * Create the frame.
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public DangNhap() throws RemoteException, MalformedURLException, NotBoundException {
//			new ConnectDB().connect();
		taiKhoan_DAO = (TaiKhoanDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/taiKhoanDao");
		dsTK = new ArrayList<TaiKhoan>();
		for (TaiKhoan taiKhoan : taiKhoan_DAO.getAllTaiKhoan()) {
			dsTK.add(taiKhoan);
		}
		
		_this = this;
		setAutoRequestFocus(false);
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getScreenHeight());
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JDialogCustom jDialogCustom = new JDialogCustom(this);
		jDialogCustom.getBtnOK().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		KGradientPanel pnlContainer = new KGradientPanel();
		pnlContainer.setBounds(0, 0, Utils.getScreenWidth(), Utils.getScreenHeight());
		pnlContainer.setkEndColor(Utils.getRGBA(109, 61, 230, 0.74f));
		pnlContainer.setkGradientFocus(600);
		pnlContainer.setkStartColor(Utils.getRGBA(153, 0, 153, 0.8f));
		contentPane.add(pnlContainer);
		pnlContainer.setLayout(null);

		int widthPnlDangNhap = 800;
		int heightPnlDangNhap = 400;
		int leftPnlDangNhap = (int) Math.ceil((Utils.getScreenWidth() - widthPnlDangNhap) / 2);
		int topPnlDangNhap = (int) Math.ceil((Utils.getScreenHeight() - heightPnlDangNhap) / 2);

		JPanel pnlDangNhap = new JPanel();
		pnlDangNhap.setBackground(Utils.getRGBA(0, 0, 0, 0));
		pnlDangNhap.setBounds(leftPnlDangNhap, topPnlDangNhap, widthPnlDangNhap, heightPnlDangNhap);
		pnlContainer.add(pnlDangNhap);
		pnlDangNhap.setLayout(null);

		PanelRound pnlFormDangNhap = new PanelRound();
		pnlFormDangNhap.setRounded(16);
		pnlFormDangNhap.setBounds(400, 0, 400, 400);
		pnlFormDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.add(pnlFormDangNhap);
		pnlFormDangNhap.setLayout(null);

		JLabel lblDangNhap = new JLabel("Đăng nhập");
		lblDangNhap.setForeground(Utils.primaryColor);
		lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 34));
		lblDangNhap.setBounds(0, 40, 400, 51);
		pnlFormDangNhap.add(lblDangNhap);

		JPanel pnlForm = new JPanel();
		pnlForm.setBackground(Color.WHITE);
		pnlForm.setBounds(40, 139, 320, 221);
		pnlFormDangNhap.add(pnlForm);
		pnlForm.setLayout(null);

		txtTaiKhoan = new TextField();

		txtTaiKhoan.setLabelText("Tài khoản");
		txtTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTaiKhoan.setBounds(0, 0, 320, 55);
		pnlForm.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtTaiKhoan.setError(false);
			}
		});

		txtMatKhau = new PasswordField();
		txtMatKhau.setLabelText("Mật khẩu");
		txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(0, 75, 320, 55);
		txtMatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtMatKhau.setError(false);
			}
		});
		pnlForm.add(txtMatKhau);

		Button btnDangNhap = new Button("Đăng nhập");
		btnDangNhap.setIcon(Utils.getImageIcon("sign-in.png"));
		btnDangNhap.setFocusable(false);
		btnDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnDangNhap.setColor(Utils.primaryColor);
		btnDangNhap.setForeground(Color.white);
		btnDangNhap.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDangNhap.setBorderColor(Color.WHITE);
		btnDangNhap.setColorOver(Utils.getOpacity(Utils.primaryColor, 0.9f));
		btnDangNhap.setColorClick(Utils.getOpacity(Utils.primaryColor, 0.8f));
		btnDangNhap.setRadius(8);
		btnDangNhap.setBounds(170, 148, 150, 40);
		btnDangNhap.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				if (!validator())
//					return;

				String taiKhoan = txtTaiKhoan.getText();
				
				String matKhau = "";
				String quyenhan = "";
				String maTK = "";
				boolean kq_acc = false;
				for(TaiKhoan tk: dsTK) {
					if(tk.getTenTaiKhoan().equals(taiKhoan)) {
						matKhau = tk.getMatKhau();
						quyenhan = tk.getQuyenHan();
						maTK = tk.getMaTaiKhoan();
						kq_acc = true;
					}
				}
				components.notification.Notification.Type type = components.notification.Notification.Type.ERROR;
				if(txtTaiKhoan.getText().equals("")) {
					new Notification(_this, type, "Mã tài khoản không được rỗng").showNotification();
					txtTaiKhoan.setError(true);
					txtTaiKhoan.requestFocus();
				}
				else if(kq_acc==true) {
					if(txtMatKhau.getText().equals(matKhau)) {
						setVisible(false);
						try {
							new ManHinhChinh(maTK).setVisible(true);
						} catch (RemoteException | MalformedURLException | NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						};
					}
					else if(txtMatKhau.getText().equals("")) {
						new Notification(_this, type, "Vui lòng nhập mật khẩu").showNotification();
						txtMatKhau.requestFocus();
					}
					else {
						new Notification(_this, type, "Sai mật khẩu").showNotification();
						txtMatKhau.requestFocus();
						txtMatKhau.selectAll();
					}
				}
				else if(kq_acc == false) {
					new Notification(_this, type, "Tài khoản này không tồn tài").showNotification();
					txtTaiKhoan.requestFocus();
					txtTaiKhoan.selectAll();
				}	
			}
		});
		pnlForm.add(btnDangNhap);

		Button btnThoat = new Button("Thoát");
		btnThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jDialogCustom.showMessage("Thoát ứng dụng", "Bạn có chắc chắn muốn thoát ứng dụng không?");
			}
		});
		btnThoat.setIcon(Utils.getImageIcon("exit (1).png"));
		btnThoat.setFocusable(false);
		btnThoat.setBorderWidth(1);
		btnThoat.setBorderBtnColor(Utils.primaryColor);
		btnThoat.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnThoat.setColor(Color.WHITE);
		btnThoat.setForeground(Utils.primaryColor);
		btnThoat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThoat.setBorderColor(Color.white);
		btnThoat.setColorOver(Utils.getOpacity(Color.BLACK, 0.025f));
		btnThoat.setColorClick(Utils.getOpacity(Color.BLACK, 0.05f));
		btnThoat.setRadius(8);
		btnThoat.setBounds(0, 148, 150, 40);
		pnlForm.add(btnThoat);

		JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu?");
		lblQuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblQuenMatKhau.setForeground(Utils.primaryColor);
		lblQuenMatKhau.setBounds(210, 200, 110, 21);
		pnlForm.add(lblQuenMatKhau);
		lblQuenMatKhau.addMouseListener(new MouseListener() {
			
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
				lblQuenMatKhau.setForeground(Utils.primaryColor);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblQuenMatKhau.setForeground(Utils.getOpacity(Utils.primaryColor, 0.5f));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					new Form_QuenMatKhau(txtTaiKhoan.getText()).setVisible(true);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblBackground = new JLabel();
		lblBackground.setIcon(Utils.getImageIcon("analytics.png"));

		lblBackground.setBounds(72, 144, 256, 256);
		pnlDangNhap.add(lblBackground);

		JLabel lblTieuDe = new JLabel("CLOTHING SHOP");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.WHITE);
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblTieuDe.setBounds(0, 10, 400, 83);
		pnlDangNhap.add(lblTieuDe);

//		Default Value
		txtTaiKhoan.setText("nv0001");
		txtMatKhau.setText("12345678");

		setAlwaysOnTop(false);
	}
	
//	private boolean validator() {
//		String maTaiKhoan = txtTaiKhoan.getText();
//		components.notification.Notification.Type type = components.notification.Notification.Type.ERROR;
//
//		if (maTaiKhoan.equals("")) {
//			new Notification(_this, type, "Mã tài khoản không được rỗng").showNotification();
//			txtTaiKhoan.setError(true);
//			txtTaiKhoan.requestFocus();
//			return false;
//		}
//
//		if (!Pattern.matches("NV[0-9]{4}", maTaiKhoan)) {
//			new Notification(_this, type, "Mã tài khoản phải có dạng NVxxXx, x là các chữ số").showNotification();
//			txtTaiKhoan.setError(true);
//			txtTaiKhoan.requestFocus();
//			return false;
//		}
//
//		@SuppressWarnings("deprecation")
//		String matKhau = txtMatKhau.getText();
//
//		if (matKhau.equals("")) {
//			new Notification(_this, type, "Mật khẩu không được rỗng").showNotification();
//			txtMatKhau.setError(true);
//			txtMatKhau.requestFocus();
//			return false;
//		}
//		if (matKhau.length() < 4) {
//			new Notification(_this, type, "Mật khẩu phải lớn hơn 4 ký tự").showNotification();
//			txtMatKhau.setError(true);
//			txtMatKhau.requestFocus();
//			return false;
//		}

//		return true;
//	}

}
