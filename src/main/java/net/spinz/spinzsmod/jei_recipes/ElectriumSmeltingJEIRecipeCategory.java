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
import java.util.ArrayList;

public class ElectriumSmeltingJEIRecipeCategory implements IRecipeCategory<ElectriumSmeltingJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("spinzs_mod:electrium_smelting_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("spinzs_mod:textures/screens/electrium_smelting_jei.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public ElectriumSmeltingJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 87);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SpinzsModModBlocks.ELECTRIUM_FURNACE.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<ElectriumSmeltingJEIRecipe> getRecipeType() {
		return SpinzsModModJeiPlugin.ElectriumSmeltingJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Electrium Smelting JEI");
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
	public void draw(ElectriumSmeltingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/electrium_flame.png"), 116, 44, 0, 32 * ((mc.player.tickCount / 1) % 28) - 1, 32, 32, 32, 896);

		guiGraphics.blit(ResourceLocation.parse("spinzs_mod:textures/screens/electrylosis_electrium.png"), 12, 25, 0, 16 * ((mc.player.tickCount / 2) % 22) - 1, 24, 16, 24, 352);

		guiGraphics.drawString(mc.font, Component.translatable("gui.spinzs_mod.electrium_smelting_jei.label_electrium_furnace"), 44, 8, -12829636, false);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ElectriumSmeltingJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> stacks = new ArrayList<>();
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(2, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		stacks.clear();
		for (ItemStack item : (List<ItemStack>) List.of(recipe.getIngredients().get(0).getItems()))
			stacks.add(new ItemStack(item.getItem(), recipe.integers().get(0)));
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 26).addItemStacks(stacks);
		stacks.clear();
		for (ItemStack item : (List<ItemStack>) List.of(recipe.getIngredients().get(1).getItems()))
			stacks.add(new ItemStack(item.getItem(), recipe.integers().get(1)));
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 62).addItemStacks(stacks);
		stacks.clear();
		for (ItemStack item : (List<ItemStack>) List.of(recipe.getIngredients().get(2).getItems()))
			stacks.add(new ItemStack(item.getItem(), recipe.integers().get(2)));
		builder.addSlot(RecipeIngredientRole.INPUT, 88, 44).addItemStacks(stacks);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 26).addItemStack(actualOutputs.get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 149, 26).addItemStack(actualOutputs.get(1));
	}
}