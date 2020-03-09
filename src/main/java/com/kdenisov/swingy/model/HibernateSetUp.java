package com.kdenisov.swingy.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateSetUp {
    private SessionFactory sessionFactory;

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
        Session session = sessionFactory.openSession();
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

        String hql = "FROM Hero order by id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        HeroEntity last = (HeroEntity)query.uniqueResult();

        ArtifactsEntity artifactsEntity = new ArtifactsEntity();
        artifactsEntity.setHeroId(last.getId());
        artifactsEntity.setArtifact(artifact);
        session.save(artifactsEntity);
        transaction.commit();

        tearDown();
    }

    @SuppressWarnings({"rawtypes", "TryFinallyCanBeTryWithResources"})
    public List<HeroEntity> getHeroes() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Transaction transaction = session.beginTransaction();
            String hql = "FROM HeroEntity";
            Query query = session.createQuery(hql);
            List<HeroEntity> heroEntities = query.list();

            // transaction.commit();
            tearDown();

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
}
