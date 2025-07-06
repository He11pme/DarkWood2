package engine;

public class ListAttributeIncreaseOption {

    public final static AttributeIncreaseOption HEALTH_INCREASE_OPTION =
            new AttributeIncreaseOption(
                    "Укрепили здоровье",
                    AttributeIncreaseOption.Attribute.HP,
                    15);

    public final static AttributeIncreaseOption STRENGTH_INCREASE_OPTION =
            new AttributeIncreaseOption(
                    "Стали сильнее",
                    AttributeIncreaseOption.Attribute.STRENGTH,
                    1);

    public final static AttributeIncreaseOption AGILITY_INCREASE_OPTION =
            new AttributeIncreaseOption(
                    "Стали быстрее",
                    AttributeIncreaseOption.Attribute.AGILITY,
                    1);

}
