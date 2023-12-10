package me.cookieblaster.cansteinsupportutils;

import org.bukkit.plugin.java.JavaPlugin;

public final class CansteinSupportUtils extends JavaPlugin {

    private static CansteinSupportUtils instance;

    @Override
    public void onEnable() {
        instance = this;
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