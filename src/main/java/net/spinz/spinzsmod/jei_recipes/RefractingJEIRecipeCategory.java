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

public class RefractingJEIRecipeCategory implements IRecipeCategory<RefractingJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("spinzs_mod:refracting_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("spinzs_mod:textures/screens/refractor_guijei.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public RefractingJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SpinzsModModBlocks.REFRACTOR.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<RefractingJEIRecipe> getRecipeType() {
		return SpinzsModModJeiPlugin.RefractingJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Refracting");
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
	public void draw(RefractingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/arrow_progress.png"), 114, 31, 0, 15 * ((mc.player.tickCount / 2) % 22) - 1, 22, 15, 22, 345);

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, RefractingJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 28, 31).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 48, 31).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 70, 31).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.INPUT, 92, 31).addIngredients(recipe.getIngredients().get(3));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 143, 31).addItemStack(actualOutputs.get(0));
	}
}