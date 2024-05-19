package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
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

import components.button.Button;
import components.comboBox.ComboBox;
import components.controlPanel.ControlPanel;
import components.notification.Notification;
import components.notification.Notification.Type;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entities.NhanVien;
import entities.PhieuDatHang;
import entities.TaiKhoan;
import utils.Utils;

public class QuanLyTaiKhoan extends JPanel implements ActionListener{
	private ManHinhChinh main;
	private final int widthPnlContainer = 1286;
	private TextField txtMa;
	private TextField txtMaTK;
	private TextField txtMK;
	private TextField txtTK;
	private JTable tbl;
	private DefaultTableModel tableModel;
	private ControlPanel pnlControl;
	private ComboBox cboQuyenHan;
	private Button btnTimKiem;
	private NhanVienDAO NhanVien_dao;
	private ArrayList<NhanVien> dsNV;
	private Button btnTao;
	private Button btnRefresh;
	private TaiKhoanDAO TaiKhoan_dao;
	private ArrayList<TaiKhoan> dsTK;
	private TextField txtEmail;
	private Button btnUpdate;
	private Button btnDelete; 
	public QuanLyTaiKhoan(ManHinhChinh main) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(792);
		NhanVien_dao = (NhanVienDAO)Naming.lookup("rmi://" + url.RmiConfig.RMI_URL + ":2008/nhanVienDao");
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
		pnlHeader.setBounds(16, 18, 1054, 50);
		pnlContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblTitle = new JLabel("Thông tin tài khoản");
		lblTitle.setBounds(470, 10, 295, 30);
		lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		pnlHeader.add(lblTitle);
		
		JPanel pnlCol1 = new JPanel();
		pnlCol1.setBackground(Utils.secondaryColor);
		pnlCol1.setBounds(left, top, 450, 400);
		top += padding+5;
		add(pnlCol1);
		pnlCol1.setLayout(null);
		
		txtMa = new TextField();
//		txtMa.setEditable(false);
		txtMa.setLabelText("Mã nhân viên:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlCol1.add(txtMa);
		
		btnTimKiem = new Button();
		btnTimKiem.setIcon(Utils.getImageIcon("check-mark.png"));
		btnTimKiem.setRadius(8);
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnTimKiem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnTimKiem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnTimKiem.setBorderColor(new Color(203, 239, 255));
		btnTimKiem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTimKiem.setBounds(390, 15, 40, 40);
		pnlCol1.add(btnTimKiem);
		
		
		txtMaTK= new TextField();
		txtMaTK.setEditable(false);
		txtMaTK.setLabelText("Mã tài khoản:");
		txtMaTK.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMaTK.setBackground(new Color(203, 239, 255));
		txtMaTK.setBounds(0, 65, 371, 55);
		pnlCol1.add(txtMaTK);
		
		txtTK= new TextField();
		txtTK.setLabelText("Tài khoản:");
		txtTK.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTK.setBackground(new Color(203, 239, 255));
		txtTK.setBounds(0, 130, 371, 55);
		pnlCol1.add(txtTK);
		
		txtMK= new TextField();
		txtMK.setLabelText("Mật khẩu:");
		txtMK.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMK.setBackground(new Color(203, 239, 255));
		txtMK.setBounds(0, 195, 371, 55);
		pnlCol1.add(txtMK);
		
		txtEmail= new TextField();
		txtEmail.setLabelText("Email:");
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtEmail.setBackground(new Color(203, 239, 255));
		txtEmail.setBounds(0, 260, 371, 55);
		pnlCol1.add(txtEmail);
		
		JLabel lblQuyenHan = new JLabel("Quyền hạn:");
		lblQuyenHan.setForeground(Utils.labelTextField);
		lblQuyenHan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblQuyenHan.setBounds(0, 310, 100, 65);
		pnlCol1.add(lblQuyenHan);
		
		cboQuyenHan = new ComboBox<>();
		cboQuyenHan.setModel(new DefaultComboBoxModel<String>());
		cboQuyenHan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cboQuyenHan.setBackground(new Color(140, 177, 180));
		cboQuyenHan.setBounds(0, 360, 371, 36);
		cboQuyenHan.addItem("");
		cboQuyenHan.addItem("quan ly");
		cboQuyenHan.addItem("nhan vien");
	
		pnlCol1.add(cboQuyenHan);
		
		
		
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left+490, top, 300, 300);
		top += padding+5;
		add(pnlActions);
		pnlActions.setLayout(null);
		
