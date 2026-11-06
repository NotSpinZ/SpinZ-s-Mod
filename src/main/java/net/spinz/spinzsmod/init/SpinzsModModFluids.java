/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.fluid.*;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class SpinzsModModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, SpinzsModMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> NITROGEN = REGISTRY.register("nitrogen", () -> new NitrogenFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_NITROGEN = REGISTRY.register("flowing_nitrogen", () -> new NitrogenFluid.Flowing());
	public static final DeferredHolder<Fluid, FlowingFluid> LIQUID_NITROGEN = REGISTRY.register("liquid_nitrogen", () -> new LiquidNitrogenFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_LIQUID_NITROGEN = REGISTRY.register("flowing_liquid_nitrogen", () -> new LiquidNitrogenFluid.Flowing());
	public static final DeferredHolder<Fluid, FlowingFluid> DARK_MATTER = REGISTRY.register("dark_matter", () -> new DarkMatterFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_DARK_MATTER = REGISTRY.register("flowing_dark_matter", () -> new DarkMatterFluid.Flowing());
	public static final DeferredHolder<Fluid, FlowingFluid> ADAMANTINE_MATTER = REGISTRY.register("adamantine_matter", () -> new AdamantineMatterFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_ADAMANTINE_MATTER = REGISTRY.register("flowing_adamantine_matter", () -> new AdamantineMatterFluid.Flowing());
	public static final DeferredHolder<Fluid, FlowingFluid> CARBONIC_MATTER = REGISTRY.register("carbonic_matter", () -> new CarbonicMatterFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_CARBONIC_MATTER = REGISTRY.register("flowing_carbonic_matter", () -> new CarbonicMatterFluid.Flowing());
	public static final DeferredHolder<Fluid, FlowingFluid> LIQUID_ELECTRIUM = REGISTRY.register("liquid_electrium", () -> new LiquidElectriumFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_LIQUID_ELECTRIUM = REGISTRY.register("flowing_liquid_electrium", () -> new LiquidElectriumFluid.Flowing());

	@EventBusSubscriber(Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(NITROGEN.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_NITROGEN.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(LIQUID_NITROGEN.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_LIQUID_NITROGEN.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(DARK_MATTER.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_DARK_MATTER.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(ADAMANTINE_MATTER.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_ADAMANTINE_MATTER.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(CARBONIC_MATTER.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_CARBONIC_MATTER.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(LIQUID_ELECTRIUM.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_LIQUID_ELECTRIUM.get(), RenderType.translucent());
		}
	}
}