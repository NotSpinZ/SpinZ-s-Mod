/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.spinz.spinzsmod.init;

import net.spinz.spinzsmod.client.gui.*;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

@EventBusSubscriber(Dist.CLIENT)
public class SpinzsModModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(SpinzsModModMenus.PARTICLE_ACCELERATOR_CONTROL_PANEL_GUI.get(), ParticleAcceleratorControlPanelGUIScreen::new);
		event.register(SpinzsModModMenus.ELECTRYLOSIS_CONDUCTOR_GUI.get(), ElectrylosisConductorGUIScreen::new);
		event.register(SpinzsModModMenus.HYDROGENATOR_GUI.get(), HydrogenatorGUIScreen::new);
		event.register(SpinzsModModMenus.DEWAR_GUI.get(), DewarGUIScreen::new);
		event.register(SpinzsModModMenus.COMPONENT_APPLIER_GUI.get(), ComponentApplierGUIScreen::new);
		event.register(SpinzsModModMenus.ELECTRIUM_FURNACE_GUI.get(), ElectriumFurnaceGUIScreen::new);
		event.register(SpinzsModModMenus.PARTICLE_ACCELERATOR_INPUT_GUI.get(), ParticleAcceleratorInputGUIScreen::new);
		event.register(SpinzsModModMenus.PARTICLE_ACCELERATOR_OUTPUT_GUI.get(), ParticleAcceleratorOutputGUIScreen::new);
		event.register(SpinzsModModMenus.ELECTRIUM_COLLECTOR_GUI.get(), ElectriumCollectorGUIScreen::new);
		event.register(SpinzsModModMenus.ELECTRIUM_CRUSHER_GUI.get(), ElectriumCrusherGUIScreen::new);
		event.register(SpinzsModModMenus.ELECTRIUM_DYE_CRUSHER_GUI.get(), ElectriumDyeCrusherGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}