package com.alllioooooo.repository;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OwnerRepository {
    private final SessionFactory sessionFactory;

    public OwnerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCatToOwner(Cat cat, Owner owner) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Owner managedOwner = session.find(Owner.class, owner.getId());
            if (managedOwner != null) {
                cat.setOwner(managedOwner);
                session.save(cat);
            }
            session.getTransaction().commit();
        }
    }

    public List<Cat> findCatsByOwner(Owner owner) {
        try (Session session = sessionFactory.openSession()) {
            String queryStr = "SELECT c FROM Cat c WHERE c.owner = :owner";
            return session.createQuery(queryStr, Cat.class)
                    .setParameter("owner", owner)
                    .getResultList();
        }
    }

    public void deleteOwner(Owner owner) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Owner managedOwner = session.find(Owner.class, owner.getId());
            if (managedOwner != null) {
                session.delete(managedOwner);
            }
            session.getTransaction().commit();
        }
    }

    public List<Owner> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Owner", Owner.class).list();
        }
    }
}
