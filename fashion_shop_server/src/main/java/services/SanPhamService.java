package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.SanPhamDAO;
import entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class SanPhamService extends UnicastRemoteObject implements SanPhamDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1737014721103565687L;
	private EntityManager entityManager;

	public SanPhamService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<SanPham> getAllSanPham() throws RemoteException {
		return entityManager.createQuery("select sp FROM SanPham sp", SanPham.class).getResultList();
	}

	@Override
	public List<SanPham> getAllSanPhamTheoNhaCungCap(String nhaCungCap) throws RemoteException {
		return entityManager.createQuery("select sp FROM SanPham sp where sp.nhaCungCap = :nhaCungCap or "
				+ "sp.nhaCungCap = ''", SanPham.class)
				.setParameter("nhaCungCap", nhaCungCap).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SanPham> Find(String sql, ArrayList<String> token) throws RemoteException {
		Query query = entityManager.createNativeQuery(sql, SanPham.class);
		for (int i = 0; i < token.size(); i++) {
			query.setParameter("a"+i, token.get(i));
		}
		return query.getResultList();
	}

	@Override
	public boolean UpdateSoLuong(String masp, int soluong) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			SanPham sp = entityManager.find(SanPham.class, masp);
			sp.setSoLuong(soluong);
			entityManager.merge(sp);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateNhaCungCap(String masp, String nhaCungCap) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			SanPham sp = entityManager.find(SanPham.class, masp);
			sp.setNhaCungCap(nhaCungCap);
			entityManager.merge(sp);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean create(SanPham sp) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(sp);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Delete(String maSP) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			SanPham sp = entityManager.find(SanPham.class, maSP);
			entityManager.remove(sp);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Update(SanPham sp) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(sp);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public SanPham getSanPham(String maSP) throws RemoteException {
		return entityManager.find(SanPham.class, maSP);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoNgay(int ngay, int thang, int nam, String loaiSP) throws RemoteException {
		String sql = "";
		Query query = null;
		if (loaiSP.equals("")) {
			sql = "select sp.maSanPham, sp.tenSanPham, sum(cthd.soLuong), cthd.donGia, sum(cthd.soLuong)*cthd.donGia from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "where Day(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by sp.maSanPham, sp.tenSanPham,cthd.donGia";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);

		} else {
			sql = "select sp.maSanPham, sp.tenSanPham, sum(cthd.soLuong), cthd.donGia, sum(cthd.soLuong)*cthd.donGia from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where Day(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ? and lsp.tenLoaiSanPham = ?\r\n"
					+ "group by sp.maSanPham, sp.tenSanPham,cthd.donGia";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);
			query.setParameter(4, loaiSP);

		}
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoThang(int thang, int nam, String loaiSP) throws RemoteException {
		String sql = "";
		Query query = null;
		if (loaiSP.equals("")) {
			sql = "select sp.maSanPham, sp.tenSanPham, sum(cthd.soLuong), cthd.donGia, sum(cthd.soLuong)*cthd.donGia from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by sp.maSanPham, sp.tenSanPham,cthd.donGia";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, thang);
			query.setParameter(2, nam);

		} else {
			sql = "select sp.maSanPham, sp.tenSanPham, sum(cthd.soLuong), cthd.donGia, sum(cthd.soLuong)*cthd.donGia from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ? and lsp.tenLoaiSanPham = ?\r\n"
					+ "group by sp.maSanPham, sp.tenSanPham,cthd.donGia";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, thang);
			query.setParameter(2, nam);
			query.setParameter(3, loaiSP);
		}
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoNam(int nam, String loaiSP) throws RemoteException {
		String sql = "";
		Query query = null;
		if (loaiSP.equals("")) {
			sql = "select sp.maSanPham, sp.tenSanPham, sum(cthd.soLuong), cthd.donGia, sum(cthd.soLuong)*cthd.donGia from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n" + "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by sp.maSanPham, sp.tenSanPham,cthd.donGia";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
		} else{

			sql = "select sp.maSanPham, sp.tenSanPham, sum(cthd.soLuong), cthd.donGia, sum(cthd.soLuong)*cthd.donGia from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where YEAR(hd.ngayLap) = ? and lsp.tenLoaiSanPham = ?\r\n"
					+ "group by sp.maSanPham, sp.tenSanPham,cthd.donGia";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);
			query.setParameter(2, loaiSP);
		}
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoDTLSPTheoNgay(int ngay, int thang, int nam) throws RemoteException {
		String sql = "";
		Query query = null;
			sql = "select lsp.tenLoaiSanPham, sum(cthd.soLuong*cthd.donGia) from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where Day(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by lsp.tenLoaiSanPham";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);

		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoDTLSPTheoThang(int thang, int nam) throws RemoteException {
		String sql = "";
		Query query = null;
			sql = "select lsp.tenLoaiSanPham, sum(cthd.soLuong*cthd.donGia) from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by lsp.tenLoaiSanPham";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, thang);
			query.setParameter(2, nam);

		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoDTLSPTheoNam(int nam) throws RemoteException {
		String sql = "";
		Query query = null;
			sql = "select lsp.tenLoaiSanPham, sum(cthd.soLuong*cthd.donGia) from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by lsp.tenLoaiSanPham";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);

		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoSLLSPTheoNgay(int ngay, int thang, int nam) throws RemoteException {
		String sql = "";
		Query query = null;
			sql = "select lsp.tenLoaiSanPham, sum(cthd.soLuong) from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where Day(hd.ngayLap) = ? and MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by lsp.tenLoaiSanPham";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, ngay);
			query.setParameter(2, thang);
			query.setParameter(3, nam);

		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoSLLSPTheoThang(int thang, int nam) throws RemoteException {
		String sql = "";
		Query query = null;
			sql = "select lsp.tenLoaiSanPham, sum(cthd.soLuong) from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where MONTH(hd.ngayLap) = ? and YEAR(hd.ngayLap) = ?\r\n"
					+ "group by lsp.tenLoaiSanPham";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, thang);
			query.setParameter(2, nam);

		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> thongKeSanPhamTheoSLLSPTheoNam(int nam) throws RemoteException {
		String sql = "";
		Query query = null;
			sql = "select lsp.tenLoaiSanPham, sum(cthd.soLuong) from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
					+ "join SanPham sp on sp.maSanPham = cthd.maSanPham\r\n"
					+ "join LoaiSanPham lsp on lsp.maLoaiSanPham = sp.maLoaiSanPham\r\n"
					+ "where YEAR(hd.ngayLap) = ?\r\n"
					+ "group by lsp.tenLoaiSanPham";
			query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nam);

		return query.getResultList();
	}

}
