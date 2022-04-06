package me.Sam.GPGui;

import me.Sam.GPGui.Commands.ClaimFlagCommand;
import me.Sam.GPGui.Flags.BaseFlag;
import me.Sam.GPGui.Flags.GUIFlag;
import me.Sam.GPGui.Listeners.ChatListener;
import me.Sam.GPGui.Listeners.ClaimCreationListener;
import me.Sam.GPGui.Listeners.GUIListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class GPGui extends JavaPlugin {

    public static GPGui instance;
    public static HashSet<GUIFlag> guiFlagHash = new HashSet<>();

    public void onEnable() {
        instance = this;
        getLogger().info("Sams GPGui plugin enabled.");
        PluginManager pluginManager = getServer().getPluginManager();
        if (pluginManager.getPlugin("GriefPrevention") == null || pluginManager.getPlugin("GriefPreventionFlags") == null) {
            getLogger().warning("This plugin cannot work without Griefprevention && GriefpreventionFlags!");
            pluginManager.disablePlugin(this);
        }
        getCommand("claimflags").setExecutor(new ClaimFlagCommand());
        pluginManager.registerEvents(new GUIListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new ClaimCreationListener(), this);
        initGUIFlags();
    }

    public void initGUIFlags() {
        GUIFlag allowpvp = new BaseFlag("{#8bf7f7}Allow PvP", DisplayType.DEFAULT, 10, "AllowPvP", "&fAllows players to {#8bf7f7}fight &feachother!");
        GUIFlag entermessage = new BaseFlag("{#8bf7f7}Enter Message", DisplayType.MESSAGE, 11, "EnterMessage", "&fSet a message for when your claim is {#8bf7f7}entered!");
        GUIFlag exitmessage = new BaseFlag("{#8bf7f7}Exit Message", DisplayType.MESSAGE, 12, "ExitMessage","&fSet a message for when your claim is {#8bf7f7}left!");
        GUIFlag noenderpearl = new BaseFlag("{#8bf7f7}No Enderpearls", DisplayType.DEFAULT, 13,"NoEnderPearl", "&fPrevent {#8bf7f7}enderpearling!");
        GUIFlag nofirespread = new BaseFlag("{#8bf7f7}No Firespread", DisplayType.DEFAULT, 14,"NoFireSpread",  "&fPrevent {#8bf7f7}fire spread!");
        GUIFlag noexplosiondamage = new BaseFlag("{#8bf7f7}No Explosion Damage", DisplayType.DEFAULT, 15,"NoExplosionDamage", "&fPrevent {#8bf7f7}explosive damage!");
        GUIFlag nomonsterspawns = new BaseFlag("{#8bf7f7}No Monster Spawns", DisplayType.DEFAULT, 16,"NoMonsterSpawns", "&fPrevent {#8bf7f7}monster spawning!");
        GUIFlag nodooropening = new BaseFlag("{#8bf7f7}No Door Opening", DisplayType.DEFAULT, 21,"NoOpenDoors", "&fPrevent others from opening {#8bf7f7}doors!");
        GUIFlag petdamage = new BaseFlag("{#8bf7f7}Pet Damage", DisplayType.DEFAULT, 22, "NoPetDamage","&fAllow or disable {#8bf7f7}pet damage!");
        GUIFlag viewcontainers = new BaseFlag("{#8bf7f7}Container Viewing", DisplayType.DEFAULT, 23, "ViewContainers","&fAllow people to view your {#8bf7f7}containers!", "&f(Only view, can't edit)");
        guiFlagHash.add(allowpvp);
        guiFlagHash.add(entermessage);
        guiFlagHash.add(exitmessage);
        guiFlagHash.add(noenderpearl);
        guiFlagHash.add(nofirespread);
        guiFlagHash.add(noexplosiondamage);
        guiFlagHash.add(nomonsterspawns);
        guiFlagHash.add(nodooropening);
        guiFlagHash.add(petdamage);
        guiFlagHash.add(viewcontainers);
    }


}
