package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class IsPABlockCorrectProcedure {
	public static BlockState execute(LevelAccessor world, double x, double y, double z, BlockState expect, Direction dir) {
		if (dir == null)
			return Blocks.AIR.defaultBlockState();
		BlockState block = Blocks.AIR.defaultBlockState();
		if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 3 + (dir.getOpposite()).getStepZ(), y, z + (dir.getOpposite()).getStepZ() * 3 + (dir.getOpposite()).getStepX() * (-1))));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == (dir.getClockWise(Direction.Axis.Y))) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 3 + (dir.getOpposite()).getStepZ(), y + 1, z + (dir.getOpposite()).getStepZ() * 3 + (dir.getOpposite()).getStepX() * (-1))));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == (dir.getClockWise(Direction.Axis.Y))) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_READER.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getCounterClockWise(Direction.Axis.Y)).getStepX(), y, z + (dir.getCounterClockWise(Direction.Axis.Y)).getStepZ())));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_COOLANT_STORAGE.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 2 + (dir.getOpposite()).getStepZ() * (-3), y, z + (dir.getOpposite()).getStepZ() * 2 + (dir.getOpposite()).getStepX() * 3)));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == (dir.getCounterClockWise(Direction.Axis.Y))) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_HEAT_MODULE.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 2 + (dir.getOpposite()).getStepZ(), y, z + (dir.getOpposite()).getStepZ() * 2 + (dir.getOpposite()).getStepX() * (-1))));
			if (block.getBlock() == expect.getBlock() && (getPropertyByName(block, "heatmod_half") instanceof EnumProperty _getep62 ? block.getValue(_getep62).toString() : "").equals("bottom")
					&& (getDirectionFromBlockState(block)) == (dir.getClockWise(Direction.Axis.Y))) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() + (dir.getOpposite()).getStepZ(), y, z + (dir.getOpposite()).getStepZ() + (dir.getOpposite()).getStepX() * (-1))));
			if (block.getBlock() == expect.getBlock() && (getPropertyByName(block, "speedmod_half") instanceof EnumProperty _getep77 ? block.getValue(_getep77).toString() : "").equals("bottom")
					&& (getDirectionFromBlockState(block)) == (dir.getClockWise(Direction.Axis.Y))) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_LEFT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 3, y, z + (dir.getOpposite()).getStepZ() * 3)));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_LEFT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX(), y, z + (dir.getOpposite()).getStepZ())));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_LEFT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 2, y, z + (dir.getOpposite()).getStepZ() * 2)));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 3 + (dir.getOpposite()).getStepZ() * (-1), y, z + (dir.getOpposite()).getStepZ() * 3 + (dir.getOpposite()).getStepX())));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() + (dir.getOpposite()).getStepZ() * (-1), y, z + (dir.getOpposite()).getStepZ() + (dir.getOpposite()).getStepX())));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_RIGHT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() + (dir.getOpposite()).getStepZ() * (-2), y, z + (dir.getOpposite()).getStepZ() + (dir.getOpposite()).getStepX() * 2)));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_RIGHT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 2 + (dir.getOpposite()).getStepZ() * (-2), y, z + (dir.getOpposite()).getStepZ() * 2 + (dir.getOpposite()).getStepX() * 2)));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_RIGHT.get()) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 3 + (dir.getOpposite()).getStepZ() * (-2), y, z + (dir.getOpposite()).getStepZ() * 3 + (dir.getOpposite()).getStepX() * 2)));
			if (block.getBlock() == expect.getBlock() && (getDirectionFromBlockState(block)) == dir) {
				return block;
			}
		} else if (expect.getBlock() == Blocks.DRIPSTONE_BLOCK) {
			block = (world.getBlockState(BlockPos.containing(x + (dir.getOpposite()).getStepX() * 4 + (dir.getOpposite()).getStepZ() * (-1), y, z + (dir.getOpposite()).getStepZ() * 4 + (dir.getOpposite()).getStepX())));
			if (block.is(BlockTags.create(ResourceLocation.parse("spinzs_mod:drivers"))) && (getDirectionFromBlockState(block)) == (dir.getOpposite())) {
				return block;
			}
		}
		return Blocks.AIR.defaultBlockState();
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = getPropertyByName(blockState, "facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = getPropertyByName(blockState, "axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}