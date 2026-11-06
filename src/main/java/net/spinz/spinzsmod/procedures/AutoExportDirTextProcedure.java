package net.spinz.spinzsmod.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class AutoExportDirTextProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep1 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep1).toString() : "").substring(0, 1))
				.toUpperCase() + ""
				+ ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep3 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep3).toString() : "").substring(1));
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