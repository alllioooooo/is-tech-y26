package com.alllioooooo.scenarios;

import java.util.ArrayList;
import java.util.List;

public class ScenarioRunner {

    private final List<Scenarioable> scenarios = new ArrayList<>();

    public void addScenario(Scenarioable scenario) {
        scenarios.add(scenario);
    }

    public void runScenarios() {
        for (Scenarioable scenario : scenarios) {
            scenario.run();
        }
    }
}
