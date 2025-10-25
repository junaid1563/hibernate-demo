package org.spring.tuts;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.spring.tuts.entities.Employee1;
import org.spring.tuts.entities.Product;
import org.spring.tuts.persistence.CustomPersistenceUnitInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.hbm2ddl.auto", "create");
        try (EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), map)) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Employee1 employee1 = new Employee1();
            employee1.setName("Ron");
            employee1.setAddress("Jakarta");
            employee1.setDateOfBirth(new Date("03/09/1999"));
            employee1.setGender(Employee1.Gender.MALE);
            em.persist(employee1);
//            System.out.println("Employee before update = " + em.find(Employee1.class, 1));
//            employee1.setName("Jack");
            System.out.println("Employee after update = " + employee1);
//
//            em.refresh(employee1);
//            System.out.println("Employee after refresh = " + em.find(Employee1.class, 1));

            //            em.persist();   -> Adding an entity in the context
            //            em.find()       -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
            //            em.remove();    -> Marking entity for removal
            //            em.merge();     -> Merges an entity from outside the context to the context.
            //            em.refresh();   -> Mirror the context from the database
            //            em.detach();    -> Taking the entity out of the context
            //            em.getReference()
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
