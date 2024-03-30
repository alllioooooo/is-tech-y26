package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SeeCatsFriendsScenario implements Scenarioable {

    private final CatService catService;

    public SeeCatsFriendsScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<Cat> cats = catService.findAllCats();

        if (cats.isEmpty()) {
            System.out.println("No cats found in the database.");
            return;
        }

        System.out.println("Select a cat to see its friends:");
        for (int i = 0; i < cats.size(); i++) {
            Cat cat = cats.get(i);
            System.out.println((i + 1) + ": " + cat.getName());
        }

        int catIndex = scanner.nextInt() - 1;
        if (catIndex < 0 || catIndex >= cats.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Cat selectedCat = cats.get(catIndex);
        Set<Cat> friends = selectedCat.getFriends();

        if (friends.isEmpty()) {
            System.out.println(selectedCat.getName() + " has no friends.");
        } else {
            System.out.println(selectedCat.getName() + "'s friends:");
            for (Cat friend : friends) {
                System.out.println("- " + friend.getName());
            }
        }
    }
}
