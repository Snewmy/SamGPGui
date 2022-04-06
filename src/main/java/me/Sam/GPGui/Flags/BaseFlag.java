package me.Sam.GPGui.Flags;

import me.Sam.GPGui.DisplayType;
import me.Sam.GPGui.GPGui;
import me.Sam.GPGui.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class BaseFlag implements GUIFlag{

    String displayName;
    List<String> description = new ArrayList<>();
    DisplayType displayType;
    int guiSlot;
    String flagDefName;

    public BaseFlag(String displayName, DisplayType displayType, int guiSlot, String flagDefName, String... args) {
        this.displayName = Utils.chat(displayName);
        this.displayType = displayType;
        this.guiSlot = guiSlot;
        this.flagDefName = flagDefName;
        for (String s : args) {
            description.add(Utils.chat(s));
        }
        GPGui.instance.getLogger().info("Initializing " + flagDefName + " flag");
    }


    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public DisplayType getDisplayType() {
        return displayType;
    }

    @Override
    public int getGUISlot() {
        return guiSlot;
    }

    @Override
    public String getFlagDefName() {
        return flagDefName;
    }
}
