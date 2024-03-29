package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class DeleteCatScenario implements Scenarioable {

    private final CatService catService;

    public DeleteCatScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
    }

    @Override
    public void run() {
        List<Cat> cats = catService.findAllCats();
        if (cats.isEmpty()) {
            System.out.println("No cats found in the database.");
            return;
        }

        System.out.println("Select a cat to delete:");
        for (int i = 0; i < cats.size(); i++) {
            Cat cat = cats.get(i);
            System.out.println((i + 1) + ": " + cat.getName() + " (" + cat.getBreed() + ")");
        }

        Scanner scanner = new Scanner(System.in);
        int catIndex = scanner.nextInt() - 1;

        if (catIndex >= 0 && catIndex < cats.size()) {
            Cat catToDelete = cats.get(catIndex);
            catService.deleteCat(catToDelete);
            System.out.println("Cat " + catToDelete.getName() + " has been deleted from the database.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}