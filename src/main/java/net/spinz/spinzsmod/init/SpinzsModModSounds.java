/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

public class SpinzsModModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, SpinzsModMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_REFRACTOR_CHARGE = REGISTRY.register("blocks.refractor.charge",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.refractor.charge")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_BLOCKS_HYDROGENATOR_PRESSURE_RELEASE = REGISTRY.register("spinzs_mod.blocks.hydrogenator.pressure_release",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.blocks.hydrogenator.pressure_release")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_MISC_ELECTRIUM_SHOCK = REGISTRY.register("spinzs_mod.misc.electrium_shock",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.misc.electrium_shock")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_PARTICLE_ACCELERATOR_PA_STARTUP = REGISTRY.register("spinzs_mod.particle_accelerator.pa_startup",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.particle_accelerator.pa_startup")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_WIP = REGISTRY.register("spinzs_mod.wip", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.wip")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_ITEMS_POPPER_POP = REGISTRY.register("spinzs_mod.items.popper.pop",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.items.popper.pop")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_ITEMS_POPPER_THROW = REGISTRY.register("spinzs_mod.items.popper.throw",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.items.popper.throw")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPINZS_MOD_PARTICLE_ACCELERATOR_RUNNING = REGISTRY.register("spinzs_mod.particle_accelerator.running",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "spinzs_mod.particle_accelerator.running")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_PARAMELACONITE_BREAK = REGISTRY.register("blocks.paramelaconite.break",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.paramelaconite.break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_PARAMELACONITE_FALL = REGISTRY.register("blocks.paramelaconite.fall",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.paramelaconite.fall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_PARAMELACONITE_HIT = REGISTRY.register("blocks.paramelaconite.hit",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.paramelaconite.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_PARAMELACONITE_PLACE = REGISTRY.register("blocks.paramelaconite.place",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.paramelaconite.place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_PARAMELACONITE_STEP = REGISTRY.register("blocks.paramelaconite.step",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.paramelaconite.step")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_ELECTRIUM_FURNACE_INCINERATE = REGISTRY.register("blocks.electrium_furnace.incinerate",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.electrium_furnace.incinerate")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOCKS_ELECTRIUM_CRUSHER_CRUSH = REGISTRY.register("blocks.electrium_crusher.crush",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "blocks.electrium_crusher.crush")));
}