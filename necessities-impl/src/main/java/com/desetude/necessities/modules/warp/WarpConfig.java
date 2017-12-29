package com.desetude.necessities.modules.warp;

import ninja.leaping.configurate.objectmapping.Setting;

public class WarpConfig {

    @Setting("per-warp-permission") private boolean usePerWarpPermissions;

    public boolean usePerWarpPermissions() {
        return this.usePerWarpPermissions;
    }

}
