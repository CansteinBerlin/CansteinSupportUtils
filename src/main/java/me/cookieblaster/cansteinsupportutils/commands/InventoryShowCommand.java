package me.cookieblaster.cansteinsupportutils.commands;

import me.cookieblaster.cansteinsupportutils.storage.PlayerDeathConfig;
import me.cookieblaster.cansteinsupportutils.storage.TimedInventorySave;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class InventoryShowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("cansteinsupportutils.inventoryShow")) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPermission"));
            return true;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPlayer"));
            return true;
        }
        if (strings.length != 2) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryShow.invalidSyntax", "command", command.getName()));
            return true;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(strings[0]);
        if (!isLong(strings[1])) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryShow.invalidSyntax", "command", command.getName()));
            return true;
        }

        PlayerDeathConfig playerDeathConfig = new PlayerDeathConfig(player.getUniqueId());
        TimedInventorySave timedInventorySave = playerDeathConfig.getInventory(Long.parseLong(strings[1]));
        if (timedInventorySave == null) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryShow.timestamp", "timestamp", strings[1]));
            return true;
        }

        Inventory showInventory = Bukkit.createInventory(null, 9 * 6, player.getName() + "´s Inventory");
        for (int i = 0; i < 9; i++) {
            showInventory.setItem(9 * 3 + i, timedInventorySave.getInventory()[i]);
        }
        for (int i = 9; i < 36; i++) {
            showInventory.setItem(i - 9, timedInventorySave.getInventory()[i]);
        }
        for (int i = 36; i < 40; i++) {
            showInventory.setItem(9 * 4 + 3 - (i % 9), timedInventorySave.getInventory()[i]);
        }
        showInventory.setItem(9 * 4 + 4, timedInventorySave.getInventory()[40]);
        ((Player) commandSender).openInventory(showInventory);

        commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryShow.success", "player", strings[0]));
        return true;
    }

    private boolean isLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException _) {
            return false;
        }
    }
}
