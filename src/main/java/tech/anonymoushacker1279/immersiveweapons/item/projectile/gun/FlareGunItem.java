package tech.anonymoushacker1279.immersiveweapons.item.projectile.gun;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;

import java.util.function.Predicate;

public class FlareGunItem extends SimplePistolItem {

	/**
	 * Constructor for FlareGunItem.
	 *
	 * @param properties the <code>Properties</code> for the item
	 */
	public FlareGunItem(Properties properties) {
		super(properties);
	}

	/**
	 * Get the default ammunition.
	 *
	 * @return Item
	 */
	@Override
	public Item defaultAmmo() {
		return DeferredRegistryHandler.FLARE.get();
	}

	/**
	 * Get ammo predicates.
	 *
	 * @return Predicate extending ItemStack
	 */
	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return FLARES;
	}

	/**
	 * Get the fire sound.
	 *
	 * @return SoundEvent
	 */
	@Override
	public SoundEvent getFireSound() {
		return DeferredRegistryHandler.FLARE_GUN_FIRE.get();
	}
}