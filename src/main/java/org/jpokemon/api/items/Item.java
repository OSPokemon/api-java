package org.jpokemon.api.items;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.jpokemon.api.Manager;

/**
 * Defines a basic item. This class provides the most basic attributes that all
 * items in a Pokémon game are likely to share. To create your own items, you
 * have two options: (1) extend this class, or (2) make use of the ``attribute``
 * system. See the wiki for details.
 * 
 * <p>
 * 
 * Note that the ``attributes`` {@link HashMap} will not initialize until an
 * attribute is added. Thus, if a traditional inheritance scheme is preferable
 * for your project, this class can be extended and this functionality ignored
 * without memory inefficiency.
 * 
 * <p>
 * 
 * The same goes for the management features; if the ``manager`` field remains
 * ``null``, its functionality is ignored, and only a very weak protest is made
 * by the constructor in the form of an unchecked error.
 * 
 * @author atheriel@gmail.com
 * 
 * @since 0.1
 */
public class Item {
	/** Indicates the manager being used to register items. May be ``null``. */
	public static Manager<Item> manager = null;

	/** Indicates the non-basic attributes of the item. */
	protected HashMap<String, String> attributes = new HashMap<String, String>();

	/** Indicates the name of the item (as it would appear in the bag or a shop). */
	protected String name = "????";

	/**
	 * Indicates the description of this item (as it would appear in the bag or a
	 * shop).
	 */
	protected String description = "";

	/** Indicates whether this item is sellable to a vendor. Defaults to `false`. */
	protected boolean sellable = false;

	/** Indicates the sale price for this item. Defaults to `0`. */
	protected int salePrice = 0;

	/**
	 * Indicates whether this item is usable outside of battle. Defaults to
	 * `false`.
	 */
	protected boolean usableOutsideBattle = false;

	/** Indicates whether this item is during battle. Defaults to `false`. */
	protected boolean usableDuringBattle = false;

	/**
	 * Indicates whether this item is consumed upon use, including use while
	 * holding. Defaults to `false`.
	 */
	protected boolean consumable = false;

	/** Indicates whether this item is holdable by a Pokémon. Defaults to `false`. */
	protected boolean holdable = false;

	/**
	 * Indicates whether this item has a passive effect when held by a Pokémon.
	 * Defaults to `false`.
	 */
	protected boolean passiveHoldEffect = false;

	/**
	 * Indicates whether this item has an active effect when held by a Pokémon.
	 * Defaults to `false`.
	 */
	protected boolean activeHoldEffect = false;

	/** Provides the default constructor. */
	public Item() {
	}

	/** Gets the name of this item. */
	public String getName() {
		return name;
	}

	/** Sets the name of this item. */
	public Item setName(String name) {
		this.name = name;

		if (manager != null) {
			manager.register(this);
		}

		return this;
	}

	/** Gets the description of this item. */
	public String getDescription() {
		return description;
	}

	/** Sets the description of this item. */
	public Item setDescription(String description) {
		this.description = description;
		return this;
	}

	/** Checks whether this item is sellable to a vendor. */
	public boolean isSellable() {
		return sellable;
	}

	/** Sets whether this item is sellable to a vendor. */
	public Item setSellable(boolean sellable) {
		this.sellable = sellable;
		return this;
	}

	/** Gets the sale price for this item. */
	public int getSalePrice() {
		return salePrice;
	}

	/** Sets the sale price for this item. */
	public Item setSalePrice(int salePrice) {
		this.salePrice = salePrice;
		return this;
	}

	/** Checks whether this item is usable outside of battle. */
	public boolean isUsableOutsideBattle() {
		return usableOutsideBattle;
	}

	/** Sets whether this item is usable outside of battle. */
	public Item setUsableOutsideBattle(boolean usableOutsideBattle) {
		this.usableOutsideBattle = usableOutsideBattle;
		return this;
	}

	/** Checks whether this item is usable during battle. */
	public boolean isUsableDuringBattle() {
		return usableDuringBattle;
	}

	/** Sets whether this item is usable during battle. */
	public Item setUsableDuringBattle(boolean usableDuringBattle) {
		this.usableDuringBattle = usableDuringBattle;
		return this;
	}

	/** Checks whether this item is consumed upon use. */
	public boolean isConsumable() {
		return consumable;
	}

	/** Sets whether this item is consumed upon use. */
	public Item setConsumable(boolean consumable) {
		this.consumable = consumable;
		return this;
	}

	/** Checks whether this item can be held by a Pokémon. */
	public boolean isHoldable() {
		return holdable;
	}

	/** Sets whether this item can be held by a Pokémon. */
	public Item setHoldable(boolean holdable) {
		this.holdable = holdable;
		return this;
	}

	/** Checks whether this item has a passive effect when held by a Pokémon. */
	public boolean hasPassiveHoldEffect() {
		return passiveHoldEffect;
	}

	/** Sets whether this item has a passive effect when held by a Pokémon. */
	public Item setPassiveHoldEffect(boolean passiveHoldEffect) {
		this.passiveHoldEffect = passiveHoldEffect;
		return this;
	}

	/** Checks whether this item has an active effect when held by a Pokémon. */
	public boolean hasActiveHoldEffect() {
		return activeHoldEffect;
	}

	/** Sets whether this item has an active effect when held by a Pokémon. */
	public Item setActiveHoldEffect(boolean activeHoldEffect) {
		this.activeHoldEffect = activeHoldEffect;
		return this;
	}

	/** Checks if the item has an attribute of the given name. */
	public boolean hasAttribute(String key) {
		if (key == null) {
			return false;
		}

		return attributes.containsKey(key);
	}

	/** Adds an attribute to the item. It must have a distinct name. */
	public Item addAttribute(String key, String value) {
		if (!attributes.containsKey(key)) {
			attributes.put(key, value);
		}

		return this;
	}

	/**
	 * Gets the attribute of this item for the given key
	 * 
	 * @param key The key of the attribute requested
	 * 
	 * @return The item's attribute under this name, or `null` if it does not
	 *         possess one
	 */
	public String getAttribute(String key) {
		if (!attributes.containsKey(key)) {
			return null;
		}

		return attributes.get(key);
	}

	/**
	 * Gets all of the attribute keys of this Item as an UnmodifiableCollection
	 * 
	 * @return A collection of this Item's attribute keys
	 */
	public Collection<String> getAllAttributes() {
		return Collections.unmodifiableCollection(attributes.keySet());
	}

	/** Gets a string representation of this item: its name. */
	@Override
	public String toString() {
		String value = "Item: " + name;
		return value;
	}
}
