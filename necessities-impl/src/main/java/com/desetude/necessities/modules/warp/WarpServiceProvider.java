package com.desetude.necessities.modules.warp;

import com.desetude.modularity.injector.AutoRegister;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.InjectConfig;
import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.WarpService;
import com.google.common.collect.ImmutableList;
import ninja.leaping.configurate.objectmapping.Setting;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoRegister
public class WarpServiceProvider implements WarpService {

    @InjectConfig("data/warps") private Config<WarpsDataConfig> config;

    public void init() {
        this.config.load();
    }

    public void close() {
        this.config.save();
    }

    private List<WarpNode> getWarps() {
        return this.config.get().getWarps();
    }

    @Override
    public Optional<Warp> getWarp(String name) {
        return getWarps().stream().filter(node -> node.getName().equals(name)).map(node -> (Warp) node).findFirst();
    }

    @Override
    public void addWarp(String name, Location location) {
        removeWarp(name);
        getWarps().add(new WarpNode(location, name));
    }

    @Override
    public boolean removeWarp(String name) {
        return getWarps().removeIf(warp -> warp.getName().equals(name));
    }

    @Override
    public List<Warp> getAllWarps() {
        return ImmutableList.copyOf(getWarps());
    }

    private static class WarpsDataConfig {

        @Setting("warps") private List<WarpNode> warps = new ArrayList<>();

        public List<WarpNode> getWarps() {
            return warps;
        }

    }

}
