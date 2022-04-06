package me.Sam.GPGui;

import me.Sam.GPGui.Flags.GUIFlag;
import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.GPFlags;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    public static void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36, Utils.chat("&eClaim Editor"));
        ClaimUtils claimUtils = new ClaimUtils();
        ItemStack filler = new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE, 1).setDisplayName(" ").toItemStack();
        for (GUIFlag guiFlag : GPGui.guiFlagHash) {
            List<String> lore = new ArrayList<>();
            String flagStatus = claimUtils.flagStatus(player, claimUtils.getClaimAtLocation(player, player.getLocation()), guiFlag.getFlagDefName());
            lore.add(Utils.chat("&fEnabled: {#8bf7f7}" + flagStatus));
            if (guiFlag.getDisplayType() == DisplayType.MESSAGE) {
                if (flagStatus.equalsIgnoreCase("true")) {
                    Flag flag = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName(guiFlag.getFlagDefName()).getFlagInstanceAtLocation(player.getLocation(), player);
                    StringBuilder sb = new StringBuilder();
                    for (String s : flag.getParametersArray()) {
                        sb.append(s + " ");
                    }
                    lore.add(Utils.chat("&fMessage: {#8bf7f7}" + sb.toString().trim()));
                }
                lore.add(Utils.chat("{#8bf7f7}Left-Click &fto set"));
                lore.add(Utils.chat("{#8bf7f7}Right-Click &fto disable"));
            }
            lore.addAll(guiFlag.getDescription());
            Material material;
            if (flagStatus.equalsIgnoreCase("true")) {
                material = Material.ENCHANTED_BOOK;
            } else {
                material = Material.BOOK;
            }
            ItemStack itemStack = new ItemBuilder(material, 1).setDisplayName(guiFlag.getDisplayName()).setLore(lore).toItemStack();
            inventory.setItem(guiFlag.getGUISlot(), itemStack);
        }
        List<Integer> emptySlots = new ArrayList<>();
        for (int x = 0; x < inventory.getSize(); x++) {
            if (inventory.getItem(x) == null || inventory.getItem(x).getType() == Material.AIR) {
                emptySlots.add(x);
            }
        }
        for (Integer i : emptySlots) {
            inventory.setItem(i, filler);
        }
        player.openInventory(inventory);
    }


}
