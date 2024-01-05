package me.cookieblaster.cansteinsupportutils.commands;

import me.cookieblaster.cansteinsupportutils.storage.InventoryDeathConfig;
import me.cookieblaster.cansteinsupportutils.storage.TimedInventorySave;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InventoryResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("cansteinsupportutils.inventoryReset")) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPermission"));
            return true;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPlayer"));
            return true;
        }
        if (strings.length != 2) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryReset.invalidSyntax", "command", command.getName()));
            return true;
        }
        Player player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.playerNotFound", "player", strings[0]));
            return true;
        }
        if (!isLong(strings[1])) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryReset.invalidSyntax", "command", command.getName()));
            return true;
        }

        InventoryDeathConfig inventoryDeathConfig = new InventoryDeathConfig(player);
        TimedInventorySave timedInventorySave = inventoryDeathConfig.getInventory(Long.parseLong(strings[1]));
        if (timedInventorySave == null) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryReset.timestamp", "timestamp", strings[1]));
            return true;
        }
        player.getInventory().setContents(timedInventorySave.getInventory());
        commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.inventoryReset.success", "player", strings[0]));
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
