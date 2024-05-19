package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonDAO;
import entities.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class HoaDonService extends UnicastRemoteObject implements HoaDonDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1065166299467889067L;
	private EntityManager entityManager;

	public HoaDonService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}

	@Override
	public List<HoaDon> getAllHoaDon() throws RemoteException {
		return entityManager.createQuery("select hd from HoaDon hd", HoaDon.class).getResultList();
	}

	@Override
	public boolean create(HoaDon hd) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(hd);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public HoaDon getHoaDonById(String id) throws RemoteException {
		return entityManager.find(HoaDon.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findHoaDon(String sql, ArrayList<String> token) throws RemoteException {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < token.size(); i++) {
			query.setParameter("a" + i, token.get(i));
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> loadDuLieuHoaDon() throws RemoteException {
		String sql = "select hd.maHoaDon, hd.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai, sum(cthd.donGia*cthd.soLuong) from HoaDon hd join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
				+ "join NhanVien nv on nv.maNhanVien = hd.nguoiLap\r\n"
				+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
				+ "group by hd.maHoaDon, hd.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai\r\n"
				+ "order by hd.ngayLap DESC";
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeDoanhThuTheoTuan(int tuan, int thang, int nam, String tenNV, String tenKH)
			throws RemoteException {
			String sql = "";
			Query query = null;
			if (tenKH.equals("") && tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, tuan);
				query.setParameter(2, thang);
				query.setParameter(3, nam);

			} else if (!tenKH.equals("") && tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
						+ "having kh.tenKhachHang = ?";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, tuan);
				query.setParameter(2, thang);
				query.setParameter(3, nam);
				query.setParameter(4, tenKH);
			} else if (tenKH.equals("") && !tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
						+ "having nv.tenNhanVien = ?";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, tuan);
				query.setParameter(2, thang);
				query.setParameter(3, nam);
				query.setParameter(4, tenNV);
			} else if (!tenKH.equals("") && !tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where ceiling(cast(datepart(dd, hd.ngayLap) as numeric(38,8))/7) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
						+ "having kh.tenKhachHang = ? and nv.tenNhanVien = ?";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, tuan);
				query.setParameter(2, thang);
				query.setParameter(3, nam);
				query.setParameter(4, tenKH);
			}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeDoanhThuTheoThang(int thang, int nam, String tenNV, String tenKH)
			throws RemoteException {
		String sql = "";
		Query query = null;
		if (tenKH.equals("") && tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, thang);
				query.setParameter(2, nam);
		} else if (!tenKH.equals("") && tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
						+ "having kh.tenKhachHang  = ?";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, thang);
				query.setParameter(2, nam);
				query.setParameter(3, tenKH);
		} else if (tenKH.equals("") && !tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
						+ "having nv.tenNhanVien = ?";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, thang);
				query.setParameter(2, nam);
				query.setParameter(3, tenNV);
		} else if (!tenKH.equals("") && !tenNV.equals("")) {
				sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
						+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
						+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
						+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
						+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
						+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
						+ "having kh.tenKhachHang = ? and nv.tenNhanVien = ?";
				query = entityManager.createNativeQuery(sql);
				query.setParameter(1, thang);
				query.setParameter(2, nam);
				query.setParameter(3, tenKH);
				query.setParameter(4, tenNV);	
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeDoanhThuTheoNam(int nam, String tenNV, String tenKH) throws RemoteException {
		String sql = "";
		Query query = null;
		if (tenKH.equals("") && tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);

		} else if (!tenKH.equals("") && tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
					+ "having kh.tenKhachHang = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, tenKH);

		} else if (tenKH.equals("") && !tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
					+ "having nv.tenNhanVien = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, tenNV);

		} else if (!tenKH.equals("") && !tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
					+ "having kh.tenKhachHang = ? and nv.tenNhanVien = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, tenKH);
			query.setParameter(3, tenNV);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeDoanhThuTheoNgay(int ngay, int thang, int nam, String tenNV, String tenKH)
			throws RemoteException {
		String sql = "";
		Query query = null;
		if (tenKH.equals("") && tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where DAY(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);

		} else if (!tenKH.equals("") && tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where DAY(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
					+ "having kh.tenKhachHang = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);
			query.setParameter(4, tenKH);
		} else if (tenKH.equals("") && !tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where DAY(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
					+ "having nv.tenNhanVien = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);
			query.setParameter(4, tenNV);
		} else if (!tenKH.equals("") && !tenNV.equals("")) {
			sql = "select hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap, sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where DAY(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by hd.maHoaDon, nv.tenNhanVien, kh.tenKhachHang, hd.ngayLap\r\n"
					+ "having kh.tenKhachHang = ? and nv.tenNhanVien = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);
			query.setParameter(4, tenKH);

		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeDoanhThuTheoNamChart(int nam, String tenNV, String tenKH) throws RemoteException {
		String sql = "";
		Query query = null;
		if (tenKH.equals("") && tenNV.equals("")) {
			sql = "select Month(hd.ngayLap), sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by Month(hd.ngayLap)";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);

		} else if (!tenKH.equals("") && tenNV.equals("")) {
			sql = "select Month(hd.ngayLap), sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by kh.tenKhachHang, Month(hd.ngayLap)\r\n"
					+ "having kh.tenKhachHang = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, tenKH);

		} else if (tenKH.equals("") && !tenNV.equals("")) {
			sql = "select Month(hd.ngayLap), sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by nv.tenNhanVien, Month(hd.ngayLap)\r\n"
					+ "having nv.tenNhanVien = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, tenNV);

		} else if (!tenKH.equals("") && !tenNV.equals("")) {
			sql = "select Month(hd.ngayLap), sum(soLuong*cthd.donGia) from HoaDon hd\r\n"
					+ "join NhanVien nv on hd.nguoiLap = nv.maNhanVien\r\n"
					+ "join KhachHang kh on kh.maKhachHang = hd.khachHang\r\n"
					+ "join ChiTietHoaDon cthd on cthd.maHoaDon = hd.maHoaDon\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by nv.tenNhanVien, kh.tenKhachHang, Month(hd.ngayLap)\r\n"
					+ "having kh.tenKhachHang = ? and nv.tenNhanVien = ?";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, tenKH);
			query.setParameter(3, tenNV);
		}
		return query.getResultList();
	}

}
