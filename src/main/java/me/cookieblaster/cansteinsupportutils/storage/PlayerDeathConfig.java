package me.cookieblaster.cansteinsupportutils.storage;

import me.cookieblaster.cansteinsupportutils.CansteinSupportUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlayerDeathConfig { //Die Configklasse, die die Config speichert und abrufbar macht -> gibt es für jeden Spieler einmal
    private final UUID uuid;
    private File file;
    private YamlConfiguration config;
    private HashMap<Long, TimedInventorySave> data;

    public PlayerDeathConfig(UUID uuid) {
        this.uuid = uuid;
        String dateiname;
        dateiname = "inv_save/" + uuid.toString() + ".yml";
        file = new File(CansteinSupportUtils.getInstance().getDataFolder(), dateiname);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        loadDataFromConfig();
    }

    public void addInventory(TimedInventorySave inventorySave) {
        data.put(inventorySave.getTimestamp(), inventorySave);
        if (data.size() > 10) {
            data.remove(getTimestamps().get(0)); //
        }
        config.set("inventories", getTimedInventorySaves()); //Überschreiben der Speicherconfigdatei mit der HashMap dieser Klasse
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TimedInventorySave getInventory(long timestamp) {
        return data.get(timestamp);
    }

    public List<Long> getTimestamps() {
        List<Long> tempTimestamps;
        tempTimestamps = new ArrayList<>(data.keySet());
        Collections.sort(tempTimestamps);
        return tempTimestamps;
    }

    private List<TimedInventorySave> getTimedInventorySaves() {
        return new ArrayList<>(data.values());
    }

    private void loadDataFromConfig() { //Daten aus der Configdatei in die Klasse laden
        ArrayList<TimedInventorySave> saves;
        saves = (ArrayList<TimedInventorySave>) config.get("inventories", new ArrayList<>());
        data = new HashMap<>();
        for (int i = 0; i < saves.size(); i++) {
            data.put(saves.get(i).getTimestamp(), saves.get(i));
        }

    }

}
