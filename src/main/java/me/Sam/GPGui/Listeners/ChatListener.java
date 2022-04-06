package me.Sam.GPGui.Listeners;

import me.Sam.GPGui.ClaimUtils;
import me.Sam.GPGui.GUI;
import me.Sam.GPGui.Utils;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;

public class ChatListener implements Listener {

    public static HashSet<Player> enterMessage = new HashSet<>();
    public static HashSet<Player> exitMessage = new HashSet<>();
    public static ClaimUtils claimUtils = new ClaimUtils();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (enterMessage.contains(player)) {
            event.setCancelled(true);
            if (message.equalsIgnoreCase("cancel")) {
                player.sendMessage(Utils.chat(Utils.prefix + "&cMessage setting cancelled."));
                enterMessage.remove(player);
                return;
            }
            Claim claim = claimUtils.getClaimAtLocation(player, player.getLocation());
            claimUtils.toggleFlag(player, claim, "EnterMessage", Utils.chat(message));
            player.sendMessage(Utils.chat(Utils.prefix + "&fSuccessfully set your enter message to \"{#8bf7f7}" + message + "&f\"!"));
            enterMessage.remove(player);
            GUI.openGUI(player);
            return;

        } else if (exitMessage.contains(player)) {
            event.setCancelled(true);
            if (message.equalsIgnoreCase("cancel")) {
                player.sendMessage(Utils.chat(Utils.prefix + "&cMessage setting cancelled."));
                exitMessage.remove(player);
                return;
            }
            Claim claim = claimUtils.getClaimAtLocation(player, player.getLocation());
            claimUtils.toggleFlag(player, claim, "ExitMessage", Utils.chat(message));
            player.sendMessage(Utils.chat(Utils.prefix + "&fSuccessfully set your exit message to \"{#8bf7f7}" + message + "&f\"!"));
            exitMessage.remove(player);
            GUI.openGUI(player);
            return;
        }
    }
}
