package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.TaiKhoanDAO;
import entities.TaiKhoan;
import jakarta.persistence.EntityManager;

public class TaiKhoanService extends UnicastRemoteObject implements TaiKhoanDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3642527358709481701L;
	private EntityManager entityManager;

	public TaiKhoanService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public boolean isTaiKhoan(TaiKhoan taiKhoan) throws RemoteException {
		List<TaiKhoan> dstk = entityManager
				.createQuery("SELECT tk FROM TaiKhoan tk WHERE tk.maNhanVien = :maNhanVien AND tk.matKhau = :matKhau",
						TaiKhoan.class)
				.setParameter("maNhanVien", taiKhoan.getNhanVien().getMaNhanVien()).setParameter("matKhau", taiKhoan.getMatKhau())
				.getResultList();
		if (dstk.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
		return entityManager.createQuery("SELECT tk FROM TaiKhoan tk", TaiKhoan.class).getResultList();
	}

	@Override
	public boolean create(TaiKhoan tk) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(tk);
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
	public boolean updateMatKhau(String maTK, String matKhauMoi) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			TaiKhoan tk = entityManager.find(TaiKhoan.class, maTK);
			tk.setMatKhau(matKhauMoi);
			entityManager.merge(tk);
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
	public boolean updateEmail(String maTK, String email) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			TaiKhoan tk = entityManager.find(TaiKhoan.class, maTK);
			tk.setEmail(email);
			entityManager.merge(tk);
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
	public boolean update(TaiKhoan tk) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(tk);
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
	public boolean delete(String maTK) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			TaiKhoan tk = entityManager.find(TaiKhoan.class, maTK);
			entityManager.remove(tk);
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
