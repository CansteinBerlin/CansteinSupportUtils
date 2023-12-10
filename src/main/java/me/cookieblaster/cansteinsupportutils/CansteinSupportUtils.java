package me.cookieblaster.cansteinsupportutils;

import org.bukkit.plugin.java.JavaPlugin;

public final class CansteinUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("tpdeath").setExecutor(new TpDeathCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}