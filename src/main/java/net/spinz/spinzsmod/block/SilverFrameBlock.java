package net.spinz.spinzsmod.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class SilverFrameBlock extends Block {
	private static final VoxelShape SHAPE = Shapes.or(box(0, 0, 0, 2, 2, 2), box(14, 0, 0, 16, 2, 2), box(0, 0, 14, 2, 2, 16), box(14, 0, 14, 16, 2, 16), box(14, 14, 14, 16, 16, 16), box(0, 14, 14, 2, 16, 16), box(0, 14, 0, 2, 16, 2),
			box(14, 14, 0, 16, 16, 2), box(14.5, 2, 14.5, 15.5, 14, 15.5), box(2, 14.5, 14.5, 14, 15.5, 15.5), box(2, 14.5, 0.5, 14, 15.5, 1.5), box(2, 0.5, 14.5, 14, 1.5, 15.5), box(14.5, 0.5, 2, 15.5, 1.5, 14), box(0.5, 0.5, 2, 1.5, 1.5, 14),
			box(14.5, 14.5, 2, 15.5, 15.5, 14), box(0.5, 14.5, 2, 1.5, 15.5, 14), box(2, 0.5, 0.5, 14, 1.5, 1.5), box(14.5, 2, 0.5, 15.5, 14, 1.5), box(0.5, 2, 14.5, 1.5, 14, 15.5), box(0.5, 2, 0.5, 1.5, 14, 1.5));

	public SilverFrameBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}
}