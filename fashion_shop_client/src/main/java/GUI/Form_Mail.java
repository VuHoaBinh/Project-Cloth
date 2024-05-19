package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import components.button.Button;
import components.passwordField.PasswordField;
import components.textField.TextField;
import dao.TaiKhoanDAO;
import entities.TaiKhoan;
import utils.Utils;

public class Form_Mail extends JPanel implements ActionListener{
	private final int widthPnlContainer = 1000;

	private ManHinhChinh main;

	private TextField txtUsername;

	private PasswordField txtPassword;

	public TextField txtTo;
	private TaiKhoanDAO TaiKhoan_dao;

	private TextField txtSubject;

	private TextField txtMessage;

	private Button btnDinhKem;

	private TextField txtDinhKem;

	private Button btnSend;
	String duongDan = "";

	private String maTK;

	private TextField txtMaTK;

	private Button btnDanhSach;


	public Form_Mail(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		this.maTK = maTK;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		TaiKhoan_dao = (TaiKhoanDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/taiKhoanDao");

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
		pnlHeader.setBounds(16, 18, 1054, 80);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Gửi email khách hàng");
		lblTitle.setBounds(380, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setBackground(Utils.secondaryColor);
		pnlThoiGian.setBounds(770, -4, 295, 85);
		pnlHeader.add(pnlThoiGian);
		pnlThoiGian.setLayout(null);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding + 30;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtUsername = new TextField();
		txtUsername.setLabelText("Người gửi:");
		txtUsername.setText("vanchihieu3@gmail.com");
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtUsername.setBackground(new Color(203, 239, 255));
		txtUsername.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtUsername);

		txtPassword = new PasswordField();
		txtPassword.setLabelText("Mật khẩu:");
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPassword.setBackground(new Color(203, 239, 255));
		txtPassword.setBounds(421, 0, 371, 55);
		txtPassword.setText("grgb zmlk ieak stuu");
		pnlRow1.add(txtPassword);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 55);
		top += padding + 30;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		txtTo = new TextField();
		txtTo.setLabelText("Người nhận:");
		txtTo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTo.setBackground(new Color(203, 239, 255));
		txtTo.setBounds(0, 0, 330, 55);
		pnlRow2.add(txtTo);
		
		btnDanhSach = new Button();
		btnDanhSach.setRadius(8);
		btnDanhSach.setIcon(Utils.getImageIcon("user 1.png"));
		btnDanhSach.setForeground(Color.WHITE);
		btnDanhSach.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnDanhSach.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDanhSach.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDanhSach.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDanhSach.setBorderColor(new Color(203, 239, 255));
		btnDanhSach.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDanhSach.setBounds(335, 15, 40, 40);
		pnlRow2.add(btnDanhSach);
		btnDanhSach.addActionListener(this);
		
		txtSubject = new TextField();
		txtSubject.setLabelText("TIêu đề:");
		txtSubject.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSubject.setBackground(new Color(203, 239, 255));
		txtSubject.setBounds(421, 0, 371, 55);
		pnlRow2.add(txtSubject);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 55);
		top += padding + 30;
		add(pnlRow3);
		pnlRow3.setLayout(null);

		txtMessage = new TextField();
		txtMessage.setLabelText("Nội dung");
		txtMessage.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMessage.setBackground(new Color(203, 239, 255));
		txtMessage.setBounds(0, 0, 371, 55);
		pnlRow3.add(txtMessage);

		btnDinhKem = new Button("File đính kèm");
		btnDinhKem.setRadius(8);
		btnDinhKem.setForeground(Color.WHITE);
		btnDinhKem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnDinhKem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDinhKem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDinhKem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDinhKem.setBorderColor(new Color(203, 239, 255));
		btnDinhKem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDinhKem.setBounds(420, 20, 120, 40);
		pnlRow3.add(btnDinhKem);
		
		txtDinhKem = new TextField();
		txtDinhKem.setLabelText("");
		txtDinhKem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDinhKem.setBackground(new Color(203, 239, 255));
		txtDinhKem.setBounds(550, 0, 350, 55);
		pnlRow3.add(txtDinhKem);
		
		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 792, 55);
		top += padding + 30;
		add(pnlRow4);
		pnlRow4.setLayout(null);
		


		btnSend = new Button("Gửi");
		btnSend.setIcon(Utils.getImageIcon("add-user (2) 1.png"));
		btnSend.setRadius(8);
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnSend.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnSend.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnSend.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnSend.setBorderColor(new Color(203, 239, 255));
		btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSend.setBounds(380, 10, 150, 40);
		pnlRow4.add(btnSend);
		
//		ArrayList<String> arrds = new ArrayList<String>();
//		arrds.add("vanchihieu3@gmail.com");
//		arrds.add("vanchihieu5@gmail.com");
		
		
		btnDinhKem.addActionListener(this);
		btnSend.addActionListener(this);
		for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
			if(tk.getMaTaiKhoan().equals(maTK)) {
				txtMaTK.setText(maTK);
//				matKhau = tk.getMatKhau();
//				txtTenTK.setText(tk.getTenTaiKhoan());
				break;
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(btnSend)) {
			btnSend.addActionListener(this);
			btnDinhKem.addActionListener(this);
			try {
				Properties p = new Properties();
				p.put("mail.smtp.auth",	"true");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");
				p.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Thay thế bằng giao thức bạn cần hỗ trợ

				String accountName = txtUsername.getText();
				String accountPassword = txtPassword.getText();
				
				Session s = Session.getInstance(p, 
						new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(accountName, accountPassword);
					}
				});
				
				String from = txtUsername.getText();
				String to = txtTo.getText();
//				String tos = arrds.get(i).toString();
				
				String subject = txtSubject.getText();
				String mess = txtMessage.getText();
				
				Message msg = new MimeMessage(s);
				msg.setFrom(new InternetAddress(from));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				
				
				// bo dinh kem
				MimeBodyPart contentPart = new MimeBodyPart();
				contentPart.setContent(mess, "text/html; charset=utf-8");
				
				msg.setSubject(subject);
				msg.setText(mess);
				
				MimeBodyPart filepart = new MimeBodyPart();
				File file = new File(duongDan);
				
				FileDataSource fds = new FileDataSource(file);
				filepart.setDataHandler(new DataHandler(fds));
				filepart.setFileName(file.getName());
				
				MimeMultipart multipart = new MimeMultipart();
				
				multipart.addBodyPart(contentPart);
				multipart.addBodyPart(filepart);
				
				msg.setContent(multipart);
//				if(TaiKhoan_dao.updateMatKhau(maTK, mess)) {
//					setVisible(false);
//				}
//				JOptionPane.showMessageDialog(null, "Mật khẩu mới đã được gửi qua email", "Message", JOptionPane.INFORMATION_MESSAGE);
				Transport.send(msg);
			
				JOptionPane.showMessageDialog(null, "Mail đã được gửi", "Message", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (MessagingException r) {
				throw new RuntimeException(r);
			}
		} else if (src.equals(btnDinhKem)) {
			JFileChooser f = new JFileChooser(System.getProperty("user.dir") + "\\src\\");
			f.setDialogTitle("Mở file");
			f.showOpenDialog(null);
			File fTenAnh = f.getSelectedFile();
			duongDan = fTenAnh.getAbsolutePath();
			txtDinhKem.setText(duongDan);
			
		}
		else if(src.equals(btnDanhSach)) {
			Form_DanhSachKhachHang F_DSKH;
			try {
				F_DSKH = new Form_DanhSachKhachHang(this);
				F_DSKH.setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}

