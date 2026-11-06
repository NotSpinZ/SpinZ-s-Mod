package net.spinz.spinzsmod.client.gui;

import net.spinz.spinzsmod.world.inventory.DewarGUIMenu;
import net.spinz.spinzsmod.procedures.FluidTankTooltipDewarProcedure;
import net.spinz.spinzsmod.init.SpinzsModModScreens;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.stream.Collectors;
import java.util.Arrays;

import com.mojang.blaze3d.systems.RenderSystem;

public class DewarGUIScreen extends AbstractContainerScreen<DewarGUIMenu> implements SpinzsModModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("spinzs_mod:textures/screens/dewar_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("spinzs_mod:textures/screens/fluid_slot_bg.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("spinzs_mod:textures/screens/fluid_slot_front.png");

	public DewarGUIScreen(DewarGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		boolean customTooltipShown = false;
		if (mouseX > leftPos + 81 && mouseX < leftPos + 98 && mouseY > topPos + 15 && mouseY < topPos + 76) {
			String hoverText = FluidTankTooltipDewarProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.renderComponentTooltip(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiTools$alphaBlit(guiGraphics, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiTools$orderedImages : {
			guiTools$alphaBlit(guiGraphics, IMAGE_0, this.leftPos + 80, this.topPos + 15, 0, 0, 17, 61, 17, 61);
			if (true) {
				int guiTools$xOffset = 0;
				int guiTools$yOffset = (int) Math.round(net.spinz.spinzsmod.procedures.DewarGUIYOffsetProcedureProcedure.execute(world, x, y, z));
				int guiTools$visibleWidth = 15;
				int guiTools$visibleHeight = (int) Math.max(0L, Math.min(59L, Math.round(net.spinz.spinzsmod.procedures.DewarGUIVisibleHeightProcedureProcedure.execute(world, x, y, z))));
				net.minecraft.resources.ResourceLocation guiTools$image = guiTools$dynamicTexture("", net.minecraft.resources.ResourceLocation.parse("spinzs_mod:textures/screens/liquid_tank_temp.png"));
				if (guiTools$image != null && guiTools$visibleWidth > 0 && guiTools$visibleHeight > 0)
					guiTools$alphaBlit(guiGraphics, guiTools$image, this.leftPos + 82 + guiTools$xOffset, this.topPos + 16 + guiTools$yOffset, 0, 0, guiTools$visibleWidth, guiTools$visibleHeight, 15, 59);
			}
			guiTools$alphaBlit(guiGraphics, IMAGE_1, this.leftPos + 81, this.topPos + 15, 0, 0, 17, 61, 17, 61);
		}
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
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.dewar_gui.label_dewar"), 6, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.dewar_gui.label_inventory"), 6, 71, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}

	private static net.minecraft.resources.ResourceLocation guiTools$dynamicTexture(String value, net.minecraft.resources.ResourceLocation fallback) {
		if (value == null || value.isBlank())
			return fallback;
		try {
			String texture = value.trim().replace('\\', '/');
			if (texture.indexOf(':') >= 0)
				return net.minecraft.resources.ResourceLocation.parse(texture);
			while (texture.startsWith("/"))
				texture = texture.substring(1);
			if (texture.startsWith("textures/screens/"))
				texture = texture.substring("textures/screens/".length());
			if (!texture.endsWith(".png"))
				texture += ".png";
			return net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("spinzs_mod", "textures/screens/" + texture);
		} catch (RuntimeException ignored) {
			return fallback;
		}
	}

	private static void guiTools$alphaBlit(net.minecraft.client.gui.GuiGraphics graphics, net.minecraft.resources.ResourceLocation texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
		boolean wasBlending = org.lwjgl.opengl.GL11.glIsEnabled(org.lwjgl.opengl.GL11.GL_BLEND);
		com.mojang.blaze3d.systems.RenderSystem.enableBlend();
		com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc();
		try {
			graphics.blit(texture, x, y, u, v, width, height, textureWidth, textureHeight);
		} finally {
			if (!wasBlending)
				com.mojang.blaze3d.systems.RenderSystem.disableBlend();
		}
	}
}