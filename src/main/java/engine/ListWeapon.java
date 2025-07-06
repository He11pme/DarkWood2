package engine;

import java.util.ArrayList;
import java.util.List;

public class ListWeapon {

    public final static Weapon FISTS = new Weapon(
            "кулаки",
            0,
            1,
            5,
            0,
            0);

    public final static Weapon DAGGER = new Weapon(
            "кинжал",
            15,
            0.5,
            15,
            2,
            1);

    public final static Weapon SWORD = new Weapon(
            "меч",
            25,
            1,
            40,
            4,
            2);

    public final static Weapon HAMMER = new Weapon(
            "молот",
            25,
            2,
            70,
            1,
            4);

    public final static Weapon CLAWS = new Weapon(
            "когти",
            0,
            1,
            10,
            0,
            0);

    public final static Weapon FANGS = new Weapon(
            "клыки",
            0,
            1,
            10,
            0,
            0);

    public final static List<Weapon> itemsOfBlacksmith = new ArrayList<>(List.of(DAGGER, SWORD, HAMMER));


}
