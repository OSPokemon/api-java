package org.jpokemon.api.items.attributes;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.exceptions.JPokemonError;
import org.jpokemon.api.items.Item;
import org.jpokemon.api.items.ItemAttribute;

/**
 * Provides a possible attribute describing qualities of berry items, including
 * flavor, size, firmness, smoothness, and index number.
 * 
 * @author atheriel@gmail.com
 * @author Zach Taylor
 * 
 * @since 0.1
 */
public class BerryAttribute implements ItemAttribute {
	/** Indicates the size of this berry */
	private float size;

	/** Indicates the smoothness of this berry */
	private int smoothness;

	/** Indicates the firmness of this berry */
	private String firmness;

	/** Indicates the flavors of this berry */
	private Map<String, Integer> flavors = new HashMap<String, Integer>(6);

	/** Provides the default constructor. */
	public BerryAttribute() {
	}

	/** Gets the size of this berry. */
	public float getSize() {
		return size;
	}

	/** Sets the size of this berry. */
	public BerryAttribute setSize(float size) {
		this.size = size;
		return this;
	}

	/** Gets the smoothness of this berry. */
	public int getSmoothness() {
		return smoothness;
	}

	/** Sets the smoothness of this berry. */
	public void setSmoothness(int smoothness) {
		this.smoothness = smoothness;
	}

	/** Gets the firmness of this berry. */
	public String getFirmness() {
		return this.firmness;
	}

	/** Sets the firmness of this berry. */
	public BerryAttribute setFirmness(String firmness) {
		this.firmness = firmness;
		return this;
	}

	/** Gets the amount of flavor for the given taste. */
	public int getFlavor(String taste) {
		return flavors.get(taste);
	}

	/** Sets the amount of bitter flavor for this berry. */
	public BerryAttribute setFlavor(String taste, int flavor) {
		flavors.put(taste, flavor);
		return this;
	}

	@Override
	public void applyAttribute(Item item) {
		if (item.hasAttribute("firmness")) {
			throw new JPokemonError("Redefinition of property (firmness) with item : " + item.toString());
		}
		item.addAttribute("firmness", firmness + "");

		if (item.hasAttribute("size")) {
			throw new JPokemonError("Redefinition of property (size) with item : " + item.toString());
		}
		item.addAttribute("size", size + "");

		if (item.hasAttribute("smoothness")) {
			throw new JPokemonError("Redefinition of property (smoothness) with item : " + item.toString());
		}
		item.addAttribute("smoothness", smoothness + "");

		for (Map.Entry<String, Integer> flavor : flavors.entrySet()) {
			if (item.hasAttribute(flavor.getKey())) {
				throw new JPokemonError("Redefinition of property (" + flavor.getKey() + ") with item : " + item.toString());
			}
			item.addAttribute("flavor." + flavor.getKey(), flavor.getValue() + "");
		}
	}

	/**
	 * Reads all necessary attributes from the specified Item to get an
	 * ItemAttribute instance
	 * 
	 * @param item Item to read attributes from
	 * @return A BerryAttribute built from attributes of the Item
	 */
	public static BerryAttribute getFromItem(Item item) {
		BerryAttribute ba = new BerryAttribute();

		if (!item.hasAttribute("firmness")) {
			throw new JPokemonError("Missing property (firmness) from item : " + item.toString());
		}
		ba.setFirmness(item.getAttribute("firmness"));

		if (!item.hasAttribute("size")) {
			throw new JPokemonError("Missing property (size) from item : " + item.toString());
		}
		ba.setSize(Integer.parseInt(item.getAttribute("size")));

		if (!item.hasAttribute("smoothness")) {
			throw new JPokemonError("Missing property (smoothness) from item : " + item.toString());
		}
		ba.setSmoothness(Integer.parseInt(item.getAttribute("smoothness")));

		for (String attributeKey : item.getAllAttributes()) {
			if (!attributeKey.startsWith("flavor.")) {
				continue;
			}

			ba.setFlavor(attributeKey.substring("flavor.".length()), Integer.parseInt(item.getAttribute(attributeKey)));
		}

		return ba;
	}
}
