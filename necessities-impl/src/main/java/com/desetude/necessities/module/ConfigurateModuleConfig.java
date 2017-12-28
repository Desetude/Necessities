package com.desetude.necessities.module;

import com.desetude.modularity.config.ModuleConfig;
import com.desetude.necessities.configurate.Config;

public class ConfigurateModuleConfig implements ModuleConfig {

    private Config<ModulesConfig> config;

    public ConfigurateModuleConfig(Config<ModulesConfig> config) {
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
