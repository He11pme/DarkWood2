public abstract class Creature {

    private final String name;
    private int health;
    private int dex;
    private int str;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public Creature(String name, int health, int dex, int str) {
        this.name = name;
        this.health = health;
        this.dex = dex;
        this.str = str;
    }
}
