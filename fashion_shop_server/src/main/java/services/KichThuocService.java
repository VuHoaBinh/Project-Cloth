package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.KichThuocDAO;
import entities.KichThuoc;
import jakarta.persistence.EntityManager;

public class KichThuocService extends UnicastRemoteObject implements KichThuocDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3001337348342731917L;
	private EntityManager entityManager;

	public KichThuocService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<KichThuoc> getAllKichThuoc() throws RemoteException {
		return entityManager.createQuery("SELECT k FROM KichThuoc k", KichThuoc.class).getResultList();
	}

	@Override
	public boolean create(KichThuoc kt) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(kt);
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
	public KichThuoc getKichThuocById(String id) throws RemoteException {
		return entityManager.find(KichThuoc.class, id);
	}

}
