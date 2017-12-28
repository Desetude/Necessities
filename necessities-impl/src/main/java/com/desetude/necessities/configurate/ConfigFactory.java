package com.desetude.necessities.configurate;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.bukkit.event.Listener;

import java.io.File;

public class ConfigFactory implements Listener {

    private final LoadingCache<Key, Config<?>> configs;

    private final File dir;

    @Inject
    public ConfigFactory(File dir) {
        this.dir = checkNotNull(dir, "dir");
        this.dir.mkdirs();

        this.configs = CacheBuilder.newBuilder()
                .build(new CacheLoader<Key, Config<?>>() {
                    @Override
                    public Config<?> load(Key key) throws Exception {
                        File file = new File(dir, key.name + ".conf");
                        file.getAbsoluteFile().getParentFile().mkdirs();
                        return new Config<>(createLoader(file), key.type);
                    }
                });
    }

    public File getDir() {
        return this.dir;
    }

    public ConfigurationLoader<CommentedConfigurationNode> createLoader(File file) {
        return HoconConfigurationLoader.builder().setFile(file).build();
    }

    public ConfigurationLoader<CommentedConfigurationNode> createLoader(String name) {
        File file = new File(this.dir, name + ".conf");
        file.getAbsoluteFile().getParentFile().mkdirs();
        return this.createLoader(file);
    }

    public void saveConfigs() {
        this.configs.asMap().values().stream().filter(Config::loadAttempted).forEach(Config::save);
    }

    public <T> Config<T> createMapping(String name, Class<T> type) {
        return (Config<T>) this.configs.getUnchecked(new Key(name, type));
    }

    private static class Key {

        private final String name;
        private final Class<?> type;

        public Key(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public Class<?> getType() {
            return this.type;
        }

    }

}
