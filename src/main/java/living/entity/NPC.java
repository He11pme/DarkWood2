package living.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import story.dialogues.ReadDialogue;
import story.ReadFile;
import story.dialogues.Dialogue;

import java.util.List;

public class NPC extends LivingEntity {

    private boolean isFirstEncounter = true;                            // первая ли встреча игрока и NPC

    private final Dialogue mainDialogue;
    private final List<Dialogue> firstEncounterDialogues;

    private final static ReadDialogue DIALOGUE_READER = new ReadDialogue();
    private final static ReadFile FILE_READER = new ReadFile();

    public NPC(String name, String mainDialoguePath, String firstEncounterDialoguePath) {
        super(name);
        this.mainDialogue = FILE_READER.getData(mainDialoguePath, new TypeReference<>() {});
        this.firstEncounterDialogues = FILE_READER.getData(firstEncounterDialoguePath, new TypeReference<>() {});
    }

    public boolean isFirstEncounter() {
        return isFirstEncounter;
    }

    public void setFirstEncounter(boolean firstEncounter) {
        isFirstEncounter = firstEncounter;
    }

    public List<Dialogue> getFirstEncounterDialogues() {
        return firstEncounterDialogues;
    }

    public Dialogue getMainDialogue() {
        return mainDialogue;
    }

    public ReadDialogue getDialogueReader() {
        return DIALOGUE_READER;
    }

    public ReadFile getFileReader() {
        return FILE_READER;
    }

    /**
     * Запускает диалог с NPC.
     * При первой встрече воспроизводится особый диалог, после чего
     * устанавливается флаг первой встречи в false.
     * Сразу после (и при последующих вызовах) запускается основной диалог.
     */
    public void startDialogue() {
        if (isFirstEncounter) {
            DIALOGUE_READER.read(firstEncounterDialogues);
            isFirstEncounter = false;
        }
        DIALOGUE_READER.readDialogueWithNPC(mainDialogue);
    }

}
