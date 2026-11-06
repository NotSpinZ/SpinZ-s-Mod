package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class CryocoolerOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double start = 0;
		start = drainTankSimulate(world, BlockPos.containing(x, y, z), 10000, null);
		final Fluid _fluid3 = new FluidStack(SpinzsModModFluids.NITROGEN.get(), 1).getFluid();
		final int _amount3 = (int) 20;
		Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(new FluidStack(_fluid3, _amount3), IFluidHandler.FluidAction.EXECUTE));
		final Fluid _fluid6 = new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1).getFluid();
		final int _amount6 = (int) (start - drainTankSimulate(world, BlockPos.containing(x, y, z), 10000, null));
		Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.fill(new FluidStack(_fluid6, _amount6), IFluidHandler.FluidAction.EXECUTE));
		if (Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, (y - 1), z))).isPresent() && (FluidStack.isSameFluid(new Object() {
			FluidStack getFluidInTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidInTank((int) 0, BlockPos.containing(x, (y - 1), z)), new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1)) || new Object() {
			FluidStack getFluidInTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidInTank((int) 0, BlockPos.containing(x, (y - 1), z)).getAmount() == 0)) {
			final Fluid _fluid15 = new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1).getFluid();
			final int _amount15 = (int) (drainTankSimulate(world, BlockPos.containing(x, y, z), 20, null));
			Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, (y - 1), z))).ifPresent(f -> f.fill(new FluidStack(_fluid15, _amount15), IFluidHandler.FluidAction.EXECUTE));
			final Fluid _fluid20 = new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1).getFluid();
			final int _amount20 = (int) (drainTankSimulate(world, BlockPos.containing(x, y, z), 20, null));
			Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(new FluidStack(_fluid20, _amount20), IFluidHandler.FluidAction.EXECUTE));
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

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
	}
}