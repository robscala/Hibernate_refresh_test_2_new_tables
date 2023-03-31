package org.example;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ManufacturerTest
{
    private static final long MANUFACTURER_ID = 364340L;
    private static final long PERSON_ID = 14532L;

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "refresh_test" );

        // Populate records in the database:
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Creating database records");
        ManufacturerComputerSystem computerSystem = new ManufacturerComputerSystem();
        Manufacturer manufacturer = new Manufacturer(computerSystem);
        manufacturer.addPerson(new Person("Henry"));
        entityManager.getTransaction().begin();
        entityManager.persist(manufacturer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void refresh_test() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Manufacturer manufacturer = entityManager.find(Manufacturer.class, 1L);
//        Manufacturer manufacturer = entityManager.find(Manufacturer.class, MANUFACTURER_ID);
        System.out.println("Refreshing manufacturer");
        entityManager.refresh(manufacturer);
        System.out.println("Calling manufacturer.getPeople()");
        List<Person> people = manufacturer.getPeople();
        System.out.println("Calling people.get(0)");
        Person person1 = people.get(0);
        System.out.println("Person is in EM: " + entityManager.contains(person1));
        System.out.println("Changing name");
        String newFirstName = "name1".equals(person1.getFirstName()) ? "name2" : "name1";
        entityManager.getTransaction().begin();
        person1.setFirstName(newFirstName);
        entityManager.getTransaction().commit();

        assertEquals("Get latest name", newFirstName, person1.getFirstName());

        entityManager.close();
    }
}