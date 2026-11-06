/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.block.entity.*;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

@EventBusSubscriber
public class SpinzsModModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SpinzsModMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RefractorBlockEntity>> REFRACTOR = register("refractor", SpinzsModModBlocks.REFRACTOR, RefractorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ParticleAcceleratorControlPanelBlockEntity>> PARTICLE_ACCELERATOR_CONTROL_PANEL = register("particle_accelerator_control_panel",
			SpinzsModModBlocks.PARTICLE_ACCELERATOR_CONTROL_PANEL, ParticleAcceleratorControlPanelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ParticleAcceleratorInputBlockEntity>> PARTICLE_ACCELERATOR_INPUT = register("particle_accelerator_input", SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT,
			ParticleAcceleratorInputBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ParticleAcceleratorOutputBlockEntity>> PARTICLE_ACCELERATOR_OUTPUT = register("particle_accelerator_output", SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT,
			ParticleAcceleratorOutputBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ParticleAcceleratorCoolantStorageBlockEntity>> PARTICLE_ACCELERATOR_COOLANT_STORAGE = register("particle_accelerator_coolant_storage",
			SpinzsModModBlocks.PARTICLE_ACCELERATOR_COOLANT_STORAGE, ParticleAcceleratorCoolantStorageBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectrylosisConductorBlockEntity>> ELECTRYLOSIS_CONDUCTOR = register("electrylosis_conductor", SpinzsModModBlocks.ELECTRYLOSIS_CONDUCTOR,
			ElectrylosisConductorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HydrogenatorBlockEntity>> HYDROGENATOR = register("hydrogenator", SpinzsModModBlocks.HYDROGENATOR, HydrogenatorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DewarBlockEntity>> DEWAR = register("dewar", SpinzsModModBlocks.DEWAR, DewarBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MembraneNitrogenGeneratorBlockEntity>> MEMBRANE_NITROGEN_GENERATOR = register("membrane_nitrogen_generator", SpinzsModModBlocks.MEMBRANE_NITROGEN_GENERATOR,
			MembraneNitrogenGeneratorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CryocoolerBlockEntity>> CRYOCOOLER = register("cryocooler", SpinzsModModBlocks.CRYOCOOLER, CryocoolerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ComponentApplierBlockEntity>> COMPONENT_APPLIER = register("component_applier", SpinzsModModBlocks.COMPONENT_APPLIER, ComponentApplierBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectriumFurnaceBlockEntity>> ELECTRIUM_FURNACE = register("electrium_furnace", SpinzsModModBlocks.ELECTRIUM_FURNACE, ElectriumFurnaceBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectriumCollectorBlockEntity>> ELECTRIUM_COLLECTOR = register("electrium_collector", SpinzsModModBlocks.ELECTRIUM_COLLECTOR, ElectriumCollectorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectriumCrusherBlockEntity>> ELECTRIUM_CRUSHER = register("electrium_crusher", SpinzsModModBlocks.ELECTRIUM_CRUSHER, ElectriumCrusherBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectriumDyeCrusherBlockEntity>> ELECTRIUM_DYE_CRUSHER = register("electrium_dye_crusher", SpinzsModModBlocks.ELECTRIUM_DYE_CRUSHER, ElectriumDyeCrusherBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, REFRACTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PARTICLE_ACCELERATOR_CONTROL_PANEL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, PARTICLE_ACCELERATOR_CONTROL_PANEL.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PARTICLE_ACCELERATOR_INPUT.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PARTICLE_ACCELERATOR_OUTPUT.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, PARTICLE_ACCELERATOR_OUTPUT.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PARTICLE_ACCELERATOR_COOLANT_STORAGE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, PARTICLE_ACCELERATOR_COOLANT_STORAGE.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRYLOSIS_CONDUCTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, HYDROGENATOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DEWAR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, DEWAR.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MEMBRANE_NITROGEN_GENERATOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, MEMBRANE_NITROGEN_GENERATOR.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CRYOCOOLER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, CRYOCOOLER.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COMPONENT_APPLIER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIUM_FURNACE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIUM_COLLECTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ELECTRIUM_COLLECTOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIUM_CRUSHER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIUM_DYE_CRUSHER.get(), SidedInvWrapper::new);
	}
}