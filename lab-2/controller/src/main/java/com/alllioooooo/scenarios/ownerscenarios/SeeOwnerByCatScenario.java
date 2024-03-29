package com.alllioooooo.scenarios.ownerscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;

import java.lang.module.Configuration;
import java.util.Scanner;

public class SeeOwnerByCatScenario implements Scenarioable {

    private CatService catService;

    public SeeOwnerByCatScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the cat to see its owner:");
        String catName = scanner.nextLine();

        Cat cat = catService.findCatByName(catName);
        if (cat != null && cat.getOwner() != null) {
            System.out.println("Owner of the cat " + catName + " is " + cat.getOwner().getName() + " " + cat.getOwner().getSurname());
        } else {
            System.out.println("No cat found with the name " + catName + " or cat does not have an owner.");
        }
    }
}
