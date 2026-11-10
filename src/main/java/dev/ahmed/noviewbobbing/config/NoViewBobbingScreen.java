package dev.ahmed.noviewbobbing.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class NoViewBobbingScreen extends Screen {
    private final Screen parent;

    private static final Text VIEW_BOBBING_LABEL = Text.translatable("option.noviewbobbing.view_bobbing");
    private static final Text HAND_VIEW_BOBBING_LABEL = Text.translatable("option.noviewbobbing.hand_view_bobbing");

    public NoViewBobbingScreen(Screen parent) {
        super(Text.translatable("title.noviewbobbing.config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int row1Y = this.height / 2 - 14;
        int row2Y = this.height / 2 + 14;

        this.addDrawableChild(ButtonWidget.builder(
                ScreenTexts.onOrOff(ModConfig.viewBobbing),
                button -> {
                    ModConfig.viewBobbing = !ModConfig.viewBobbing;
                    button.setMessage(ScreenTexts.onOrOff(ModConfig.viewBobbing));
                }
        ).dimensions(centerX + 20, row1Y, 80, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                ScreenTexts.onOrOff(ModConfig.handViewBobbing),
                button -> {
                    ModConfig.handViewBobbing = !ModConfig.handViewBobbing;
                    button.setMessage(ScreenTexts.onOrOff(ModConfig.handViewBobbing));
                }
        ).dimensions(centerX + 20, row2Y, 80, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        int centerX = this.width / 2;
        int row1Y = this.height / 2 - 14;
        int row2Y = this.height / 2 + 14;

        context.drawCenteredTextWithShadow(this.textRenderer, this.title, centerX, row1Y - 30, 0xFFFFFFFF);
        context.drawTextWithShadow(this.textRenderer, VIEW_BOBBING_LABEL, centerX - 100, row1Y + 6, 0xFFFFFFFF);
        context.drawTextWithShadow(this.textRenderer, HAND_VIEW_BOBBING_LABEL, centerX - 100, row2Y + 6, 0xFFFFFFFF);
    }

    @Override
    public void close() {
        ModConfig.save();
        if (this.client != null) {
            this.client.setScreen(this.parent);
        }
    }
}
