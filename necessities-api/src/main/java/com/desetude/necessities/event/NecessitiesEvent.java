package com.desetude.necessities.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Removes the need for HandlerList in every event.
 */
public abstract class NecessitiesEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
