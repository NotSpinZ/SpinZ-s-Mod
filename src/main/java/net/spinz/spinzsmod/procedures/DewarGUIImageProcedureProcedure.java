package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModFluids;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class DewarGUIImageProcedureProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		if (FluidStack.isSameFluid(new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(Fluids.WATER, 1))) {
			return "liquid_tank_water.png";
		} else if (FluidStack.isSameFluid(new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(SpinzsModModFluids.LIQUID_NITROGEN.get(), 1)) || FluidStack.isSameFluid(new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(SpinzsModModFluids.NITROGEN.get(), 1))) {
			return "liquid_tank_liquid_nitrogen.png";
		} else if (FluidStack.isSameFluid(new Object() {
			FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
				return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
			}
		}.getFluidFromTank((int) 0, BlockPos.containing(x, y, z)), new FluidStack(Fluids.LAVA, 1))) {
			return "liquid_tank_lava.png";
		}
		return "liquid_tank_temp.png";
	}

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
	}
}