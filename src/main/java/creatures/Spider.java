public class Spider extends Enemy {

    private static final String NAME = "Spider";
    private static final int HEALTH = 100, DEX = 2, STR = 10;
    private static final int GOLD_REWARD = 5, EXP_REWARD = 5;


    public Spider() {
        super(NAME, HEALTH, DEX, STR, GOLD_REWARD, EXP_REWARD);
    }

}
