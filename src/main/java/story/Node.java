package story;

public abstract class Node {
    public Node() {}

    private String id;
    private boolean isUnlocked;
    private boolean isFirst = true;
    private boolean alwaysUnlocked;
    private String text;

    public String getId() {
        return id;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isAlwaysUnlocked() {
        return alwaysUnlocked;
    }

    public String getText() {
        return text;
    }

    public void locked() {
        if (!isAlwaysUnlocked()) isFirst = false;
    }

}
