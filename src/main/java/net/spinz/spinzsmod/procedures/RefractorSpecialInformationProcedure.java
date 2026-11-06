package net.spinz.spinzsmod.procedures;

import net.minecraft.client.gui.screens.Screen;

public class RefractorSpecialInformationProcedure {
	public static String execute() {
		if (Screen.hasShiftDown()) {
			return "\u00A77The hyper-focused laser produced by this machine combined with a radioactive material produces enough heat to flash boil all humans in a radius of 50 blocks in less than a nanosecond. Power from the back with a \u00A7cRedstone Block\u00A77 and put a \u00A7eGlowine Block\u00A77 above it and right-click to power.";
		}
		return "\u00A78Press Shift for more info";
	}
}