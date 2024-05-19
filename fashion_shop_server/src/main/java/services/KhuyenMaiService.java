package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.KhuyenMaiDAO;
import entities.KhuyenMai;
import jakarta.persistence.EntityManager;

public class KhuyenMaiService extends UnicastRemoteObject implements KhuyenMaiDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5991882777952396938L;
	private EntityManager entityManager;

	public KhuyenMaiService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<KhuyenMai> getAllKhuyenMai() throws RemoteException {
		return entityManager.createQuery("Select km from KhuyenMai km", KhuyenMai.class).getResultList();
	}

	@Override
	public boolean create(KhuyenMai km) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(km);
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
	public boolean Delete(String maKM) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.find(KhuyenMai.class, maKM));
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
	public boolean Update(KhuyenMai km) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(km);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

}
