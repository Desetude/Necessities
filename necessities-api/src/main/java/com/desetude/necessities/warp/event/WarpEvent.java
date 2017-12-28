package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import org.bukkit.event.Event;

public abstract class WarpEvent extends Event {

    private final Warp warp;

    public WarpEvent(Warp warp) {
        this.warp = warp;
    }

    public Warp getWarp() {
        return warp;
    }

}
