package com.desetude.necessities.warp.event;

import com.desetude.necessities.warp.Warp;
import org.bukkit.Location;

/**
 * Called when a {@link Warp}'s cost is changed.
 *
 * <p>Note: {@link this#getWarp()} returns the {@link Warp}
 * before it was modified.</p>
 */
public class WarpChangeCostEvent extends WarpEvent {

    private final double cost;

    public WarpChangeCostEvent(Warp warp, double cost) {
        super(warp);

        this.cost = cost;
    }

    /**
     * Returns the new, updated {@link Location} of the {@link Warp}.
     *
     * @return The new Location of the warp
     */
    public double getNewCost() {
        return this.cost;
    }

}
