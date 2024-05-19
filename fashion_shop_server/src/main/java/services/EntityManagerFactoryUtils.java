package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtils {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EntityManagerFactoryUtils() {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA_ORM_MariaDB");
		entityManager = entityManagerFactory.createEntityManager();
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void closeEntityManager() {
		entityManager.close();
	}

	public void closeEntityManagerFactory() {
		entityManagerFactory.close();
	}
}
