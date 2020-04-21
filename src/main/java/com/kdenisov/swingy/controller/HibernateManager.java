package com.kdenisov.swingy.controller;

import com.kdenisov.swingy.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.validation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public boolean saveHero(String name, HeroClass heroClass, Artifact artifact, int attack, int defense, int hitPoints, int y, int x)
    {
        Session session = null;

        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

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

            Set<ConstraintViolation<HeroEntity>> constraintViolations = validator.validate(hero);
            if (constraintViolations.size() != 0) {
                System.out.println("List of constraint violations:");
                for (ConstraintViolation<HeroEntity> constraintViolation : constraintViolations) {
                    System.out.println("Constraint Violation: " + constraintViolation.getMessage());
                }
                return false;
            }

            session.save(hero);

            ArtifactsEntity artifactsEntity = new ArtifactsEntity();
            artifactsEntity.setHeroEntity(hero);
            artifactsEntity.setArtifact(artifact);
            session.save(artifactsEntity);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public void deleteHero(GameEngine game) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            HeroEntity heroEntity = session.get(HeroEntity.class, game.getHero().getId());

            session.delete(heroEntity);
            transaction.commit();
        } finally {
            assert session != null;
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
            String hql = "FROM HeroEntity";
            Query query = session.createQuery(hql);
            List<HeroEntity> heroEntities = query.list();

            return heroEntities;
        } finally {
            session.close();
        }
    }

    public List<Artifact> getListArtifacts(int id) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
        String hql = "FROM ArtifactsEntity WHERE heroId = " + id;
            Query query = session.createQuery(hql);
            List<ArtifactsEntity> artifactsEntities = query.list();
            List<Artifact> artifacts = new ArrayList<>();

            for (ArtifactsEntity artifactsEntity : artifactsEntities) {
                artifacts.add(artifactsEntity.getArtifact());
            }

            return artifacts;
        } finally {
            session.close();
        }
    }

    public void updateHero(Hero hero) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE HeroEntity SET level =" + hero.getLevel() +
                    ", experience = " + hero.getExperience() + ", attack = " +
                    hero.getAttack() + ", defense = " + hero.getDefense() +
                    ", hitPoints = " + hero.getHitPoints() + ", y = " + hero.getY() + ", x = " +
                    hero.getX() + " WHERE id =" + hero.getId();

            //session.saveOrUpdate(hero);

            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();

        } finally {
            session.close();
        }
    }

    public void saveGame(GameEngine game) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(game.getGameEntities());
            objectOutputStream.writeObject(game.getVillains());
            objectOutputStream.writeObject(game.getObstacles());
            objectOutputStream.close();

            HeroEntity heroEntity = session.get(HeroEntity.class, game.getHero().getId());
            heroEntity.setSave(byteArrayOutputStream.toByteArray());

            session.saveOrUpdate(heroEntity);

            transaction.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public InputStream loadGame(int heroId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            HeroEntity heroEntity = session.get(HeroEntity.class, heroId);
            if (heroEntity.getSave() == null) {
                return null;
            }

            return new ByteArrayInputStream(heroEntity.getSave());

        } finally {
            assert session != null;
            session.close();
        }
    }

    public void updateArtifacts(Hero hero, Artifact artifact) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            HeroEntity heroEntity = session.get(HeroEntity.class, hero.getId());

            ArtifactsEntity artifactsEntity = new ArtifactsEntity();
            artifactsEntity.setHeroEntity(heroEntity);
            artifactsEntity.setArtifact(artifact);
            
            session.save(artifactsEntity);
            transaction.commit();

        } finally {
            session.close();
        }
    }
}
