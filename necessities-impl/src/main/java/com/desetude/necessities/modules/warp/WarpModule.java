package com.desetude.necessities.modules.warp;

import co.aikar.commands.BukkitCommandManager;
import com.desetude.modularity.Module;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.InjectConfig;
import com.desetude.necessities.configurate.PluginLogger;
import com.desetude.necessities.lifecycle.InitializationEvent;
import com.desetude.necessities.modules.warp.commands.WarpCommands;
import com.desetude.necessities.warp.WarpService;
import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

import java.util.logging.Logger;

@Module(name = "warp")
public class WarpModule implements Listener {

    @InjectConfig("warp") private Config<WarpConfig> config;
    @Inject private Plugin plugin;
    @Inject @PluginLogger private Logger logger;
    @Inject private BukkitCommandManager commandManager;

    @EventHandler
    public void onInit(InitializationEvent event) {
        this.config.load();
        WarpService service = new WarpServiceProvider(this.config);
        Bukkit.getServicesManager().register(WarpService.class, service, this.plugin, ServicePriority.Normal);

        this.commandManager.registerCommand(new WarpCommands(service));

        this.logger.info("Initialized warp module.");
    }

}
