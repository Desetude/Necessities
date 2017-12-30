package com.desetude.necessities.modules.warp;

import co.aikar.commands.BukkitCommandManager;
import com.desetude.modularity.Module;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.InjectConfig;
import com.desetude.necessities.module.LifecycleListener;
import com.desetude.necessities.warp.WarpService;
import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

@Module(name = "warp", injectorModules = {WarpGuiceModule.class})
public class WarpModule implements LifecycleListener {

    @Inject private Plugin plugin;
    @Inject private WarpServiceProvider service;
    @InjectConfig("warp") private Config<WarpConfig> config;

    @Inject private BukkitCommandManager commandManager;
    @Inject private WarpCommands commands;

    @Override
    public void initialize() {
        this.config.load();
        Bukkit.getServicesManager().register(WarpService.class, this.service, this.plugin, ServicePriority.Normal);

        this.service.init();

        this.commandManager.registerCommand(this.commands);

        this.plugin.getLogger().info("Initialized warp module.");
    }

    @Override
    public void close() {
        this.service.close();
    }

}
