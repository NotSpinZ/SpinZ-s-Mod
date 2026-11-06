package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class SpeedmodBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == (blockStateWithEnum(SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get().defaultBlockState(), "speedmod_half", "top")).getBlock()
						&& (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == (blockStateWithEnum(SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get().defaultBlockState(), "speedmod_half", "bottom")).getBlock()
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == (blockStateWithEnum(SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get().defaultBlockState(), "speedmod_half", "bottom")).getBlock()
						&& (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == (blockStateWithEnum(SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get().defaultBlockState(), "speedmod_half", "top")).getBlock()) {
			return true;
		}
		return false;
	}

	private static BlockState blockStateWithEnum(BlockState blockState, String property, String newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof EnumProperty ep && ep.getValue(newValue).isPresent() ? blockState.setValue(ep, (Enum) ep.getValue(newValue).get()) : blockState;
	}
}