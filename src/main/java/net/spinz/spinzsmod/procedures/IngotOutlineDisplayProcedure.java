package net.spinz.spinzsmod.procedures;

import net.spinz.spinzsmod.init.SpinzsModModMenus;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class IngotOutlineDisplayProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return getAmountInGUISlot(entity, 2) <= 0;
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof SpinzsModModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}