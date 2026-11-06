package net.spinz.spinzsmod.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

public class ElectriumWrenchRightclickedOnBlockProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return InteractionResult.PASS;
		String export_dir = "";
		if (((BuiltInRegistries.BLOCK.getKey(blockstate.getBlock()).toString()).substring(0, 10)).equals("spinzs_mod")) {
			if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep3 ? blockstate.getValue(_getep3).toString() : "") != null) {
				if (!itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("toggle")) {
					if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep7 ? blockstate.getValue(_getep7).toString() : "").equals("north")) {
						export_dir = "East";
						{
							String _value = "east";
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
								world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
						}
					} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep10 ? blockstate.getValue(_getep10).toString() : "").equals("south")) {
						export_dir = "West";
						{
							String _value = "west";
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
								world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
						}
					} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep13 ? blockstate.getValue(_getep13).toString() : "").equals("west")) {
						export_dir = "Up";
						{
							String _value = "up";
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
								world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
						}
					} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep16 ? blockstate.getValue(_getep16).toString() : "").equals("east")) {
						export_dir = "South";
						{
							String _value = "south";
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
								world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
						}
					} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep19 ? blockstate.getValue(_getep19).toString() : "").equals("up")) {
						export_dir = "Down";
						{
							String _value = "down";
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
								world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
						}
					} else if ((getPropertyByName(blockstate, "export_dir") instanceof EnumProperty _getep22 ? blockstate.getValue(_getep22).toString() : "").equals("down")) {
						export_dir = "North";
						{
							String _value = "north";
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("export_dir") instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
								world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
						}
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Export Direction: " + export_dir)), true);
				} else {
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("auto_export") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, (!(getPropertyByName(blockstate, "auto_export") instanceof BooleanProperty _getbp26 && blockstate.getValue(_getbp26)))), 3);
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("Auto Export: " + (!(getPropertyByName(blockstate, "auto_export") instanceof BooleanProperty _getbp29 && blockstate.getValue(_getbp29))))), true);
				}
				if (!(getEntityGameType(entity) == GameType.CREATIVE)) {
					if (world instanceof ServerLevel _level) {
						itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
						});
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:spinzs_mod.misc.electrium_shock")), SoundSource.PLAYERS, (float) 0.6,
								(float) Mth.nextDouble(RandomSource.create(), 1.1, 1.2));
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:spinzs_mod.misc.electrium_shock")), SoundSource.PLAYERS, (float) 0.6, (float) Mth.nextDouble(RandomSource.create(), 1.1, 1.2),
								false);
					}
				}
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.FAIL;
		}
		return InteractionResult.FAIL;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}