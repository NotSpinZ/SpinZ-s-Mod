package net.spinz.spinzsmod.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class SaltBlockBlock extends Block {
	public SaltBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.CALCITE).strength(2f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}