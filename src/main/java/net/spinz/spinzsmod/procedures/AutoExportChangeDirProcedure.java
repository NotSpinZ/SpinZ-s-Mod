package net.spinz.spinzsmod.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class AutoExportChangeDirProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep1 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep1).toString() : "").equals("north")) {
			{
				String _value = "east";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
		} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep4 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep4).toString() : "").equals("south")) {
			{
				String _value = "west";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
		} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep7 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep7).toString() : "").equals("west")) {
			{
				String _value = "up";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
		} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep10 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep10).toString() : "").equals("east")) {
			{
				String _value = "south";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
		} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep13 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep13).toString() : "").equals("up")) {
			{
				String _value = "down";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
		} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep16 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep16).toString() : "").equals("down")) {
			{
				String _value = "north";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
			}
		} else {
			{
				String _value = "up";
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
					world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
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
}