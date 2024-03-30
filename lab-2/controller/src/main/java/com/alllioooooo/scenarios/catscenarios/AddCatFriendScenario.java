package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class AddCatFriendScenario implements Scenarioable {

    private CatService catService;

    public AddCatFriendScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<Cat> cats = catService.findAllCats();

        if (cats.size() < 2) {
            System.out.println("There are not enough cats in the database to make a friendship.");
            return;
        }

        System.out.println("Select two cats to make them friends:");
        for (int i = 0; i < cats.size(); i++) {
            Cat cat = cats.get(i);
            System.out.println((i + 1) + ": " + cat.getName());
        }

        System.out.println("Enter the number of the first cat:");
        int firstCatIndex = scanner.nextInt() - 1;
        System.out.println("Enter the number of the second cat:");
        int secondCatIndex = scanner.nextInt() - 1;

        if (firstCatIndex < 0 || firstCatIndex >= cats.size() || secondCatIndex < 0 || secondCatIndex >= cats.size() || firstCatIndex == secondCatIndex) {
            System.out.println("Invalid selection.");
            return;
        }

        Cat firstCat = cats.get(firstCatIndex);
        Cat secondCat = cats.get(secondCatIndex);

        catService.addFriendship(firstCat, secondCat);
        System.out.println("Friendship added between " + firstCat.getName() + " and " + secondCat.getName());
    }
}
