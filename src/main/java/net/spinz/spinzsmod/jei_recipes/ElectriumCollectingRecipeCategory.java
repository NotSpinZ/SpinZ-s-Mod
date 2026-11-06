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

public class ElectriumCollectingRecipeCategory implements IRecipeCategory<ElectriumCollectingRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("spinzs_mod:electrium_collecting");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("spinzs_mod:textures/screens/electrium_collector_jei.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public ElectriumCollectingRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 87);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SpinzsModModBlocks.ELECTRIUM_COLLECTOR.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<ElectriumCollectingRecipe> getRecipeType() {
		return SpinzsModModJeiPlugin.ElectriumCollecting_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Electrium Collecting");
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
	public void draw(ElectriumCollectingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.drawString(mc.font, recipe.strings().get(0), 35, 13, -13369600, false);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ElectriumCollectingRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 43, 34).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 34).addItemStack(actualOutputs.get(0));
	}
}