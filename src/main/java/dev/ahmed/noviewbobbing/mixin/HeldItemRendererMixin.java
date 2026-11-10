package dev.ahmed.noviewbobbing.mixin;

import dev.ahmed.noviewbobbing.config.ModConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Inject(
            method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At("HEAD")
    )
    private void noviewbobbing$disableHandSway(
            float tickProgress,
            MatrixStack matrices,
            OrderedRenderCommandQueue orderedRenderCommandQueue,
            ClientPlayerEntity player,
            int light,
            CallbackInfo ci
    ) {
        if (!ModConfig.get().handViewBobbing) {
            float currentPitch = player.getPitch(tickProgress);
            float currentYaw = player.getYaw(tickProgress);

            player.renderPitch = currentPitch;
            player.lastRenderPitch = currentPitch;
            player.renderYaw = currentYaw;
            player.lastRenderYaw = currentYaw;
        }
    }
}
