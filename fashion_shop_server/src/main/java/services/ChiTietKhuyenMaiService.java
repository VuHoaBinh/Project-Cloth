package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ChiTietKhuyenMaiDAO;
import entities.ChiTietKhuyenMai;
import jakarta.persistence.EntityManager;

public class ChiTietKhuyenMaiService extends UnicastRemoteObject implements ChiTietKhuyenMaiDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7366395145854660557L;
	private EntityManager entityManager;

	public ChiTietKhuyenMaiService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<ChiTietKhuyenMai> getAllChiTietKhuyenMai(String maKhuyenMai) throws RemoteException {
		return entityManager.createQuery("select ctkm from ChiTietKhuyenMai ctkm where ctkm.khuyenMai.maKhuyenMai = :maKhuyenMai", ChiTietKhuyenMai.class)
				.setParameter("maKhuyenMai", maKhuyenMai).getResultList();
	}

	@Override
	public List<ChiTietKhuyenMai> getAllChiTietKhuyenMai() throws RemoteException {
		return entityManager.createQuery("select ctkm from ChiTietKhuyenMai ctkm", ChiTietKhuyenMai.class).getResultList();
	}

	@Override
	public boolean create(ChiTietKhuyenMai ctkm) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(ctkm);
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
	public boolean Update(ChiTietKhuyenMai ctkm) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(ctkm);
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
	public boolean Delete(String maKM, String maSP) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			ChiTietKhuyenMai ctkm = entityManager.createQuery(
					"select ctkm from ChiTietKhuyenMai ctkm where ctkm.khuyenMai.maKhuyenMai = :maKhuyenMai and ctkm.sanPham.maSanPham = :maSanPham",
					ChiTietKhuyenMai.class).setParameter("maKhuyenMai", maKM).setParameter("maSanPham", maSP)
					.getSingleResult();
			entityManager.remove(ctkm);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean Delete(String maKM) throws RemoteException {
		int rs = 0;
		List<ChiTietKhuyenMai> dsCTKM = entityManager
				.createQuery("select ctkm from ChiTietKhuyenMai ctkm where ctkm.khuyenMai.maKhuyenMai = :maKhuyenMai",
						ChiTietKhuyenMai.class)
				.setParameter("maKhuyenMai", maKM).getResultList();
		for (ChiTietKhuyenMai chiTietKhuyenMai : dsCTKM) {
			try {
                entityManager.getTransaction().begin();
                entityManager.remove(chiTietKhuyenMai);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
				if (entityManager.getTransaction().isActive()) {
					entityManager.getTransaction().rollback();
				}
				e.printStackTrace();
				rs+=1;
            }
		}
		if(rs != 0)
			return true;
		else
			return false;
	}

}
