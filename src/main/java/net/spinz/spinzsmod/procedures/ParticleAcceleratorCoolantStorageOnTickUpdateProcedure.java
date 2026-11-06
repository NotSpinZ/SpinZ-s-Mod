package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class ParticleAcceleratorCoolantStorageOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double amount = 0;
		double xOff = 0;
		double zOff = 0;
		xOff = x + ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getOpposite()).getStepX() * 3
				+ ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getClockWise(Direction.Axis.Y)).getStepX() * 2;
		zOff = z + ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getOpposite()).getStepZ() * 3
				+ ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getClockWise(Direction.Axis.Y)).getStepZ() * 2;
		if ((world.getBlockState(BlockPos.containing(xOff, y, zOff))).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_CONTROL_PANEL.get()) {
			amount = new Object() {
				FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
					return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
				}
			}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)).getAmount();
			if ((getBlockNBTString(world, BlockPos.containing(xOff, y, zOff), "coolantType")).equals("none")) {
				final int _amount21 = (int) amount;
				Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(_amount21, IFluidHandler.FluidAction.EXECUTE));
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(xOff, y, zOff);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "coolant") + amount));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (FluidStack.isSameFluid(new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1))) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(xOff, y, zOff);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putString("coolantType", "nitrogen");
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if (FluidStack.isSameFluid(new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(Fluids.FLOWING_WATER, 1))) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(xOff, y, zOff);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putString("coolantType", "helium");
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(xOff, y, zOff);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putString("coolantType", "coolium");
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if ((getBlockNBTString(world, BlockPos.containing(xOff, y, zOff), "coolantType")).equals("nitrogen")) {
				if (FluidStack.isSameFluid(new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1))) {
					final int _amount37 = (int) amount;
					Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(_amount37, IFluidHandler.FluidAction.EXECUTE));
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(xOff, y, zOff);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "coolant") + amount));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if ((getBlockNBTString(world, BlockPos.containing(xOff, y, zOff), "coolantType")).equals("helium")) {
				if (FluidStack.isSameFluid(new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(Fluids.FLOWING_WATER, 1))) {
					final int _amount44 = (int) amount;
					Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(_amount44, IFluidHandler.FluidAction.EXECUTE));
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(xOff, y, zOff);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "coolant") + amount));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if ((getBlockNBTString(world, BlockPos.containing(xOff, y, zOff), "coolantType")).equals("coolium")) {
				if (FluidStack.isSameFluid(new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(Fluids.FLOWING_LAVA, 1))) {
					final int _amount51 = (int) amount;
					Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(_amount51, IFluidHandler.FluidAction.EXECUTE));
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(xOff, y, zOff);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "coolant") + amount));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
		}
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = getPropertyByName(blockState, "facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = getPropertyByName(blockState, "axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
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