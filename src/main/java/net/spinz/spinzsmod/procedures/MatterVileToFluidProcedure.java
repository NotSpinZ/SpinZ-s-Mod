package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;
import net.spinz.spinzsmod.init.SpinzsModModFluids;

import net.neoforged.neoforge.fluids.FluidStack;

import net.minecraft.world.item.ItemStack;

public class MatterVileToFluidProcedure {
	public static FluidStack execute(ItemStack item) {
		if (item.getItem() == SpinzsModModItems.DARK_MATTER_VILE.get()) {
			return new FluidStack(SpinzsModModFluids.DARK_MATTER.get(), 1);
		}
		return FluidStack.EMPTY;
	}
}