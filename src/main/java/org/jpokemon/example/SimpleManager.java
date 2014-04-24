package org.jpokemon.example;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Manager;

/**
 * Provides generic management for any type, 'T'. In order for this class to
 * function properly, 'T' must have a method 'getName,' which returns a unique
 * string that can identify the object of type 'T'.
 * 
 * <p>
 * 
 * Note that this class uses reflection. Pains have been taken to avoid
 * producing errors, but in the case that they appear, they will be silently
 * repressed.
 * 
 * @author atheriel@gmail.com
 * 
 * @since 0.1
 * 
 * @param T The type to be managed
 */
public class SimpleManager<T> implements Manager<T> {
	protected Map<String, T> objectMap = new TreeMap<String, T>();
	protected Class<T> managedClass;

	/**
	 * Constructs a new SimpleManager for the given type, 'T'
	 * 
	 * Due to type erasure, the class of the managed type needs to be passed to
	 * the manager during construction.
	 * 
	 * @param managedClass The class of the managed type, e.g. 'MyObject.class'
	 * 
	 * @throws JPokemonException In the case that T does not have a 'getName'
	 *           method
	 */
	public SimpleManager(Class<T> managedClass) throws JPokemonException {
		this.managedClass = managedClass;

		try {
			managedClass.getMethod("getName");
		} catch (Exception e) {
			throw new JPokemonException("No method \"getName\" available for class: " + managedClass.toString());
		}
	}

	@Override
	public void register(T managed) throws JPokemonException {
		if (managed == null) {
			throw new JPokemonException("Cannot register null object");
		}
		// Get the name via reflection
		String name = null;
		try {
			Method getName = managedClass.getMethod("getName");
			name = (String) getName.invoke(managed);
		} catch (Exception e) {
		}

		// Use the name to register, and check that it does not conflict
		if (name == null) {
			throw new JPokemonException("Cannot register object without a name: " + managed);
		}
		else if (objectMap.containsKey(name) && !managed.equals(objectMap.get(name))) {
			throw new JPokemonException("A object with the same name is already registered: " + name);
		}

		objectMap.put(name, managed);
	}

	@Override
	public boolean isRegistered(String name) {
		if (name == null) {
			return false;
		}

		return objectMap.containsKey(name);
	}

	@Override
	public T getByName(String name) {
		if (!isRegistered(name)) {
			return null;
		}

		return objectMap.get(name);
	}
}
