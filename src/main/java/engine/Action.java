package engine;

import living.entity.CombatEntity;

public abstract class Action {

    private final String name;

    public String getName() {
        return name;
    }

    public Action(String name) {
        this.name = name;
    }

    public abstract double getActionPoints(CombatEntity attacker);

    @Override
    public String toString() {
        return name;
    }
}
