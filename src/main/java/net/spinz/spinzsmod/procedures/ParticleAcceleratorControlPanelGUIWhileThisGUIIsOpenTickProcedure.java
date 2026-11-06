package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModMenus;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ParticleAcceleratorControlPanelGUIWhileThisGUIIsOpenTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double diff = 0;
		double added = 0;
		Vec3 coords = Vec3.ZERO;
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "on")) {
			diff = Math.round(Math
					.abs(((entity instanceof Player _entity1 && _entity1.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(2, "heat", 0.0) : 0.0) - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat"))
					/ 0.001) * 0.001;
			added = Math.round(Math.min(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") * 0.1, 4) / 0.001) * 0.001;
			if (diff > added) {
				if (((entity instanceof Player _entity4 && _entity4.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu4) ? _menu4.getMenuState(2, "heat", 0.0) : 0.0) - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") >= 0) {
					if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "electrium") >= diff * 0.5) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("electrium", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "electrium") - diff * 0.5));
								_blockEntity.getPersistentData().putDouble("heat", ((entity instanceof Player _entity9 && _entity9.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu9) ? _menu9.getMenuState(2, "heat", 0.0) : 0.0));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else {
						if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu)
							_menu.sendMenuStateUpdate(_player, 2, "heat", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat")), true);
					}
				} else {
					if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") >= diff * 8) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") - diff * 8));
								_blockEntity.getPersistentData().putDouble("heat", ((entity instanceof Player _entity16 && _entity16.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu16) ? _menu16.getMenuState(2, "heat", 0.0) : 0.0));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else {
						if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu)
							_menu.sendMenuStateUpdate(_player, 2, "heat", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat")), true);
					}
				}
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 2, "heat", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat")), true);
			}
			diff = Math.round(Math.abs(
					((entity instanceof Player _entity22 && _entity22.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu22) ? _menu22.getMenuState(2, "speed", 0.0) : 0.0) - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "speed"))
					/ 0.001) * 0.001;
			if (diff > added && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "electrium") - diff * 8 > 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("electrium", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "electrium") - diff * 8));
						_blockEntity.getPersistentData().putDouble("speed", ((entity instanceof Player _entity27 && _entity27.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu27) ? _menu27.getMenuState(2, "speed", 0.0) : 0.0));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else {
				if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu)
					_menu.sendMenuStateUpdate(_player, 2, "speed", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "speed")), true);
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("speed", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 2, "speed", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "speed")), true);
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("heat", 90);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof SpinzsModModMenus.MenuAccessor _menu)
				_menu.sendMenuStateUpdate(_player, 2, "heat", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat")), true);
		}
		coords = GetPACoordsProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT.get().defaultBlockState(), getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))));
		if ((world.getBlockState(BlockPos.containing(coords.x(), coords.y(), coords.z()))).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_INPUT.get()) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(coords.x(), coords.y(), coords.z()), 0).copy()).copy();
				_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(coords.x(), coords.y(), coords.z()), 0).getCount());
				_itemHandlerModifiable.setStackInSlot(1, _setstack);
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(coords.x(), coords.y(), coords.z()), 2).copy()).copy();
				_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(coords.x(), coords.y(), coords.z()), 2).getCount());
				_itemHandlerModifiable.setStackInSlot(0, _setstack);
			}
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = getPropertyByName(blockState, "facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = getPropertyByName(blockState, "axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
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