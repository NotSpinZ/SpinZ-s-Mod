package net.spinz.spinzsmod.client.gui;

import net.spinz.spinzsmod.world.inventory.ParticleAcceleratorControlPanelGUIMenu;
import net.spinz.spinzsmod.procedures.ReturnCoolantTextPAProcedure;
import net.spinz.spinzsmod.network.ParticleAcceleratorControlPanelGUIButtonMessage;
import net.spinz.spinzsmod.init.SpinzsModModScreens;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.client.gui.widget.ExtendedSlider;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

public class ParticleAcceleratorControlPanelGUIScreen extends AbstractContainerScreen<ParticleAcceleratorControlPanelGUIMenu> implements SpinzsModModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_start;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("spinzs_mod:textures/screens/particle_accelerator_control_panel_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("spinzs_mod:textures/screens/nitrogen_display_noanim.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("spinzs_mod:textures/screens/large_electrium_display_empty.png");
	private ExtendedSlider speed;
	private ExtendedSlider heat;

	public ParticleAcceleratorControlPanelGUIScreen(ParticleAcceleratorControlPanelGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 227;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 2 && elementState instanceof Number n) {
			if (name.equals("speed"))
				speed.setValue(n.doubleValue());
			else if (name.equals("heat"))
				heat.setValue(n.doubleValue());
		}
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
		guiTools$alphaBlit(guiGraphics, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiTools$orderedImages : {
			guiTools$alphaBlit(guiGraphics, IMAGE_0, this.leftPos + 102, this.topPos + 6, 0, 0, 12, 12, 12, 12);
			guiTools$alphaBlit(guiGraphics, IMAGE_1, this.leftPos + 177, this.topPos + 45, 0, 0, 32, 32, 32, 32);
			if (true) {
				int guiTools$xOffset = 0;
				int guiTools$yOffset = 0;
				int guiTools$visibleWidth = (int) Math.max(0L, Math.min(32L, Math.round(net.spinz.spinzsmod.procedures.ParticleAcceleratorControlPanelGUIVisibleWidthProcedureProcedure.execute(world, x, y, z))));
				int guiTools$visibleHeight = 32;
				net.minecraft.resources.ResourceLocation guiTools$image = guiTools$dynamicTexture("", net.minecraft.resources.ResourceLocation.parse("spinzs_mod:textures/screens/large_electrium_display.png"));
				if (guiTools$image != null && guiTools$visibleWidth > 0 && guiTools$visibleHeight > 0)
					guiTools$alphaBlit(guiGraphics, guiTools$image, this.leftPos + 177 + guiTools$xOffset, this.topPos + 45 + guiTools$yOffset, 0, 0, guiTools$visibleWidth, guiTools$visibleHeight, 32, 32);
			}
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
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		return (this.getFocused() != null && this.isDragging() && button == 0) ? this.getFocused().mouseDragged(mouseX, mouseY, button, dragX, dragY) : super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.label_particle"), 177, 144, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.label_control_panel"), 6, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.label_viles"), 183, 110, -12829636, false);
		guiGraphics.drawString(this.font, ReturnCoolantTextPAProcedure.execute(world, x, y, z), 116, 8, -6684673, false);
	}

	@Override
	public void init() {
		super.init();
		button_start = Button.builder(Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.button_start"), e -> {
			int x = ParticleAcceleratorControlPanelGUIScreen.this.x;
			int y = ParticleAcceleratorControlPanelGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new ParticleAcceleratorControlPanelGUIButtonMessage(0, x, y, z));
				ParticleAcceleratorControlPanelGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 113, this.topPos + 52, 36, 20).build();
		this.addRenderableWidget(button_start);
		speed = new ExtendedSlider(this.leftPos + 5, this.topPos + 52, 75, 20, Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.speed_prefix"),
				Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.speed_suffix"), 0, 299, 0, 0.02, 0, true) {
			@Override
			protected void applyValue() {
				if (!menuStateUpdateActive)
					menu.sendMenuStateUpdate(entity, 2, "speed", this.getValue(), false);
			}
		};
		this.addRenderableWidget(speed);
		if (!menuStateUpdateActive)
			menu.sendMenuStateUpdate(entity, 2, "speed", speed.getValue(), false);
		heat = new ExtendedSlider(this.leftPos + 5, this.topPos + 25, 80, 20, Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.heat_prefix"),
				Component.translatable("gui.spinzs_mod.particle_accelerator_control_panel_gui.heat_suffix"), -200, 200, 90, 0.1, 0, true) {
			@Override
			protected void applyValue() {
				if (!menuStateUpdateActive)
					menu.sendMenuStateUpdate(entity, 2, "heat", this.getValue(), false);
			}
		};
		this.addRenderableWidget(heat);
		if (!menuStateUpdateActive)
			menu.sendMenuStateUpdate(entity, 2, "heat", heat.getValue(), false);
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