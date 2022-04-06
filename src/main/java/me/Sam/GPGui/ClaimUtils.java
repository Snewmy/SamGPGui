package me.Sam.GPGui;

import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.flags.FlagDefinition;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClaimUtils {

    public boolean canBuildAtLocation(Player player) {
        String noBuildReason = GriefPrevention.instance.allowBuild(player, player.getLocation());
        if (noBuildReason == null) {
            return true;
        }
        return false;
    }

    public boolean isClaimAtLocation(Player player, Location location) {
        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, false, playerData.lastClaim);
        if (claim != null) {
            return true;
        }
        return false;
    }

    public Claim getClaimAtLocation(Player player, Location location) {
        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, false, playerData.lastClaim);
        if (claim == null) {
            GPGui.instance.getLogger().warning("Claim is null");
            return null;
        }
        return claim;
    }

    public String flagStatus(Player player, Claim claim, String flagName) {
        FlagDefinition def = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName(flagName);
        if (def == null) {
            return "incorrect name";
        }
        Flag flag = def.getFlagInstanceAtLocation(player.getLocation(), player);
        if (flag == null) {
            return "false";
        }
        return "true";
    }

    public void toggleFlag(Player player, Claim claim, String flagName, String... stringArgs) {
        FlagDefinition def = GPFlags.getInstance().getFlagManager().getFlagDefinitionByName(flagName);
        if (def == null) {
            player.sendMessage(Utils.chat(Utils.prefix + "&cSomething went wrong, please notify Sam. Sorry!"));
            return;
        }
        Flag flag = def.getFlagInstanceAtLocation(player.getLocation(), player);
        if (flag == null) {
            //Turn it on
            if (def.getName().equalsIgnoreCase("EnterMessage") || def.getName().equalsIgnoreCase("ExitMessage")) {
                StringBuilder sb = new StringBuilder();
                for (String s : stringArgs) {
                    sb.append(s + " ");
                }
                String string = sb.toString().trim();
                SetFlagResult result = GPFlags.getInstance().getFlagManager().setFlag(claim.getID().toString(), def, true, string);
                GPFlags.getInstance().getFlagManager().save();
                player.sendMessage(Utils.chat(Utils.prefix + "&fSuccessfully toggled on the {#8bf7f7}" + def.getName() + " &fflag and set it to {#8bf7f7}" + string));
            } else {
                SetFlagResult result = GPFlags.getInstance().getFlagManager().setFlag(claim.getID().toString(), def, true);
                GPFlags.getInstance().getFlagManager().save();
                player.sendMessage(Utils.chat(Utils.prefix + "&fSuccessfully toggled on the {#8bf7f7}" + def.getName() + " &fflag!"));
            }
        } else {
            SetFlagResult result = GPFlags.getInstance().getFlagManager().unSetFlag(claim, def);
            GPFlags.getInstance().getFlagManager().save();
            player.sendMessage(Utils.chat(Utils.prefix + "&fSuccessfully toggled off the {#8bf7f7}" + def.getName() + " &fflag!"));
        }
    }
}
