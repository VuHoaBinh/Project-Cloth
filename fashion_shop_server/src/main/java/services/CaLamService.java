package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.CaLamDAO;
import entities.CaLam;
import jakarta.persistence.EntityManager;

public class CaLamService extends UnicastRemoteObject implements CaLamDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8930073659026656450L;
	private EntityManager entityManager;

	public CaLamService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<CaLam> getAllCaLam() throws RemoteException {
		return entityManager.createQuery("select cl from CaLam cl", CaLam.class).getResultList();
	}
	@Override
	public CaLam getCaLam(String id) throws RemoteException {
		return entityManager.find(CaLam.class, id);
	}

}
