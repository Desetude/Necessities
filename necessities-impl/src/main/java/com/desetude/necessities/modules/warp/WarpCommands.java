package com.desetude.necessities.modules.warp;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import com.desetude.modularity.injector.AutoRegister;
import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.WarpService;
import com.desetude.necessities.warp.event.WarpUseEvent;
import com.google.inject.Inject;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AutoRegister
public class WarpCommands extends BaseCommand {

    @Inject private WarpService service;
    @Inject private Economy econ;

    @CommandAlias("setwarp")
    public void setWarp(Player player, String warp) {
        this.service.setWarp(warp, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Successfully set the warp '" + warp + "' to your current location.");
    }

    @CommandAlias("delwarp")
    public void delWarp(Player player, String warp) {
        if (this.service.removeWarp(warp)) {
            player.sendMessage(ChatColor.GREEN + "Successfully removed the warp '" + warp + "'.");
        } else {
            player.sendMessage(ChatColor.RED + "Failed to find the warp '" + warp + "'.");
        }
    }

    @CommandAlias("setwarpcost")
    public void setWarpCost(Player player, String warp, Double cost) {
        Optional<Warp> opt = this.service.getWarp(warp);
        if (!opt.isPresent()) {
            player.sendMessage(ChatColor.RED + "Invalid warp.");
            return;
        }

        opt.get().setCost(cost);
        player.sendMessage(ChatColor.GREEN + "Successfully set the cost of the warp " + warp + " to " + cost + ".");
    }

    @CommandAlias("warp")
    public void warp(Player player, String warpName) {
        Optional<Warp> opt = this.service.getWarp(warpName);

        if (!opt.isPresent()) {
            player.sendMessage(ChatColor.RED + "Invalid warp.");
            return;
        }

        Warp warp = opt.get();

        Optional<Location> loc = warp.getLocation();

        if (!loc.isPresent()) {
            player.sendMessage(ChatColor.RED + "Failed to get location. Is the world loaded?");
            return;
        }

        Optional<Double> cost = warp.getCost();
        if (cost.isPresent() && !this.econ.has(player, cost.get())) {
            player.sendMessage(ChatColor.RED + "You need a total of " +
                    ChatColor.GREEN + this.econ.format(cost.get()) + ChatColor.RED + " in order to use this warp.");
            return;
        }

        WarpUseEvent event = new WarpUseEvent(warp, player);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        if (cost.isPresent()) {
            EconomyResponse resp = this.econ.withdrawPlayer(player, cost.get());
            Validate.isTrue(resp.type == EconomyResponse.ResponseType.SUCCESS, "Warping transaction failed "
                    + "after confirming player had sufficient funds. Was their balance changed from another thread?");
        }

        player.teleport(loc.get());
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Warped!");
    }

    @CommandAlias("warps")
    public void warps(Player player) {
        List<Warp> warps = this.service.getAllWarps();

        player.sendMessage(ChatColor.GOLD + "There are " + ChatColor.RED + ChatColor.GOLD + warps.size() + " warps.");

        player.sendMessage(ChatColor.GOLD + "Warps" + ChatColor.RESET + ": " +
                String.join(", ", warps.stream().map(Warp::getName).collect(Collectors.toList()))
        );
    }

}
