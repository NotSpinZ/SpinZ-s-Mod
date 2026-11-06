package net.spinz.spinzsmod.jei_recipes;

import net.spinz.spinzsmod.init.SpinzsModModJeiPlugin;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

import java.util.List;

public class ComponentApplicationRecipeCategory implements IRecipeCategory<ComponentApplicationRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("spinzs_mod:component_application");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("spinzs_mod:textures/screens/component_applier_jei.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public ComponentApplicationRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 89);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SpinzsModModBlocks.COMPONENT_APPLIER.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<ComponentApplicationRecipe> getRecipeType() {
		return SpinzsModModJeiPlugin.ComponentApplication_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Component Application");
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return this.background.getWidth();
	}

	@Override
	public int getHeight() {
		return this.background.getHeight();
	}

	@Override
	public boolean needsRecipeBorder() {
		return false;
	}

	@Override
	public void draw(ComponentApplicationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/material_applier_ui_element.png"), 76, 32, 0, 32 * ((mc.player.tickCount / 2) % 11) - 1, 32, 32, 32, 352);

		guiGraphics.drawString(mc.font, Component.translatable("gui.spinzs_mod.component_application.label_component_applier"), 42, 7, -12829636, false);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ComponentApplicationRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 43, 53).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 25, 26).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 26).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 44).addItemStack(actualOutputs.get(0));
	}
}