package com.desetude.necessities.warp;

import org.bukkit.Location;

import java.util.List;
import java.util.Optional;

public interface WarpService {

    Optional<Warp> getWarp(String name);

    void addWarp(String name, Location location);

    boolean removeWarp(String name);

    List<Warp> getAllWarps();

}
