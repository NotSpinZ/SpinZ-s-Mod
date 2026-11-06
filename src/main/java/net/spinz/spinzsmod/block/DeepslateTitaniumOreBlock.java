package net.spinz.spinzsmod.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class DeepslateTitaniumOreBlock extends Block {
	public DeepslateTitaniumOreBlock() {
		super(BlockBehaviour.Properties.of().strength(4.5f, 3f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}