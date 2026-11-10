package dev.ahmed.noviewbobbing.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("noviewbobbing.json");

    public static boolean viewBobbing = false;
    public static boolean handViewBobbing = false;

    private static class ConfigData {
        boolean viewBobbing = false;
        boolean handViewBobbing = false;
    }

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                ConfigData loaded = GSON.fromJson(reader, ConfigData.class);
                if (loaded != null) {
                    viewBobbing = loaded.viewBobbing;
                    handViewBobbing = loaded.handViewBobbing;
                }
            } catch (IOException ignored) {
            }
        } else {
            save();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.viewBobbing = viewBobbing;
        data.handViewBobbing = handViewBobbing;
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(data, writer);
        } catch (IOException ignored) {
        }
    }
}
