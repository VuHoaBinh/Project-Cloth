package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.textField.TextField;
import dao.TaiKhoanDAO;
import entities.TaiKhoan;
import utils.Utils;

public class FormDoiMatKhau extends JFrame implements ActionListener{
	private final int widthPnlContainer = 960;
	private XemThongTinCaNhan TTCN;
	private TextField txtMaTK;
	private TextField txtTenTK;
	private TextField txtMK_old;
	private TextField txtMK_new;
	private Button btnDoiMK;
	private Button btnLamMoi;
	private String maTK;
	private TaiKhoanDAO TaiKhoan_dao;
	private String matKhau;
	public FormDoiMatKhau(String maTK) throws MalformedURLException, RemoteException, NotBoundException {
		this.maTK = maTK;
		setSize(960, 500);
		setTitle("Đổi mật khẩu");
		setLocationRelativeTo(null);
		setLayout(null);
		TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/taiKhoanDao");

		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(203, 239, 255));
		pnlContainer.setBounds(0, 0, widthPnlContainer, 250);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(203, 239, 255));
		pnlHeader.setBounds(16, 18, 1000, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblTitle = new JLabel("Đổi mật khẩu");
		lblTitle.setBounds(380, 10, 400, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(new Color(203, 239, 255));
		pnlRow1.setBounds(80, 100, 792, 55);
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		txtMaTK = new TextField();
		txtMaTK.setEditable(false);
		txtMaTK.setLabelText("Mã tài khoản:");
		txtMaTK.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaTK.setBackground(new Color(203, 239, 255));
		txtMaTK.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMaTK);
		
		txtTenTK= new TextField();
		txtTenTK.setEditable(false);
		txtTenTK.setLabelText("Tài khoản:");
		txtTenTK.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenTK.setBackground(new Color(203, 239, 255));
		txtTenTK.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtTenTK);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(new Color(203, 239, 255));
		pnlRow2.setBounds(80, 160, 792, 55);
		add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtMK_old = new TextField();
		txtMK_old.setLabelText("Mật khẩu hiện tại:");
		txtMK_old.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMK_old.setBackground(new Color(203, 239, 255));
		txtMK_old.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtMK_old);
		
		txtMK_new = new TextField();
		txtMK_new.setLabelText("Mật khẩu mới:");
		txtMK_new.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMK_new.setBackground(new Color(203, 239, 255));
		txtMK_new.setBounds(421, 0, 371, 55);
		pnlRow2.add(txtMK_new);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(new Color(203, 239, 255));
		pnlActions.setBounds(0, 250, 1000, 270);
//		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnDoiMK = new Button("Đổi mật khẩu");
		btnDoiMK.setFocusable(false);
		btnDoiMK.setIcon(Utils.getImageIcon("password.png"));
		btnDoiMK.setRadius(4);
		btnDoiMK.setForeground(Color.WHITE);
		btnDoiMK.setColor(new Color(134, 229, 138));
		btnDoiMK.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnDoiMK.setBounds(904, -2, 150, 40);
		btnDoiMK.setBorderColor(Utils.secondaryColor);
		btnDoiMK.setColorOver(new Color(134, 229, 138));
		btnDoiMK.setColorClick(new Color(59, 238, 66));
		btnDoiMK.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDoiMK.setBounds(200, 0, 180, 40);

		pnlActions.add(btnDoiMK);
		
		
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
		btnLamMoi.setBounds(550, -2, 150, 40);
		pnlActions.add(btnLamMoi);
		matKhau = "";
		for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
			if(tk.getMaTaiKhoan().equals(maTK)) {
				txtMaTK.setText(maTK);
				matKhau = tk.getMatKhau();
				txtTenTK.setText(tk.getTenTaiKhoan());
				break;
			}
		}
		btnDoiMK.addActionListener(this);
		btnLamMoi.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnLamMoi)) {
			refresh();
		}
		else if(src.equals(btnDoiMK)) {
			if(txtMK_old.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu hiện tại");
			else if(txtMK_new.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
			else {
				String mk_old = txtMK_old.getText();
				String mk_new = txtMK_new.getText();
				if(!mk_old.equals(matKhau)) {
					JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không khớp với tài khoản đăng nhập");
				}
				else {
					try {
						if(TaiKhoan_dao.updateMatKhau(maTK, mk_new)) {
							JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
							setVisible(false);
						}
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
	public void refresh() {
		txtMK_old.setText("");
		txtMK_new.setText("");
	}
}
