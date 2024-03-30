package com.alllioooooo.scenarios.catscenarios;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeeAllCatsScenario implements Scenarioable {

    private CatService catService;

    public SeeAllCatsScenario(SessionFactory sessionFactory) {
        this.catService = new CatService(sessionFactory);
    }

    @Override
    public void run() {
        List<Cat> cats = catService.findAllCats();
        if (cats.isEmpty()) {
            System.out.println("No cats found in the database.");
            return;
        }

        System.out.println("List of all cats:");
        for (Cat cat : cats) {
            System.out.println("Cat Name: " + cat.getName() + ", Breed: " + cat.getBreed() + ", Color: " + cat.getColor());
        }
    }
}
