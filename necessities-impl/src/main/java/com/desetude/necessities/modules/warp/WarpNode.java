package com.desetude.necessities.modules.warp;

import com.desetude.necessities.configurate.nodes.LocationNode;
import com.desetude.necessities.warp.Warp;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.bukkit.Location;

@ConfigSerializable
public class WarpNode extends LocationNode implements Warp {

    @Setting private String name;

    public WarpNode(){
    }

    public WarpNode(Location location, String name) {
        super(location);

        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
