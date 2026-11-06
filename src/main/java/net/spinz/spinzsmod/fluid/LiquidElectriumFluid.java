package net.spinz.spinzsmod.fluid;

import net.spinz.spinzsmod.init.SpinzsModModFluids;
import net.spinz.spinzsmod.init.SpinzsModModFluidTypes;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

public abstract class LiquidElectriumFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> SpinzsModModFluidTypes.LIQUID_ELECTRIUM_TYPE.get(), () -> SpinzsModModFluids.LIQUID_ELECTRIUM.get(),
			() -> SpinzsModModFluids.FLOWING_LIQUID_ELECTRIUM.get()).explosionResistance(100f).tickRate(2).slopeFindDistance(1).block(() -> (LiquidBlock) SpinzsModModBlocks.LIQUID_ELECTRIUM.get());

	private LiquidElectriumFluid() {
		super(PROPERTIES);
	}

	public static class Source extends LiquidElectriumFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends LiquidElectriumFluid {
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