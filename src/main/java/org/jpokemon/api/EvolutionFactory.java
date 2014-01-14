package org.jpokemon.api;

/**
 * Defines an algorithm for producing an {@link Evolution}. Provides a
 * {@link Manager} reference for construction libraries to target. It is
 * recommended for library code, and therefore, library consumers. A simple
 * project may choose to completely ignore this class.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public abstract class EvolutionFactory {
	/** Indicates the manager used to register evolution factories. May be 'null' */
	public static Manager<EvolutionFactory> manager;

	/**
	 * Gets the unique name of this factory. Effectively, the class of evolution
	 * that this factory produces
	 */
	public abstract String getName();

	/** Builds an evolution, given the options provided */
	public abstract Evolution buildEvolution(String options);
}