package tech.anonymoushacker1279.immersiveweapons.item.tool.molten;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

public class MoltenPickaxe extends PickaxeItem {

	/**
	 * Constructor for MoltenPickaxe.
	 *
	 * @param tier           the <code>Tier</code>
	 * @param attackDamageIn attack damage
	 * @param attackSpeedIn  attack speed
	 * @param properties     the <code>Properties</code> for the item
	 */
	public MoltenPickaxe(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
		super(tier, attackDamageIn, attackSpeedIn, properties);
	}

	/**
	 * Runs when an entity is hit.
	 *
	 * @param itemStack the <code>ItemStack</code> instance
	 * @param target    the <code>LivingEntity</code> attacking
	 * @param attacker  the <code>LivingEntity</code> being hit
	 * @return boolean
	 */
	@Override
	public boolean hurtEnemy(@NotNull ItemStack itemStack, LivingEntity target, @NotNull LivingEntity attacker) {
		target.setSecondsOnFire(10);
		return super.hurtEnemy(itemStack, target, attacker);
	}
}