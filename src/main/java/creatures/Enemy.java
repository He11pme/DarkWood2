public abstract class Enemy extends Creature {

    private final int goldReward;
    private final int expReward;

    public int getGoldReward() {
        return goldReward;
    }

    public int getExpReward() {
        return expReward;
    }

    public Enemy(String name, int health, int dex, int str, int goldReward, int expReward) {
        super(name, health, dex, str);
        this.goldReward = goldReward;
        this.expReward = expReward;
    }
}
