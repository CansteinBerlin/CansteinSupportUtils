package me.cookieblaster.cansteinsupportutils;

import me.cookieblaster.cansteinsupportutils.commands.DeathHistoryCommand;
import me.cookieblaster.cansteinsupportutils.commands.InventoryResetCommand;
import me.cookieblaster.cansteinsupportutils.commands.InventoryShowCommand;
import me.cookieblaster.cansteinsupportutils.commands.TpDeathCommand;
import me.cookieblaster.cansteinsupportutils.listener.PlayerDeathListener;
import me.cookieblaster.cansteinsupportutils.storage.TimedInventorySave;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class CansteinSupportUtils extends JavaPlugin {

    private static CansteinSupportUtils instance;
    public static String PREFIX;

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationSerialization.registerClass(TimedInventorySave.class);
        saveResource("config.yml", false); //ggf ändern!
        PREFIX = ConfigUtil.getColoredStringFromConfig("prefix");
        getCommand("tpdeath").setExecutor(new TpDeathCommand());
        getCommand("deathHistory").setExecutor(new DeathHistoryCommand());
        getCommand("inventoryReset").setExecutor(new InventoryResetCommand());
        getCommand("inventoryShow").setExecutor(new InventoryShowCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CansteinSupportUtils getInstance() {
        return instance;
    }
}