package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ChiTietHoaDonDAO;
import entities.ChiTietHoaDon;
import jakarta.persistence.EntityManager;

public class ChiTietHoaDonService extends UnicastRemoteObject implements ChiTietHoaDonDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8527084986233256726L;
	private EntityManager entityManager;

	public ChiTietHoaDonService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<ChiTietHoaDon> getAllChiTietHoaDon(String maHoaDon) throws RemoteException {
		return entityManager.createQuery("select cthd from ChiTietHoaDon cthd where cthd.hoaDon.maHoaDon = :maHoaDon", ChiTietHoaDon.class)
				.setParameter("maHoaDon", maHoaDon).getResultList();
	}

	@Override
	public List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException {
		return entityManager.createQuery("select cthd from ChiTietHoaDon cthd", ChiTietHoaDon.class).getResultList();
	}

	@Override
	public boolean create(ChiTietHoaDon cthd) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cthd);
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

}
