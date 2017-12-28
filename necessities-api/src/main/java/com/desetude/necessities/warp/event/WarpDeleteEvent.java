package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import org.bukkit.event.HandlerList;

public class WarpDeleteEvent extends WarpEvent {

    private static final HandlerList handlers = new HandlerList();

    public WarpDeleteEvent(Warp warp) {
        super(warp);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
