package com.alllioooooo.services;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.repository.OwnerRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerService(SessionFactory sessionFactory) {
        this.ownerRepository = new OwnerRepository(sessionFactory);
    }

    public void addCatToOwner(Cat cat, Owner owner) {
        ownerRepository.addCatToOwner(cat, owner);
    }

    public List<Cat> findCatsByOwner(Owner owner) {
        return ownerRepository.findCatsByOwner(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerRepository.deleteOwner(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

}
