package com.alllioooooo;

import com.alllioooooo.scenarios.Scenarioable;
import com.alllioooooo.scenarios.catscenarios.*;
import com.alllioooooo.scenarios.ownerscenarios.DeleteOwnerFromDatabaseScenario;
import com.alllioooooo.scenarios.ownerscenarios.SeeOwnerByCatScenario;
import com.alllioooooo.scenarios.ownerscenarios.SeeOwnersScenario;
import com.alllioooooo.services.CatService;
import com.alllioooooo.services.OwnerService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();

        CatService catService = new CatService(sessionFactory);
        OwnerService ownerService = new OwnerService(sessionFactory);

        Map<Integer, Scenarioable> scenarios = new HashMap<>();
        scenarios.put(1, new AddCatScenario(sessionFactory));
        scenarios.put(2, new DeleteCatScenario(sessionFactory));
        scenarios.put(3, new SeeAllCatsScenario(sessionFactory));
        scenarios.put(4, new SeeCatDataByCatScenario(sessionFactory));
        scenarios.put(5, new AddCatFriendScenario(sessionFactory));
        scenarios.put(6, new SeeCatsFriendsScenario(sessionFactory));
        scenarios.put(7, new SeeOwnersScenario(sessionFactory));
        scenarios.put(8, new DeleteOwnerFromDatabaseScenario(sessionFactory));
        scenarios.put(9, new SeeOwnerByCatScenario(sessionFactory));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            scenarios.forEach((key, value) -> System.out.println(key + ": " + value.getClass().getSimpleName()));
            System.out.println("0: Exit");

            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }

            Scenarioable scenario = scenarios.get(choice);
            if (scenario != null) {
                scenario.run();
            } else {
                System.out.println("Invalid choice.");
            }
        }

        sessionFactory.close();
    }
}