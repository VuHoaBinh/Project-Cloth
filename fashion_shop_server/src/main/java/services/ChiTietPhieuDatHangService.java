package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ChiTietDatHangDAO;
import entities.ChiTietPhieuDatHang;
import jakarta.persistence.EntityManager;

public class ChiTietPhieuDatHangService extends UnicastRemoteObject implements ChiTietDatHangDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8039105475086943718L;
	private EntityManager entityManager;

	public ChiTietPhieuDatHangService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<ChiTietPhieuDatHang> getAllChiTietPhieuDatHang(String maPDH) throws RemoteException {
		return entityManager.createQuery("select ctpdh from ChiTietPhieuDatHang ctpdh where ctpdh.phieuDatHang.maPhieuDatHang = :maPhieuDatHang", ChiTietPhieuDatHang.class)
				.setParameter("maPhieuDatHang", maPDH)
				.getResultList();
	}

	@Override
	public boolean create(ChiTietPhieuDatHang ctpdh) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(ctpdh);
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
