package com.alllioooooo.services;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found with id " + id));
    }

    @Transactional
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Transactional
    public void deleteOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found with id " + id));
        ownerRepository.delete(owner);
    }

    @Transactional
    public void addCatToOwner(Cat cat, Owner owner) {
        if (!owner.getCats().contains(cat)) {
            owner.getCats().add(cat);
            cat.setOwner(owner);
            ownerRepository.save(owner);
        }
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    @Transactional
    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }
}
