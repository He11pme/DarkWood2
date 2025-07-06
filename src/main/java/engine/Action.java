package engine;

import creatures.Fighter;

public abstract class Action {

    private final String name;

    public String getName() {
        return name;
    }

    public Action(String name) {
        this.name = name;
    }

    public abstract double getActionPoints(Fighter attacker);

    @Override
    public String toString() {
        return name;
    }
}
