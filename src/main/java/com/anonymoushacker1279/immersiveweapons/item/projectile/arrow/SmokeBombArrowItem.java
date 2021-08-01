package com.anonymoushacker1279.immersiveweapons.item.projectile.arrow;

import com.anonymoushacker1279.immersiveweapons.entity.projectile.CustomArrowEntity.SmokeBombArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SmokeBombArrowItem extends AbstractArrowItem {

	private final int color;

	/**
	 * Constructor for SmokeBombArrowItem.
	 * @param properties the <code>Properties</code> for the item
	 * @param damageIn the damage to deal on impact
	 */
	public SmokeBombArrowItem(Properties properties, double damageIn, int color) {
		super(properties, damageIn);
		damage = damageIn;
		this.color = color;
	}

	/**
	 * Create an arrow item.
	 * @param worldIn the <code>World</code> the shooter is in
	 * @param stack the <code>ItemStack</code> being shot
	 * @param shooter the <code>LivingEntity</code> shooting the arrow
	 * @return SmokeBombArrowEntity
	 */
	@Override
	public SmokeBombArrowEntity createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
		SmokeBombArrowEntity arrowEntity = new SmokeBombArrowEntity(shooter, worldIn, ref.get());
		arrowEntity.setBaseDamage(damage);
		arrowEntity.pickup = Pickup.DISALLOWED;
		SmokeBombArrowEntity.setColor(color);
		return arrowEntity;
	}
}