package com.desetude.necessities.warp;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Represents a named warp which players can teleport to.
 *
 * TODO 2017-12-29: Add {@code Optional<String>} description.
 */
public interface Warp {

    /**
     * Returns the name identifying this Warp.
     *
     * @return this Warp's name
     */
    String getName();

    /**
     * Returns an {@link Optional} {@link Location} of this Warp.
     *
     * <p>Note: Returns {@link Optional#empty()} if the {@link Location} could
     * not be created due to the {@link World} the Warp is in
     * not being present on the server.</p>
     *
     * @return The location of this Warp
     */
    Optional<Location> getLocation();

    /**
     * Sets the {@link Location} this Warp teleports {@link Player}s to on use.
     *
     * @param location The Location to set
     */
    void setLocation(Location location);

    /**
     * Returns an {@link Optional} cost of this Warp. Every time a
     * {@link Player} uses this Warp,WarpChangeLocationEvent they will be charged this amount, if present.
     *
     * @return The cost of this Warp
     */
    Optional<Double> getCost();

    /**
     * Sets the amount charged to {@link Player}s for using this Warp.
     *
     * <p>Note: In order to remove the cost, set the cost to 0.0.</p>
     *
     * @param cost The cost to set
     */
    void setCost(double cost);

}
