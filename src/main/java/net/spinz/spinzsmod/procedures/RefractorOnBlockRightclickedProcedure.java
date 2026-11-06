package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModBlocks;
import net.spinz.spinzsmod.SpinzsModMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.function.Function;

public class RefractorOnBlockRightclickedProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Direction direction) {
		if (direction == null)
			return InteractionResult.PASS;
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == SpinzsModModBlocks.GLOWINE_BLOCK.get() && !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "running")) {
			if ((world.getBlockState(BlockPos.containing(x + 0 - (getDirectionFromBlockState(blockstate)).getStepX(), y, z + 0 - (getDirectionFromBlockState(blockstate)).getStepZ()))).getBlock() == Blocks.REDSTONE_BLOCK) {
				if (!((getDirectionFromBlockState(blockstate)) == (direction.getOpposite())) && !(direction == Direction.UP)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putBoolean("running", true);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.refractor.charge")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.refractor.charge")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
					SpinzsModMod.queueServerWork(69, () -> {
						if (!((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == SpinzsModModBlocks.GLOWINE_BLOCK.get()
								&& (world.getBlockState(BlockPos.containing(x + 0 - (getDirectionFromBlockState(blockstate)).getStepX(), y, z + 0 - (getDirectionFromBlockState(blockstate)).getStepZ()))).getBlock() == Blocks.REDSTONE_BLOCK)) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putBoolean("running", false);
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						} else {
							double blastResistance = world.getBlockState(new BlockPos((int) x, (int) y - 1, (int) z)).getBlock().getExplosionResistance();
							double blastResistanceRight = world.getBlockState(
									new BlockPos((int) x + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepX(), (int) y, (int) z + ((getDirectionFromBlockState(blockstate)).getClockWise(Direction.Axis.Y)).getStepZ()))
									.getBlock().getExplosionResistance();
							double blastResistanceLeft = world.getBlockState(new BlockPos((int) x + ((getDirectionFromBlockState(blockstate)).getCounterClockWise(Direction.Axis.Y)).getStepX(), (int) y,
									(int) z + ((getDirectionFromBlockState(blockstate)).getCounterClockWise(Direction.Axis.Y)).getStepZ())).getBlock().getExplosionResistance();
							world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
							if (blastResistanceLeft < 500 || blastResistanceRight < 500 || blastResistance < 500) {
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											"execute as @e[distance=0..50] run damage @s 80 spinzs_mod:flash_boil");
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.EXPLOSION, (x + 0.5), (y + 0.5), (z + 0.5), 8000, 50, 50, 50, 5);
								int horizontalRadiusSphere = (int) 50 - 1;
								int verticalRadiusSphere = (int) 50 - 1;
								int yIterationsSphere = verticalRadiusSphere;
								for (int i = -yIterationsSphere; i <= yIterationsSphere; i++) {
									for (int xi = -horizontalRadiusSphere; xi <= horizontalRadiusSphere; xi++) {
										for (int zi = -horizontalRadiusSphere; zi <= horizontalRadiusSphere; zi++) {
											double distanceSq = (xi * xi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere) + (i * i) / (double) (verticalRadiusSphere * verticalRadiusSphere)
													+ (zi * zi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere);
											if (distanceSq <= 1.0) {
												if ((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).is(BlockTags.create(ResourceLocation.parse("minecraft:logs")))) {
													if (Math.abs(z - z + zi) > 3 || Math.abs(x - x + xi) > 3 || Math.abs(y - y + i) > 1) {
														{
															BlockPos _bp = BlockPos.containing(x + xi, y + i, z + zi);
															BlockState _bs = Blocks.BASALT.defaultBlockState();
															BlockState _bso = world.getBlockState(_bp);
															for (Property<?> _propertyOld : _bso.getProperties()) {
																Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
																if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
																	try {
																		_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
																	} catch (Exception e) {
																	}
															}
															world.setBlock(_bp, _bs, 3);
														}
													}
												} else {
													CharBlockProcedure.execute(world, x + xi, y + i, z + zi, world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi)));
												}
											}
										}
									}
								}
							}
							createTimedLoop(20, 5, _timedLoop -> {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null) {
										_blockEntity.getPersistentData().putDouble("dist", (0.5 + Mth.nextDouble(RandomSource.create(), -0.1, 0.1)));
									}
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
								for (int index0 = 0; index0 < 30; index0++) {
									if (!world.isClientSide()) {
										BlockPos _bp = BlockPos.containing(x, y, z);
										BlockEntity _blockEntity = world.getBlockEntity(_bp);
										BlockState _bs = world.getBlockState(_bp);
										if (_blockEntity != null) {
											_blockEntity.getPersistentData().putDouble("dist", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dist") + 1d / 10));
										}
										if (world instanceof Level _level)
											_level.sendBlockUpdated(_bp, _bs, _bs, 3);
									}
									if (world instanceof ServerLevel _level)
										_level.sendParticles(ParticleTypes.WAX_OFF, (x + (getDirectionFromBlockState(blockstate)).getStepX() * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dist") + 0.5), (y + 0.5),
												(z + (getDirectionFromBlockState(blockstate)).getStepZ() * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dist") + 0.5), 1, 0, 0, 0, 0);
								}
								return true;
							});
							SpinzsModMod.queueServerWork(116, () -> {
								if ((world.getBlockState(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX(), y, z + (getDirectionFromBlockState(blockstate)).getStepZ())))
										.is(BlockTags.create(ResourceLocation.parse("spinzs_mod:refractable_blocks")))) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() + 0.5, y + 0.5, z + (getDirectionFromBlockState(blockstate)).getStepZ() + 0.5),
													BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
										} else {
											_level.playLocalSound((x + (getDirectionFromBlockState(blockstate)).getStepX() + 0.5), (y + 0.5), (z + (getDirectionFromBlockState(blockstate)).getStepZ() + 0.5),
													BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
										}
									}
									if (world instanceof ServerLevel _level) {
										ItemEntity entityToSpawn = new ItemEntity(_level, (x + (getDirectionFromBlockState(blockstate)).getStepX() + 0.5), (y + 0.5), (z + (getDirectionFromBlockState(blockstate)).getStepZ() + 0.5),
												RefractorBlockToItemProcedure.execute(world.getBlockState(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX(), y, z + (getDirectionFromBlockState(blockstate)).getStepZ()))));
										entityToSpawn.setPickUpDelay(10);
										_level.addFreshEntity(entityToSpawn);
									}
									world.setBlock(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX(), y, z + (getDirectionFromBlockState(blockstate)).getStepZ()), Blocks.AIR.defaultBlockState(), 3);
								}
								SpinzsModMod.queueServerWork(5, () -> {
									if ((world.getBlockState(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 2, y, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 2)))
											.is(BlockTags.create(ResourceLocation.parse("spinzs_mod:refractable_blocks")))) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 2 + 0.5, y + 0.5, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 2 + 0.5),
														BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
											} else {
												_level.playLocalSound((x + (getDirectionFromBlockState(blockstate)).getStepX() * 2 + 0.5), (y + 0.5), (z + (getDirectionFromBlockState(blockstate)).getStepZ() * 2 + 0.5),
														BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
											}
										}
										if (world instanceof ServerLevel _level) {
											ItemEntity entityToSpawn = new ItemEntity(_level, (x + (getDirectionFromBlockState(blockstate)).getStepX() * 2 + 0.5), (y + 0.5), (z + (getDirectionFromBlockState(blockstate)).getStepZ() * 2 + 0.5),
													RefractorBlockToItemProcedure
															.execute(world.getBlockState(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 2, y, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 2))));
											entityToSpawn.setPickUpDelay(10);
											_level.addFreshEntity(entityToSpawn);
										}
										world.setBlock(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 2, y, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 2), Blocks.AIR.defaultBlockState(), 3);
									}
									SpinzsModMod.queueServerWork(5, () -> {
										if ((world.getBlockState(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 3, y, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 3)))
												.is(BlockTags.create(ResourceLocation.parse("spinzs_mod:refractable_blocks")))) {
											if (world instanceof Level _level) {
												if (!_level.isClientSide()) {
													_level.playSound(null, BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 3 + 0.5, y + 0.5, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 3 + 0.5),
															BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
												} else {
													_level.playLocalSound((x + (getDirectionFromBlockState(blockstate)).getStepX() * 3 + 0.5), (y + 0.5), (z + (getDirectionFromBlockState(blockstate)).getStepZ() * 3 + 0.5),
															BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
												}
											}
											if (world instanceof ServerLevel _level) {
												ItemEntity entityToSpawn = new ItemEntity(_level, (x + (getDirectionFromBlockState(blockstate)).getStepX() * 3 + 0.5), (y + 0.5), (z + (getDirectionFromBlockState(blockstate)).getStepZ() * 3 + 0.5),
														RefractorBlockToItemProcedure
																.execute(world.getBlockState(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 3, y, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 3))));
												entityToSpawn.setPickUpDelay(10);
												_level.addFreshEntity(entityToSpawn);
											}
											world.setBlock(BlockPos.containing(x + (getDirectionFromBlockState(blockstate)).getStepX() * 3, y, z + (getDirectionFromBlockState(blockstate)).getStepZ() * 3), Blocks.AIR.defaultBlockState(), 3);
										}
										SpinzsModMod.queueServerWork(10, () -> {
											if (!world.isClientSide()) {
												BlockPos _bp = BlockPos.containing(x, y, z);
												BlockEntity _blockEntity = world.getBlockEntity(_bp);
												BlockState _bs = world.getBlockState(_bp);
												if (_blockEntity != null) {
													_blockEntity.getPersistentData().putBoolean("running", false);
												}
												if (world instanceof Level _level)
													_level.sendBlockUpdated(_bp, _bs, _bs, 3);
											}
										});
									});
								});
							});
						}
					});
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.FAIL;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = getPropertyByName(blockState, "facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = getPropertyByName(blockState, "axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static class SpinzsModModTimedLoop {
		private int iterator = 0;
		private int ticks = 0;
		private final int total;
		private final Function<SpinzsModModTimedLoop, Boolean> forEach;

		private SpinzsModModTimedLoop(int total, int ticks, Function<SpinzsModModTimedLoop, Boolean> forEach) {
			this.total = total;
			this.ticks = ticks;
			this.forEach = forEach;
			run();
		}

		private void run() {
			if (forEach.apply(this))
				SpinzsModMod.queueServerWork(ticks, this::next);
		}

		private void next() {
			if (total > ++iterator)
				run();
		}
	}

	private static SpinzsModModTimedLoop createTimedLoop(int total, int ticks, Function<SpinzsModModTimedLoop, Boolean> forEach) {
		return new SpinzsModModTimedLoop(total, ticks, forEach);
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