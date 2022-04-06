package me.Sam.GPGui;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    Material material;
    int amount;
    HashMap<Enchantment, Integer> enchants = new HashMap<>();
    List<ItemFlag> itemFlags = new ArrayList<>();
    List<String> lore = new ArrayList<>();
    String displayName;

    public ItemBuilder(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public ItemBuilder setDisplayName(String s) {
        this.displayName = s;
        return this;
    }

    public ItemBuilder addLore(String... args) {
        for (String s : args) {
            lore.add(Utils.chat(s));
        }
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        enchants.put(enchantment, level);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        itemFlags.add(itemFlag);
        return this;
    }

    public ItemBuilder addGlow() {
        enchants.put(Enchantment.ARROW_DAMAGE, 1);
        itemFlags.add(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Utils.chat(displayName));
        itemMeta.setLore(lore);
        for (ItemFlag itemFlag : itemFlags) {
            itemMeta.addItemFlags(itemFlag);
        }
        for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
            itemMeta.addEnchant(entry.getKey(), entry.getValue(), true);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
