package net.spinz.spinzsmod.fluid;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.init.SpinzsModModFluidTypes;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

public abstract class AdamantineMatterFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> SpinzsModModFluidTypes.ADAMANTINE_MATTER_TYPE.get(), () -> SpinzsModModFluids.ADAMANTINE_MATTER.get(),
			() -> SpinzsModModFluids.FLOWING_ADAMANTINE_MATTER.get()).explosionResistance(100f).tickRate(150).levelDecreasePerBlock(4).slopeFindDistance(1).block(() -> (LiquidBlock) SpinzsModModBlocks.ADAMANTINE_MATTER.get());

	private AdamantineMatterFluid() {
		super(PROPERTIES);
	}

	public static class Source extends AdamantineMatterFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends AdamantineMatterFluid {
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