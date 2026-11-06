/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.fluid.types.*;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

public class SpinzsModModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, SpinzsModMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> NITROGEN_TYPE = REGISTRY.register("nitrogen", () -> new NitrogenFluidType());
	public static final DeferredHolder<FluidType, FluidType> LIQUID_NITROGEN_TYPE = REGISTRY.register("liquid_nitrogen", () -> new LiquidNitrogenFluidType());
	public static final DeferredHolder<FluidType, FluidType> DARK_MATTER_TYPE = REGISTRY.register("dark_matter", () -> new DarkMatterFluidType());
	public static final DeferredHolder<FluidType, FluidType> ADAMANTINE_MATTER_TYPE = REGISTRY.register("adamantine_matter", () -> new AdamantineMatterFluidType());
	public static final DeferredHolder<FluidType, FluidType> CARBONIC_MATTER_TYPE = REGISTRY.register("carbonic_matter", () -> new CarbonicMatterFluidType());
	public static final DeferredHolder<FluidType, FluidType> LIQUID_ELECTRIUM_TYPE = REGISTRY.register("liquid_electrium", () -> new LiquidElectriumFluidType());
}