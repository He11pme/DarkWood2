package story;

public abstract class Node {
    public Node() {}

    private String id;
    private boolean isUnlocked;
    private boolean isUnlockable;
    private String text;

    public String getId() {
        return id;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public boolean isUnlockable() {
        return isUnlockable;
    }

    public String getText() {
        return text;
    }

    public void unlocked() {
        isUnlocked = true;
        isUnlockable = false;
    }

    public void locked() {
        isUnlocked = false;
    }

}
