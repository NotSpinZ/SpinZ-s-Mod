package net.spinz.spinzsmod.block;

import net.spinz.spinzsmod.procedures.DewarOnTickUpdateProcedure;
import net.spinz.spinzsmod.procedures.DewarOnBlockRightclickedProcedure;
import net.spinz.spinzsmod.procedures.DewarBlockValidPlacementConditionProcedure;
import net.spinz.spinzsmod.procedures.DewarBlockIsPlacedByProcedure;
import net.spinz.spinzsmod.procedures.DewarBlockDestroyedByPlayerProcedure;
import net.spinz.spinzsmod.block.entity.DewarBlockEntity;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.google.common.collect.ImmutableMap;

public class DewarBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<DewarHalfProperty> DEWAR_HALF = EnumProperty.create("dewar_half", DewarHalfProperty.class);
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public DewarBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false)
				.instrument(NoteBlockInstrument.IRON_XYLOPHONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(DEWAR_HALF, DewarHalfProperty.ITEM).setValue(WATERLOGGED, false));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(DEWAR_HALF) == DewarHalfProperty.TOP) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(1, 0, 1, 15, 14, 15), box(3, 14, 10, 9.5, 14.5, 13), box(6, 14, 3, 9.5, 14.5, 6), box(3, 14, 3, 6, 14.5, 10), box(9.5, 14, 3, 13, 14.5, 13), box(6, 14, 6, 10, 15, 6.5), box(6, 14, 6.5, 6.5, 15, 9.5),
							box(9.5, 14, 6.5, 10, 15, 9.5), box(6, 14, 9.5, 10, 15, 10));
					case NORTH -> Shapes.or(box(1, 0, 1, 15, 14, 15), box(6.5, 14, 3, 13, 14.5, 6), box(6.5, 14, 10, 10, 14.5, 13), box(10, 14, 6, 13, 14.5, 13), box(3, 14, 3, 6.5, 14.5, 13), box(6, 14, 9.5, 10, 15, 10),
							box(9.5, 14, 6.5, 10, 15, 9.5), box(6, 14, 6.5, 6.5, 15, 9.5), box(6, 14, 6, 10, 15, 6.5));
					case EAST -> Shapes.or(box(1, 0, 1, 15, 14, 15), box(10, 14, 6.5, 13, 14.5, 13), box(3, 14, 6.5, 6, 14.5, 10), box(3, 14, 10, 10, 14.5, 13), box(3, 14, 3, 13, 14.5, 6.5), box(6, 14, 6, 6.5, 15, 10), box(6.5, 14, 9.5, 9.5, 15, 10),
							box(6.5, 14, 6, 9.5, 15, 6.5), box(9.5, 14, 6, 10, 15, 10));
					case WEST -> Shapes.or(box(1, 0, 1, 15, 14, 15), box(3, 14, 3, 6, 14.5, 9.5), box(10, 14, 6, 13, 14.5, 9.5), box(6, 14, 3, 13, 14.5, 6), box(3, 14, 9.5, 13, 14.5, 13), box(9.5, 14, 6, 10, 15, 10), box(6.5, 14, 6, 9.5, 15, 6.5),
							box(6.5, 14, 9.5, 9.5, 15, 10), box(6, 14, 6, 6.5, 15, 10));
				};
			} else if (state.getValue(DEWAR_HALF) == DewarHalfProperty.BOTTOM) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(14.5, 1, 0.5, 15.5, 11, 1.5), box(0.5, 1, 0.5, 1.5, 11, 1.5),
							box(0.5, 1, 14.5, 1.5, 11, 15.5), box(14.5, 1, 14.5, 15.5, 11, 15.5));
					case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(0.5, 1, 14.5, 1.5, 11, 15.5), box(14.5, 1, 14.5, 15.5, 11, 15.5),
							box(14.5, 1, 0.5, 15.5, 11, 1.5), box(0.5, 1, 0.5, 1.5, 11, 1.5));
					case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(0.5, 1, 0.5, 1.5, 11, 1.5), box(0.5, 1, 14.5, 1.5, 11, 15.5),
							box(14.5, 1, 14.5, 15.5, 11, 15.5), box(14.5, 1, 0.5, 15.5, 11, 1.5));
					case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(14.5, 1, 14.5, 15.5, 11, 15.5), box(14.5, 1, 0.5, 15.5, 11, 1.5),
							box(0.5, 1, 0.5, 1.5, 11, 1.5), box(0.5, 1, 14.5, 1.5, 11, 15.5));
				};
			} else if (state.getValue(DEWAR_HALF) == DewarHalfProperty.ITEM) {
				return switch (state.getValue(FACING)) {
					default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(3, 30, 10, 9.5, 30.5, 13), box(6, 30, 3, 9.5, 30.5, 6), box(3, 30, 3, 6, 30.5, 10), box(9.5, 30, 3, 13, 30.5, 13),
							box(6, 30, 6, 10, 31, 6.5), box(6, 30, 6.5, 6.5, 31, 9.5), box(9.5, 30, 6.5, 10, 31, 9.5), box(6, 30, 9.5, 10, 31, 10), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(14.5, 1, 0.5, 15.5, 11, 1.5),
							box(0.5, 1, 0.5, 1.5, 11, 1.5), box(0.5, 1, 14.5, 1.5, 11, 15.5), box(14.5, 1, 14.5, 15.5, 11, 15.5));
					case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(6.5, 30, 3, 13, 30.5, 6), box(6.5, 30, 10, 10, 30.5, 13), box(10, 30, 6, 13, 30.5, 13), box(3, 30, 3, 6.5, 30.5, 13),
							box(6, 30, 9.5, 10, 31, 10), box(9.5, 30, 6.5, 10, 31, 9.5), box(6, 30, 6.5, 6.5, 31, 9.5), box(6, 30, 6, 10, 31, 6.5), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(0.5, 1, 14.5, 1.5, 11, 15.5),
							box(14.5, 1, 14.5, 15.5, 11, 15.5), box(14.5, 1, 0.5, 15.5, 11, 1.5), box(0.5, 1, 0.5, 1.5, 11, 1.5));
					case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(10, 30, 6.5, 13, 30.5, 13), box(3, 30, 6.5, 6, 30.5, 10), box(3, 30, 10, 10, 30.5, 13), box(3, 30, 3, 13, 30.5, 6.5),
							box(6, 30, 6, 6.5, 31, 10), box(6.5, 30, 9.5, 9.5, 31, 10), box(6.5, 30, 6, 9.5, 31, 6.5), box(9.5, 30, 6, 10, 31, 10), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(0.5, 1, 0.5, 1.5, 11, 1.5),
							box(0.5, 1, 14.5, 1.5, 11, 15.5), box(14.5, 1, 14.5, 15.5, 11, 15.5), box(14.5, 1, 0.5, 15.5, 11, 1.5));
					case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(3, 30, 3, 6, 30.5, 9.5), box(10, 30, 6, 13, 30.5, 9.5), box(6, 30, 3, 13, 30.5, 6), box(3, 30, 9.5, 13, 30.5, 13),
							box(9.5, 30, 6, 10, 31, 10), box(6.5, 30, 6, 9.5, 31, 6.5), box(6.5, 30, 9.5, 9.5, 31, 10), box(6, 30, 6, 6.5, 31, 10), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(14.5, 1, 14.5, 15.5, 11, 15.5),
							box(14.5, 1, 0.5, 15.5, 11, 1.5), box(0.5, 1, 0.5, 1.5, 11, 1.5), box(0.5, 1, 14.5, 1.5, 11, 15.5));
				};
			}
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(3, 30, 10, 9.5, 30.5, 13), box(6, 30, 3, 9.5, 30.5, 6), box(3, 30, 3, 6, 30.5, 10), box(9.5, 30, 3, 13, 30.5, 13),
						box(6, 30, 6, 10, 31, 6.5), box(6, 30, 6.5, 6.5, 31, 9.5), box(9.5, 30, 6.5, 10, 31, 9.5), box(6, 30, 9.5, 10, 31, 10), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(14.5, 1, 0.5, 15.5, 11, 1.5),
						box(0.5, 1, 0.5, 1.5, 11, 1.5), box(0.5, 1, 14.5, 1.5, 11, 15.5), box(14.5, 1, 14.5, 15.5, 11, 15.5));
				case NORTH -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(6.5, 30, 3, 13, 30.5, 6), box(6.5, 30, 10, 10, 30.5, 13), box(10, 30, 6, 13, 30.5, 13), box(3, 30, 3, 6.5, 30.5, 13),
						box(6, 30, 9.5, 10, 31, 10), box(9.5, 30, 6.5, 10, 31, 9.5), box(6, 30, 6.5, 6.5, 31, 9.5), box(6, 30, 6, 10, 31, 6.5), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(0.5, 1, 14.5, 1.5, 11, 15.5),
						box(14.5, 1, 14.5, 15.5, 11, 15.5), box(14.5, 1, 0.5, 15.5, 11, 1.5), box(0.5, 1, 0.5, 1.5, 11, 1.5));
				case EAST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(10, 30, 6.5, 13, 30.5, 13), box(3, 30, 6.5, 6, 30.5, 10), box(3, 30, 10, 10, 30.5, 13), box(3, 30, 3, 13, 30.5, 6.5),
						box(6, 30, 6, 6.5, 31, 10), box(6.5, 30, 9.5, 9.5, 31, 10), box(6.5, 30, 6, 9.5, 31, 6.5), box(9.5, 30, 6, 10, 31, 10), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(0.5, 1, 0.5, 1.5, 11, 1.5),
						box(0.5, 1, 14.5, 1.5, 11, 15.5), box(14.5, 1, 14.5, 15.5, 11, 15.5), box(14.5, 1, 0.5, 15.5, 11, 1.5));
				case WEST -> Shapes.or(box(0, 0, 0, 16, 1, 16), box(1, 3.5, 1, 15, 16, 15), box(1, 16, 1, 15, 30, 15), box(3, 30, 3, 6, 30.5, 9.5), box(10, 30, 6, 13, 30.5, 9.5), box(6, 30, 3, 13, 30.5, 6), box(3, 30, 9.5, 13, 30.5, 13),
						box(9.5, 30, 6, 10, 31, 10), box(6.5, 30, 6, 9.5, 31, 6.5), box(6.5, 30, 9.5, 9.5, 31, 10), box(6, 30, 6, 6.5, 31, 10), box(1, 1, 1, 15, 3, 15), box(1.5, 3, 1.5, 14.5, 3.5, 14.5), box(14.5, 1, 14.5, 15.5, 11, 15.5),
						box(14.5, 1, 0.5, 15.5, 11, 1.5), box(0.5, 1, 0.5, 1.5, 11, 1.5), box(0.5, 1, 14.5, 1.5, 11, 15.5));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.get(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return propagatesSkylightDown(state, worldIn, pos) ? 0 : 1;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, DEWAR_HALF, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(DEWAR_HALF, DewarHalfProperty.ITEM).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return DewarBlockValidPlacementConditionProcedure.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		DewarOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		DewarBlockDestroyedByPlayerProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		return retval;
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		DewarBlockIsPlacedByProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		DewarOnBlockRightclickedProcedure.execute(world, x, y, z, blockstate, entity);
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DewarBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof DewarBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof DewarBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}

	public enum DewarHalfProperty implements StringRepresentable {
		TOP("top"), BOTTOM("bottom"), ITEM("item");

		private final String name;

		private DewarHalfProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}