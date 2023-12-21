package me.cookieblaster.cansteinsupportutils.utils;

import me.cookieblaster.cansteinsupportutils.CansteinSupportUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Objects;

public class ConfigUtil {

    /**
     * Gets a language string from the config, translates the color codes and replaces fields with values provided by the args argument
     *
     * @param key  The Key to the translation. "lang." + key
     * @param args A list of name value arguments to replace. Example: "player", player.getName()
     * @return Returns the string from the config with translated color codes and replaced fields
     */
    public static String getTrans(String key, String... args) {
        String lang = CansteinSupportUtils.getInstance().getConfig().getString("lang." + key, "&cUnknown language key \"&6" + key + "&c\"");
        for (int i = 0; i + 1 < args.length; i += 2) {
            lang = lang.replace("%" + args[i] + "%", args[i + 1]);
        }
        return ChatColor.translateAlternateColorCodes('&', lang);
    }

    /**
     * The same as {@link #getTrans(String, String...)} but with an added index infront of it
     */
    public static String getPrefixedTrans(String key, String... args) {
        return CansteinSupportUtils.PREFIX + getTrans(key, args);
    }

    /**
     * Gets an int from the default config. If not found defaults to 0
     *
     * @param key Path to the int
     * @return The int from the config
     */
    public static int getIntFromConfig(String key) {
        return getIntFromConfig(key, 0);
    }

    /**
     * Gets an int from the default config
     *
     * @param key Path to the int
     * @param def The default value
     * @return The int from the config
     */
    public static int getIntFromConfig(String key, int def) {
        return CansteinSupportUtils.getInstance().getConfig().getInt(key, def);
    }

    /**
     * Gets a double from the default config. If not found defaults to 0d
     *
     * @param key Path to the double
     * @return The value from the config
     */
    public static double getDoubleFromConfig(String key) {
        return getDoubleFromConfig(key, 0);
    }

    /**
     * Gets a double from the default config.
     *
     * @param key Path to the double
     * @param def The default value to return if path does not exist
     * @return The value from the config
     */
    public static double getDoubleFromConfig(String key, double def) {
        return CansteinSupportUtils.getInstance().getConfig().getDouble(key, def);
    }

    /**
     * Gets a string from the default config. If not found defaults to ""
     *
     * @param key Path to the string
     * @return The value from the config
     */
    public static String getStringFromConfig(String key) {
        return getStringFromConfig(key, "");
    }

    /**
     * Gets a string from the default config.
     *
     * @param key Path to the double
     * @param def The default value to return if path does not exist
     * @return The value from the config
     */
    public static String getStringFromConfig(String key, String def) {
        return CansteinSupportUtils.getInstance().getConfig().getString(key, def);
    }

    public static String getColoredStringFromConfig(String key) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(CansteinSupportUtils.getInstance().getConfig().getString(key)));
    }

    /**
     * Gets a boolean from the default config. If not found defaults to false
     *
     * @param key Path to the boolean
     * @return The value from the config
     */
    public static boolean getBooleanFromConfig(String key) {
        return getBooleanFromConfig(key, false);
    }

    /**
     * Gets a boolean from the default config.
     *
     * @param key Path to the double
     * @param def The default value of the boolean if path does not exist
     * @return The value from the config
     */
    public static boolean getBooleanFromConfig(String key, boolean def) {
        return CansteinSupportUtils.getInstance().getConfig().getBoolean(key, def);
    }

    /**
     * Gets a Sound from the default config.
     *
     * @param key Path to the double
     * @return The value from the config
     */
    public static Sound getSoundFromConfig(String key) {
        return Sound.valueOf(CansteinSupportUtils.getInstance().getConfig().getString(key));
    }

    /**
     * Gets a Material from the default config.
     *
     * @param key Path to the double
     * @return The value from the config
     */
    public static Material getMaterialFromConfig(String key) {
        return Material.valueOf(CansteinSupportUtils.getInstance().getConfig().getString(key));
    }


}
