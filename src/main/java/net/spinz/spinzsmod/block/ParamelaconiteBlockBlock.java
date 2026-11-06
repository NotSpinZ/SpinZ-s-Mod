package net.spinz.spinzsmod.block;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

public class ParamelaconiteBlockBlock extends Block {
	public ParamelaconiteBlockBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.paramelaconite.break")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.paramelaconite.step")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.paramelaconite.place")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.paramelaconite.hit")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("spinzs_mod:blocks.paramelaconite.fall"))))
				.strength(5f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BIT));
	}
}