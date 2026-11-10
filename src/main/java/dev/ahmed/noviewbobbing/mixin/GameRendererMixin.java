package dev.ahmed.noviewbobbing.mixin;

import dev.ahmed.noviewbobbing.config.ModConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "bobView", at = @At("HEAD"), cancellable = true)
    private void noviewbobbing$disableViewBobbing(MatrixStack matrices, float tickProgress, CallbackInfo ci) {
        if (!ModConfig.get().viewBobbing) {
            ci.cancel();
        }
    }
}
