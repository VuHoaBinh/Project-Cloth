package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
import components.controlPanel.ControlPanel;
import components.radio.RadioButtonCustom;
import components.scrollbarCustom.ScrollBarCustom;
import components.textField.TextField;
import components.notification.Notification;
import components.notification.Notification.Type;
import dao.KhachHangDAO;
import entities.KhachHang;
import entities.PhieuDatHang;
import utils.Utils;

public class QuanLyKhachHang extends JPanel implements ActionListener{
	private ControlPanel pnlControl;
	private DefaultTableModel tableModel;
	private JTable tbl;
	private ManHinhChinh main;
	private String maTK;
	private final int widthPnlContainer = 1200;
	private JLabel lblTieuDe;
	private TextField txtMa;
	private TextField txtTen;
	private RadioButtonCustom rdoNam, rdoNu;
	private TextField txtAddress;
	private TextField txtEmail;
	public TextField txtPhone;
	private JLabel lblTable;
	private Button btnThem;
	private Button btnUpdate;
	private Button btnDelete;
	private Button btnLapHoaDon;
	private KhachHangDAO KhachHang_dao;
	private Button btnRefresh;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public QuanLyKhachHang(ManHinhChinh main, String maTK) throws MalformedURLException, RemoteException, NotBoundException {
		this.main = main;
		this.maTK = maTK;
		int padding = (int) Math.floor((Utils.getBodyHeight() - 371) * 1.0 / 7);
		int top = padding;
		int left = Utils.getLeft(1200);
		KhachHang_dao = (KhachHangDAO)Naming.lookup("rmi://"+url.RmiConfig.RMI_URL+":2008/khachHangDao");

		setBackground(Utils.secondaryColor);
		setBounds(0, 0, Utils.getScreenWidth() , Utils.getBodyHeight());
		setLayout(null);
		
		int topPnlControl = Utils.getBodyHeight() - 80;
		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBackground(Utils.secondaryColor);
		pnlContainer.setBounds(Utils.getLeft(widthPnlContainer), -10, 1200, 90);
		pnlContainer.setLayout(null);
		this.add(pnlContainer);
		
		lblTieuDe = new JLabel();
		lblTieuDe.setText("Thông tin khách hàng");
		lblTieuDe.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblTieuDe.setBackground(new Color(203, 239, 255));
		lblTieuDe.setBounds(0, 30, 371, 55);
		pnlContainer.add(lblTieuDe);
		
		lblTable = new JLabel();
		lblTable.setText("Danh sách khách hàng");
		lblTable.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		lblTable.setBackground(new Color(203, 239, 255));
		lblTable.setBounds(750, 30, 371, 55);
		pnlContainer.add(lblTable);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setBackground(Utils.secondaryColor);
		pnlRow1.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow1);
		pnlRow1.setLayout(null);

		txtMa = new TextField();
		txtMa.setEditable(false);
		txtMa.setLabelText("Mã khách hàng:");
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtMa.setBackground(new Color(203, 239, 255));
		txtMa.setBounds(0, 0, 371, 55);
		pnlRow1.add(txtMa);
		
