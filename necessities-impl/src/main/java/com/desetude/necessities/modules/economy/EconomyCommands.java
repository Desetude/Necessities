package com.desetude.necessities.modules.economy;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import com.desetude.modularity.injector.AutoRegister;
import com.google.inject.Inject;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@AutoRegister
public class EconomyCommands extends BaseCommand {

    @Inject private Economy econ;

    @CommandAlias("bal")
    public void bal(Player player) {
        player.sendMessage(ChatColor.GOLD + "Balance: " + ChatColor.GREEN + this.econ.format(this.econ.getBalance(player)));
    }

}
