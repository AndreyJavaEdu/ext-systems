package edu.javacourse.register.manager;

import edu.javacourse.register.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class PersonManager {
    public static void main(String[] args) {

//        sessionExample();
        jpaExample();


    }

    private static void jpaExample() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person p = new Person();
        p.setFirstName("Алексей");
        p.setLastName("Федоров");
        em.persist(p);
        System.out.println(p.getPersonId());
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        List list = em.createQuery("FROM Person").getResultList();
        list.forEach(p1-> System.out.println(p1));
        em.close();


    }

    private static void sessionExample() {
        SessionFactory sf = buildSessionFactory();

        System.out.println();
        System.out.println();
        System.out.println();

        Session session = sf.openSession();
        session.getTransaction().begin();
        Person p = new Person();
        p.setFirstName("Василий");
        p.setLastName("Сидоров");
        session.persist(p);
        Object identifier = session.getIdentifier(p);

        session.getTransaction().commit();
        session.close();

        session = sf.openSession();
        Person person = session.get(Person.class, identifier);
        System.out.println(person);
        session.close();
        System.out.println();
        System.out.println();
        System.out.println();
        session = sf.openSession();
        List<Person> list = session.createQuery("FROM Person", Person.class).list();
        list.forEach(p1 -> System.out.println(p1));
    }


    public static SessionFactory buildSessionFactory (){
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            return sessionFactory;
        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
