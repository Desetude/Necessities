package com.desetude.necessities.modules.warp;

import com.desetude.necessities.configurate.nodes.LocationNode;
import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.event.WarpChangeCostEvent;
import com.desetude.necessities.warp.event.WarpChangeLocationEvent;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Optional;

@ConfigSerializable
public class WarpNode extends LocationNode implements Warp {

    @Setting private String name;
    @Setting private double cost;

    public WarpNode() {
    }

    public WarpNode(Location location, String name) {
        super(location);

        this.name = name.toLowerCase();
    }

    @Override
    public String getName() {
        return this.name.toLowerCase();
    }

    @Override
    public Optional<Double> getCost() {
        if (this.cost == 0.0) {
            return Optional.empty();
        }

        return Optional.of(this.cost);
    }

    @Override
    public void setCost(double cost) {
        Bukkit.getPluginManager().callEvent(new WarpChangeCostEvent(this, cost));

        this.cost = cost;
    }

    @Override
    public void setLocation(Location location) {
        Bukkit.getPluginManager().callEvent(new WarpChangeLocationEvent(this, location));

        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }

}
