package com.desetude.necessities.modules.warp;

import com.desetude.modularity.injector.AutoRegister;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.InjectConfig;
import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.WarpService;
import com.desetude.necessities.warp.event.WarpCreateEvent;
import com.google.common.collect.ImmutableList;
import ninja.leaping.configurate.objectmapping.Setting;
import org.bukkit.Bukkit;
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
        return getWarps().stream().filter(node -> node.getName().equals(name.toLowerCase())).map(node -> (Warp) node).findFirst();
    }

    @Override
    public Warp setWarp(String name, Location location) {
        name = name.toLowerCase();
        Optional<Warp> opt = getWarp(name);

        if (opt.isPresent()) {
            opt.get().setLocation(location);
            return opt.get();
        } else {
            WarpNode warp = new WarpNode(location, name);
            Bukkit.getPluginManager().callEvent(new WarpCreateEvent(warp));
            getWarps().add(new WarpNode(location, name));
            return warp;
        }
    }

    @Override
    public boolean removeWarp(String name) {
        return getWarps().removeIf(warp -> warp.getName().equals(name.toLowerCase()));
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
