package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.MauSacDAO;
import entities.MauSac;
import jakarta.persistence.EntityManager;

public class MauSacService extends UnicastRemoteObject implements MauSacDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -359381273609707520L;
	private EntityManager entityManager;

	public MauSacService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<MauSac> getAllMauSac() throws RemoteException {
		return entityManager.createQuery("select ms from MauSac ms", MauSac.class).getResultList();
	}

	@Override
	public boolean create(MauSac ms) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(ms);
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
	public MauSac getMauSacById(String id) throws RemoteException {
		return entityManager.find(MauSac.class, id);
	}

}
