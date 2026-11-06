package net.spinz.spinzsmod.procedures;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class FluidTankTooltipDewarProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)).getHoverName().getString() + " " + (new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)).getAmount() + new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, (y + 1), z)).getAmount()) + "mB";
	}

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
	}
}