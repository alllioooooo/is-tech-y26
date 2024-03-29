package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class SeeCatDataByCatScenario implements Scenarioable {

    private CatService catService;

    public SeeCatDataByCatScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
    }

    @Override
    public void run() {
        List<Cat> cats = catService.findAllCats();
        if (cats.isEmpty()) {
            System.out.println("No cats found in the database.");
            return;
        }

        System.out.println("Select a cat to see its data:");
        for (int i = 0; i < cats.size(); i++) {
            Cat cat = cats.get(i);
            System.out.println((i + 1) + ": " + cat.getName());
        }

        Scanner scanner = new Scanner(System.in);
        int catIndex = scanner.nextInt() - 1;

        if (catIndex >= 0 && catIndex < cats.size()) {
            Cat selectedCat = cats.get(catIndex);
            System.out.println("Cat Name: " + selectedCat.getName());
            System.out.println("Birth Date: " + selectedCat.getBirthDate());
            System.out.println("Breed: " + selectedCat.getBreed());
            System.out.println("Color: " + selectedCat.getColor());
            if (selectedCat.getOwner() != null) {
                System.out.println("Owner: " + selectedCat.getOwner().getName() + " " + selectedCat.getOwner().getSurname());
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
}