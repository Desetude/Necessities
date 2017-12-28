package com.desetude.necessities.module;

import ninja.leaping.configurate.objectmapping.Setting;

import java.util.HashMap;
import java.util.Map;

public class ModulesConfig {

    @Setting(comment = "Modules: warp")
    private Map<String, Boolean> modules;

    public ModulesConfig() {
        this.modules = new HashMap<>();
        this.modules.put("warp", true);
    }

    public Map<String, Boolean> getModules() {
        return this.modules;
    }

}
