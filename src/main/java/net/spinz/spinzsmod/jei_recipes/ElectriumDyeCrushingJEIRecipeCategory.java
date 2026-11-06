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

public class ElectriumDyeCrushingJEIRecipeCategory implements IRecipeCategory<ElectriumDyeCrushingJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("spinzs_mod:electrium_dye_crushing_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("spinzs_mod:textures/screens/electrium_dye_crushing_jei.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public ElectriumDyeCrushingJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 87);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SpinzsModModBlocks.ELECTRIUM_DYE_CRUSHER.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<ElectriumDyeCrushingJEIRecipe> getRecipeType() {
		return SpinzsModModJeiPlugin.ElectriumDyeCrushingJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Electrium Dye Crushing");
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
	public void draw(ElectriumDyeCrushingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/electrylosis_electrium.png"), 30, 15, 0, 16 * ((mc.player.tickCount / 2) % 22) - 1, 24, 16, 24, 352);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/arrow_progress.png"), 105, 33, 0, 15 * ((mc.player.tickCount / 2) % 23) - 1, 22, 15, 22, 345);

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ElectriumDyeCrushingJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 79, 53).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 79, 17).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 35).addItemStack(actualOutputs.get(0));
	}
}