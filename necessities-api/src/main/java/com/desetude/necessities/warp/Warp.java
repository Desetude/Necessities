package com.desetude.necessities.warp;

import org.bukkit.Location;

import java.util.Optional;

/**
 * Represents a warp which players can teleport to.
 *
 * TODO: Add {@code Optional<Double>} cost, {@code Optional<String>} description.
 */
public interface Warp {

    String getName();

    Optional<Location> getLocation();

}
