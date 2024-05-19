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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.raven.datechooser.DateChooser;

import components.button.Button;
import components.notification.Notification;
import components.notification.Notification.Type;
import components.passwordField.PasswordField;
import components.textField.TextField;
import dao.CaLamDAO;
import dao.LoaiNhanVienDAO;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entities.CaLam;
import entities.LoaiNhanVien;
import entities.NhanVien;
import entities.TaiKhoan;
import utils.Utils;

public class XemThongTinCaNhan extends JPanel implements ActionListener {
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtDiaChi;
	private TextField txtTen;
	private TextField txtSoDienThoai;
	private TextField txtNgaySinh;
	private TextField txtEmail;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private TextField txtQuanLy;
	private PasswordField txtMatKhau;
	private TextField txtLoaiNhanVien;
	private TextField txtTaiKhoan;
	private DateChooser dateChoose;
	private TextField txtCaLam;
	private TaiKhoanDAO TaiKhoan_dao;
	private NhanVienDAO NhanVien_dao;
	private LoaiNhanVienDAO LoaiNhanVien_dao;
	private CaLamDAO CaLam_dao;
	private List<TaiKhoan> dsTK;
	private List<NhanVien> dsNV;
	private List<LoaiNhanVien> dsLNV;
	private List<CaLam> dsCL;
	private Button btnCapNhat;
	private Button btnDoiMatKHau;
	private String maTK;
	private String loaiNhanVien;
	private String caLam;
	private String maNV;

	public XemThongTinCaNhan(ManHinhChinh main, String maTK)
			throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		this.maTK = maTK;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth(), Utils.getBodyHeight());
		setLayout(null);
		NhanVien_dao = (NhanVienDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/nhanVienDao");
		TaiKhoan_dao = (TaiKhoanDAO) Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/taiKhoanDao");

		int topPnlControl = Utils.getBodyHeight() - 80;

		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, widthPnlContainer, 80);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Utils.secondaryColor);
		pnlHeader.setBounds(16, 18, 1054, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Thông tin cá nhân");
		lblTitle.setBounds(470, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);

		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 792, 55);
		top += padding + 5;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã nhân viên:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);

		txtDiaChi = new TextField();
		txtDiaChi.setLabelText("Địa chỉ:");
		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiaChi.setBackground(new Color(203, 239, 255));
		txtDiaChi.setBounds(421, 0, 371, 55);
		pnlRow1.add(txtDiaChi);

		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 792, 55);
		top += padding + 5;
		add(pnlRow2);
		pnlRow2.setLayout(null);

		txtTen = new TextField();
		txtTen.setLabelText("Tên nhân viên");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtTen);

		txtSoDienThoai = new TextField();
		txtSoDienThoai.setLabelText("Số điện thoại:");
		txtSoDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoDienThoai.setBackground(new Color(203, 239, 255));
		txtSoDienThoai.setBounds(421, 0, 371, 55);
		pnlRow2.add(txtSoDienThoai);

		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 792, 80);
		top += padding + 15;
		add(pnlRow3);
		pnlRow3.setLayout(null);

		txtNgaySinh = new TextField();
		txtNgaySinh.setIcon(Utils.getImageIcon("add-event 2.png"));
		txtNgaySinh.setLineColor(new Color(149, 166, 248));
		txtNgaySinh.setLabelText("Ngày sinh:");
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(203, 239, 255));
		txtNgaySinh.setBounds(0, 0, 371, 55);
		pnlRow3.add(txtNgaySinh);
		dateChoose = new DateChooser();
		dateChoose.setDateFormat("yyyy-MM-dd");
		dateChoose.setTextRefernce(txtNgaySinh);

		txtEmail = new TextField();
		txtEmail.setLabelText("Email:");
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtEmail.setBackground(new Color(203, 239, 255));
		txtEmail.setBounds(421, 0, 371, 55);
		pnlRow3.add(txtEmail);

		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 792, 80);
		top += padding + 15;
		add(pnlRow4);
		pnlRow4.setLayout(null);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(0, 0, 100, 65);
		pnlRow4.add(lblGioiTinh);

		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		radNam.setBackground(Utils.secondaryColor);
		radNam.setBounds(0, 46, 90, 36);
		pnlRow4.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		radNu.setBackground(Utils.secondaryColor);
		radNu.setBounds(120, 46, 90, 36);
		pnlRow4.add(radNu);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(radNam);
		btnGroup.add(radNu);

		txtTaiKhoan = new TextField();
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setLabelText("Tài khoản:");
		txtTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTaiKhoan.setBackground(new Color(203, 239, 255));
		txtTaiKhoan.setBounds(421, 0, 371, 55);
		pnlRow4.add(txtTaiKhoan);

		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 792, 80);
		top += padding + 15;
		add(pnlRow5);
		pnlRow5.setLayout(null);

		txtQuanLy = new TextField();
		txtQuanLy.setEditable(false);
		txtQuanLy.setLabelText("Quản lý bởi:");
		txtQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtQuanLy.setBackground(new Color(203, 239, 255));
		txtQuanLy.setBounds(0, 0, 371, 55);
		pnlRow5.add(txtQuanLy);

		txtMatKhau = new PasswordField();
		txtMatKhau.setEditable(false);
		txtMatKhau.setLabelText("Mật khẩu:");
		txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMatKhau.setBackground(new Color(203, 239, 255));
		txtMatKhau.setBounds(421, 0, 371, 55);
		pnlRow5.add(txtMatKhau);

		JPanel pnlRow6 = new JPanel();
		pnlRow6.setBackground(Utils.secondaryColor);
		pnlRow6.setBounds(left, top, 792, 80);
		top += padding + 30;
		add(pnlRow6);
		pnlRow6.setLayout(null);

		txtLoaiNhanVien = new TextField();
		txtLoaiNhanVien.setEditable(false);
		txtLoaiNhanVien.setLabelText("Loại nhân viên:");
		txtLoaiNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtLoaiNhanVien.setBackground(new Color(203, 239, 255));
		txtLoaiNhanVien.setBounds(0, 0, 371, 55);
		pnlRow6.add(txtLoaiNhanVien);

		txtCaLam = new TextField();
		txtCaLam.setEditable(false);
		txtCaLam.setLabelText("Ca làm:");
		txtCaLam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCaLam.setBackground(new Color(203, 239, 255));
		txtCaLam.setBounds(421, 0, 371, 55);
		pnlRow6.add(txtCaLam);

		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top, 992, 50);
		top += padding + 20;
		add(pnlActions);
		pnlActions.setLayout(null);

		btnCapNhat = new Button("Cập nhật thông tin");
		btnCapNhat.setIcon(Utils.getImageIcon("update 1.png"));
		btnCapNhat.setRadius(8);
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnCapNhat.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnCapNhat.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnCapNhat.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnCapNhat.setBorderColor(new Color(203, 239, 255));
		btnCapNhat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCapNhat.setBounds(150, 0, 280, 40);
		pnlActions.add(btnCapNhat);

		btnDoiMatKHau = new Button("Đổi mật khẩu");
		btnDoiMatKHau.setIcon(Utils.getImageIcon("password.png"));
		btnDoiMatKHau.setRadius(8);
		btnDoiMatKHau.setForeground(Color.WHITE);
		btnDoiMatKHau.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnDoiMatKHau.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDoiMatKHau.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDoiMatKHau.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDoiMatKHau.setBorderColor(new Color(203, 239, 255));
		btnDoiMatKHau.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDoiMatKHau.setBounds(515, 0, 190, 40);
		pnlActions.add(btnDoiMatKHau);

