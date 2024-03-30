package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.OwnerService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class SeeCatsByOwnerScenario implements Scenarioable {

    private OwnerService ownerService;

    public SeeCatsByOwnerScenario(SessionFactory sessionFactory) {
        this.ownerService = new OwnerService(sessionFactory);
    }

    @Override
    public void run() {
        List<Owner> owners = ownerService.findAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found in the database.");
            return;
        }

        System.out.println("Select an owner to see their cats:");
        for (int i = 0; i < owners.size(); i++) {
            Owner owner = owners.get(i);
            System.out.println((i + 1) + ": " + owner.getName() + " " + owner.getSurname());
        }

        Scanner scanner = new Scanner(System.in);
        int ownerIndex = scanner.nextInt() - 1;

        if (ownerIndex >= 0 && ownerIndex < owners.size()) {
            Owner selectedOwner = owners.get(ownerIndex);
            List<Cat> cats = ownerService.findCatsByOwner(selectedOwner);
            if (cats.isEmpty()) {
                System.out.println("No cats found for owner " + selectedOwner.getName());
            } else {
                System.out.println("Cats owned by " + selectedOwner.getName() + ":");
                for (Cat cat : cats) {
                    System.out.println("- " + cat.getName() + " (" + cat.getBreed() + ", " + cat.getColor() + ")");
                }
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
}