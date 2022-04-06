package me.Sam.GPGui.Listeners;

import me.Sam.GPGui.Utils;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClaimCreationListener implements Listener {

    @EventHandler
    public void onClaimCreate(ClaimCreatedEvent event) {
        if (event.getCreator() instanceof Player) {
            Player player = (Player) event.getCreator();
            player.sendMessage(Utils.chat(Utils.prefix + "&fType {#8bf7f7}/claimflags &fto edit various claim settings!"));
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 20, 1);
        }
    }
}
