package com.desetude.necessities.modules.warp;

import com.desetude.necessities.warp.WarpService;
import com.google.inject.AbstractModule;

public class WarpGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WarpService.class).to(WarpServiceProvider.class);
    }

}