		btnTao = new Button("Tạo tài khoản");
		btnTao.setIcon(Utils.getImageIcon("add-user.png"));
		btnTao.setRadius(8);
		btnTao.setForeground(Color.WHITE);
		btnTao.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnTao.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnTao.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnTao.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnTao.setBorderColor(new Color(203, 239, 255));
		btnTao.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnTao.setBounds(0, 80, 220, 40);
		pnlActions.add(btnTao);
		
		btnUpdate = new Button("Cập nhật");
		btnUpdate.setIcon(Utils.getImageIcon("update 1.png"));
		btnUpdate.setRadius(8);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnUpdate.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnUpdate.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnUpdate.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnUpdate.setBorderColor(new Color(203, 239, 255));
		btnUpdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUpdate.setBounds(0, 125, 220, 40);
		pnlActions.add(btnUpdate);
		
		btnDelete = new Button("Xóa");
		btnDelete.setIcon(Utils.getImageIcon("delet.png"));
		btnDelete.setRadius(8);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnDelete.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDelete.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setBorderColor(new Color(203, 239, 255));
		btnDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDelete.setBounds(0, 170, 220, 40);
		pnlActions.add(btnDelete);
		
		btnRefresh = new Button("Làm mới");
		btnRefresh.setIcon(Utils.getImageIcon("refresh.png"));
		btnRefresh.setRadius(8);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnRefresh.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnRefresh.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setBorderColor(new Color(203, 239, 255));
		btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRefresh.setBounds(0, 215, 220, 40);
		pnlActions.add(btnRefresh);
		
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left-200, 480, 1200, 250);
		scr.setBackground(Utils.primaryColor);
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
		tbl.setAutoCreateRowSorter(true);  //tự động tạo 1 bộ sắp xếp cho tất cả các cột trong bảng
		JTableHeader tblHeader = tbl.getTableHeader();
		TableColumnModel tableColumnModel = tbl.getColumnModel();

		tableModel = new DefaultTableModel(new String[] { "Mã nhân viên", "Mã tài khoản", "Tài khoản", "Mật khẩu","Email","Quyền hạn"}, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
				txtMaTK.setText(tbl.getValueAt(row, 1).toString());
				txtTK.setText(tbl.getValueAt(row, 2).toString());
				txtMK.setText(tbl.getValueAt(row, 3).toString());
				if(tbl.getValueAt(row, 4) == null)
					txtEmail.setText("");
				else
					txtEmail.setText(tbl.getValueAt(row, 4).toString());
				cboQuyenHan.setSelectedItem(tbl.getValueAt(row, 5));
			}
		});
		tableColumnModel.getColumn(0).setPreferredWidth(200);
		tableColumnModel.getColumn(1).setPreferredWidth(200);
		tableColumnModel.getColumn(2).setPreferredWidth(200);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(200);
		tableColumnModel.getColumn(5).setPreferredWidth(200);

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
//		pnlControl = new ControlPanel(Utils.getLeft(286),topPnlControl + 10 , main);
//		this.add(pnlControl);
		
		dsNV = new ArrayList<NhanVien>();
		
		for (NhanVien nv : NhanVien_dao.getAllNhanVien()) {
			dsNV.add(nv);
		}
		
		dsTK = new ArrayList<TaiKhoan>();
		for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
			dsTK.add(tk);
			Object[] data = {tk.getNhanVien().getMaNhanVien(), tk.getMaTaiKhoan(), tk.getTenTaiKhoan(),
					tk.getMatKhau(), tk.getEmail(),tk.getQuyenHan()};
			tableModel.addRow(data);
		}
		
		btnTimKiem.addActionListener(this);
		btnTao.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src.equals(btnTimKiem)) {
			String maNV = txtMa.getText();
			boolean kq_tt = false;
			for (NhanVien nv : dsNV) {
				if(maNV.equals(nv.getMaNhanVien())) {
					kq_tt = true;
					if(nv.getLoaiNhanVien().getMaLoaiNhanVien().equals("LNV001"))
						cboQuyenHan.setSelectedIndex(1);
					else
						cboQuyenHan.setSelectedIndex(2);
				}	
			}
			if(kq_tt == false)
				JOptionPane.showMessageDialog(this, "Nhân viên này không tồn tại!");
		}
		else if(src.equals(btnTao)) {
			String maNV = txtMa.getText();
			if(kiemTraTaiKhoan(maNV) == false)
				JOptionPane.showMessageDialog(this, "Nhân viên này đã có tài khoản!");
			else {
				if(validator()) {
					
					try {
						String maTK = taoMaTaiKhoan();
						String TK = txtTK.getText();
						String MK = txtMK.getText();
						String email = txtEmail.getText();
						String quyenhan = cboQuyenHan.getSelectedItem().toString();
						NhanVien nv;
						nv = NhanVien_dao.getNhanVien(maNV);
						TaiKhoan tk = new TaiKhoan(maTK, TK, MK, email, quyenhan, nv);
						if(TaiKhoan_dao.create(tk)) {
							Object[] data = {tk.getNhanVien().getMaNhanVien(), tk.getMaTaiKhoan(), tk.getTenTaiKhoan(),
									tk.getMatKhau(), tk.getEmail(),tk.getQuyenHan()};
							tableModel.addRow(data);
							JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công");
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}					
			}
//			JOptionPane.showMessageDialog(this, taoMaTaiKhoan());
		}
		else if(src.equals(btnRefresh))
			refresh();
		else if(src.equals(btnUpdate)) {
			int row = tbl.getSelectedRow();
			if(row!=-1) {
				if(validator()) {		
					try {
						String maTK = txtMaTK.getText();
						String maNV = txtMa.getText();
						String TK = txtTK.getText();
						String MK = txtMK.getText();
						String email = txtEmail.getText();
						String quyenhan = cboQuyenHan.getSelectedItem().toString();
						NhanVien nv = NhanVien_dao.getNhanVien(maNV);
						TaiKhoan tk = new TaiKhoan(maTK, TK, MK, email, quyenhan, nv);
						if (TaiKhoan_dao.update(tk)) {
							tableModel.setValueAt(maNV, row, 0);
							tableModel.setValueAt(maTK, row, 1);
							tableModel.setValueAt(TK, row, 2);
							tableModel.setValueAt(MK, row, 3);
							tableModel.setValueAt(email, row, 4);
							tableModel.setValueAt(quyenhan, row, 5);
							JOptionPane.showMessageDialog(this, "Cập nhật thành công");
							refresh();
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần cập nhật!");
            }
		}
		else if(src.equals(btnDelete)) {
			int row = tbl.getSelectedRow();
			if (row != -1) {
				int kq = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài khoản này không?");
				if (kq == JOptionPane.YES_OPTION) {
					String maTK = txtMaTK.getText();
					try {
						if (TaiKhoan_dao.delete(maTK)) {
							tableModel.removeRow(row);
							JOptionPane.showMessageDialog(this, "Xóa thành công");
							refresh();
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa!");
			}
		}
	}
	public boolean kiemTraTaiKhoan(String maNV) {
		for (TaiKhoan tk : dsTK) {
			if(maNV.equals(tk.getNhanVien().getMaNhanVien()))
				return false;
		}
		return true;
	}
	public void refresh() {
		txtMa.setText("");
		txtMaTK.setText("");
		txtTK.setText("");
		txtMK.setText("");
		cboQuyenHan.setSelectedIndex(0);
		tbl.clearSelection();
	}
	public String taoMaTaiKhoan() throws RemoteException {
		ArrayList<String> dsmaTK = new ArrayList<String>();
		try {
			for (TaiKhoan tk : TaiKhoan_dao.getAllTaiKhoan()) {
				dsmaTK.add(tk.getMaTaiKhoan());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1;i <= 1000;i++) {
			String ma = "";
			if(i<10)
				ma = "TK000"+i;
			else if(i<100)
				ma = "TK00"+i;
			else if(i<1000)
				ma = "TK0"+i;
			else
				ma = "TK1000";
			if(dsmaTK.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}
	private boolean showThongBaoLoi(TextField txt, String message) {
		new Notification(main, Type.ERROR, message).showNotification();
//		txt.setError(true);
		txt.selectAll();
		txt.requestFocus();
		return false;
	}
	private boolean validator() {
		String vietNamese = Utils.getVietnameseDiacriticCharacters() + "A-Z";
		String vietNameseLower = Utils.getVietnameseDiacriticCharactersLower() + "a-z";
		String email = txtEmail.getText().trim();
		
		if (email.length() > 0)
			if(!email.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$"))
				return showThongBaoLoi(txtEmail, "Email không hợp lệ");
		

		return true;
	}
}
