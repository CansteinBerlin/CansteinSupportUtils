package me.cookieblaster.cansteinsupportutils.commands;

import de.themoep.minedown.adventure.MineDown;
import me.cookieblaster.cansteinsupportutils.storage.InventoryDeathConfig;
import me.cookieblaster.cansteinsupportutils.storage.TimedInventorySave;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DeathHistoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("cansteinsupportutils.deathHistory")) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPermission"));
            return true;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPlayer"));
            return true;
        }
        Player executor = (Player) commandSender;
        if (strings.length > 1) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.deathCommand.invalidSyntax", "command", command.getName()));
            return true;
        }
        Player deathPlayer;
        if (strings.length == 0) deathPlayer = executor;
        else {
            deathPlayer = Bukkit.getPlayer(strings[0]);
            if (deathPlayer == null) {
                commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.playerNotFound", "player", strings[0]));
                return true;
            }
        }

        InventoryDeathConfig deathConfig = new InventoryDeathConfig(deathPlayer);
        List<Long> deathTimestamps = deathConfig.getTimestamps();
        Collections.reverse(deathTimestamps);
        commandSender.sendMessage(ConfigUtil.getTrans("commands.deathHistory.header", "player", deathPlayer.getName()));
        for (int i = 0; i < deathTimestamps.size(); i++) {
            informationToPlayer(executor, deathConfig.getInventory(deathTimestamps.get(i)), deathPlayer);
        }
        return false;
    }

    private void informationToPlayer(Player executor, TimedInventorySave inventory, Player deathPlayer) {
        Date deathTimestamp = new Date(inventory.getTimestamp());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        String text = ConfigUtil.getTrans("commands.deathHistory.base").replace("%datum%", dateFormat.format(deathTimestamp));
        if (executor.hasPermission("cansteinsupportutils.inventoryReset")) {
            text = text + ConfigUtil.getStringFromConfig("lang.commands.deathHistory.reset").replace("%params%", deathPlayer.getName() + " " + inventory.getTimestamp());
        }
        if (executor.hasPermission("cansteinsupportutils.inventoryShow"))
            text = text + ConfigUtil.getStringFromConfig("lang.commands.deathHistory.lookup").replace("%params%", deathPlayer.getName() + " " + inventory.getTimestamp());
        executor.sendMessage(new MineDown(text).toComponent());
    }
}