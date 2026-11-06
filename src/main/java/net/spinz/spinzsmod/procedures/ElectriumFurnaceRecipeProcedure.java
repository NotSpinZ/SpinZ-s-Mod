package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.jei_recipes.ElectriumSmeltingJEIRecipe;
import net.spinz.spinzsmod.init.SpinzsModModItems;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

import java.util.stream.Collectors;
import java.util.List;

public class ElectriumFurnaceRecipeProcedure {
	public static ItemStack execute(LevelAccessor world, ItemStack item1, ItemStack item2) {
		if ((new Object() {
			public ItemStack getResult() {
				if (world instanceof Level _lvl) {
					net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
					List<ElectriumSmeltingJEIRecipe> recipes = rm.getAllRecipesFor(ElectriumSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					for (ElectriumSmeltingJEIRecipe recipe : recipes) {
						NonNullList<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test(item1))
							continue;
						if (!ingredients.get(1).test(item2))
							continue;
						if (!ingredients.get(2).test(new ItemStack(SpinzsModModItems.COPPER_COIL.get())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
				}
				return ItemStack.EMPTY;
			}
		}.getResult()) != null) {
			return new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<ElectriumSmeltingJEIRecipe> recipes = rm.getAllRecipesFor(ElectriumSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (ElectriumSmeltingJEIRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test(item1))
								continue;
							if (!ingredients.get(1).test(item2))
								continue;
							if (!ingredients.get(2).test(new ItemStack(SpinzsModModItems.COPPER_COIL.get())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult();
		}
		return new ItemStack(Blocks.AIR);
	}
}