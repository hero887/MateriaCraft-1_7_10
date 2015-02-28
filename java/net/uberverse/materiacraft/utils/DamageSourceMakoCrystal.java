package net.uberverse.materiacraft.utils;

import net.minecraft.util.DamageSource;

public class DamageSourceMakoCrystal extends DamageSource {

	public DamageSourceMakoCrystal() {
		super("MakoCrystal");
	}

	public boolean isDifficultyScaled() {
		return true;
	}

	public boolean isDamageAbsolute() {
		return true;
	}

}
