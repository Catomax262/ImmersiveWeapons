package com.anonymoushacker1279.immersiveweapons.item.pike;

import com.anonymoushacker1279.immersiveweapons.init.PostSetupHandler;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class GoldenPikeItem extends PikeItem {

	public static Multimap<Attribute, AttributeModifier> pikeAttributes;

	/**
	 * Constructor for GoldPikeItem.
	 *
	 * @param properties    the <code>Properties</code> for the item
	 * @param damageIn      the damage
	 * @param attackSpeedIn the attack speed
	 */
	public GoldenPikeItem(Properties properties, double damageIn, double attackSpeedIn) {
		super(properties);
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", damageIn, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeedIn, AttributeModifier.Operation.ADDITION));
		pikeAttributes = builder.build();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> returnValue;
		if (PostSetupHandler.hasCompletedClientSetup) {
			returnValue = equipmentSlot == EquipmentSlot.MAINHAND ? GoldenPikeItem.pikeAttributes : super.getAttributeModifiers(equipmentSlot, stack);
		} else {
			returnValue = GoldenPikeItem.pikeAttributes;
		}
		return returnValue;
	}

	@Override
	Ingredient getRepairMaterial() {
		return Ingredient.of(Items.GOLD_INGOT);
	}
}