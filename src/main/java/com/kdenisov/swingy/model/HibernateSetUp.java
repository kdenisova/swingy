package com.kdenisov.swingy.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class HibernateSetUp {
    private SessionFactory sessionFactory;

    public void setUp() {
        Configuration configuration = new Configuration();
        sessionFactory =  configuration.configure().buildSessionFactory();

        /*
        // A SessionFactory is set up once for an application
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        */
    }

    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public void testSession() {
        Session session = sessionFactory.openSession();


        Transaction transaction = session.beginTransaction();
        Hero hero = new Hero();
        hero.setId(1);
        hero.setName("Legolas");
        hero.setHeroClass(HeroClass.ELF);
        hero.setLevel(1);
        hero.setExperience(0);
        hero.setAttack(100);
        hero.setDefense(50);
        hero.setHitPoints(10);
        session.save(hero);
        transaction.commit();

        /*
        Transaction transaction = session.beginTransaction();
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("First");
        session.save(testEntity);
        transaction.commit();
         */

//        Criteria c = session.createCriteria(TestEntity.class);
//        c.add(Restrictions.like("name", "%irs%"));
//
//        List list = c.list();
//        if (list.size() > 0) {
//            System.out.println("list.get(0).toString() = " + list.get(0).toString());
//        }


//        Hero hero = new Hero();
//        hero.setName("Legolas");
      //  session.save(hero);
    }
}
