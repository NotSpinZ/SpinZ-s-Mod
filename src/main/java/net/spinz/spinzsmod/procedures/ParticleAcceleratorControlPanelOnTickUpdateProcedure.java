package net.spinz.spinzsmod.procedures;

import org.checkerframework.checker.units.qual.s;

import net.spinz.spinzsmod.jei_recipes.ParticleAccelerationRecipe;
import net.spinz.spinzsmod.init.SpinzsModModItems;
import net.spinz.spinzsmod.init.SpinzsModModBlocks;
import net.spinz.spinzsmod.SpinzsModMod;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

public class ParticleAcceleratorControlPanelOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double num = 0;
		boolean valid = false;
		Vec3 vect = Vec3.ZERO;
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "on")) {
			if (CheckAllPaBlocksProcedure.execute(world, x, y, z, getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))) && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "hhtimer") <= 6
					&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "hstimer") <= 6 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") > 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("timer", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") + 0.05));
						_blockEntity.getPersistentData().putDouble("heat", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") + Math.min(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") * 0.1, 4)));
						_blockEntity.getPersistentData().putDouble("speed", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "speed") + Math.min(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") * 0.1, 4)));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "coolantType")).equals("nitrogen") || (getBlockNBTString(world, BlockPos.containing(x, y, z), "coolantType")).equals("none")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") - 3));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "coolantType")).equals("helium")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") - 2));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "coolantType")).equals("coolium")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("coolant", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") - 0.1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") >= 185) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("hhtimer", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "hhtimer") + 0.05));
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
							_blockEntity.getPersistentData().putDouble("hhtimer", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "speed") >= 285) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("hstimer", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "hstimer") + 0.05));
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
							_blockEntity.getPersistentData().putDouble("hstimer", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "timer") >= 44.95) {
					{
						if (world instanceof Level _lvl) {
							net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
							List<ParticleAccelerationRecipe> recipes = rm.getAllRecipesFor(ParticleAccelerationRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
							for (ParticleAccelerationRecipe recipe : recipes) {
								List<Ingredient> ingredients = recipe.getIngredients();
								if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
									continue;
								if (!ingredients.get(1).test(new ItemStack(SpinzsModModItems.VILE.get())))
									continue;
								List<ItemStack> reciperesult = recipe.getResultItems();
								for (String stringiterator : recipe.strings()) {
									valid = true;
									SpinzsModMod.LOGGER.info(stringiterator);
									if ((stringiterator.substring(0, 5)).equals("heat:")) {
										SpinzsModMod.LOGGER.info(stringiterator.substring(6, (stringiterator).length() - 2));
										num = new Object() {
											double convert(String s) {
												try {
													return Double.parseDouble(s.trim());
												} catch (Exception e) {
												}
												return 0;
											}
										}.convert(stringiterator.substring(6, (stringiterator).length() - 2));
										valid = valid && Math.abs(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") - num) <= 28;
									} else {
										SpinzsModMod.LOGGER.info(stringiterator.substring(7, (stringiterator).length() - 4));
										num = new Object() {
											double convert(String s) {
												try {
													return Double.parseDouble(s.trim());
												} catch (Exception e) {
												}
												return 0;
											}
										}.convert(stringiterator.substring(7, (stringiterator).length() - 4));
										valid = valid && Math.abs(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "speed") - num) <= 19;
									}
								}
								break;
							}
						}
					}
					if (valid) {
						vect = GetPACoordsProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT.get().defaultBlockState(), getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))));
						final Fluid _fluid56 = MatterVileToFluidProcedure.execute(new Object() {
							public ItemStack getResult() {
								if (world instanceof Level _lvl) {
									net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
									List<ParticleAccelerationRecipe> recipes = rm.getAllRecipesFor(ParticleAccelerationRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
									for (ParticleAccelerationRecipe recipe : recipes) {
										NonNullList<Ingredient> ingredients = recipe.getIngredients();
										if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
											continue;
										if (!ingredients.get(1).test(new ItemStack(SpinzsModModItems.VILE.get())))
											continue;
										return recipe.getResultItems().get(0).copy();
									}
								}
								return ItemStack.EMPTY;
							}
						}.getResult()).getFluid();
						final int _amount56 = (int) (Mth.nextInt(RandomSource.create(), 9, 13));
						Optional.ofNullable(getIFluidHandler(world, BlockPos.containing((vect.x()), (vect.y()), (vect.z())))).ifPresent(f -> f.fill(new FluidStack(_fluid56, _amount56), IFluidHandler.FluidAction.EXECUTE));
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putBoolean("on", false);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("on", false);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, x, (y - 1), z, 20, Level.ExplosionInteraction.BLOCK);
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"execute as @e[distance=0..75] run damage @s 120 spinzs_mod:flash_boil");
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.EXPLOSION, (x + 0.5), (y + 0.5), (z + 0.5), 8000, 50, 50, 50, 5);
				int horizontalRadiusSphere = (int) 75 - 1;
				int verticalRadiusSphere = (int) 75 - 1;
				int yIterationsSphere = verticalRadiusSphere;
				for (int i = -yIterationsSphere; i <= yIterationsSphere; i++) {
					for (int xi = -horizontalRadiusSphere; xi <= horizontalRadiusSphere; xi++) {
						for (int zi = -horizontalRadiusSphere; zi <= horizontalRadiusSphere; zi++) {
							double distanceSq = (xi * xi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere) + (i * i) / (double) (verticalRadiusSphere * verticalRadiusSphere)
									+ (zi * zi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere);
							if (distanceSq <= 1.0) {
								CharBlockProcedure.execute(world, x + xi, y + i, z + zi, world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi)));
							}
						}
					}
				}
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
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

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static IFluidHandler getIFluidHandler(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension extension) {
			return extension.getCapability(Capabilities.FluidHandler.BLOCK, pos, null);
		}
		return null;
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