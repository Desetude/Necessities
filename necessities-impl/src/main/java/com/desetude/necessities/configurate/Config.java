package com.desetude.necessities.configurate;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;

public class Config<T> {

    private final ConfigurationLoader<CommentedConfigurationNode> loader;
    private final ObjectMapper<T>.BoundInstance configMapper;
    private CommentedConfigurationNode root;
    private T object;
    private boolean loadAttempted;

    public Config(ConfigurationLoader<CommentedConfigurationNode> loader, Class<T> type) throws ConfigLoadException {
        try {
            this.loader = checkNotNull(loader, "loader");
            this.configMapper = ObjectMapper.forClass(type).bindToNew();
            this.root = SimpleCommentedConfigurationNode.root();
            this.object = configMapper.populate(root);
        } catch (ObjectMappingException e) {
            throw new ConfigLoadException(e);
        }
    }

    public boolean load() {
        try {
            this.root = this.loader.load();
            this.object = this.configMapper.populate(this.root);
            return true;
        } catch (IOException | ObjectMappingException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.loadAttempted = true;
        }
    }

    public boolean save() {
        checkState(this.loadAttempted, "%s: Config#load() must be called before Config#save().", this.object.getClass().getName());

        try {
            this.configMapper.serialize(this.root);
            this.loader.save(this.root);
            return true;
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadAttempted() {
        return this.loadAttempted;
    }

    public T get() {
        return this.object;
    }

}
