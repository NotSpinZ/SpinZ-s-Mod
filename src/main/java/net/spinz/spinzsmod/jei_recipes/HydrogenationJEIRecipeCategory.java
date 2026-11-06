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
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

import java.util.List;

public class HydrogenationJEIRecipeCategory implements IRecipeCategory<HydrogenationJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("spinzs_mod:hydrogenation_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("spinzs_mod:textures/screens/hydrogenator_jei.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public HydrogenationJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 87);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SpinzsModModBlocks.HYDROGENATOR.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<HydrogenationJEIRecipe> getRecipeType() {
		return SpinzsModModJeiPlugin.HydrogenationJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Hydrogenation");
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
	public void draw(HydrogenationJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/bubbles_ui.png"), 44, 28, 0, 26 * ((mc.player.tickCount / 1) % 20) - 1, 14, 26, 14, 520);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/hydrogenator_flask_ui.png"), 79, 35, 0, 13 * ((mc.player.tickCount / 1) % 22) - 1, 58, 13, 58, 286);

	}

	public void getTooltip(ITooltipBuilder tooltip, HydrogenationJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		if (mouseX > 40 && mouseX < 64 && mouseY > 33 && mouseY < 57) {
			tooltip.add(Component.translatable("gui.spinzs_mod.hydrogenation_jei.tooltip_hydrogen_level"));
		}
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, HydrogenationJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 8).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 35).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 62).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 140, 35).addItemStack(actualOutputs.get(0));
	}
}