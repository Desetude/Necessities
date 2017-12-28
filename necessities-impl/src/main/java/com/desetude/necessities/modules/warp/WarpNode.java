package com.desetude.necessities.modules.warp;

import com.desetude.necessities.configurate.nodes.LocationNode;
import com.desetude.necessities.warp.Warp;
import org.bukkit.Location;

public class WarpNode extends LocationNode implements Warp {

    private String name;

    public WarpNode() {
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
