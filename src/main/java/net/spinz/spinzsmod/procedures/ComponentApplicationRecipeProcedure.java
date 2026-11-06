package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class ComponentApplicationRecipeProcedure {
	public static ItemStack execute(ItemStack item0, ItemStack item1, ItemStack item2) {
		if ((item1.getItem() == Items.GOLD_INGOT && item2.getItem() == Items.AMETHYST_SHARD || item2.getItem() == Items.GOLD_INGOT && item1.getItem() == Items.AMETHYST_SHARD) && item0.getItem() == SpinzsModModItems.SIMPLE_ELECTRIUM_BOARD.get()) {
			return new ItemStack(SpinzsModModItems.COMPLEX_ELECTRIUM_BOARD.get());
		} else if ((item1.getItem() == SpinzsModModItems.ENDERRUBBER_MEMBRANE.get() && item2.getItem() == SpinzsModModItems.PURE_ELECTRIUM.get()
				|| item2.getItem() == SpinzsModModItems.ENDERRUBBER_MEMBRANE.get() && item1.getItem() == SpinzsModModItems.PURE_ELECTRIUM.get()) && item0.getItem() == SpinzsModModItems.SIMPLE_ELECTRIUM_WIRE.get()) {
			return new ItemStack(SpinzsModModItems.COMPLEX_ELECTRIUM_WIRE.get());
		} else if ((item1.getItem() == SpinzsModModItems.CONDUCTIVE_COIL.get() && item2.getItem() == Blocks.AIR.asItem() || item2.getItem() == SpinzsModModItems.CONDUCTIVE_COIL.get() && item1.getItem() == Blocks.AIR.asItem())
				&& item0.getItem() == SpinzsModModItems.REFINED_SHADIUM_INGOT.get()) {
			return new ItemStack(SpinzsModModItems.ELECTROMAGNETIC_COIL.get());
		} else if ((item1.getItem() == Items.STRING && item2.getItem() == Blocks.AIR.asItem() || item2.getItem() == Items.STRING && item1.getItem() == Blocks.AIR.asItem()) && item0.getItem() == Items.PAPER) {
			return new ItemStack(Items.NAME_TAG);
		} else if (item1.getItem() == SpinzsModModItems.ELECTRIUM_SHARD.get() && item2.getItem() == SpinzsModModItems.ELECTRIUM_SHARD.get() && item0.getItem() == Items.IRON_INGOT) {
			return new ItemStack(SpinzsModModItems.ELECTRIUM_INGOT.get());
		} else if ((item1.getItem() == Items.DIAMOND && item2.getItem() == Blocks.AIR.asItem() || item2.getItem() == Items.DIAMOND && item1.getItem() == Blocks.AIR.asItem()) && item0.getItem() == SpinzsModModItems.GOLD_GEAR.get()) {
			return new ItemStack(SpinzsModModItems.DIAMOND_GEAR.get());
		} else if ((item1.getItem() == SpinzsModModItems.ELECTRIUM_SHARD.get() && item2.getItem() == Items.COPPER_INGOT || item2.getItem() == SpinzsModModItems.ELECTRIUM_SHARD.get() && item1.getItem() == Items.COPPER_INGOT)
				&& item0.getItem() == SpinzsModModItems.SIMPLE_ELECTRIUM_PUMP.get()) {
			return new ItemStack(SpinzsModModItems.COMPLEX_ELECTRIUM_PUMP.get());
		} else if ((item1.getItem() == Items.DIAMOND && item2.getItem() == Blocks.AIR.asItem() || item2.getItem() == Items.DIAMOND && item1.getItem() == Blocks.AIR.asItem()) && item0.getItem() == SpinzsModModItems.GOLD_ROD.get()) {
			return new ItemStack(SpinzsModModItems.DIAMOND_ROD.get());
		} else if (item1.getItem() == SpinzsModModItems.REFRACTING_SHARD.get() && item2.getItem() == SpinzsModModItems.REFRACTING_SHARD.get() && item0.getItem() == Blocks.TINTED_GLASS.asItem()) {
			return new ItemStack(SpinzsModModItems.PRISM.get());
		}
		return new ItemStack(Blocks.AIR);
	}
}