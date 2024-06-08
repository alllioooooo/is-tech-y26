package com.alllioooooo.catmicroservice.service;

import com.alllioooooo.catmicroservice.entity.Cat;
import com.alllioooooo.catmicroservice.repository.CatRepository;
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
    public Cat saveCat(Cat cat) {
        return catRepository.save(cat);
    }

    @Transactional
    public void deleteCatById(Long id) {
        Cat cat = findCatById(id);
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

    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }

    public List<Cat> findCatsByOwnerId(Long ownerId) {
        return catRepository.findByOwnerId(ownerId);
    }
}