		JPanel pnlRow2 = new JPanel();
		pnlRow2.setBackground(Utils.secondaryColor);
		pnlRow2.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow2);
		pnlRow2.setLayout(null);
		
		txtTen = new TextField();
		txtTen.setLabelText("Tên khách hàng:");
		txtTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTen.setBackground(new Color(203, 239, 255));
		txtTen.setBounds(0, 0, 371, 55);
		pnlRow2.add(txtTen);
		
		JPanel pnlRow3 = new JPanel();
		pnlRow3.setBackground(Utils.secondaryColor);
		pnlRow3.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow3);
		pnlRow3.setLayout(null);
		
		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBackground(Utils.secondaryColor);
		pnlGioiTinh.setBounds(0, 0, 371, 55);
		pnlRow3.add(pnlGioiTinh);
		pnlGioiTinh.setLayout(null);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(Utils.labelTextField);
		lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblGioiTinh.setBounds(2, 0, 371, 19);
		pnlGioiTinh.add(lblGioiTinh);

		JPanel pnlGroupGioiTinh = new JPanel();
		pnlGroupGioiTinh.setBackground(Utils.secondaryColor);
		pnlGroupGioiTinh.setBounds(2, 30, 138, 16);
		pnlGioiTinh.add(pnlGroupGioiTinh);
		pnlGroupGioiTinh.setLayout(null);

		rdoNam = new RadioButtonCustom("Nam");
		rdoNam.setFocusable(false);
		rdoNam.setBackground(Utils.secondaryColor);
		rdoNam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdoNam.setBounds(0, -2, 59, 21);
		rdoNam.setSelected(true);
		pnlGroupGioiTinh.add(rdoNam);

		rdoNu = new RadioButtonCustom("Nữ");
		rdoNu.setFocusable(false);
		rdoNu.setBackground(Utils.secondaryColor);
		rdoNu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		rdoNu.setBounds(79, -2, 59, 21);
		pnlGroupGioiTinh.add(rdoNu);

		ButtonGroup buttonGroupGioiTinh = new ButtonGroup();
		buttonGroupGioiTinh.add(rdoNam);
		buttonGroupGioiTinh.add(rdoNu);
		
		JPanel pnlRow4 = new JPanel();
		pnlRow4.setBackground(Utils.secondaryColor);
		pnlRow4.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow4);
		pnlRow4.setLayout(null);
		
		txtAddress = new TextField();
		txtAddress.setLabelText("Địa chỉ:");
		txtAddress.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtAddress.setBackground(new Color(203, 239, 255));
		txtAddress.setBounds(0, 0, 371, 55);
		pnlRow4.add(txtAddress);
		
		JPanel pnlRow5 = new JPanel();
		pnlRow5.setBackground(Utils.secondaryColor);
		pnlRow5.setBounds(left, top, 400, 55);
		top += padding + 15;
		add(pnlRow5);
		pnlRow5.setLayout(null);
		
		txtPhone = new TextField();
		txtPhone.setLabelText("Số điện thoại:");
		txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPhone.setBackground(new Color(203, 239, 255));
		txtPhone.setBounds(0, 0, 371, 55);
		pnlRow5.add(txtPhone);
		
		JPanel pnlRow6 = new JPanel();
		pnlRow6.setBackground(Utils.secondaryColor);
		pnlRow6.setBounds(left, top, 400, 55);
		top += padding + 25;
		add(pnlRow6);
		pnlRow6.setLayout(null);
		
		txtEmail = new TextField();
		txtEmail.setLabelText("Email:");
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtEmail.setBackground(new Color(203, 239, 255));
		txtEmail.setBounds(0, 0, 371, 55);
		pnlRow6.add(txtEmail);
		
		// table
		JScrollPane scr = new JScrollPane();
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setBounds(left + 450, 90, 880, 370);
		scr.setBackground(Utils.primaryColor);
		scr.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
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

		tableModel = new DefaultTableModel(new String[] { "Mã KH", "Tên khách hàng", "Giới tính", "Địa chỉ","Số điện thoại", "Email" }, 0);
		tbl.setModel(tableModel);
		tbl.setFocusable(false);
		tblHeader.setBackground(Utils.primaryColor);
		tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tbl.setBackground(Color.WHITE);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(180);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(150);
		tableColumnModel.getColumn(5).setPreferredWidth(150);
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
				txtTen.setText(tbl.getValueAt(row, 1).toString());
				String gioiTinh = tbl.getValueAt(row, 2).toString();
				if(gioiTinh.equals("nam")) {
					rdoNam.setSelected(true);
					rdoNu.setSelected(false);
				}
				else {
					rdoNam.setSelected(false);
					rdoNu.setSelected(true);
				}
				if(tbl.getValueAt(row, 3) == null)
					txtAddress.setText("");
				else
					txtAddress.setText(tbl.getValueAt(row, 3).toString());
				txtPhone.setText(tbl.getValueAt(row, 4).toString());
				if(tbl.getValueAt(row, 5) == null)
					txtEmail.setText("");
				else
					txtEmail.setText(tbl.getValueAt(row, 5).toString());
//				JOptionPane.showMessageDialog(null, tbl.getValueAt(row, 4).toString());
				btnLapHoaDon.setEnabled(true);
			}
		});

		tblHeader.setPreferredSize(new Dimension((int) tblHeader.getPreferredSize().getWidth(), 36));
		tblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tblHeader.setForeground(Utils.getOpacity(Color.BLACK, 0.55f));
		tbl.setRowHeight(36);
		scr.setViewportView(tbl);
		
//		pnlControl = new ControlPanel(900,470 , main);
//		this.add(pnlControl);
		// end table
		
		JPanel pnlActions = new JPanel();
		pnlActions.setBackground(Utils.secondaryColor);
		pnlActions.setBounds(left, top + 50, 1090, 50);
