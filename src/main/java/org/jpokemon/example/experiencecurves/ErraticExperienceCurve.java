package org.jpokemon.example.experiencecurves;

import org.jpokemon.api.ExperienceCurve;

/**
 * Provides a possible ExperienceCurve which has erratic growth.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ErraticExperienceCurve extends ExperienceCurve {
	/** Indicates the name of this experience curve */
	public static final String EXPERIENCE_CURVE_NAME = "erratic";

	@Override
	public String getName() {
		return EXPERIENCE_CURVE_NAME;
	}

	@Override
	public int experienceRequiredForLevel(int level) {
		if (level <= 50) {
			return ((level * level * level) * (100 - level)) / 50;
		}
		else if (level <= 68) {
			return ((level * level * level) * (150 - level)) / 100;
		}
		else if (level <= 98) {
			return (int) ((level * level * level) * Math.floor((1911 - 10 * level) / 3)) / 500;
		}
		else {
			return ((level * level * level) * (160 - level)) / 100;
		}
	}
}