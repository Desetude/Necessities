package com.desetude.necessities.configurate;

import static com.google.common.base.Preconditions.checkNotNull;

import com.desetude.necessities.Necessities;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.InputStream;

public class ConfigFactory implements Listener {

    private final LoadingCache<Key, Config<?>> configs;

    private final File dir;

    @Inject
    public ConfigFactory(File dir, Plugin plugin) {
        this.dir = checkNotNull(dir, "dir");
        this.dir.mkdirs();

        this.configs = CacheBuilder.newBuilder()
                .build(new CacheLoader<Key, Config<?>>() {
                    @Override
                    public Config<?> load(Key key) throws Exception {
                        File file = new File(dir, key.name + ".yml");
                        file.getAbsoluteFile().getParentFile().mkdirs();

                        InputStream is = Necessities.class.getResourceAsStream("/" + file.getName());

                        if (!file.exists() && is != null) {
                            plugin.saveResource(file.getName(), false);
                        }

                        return new Config<>(createLoader(file), key.type);
                    }
                });
    }

    public ConfigurationLoader<ConfigurationNode> createLoader(File file) {
        return YAMLConfigurationLoader.builder()
                .setFile(file)
                .setIndent(2)
                .build();
    }

    public ConfigurationLoader<ConfigurationNode> createLoader(String name) {
        File file = new File(this.dir, name + ".yml");
        file.getAbsoluteFile().getParentFile().mkdirs();
        return this.createLoader(file);
    }

    public File getDir() {
        return this.dir;
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
