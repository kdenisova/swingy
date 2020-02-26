package com.kdenisov.swingy.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

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

    public void saveHero(String name, HeroClass heroClass, Artefact artefact, int attack, int defense, int hitPoints) {
        setUp();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        Hero hero = new Hero();
        hero.setName(name);
        hero.setHeroClass(heroClass);
        hero.setLevel(1);
        hero.setExperience(0);
        hero.setAttack(attack);
        hero.setDefense(defense);
        hero.setHitPoints(hitPoints);
        session.save(hero);

        String hql = "FROM Hero order by id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        Hero last = (Hero)query.uniqueResult();

        ArtefactsEntity artefactsEntity = new ArtefactsEntity();
        artefactsEntity.setHeroId(last.getId());
        artefactsEntity.setArtefact(artefact);
        session.save(artefactsEntity);
        transaction.commit();

        tearDown();

//        Criteria c = session.createCriteria(TestEntity.class);
//        c.add(Restrictions.like("name", "%irs%"));
//
//        List list = c.list();
//        if (list.size() > 0) {
//            System.out.println("list.get(0).toString() = " + list.get(0).toString());
//        }


    }
}
