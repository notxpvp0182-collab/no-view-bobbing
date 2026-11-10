package dev.ahmed.noviewbobbing;

import dev.ahmed.noviewbobbing.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class NoViewBobbingClient implements ClientModInitializer {
    public static final String MOD_ID = "noviewbobbing";
    public static KeyBinding toggleKeyBinding;

    @Override
    public void onInitializeClient() {
        ModConfig.load();

        KeyBinding.Category category = KeyBinding.Category.create(Identifier.of(MOD_ID, "general"));

        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.noviewbobbing.toggle",
                InputUtil.Type.KEYSYM,
                InputUtil.UNKNOWN_KEY.getCode(),
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeyBinding.wasPressed()) {
                boolean newState = !ModConfig.viewBobbing && !ModConfig.handViewBobbing;
                ModConfig.viewBobbing = newState;
                ModConfig.handViewBobbing = newState;
                ModConfig.save();
            }
        });
    }
}
