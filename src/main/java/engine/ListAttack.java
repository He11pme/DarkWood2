package engine;

public class ListAttack {

    public final static Attack BASIC = new Attack(
            "Обычная атака",
            1,
            1,
            5,
            5,
            1,
            1);

    public final static Attack WHIRL = new Attack(
            "Мельница",
            0.7,
            2,
            10,
            3,
            2,
            7);

    public final static Attack REND = new Attack(
            "Рассечение",
            2,
            3,
            0,
            7,
            7,
            2);


}
