package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;

public class CheckAllPaBlocksProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Direction dir) {
		if (dir == null)
			return false;
		return IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_READER.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_READER.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_COOLANT_STORAGE.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_COOLANT_STORAGE.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_HEAT_MODULE.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_HEAT_MODULE.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_SPEED_MODULE.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_LEFT.get().defaultBlockState(), dir)
						.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_LEFT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT.get().defaultBlockState(), dir)
						.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_RIGHT.get().defaultBlockState(), dir)
						.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_FRONT_RIGHT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_RIGHT.get().defaultBlockState(), dir)
						.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_RIGHT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_RIGHT.get().defaultBlockState(), dir)
						.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_RIGHT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK
						.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_LEFT.get().defaultBlockState(), dir)
						.getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_BACK_LEFT.get()
				&& IsPABlockCorrectProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_LEFT.get().defaultBlockState(), dir).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_ELECTROMAGNETIC_LOOPER_LEFT
						.get();
	}
}