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
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.textField.TextField;
import dao.MauSacDAO;
import entities.LoaiSanPham;
import entities.MauSac;
import utils.Utils;

public class Form_ThemMauSac extends JDialog implements ActionListener{
	private final int widthPnlContainer = 500;
	private TextField txtTenMauSac;
	private Button btnThem;
	private Button btnHuy;
	private QuanLySanPham qlSP;
	private MauSacDAO MauSac_dao;
	public Form_ThemMauSac(JPanel parent) throws MalformedURLException, RemoteException, NotBoundException {
		setSize(500, 300);
		setTitle("Thêm màu sắc");
		setLocationRelativeTo(null);
		setLayout(null);
		qlSP = (QuanLySanPham)parent;
		MauSac_dao = (MauSacDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/mauSacDao");
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(203, 239, 255));
		pnlContainer.setBounds(0, 0, widthPnlContainer, 220);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(203, 239, 255));
		pnlHeader.setBounds(16, 18, 1000, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblTitle = new JLabel("Thêm màu sắc");
		lblTitle.setBounds(100, 10, 500, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(new Color(203, 239, 255));
		pnlRow1.setBounds(100, 100, 550, 55);
		add(pnlRow1);
		pnlRow1.setLayout(null);
		
		txtTenMauSac = new TextField();
		txtTenMauSac.setLabelText("Tên màu sắc:");
		txtTenMauSac.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenMauSac.setBackground(new Color(203, 239, 255));
		txtTenMauSac.setBounds(0, 0, 250, 55);
		pnlRow1.add(txtTenMauSac);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(new Color(203, 239, 255));
		pnlActions.setBounds(0, 220, 500, 60);
//		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnThem = new Button("Thêm");
		btnThem.setFocusable(false);
		btnThem.setIcon(Utils.getImageIcon("plus.png"));
		btnThem.setRadius(4);
		btnThem.setForeground(Color.WHITE);
		btnThem.setColor(new Color(134, 229, 138));
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnThem.setBounds(904, -2, 150, 40);
		btnThem.setBorderColor(Utils.secondaryColor);
		btnThem.setColorOver(new Color(134, 229, 138));
		btnThem.setColorClick(new Color(59, 238, 66));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(70, 0, 150, 40);

		pnlActions.add(btnThem);
		
		
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
		
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnThem)) {
//			JOptionPane.showMessageDialog(this, taoMaLoaiSanPham());
			String mams = null;
			try {
				mams = taoMaMauSac();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String tenms = txtTenMauSac.getText();
			MauSac ms = new MauSac();
			ms.setMaMauSac(mams);
			ms.setTenMauSac(tenms);
			try {
				if(MauSac_dao.create(ms)) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					qlSP.resetcboMS();;
					setVisible(false);
				}
			} catch (HeadlessException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(src.equals(btnHuy)) {
			setVisible(false);
		}
	}
	public String taoMaMauSac() throws RemoteException {
		ArrayList<String> dsmaMS = new ArrayList<String>();
		for (MauSac ms : MauSac_dao.getAllMauSac()) {
			dsmaMS.add(ms.getMaMauSac());
		}
		for(int i = 1;i < 100;i++) {
			String ma = "";
			if(i<10)
				ma = "MS0"+i;
			else if(i<100)
				ma = "MS"+i;
			if(dsmaMS.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}
}
