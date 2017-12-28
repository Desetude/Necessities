package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class WarpUseEvent extends WarpEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;

    public WarpUseEvent(Warp warp, Player player) {
        super(warp);

        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
