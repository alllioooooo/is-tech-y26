package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import com.alllioooooo.services.OwnerService;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AddCatScenario implements Scenarioable {

    private CatService catService;
    private OwnerService ownerService;

    public AddCatScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
        this.ownerService = new OwnerService(sessionFactory);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adding a new cat to the database.");
        System.out.println("Enter cat's name:");
        String name = scanner.nextLine();

        System.out.println("Enter cat's birth date (YYYY-MM-DD):");
        String birthDateStr = scanner.nextLine();
        Date birthDate = Date.valueOf(birthDateStr);

        System.out.println("Enter cat's breed:");
        String breed = scanner.nextLine();

        System.out.println("Enter cat's color:");
        String color = scanner.nextLine();

        List<Owner> owners = ownerService.findAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found. Please add an owner first.");
            return;
        }

        System.out.println("Select an owner for the cat:");
        for (int i = 0; i < owners.size(); i++) {
            System.out.println((i + 1) + ": " + owners.get(i).getName() + " " + owners.get(i).getSurname());
        }
        int ownerIndex = scanner.nextInt() - 1;

        if (ownerIndex < 0 || ownerIndex >= owners.size()) {
            System.out.println("Invalid owner selection.");
            return;
        }

        Owner selectedOwner = owners.get(ownerIndex);

        Cat newCat = new Cat();
        newCat.setName(name);
        newCat.setBirthDate(birthDate);
        newCat.setBreed(breed);
        newCat.setColor(color);

        ownerService.addCatToOwner(newCat, selectedOwner);

        System.out.println("New cat added: " + name + ", Owner: " + selectedOwner.getName());
    }
}