package me.cookieblaster.cansteinsupportutils.storage;

import com.google.common.collect.ImmutableMap;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class TimedInventorySave implements ConfigurationSerializable {
    private final ItemStack[] inventory;
    private final long timestamp;

    public TimedInventorySave(Map<String, Object> data) { //wenn die Configdatei geladen wird
        inventory = ((List<ItemStack>) data.get("inventory")).toArray(new ItemStack[41]);
        timestamp = (long) data.get("timestamp");
    }

    public TimedInventorySave(Player player) { //wenn der Spieler stirbt, wird das hier aufgerufen
        inventory = player.getInventory().getContents();
        timestamp = System.currentTimeMillis();
    }

    @Override
    public @NotNull Map<String, Object> serialize() { //Inventar wird als Map ausgegeben
        return new ImmutableMap.Builder<String, Object>()
                .put("inventory", inventory)
                .put("timestamp", timestamp)
                .build();
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
