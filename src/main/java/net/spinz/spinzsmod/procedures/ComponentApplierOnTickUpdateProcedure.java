package net.spinz.spinzsmod.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class ComponentApplierOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		double xOff = 0;
		double yOff = 0;
		double zOff = 0;
		if (getPropertyByName(blockstate, "auto_export") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == Blocks.AIR.asItem())) {
				i = 0;
				if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep5 ? blockstate.getValue(_getep5).toString() : "").equals("north")) {
					zOff = -1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep7 ? blockstate.getValue(_getep7).toString() : "").equals("south")) {
					zOff = 1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep9 ? blockstate.getValue(_getep9).toString() : "").equals("west")) {
					xOff = -1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep11 ? blockstate.getValue(_getep11).toString() : "").equals("east")) {
					xOff = 1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep13 ? blockstate.getValue(_getep13).toString() : "").equals("down")) {
					yOff = -1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep15 ? blockstate.getValue(_getep15).toString() : "").equals("up")) {
					yOff = 1;
				}
				for (int index0 = 0; index0 < (int) new Object() {
					public int getContainerSize(LevelAccessor world, BlockPos pos) {
						BlockEntity _ent = world.getBlockEntity(pos);
						if (_ent != null) {
							if (_ent instanceof BaseContainerBlockEntity _block) {
								return _block.getContainerSize();
							}
						}
						return 0;
					}

					public int getAmount(LevelAccessor world, BlockPos pos) {
						Block block = world.getBlockState(pos).getBlock();
						if (block == Blocks.CHEST || block == Blocks.TRAPPED_CHEST) {
							boolean isSingle = block.getStateDefinition().getProperty("type") instanceof EnumProperty _getep5 && world.getBlockState(pos).getValue(_getep5).toString().equals("SINGLE");
							if (!isSingle) {
								return getContainerSize(world, pos) * 2;
							}
						}
						return getContainerSize(world, pos);
					}
				}.getAmount(world, new BlockPos((int) (x + xOff), (int) (y + yOff), (int) (z + zOff))); index0++) {
					if ((itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).copy()).getItem() == Blocks.AIR.asItem()
							|| itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).getCount() < (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getMaxStackSize()
									&& (itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).copy()).getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()) {
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x + xOff, y + yOff, z + zOff), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).copy();
							_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).getCount() + 1);
							_itemHandlerModifiable.setStackInSlot((int) i, _setstack);
						}
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							int _slotid = 3;
							ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
							_stk.shrink(1);
							_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
						}
						break;
					}
					i = i + 1;
				}
			}
		}
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).getCount() >= (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getMaxStackSize() || !(ComponentApplicationRecipeProcedure
				.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy(), itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy(), itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())
				.getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem())) && !((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == Blocks.AIR.asItem())
				|| ComponentApplicationRecipeProcedure
						.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy(), itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy(), itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())
						.getItem() == Blocks.AIR.asItem()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("stage", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("stage", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "stage") + 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "stage") >= 160) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("stage", 0);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = ComponentApplicationRecipeProcedure
							.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy(), itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy(), itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())
							.copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).getCount() + 1);
					_itemHandlerModifiable.setStackInSlot(3, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() - 1);
					_itemHandlerModifiable.setStackInSlot(0, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() - 1);
					_itemHandlerModifiable.setStackInSlot(1, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).getCount() - 1);
					_itemHandlerModifiable.setStackInSlot(2, _setstack);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.anvil.use")), SoundSource.BLOCKS, 1, (float) Mth.nextDouble(RandomSource.create(), 0.55, 0.75));
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.anvil.use")), SoundSource.BLOCKS, 1, (float) Mth.nextDouble(RandomSource.create(), 0.55, 0.75), false);
					}
				}
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
}