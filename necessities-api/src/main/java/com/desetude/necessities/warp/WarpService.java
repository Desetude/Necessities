package com.desetude.necessities.warp;

import com.desetude.necessities.warp.event.WarpChangeLocationEvent;
import com.desetude.necessities.warp.event.WarpCreateEvent;
import org.bukkit.Location;

import java.util.List;
import java.util.Optional;

/**
 * A service which allows the retrieval, removal
 * and addition of {@link Warp}s.
 */
public interface WarpService {

    /**
     * Returns an {@link Optional} of the {@link Warp} with
     * the specified, case-insensitive name if it exists,
     * otherwise {@link Optional#empty()}.
     *
     * @param name The name of the Warp to get
     * @return The warp with the specified name
     */
    Optional<Warp> getWarp(String name);

    /**
     * Sets the {@link Location} of the {@link Warp} with the specified name
     * to the specified {@link Location}.
     *
     * <p>A {@link Warp} is created if no {@link Warp} exists with the
     * specified name and a {@link WarpCreateEvent} is called.
     * Otherwise, the existing {@link Warp} is updated to have the specified
     * {@link Location} and a {@link WarpChangeLocationEvent} is called.</p>
     *
     * @param name The name of the Warp
     * @param location The location of the Warp
     * @return The Warp created or updated
     */
    Warp setWarp(String name, Location location);

    /**
     * Removes the {@link Warp} with the specified name.
     *
     * @param name The name of the Warp
     * @return Whether the Warp was successfully removed
     */
    boolean removeWarp(String name);

    /**
     * Returns an <b>immutable</b> {@link List} of all
     * existing {@link Warp}s.
     *
     * @return A List of all Warps
     */
    List<Warp> getAllWarps();

}
