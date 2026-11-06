package net.spinz.spinzsmod.fluid;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.init.SpinzsModModFluidTypes;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

public abstract class CarbonicMatterFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> SpinzsModModFluidTypes.CARBONIC_MATTER_TYPE.get(), () -> SpinzsModModFluids.CARBONIC_MATTER.get(),
			() -> SpinzsModModFluids.FLOWING_CARBONIC_MATTER.get()).explosionResistance(100f).tickRate(150).levelDecreasePerBlock(4).slopeFindDistance(1).block(() -> (LiquidBlock) SpinzsModModBlocks.CARBONIC_MATTER.get());

	private CarbonicMatterFluid() {
		super(PROPERTIES);
	}

	public static class Source extends CarbonicMatterFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends CarbonicMatterFluid {
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