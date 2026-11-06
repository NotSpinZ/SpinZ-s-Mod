package net.spinz.spinzsmod.block;

import net.spinz.spinzsmod.procedures.CryocoolerOnTickUpdateProcedure;
import net.spinz.spinzsmod.block.entity.CryocoolerBlockEntity;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.google.common.collect.ImmutableMap;

public class CryocoolerBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public CryocoolerBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.BLOCK).isRedstoneConductor((bs, br, bp) -> false)
				.instrument(NoteBlockInstrument.IRON_XYLOPHONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(7, -3.5, 7, 9, 0, 9), box(6.5, -4, 6.5, 9.5, -3.5, 9.5), box(6.25, 0.5, 6.25, 9.75, 1.5, 9.75), box(6.5, -1, 6.5, 9.5, 0, 9.5), box(6, 0, 6, 10, 0.5, 10), box(6.5, 1, 6.5, 9.5, 5.5, 9.5),
						box(6, 5.5, 6, 10, 6, 10), box(5, 8.5, 5, 16, 9, 11), box(14, 6, 5.5, 15.5, 11, 10.5), box(15.5, 6, 6.5, 17, 8.5, 9.5), box(15.5, 9.5, 7, 16, 10.5, 9), box(15.5, 9.5, 9.5, 16, 10, 10), box(15.5, 8.25, 9.5, 16.5, 9.25, 10.5),
						box(15.5, 8.25, 5.5, 16.5, 9.25, 6.5), box(15.5, 9.5, 6, 16, 10, 6.5), box(10.5, 9.5, 6, 14, 10.5, 7), box(10.5, 9.5, 9, 14, 10.5, 10), box(10.5, 6.5, 9, 14, 7.5, 10), box(10.5, 6.5, 6, 14, 7.5, 7),
						box(10.5, 7, 7.75, 14, 7.5, 8.25), box(10.5, 9.5, 7.75, 14, 10, 8.25), box(5.5, 6, 5.5, 10.5, 11, 10.5), box(6, 11, 6, 10, 12, 10), box(7.5, 12, 7.5, 8.5, 13.5, 8.5), box(6, 12, 9.5, 6.5, 12.5, 10),
						box(9.5, 12, 9.5, 10, 12.5, 10), box(6, 12, 6, 6.5, 12.5, 6.5), box(9.5, 12, 6, 10, 12.5, 6.5));
				case NORTH -> Shapes.or(box(7, -3.5, 7, 9, 0, 9), box(6.5, -4, 6.5, 9.5, -3.5, 9.5), box(6.25, 0.5, 6.25, 9.75, 1.5, 9.75), box(6.5, -1, 6.5, 9.5, 0, 9.5), box(6, 0, 6, 10, 0.5, 10), box(6.5, 1, 6.5, 9.5, 5.5, 9.5),
						box(6, 5.5, 6, 10, 6, 10), box(0, 8.5, 5, 11, 9, 11), box(0.5, 6, 5.5, 2, 11, 10.5), box(-1, 6, 6.5, 0.5, 8.5, 9.5), box(0, 9.5, 7, 0.5, 10.5, 9), box(0, 9.5, 6, 0.5, 10, 6.5), box(-0.5, 8.25, 5.5, 0.5, 9.25, 6.5),
						box(-0.5, 8.25, 9.5, 0.5, 9.25, 10.5), box(0, 9.5, 9.5, 0.5, 10, 10), box(2, 9.5, 9, 5.5, 10.5, 10), box(2, 9.5, 6, 5.5, 10.5, 7), box(2, 6.5, 6, 5.5, 7.5, 7), box(2, 6.5, 9, 5.5, 7.5, 10), box(2, 7, 7.75, 5.5, 7.5, 8.25),
						box(2, 9.5, 7.75, 5.5, 10, 8.25), box(5.5, 6, 5.5, 10.5, 11, 10.5), box(6, 11, 6, 10, 12, 10), box(7.5, 12, 7.5, 8.5, 13.5, 8.5), box(9.5, 12, 6, 10, 12.5, 6.5), box(6, 12, 6, 6.5, 12.5, 6.5), box(9.5, 12, 9.5, 10, 12.5, 10),
						box(6, 12, 9.5, 6.5, 12.5, 10));
				case EAST -> Shapes.or(box(7, -3.5, 7, 9, 0, 9), box(6.5, -4, 6.5, 9.5, -3.5, 9.5), box(6.25, 0.5, 6.25, 9.75, 1.5, 9.75), box(6.5, -1, 6.5, 9.5, 0, 9.5), box(6, 0, 6, 10, 0.5, 10), box(6.5, 1, 6.5, 9.5, 5.5, 9.5),
						box(6, 5.5, 6, 10, 6, 10), box(5, 8.5, 0, 11, 9, 11), box(5.5, 6, 0.5, 10.5, 11, 2), box(6.5, 6, -1, 9.5, 8.5, 0.5), box(7, 9.5, 0, 9, 10.5, 0.5), box(9.5, 9.5, 0, 10, 10, 0.5), box(9.5, 8.25, -0.5, 10.5, 9.25, 0.5),
						box(5.5, 8.25, -0.5, 6.5, 9.25, 0.5), box(6, 9.5, 0, 6.5, 10, 0.5), box(6, 9.5, 2, 7, 10.5, 5.5), box(9, 9.5, 2, 10, 10.5, 5.5), box(9, 6.5, 2, 10, 7.5, 5.5), box(6, 6.5, 2, 7, 7.5, 5.5), box(7.75, 7, 2, 8.25, 7.5, 5.5),
						box(7.75, 9.5, 2, 8.25, 10, 5.5), box(5.5, 6, 5.5, 10.5, 11, 10.5), box(6, 11, 6, 10, 12, 10), box(7.5, 12, 7.5, 8.5, 13.5, 8.5), box(9.5, 12, 9.5, 10, 12.5, 10), box(9.5, 12, 6, 10, 12.5, 6.5), box(6, 12, 9.5, 6.5, 12.5, 10),
						box(6, 12, 6, 6.5, 12.5, 6.5));
				case WEST -> Shapes.or(box(7, -3.5, 7, 9, 0, 9), box(6.5, -4, 6.5, 9.5, -3.5, 9.5), box(6.25, 0.5, 6.25, 9.75, 1.5, 9.75), box(6.5, -1, 6.5, 9.5, 0, 9.5), box(6, 0, 6, 10, 0.5, 10), box(6.5, 1, 6.5, 9.5, 5.5, 9.5),
						box(6, 5.5, 6, 10, 6, 10), box(5, 8.5, 5, 11, 9, 16), box(5.5, 6, 14, 10.5, 11, 15.5), box(6.5, 6, 15.5, 9.5, 8.5, 17), box(7, 9.5, 15.5, 9, 10.5, 16), box(6, 9.5, 15.5, 6.5, 10, 16), box(5.5, 8.25, 15.5, 6.5, 9.25, 16.5),
						box(9.5, 8.25, 15.5, 10.5, 9.25, 16.5), box(9.5, 9.5, 15.5, 10, 10, 16), box(9, 9.5, 10.5, 10, 10.5, 14), box(6, 9.5, 10.5, 7, 10.5, 14), box(6, 6.5, 10.5, 7, 7.5, 14), box(9, 6.5, 10.5, 10, 7.5, 14),
						box(7.75, 7, 10.5, 8.25, 7.5, 14), box(7.75, 9.5, 10.5, 8.25, 10, 14), box(5.5, 6, 5.5, 10.5, 11, 10.5), box(6, 11, 6, 10, 12, 10), box(7.5, 12, 7.5, 8.5, 13.5, 8.5), box(6, 12, 6, 6.5, 12.5, 6.5),
						box(6, 12, 9.5, 6.5, 12.5, 10), box(9.5, 12, 6, 10, 12.5, 6.5), box(9.5, 12, 9.5, 10, 12.5, 10));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.get(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		CryocoolerOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CryocoolerBlockEntity(pos, state);
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
			if (blockEntity instanceof CryocoolerBlockEntity be) {
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
		if (tileentity instanceof CryocoolerBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}