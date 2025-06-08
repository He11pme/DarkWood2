import org.w3c.dom.Text;

public class Player extends Creature {

    private int gold = 10;
    private int exp = 0;
    private int level = 1;

    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public Player(String name, int health, int dex, int str) {
        super(name, health, dex, str);
    }

    public void addExp(int i) {
        exp += i;
        checkUpLevel();
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public boolean spendGold(int amount) {
        if (gold < amount) return false;
        else {
            gold -= amount;
            return true;
        }
    }

    private boolean checkUpLevel() {
        if (exp / 100 > level - 1) {
            System.out.println(TextStyler.colorize("Отдохните в деревне, чтобы повысить уровень.", TextStyler.YELLOW));
            return true;
        }
        return false;
    }

}
