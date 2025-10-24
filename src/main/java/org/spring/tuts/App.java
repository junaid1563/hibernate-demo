package org.spring.tuts;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.spring.tuts.entities.Product;
import org.spring.tuts.persistence.CustomPersistenceUnitInfo;

import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try (EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>())) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Product prod1 = new Product("MacBook");
            prod1.setId(2);
            em.persist(prod1);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
