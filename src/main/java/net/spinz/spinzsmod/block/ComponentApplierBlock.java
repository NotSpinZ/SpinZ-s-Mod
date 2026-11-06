package net.spinz.spinzsmod.block;

import net.spinz.spinzsmod.world.inventory.ComponentApplierGUIMenu;
import net.spinz.spinzsmod.procedures.ComponentApplierPlayerStartsToDestroyProcedure;
import net.spinz.spinzsmod.procedures.ComponentApplierOnTickUpdateProcedure;
import net.spinz.spinzsmod.procedures.ComponentApplierBlockAddedProcedure;
import net.spinz.spinzsmod.block.entity.ComponentApplierBlockEntity;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

import com.google.common.collect.ImmutableMap;

public class ComponentApplierBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty AUTO_EXPORT = BooleanProperty.create("auto_export");
	public static final EnumProperty<ExportDirProperty> EXPORT_DIR = EnumProperty.create("export_dir", ExportDirProperty.class);
	private final ImmutableMap<BlockState, VoxelShape> shapes = this.makeShapes();

	public ComponentApplierBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.IRON_XYLOPHONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AUTO_EXPORT, false).setValue(EXPORT_DIR, ExportDirProperty.UP));
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(0, 0, 0, 16, 2, 16), box(14, 2, 0.5, 15.5, 12, 2), box(14, 2, 14, 15.5, 12, 15.5), box(0.5, 2, 0.5, 2, 12, 2), box(0.5, 2, 14, 2, 12, 15.5), box(1.5, 2, 1.5, 14.5, 5, 14.5), box(1.5, 10, 1.5, 14.5, 12, 14.5),
						box(0, 12, 0, 16, 13.5, 16), box(2, 13.5, 2, 14, 17.5, 14), box(6, 13.5, 2.5, 10, 18, 6), box(6, 13.5, 10, 10, 18, 13.5), box(2.5, 13.5, 6, 13.5, 18, 10), box(1, 5.5, 1, 15, 6.5, 15), box(2, 5, 2, 14, 5.5, 14));
				case NORTH ->
					Shapes.or(box(0, 0, 0, 16, 2, 16), box(0.5, 2, 14, 2, 12, 15.5), box(0.5, 2, 0.5, 2, 12, 2), box(14, 2, 14, 15.5, 12, 15.5), box(14, 2, 0.5, 15.5, 12, 2), box(1.5, 2, 1.5, 14.5, 5, 14.5), box(1.5, 10, 1.5, 14.5, 12, 14.5),
							box(0, 12, 0, 16, 13.5, 16), box(2, 13.5, 2, 14, 17.5, 14), box(6, 13.5, 10, 10, 18, 13.5), box(6, 13.5, 2.5, 10, 18, 6), box(2.5, 13.5, 6, 13.5, 18, 10), box(1, 5.5, 1, 15, 6.5, 15), box(2, 5, 2, 14, 5.5, 14));
				case EAST ->
					Shapes.or(box(0, 0, 0, 16, 2, 16), box(0.5, 2, 0.5, 2, 12, 2), box(14, 2, 0.5, 15.5, 12, 2), box(0.5, 2, 14, 2, 12, 15.5), box(14, 2, 14, 15.5, 12, 15.5), box(1.5, 2, 1.5, 14.5, 5, 14.5), box(1.5, 10, 1.5, 14.5, 12, 14.5),
							box(0, 12, 0, 16, 13.5, 16), box(2, 13.5, 2, 14, 17.5, 14), box(2.5, 13.5, 6, 6, 18, 10), box(10, 13.5, 6, 13.5, 18, 10), box(6, 13.5, 2.5, 10, 18, 13.5), box(1, 5.5, 1, 15, 6.5, 15), box(2, 5, 2, 14, 5.5, 14));
				case WEST ->
					Shapes.or(box(0, 0, 0, 16, 2, 16), box(14, 2, 14, 15.5, 12, 15.5), box(0.5, 2, 14, 2, 12, 15.5), box(14, 2, 0.5, 15.5, 12, 2), box(0.5, 2, 0.5, 2, 12, 2), box(1.5, 2, 1.5, 14.5, 5, 14.5), box(1.5, 10, 1.5, 14.5, 12, 14.5),
							box(0, 12, 0, 16, 13.5, 16), box(2, 13.5, 2, 14, 17.5, 14), box(10, 13.5, 6, 13.5, 18, 10), box(2.5, 13.5, 6, 6, 18, 10), box(6, 13.5, 2.5, 10, 18, 13.5), box(1, 5.5, 1, 15, 6.5, 15), box(2, 5, 2, 14, 5.5, 14));
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
		builder.add(FACING, AUTO_EXPORT, EXPORT_DIR);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(AUTO_EXPORT, false).setValue(EXPORT_DIR, ExportDirProperty.UP);
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
		ComponentApplierBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		ComponentApplierOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void attack(BlockState blockstate, Level world, BlockPos pos, Player entity) {
		super.attack(blockstate, world, pos, entity);
		ComponentApplierPlayerStartsToDestroyProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Component Applier");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new ComponentApplierGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ComponentApplierBlockEntity(pos, state);
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
			if (blockEntity instanceof ComponentApplierBlockEntity be) {
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
		if (tileentity instanceof ComponentApplierBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}

	public enum ExportDirProperty implements StringRepresentable {
		NORTH("north"), SOUTH("south"), EAST("east"), WEST("west"), UP("up"), DOWN("down");

		private final String name;

		private ExportDirProperty(String name) {
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