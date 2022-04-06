package me.Sam.GPGui.Commands;

import me.Sam.GPGui.ClaimUtils;
import me.Sam.GPGui.GUI;
import me.Sam.GPGui.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClaimFlagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ClaimUtils claimUtils = new ClaimUtils();
            if (!claimUtils.isClaimAtLocation(player, player.getLocation())) {
                player.sendMessage(Utils.chat(Utils.prefix + "&cThere is no claim at this location!"));
                return false;
            }
            if (!claimUtils.canBuildAtLocation(player)) {
                player.sendMessage(Utils.chat(Utils.prefix + "&cYou don't have permission to edit this claim!"));
                return false;
            }
            player.sendMessage(Utils.chat(Utils.prefix + "&fOpening claim {#8bf7f7}editor!"));
            GUI.openGUI(player);
        }
        return false;
    }
}
