package com.kdenisov.swingy.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateManager {
    private static SessionFactory sessionFactory;
    private static HibernateManager hibernateManager;

    private HibernateManager() {
    }

    public static HibernateManager getHibernateManager() {
        if (hibernateManager == null) {
            hibernateManager = new HibernateManager();
            hibernateManager.setUp();
        }
        return hibernateManager;
    }

    public void setUp() {
        Configuration configuration = new Configuration();
        sessionFactory =  configuration.configure().buildSessionFactory();
    }

    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public void saveHero(String name, HeroClass heroClass, Artifact artifact, int attack, int defense, int hitPoints) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            HeroEntity hero = new HeroEntity();
            hero.setName(name);
            hero.setHeroClass(heroClass);
            hero.setLevel(1);
            hero.setExperience(0);
            hero.setAttack(attack);
            hero.setDefense(defense);
            hero.setHitPoints(hitPoints);
            session.save(hero);

            String hql = "FROM HeroEntity order by id DESC";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            HeroEntity last = (HeroEntity)query.uniqueResult();

            ArtifactsEntity artifactsEntity = new ArtifactsEntity();
            artifactsEntity.setHeroId(last.getId());
            artifactsEntity.setArtifact(artifact);
            session.save(artifactsEntity);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public HeroEntity getNewHero() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String hql = "FROM HeroEntity order by id DESC";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            HeroEntity heroEntity = (HeroEntity)query.uniqueResult();

            return heroEntity;
        } finally {
            session.close();
        }
    }

    @SuppressWarnings({"rawtypes", "TryFinallyCanBeTryWithResources"})
    public List<HeroEntity> getListHeroes() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            // Transaction transaction = session.beginTransaction();
            String hql = "FROM HeroEntity";
            Query query = session.createQuery(hql);
            List<HeroEntity> heroEntities = query.list();

            // transaction.commit();
            //tearDown();

//            List<Hero> heroes = new ArrayList<>();
//            for (HeroEntity heroEntity : heroEntities) {
//                Hero hero = HeroFactory.getInstance().buildHero(heroEntity);
//                heroes.add(hero);
//            }

            return heroEntities;
        } finally {
            session.close();
        }
    }

    public void updateHero(Hero hero) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE HeroEntity SET x =" + hero.getX() + " WHERE id =" + hero.getId();
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();

        } finally {
            session.close();
        }
    }
}
