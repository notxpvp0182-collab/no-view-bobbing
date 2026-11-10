package dev.ahmed.noviewbobbing.mixin;

import dev.ahmed.noviewbobbing.config.ModConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(
            method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerEntity;lastRenderPitch:F", opcode = Opcodes.GETFIELD)
    )
    private float noviewbobbing$redirectLastRenderPitch(ClientPlayerEntity player, float tickProgress, MatrixStack matrices, OrderedRenderCommandQueue queue, ClientPlayerEntity p, int light) {
        return ModConfig.handViewBobbing ? player.lastRenderPitch : player.getPitch(tickProgress);
    }

    @Redirect(
            method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerEntity;renderPitch:F", opcode = Opcodes.GETFIELD)
    )
    private float noviewbobbing$redirectRenderPitch(ClientPlayerEntity player, float tickProgress, MatrixStack matrices, OrderedRenderCommandQueue queue, ClientPlayerEntity p, int light) {
        return ModConfig.handViewBobbing ? player.renderPitch : player.getPitch(tickProgress);
    }

    @Redirect(
            method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerEntity;lastRenderYaw:F", opcode = Opcodes.GETFIELD)
    )
    private float noviewbobbing$redirectLastRenderYaw(ClientPlayerEntity player, float tickProgress, MatrixStack matrices, OrderedRenderCommandQueue queue, ClientPlayerEntity p, int light) {
        return ModConfig.handViewBobbing ? player.lastRenderYaw : player.getYaw(tickProgress);
    }

    @Redirect(
            method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerEntity;renderYaw:F", opcode = Opcodes.GETFIELD)
    )
    private float noviewbobbing$redirectRenderYaw(ClientPlayerEntity player, float tickProgress, MatrixStack matrices, OrderedRenderCommandQueue queue, ClientPlayerEntity p, int light) {
        return ModConfig.handViewBobbing ? player.renderYaw : player.getYaw(tickProgress);
    }
}
