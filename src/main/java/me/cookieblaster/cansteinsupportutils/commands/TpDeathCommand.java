package me.cookieblaster.cansteinsupportutils.commands;

import me.cookieblaster.cansteinsupportutils.CansteinSupportUtils;
import me.cookieblaster.cansteinsupportutils.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
        if (commandSender.hasPermission("cansteinsupportutils.tpdeath")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (strings.length == 1) {
                    Player deathPlayer = Bukkit.getPlayer(strings[0]);
                    if (deathPlayer != null) {
                        Location lastDeathLocation = deathPlayer.getLastDeathLocation();
                        if (lastDeathLocation != null) {
                            if (lastDeathLocation.getY() < -60) lastDeathLocation.setY(-60);
                            player.setVelocity(new Vector(0, 0, 0));
                            player.setCanPickupItems(false);
                            player.teleport(lastDeathLocation.add(0.5, 0, 0.5));
                            player.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.success", "player", deathPlayer.getName()));
                            (new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.setGameMode(GameMode.SPECTATOR);
                                    player.setCanPickupItems(true);
                                }
                            }).runTaskLater(CansteinSupportUtils.getInstance(), 5);
                        } else
                            player.sendMessage(ConfigUtil.getPrefixedTrans("commands.tpDeath.failed", "player", deathPlayer.getName()));
                    } else
                        player.sendMessage(ConfigUtil.getPrefixedTrans("commands.playerNotFound", "player", strings[0]));
                } else player.sendMessage(ConfigUtil.getPrefixedTrans("commands.invalidSyntax"));
            } else commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPlayer"));
        } else commandSender.sendMessage(ConfigUtil.getPrefixedTrans("commands.noPermission"));
        return true;
    }
}