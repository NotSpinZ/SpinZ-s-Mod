package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;

public class RefractorBlockToItemProcedure {
	public static ItemStack execute(BlockState block) {
		if (block.getBlock() == SpinzsModModBlocks.SHADIUM_BLOCK.get()) {
			return new ItemStack(SpinzsModModItems.REFINED_SHADIUM_INGOT.get());
		} else if (block.getBlock() == Blocks.GLASS) {
			return new ItemStack(Blocks.TINTED_GLASS);
		} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:logs")))) {
			return new ItemStack(Blocks.COAL_BLOCK);
		}
		return new ItemStack(Blocks.AIR);
	}
}