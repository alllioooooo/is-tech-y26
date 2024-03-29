package com.alllioooooo.scenarios.ownerscenarios;

import com.alllioooooo.entity.Owner;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.OwnerService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class DeleteOwnerFromDatabaseScenario implements Scenarioable {

    private OwnerService ownerService;

    public DeleteOwnerFromDatabaseScenario(SessionFactory sessionFactory) {
        this.ownerService = new OwnerService(sessionFactory);
    }

    @Override
    public void run() {
        List<Owner> owners = ownerService.findAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found in the database.");
            return;
        }

        System.out.println("Select an owner to delete:");
        for (int i = 0; i < owners.size(); i++) {
            Owner owner = owners.get(i);
            System.out.println((i + 1) + ": " + owner.getName() + " " + owner.getSurname());
        }

        Scanner scanner = new Scanner(System.in);
        int ownerIndex = scanner.nextInt() - 1;

        if (ownerIndex >= 0 && ownerIndex < owners.size()) {
            Owner ownerToDelete = owners.get(ownerIndex);
            ownerService.deleteOwner(ownerToDelete);
            System.out.println("Owner " + ownerToDelete.getName() + " " + ownerToDelete.getSurname() + " and all their cats have been deleted from the database.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
