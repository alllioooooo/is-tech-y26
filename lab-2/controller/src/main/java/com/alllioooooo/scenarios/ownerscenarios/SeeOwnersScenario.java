package com.alllioooooo.scenarios.ownerscenarios;

import com.alllioooooo.entity.Owner;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.OwnerService;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeeOwnersScenario implements Scenarioable {

    private final OwnerService ownerService;

    public SeeOwnersScenario(SessionFactory sessionFactory) {
        this.ownerService = new OwnerService(sessionFactory);
    }

    @Override
    public void run() {
        List<Owner> owners = ownerService.findAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }

        System.out.println("Owners list:");
        for (int i = 0; i < owners.size(); i++) {
            Owner owner = owners.get(i);
            System.out.println((i + 1) + ": " + owner.getName() + " " + owner.getSurname());
        }
    }
}