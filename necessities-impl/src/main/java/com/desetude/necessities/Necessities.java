package com.desetude.necessities;

import co.aikar.commands.BukkitCommandManager;
import com.desetude.modularity.additionalmodules.BukkitGuiceModule;
import com.desetude.modularity.loader.ModuleLoader;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.ConfigFactory;
import com.desetude.necessities.module.ConfigurateModuleConfig;
import com.desetude.necessities.module.LifecycleListener;
import com.desetude.necessities.module.NecessitiesGuiceModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Necessities extends JavaPlugin {

    private BukkitCommandManager commandManager;
    private List<LifecycleListener> lifecycleListeners;
    private boolean loaded = false;

    @Override
    public void onEnable() {
        load();
    }

    @Override
    public void onDisable() {
        if (this.loaded) {
            this.commandManager.unregisterCommands();

            this.lifecycleListeners.forEach(LifecycleListener::close);

            this.loaded = false;
        }
    }

    public void reload() {
        //TODO: async
        load();
    }

    private void load() {
        this.commandManager = new BukkitCommandManager(this);
        ConfigFactory configFactory = new ConfigFactory(getDataFolder(), this);

        Config<NecessitiesConfig> modulesConfig = configFactory.createMapping("config", NecessitiesConfig.class);
        modulesConfig.load();

        this.lifecycleListeners = new ArrayList<>();

        new ModuleLoader()
                .addInjectorModules(
                        new BukkitGuiceModule(this),
                        new NecessitiesGuiceModule(getLogger(), configFactory, this.commandManager, this.lifecycleListeners)
                ).setConfig(new ConfigurateModuleConfig(modulesConfig))
                .load();

        this.lifecycleListeners.forEach(LifecycleListener::initialize);

        this.loaded = true;
    }

}
