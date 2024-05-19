package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.passwordField.PasswordField;
import components.textField.TextField;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entities.NhanVien;
import entities.TaiKhoan;
import utils.Utils;

public class Form_QuenMatKhau extends JFrame implements ActionListener{
	private final int widthPnlContainer = 500;
	private TextField txtTaiKhoan;
	private Button btnLayMK;
	private Button btnHuy;
	private QuanLySanPham qlSP;
	private String taiKhoan;
	private TextField txtTenNV;
	private PasswordField txtMK;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	public Form_QuenMatKhau(String taiKhoan) throws MalformedURLException, RemoteException, NotBoundException {
		setAlwaysOnTop(true);
		setSize(500, 400);
		setTitle("Quên mật khẩu");
		setLocationRelativeTo(null);
		setLayout(null);
		NhanVien_dao = (NhanVienDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/nhanVienDao");
		TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/taiKhoanDao");
		this.taiKhoan = taiKhoan;
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(203, 239, 255));
		pnlContainer.setBounds(0, 0, widthPnlContainer, 320);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(203, 239, 255));
		pnlHeader.setBounds(16, 18, 1000, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblTitle = new JLabel("Quên mật khẩu");
		lblTitle.setBounds(150, 10, 500, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(new Color(203, 239, 255));
		pnlRow1.setBounds(100, 100, 550, 55);
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		txtTaiKhoan = new TextField();
		txtTaiKhoan.setLabelText("Tài khoản:");
		txtTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTaiKhoan.setBackground(new Color(203, 239, 255));
		txtTaiKhoan.setBounds(0, 0, 250, 55);
		pnlRow1.add(txtTaiKhoan);
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setText(taiKhoan);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(new Color(203, 239, 255));
		pnlRow2.setBounds(100, 165, 550, 55);
		add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtTenNV = new TextField();
		txtTenNV.setLabelText("Tên nhân viên:");
		txtTenNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenNV.setBackground(new Color(203, 239, 255));
		txtTenNV.setBounds(0, 0, 250, 55);
		pnlRow2.add(txtTenNV);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(new Color(203, 239, 255));
		pnlRow3.setBounds(100, 230, 550, 55);
		add(pnlRow3);
		pnlRow3.setLayout(null);
		
		txtMK = new PasswordField();
		txtMK.setEditable(false);
		txtMK.setLabelText("Mật khẩu:");
		txtMK.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMK.setBackground(new Color(203, 239, 255));
		txtMK.setBounds(0, 0, 250, 55);
		pnlRow3.add(txtMK);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(new Color(203, 239, 255));
		pnlActions.setBounds(0, 320, 500, 60);
//		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnLayMK = new Button("Lấy mật khẩu");
		btnLayMK.setFocusable(false);
		btnLayMK.setIcon(Utils.getImageIcon("plus.png"));
		btnLayMK.setRadius(4);
		btnLayMK.setForeground(Color.WHITE);
		btnLayMK.setColor(new Color(134, 229, 138));
		btnLayMK.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnLayMK.setBounds(904, -2, 150, 40);
		btnLayMK.setBorderColor(Utils.secondaryColor);
		btnLayMK.setColorOver(new Color(134, 229, 138));
		btnLayMK.setColorClick(new Color(59, 238, 66));
		btnLayMK.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLayMK.setBounds(70, 0, 200, 40);

		pnlActions.add(btnLayMK);
		
		
		btnHuy = new Button("Hủy");
		btnHuy.setIcon(Utils.getImageIcon("close_16X16.png"));
		btnHuy.setRadius(4);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnHuy.setColorOver(Utils.primaryColor);
		btnHuy.setColorClick(new Color(161, 184, 186));
		btnHuy.setColor(Utils.primaryColor);
		btnHuy.setBorderColor(Utils.secondaryColor);
		btnHuy.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHuy.setBounds(280, 0, 150, 40);
		pnlActions.add(btnHuy);
		
		btnLayMK.addActionListener(this);
		btnHuy.addActionListener(this);
	}
//	public static void main(String[] args) {
//		new Form_QuenMatKhau().setVisible(true);
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnHuy)) {
			setVisible(false);
		}
		else if(src.equals(btnLayMK)) {
			String mk = "";
			String emailTo = "";
			boolean tontai = false;
			try {
				for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
					if(nv.getTenNhanVien().equals(txtTenNV.getText())) {
						tontai = true;
						break;
					}		
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(tontai == true) {
				boolean tontaiTK = false;
				try {
					for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
						if(tk.getTenTaiKhoan().equals(txtTaiKhoan.getText())) {
							tontaiTK = true;
							mk = tk.getMatKhau();
							emailTo = tk.getEmail();
							break;
						}				
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(tontaiTK == true) {
//					int tb = JOptionPane.showInternalConfirmDialog(null, "Hiển thị mật khẩu qua gmail không?", "Chú ý", JOptionPane.YES_NO_OPTION);
//					if(tb == JOptionPane.YES_OPTION) {
//						try {
//							Properties p = new Properties();
//							p.put("mail.smtp.auth",	"true");
//							p.put("mail.smtp.starttls.enable", "true");
//							p.put("mail.smtp.host", "smtp.gmail.com");
//							p.put("mail.smtp.port", "587");
//							p.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Thay thế bằng giao thức bạn cần hỗ trợ
//
//							
//							Session s = Session.getInstance(p, 
//									new javax.mail.Authenticator() {
//								protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//									return new javax.mail.PasswordAuthentication("vanchihieu3@gmail.com", "grgb zmlk ieak stuu");
//								}
//							});
//							
////							String tos = arrds.get(i).toString();
//							
//							String mess = mk;
//							
//							Message msg = new MimeMessage(s);
//							msg.setFrom(new InternetAddress("vanchihieu3@gmail.com"));
//							msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
//							
//							msg.setSubject("Lấy lại mật khẩu");
//							msg.setText(mess);
//							Transport.send(msg);
//							JOptionPane.showMessageDialog(this, "Gửi email thành công");
//						} catch (MessagingException r) {
//							throw new RuntimeException(r);
//						}
//					}
						
//					else
						txtMK.setText(mk);
				}
					
				else
					JOptionPane.showMessageDialog(this, "Tài khoản này không tồn tại");
			}
			else
				JOptionPane.showMessageDialog(this, "Nhân viên này không tồn tại");
			
		}
	}
}