//		maNV = "";
//		dsTK = new ArrayList<TaiKhoan>();
//		dsNV = new ArrayList<NhanVien>();
//		dsLNV = new ArrayList<LoaiNhanVien>();
//		dsCL = new ArrayList<CaLam>();
//		TaiKhoan_dao = new TaiKhoan_Dao();
//		dsTK = TaiKhoan_dao.getAllTaiKhoan();
//		for (TaiKhoan tk : dsTK) {
//			if(tk.getMaTaiKhoan().equals(maTK)) {
//				maNV = tk.getNhanVien().getMaNhanVien();
//				txtMa.setText(tk.getNhanVien().getMaNhanVien());
//				txtTaiKhoan.setText(tk.getTenTaiKhoan());
//				txtMatKhau.setText(tk.getMatKhau());
//				if(tk.getEmail() == null)
//					txtEmail.setText("");
//				else
//					txtEmail.setText(tk.getEmail());
//				break;
//			}
//		}
//		loaiNhanVien = "";
//		caLam = "";
//		NhanVien_dao = new NhanVien_Dao();
//		dsNV = NhanVien_dao.getAllNhanVien();
//		for (NhanVien nv : dsNV) {
//			if(nv.getMaNhanVien().equals(maNV)) {
//				loaiNhanVien = nv.getLoaiNhanVien();
//				caLam = nv.getCaLam();
//				txtTen.setText(nv.getTenNhanVien());
//				if(nv.getDiaChi() == null)
//					txtDiaChi.setText("");
//				else
//					txtDiaChi.setText(nv.getDiaChi());
//				txtSoDienThoai.setText(nv.getSoDienThoai());
//				txtNgaySinh.setText(nv.getNgaySinh().toString());
//				boolean gt = nv.getGioiTinh();
//				if(gt == true) {
//					radNam.setSelected(true);
//					radNu.setSelected(false);
//				}
//				else {
//					radNam.setSelected(false);
//					radNu.setSelected(true);
//				}
//				break;
//			}
//		}
//		LoaiNhanVien_dao = new LoaiNhanVien_Dao();
//		dsLNV = LoaiNhanVien_dao.getAllLoaiNhanVien();
//		for (LoaiNhanVien lnv : dsLNV) {
//			if(lnv.getMaLoaiNhanVien().equals(loaiNhanVien)) {
//				txtLoaiNhanVien.setText(lnv.getTenLoaiNhanVien());
//				break;
//			}
//		}
//		CaLam_dao = new CaLam_Dao();
//		dsCL = CaLam_dao.getAllCaLam();
//		for (CaLam cl : dsCL) {
//			if(cl.getMaCaLam().equals(caLam)) {
//				txtCaLam.setText(cl.getBuoi());
//				break;
//			}
//		}
		getThongTinCaNhan(maTK);
		btnDoiMatKHau.addActionListener(this);
		btnCapNhat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnDoiMatKHau)) {
			try {
				new FormDoiMatKhau(maTK).setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			txtMatKhau.setText(TaiKhoan_dao.getMatKhau(maTK));
		} else if (src.equals(btnCapNhat)) {
			if (validator()) {
				String maNV = txtMa.getText();
				String tenNV = txtTen.getText();
				String email = txtEmail.getText();
				boolean gt1 = false;
				if (radNam.isSelected() && !radNu.isSelected()) {
					gt1 = true;
				} else if (!radNam.isSelected() && radNu.isSelected()) {
					gt1 = false;
				}
				String diachi = txtDiaChi.getText();
				String sodienthoai = txtSoDienThoai.getText();
				String[] date = txtNgaySinh.getText().split("-");
				LocalDate ngaySinh = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]),
						Integer.valueOf(date[2]));
				try {
					if (NhanVien_dao.UpdateTTCN(maNV, tenNV, diachi, sodienthoai, ngaySinh, gt1)
							&& TaiKhoan_dao.updateEmail(maTK, email))
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
					this.repaint();
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		}
	}

	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
