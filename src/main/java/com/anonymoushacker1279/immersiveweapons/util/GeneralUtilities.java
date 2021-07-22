package com.anonymoushacker1279.immersiveweapons.util;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.item.DyeColor;

public class GeneralUtilities {

	/**
	 * Get a random number between a minimum and maximum.
	 * @param min minimum number
	 * @param max maximum number
	 * @return float
	 */
	public static float getRandomNumber(float min, float max) {
		return (float) ((Math.random() * (max - min)) + min);
	}

	/**
	 * Get a random number between a minimum and maximum.
	 * @param min minimum number
	 * @param max maximum number
	 * @return double
	 */
	public static double getRandomNumber(double min, double max) {
		return (Math.random() * (max - min)) + min;
	}

	/**
	 * Get a random number between a minimum and maximum.
	 * @param min minimum number
	 * @param max maximum number
	 * @return int
	 */
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	/**
	 * Create a stained glass block from a color.
	 * @param color the <code>DyeColor</code>
	 * @param properties the <code>Properties</code> for the block
	 * @return StainedGlassBlock
	 */
	public static StainedGlassBlock createStainedGlassFromColor(DyeColor color, Properties properties) {
		return new StainedGlassBlock(color, properties);
	}
}