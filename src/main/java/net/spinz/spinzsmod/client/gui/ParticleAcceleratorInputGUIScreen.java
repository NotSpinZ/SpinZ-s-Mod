package net.spinz.spinzsmod.client.gui;

import net.spinz.spinzsmod.world.inventory.ParticleAcceleratorInputGUIMenu;
import net.spinz.spinzsmod.init.SpinzsModModScreens;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

public class ParticleAcceleratorInputGUIScreen extends AbstractContainerScreen<ParticleAcceleratorInputGUIMenu> implements SpinzsModModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("spinzs_mod:textures/screens/particle_accelerator_input_gui.png");

	public ParticleAcceleratorInputGUIScreen(ParticleAcceleratorInputGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 178;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_input_gui.label_viles"), 18, 77, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_input_gui.label_viles_copy"), 117, 77, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_input_gui.label_accelerator_input"), 7, 84, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_input_gui.label_accelerator_input_copy"), 5, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_input_gui.label_viles_copy_copy"), 69, 41, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}