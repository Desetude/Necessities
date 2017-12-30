package com.desetude.necessities.modules.economy;

import co.aikar.commands.BukkitCommandManager;
import com.desetude.modularity.Module;
import com.desetude.necessities.module.LifecycleListener;
import com.google.inject.Inject;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

@Module(name = "economy", injectorModules = {EconomyGuiceModule.class})
public class EconomyModule implements LifecycleListener {

    @Inject private Plugin plugin;
    @Inject private NecessitiesEconomy econ;

    @Inject private BukkitCommandManager commandManager;
    @Inject private EconomyCommands commands;

    @Override
    public void initialize() {
        Bukkit.getServicesManager().register(Economy.class, this.econ, this.plugin, ServicePriority.High);

        this.commandManager.registerCommand(this.commands);
    }

    @Override
    public void close() {
        Bukkit.getServicesManager().unregister(Economy.class, this.econ);
    }

}
