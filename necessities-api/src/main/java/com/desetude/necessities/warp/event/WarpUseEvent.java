package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when a {@link Player} uses a warp. If cancelled, the
 * {@link Player} will not be charged or teleported.
 */
public class WarpUseEvent extends WarpEvent implements Cancellable {

    private final Player player;
    private boolean isCancelled = false;

    public WarpUseEvent(Warp warp, Player player) {
        super(warp);

        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

}
