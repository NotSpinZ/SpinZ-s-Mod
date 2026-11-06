package net.spinz.spinzsmod.fluid;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.init.SpinzsModModFluidTypes;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

public abstract class NitrogenFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> SpinzsModModFluidTypes.NITROGEN_TYPE.get(), () -> SpinzsModModFluids.NITROGEN.get(), () -> SpinzsModModFluids.FLOWING_NITROGEN.get())
			.explosionResistance(0f).tickRate(10000).levelDecreasePerBlock(8).slopeFindDistance(1).block(() -> (LiquidBlock) SpinzsModModBlocks.NITROGEN.get());

	private NitrogenFluid() {
		super(PROPERTIES);
	}

	public static class Source extends NitrogenFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends NitrogenFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}