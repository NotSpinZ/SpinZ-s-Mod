package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;

import net.minecraft.world.item.ItemStack;

public class ElectriumToFEProcedure {
	public static double execute(ItemStack item) {
		if (item.getItem() == SpinzsModModItems.ELECTRIUM_INGOT.get()) {
			return 10;
		} else if (item.getItem() == SpinzsModModItems.PURE_ELECTRIUM.get()) {
			return 50;
		}
		return 0;
	}
}