//		txt.setError(true);
		txt.selectAll();
		txt.requestFocus();
		return false;
	}

	public void getThongTinCaNhan(String maTK) throws RemoteException {
		Object[] thongTin = NhanVien_dao.getThongTinCaNhans(maTK);
		txtMa.setText(thongTin[0].toString());
		txtDiaChi.setText(thongTin[1].toString());
		txtTen.setText(thongTin[2].toString());
		txtSoDienThoai.setText(thongTin[3].toString());
		txtNgaySinh.setText(thongTin[4].toString());
		if (thongTin[5] == null)
			txtEmail.setText("");
		else
			txtEmail.setText(thongTin[5].toString());
		if (thongTin[6].equals("1")) {
			radNam.setSelected(true);
			radNu.setSelected(false);
		} else {
			radNam.setSelected(false);
			radNu.setSelected(true);
		}
		txtTaiKhoan.setText(thongTin[7].toString());
		if (thongTin[8] == null)
			txtQuanLy.setText("");
		else
			txtQuanLy.setText(thongTin[8].toString());
		txtMatKhau.setText(thongTin[9].toString());
		txtLoaiNhanVien.setText(thongTin[10].toString());
		txtCaLam.setText(thongTin[11].toString());
	}

	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";

//		String ma = txtMa.getText().trim();

//		if (ma.length() <= 0)
//			return showThongBaoLoi(txtMa, "Vui lòng nhập mã nhân viên");
//
//		if (!Pattern.matches("^NV\\d{4}$", ma))
//			return showThongBaoLoi(txtMa, "Mã nhân viên phải theo định dạng NVXXXX với XX là 4 chữ số");
//
		String ten = txtTen.getText().trim();

		if (ten.length() <= 0)
			return showThongBaoLoi(txtTen, "Vui lòng nhập tên nhân viên");

		if (!ten.matches("^[a-zA-ZÀ-ỹ ]*$"))
			return showThongBaoLoi(txtTen, "tên chỉ chứa các ký tự chữ cái");

		String diaChi = txtDiaChi.getText().trim();

		if (diaChi.length() <= 0)
			return showThongBaoLoi(txtDiaChi, "Vui lòng nhập địa chỉ");
		if (!diaChi.matches("^[0-9a-zA-ZÀ-ỹ \\,-]+$"))
			return showThongBaoLoi(txtDiaChi, "địa chỉ chỉ chứa các ký tự chữ cái");

		String soDienThoai = txtSoDienThoai.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtSoDienThoai, "Vui lòng nhập số điện thoại");
		if (!soDienThoai.matches("^[0-9]{10}$"))
			return showThongBaoLoi(txtSoDienThoai, "Số điện thoại gồm 10 ký tự số");
		String selectedDate = txtNgaySinh.getText();

		LocalDate ngaySinh = LocalDate.parse(selectedDate);

		LocalDate ngayHienTai = LocalDate.now();

		if (!ngaySinh.isBefore(ngayHienTai)) {
			new Notification(main, Type.ERROR, "Ngày sinh phải trước ngày hiện tại").showNotification();
			dateChoose.showPopup();
			return false;
		}
		String email = txtEmail.getText().trim();

		if (email.length() > 0)
			if (!email.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$"))
				return showThongBaoLoi(txtEmail, "Email không hợp lệ");

		return true;
	}
}
