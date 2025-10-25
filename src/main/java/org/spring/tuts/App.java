package org.spring.tuts;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.spring.tuts.entities.Employee1;
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
//            Product prod1 = new Product("MacBook");
//            prod1.setId(2);
//            em.persist(prod1);

            Employee1 employee1 = new Employee1();
            employee1.setName("Jacob");
            employee1.setAddress("New York subway");

            em.persist(employee1);
            Employee1 employee2 = em.find(Employee1.class, 1);
            System.out.println("Employee name: " + employee2.getName() + ", address: " + employee2.getAddress());

            employee2.setName("Jack the Sparrow");

            //            em.persist();   -> Adding an entity in the context
//            em.find()       -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();    -> Marking entity for removal
//            em.merge();     -> Merges an entity from outside the context to the context.
//            em.refresh();   -> Mirror the context from the database
//            em.detach();    -> Taking the entity out of the context
//            em.getReference()

            em.remove(em.find(Employee1.class, 2));
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
