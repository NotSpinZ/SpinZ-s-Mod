package net.spinz.spinzsmod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class ParticleAcceleratorControlPanelBlockIsPlacedByProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("on", false);
				_blockEntity.getPersistentData().putDouble("heat", 90);
				_blockEntity.getPersistentData().putDouble("speed", 0);
				_blockEntity.getPersistentData().putDouble("timer", 0);
				_blockEntity.getPersistentData().putDouble("hhtimer", 0);
				_blockEntity.getPersistentData().putDouble("hstimer", 0);
				_blockEntity.getPersistentData().putDouble("coolant", 0);
				_blockEntity.getPersistentData().putString("coolantType", "none");
				_blockEntity.getPersistentData().putDouble("electrium", 0);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
	}
}