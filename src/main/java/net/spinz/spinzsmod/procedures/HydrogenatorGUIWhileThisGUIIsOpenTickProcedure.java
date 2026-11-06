package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModMenus;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class HydrogenatorGUIWhileThisGUIIsOpenTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double xOff = 0;
		double yOff = 0;
		double zOff = 0;
		if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "auto_export") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp1)) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep3 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep3).toString() : "").equals("north")) {
				zOff = -1;
			} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep5 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep5).toString() : "").equals("south")) {
				zOff = 1;
			} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep7 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep7).toString() : "").equals("east")) {
				xOff = 1;
			} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep9 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep9).toString() : "").equals("west")) {
				xOff = -1;
			} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep11 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep11).toString() : "").equals("up")) {
				yOff = 1;
			} else if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "export_dir") instanceof EnumProperty _getep13 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep13).toString() : "").equals("down")) {
				yOff = -1;
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu) {
				ItemStack _setstack16 = (new ItemStack((world.getBlockState(BlockPos.containing(x + xOff, y + yOff, z + zOff))).getBlock())).copy();
				_setstack16.setCount(1);
				_menu.getSlots().get(5).set(_setstack16);
				_player.containerMenu.broadcastChanges();
			}
		} else {
			if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu) {
				ItemStack _setstack17 = new ItemStack(Blocks.AIR).copy();
				_setstack17.setCount(1);
				_menu.getSlots().get(5).set(_setstack17);
				_player.containerMenu.broadcastChanges();
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