package com.desetude.necessities.modules.warp;

import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.WarpService;
import com.google.common.collect.ImmutableList;
import org.bukkit.Location;

import java.util.List;
import java.util.Optional;

public class WarpServiceProvider implements WarpService {

    private final Config<WarpConfig> config;

    public WarpServiceProvider(Config<WarpConfig> config) {
        this.config = config;
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

}
