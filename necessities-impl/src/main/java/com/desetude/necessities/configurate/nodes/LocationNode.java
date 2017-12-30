package com.desetude.necessities.configurate.nodes;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Optional;
import java.util.UUID;

@ConfigSerializable
public class LocationNode {

    @Setting protected String world;
    @Setting protected double x;
    @Setting protected double y;
    @Setting protected double z;
    @Setting protected float pitch;
    @Setting protected float yaw;

    public LocationNode() {
    }

    public LocationNode(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }

    public Optional<Location> getLocation() {
        World world = Bukkit.getWorld(this.world);

        if (world == null) {
            return Optional.empty();
        }

        return Optional.of(new Location(world, this.x, this.y, this.z, this.yaw, this.pitch));
    }

}
