package net.spinz.spinzsmod.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SpeedmodBlockDestroyedByPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if ((getPropertyByName(blockstate, "speedmod_half") instanceof EnumProperty _getep1 ? blockstate.getValue(_getep1).toString() : "").equals("top")) {
			world.destroyBlock(BlockPos.containing(x, y - 1, z), false);
		} else {
			world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
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
}