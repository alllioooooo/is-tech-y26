package com.alllioooooo.ownermicroservice.service;

import com.alllioooooo.ownermicroservice.entity.Owner;
import com.alllioooooo.ownermicroservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found with id " + id));
    }

    @Transactional
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Transactional
    public void deleteOwnerById(Long id) {
        Owner owner = findOwnerById(id);
        ownerRepository.delete(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }
}
