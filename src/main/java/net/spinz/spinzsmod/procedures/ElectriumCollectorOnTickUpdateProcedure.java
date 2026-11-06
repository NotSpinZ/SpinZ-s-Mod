package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.energy.IEnergyStorage;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ElectriumCollectorOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		double xOff = 0;
		double yOff = 0;
		double zOff = 0;
		if (getPropertyByName(blockstate, "auto_export") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == Blocks.AIR.asItem())) {
				i = 0;
				if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep5 ? blockstate.getValue(_getep5).toString() : "").equals("north")) {
					zOff = -1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep7 ? blockstate.getValue(_getep7).toString() : "").equals("south")) {
					zOff = 1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep9 ? blockstate.getValue(_getep9).toString() : "").equals("east")) {
					xOff = 1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep11 ? blockstate.getValue(_getep11).toString() : "").equals("west")) {
					xOff = -1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep13 ? blockstate.getValue(_getep13).toString() : "").equals("up")) {
					yOff = 1;
				} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep15 ? blockstate.getValue(_getep15).toString() : "").equals("down")) {
					yOff = -1;
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
							|| itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).getCount() < 64
									&& (itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).copy()).getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()) {
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x + xOff, y + yOff, z + zOff), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).copy();
							_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x + xOff, y + yOff, z + zOff), (int) i).getCount() + 1);
							_itemHandlerModifiable.setStackInSlot((int) i, _setstack);
						}
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							int _slotid = 1;
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
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Items.IRON_INGOT
				|| (BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()).toString()).equals("mekanism:ingot_osmium")) {
			if (((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == SpinzsModModItems.ELECTRIUM_INGOT.get()
					&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() < (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getMaxStackSize()
					|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == Blocks.AIR.asItem()) && getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 100) {
				if (world instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
					if (_entityStorage != null)
						_entityStorage.extractEnergy(100, false);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = new ItemStack(SpinzsModModItems.ELECTRIUM_INGOT.get()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() + 1);
					_itemHandlerModifiable.setStackInSlot(1, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() - 1);
					_itemHandlerModifiable.setStackInSlot(0, _setstack);
				}
			}
		} else if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Items.IRON_NUGGET
				|| (BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()).toString()).equals("mekanism:nugget_osmium")) {
			if (((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == SpinzsModModItems.PURE_ELECTRIUM.get()
					&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() < (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getMaxStackSize()
					|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == Blocks.AIR.asItem()) && getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 500) {
				if (world instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
					if (_entityStorage != null)
						_entityStorage.extractEnergy(500, false);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = new ItemStack(SpinzsModModItems.PURE_ELECTRIUM.get()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() + 1);
					_itemHandlerModifiable.setStackInSlot(1, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() - 1);
					_itemHandlerModifiable.setStackInSlot(0, _setstack);
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

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			IEnergyStorage energyStorage = levelExtension.getCapability(Capabilities.EnergyStorage.BLOCK, pos, direction);
			if (energyStorage != null)
				return energyStorage.getEnergyStored();
		}
		return 0;
	}
}