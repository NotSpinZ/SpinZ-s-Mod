package net.spinz.spinzsmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ShadiumFragmentItem extends Item {
	public ShadiumFragmentItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}
}