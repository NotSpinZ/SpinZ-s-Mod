/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.world.inventory.*;
import net.spinz.spinzsmod.network.MenuStateUpdateMessage;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import java.util.Map;

public class SpinzsModModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, SpinzsModMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<ParticleAcceleratorControlPanelGUIMenu>> PARTICLE_ACCELERATOR_CONTROL_PANEL_GUI = REGISTRY.register("particle_accelerator_control_panel_gui",
			() -> IMenuTypeExtension.create(ParticleAcceleratorControlPanelGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElectrylosisConductorGUIMenu>> ELECTRYLOSIS_CONDUCTOR_GUI = REGISTRY.register("electrylosis_conductor_gui", () -> IMenuTypeExtension.create(ElectrylosisConductorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<HydrogenatorGUIMenu>> HYDROGENATOR_GUI = REGISTRY.register("hydrogenator_gui", () -> IMenuTypeExtension.create(HydrogenatorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<DewarGUIMenu>> DEWAR_GUI = REGISTRY.register("dewar_gui", () -> IMenuTypeExtension.create(DewarGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ComponentApplierGUIMenu>> COMPONENT_APPLIER_GUI = REGISTRY.register("component_applier_gui", () -> IMenuTypeExtension.create(ComponentApplierGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElectriumFurnaceGUIMenu>> ELECTRIUM_FURNACE_GUI = REGISTRY.register("electrium_furnace_gui", () -> IMenuTypeExtension.create(ElectriumFurnaceGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ParticleAcceleratorInputGUIMenu>> PARTICLE_ACCELERATOR_INPUT_GUI = REGISTRY.register("particle_accelerator_input_gui",
			() -> IMenuTypeExtension.create(ParticleAcceleratorInputGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ParticleAcceleratorOutputGUIMenu>> PARTICLE_ACCELERATOR_OUTPUT_GUI = REGISTRY.register("particle_accelerator_output_gui",
			() -> IMenuTypeExtension.create(ParticleAcceleratorOutputGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElectriumCollectorGUIMenu>> ELECTRIUM_COLLECTOR_GUI = REGISTRY.register("electrium_collector_gui", () -> IMenuTypeExtension.create(ElectriumCollectorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElectriumCrusherGUIMenu>> ELECTRIUM_CRUSHER_GUI = REGISTRY.register("electrium_crusher_gui", () -> IMenuTypeExtension.create(ElectriumCrusherGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ElectriumDyeCrusherGUIMenu>> ELECTRIUM_DYE_CRUSHER_GUI = REGISTRY.register("electrium_dye_crusher_gui", () -> IMenuTypeExtension.create(ElectriumDyeCrusherGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof SpinzsModModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				PacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}