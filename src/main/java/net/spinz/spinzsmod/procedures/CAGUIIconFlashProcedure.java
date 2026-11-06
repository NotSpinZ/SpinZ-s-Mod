package net.spinz.spinzsmod.procedures;

import net.minecraft.world.level.LevelAccessor;

public class CAGUIIconFlashProcedure {
	public static double execute(LevelAccessor world) {
		return (world.dayTime() / 20) % 3 + 1;
	}
}