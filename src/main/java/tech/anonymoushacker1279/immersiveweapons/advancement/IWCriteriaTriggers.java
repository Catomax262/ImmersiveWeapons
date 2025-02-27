package tech.anonymoushacker1279.immersiveweapons.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;

public class IWCriteriaTriggers {

	public static EntityDiscoveredTrigger ENTITY_DISCOVERED_TRIGGER = null;
	public static WarriorStatueActivatedTrigger WARRIOR_STATUE_ACTIVATED_TRIGGER = null;

	public static void init() {
		ImmersiveWeapons.LOGGER.info("Initializing advancement criteria triggers");

		ENTITY_DISCOVERED_TRIGGER = CriteriaTriggers.register(new EntityDiscoveredTrigger());
		WARRIOR_STATUE_ACTIVATED_TRIGGER = CriteriaTriggers.register(new WarriorStatueActivatedTrigger());
	}
}