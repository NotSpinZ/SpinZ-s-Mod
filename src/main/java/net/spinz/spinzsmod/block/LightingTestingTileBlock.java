package net.spinz.spinzsmod.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class LightingTestingTileBlock extends Block {
	public LightingTestingTileBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 6f).lightLevel(s -> 14).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).instrument(NoteBlockInstrument.BASEDRUM));
	}
}