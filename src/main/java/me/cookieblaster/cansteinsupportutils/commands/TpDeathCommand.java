package me.cookieblaster.cansteinsupportutils.commands;

import me.cookieblaster.cansteinsupportutils.CansteinSupportUtils;
import me.cookieblaster.cansteinsupportutils.storage.PlayerDeathConfig;
import me.cookieblaster.cansteinsupportutils.storage.TimedInventorySave;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class TpDeathCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission("cansteinsupportutils.tpDeath")) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPermission"));
            return true;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPlayer"));
            return true;
        }
        if (strings.length != 2) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.invalidSyntax", "command", command.getName()));
            return true;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(strings[0]);
        if (!isLong(strings[1])) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.invalidSyntax", "command", command.getName()));
            return true;
        }

        PlayerDeathConfig playerDeathConfig = new PlayerDeathConfig(player.getUniqueId());
        TimedInventorySave timedInventorySave = playerDeathConfig.getInventory(Long.parseLong(strings[1]));
        if (timedInventorySave == null) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.timestamp", "timestamp", strings[1]));
            return true;
        }
        if (timedInventorySave.getLocation() == null) {
            commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.noDeathLocation"));
            return true;
        }

        Player playerToTeleport = (Player) commandSender;
        Location locationToTeleport = timedInventorySave.getLocation();
        if (locationToTeleport.getY() <= -60) locationToTeleport.setY(-60);
        playerToTeleport.setCanPickupItems(false);
        playerToTeleport.setVelocity(new Vector(0, 0, 0));
        playerToTeleport.teleport(locationToTeleport); //ist nie null

        (new BukkitRunnable() {
            @Override
            public void run() {
                playerToTeleport.setGameMode(GameMode.SPECTATOR);
                playerToTeleport.setCanPickupItems(true);
            }
        }).runTaskLater(CansteinSupportUtils.getInstance(), 5);

        commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.success", "player", strings[0]));
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
