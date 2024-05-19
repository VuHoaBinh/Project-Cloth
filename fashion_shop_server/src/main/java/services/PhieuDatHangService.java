package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.PhieuDatHangDAO;
import entities.PhieuDatHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class PhieuDatHangService extends UnicastRemoteObject implements PhieuDatHangDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2222912976171496215L;
	private EntityManager entityManager;

	public PhieuDatHangService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<PhieuDatHang> getAllPhieuDatHang() throws RemoteException {
		return entityManager.createQuery("select pdh from PhieuDatHang pdh", PhieuDatHang.class).getResultList();
	}

	@Override
	public boolean create(PhieuDatHang pdh) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(pdh);
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
	public boolean Delete(String maPDH) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			PhieuDatHang pdh = entityManager.find(PhieuDatHang.class, maPDH);
			entityManager.remove(pdh);
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
	public PhieuDatHang getPhieuDatHang(String maPDH) throws RemoteException {
		return entityManager.find(PhieuDatHang.class, maPDH);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findPhieuDatHang(String sql, ArrayList<String> token) throws RemoteException {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < token.size(); i++) {
			query.setParameter("a"+i, token.get(i));
		}
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> loadDuLieuPhieuDatHang() throws RemoteException {
		String sql = "select pdh.maPhieuDatHang,pdh.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai, sum(ctpdh.donGia*ctpdh.soLuong) from PhieuDatHang pdh\r\n"
				+ "join KhachHang kh on pdh.khachHang = kh.maKhachHang\r\n"
				+ "join NhanVien nv on pdh.nguoiLap = nv.maNhanVien\r\n"
				+ "join ChiTietPhieuDatHang ctpdh on pdh.maPhieuDatHang = ctpdh.maPhieuDatHang\r\n"
				+ "group by pdh.maPhieuDatHang,pdh.ngayLap, nv.tenNhanVien, kh.tenKhachHang, kh.soDienThoai\r\n"
				+ "order by pdh.ngayLap DESC";
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
}
