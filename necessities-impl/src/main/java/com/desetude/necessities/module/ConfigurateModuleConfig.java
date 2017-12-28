package com.desetude.necessities.module;

import com.desetude.modularity.config.ModuleConfig;
import com.desetude.necessities.NecessitiesConfig;
import com.desetude.necessities.configurate.Config;

public class ConfigurateModuleConfig implements ModuleConfig {

    private Config<NecessitiesConfig> config;

    public ConfigurateModuleConfig(Config<NecessitiesConfig> config) {
        this.config = config;
    }

    @Override
    public boolean isSet(String name) {
        return this.config.get().getModules().containsKey(name);
    }

    @Override
    public boolean isEnabled(String name) {
        return this.config.get().getModules().get(name);
    }

}
