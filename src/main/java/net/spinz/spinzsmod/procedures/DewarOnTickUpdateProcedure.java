package net.spinz.spinzsmod.procedures;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class DewarOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double start = 0;
		if ((getPropertyByName(blockstate, "dewar_half") instanceof EnumProperty _getep1 ? blockstate.getValue(_getep1).toString() : "").equals("top")) {
			if (FluidStack.isSameFluid(new Object() {
				FluidStack getFluidInTank(final int tank, final BlockPos pos) {
					return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
				}
			}.getFluidInTank((int) 0, BlockPos.containing(x, (y - 1), z)), new Object() {
				FluidStack getFluidInTank(final int tank, final BlockPos pos) {
					return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
				}
			}.getFluidInTank((int) 0, BlockPos.containing(x, y, z))) || new Object() {
				FluidStack getFluidInTank(final int tank, final BlockPos pos) {
					return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
				}
			}.getFluidInTank((int) 0, BlockPos.containing(x, (y - 1), z)).getAmount() == 0) {
				start = new Object() {
					FluidStack getFluidInTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidInTank((int) 0, BlockPos.containing(x, (y - 1), z)).getAmount();
				final Fluid _fluid12 = new Object() {
					FluidStack getFluidInTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidInTank((int) 0, BlockPos.containing(x, y, z)).getFluid();
				final int _amount12 = (int) new Object() {
					FluidStack getFluidInTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidInTank((int) 0, BlockPos.containing(x, y, z)).getAmount();
				Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, (y - 1), z))).ifPresent(f -> f.fill(new FluidStack(_fluid12, _amount12), IFluidHandler.FluidAction.EXECUTE));
				final int _amount15 = (int) (new Object() {
					FluidStack getFluidInTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidInTank((int) 0, BlockPos.containing(x, (y - 1), z)).getAmount() - start);
				Optional.ofNullable(getIFluidHandler(world, BlockPos.containing(x, y, z))).ifPresent(f -> f.drain(_amount15, IFluidHandler.FluidAction.EXECUTE));
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
	}
}