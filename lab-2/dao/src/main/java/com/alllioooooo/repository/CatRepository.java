package com.alllioooooo.repository;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CatRepository {

    private final SessionFactory sessionFactory;

    public CatRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Cat findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            org.hibernate.query.Query<Cat> query = session.createQuery("FROM Cat WHERE name = :name", Cat.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    public List<Cat> findByColor(String color) {
        try (Session session = sessionFactory.openSession()) {
            org.hibernate.query.Query<Cat> query = session.createQuery("FROM Cat WHERE color = :color", Cat.class);
            query.setParameter("color", color);
            return query.list();
        }
    }

    public List<Cat> findByOwner(Owner owner) {
        try (Session session = sessionFactory.openSession()) {
            org.hibernate.query.Query<Cat> query = session.createQuery("FROM Cat WHERE owner = :owner", Cat.class);
            query.setParameter("owner", owner);
            return query.list();
        }
    }

    public void deleteCat(Cat cat) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(cat);
            session.getTransaction().commit();
        }
    }

    public void addFriendship(Cat cat1, Cat cat2) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Cat managedCat1 = session.find(Cat.class, cat1.getId());
            Cat managedCat2 = session.find(Cat.class, cat2.getId());

            managedCat1.getFriends().add(managedCat2);
            managedCat2.getFriends().add(managedCat1);

            session.saveOrUpdate(managedCat1);
            session.saveOrUpdate(managedCat2);

            session.getTransaction().commit();
        }
    }

    public List<Cat> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Cat", Cat.class).list();
        }
    }
}
