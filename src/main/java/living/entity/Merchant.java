package living.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import engine.GameSession;
import engine.Item;
import story.dialogues.Dialogue;
import text_styler.Reader;

import java.util.List;

public class Merchant extends NPC{

    private final Dialogue interactionDialogue;
    private final List<? extends Item> itemsForSale;

    public Merchant(
            String name,
            String mainDialoguePath,
            String firstEncounterDialoguePath,
            String interactionDialoguePath,
            List<? extends Item> itemsForSale
    ) {
        super(name, mainDialoguePath, firstEncounterDialoguePath);
        this.interactionDialogue = getFileReader().getData(interactionDialoguePath, new TypeReference<>() {});
        this.itemsForSale = itemsForSale;
    }

    /**
     * Запускает диалог с NPC.
     * При первой встрече воспроизводится особый диалог, после чего
     * устанавливается флаг первой встречи в false.
     * Сразу после (и при последующих вызовах) запускается уникальный диалог
     * для торговцев. В зависимости от выбора игрока, открывается либо торговое окно,
     * либо запускается основной диалог.
     */
    @Override
    public void startDialogue() {

        if (isFirstEncounter()) {
            getDialogueReader().read(getFirstEncounterDialogues());
            setFirstEncounter(false);
        }

        interactionLoop: do {
            switch (getDialogueReader().readHub(interactionDialogue)) {
                case "buy" -> openTradeMenu();
                case "talk" -> getDialogueReader().readDialogueWithNPC(getMainDialogue());
                case "exit" -> {
                    break interactionLoop;
                }
            }
        } while (true);

    }

    private void openTradeMenu() {

        displayItemsForSale();

        int index = Reader.readResponse(itemsForSale.size());
        Item selectedItem = itemsForSale.get(index);

        Player player = getPlayer();

        if (player.spendGold(selectedItem.getCost())) {
            player.addItemToInventory(selectedItem);
            System.out.println("Успешно приобрели товар!");
        } else {
            System.out.println("Недостаточно денег!");
        }

    }

    private void displayItemsForSale() {
        System.out.println(buildItemsForSaleString());
    }

    private String buildItemsForSaleString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < itemsForSale.size(); i++) {
            builder.append(i + 1)
                    .append(". ")
                    .append(itemsForSale.get(i).toString())
                    .append("\n");
        }
        return builder.toString();
    }

    private Player getPlayer() {
        return GameSession.getInstance().getPlayer();
    }

}
