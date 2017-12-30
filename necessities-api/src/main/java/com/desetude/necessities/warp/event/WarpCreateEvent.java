package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import com.desetude.necessities.warp.WarpService;

/**
 * Called when a {@link Warp} is created.
 *
 * <p>Note: Called before the {@link Warp} is added
 * to the list in the {@link WarpService}.</p>
 */
public class WarpCreateEvent extends WarpEvent {

    public WarpCreateEvent(Warp warp) {
        super(warp);
    }

}
