package engine;

import java.util.ArrayList;
import java.util.List;

public class ListPotion {

    public final static Potion POTION_HEALTH = new Potion(
            "Зелье здоровья [+50 HP]",
            10,
            1,
            "buff",
            "HP",
            50);

    public final static UsePotion USE_POTION_HEALTH = new UsePotion("Использовать ", POTION_HEALTH);

    public final static List<Potion> itemsOfAlchemist = new ArrayList<>(List.of(POTION_HEALTH));

}
