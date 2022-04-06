package me.Sam.GPGui.Listeners;

import me.Sam.GPGui.*;
import me.Sam.GPGui.Flags.GUIFlag;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIListener implements Listener {

    public static ClaimUtils claimUtils = new ClaimUtils();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(Utils.chat("&eClaim Editor"))) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) {
                return;
            }
            ItemMeta itemMeta = clickedItem.getItemMeta();
            if (itemMeta.getDisplayName().equalsIgnoreCase(" ")) {
                return;
            }
            String itemName = itemMeta.getDisplayName();
            for (GUIFlag guiFlag : GPGui.guiFlagHash) {
                String guiFlagName = guiFlag.getDisplayName();
                if (itemName.equalsIgnoreCase(guiFlagName)) {
                    player.playSound(player.getLocation(), Sound.UI_LOOM_SELECT_PATTERN, 20, 1);
                    if (guiFlag.getDisplayType() == DisplayType.DEFAULT) {
                        claimUtils.toggleFlag(player, claimUtils.getClaimAtLocation(player, player.getLocation()), guiFlag.getFlagDefName());
                    } else if (guiFlag.getDisplayType() == DisplayType.MESSAGE) {
                        switch (event.getClick()) {
                            case LEFT:
                                switch (guiFlag.getFlagDefName()) {
                                    case "EnterMessage":
                                        player.sendMessage(Utils.chat(Utils.prefix + "&fPlease type in the message you would like to set it to. Type {#8bf7f7}cancel &fto cancel."));
                                        ChatListener.enterMessage.add(player);
                                        player.closeInventory();
                                        return;
                                    case "ExitMessage":
                                        player.sendMessage(Utils.chat(Utils.prefix + "&fPlease type in the message you would like to set it to. Type {#8bf7f7}cancel &fto cancel."));
                                        ChatListener.exitMessage.add(player);
                                        player.closeInventory();
                                        return;
                                }
                                break;
                            case RIGHT:
                                switch (guiFlag.getFlagDefName()) {
                                    case "EnterMessage":
                                        if (claimUtils.flagStatus(player, claimUtils.getClaimAtLocation(player, player.getLocation()), "EnterMessage").equalsIgnoreCase("false")) {
                                            player.sendMessage(Utils.chat(Utils.prefix + "&cThis flag is not turned on!"));
                                            return;
                                        }
                                        claimUtils.toggleFlag(player, claimUtils.getClaimAtLocation(player, player.getLocation()), guiFlag.getFlagDefName());
                                        break;
                                    case "ExitMessage":
                                        if (claimUtils.flagStatus(player, claimUtils.getClaimAtLocation(player, player.getLocation()), "ExitMessage").equalsIgnoreCase("false")) {
                                            player.sendMessage(Utils.chat(Utils.prefix + "&cThis flag is not turned on!"));
                                            return;
                                        }
                                        claimUtils.toggleFlag(player, claimUtils.getClaimAtLocation(player, player.getLocation()), guiFlag.getFlagDefName());
                                        break;
                                }
                                break;
                        }
                    }
                }
            }
            GUI.openGUI(player);
        }
    }
}
