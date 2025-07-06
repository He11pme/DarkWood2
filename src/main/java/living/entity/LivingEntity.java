package living.entity;

public abstract class LivingEntity {

    private final String name;

    public LivingEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
