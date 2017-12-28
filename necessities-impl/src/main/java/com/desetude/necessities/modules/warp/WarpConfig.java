package com.desetude.necessities.modules.warp;

import ninja.leaping.configurate.objectmapping.Setting;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class WarpConfig {

    @Setting
    private List<WarpNode> warps;

    public WarpConfig() {
        this.warps = new ArrayList<>();
        this.warps.add(new WarpNode(new Location(Bukkit.getWorlds().get(0), 0.0, 65.0, 0.0), "default"));
    }

    public List<WarpNode> getWarps() {
        return warps;
    }

}
