package me.cookieblaster.cansteinsupportutils;

import me.cookieblaster.cansteinsupportutils.commands.TpDeathCommand;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class CansteinSupportUtils extends JavaPlugin {

    private static CansteinSupportUtils instance;
    public static String PREFIX;

    @Override
    public void onEnable() {
        instance = this;
        saveResource("config.yml", false); //ggf ändern!
        PREFIX = ConfigUtil.getColoredStringFromConfig("prefix");
        getCommand("tpdeath").setExecutor(new TpDeathCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CansteinSupportUtils getInstance() {
        return instance;
    }
}