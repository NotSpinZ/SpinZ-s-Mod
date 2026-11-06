package net.spinz.spinzsmod.client.gui;

import net.spinz.spinzsmod.world.inventory.ElectrylosisConductorGUIMenu;
import net.spinz.spinzsmod.procedures.ElectrylosisElectriumIndexProcedure;
import net.spinz.spinzsmod.procedures.ElectrolysisBottleInputImageDisplayProcedure;
import net.spinz.spinzsmod.procedures.AutoExportDirTextProcedure;
import net.spinz.spinzsmod.procedures.AutoExportButtonDisplayProcedure;
import net.spinz.spinzsmod.network.ElectrylosisConductorGUIButtonMessage;
import net.spinz.spinzsmod.init.SpinzsModModScreens;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

public class ElectrylosisConductorGUIScreen extends AbstractContainerScreen<ElectrylosisConductorGUIMenu> implements SpinzsModModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_empty;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("spinzs_mod:textures/screens/electrylosis_conductor_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("spinzs_mod:textures/screens/slot_big.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("spinzs_mod:textures/screens/bottle_outline.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("spinzs_mod:textures/screens/electrylosis_electrium.png");

	public ElectrylosisConductorGUIScreen(ElectrylosisConductorGUIMenu container, Inventory inventory, Component text) {
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
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiTools$alphaBlit(guiGraphics, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiTools$orderedImages : {
			guiTools$alphaBlit(guiGraphics, IMAGE_0, this.leftPos + 100, this.topPos + 29, 0, 0, 28, 28, 28, 28);
			guiTools$alphaBlit(guiGraphics, SPRITE_0, this.leftPos + 58, this.topPos + 35, 0, Mth.clamp((int) ElectrylosisElectriumIndexProcedure.execute(world, x, y, z) * 16, 0, 336), 24, 16, 24, 352);
			if (ElectrolysisBottleInputImageDisplayProcedure.execute(entity)) {
				guiTools$alphaBlit(guiGraphics, IMAGE_1, this.leftPos + 142, this.topPos + 35, 0, 0, 16, 16, 16, 16);
			}
			if (this.enhanced_image_button_8x8_x != null && this.enhanced_image_button_8x8_x.visible) {
				this.enhanced_image_button_8x8_x.render(guiGraphics, mouseX, mouseY, partialTicks);
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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.electrylosis_conductor_gui.label_electrylosis_conductor"), 6, 4, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.spinzs_mod.electrylosis_conductor_gui.label_electrylosis_conductor_copy"), 6, 70, -12829636, false);
		guiGraphics.drawString(this.font, AutoExportDirTextProcedure.execute(world, x, y, z), 152, -31, -14145496, true);
		this.guiTools$renderMultilineLabel(guiGraphics, "Auto-export", 115, 6, 120, 40, -12829636, false, 0.75F);
	}

	@Override
	public void init() {
		super.init();
		button_empty = new PlainTextButton(this.leftPos + 158, this.topPos + -21, 20, 20, Component.translatable("gui.spinzs_mod.electrylosis_conductor_gui.button_empty"), e -> {
			int x = ElectrylosisConductorGUIScreen.this.x;
			int y = ElectrylosisConductorGUIScreen.this.y;
			if (AutoExportButtonDisplayProcedure.execute(world, x, y, z)) {
				PacketDistributor.sendToServer(new ElectrylosisConductorGUIButtonMessage(0, x, y, z));
				ElectrylosisConductorGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}, this.font);
		this.addRenderableWidget(button_empty);
		enhanced_image_button_8x8_x = new net.minecraft.client.gui.components.ImageButton(this.leftPos + 163, this.topPos + 5, 8, 8, new net.minecraft.client.gui.components.WidgetSprites(
				net.minecraft.resources.ResourceLocation.parse("spinzs_mod:textures/screens/8x8_x.png"), net.minecraft.resources.ResourceLocation.parse("spinzs_mod:textures/screens/8x8_button_hover.png")), e -> {
					int x = ElectrylosisConductorGUIScreen.this.x;
					int y = ElectrylosisConductorGUIScreen.this.y;
					if (true) {
						net.neoforged.neoforge.network.PacketDistributor.sendToServer(new net.spinz.spinzsmod.network.ElectrylosisConductorGUIButtonMessage(1, x, y, z));
						net.spinz.spinzsmod.network.ElectrylosisConductorGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(net.minecraft.client.gui.GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				net.minecraft.resources.ResourceLocation guiTools$normalTexture = guiTools$buttonTexture(java.util.Objects.toString(net.spinz.spinzsmod.procedures.AutoExportButtonImageProcedure.execute(world, x, y, z), ""),
						net.minecraft.resources.ResourceLocation.parse("spinzs_mod:textures/screens/8x8_x.png"));
				net.minecraft.resources.ResourceLocation guiTools$hoveredTexture = net.minecraft.resources.ResourceLocation.parse("spinzs_mod:textures/screens/8x8_button_hover.png");
				net.minecraft.resources.ResourceLocation guiTools$pressedTexture = guiTools$hoveredTexture;
				boolean mouseOverButton = mouseX >= getX() && mouseY >= getY() && mouseX < getX() + width && mouseY < getY() + height;
				boolean mousePressed = mouseOverButton && org.lwjgl.glfw.GLFW.glfwGetMouseButton(net.minecraft.client.Minecraft.getInstance().getWindow().getWindow(), org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT) == org.lwjgl.glfw.GLFW.GLFW_PRESS;
				net.minecraft.resources.ResourceLocation buttonTexture = mousePressed ? guiTools$pressedTexture : mouseOverButton ? guiTools$hoveredTexture : guiTools$normalTexture;
				guiTools$alphaBlit(guiGraphics, buttonTexture, getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addWidget(enhanced_image_button_8x8_x);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.button_empty.visible = AutoExportButtonDisplayProcedure.execute(world, x, y, z);
	}

	private final java.util.Map<String, java.util.List<String>> guiTools$multilineCache = new java.util.HashMap<>();

	private void guiTools$renderMultilineLabel(GuiGraphics guiGraphics, String text, int x, int y, int boxWidth, int boxHeight, int color, boolean shadow, float scale) {
		if (text == null || scale <= 0.0F || boxWidth <= 0 || boxHeight <= 0)
			return;
		int wrapWidth = Math.max(1, (int) Math.floor(boxWidth / scale));
		int lineStep = this.font.lineHeight + 1;
		int currentY = 0;
		java.util.List<String> lines = this.guiTools$multilineCache.computeIfAbsent(text + "\u0000" + wrapWidth, key -> this.guiTools$wrapMultilineText(text, wrapWidth));
		if (this.guiTools$multilineCache.size() > 64)
			this.guiTools$multilineCache.clear();
		guiGraphics.pose().pushPose();
		try {
			guiGraphics.pose().translate(x, y, 0.0F);
			guiGraphics.pose().scale(scale, scale, 1.0F);
			for (String line : lines) {
				guiGraphics.drawString(this.font, line, 0, currentY, color, shadow);
				currentY += lineStep;
			}
		} finally {
			guiGraphics.pose().popPose();
		}
	}

	private java.util.List<String> guiTools$wrapMultilineText(String text, int wrapWidth) {
		java.util.List<String> lines = new java.util.ArrayList<>();
		for (String paragraph : text.replace("\r", "").split("\n", -1)) {
			if (paragraph.isEmpty()) {
				lines.add("");
				continue;
			}
			StringBuilder line = new StringBuilder();
			for (String word : paragraph.split("\s+")) {
				String candidate = line.isEmpty() ? word : line + " " + word;
				if (!line.isEmpty() && this.font.width(candidate) > wrapWidth) {
					lines.add(line.toString());
					line.setLength(0);
					line.append(word);
				} else {
					line.setLength(0);
					line.append(candidate);
				}
			}
			lines.add(line.toString());
		}
		return java.util.List.copyOf(lines);
	}

	private static final boolean guiTools$enhancedImageButton = true;
	private net.minecraft.client.gui.components.ImageButton enhanced_image_button_8x8_x;

	private static net.minecraft.resources.ResourceLocation guiTools$buttonTexture(String value, net.minecraft.resources.ResourceLocation fallback) {
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