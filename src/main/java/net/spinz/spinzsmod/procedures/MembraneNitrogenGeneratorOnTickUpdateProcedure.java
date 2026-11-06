package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class MembraneNitrogenGeneratorOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double testfill = 0;
		if (world instanceof ILevelExtension _ext) {
			IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), null);
			if (_fluidHandler != null)
				_fluidHandler.fill(new FluidStack(SpinzsModModFluids.NITROGEN.get(), 5), IFluidHandler.FluidAction.EXECUTE);
		}
		if (FluidStack.isSameFluid(new Object() {
			FluidStack getFluidInTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidInTank((int) 0, BlockPos.containing((x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX()), (y + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepY()),
				(z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()))), new FluidStack(SpinzsModModFluids.NITROGEN.get(), 1)) || FluidStack.isSameFluid(new Object() {
					FluidStack getFluidInTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidInTank((int) 0,
						BlockPos.containing((x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX()), (y + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepY()),
								(z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()))),
						new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1))
						&& (world.getBlockState(BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepY(),
								z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()))).getBlock() == SpinzsModModBlocks.CRYOCOOLER.get()
				|| new Object() {
					FluidStack getFluidInTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidInTank((int) 0, BlockPos.containing((x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX()), (y + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepY()),
						(z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()))).getAmount() == 0) {
			testfill = fillTankSimulate(world,
					BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepY(),
							z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()),
					(int) Math.max(Math.min(drainTankSimulate(world, BlockPos.containing(x, y, z), 10000, null), 20), 0), null, SpinzsModModFluids.NITROGEN.get());
			final Fluid _fluid101 = new FluidStack(SpinzsModModFluids.NITROGEN.get(), 1).getFluid();
			final int _amount101 = (int) Math.max(Math.min(drainTankSimulate(world, BlockPos.containing(x, y, z), 10000, null), 20), 0);
			Optional.ofNullable(getIFluidHandler(world, BlockPos.containing((x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX()),
					(y + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepY()), (z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()))))
					.ifPresent(f -> f.fill(new FluidStack(_fluid101, _amount101), IFluidHandler.FluidAction.EXECUTE));
			if (world instanceof ILevelExtension _ext) {
				IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), null);
				if (_fluidHandler != null)
					_fluidHandler.drain((int) testfill, IFluidHandler.FluidAction.EXECUTE);
			}
		}
	}

	private static int drainTankSimulate(LevelAccessor level, BlockPos pos, int amount, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			IFluidHandler fluidHandler = levelExtension.getCapability(Capabilities.FluidHandler.BLOCK, pos, direction);
			if (fluidHandler != null)
				return fluidHandler.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount();
		}
		return 0;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
	}

	private static int fillTankSimulate(LevelAccessor level, BlockPos pos, int amount, Direction direction, Fluid fluid) {
		if (level instanceof ILevelExtension levelExtension) {
			IFluidHandler fluidHandler = levelExtension.getCapability(Capabilities.FluidHandler.BLOCK, pos, direction);
			if (fluidHandler != null)
				return fluidHandler.fill(new FluidStack(fluid, amount), IFluidHandler.FluidAction.SIMULATE);
		}
		return 0;
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