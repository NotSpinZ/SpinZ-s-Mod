package net.spinz.spinzsmod.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class SpinzsModModJeiInformation implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("spinzs_mod:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModItems.ELECTRIUM_INGOT.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.electrium_ingot_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModItems.PURE_ELECTRIUM.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.pure_electrium_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModBlocks.ELECTRIUM_CLUSTER.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.electrium_cluster_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModBlocks.REFRACTOR.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.refractor_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModBlocks.SHADIUM_ORE.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.shadium_ore_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModItems.SHADIUM_FRAGMENT.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.shadium_fragment_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SpinzsModModItems.ENDERRUBBER_GOO.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.spinzs_mod.enderrubber_goo_info"));
	}
}