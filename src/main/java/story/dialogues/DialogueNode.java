package story.dialogues;

import story.Node;

public class DialogueNode extends Node {
    public DialogueNode() {}

    private String response;
    private String[] nextNodes;
    private String action;

    public String getResponse() {
        return response;
    }

    public String[] getNextNodes() {
        return nextNodes;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return response;
    }

}
