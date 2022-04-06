package me.Sam.GPGui.Flags;

import me.Sam.GPGui.DisplayType;

import java.util.List;

public interface GUIFlag {

    String getDisplayName();

    List<String> getDescription();

    DisplayType getDisplayType();

    int getGUISlot();

    String getFlagDefName();
}
