package me.cookieblaster.cansteinsupportutils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
                            player.setGameMode(GameMode.SPECTATOR);
                            player.teleport(lastDeathLocation.add(0.5, 0, 0.5));
                            player.sendMessage("§aDu wurdest zum Todespunkt von§6 " + deathPlayer.getName() + " §ateleportiert (Y>-60)");

                        } else
                            player.sendMessage("§cDer*die Spieler*n §6" + deathPlayer.getName() + " $eist noch nie gestorben. Du kannst gratulieren!");
                    } else player.sendMessage("§cDas ist kein Spielername");
                } else player.sendMessage("§cBitte ein Spielername");
            } else commandSender.sendMessage("Du bist kein Lebewesen");
        } else commandSender.sendMessage("$cDu hast keine Rechte");
        return true;
    }
}