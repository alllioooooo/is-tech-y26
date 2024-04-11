package com.alllioooooo.services;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatService {

    private final CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat findCatById(Long id) {
        return catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found with id " + id));
    }

    @Transactional
    public Cat saveCat(Cat cat, Long ownerId) {
        cat.setOwnerId(ownerId); // Устанавливаем ownerId для Cat
        return catRepository.save(cat);
    }

    @Transactional
    public void deleteCatById(Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found with id " + id));
        catRepository.delete(cat);
    }

    @Transactional
    public void addFriendship(Cat cat1, Cat cat2) {
        if (!cat1.getFriends().contains(cat2)) {
            cat1.getFriends().add(cat2);
            catRepository.save(cat1);
            catRepository.save(cat2);
        }
    }

    @Transactional
    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }

    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }
}