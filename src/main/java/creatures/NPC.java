package creatures;

import com.fasterxml.jackson.core.type.TypeReference;
import story.dialogues.ReadDialogue;
import story.ReadFile;
import story.dialogues.Dialogue;

import java.util.List;

public class NPC extends Creature {

    private boolean isFirstMeeting = true;
    private final Dialogue generalDialogue;
    private final List<Dialogue> firstMeeting;

    public boolean isFirstMeeting() {
        return isFirstMeeting;
    }

    public void setFirstMeeting(boolean firstMeeting) {
        isFirstMeeting = firstMeeting;
    }

    public Dialogue getGeneralDialogue() {
        return generalDialogue;
    }

    public List<Dialogue> getFirstMeeting() {
        return firstMeeting;
    }

    public NPC(String name, String jsonFilePathGenDial, String jsonFilePathFirstMeeting) {
        super(name);
        generalDialogue = new ReadFile().getData(jsonFilePathGenDial, new TypeReference<>() {});
        firstMeeting = new ReadFile().getData(jsonFilePathFirstMeeting, new TypeReference<>() {});
    }

    public void startDialogue() {
        if (isFirstMeeting) {
            new ReadDialogue().read(firstMeeting);
            isFirstMeeting = false;
        }
        new ReadDialogue().readDialogueWithNPC(generalDialogue);
    }

}
