package com.desetude.necessities.configurate;

import static com.google.common.base.Preconditions.checkState;

import com.desetude.necessities.Necessities;
import com.google.common.base.Charsets;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class Config<T> {

    private final File file;
    private final Yaml yaml;
    private final Class<T> type;
    private T object;
    private boolean loadAttempted;

    public Config(File file, Class<T> type) throws ConfigLoadException {
        this.file = file;
        this.type = type;
        this.yaml = new Yaml(new CustomClassLoaderConstructor(Necessities.class.getClassLoader()));
    }

    public boolean load() {
        String data;

        try {
            data = load(this.file);

            this.object = this.yaml.loadAs(data, this.type);
            return true;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.loadAttempted = true;
        }
    }

    public String load(File file) throws IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");

        final FileInputStream stream = new FileInputStream(file);

        return load(new InputStreamReader(stream, Charsets.UTF_8));
    }

    public String load(Reader reader) throws IOException, InvalidConfigurationException {
        BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        return builder.toString();
    }

    public boolean save() {
        checkState(this.loadAttempted, "%s: Config#load() must be called before Config#save().", this.object.getClass().getName());

        String str = this.yaml.dumpAsMap(this.object);

        if (str.equals("{}\n")) {
            str = "";
        }

        OutputStreamWriter writer = null;

        try {
            writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

            writer.write(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean loadAttempted() {
        return this.loadAttempted;
    }

    public T get() {
        return this.object;
    }

}
