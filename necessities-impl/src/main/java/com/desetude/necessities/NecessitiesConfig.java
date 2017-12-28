package com.desetude.necessities;

import ninja.leaping.configurate.objectmapping.Setting;

import java.util.HashMap;
import java.util.Map;

public class NecessitiesConfig {

    @Setting
    private Map<String, Boolean> modules;

    public NecessitiesConfig() {
        this.modules = new HashMap<>();
        this.modules.put("warp", true);
    }

    public Map<String, Boolean> getModules() {
        return this.modules;
    }

}
