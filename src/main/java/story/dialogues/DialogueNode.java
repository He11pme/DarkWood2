package story.dialogues;

import story.Node;

public class DialogueNode extends Node {
    public DialogueNode() {}

    private String response;
    private String[] nextNodes;

    public String getResponse() {
        return response;
    }

    public String[] getNextNodes() {
        return nextNodes;
    }

    @Override
    public String toString() {
        return response;
    }

}
