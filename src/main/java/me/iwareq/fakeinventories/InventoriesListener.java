package me.iwareq.fakeinventories;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.item.Item;

public class InventoriesListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryTransaction(InventoryTransactionEvent event) {
        event.getTransaction().getActions().forEach(action -> {
            if (action instanceof SlotChangeAction) {
                SlotChangeAction slotChange = (SlotChangeAction) action;
                if (slotChange.getInventory() instanceof FakeInventory) {
                    FakeInventory inventory = (FakeInventory) slotChange.getInventory();

                    int slot = slotChange.getSlot();
                    Item sourceItem = action.getSourceItem();
                    inventory.handle(slot, sourceItem, event);
                }
            }
        });
    }
}
