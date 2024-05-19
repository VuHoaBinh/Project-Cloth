package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.KhachHangDAO;
import entities.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class KhachHangService extends UnicastRemoteObject implements KhachHangDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5370822227591460198L;
	private EntityManager entityManager;

	public KhachHangService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<KhachHang> getAllKhachHang() throws RemoteException {
		return entityManager.createQuery("select kh FROM KhachHang kh", KhachHang.class).getResultList();
	}

	@Override
	public boolean create(KhachHang kh) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(kh);
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
	public boolean update(KhachHang kh) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(kh);
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
	public boolean delete(String maKhachHang) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.find(KhachHang.class, maKhachHang));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<KhachHang> Find(String sql, ArrayList<String> token) throws RemoteException {
		Query query =  entityManager.createNativeQuery(sql, KhachHang.class);
		for (int i = 0; i < token.size(); i++) {
			query.setParameter("a"+i, token.get(i));
		}
		return query.getResultList();
	}
	@Override
	public KhachHang getKhachHang(String maKhachHang) throws RemoteException {
		return entityManager.find(KhachHang.class, maKhachHang);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> loadDuLieuKhachHang() throws RemoteException {
		String sql = "select kh.tenKhachHang, kh.soDienThoai, kh.email, hd.maHoaDon, hd.ngayLap from HoaDon hd\r\n"
				+ "join KhachHang kh on hd.khachHang = kh.maKhachHang\r\n"
				+ "Order by ngayLap DESC";
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}

}
