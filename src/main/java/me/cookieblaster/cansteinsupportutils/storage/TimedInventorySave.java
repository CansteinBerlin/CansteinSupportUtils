package me.cookieblaster.cansteinsupportutils.storage;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class TimedInventorySave implements ConfigurationSerializable {
    private final ItemStack[] inventory;
    private final long timestamp;
    private final Location coordinates;

    private final int level;
    private final float experience;

    public TimedInventorySave(Map<String, Object> data) { //wenn die Configdatei geladen wird
        inventory = ((List<ItemStack>) data.get("inventory")).toArray(new ItemStack[41]);
        timestamp = (long) data.get("timestamp");
        if (data.get("coordinates") instanceof Location) coordinates = (Location) data.get("coordinates");
        else coordinates = null;
        if (data.get("experience") == null) experience = 0;
        else experience = Float.parseFloat(data.get("experience") + "");
        if (data.get("level") == null) level = 0;
        else level = (int) data.get("level");
    }

    public TimedInventorySave(Player player) { //wenn der Spieler stirbt, wird das hier aufgerufen
        inventory = player.getInventory().getContents();
        timestamp = System.currentTimeMillis();
        coordinates = player.getLocation();
        experience = player.getExp();
        level = player.getLevel();
    }

    @Override
    public @NotNull Map<String, Object> serialize() { //Inventar wird als Map ausgegeben
        return new ImmutableMap.Builder<String, Object>()
                .put("inventory", inventory)
                .put("timestamp", timestamp)
                .put("coordinates", (coordinates == null) ? "null" : coordinates)
                .put("experience", experience)
                .put("level", level)
                .build();
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return coordinates;
    }

    public float getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }
}
