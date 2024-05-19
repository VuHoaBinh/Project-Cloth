package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ChiTietPhieuNhapDAO;
import entities.ChiTietPhieuNhap;
import jakarta.persistence.EntityManager;

public class ChiTietPhieuNhapService extends UnicastRemoteObject implements ChiTietPhieuNhapDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4812993998146035972L;
	private EntityManager entityManager;

	public ChiTietPhieuNhapService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}

	@Override
	public List<ChiTietPhieuNhap> getAllChiTietHoaDon(String maPhieuLap) throws RemoteException {
		return entityManager
				.createQuery("select ctpn from ChiTietPhieuNhap ctpn where ctpn.phieuNhap.maPhieuNhap = :maPhieuNhap",
						ChiTietPhieuNhap.class)
				.setParameter("maPhieuNhap", maPhieuLap).getResultList();
	}

	@Override
	public boolean create(ChiTietPhieuNhap ctpn) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(ctpn);
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

}
