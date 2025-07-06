package engine;

public class AttributeIncreaseOption {

    private final String name;
    private final Attribute attribute;
    private final int increaseAmount;

    public AttributeIncreaseOption(String name, Attribute attribute, int increaseAmount) {
        this.name = name;
        this.attribute = attribute;
        this.increaseAmount = increaseAmount;
    }

    public String getName() {
        return name;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public int getIncreaseAmount() {
        return increaseAmount;
    }

    @Override
    public String toString() {
        return name + ": [" + attribute + " +" + increaseAmount + "].";
    }

    public enum Attribute {
        HP, STRENGTH, AGILITY
    }
}



