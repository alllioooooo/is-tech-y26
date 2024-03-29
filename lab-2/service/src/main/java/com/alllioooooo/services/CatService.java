package com.alllioooooo.services;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.repository.CatRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class CatService {

    public CatRepository catRepository;

    public CatService(SessionFactory sessionFactory) {
        this.catRepository = new CatRepository(sessionFactory);
    }

    public Cat findCatByName(String name) {
        return catRepository.findByName(name);
    }

    public List<Cat> findCatsByOwner(Owner owner) {
        return catRepository.findByOwner(owner);
    }

    public void deleteCat(Cat cat) {
        catRepository.deleteCat(cat);
    }

    public void addFriendship(Cat cat1, Cat cat2) {
        catRepository.addFriendship(cat1, cat2);
    }

    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }
}