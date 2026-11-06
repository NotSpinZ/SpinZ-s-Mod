package net.spinz.spinzsmod.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class RawTitaniumBlockBlock extends Block {
	public RawTitaniumBlockBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}