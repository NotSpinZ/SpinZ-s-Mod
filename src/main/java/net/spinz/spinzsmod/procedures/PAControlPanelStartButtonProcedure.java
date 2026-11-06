package net.spinz.spinzsmod.procedures;

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
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

public class PAControlPanelStartButtonProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z) {
		Vec3 vector = Vec3.ZERO;
		vector = GetPACoordsProcedure.execute(world, x, y, z, SpinzsModModBlocks.PARTICLE_ACCELERATOR_OUTPUT.get().defaultBlockState(), getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z)))));
		if (CheckAllPaBlocksProcedure.execute(world, x, y, z, getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y, z))))) && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "coolant") >= 10
				&& !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "on") && (FluidStack.isSameFluid(MatterVileToFluidProcedure.execute(new Object() {
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
				}.getResult()), new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing((vector.x()), (vector.y()), (vector.z())))) || FluidStack.isSameFluid(FluidStack.EMPTY, new Object() {
					FluidStack getFluidFromTank(final int tank, final BlockPos pos) {
						return Optional.ofNullable(getIFluidHandler(world, pos)).map(f -> f.getFluidInTank(tank).copy()).orElse(FluidStack.EMPTY);
					}
				}.getFluidFromTank((int) 0, BlockPos.containing((vector.x()), (vector.y()), (vector.z()))))) && !FluidStack.isSameFluid(FluidStack.EMPTY, MatterVileToFluidProcedure.execute(new Object() {
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
				}.getResult())) && itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() >= 1) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("on", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:spinzs_mod.particle_accelerator.pa_startup")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:spinzs_mod.particle_accelerator.pa_startup")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			SpinzsModMod.queueServerWork(55, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:spinzs_mod.particle_accelerator.running")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:spinzs_mod.particle_accelerator.running")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			});
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
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

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
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