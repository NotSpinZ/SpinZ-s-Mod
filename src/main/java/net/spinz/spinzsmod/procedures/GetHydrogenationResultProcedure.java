package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;

public class GetHydrogenationResultProcedure {
	public static ItemStack execute(ItemStack item) {
		if (item.getItem() == SpinzsModModItems.CO_2_VILE.get()) {
			return new ItemStack(SpinzsModModItems.FORMIC_ACID_VILE.get());
		}
		return new ItemStack(Blocks.AIR);
	}
}