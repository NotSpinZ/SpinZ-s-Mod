package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModBlocks;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ParticleAcceleratorInputOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double xOff = 0;
		double zOff = 0;
		xOff = x + ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getOpposite()).getStepX()
				+ ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getCounterClockWise(Direction.Axis.Y)).getStepX() * 3;
		zOff = z + ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getOpposite()).getStepZ()
				+ ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))).getCounterClockWise(Direction.Axis.Y)).getStepZ() * 3;
		if ((world.getBlockState(BlockPos.containing(xOff, y, zOff))).getBlock() == SpinzsModModBlocks.PARTICLE_ACCELERATOR_CONTROL_PANEL.get()) {
			if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() >= 1) {
				if (getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "electrium") + ElectriumToFEProcedure.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()) <= 50000) {
					if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).isDamageableItem()) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(xOff, y, zOff);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("electrium",
										(getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "electrium") + ElectriumToFEProcedure.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
								&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							int _slotid = 1;
							ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
							_stk.hurtAndBreak(1, _serverLevel, null, _stkprov -> {
							});
							_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
						}
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(xOff, y, zOff);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("electrium",
										(getBlockNBTNumber(world, BlockPos.containing(xOff, y, zOff), "electrium") + ElectriumToFEProcedure.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							int _slotid = 1;
							ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
							_stk.shrink(1);
							_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
						}
					}
				}
			}
		}
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
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