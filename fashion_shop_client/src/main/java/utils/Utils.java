package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.Locale;

import javax.swing.ImageIcon;

public class Utils {
	private static final Class<?> _class = Utils.class;
	private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Color labelTextField = new Color(150, 150, 150);
	public static final Color primaryColor = new Color(140, 177, 180);
	public static final Color secondaryColor = new Color(203, 239, 255);
	public static final String trangChuMenuItem = "Trang chủ";
	public static final String quanLySanPhamMenuItem = "Quản lý sản phẩm";
	public static final String timKiemSanPhamMenuItem = "Tìm kiếm sản phẩm";
	public static final String thongKeSanPhamMenuItem = "Thống kê sản phẩm";
	public static final String thongKeDoanhThuMenuItem = "Thống kê doanh thu";
	public static final String quanLyNhanVienMenuItem = "Quản lý nhân viên";
	public static final String timKiemNhanVienMenuItem = "Tìm kiếm nhân viên";
	public static final String quanLyTaiKhoanMenuItem = "Quản lý tài khoản";
	public static final String datHangMenuItem = "Đặt hàng";
	public static final String XemDanhSachDatHangMenuItem = "Xem danh sách đặt hàng";
	public static final String lapPhieuNhapMenuItem = "Lập phiếu nhập";
	public static final String xemDanhSachPhieuNhapMenuItem = "Xem danh sách phiếu nhập";
	public static final String guiEmailMenuItem = "Gửi email";
	
	public static final String lapHoaDonMenuItem = "Lập hóa đơn";
	public static final String xemDanhSachHoaDonMenuItem = "Xem danh sách hóa đơn";
	public static final String quanLyKhuyenMaiMenuItem = "Quản lý khuyến mãi";
	public static final String xemDanhSachKhuyenMaiMenuItem = "Xem danh sách khuyến mãi";
	public static final String xemThongTinCaNhanMenuItem = "Xem thông tin cá nhân";
	
	
	public static final String quanLyKhachHangMenuItem = "Quản lý khách hàng";
	public static final String timKiemKhachHangMenuItem = "Tìm kiếm khách hàng";
	public static final String thongKeKhachHangMenuItem = "Thống kê khách hàng";
	
	
	
	public static final String troGiupItem = "Trợ giúp";
	public static final String dangXuatMenuItem = "Đăng xuất";
	public static final String thoatMenuItem = "Thoát";

	/**
	 * Get ImageIcon
	 * 
	 * @param iconName
	 * @return
	 */
	public static ImageIcon getImageIcon(String iconName) {
//		URL location = _class.getResource("/"+ iconName);
//		if (location != null) {
//			return new ImageIcon(location);
//		} else {
//			System.err.println("Resource not found: " + iconName);
//			return null;
//		}
		return new ImageIcon(System.getProperty("user.dir") + "\\Icon\\" + iconName);
	}

	/**
	 * Get màu RGBA
	 * 
	 * @param color
	 * @param alpha
	 * @return
	 */
	public static Color getOpacity(Color color, float alpha) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		int a = color.getAlpha();

		return new Color(r, g, b, Math.round(a * alpha));
	}

	/**
	 * Get màu RGBA
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 * @return
	 */
	public static Color getRGBA(int r, int g, int b, float a) {
		return new Color(r, g, b, Math.round(a * 255));
	}

	/**
	 * Get chiều cao màn hình
	 * 
	 * @return
	 */
	public static int getScreenHeight() {
		return (int) dimension.getHeight() + 7;
	}

	/**
	 * Get độ dài màn hình
	 * 
	 * @return
	 */
	public static int getScreenWidth() {
		return (int) dimension.getWidth() + 14;
	}
	
	/**
	 * Get left căn giữa component với màn hình
	 * 
	 * @param width
	 * @return
	 */
	public static int getLeft(int width) {
		return (int) Math.ceil((Utils.getScreenWidth() - width - 14) / 2);
	}

	/**
	 * Get left căn giữa component với một width bất kỳ
	 * 
	 * @param widthParent
	 * @param width
	 * @return
	 */
	public static int getLeft(int widthParent, int width) {
		return (int) Math.ceil((widthParent - width) / 2);
	}
	
	/**
	 * Get chiều cao phần header
	 * 
	 * @return
	 */
	public static int getHeaderHeight() {
		return 65;
	}
	
	/**
	 * Get chiều cao phần thân
	 * 
	 * @return
	 */
	public static int getBodyHeight() {
		return getScreenHeight() - getHeaderHeight();
	}
	/**
	 * Convert int to String
	 * 
	 * @param number
	 * @return
	 */
	public static String convertIntToString(int number) {
		if (number < 10)
			return "0" + number;
		return number + "";
	}
	
	/**
	 * Convert LocalTime to String
	 * 
	 * @param time
	 * @return
	 */
	public static String convertLocalTimeToString(LocalTime time) {
		return String.format("%s:%s", convertIntToString(time.getHour()), convertIntToString(time.getMinute()));
	}
	public static String formatTienTe(double soTien) {
		Locale locale = new Locale("vi", "vn");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(soTien);
	}
	/**
	 * Get các từ tiếng Việt hoa có dấu
	 * 
	 * @return
	 */
	public static String getVietnameseDiacriticCharacters() {
		return "ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";
	}

	/**
	 * Get các từ tiếng Việt thường có dấu
	 * 
	 * @return
	 */
	public static String getVietnameseDiacriticCharactersLower() {
		return getVietnameseDiacriticCharacters().toLowerCase();
	}
}