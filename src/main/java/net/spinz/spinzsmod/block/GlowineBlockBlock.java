package net.spinz.spinzsmod.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class GlowineBlockBlock extends Block {
	public GlowineBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(0.2f).lightLevel(s -> 15).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).instrument(NoteBlockInstrument.PLING));
	}
}