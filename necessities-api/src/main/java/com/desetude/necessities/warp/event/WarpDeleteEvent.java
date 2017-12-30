package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;

/**
 * Called when a {@link Warp} is deleted.
 */
public class WarpDeleteEvent extends WarpEvent {

    public WarpDeleteEvent(Warp warp) {
        super(warp);
    }

}
