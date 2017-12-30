package com.desetude.necessities.warp.event;

import com.desetude.necessities.event.NecessitiesEvent;
import com.desetude.necessities.warp.Warp;

abstract class WarpEvent extends NecessitiesEvent {

    protected final Warp warp;

    WarpEvent(Warp warp) {
        this.warp = warp;
    }

    public Warp getWarp() {
        return warp;
    }

}
