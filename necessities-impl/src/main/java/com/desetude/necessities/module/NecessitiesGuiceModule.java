package com.desetude.necessities.module;

import static com.google.common.base.Preconditions.checkState;

import co.aikar.commands.BukkitCommandManager;
import com.desetude.necessities.configurate.Config;
import com.desetude.necessities.configurate.ConfigFactory;
import com.desetude.necessities.configurate.ConfigLoadException;
import com.desetude.necessities.configurate.InjectConfig;
import com.desetude.necessities.configurate.PluginLogger;
import com.google.inject.AbstractModule;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

public class NecessitiesGuiceModule extends AbstractModule {

    private final Logger logger;
    private final ConfigFactory configFactory;
    private final BukkitCommandManager commandManager;
    private final List<LifecycleListener> lifecycleListeners;

    public NecessitiesGuiceModule(Logger logger, ConfigFactory configFactory,
            BukkitCommandManager commandManager, List<LifecycleListener> lifecycleListeners) {
        this.logger = logger;
        this.configFactory = configFactory;
        this.commandManager = commandManager;
        this.lifecycleListeners = lifecycleListeners;
    }

    @Override
    protected void configure() {
        bind(Logger.class).annotatedWith(PluginLogger.class).toInstance(this.logger);
        bind(ConfigFactory.class).toInstance(this.configFactory);
        bind(BukkitCommandManager.class).toInstance(this.commandManager);

        bindListener(Matchers.any(), new ConfigTypeListener());
        bindListener(Matchers.any(), new ListenerTypeListener());
    }

    private class ListenerTypeListener implements TypeListener {

        @Override
        public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
            Class<?> clazz = type.getRawType();
            if (LifecycleListener.class.isAssignableFrom(clazz)) {
                encounter.register((InjectionListener<I>) obj -> lifecycleListeners.add((LifecycleListener) obj));
            }
        }

    }

    private class ConfigTypeListener implements TypeListener {

        @Override
        public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
            Class<?> clazz = type.getRawType();
            while (clazz != null) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(InjectConfig.class)) {
                        checkState(field.getType() == Config.class, "InjectConfig type must be Config");
                        encounter.register(new ConfigMembersInjector<>(field.getAnnotation(InjectConfig.class), field));
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }

    }

    private class ConfigMembersInjector<T> implements MembersInjector<T> {

        private final InjectConfig annotation;
        private final Field field;

        public ConfigMembersInjector(InjectConfig annotation, Field field) {
            this.annotation = annotation;

            this.field = field;
            this.field.setAccessible(true);
        }

        @Override
        public void injectMembers(T instance) {
            try {
                ParameterizedType paramType = (ParameterizedType) this.field.getGenericType();
                this.field.set(instance, configFactory.createMapping(this.annotation.value(), (Class<?>) paramType.getActualTypeArguments()[0]));
            } catch (IllegalAccessException e) {
                throw new ConfigLoadException("Failed to inject members of config " + this.annotation.value() + ".", e);
            }
        }

    }

}
