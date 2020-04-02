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

    public void saveHero(String name, HeroClass heroClass, Artifact artifact, int attack, int defense, int hitPoints, int y, int x) {
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
            hero.setY(y);
            hero.setX(x);
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
            String hql = "UPDATE HeroEntity SET y =" + hero.getY() + ", x = " + hero.getX() + " WHERE id =" + hero.getId();
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();

        } finally {
            session.close();
        }
    }

    public void createVillains() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
/*
            VillainEntity villain = new VillainEntity();
            villain.setName("Centaur");
            villain.setLevel(1);
            villain.setAttack(50);
            villain.setDefense(30);
            villain.setHitPoints(10);
            session.save(villain);


            VillainEntity v1 = new VillainEntity();
            v1.setName("Cyclops");
            v1.setLevel(3);
            v1.setAttack(100);
            v1.setDefense(80);
            v1.setHitPoints(50);
            session.save(v1);
            transaction.commit();


            VillainEntity villain3 = new VillainEntity();
            villain3.setName("Goblin");
            villain3.setLevel(1);
            villain3.setAttack(10);
            villain3.setDefense(5);
            villain3.setHitPoints(4);
            session.save(villain3);

            VillainEntity v4 = new VillainEntity();
            v4.setName("Golem");
            v4.setLevel(1);
            v4.setAttack(20);
            v4.setDefense(10);
            v4.setHitPoints(7);
            session.save(v4);

            VillainEntity villain2 = new VillainEntity();
            villain2.setName("Ghost");
            villain2.setLevel(1);
            villain2.setAttack(10);
            villain2.setDefense(5);
            villain2.setHitPoints(4);
            session.save(villain2);


            VillainEntity v3 = new VillainEntity();
            v3.setName("Giant");
            v3.setLevel(2);
            v3.setAttack(25);
            v3.setDefense(15);
            v3.setHitPoints(10);
            session.save(v3);

            VillainEntity villain4 = new VillainEntity();
            villain4.setName("Mummy");
            villain4.setLevel(1);
            villain4.setAttack(14);
            villain4.setDefense(2);
            villain4.setHitPoints(5);
            session.save(villain4);


            VillainEntity v5 = new VillainEntity();
            v5.setName("Monster");
            v5.setLevel(2);
            v5.setAttack(45);
            v5.setDefense(20);
            v5.setHitPoints(15);
            session.save(v5);

            VillainEntity villain5 = new VillainEntity();
            villain5.setName("Ufo");
            villain5.setLevel(1);
            villain5.setAttack(20);
            villain5.setDefense(10);
            villain5.setHitPoints(8);
            session.save(villain5);

            VillainEntity v6 = new VillainEntity();
            v6.setName("Wolf");
            v6.setLevel(2);
            v6.setAttack(50);
            v6.setDefense(30);
            v6.setHitPoints(15);
            session.save(v6);

            VillainEntity v7 = new VillainEntity();
            v7.setName("Zombi");
            v7.setLevel(2);
            v7.setAttack(40);
            v7.setDefense(10);
            v7.setHitPoints(15);
            session.save(v7);

            VillainEntity villain1 = new VillainEntity();
            villain1.setName("Devil");
            villain1.setLevel(5);
            villain1.setAttack(70);
            villain1.setDefense(50);
            villain1.setHitPoints(20);
            session.save(villain1);

            VillainEntity v2 = new VillainEntity();
            v2.setName("Dragon");
            v2.setLevel(6);
            v2.setAttack(100);
            v2.setDefense(80);
            v2.setHitPoints(70);
            session.save(v2);
            transaction.commit();
*/
            VillainEntity v8 = new VillainEntity();
            v8.setName("Genie");
            v8.setLevel(2);
            v8.setAttack(50);
            v8.setDefense(20);
            v8.setHitPoints(80);
            session.save(v8);

            VillainEntity v9 = new VillainEntity();
            v9.setName("Vampire");
            v9.setLevel(1);
            v9.setAttack(35);
            v9.setDefense(15);
            v9.setHitPoints(30);
            session.save(v9);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<VillainEntity> getListVillains() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String hql = "FROM VillainEntity";
            Query query = session.createQuery(hql);
            List<VillainEntity> villainEntities = query.list();

            return villainEntities;
        } finally {
            session.close();
        }
    }
}
