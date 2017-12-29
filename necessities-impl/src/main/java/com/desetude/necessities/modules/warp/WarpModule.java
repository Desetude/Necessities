package com.desetude.necessities.modules.warp;

import co.aikar.commands.BukkitCommandManager;
import com.desetude.modularity.Module;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.InjectConfig;
import com.desetude.necessities.lifecycle.DeinitializationEvent;
import com.desetude.necessities.lifecycle.InitializationEvent;
import com.desetude.necessities.warp.WarpService;
import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

@Module(name = "warp")
public class WarpModule implements Listener {

    @InjectConfig("warp") private Config<WarpConfig> config;
    @Inject private Plugin plugin;
    @Inject private BukkitCommandManager commandManager;
    @Inject private WarpServiceProvider service;

    @EventHandler
    public void onInit(InitializationEvent event) {
        this.config.load();
        Bukkit.getServicesManager().register(WarpService.class, this.service, this.plugin, ServicePriority.Normal);

        this.service.init();

        this.commandManager.registerCommand(new WarpCommands(this.service));

        this.plugin.getLogger().info("Initialized warp module.");
    }

    @EventHandler
    public void onDeinit(DeinitializationEvent event) {
        this.service.close();
    }

}
