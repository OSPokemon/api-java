package org.ospokemon.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;

/**
 * Provides a simple implementation of the {@link Manager} interface, which
 * refers to another concrete implementation of Manager for the specified type.
 * Provides simple caching, backed by a {@link HashMap}.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class HashedCachingManager<T> implements Manager<T> {
	/** Indicates the referenced manager */
	protected Manager<T> manager;

	/** The HashMap used to cache objects */
	protected Map<String, T> cache = new HashMap<String, T>();

	public HashedCachingManager(Manager<T> manager) {
		this.manager = manager;
	}

	@Override
	public T create() {
		return manager.create();
	}

	@Override
	public boolean isRegistered(String name) {
		if (cache.containsKey(name)) {
			return true;
		}

		return manager.isRegistered(name);
	}

	@Override
	public List<T> getAll() throws JPokemonException {
		List<T> allObjects = manager.getAll();
		cache.clear();

		try {
			for (T object : allObjects) {
				String name = (String) object.getClass().getMethod("getName").invoke(object);
				cache.put(name, object);
			}
		} catch (Exception e) {
		}

		return allObjects;
	}

	@Override
	public T get(String name) throws JPokemonException {
		if (cache.containsKey(name)) {
			return cache.get(name);
		}

		T object = manager.get(name);
		cache.put(name, object);
		return object;
	}

	@Override
	public void register(T object) throws JPokemonException {
		manager.register(object);

		try {
			String name = (String) object.getClass().getMethod("getName").invoke(object);
			cache.put(name, object);
		} catch (Exception e) {
		}
	}

	@Override
	public void update(T object) throws JPokemonException {
		manager.update(object);

		try {
			String name = (String) object.getClass().getMethod("getName").invoke(object);
			cache.put(name, object);
		} catch (Exception e) {
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		manager.unregister(name);
		cache.remove(name);
	}
}
