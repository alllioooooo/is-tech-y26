package com.alllioooooo.services;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.repository.CatRepository;
import com.alllioooooo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;

    private CatRepository catRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
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
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found with id " + id));
        ownerRepository.delete(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    @Transactional
    public List<Owner> findAllOwnersWithCats() {
        List<Owner> owners = ownerRepository.findAll();
        for (Owner owner : owners) {
            Set<Long> catIds = catRepository.findByOwnerId(owner.getId())
                    .stream()
                    .map(Cat::getId)
                    .collect(Collectors.toSet());
            owner.setCatIds(catIds);
        }
        return owners;
    }

    @Transactional
    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }
}
