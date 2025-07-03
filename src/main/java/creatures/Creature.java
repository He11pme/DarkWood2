package creatures;

public abstract class Creature {

    private final String name;

    public String getName() {
        return name;
    }

    public Creature(String name) {
        this.name = name;
    }

}
