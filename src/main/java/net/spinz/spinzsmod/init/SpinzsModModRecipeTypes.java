package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.jei_recipes.*;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;

@EventBusSubscriber
public class SpinzsModModRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "spinzs_mod");
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "spinzs_mod");

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		IEventBus bus = ModList.get().getModContainerById("spinzs_mod").get().getEventBus();
		event.enqueueWork(() -> {
			RECIPE_TYPES.register(bus);
			SERIALIZERS.register(bus);
			RECIPE_TYPES.register("electrolysis_jei", () -> ElectrolysisJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("electrolysis_jei", () -> ElectrolysisJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("refracting_jei", () -> RefractingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("refracting_jei", () -> RefractingJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("charring_jei", () -> CharringJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("charring_jei", () -> CharringJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("hydrogenation_jei", () -> HydrogenationJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("hydrogenation_jei", () -> HydrogenationJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("component_application", () -> ComponentApplicationRecipe.Type.INSTANCE);
			SERIALIZERS.register("component_application", () -> ComponentApplicationRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("electrium_smelting_jei", () -> ElectriumSmeltingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("electrium_smelting_jei", () -> ElectriumSmeltingJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("particle_acceleration", () -> ParticleAccelerationRecipe.Type.INSTANCE);
			SERIALIZERS.register("particle_acceleration", () -> ParticleAccelerationRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("electrium_collecting", () -> ElectriumCollectingRecipe.Type.INSTANCE);
			SERIALIZERS.register("electrium_collecting", () -> ElectriumCollectingRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("electrium_crushing_jei", () -> ElectriumCrushingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("electrium_crushing_jei", () -> ElectriumCrushingJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("electrium_dye_crushing_jei", () -> ElectriumDyeCrushingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("electrium_dye_crushing_jei", () -> ElectriumDyeCrushingJEIRecipe.Serializer.INSTANCE);
		});
	}
}