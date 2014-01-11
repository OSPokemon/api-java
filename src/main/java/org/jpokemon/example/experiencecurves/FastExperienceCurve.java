package org.jpokemon.example.experiencecurves;

import org.jpokemon.api.ExperienceCurve;

/**
 * Provides a possible ExperienceCurve which has fast growth.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class FastExperienceCurve extends ExperienceCurve {
	@Override
	public int experienceRequiredForLevel(int level) {
		return 4 * level * level * level / 5;
	}
}