//		top += padding + 55;
		add(pnlActions);
		pnlActions.setLayout(null);

		btnThem = new Button("Thêm");
		btnThem.setIcon(Utils.getImageIcon("add-user (2) 1.png"));
		btnThem.setRadius(8);
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnThem.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnThem.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnThem.setBorderColor(new Color(203, 239, 255));
		btnThem.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnThem.setBounds(165, 0, 150, 40);
		pnlActions.add(btnThem);
		
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
		btnUpdate.setBounds(330, 0, 170, 40);
		pnlActions.add(btnUpdate);
		
		btnDelete = new Button("Xóa");
		btnDelete.setIcon(Utils.getImageIcon("unemployed 1.png"));
		btnDelete.setRadius(8);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnDelete.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setColorClick(Utils.getRGBA(24, 140, 87, 1));
		btnDelete.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnDelete.setBorderColor(new Color(203, 239, 255));
		btnDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDelete.setBounds(510, 0, 150, 40);
		pnlActions.add(btnDelete);
		
		btnRefresh = new Button("Làm mới");
		btnRefresh.setIcon(Utils.getImageIcon("refresh.png"));
		btnRefresh.setRadius(8);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnRefresh.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColorClick(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnRefresh.setBorderColor(new Color(203, 239, 255));
		btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRefresh.setBounds(670, 0, 150, 40);
		pnlActions.add(btnRefresh);
		
		btnLapHoaDon = new Button("Lập hóa đơn");
		btnLapHoaDon.setIcon(Utils.getImageIcon("bill (1).png"));
		btnLapHoaDon.setRadius(8);
		btnLapHoaDon.setForeground(Color.WHITE);
		btnLapHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		btnLapHoaDon.setColorOver(Utils.getRGBA(36, 214, 134, 1));
		btnLapHoaDon.setColorClick(Utils.getRGBA(36, 214, 134, 1));
		btnLapHoaDon.setColor(Utils.getRGBA(36, 214, 134, 1));
		btnLapHoaDon.setBorderColor(new Color(203, 239, 255));
		btnLapHoaDon.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLapHoaDon.setBounds(830, 0, 200, 40);
		pnlActions.add(btnLapHoaDon);
		btnLapHoaDon.setEnabled(false);

		btnThem.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnLapHoaDon.addActionListener(this);
		
		for (KhachHang kh : KhachHang_dao.getAllKhachHang()) {
			Object[] data = {kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh()==true?"nam":"nữ",
					kh.getDiaChi(), kh.getSoDienThoai(), kh.getEmail()};
			tableModel.addRow(data);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnThem)) {
//			JOptionPane.showMessageDialog(this, taoMaKhachHang());
			if(validator()) {
				String maKH = null;
				try {
					maKH = taoMaKhachHang();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String tenKH = txtTen.getText();
				boolean gioiTinh = false;
				if(rdoNam.isSelected() && !rdoNu.isSelected())
					gioiTinh = true;
				else
					gioiTinh = false;
				String diaChi = txtAddress.getText();
				String email = txtEmail.getText();
				String soDienThoai = txtPhone.getText();

				// Tạo đối tượng KhachHang từ dữ liệu nhập
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(maKH);
				kh.setTenKhachHang(tenKH);
				kh.setGioiTinh(gioiTinh);
				kh.setDiaChi(diaChi);
				kh.setEmail(email);
				kh.setSoDienThoai(soDienThoai);
				try {
					if (KhachHang_dao.create(kh)) {
						Object[] data = { maKH, txtTen.getText(), rdoNam.isSelected() ? "Nam" : "Nữ", txtAddress.getText(),
								txtPhone.getText(), txtEmail.getText() };
						tableModel.addRow(data);
						JOptionPane.showMessageDialog(this, "Thêm thành công");
					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} else if (src.equals(btnDelete)) {
			int row = tbl.getSelectedRow();
			if (row != -1) {
				String maKH = tbl.getValueAt(row, 0).toString();
				int tb = JOptionPane.showConfirmDialog(this, "Có chắc muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if (tb == JOptionPane.YES_OPTION) {
					try {
						KhachHang_dao.delete(maKH);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
			} else
				JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
		} else if (src.equals(btnUpdate)) {
			int row = tbl.getSelectedRow();
			if (row != -1) {
				if(validator()) {
					String maKH = txtMa.getText();
					String tenKH = txtTen.getText();
					boolean gioiTinh = false;
					if(rdoNam.isSelected() && !rdoNu.isSelected())
						gioiTinh = true;
					else
						gioiTinh = false;
					String diaChi = txtAddress.getText();
					String email = txtEmail.getText();
					String soDienThoai = txtPhone.getText();

					// Tạo đối tượng KhachHang từ dữ liệu nhập
					KhachHang kh = new KhachHang();
					kh.setMaKhachHang(maKH);
					kh.setTenKhachHang(tenKH);
					kh.setGioiTinh(gioiTinh);
					kh.setDiaChi(diaChi);
					kh.setEmail(email);
					kh.setSoDienThoai(soDienThoai);

					try {
						if(KhachHang_dao.update(kh)) {
							Object[] data = { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh()?"nam":"nữ", kh.getDiaChi(),
									kh.getSoDienThoai(), kh.getEmail() };
							tableModel.insertRow(row, data);
							tableModel.removeRow(row+1);
							JOptionPane.showMessageDialog(this, "Sửa thành công");
							refresh();
						}
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
			else
				JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để cập nhật thông tin");
		}
		else if(src.equals(btnRefresh))
			refresh();
		else if(src.equals(btnLapHoaDon)) {
			try {
				LapHoaDon lapHoaDon = new LapHoaDon(main, maTK);
				lapHoaDon.txtSoDienThoai.setText(txtPhone.getText());
				main.addPnlBody(lapHoaDon, "Lập hóa đơn", 2, 1);
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	public String taoMaKhachHang() throws RemoteException {
		ArrayList<String> dsmaKH = new ArrayList<String>();
		for (KhachHang kh: KhachHang_dao.getAllKhachHang()) {
			dsmaKH.add(kh.getMaKhachHang());
		}
		for(int i = 1;i <= 1000;i++) {
			String ma = "";
			if(i<10)
				ma = "KH000"+i;
			else if(i<100)
				ma = "KH00"+i;
			else if(i<1000)
				ma = "KH0"+i;
			else
				ma = "KH1000";
			if(dsmaKH.contains(ma) == false)
				return ma;
			else
				continue;
		}
		return null;
	}
	public void refresh() {
		txtMa.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		txtTen.setText("");
		txtPhone.setText("");
		rdoNam.setSelected(true);
		tbl.clearSelection();
		btnLapHoaDon.setEnabled(false);
		
	}
	/**
	 * Hiển thị thông báo lỗi và focus các JTextField
	 *
	 * @param txt     JtextField cần focus
	 * @param message thông báo lỗi
	 * @return false
	 */
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
//		String ma = txtMa.getText().trim();

//		if (ma.length() <= 0)
//			return showThongBaoLoi(txtMa, "Vui lòng nhập mã khách hàng");

//		if (!Pattern.matches("^SP(0[1-9]|[1-9][0-9])$", ma))
//			return showThongBaoLoi(txtMa, "Mã khách hàng phải theo định dạng KHXX với XX là 2 chữ số");

		String ten = txtTen.getText().trim();

		if (ten.length() <= 0)
			return showThongBaoLoi(txtTen, "Vui lòng nhập tên khách hàng");

		if (!ten.matches("^[a-zA-ZÀ-ỹ ]*$"))
			return showThongBaoLoi(txtTen, "tên chỉ chứa các ký tự chữ cái");

		String diaChi = txtAddress.getText().trim();

		if (diaChi.length() <= 0)
			return showThongBaoLoi(txtAddress, "Vui lòng nhập địa chỉ");
		if (!diaChi.matches("^[0-9a-zA-ZÀ-ỹ \\,-]+$"))
			return showThongBaoLoi(txtAddress, "địa chỉ chỉ chứa các ký tự chữ cái");

		String soDienThoai = txtPhone.getText().trim();

		if (soDienThoai.length() <= 0)
			return showThongBaoLoi(txtPhone, "Vui lòng nhập số điện thoại");
		if(!soDienThoai.matches("^[0-9]{10}$"))
			return showThongBaoLoi(txtPhone, "Số điện thoại gồm 10 ký tự số");

		
		String email = txtEmail.getText().trim();
		
		if (email.length() > 0)
			if(!email.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$"))
				return showThongBaoLoi(txtEmail, "Email không hợp lệ");
		

		return true;
	}
}