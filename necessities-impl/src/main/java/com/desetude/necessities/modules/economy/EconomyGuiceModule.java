package com.desetude.necessities.modules.economy;

import com.google.inject.AbstractModule;
import net.milkbowl.vault.economy.Economy;

public class EconomyGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Economy.class).to(NecessitiesEconomy.class);
    }

}
