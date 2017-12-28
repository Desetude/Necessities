package com.desetude.necessities.modules.warp.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.WarpService;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public class WarpCommands extends BaseCommand {

    private final WarpService service;

    public WarpCommands(WarpService service) {
        this.service = service;
    }

    @CommandAlias("setwarp")
    public void setWarp(Player player, String warp) {
        this.service.addWarp(warp, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Successfully added the warp " + warp + " at your current location.");
    }

    @CommandAlias("warp")
    public void warp(Player player, String warpName) {
        Optional<Warp> warp = this.service.getWarp(warpName);

        if(!warp.isPresent()) {
            player.sendMessage(ChatColor.RED + "Invalid warp.");
            return;
        }

        Optional<Location> loc = warp.get().getLocation();

        if (!loc.isPresent()) {
            player.sendMessage(ChatColor.RED + "Failed to get location. Is the world loaded?");
            return;
        }

        player.teleport(loc.get());
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Warped!");
    }

}
