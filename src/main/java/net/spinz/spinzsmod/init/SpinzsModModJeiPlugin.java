package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.jei_recipes.*;

import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;

@JeiPlugin
public class SpinzsModModJeiPlugin implements IModPlugin {
	public static mezz.jei.api.recipe.RecipeType<ElectrolysisJEIRecipe> ElectrolysisJEI_Type = new mezz.jei.api.recipe.RecipeType<>(ElectrolysisJEIRecipeCategory.UID, ElectrolysisJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<RefractingJEIRecipe> RefractingJEI_Type = new mezz.jei.api.recipe.RecipeType<>(RefractingJEIRecipeCategory.UID, RefractingJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<CharringJEIRecipe> CharringJEI_Type = new mezz.jei.api.recipe.RecipeType<>(CharringJEIRecipeCategory.UID, CharringJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<HydrogenationJEIRecipe> HydrogenationJEI_Type = new mezz.jei.api.recipe.RecipeType<>(HydrogenationJEIRecipeCategory.UID, HydrogenationJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ComponentApplicationRecipe> ComponentApplication_Type = new mezz.jei.api.recipe.RecipeType<>(ComponentApplicationRecipeCategory.UID, ComponentApplicationRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ElectriumSmeltingJEIRecipe> ElectriumSmeltingJEI_Type = new mezz.jei.api.recipe.RecipeType<>(ElectriumSmeltingJEIRecipeCategory.UID, ElectriumSmeltingJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ParticleAccelerationRecipe> ParticleAcceleration_Type = new mezz.jei.api.recipe.RecipeType<>(ParticleAccelerationRecipeCategory.UID, ParticleAccelerationRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ElectriumCollectingRecipe> ElectriumCollecting_Type = new mezz.jei.api.recipe.RecipeType<>(ElectriumCollectingRecipeCategory.UID, ElectriumCollectingRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ElectriumCrushingJEIRecipe> ElectriumCrushingJEI_Type = new mezz.jei.api.recipe.RecipeType<>(ElectriumCrushingJEIRecipeCategory.UID, ElectriumCrushingJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ElectriumDyeCrushingJEIRecipe> ElectriumDyeCrushingJEI_Type = new mezz.jei.api.recipe.RecipeType<>(ElectriumDyeCrushingJEIRecipeCategory.UID, ElectriumDyeCrushingJEIRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("spinzs_mod:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new ElectrolysisJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new RefractingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CharringJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new HydrogenationJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ComponentApplicationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElectriumSmeltingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ParticleAccelerationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElectriumCollectingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElectriumCrushingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElectriumDyeCrushingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<ElectrolysisJEIRecipe> ElectrolysisJEIRecipes = recipeManager.getAllRecipesFor(ElectrolysisJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElectrolysisJEI_Type, ElectrolysisJEIRecipes);
		List<RefractingJEIRecipe> RefractingJEIRecipes = recipeManager.getAllRecipesFor(RefractingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(RefractingJEI_Type, RefractingJEIRecipes);
		List<CharringJEIRecipe> CharringJEIRecipes = recipeManager.getAllRecipesFor(CharringJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CharringJEI_Type, CharringJEIRecipes);
		List<HydrogenationJEIRecipe> HydrogenationJEIRecipes = recipeManager.getAllRecipesFor(HydrogenationJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(HydrogenationJEI_Type, HydrogenationJEIRecipes);
		List<ComponentApplicationRecipe> ComponentApplicationRecipes = recipeManager.getAllRecipesFor(ComponentApplicationRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ComponentApplication_Type, ComponentApplicationRecipes);
		List<ElectriumSmeltingJEIRecipe> ElectriumSmeltingJEIRecipes = recipeManager.getAllRecipesFor(ElectriumSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElectriumSmeltingJEI_Type, ElectriumSmeltingJEIRecipes);
		List<ParticleAccelerationRecipe> ParticleAccelerationRecipes = recipeManager.getAllRecipesFor(ParticleAccelerationRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ParticleAcceleration_Type, ParticleAccelerationRecipes);
		List<ElectriumCollectingRecipe> ElectriumCollectingRecipes = recipeManager.getAllRecipesFor(ElectriumCollectingRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElectriumCollecting_Type, ElectriumCollectingRecipes);
		List<ElectriumCrushingJEIRecipe> ElectriumCrushingJEIRecipes = recipeManager.getAllRecipesFor(ElectriumCrushingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElectriumCrushingJEI_Type, ElectriumCrushingJEIRecipes);
		List<ElectriumDyeCrushingJEIRecipe> ElectriumDyeCrushingJEIRecipes = recipeManager.getAllRecipesFor(ElectriumDyeCrushingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElectriumDyeCrushingJEI_Type, ElectriumDyeCrushingJEIRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.ELECTRYLOSIS_CONDUCTOR.get().asItem()), ElectrolysisJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.REFRACTOR.get().asItem()), RefractingJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.REFRACTOR.get().asItem()), CharringJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_CONTROL_PANEL.get().asItem()), CharringJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.HYDROGENATOR.get().asItem()), HydrogenationJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.COMPONENT_APPLIER.get().asItem()), ComponentApplication_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.ELECTRIUM_FURNACE.get().asItem()), ElectriumSmeltingJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_CONTROL_PANEL.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_READER.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_COOLANT_STORAGE.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_HEAT_MODULE.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_LEFT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_RIGHT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_RIGHT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_RIGHT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_LEFT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_LEFT.get().asItem()), ParticleAcceleration_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.ELECTRIUM_COLLECTOR.get().asItem()), ElectriumCollecting_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.ELECTRIUM_CRUSHER.get().asItem()), ElectriumCrushingJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(SpinzsModModBlocks.ELECTRIUM_DYE_CRUSHER.get().asItem()), ElectriumDyeCrushingJEI_Type);
	}
}