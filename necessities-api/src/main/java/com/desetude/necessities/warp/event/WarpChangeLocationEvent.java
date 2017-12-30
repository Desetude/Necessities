package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import org.bukkit.Location;

/**
 * Called when a {@link Warp}'s {@link Location} is changed.
 *
 * <p>Note: {@link this#getWarp()} returns the {@link Warp}
 * before it was modified.</p>
 */
public class WarpChangeLocationEvent extends WarpEvent {

    private final Location newLoc;

    public WarpChangeLocationEvent(Warp warp, Location newLoc) {
        super(warp);

        this.newLoc = newLoc;
    }

    /**
     * Returns the new, updated {@link Location} of the {@link Warp}.
     *
     * @return The new Location of the warp
     */
    public Location getNewLocation() {
        return this.newLoc;
    }

}
