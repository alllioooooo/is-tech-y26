package com.alllioooooo.services;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.entity.User;
import com.alllioooooo.repository.CatRepository;
import com.alllioooooo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CatService {

    private CatRepository catRepository;

    private UserRepository userRepository;

    @Autowired
    public CatService(CatRepository catRepository, UserRepository userRepository) {
        this.catRepository = catRepository;
        this.userRepository = userRepository;
    }

    public Cat findCatById(Long id) {
        return catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found with id " + id));
    }

    @Transactional
    public Cat saveCat(Cat cat, Long ownerId) {
        cat.setOwnerId(ownerId);
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

    public List<Cat> findCatsByOwnerId(Long ownerId) {
        return catRepository.findByOwnerId(ownerId);
    }

    public Set<Cat> findCatFriends(Long id) {
        Cat cat = findCatById(id);
        return cat.getFriends();
    }

    public Long findOwnerIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getOwner)
                .map(Owner::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public boolean isOwner(Long catId, String username) {
        return catRepository.findById(catId)
                .map(Cat::getOwnerId)
                .map(ownerId -> userRepository.findById(ownerId).orElseThrow())
                .map(owner -> owner.getUsername().equals(username))
                .orElse(false);
    }
}