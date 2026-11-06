package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModItems;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class GetRecipeRemainderProcedure {
	public static ItemStack execute(ItemStack item) {
		if (item.getItem() == SpinzsModModItems.SALT_WATER_BOTTLE.get() || item.is(ItemTags.create(ResourceLocation.parse("minecraft:brewing_potion_ingredients"))) || item.getItem() == Items.OMINOUS_BOTTLE || item.getItem() == Items.EXPERIENCE_BOTTLE
				|| item.getItem() == Items.HONEY_BOTTLE || item.getItem() == Items.DRAGON_BREATH) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else if (item.getItem() == Items.WATER_BUCKET || item.getItem() == Items.LAVA_BUCKET || item.getItem() == Items.TADPOLE_BUCKET || item.getItem() == Items.POWDER_SNOW_BUCKET || item.getItem() == Items.MILK_BUCKET
				|| item.getItem() == Items.PUFFERFISH_BUCKET || item.getItem() == Items.AXOLOTL_BUCKET || item.getItem() == Items.SALMON_BUCKET || item.getItem() == Items.COD_BUCKET || item.getItem() == Items.TROPICAL_FISH_BUCKET
				|| item.getItem() == Items.AXOLOTL_BUCKET) {
			return new ItemStack(Items.BUCKET);
		} else if (item.getItem() == SpinzsModModItems.CO_2_VILE.get() || item.getItem() == SpinzsModModItems.HYDROGEN_VILE.get() || item.getItem() == SpinzsModModItems.FORMIC_ACID_VILE.get()
				|| item.is(ItemTags.create(ResourceLocation.parse("spinzs_mod:matter_viles")))) {
			return new ItemStack(SpinzsModModItems.VILE.get());
		}
		return new ItemStack(Blocks.AIR);
	}
}