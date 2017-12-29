package com.desetude.necessities.lifecycle;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeinitializationEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}