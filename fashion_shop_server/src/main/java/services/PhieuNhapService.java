package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.PhieuNhapDAO;
import entities.PhieuNhap;
import jakarta.persistence.EntityManager;

public class PhieuNhapService extends UnicastRemoteObject implements PhieuNhapDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 213216694351268565L;
	private EntityManager entityManager;

	public PhieuNhapService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<PhieuNhap> getAllPhieuNhap() throws RemoteException {
		return entityManager.createQuery("select pn from PhieuNhap pn order by pn.ngayLap DESC", PhieuNhap.class).getResultList();
	}

	@Override
	public boolean create(PhieuNhap pn) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(pn);
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
	public PhieuNhap getPhieuNhapById(String id) throws RemoteException {
		return entityManager.find(PhieuNhap.class, id);
	}

}
