package me.cookieblaster.cansteinsupportutils.listener;

import me.cookieblaster.cansteinsupportutils.storage.PlayerDeathConfig;
import me.cookieblaster.cansteinsupportutils.storage.TimedInventorySave;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        PlayerDeathConfig playerDeathConfig = new PlayerDeathConfig(event.getPlayer().getUniqueId());
        playerDeathConfig.addInventory(new TimedInventorySave(event.getPlayer()));
    }
}
