package com.anonymoushacker1279.immersiveweapons.data.tags.groups.immersiveweapons;

import com.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ImmersiveWeaponsWorldGenTagGroups {

	// Biome Tags
	public static final TagKey<Biome> HAS_ABANDONED_FACTORY = createStructureTag("abandoned_factory");
	public static final TagKey<Biome> HAS_PITFALL_TRAP = createStructureTag("pitfall_trap");
	public static final TagKey<Biome> HAS_BEAR_TRAP = createStructureTag("bear_trap");
	public static final TagKey<Biome> HAS_LANDMINE_TRAP = createStructureTag("landmine_trap");
	public static final TagKey<Biome> HAS_UNDERGROUND_BUNKER = createStructureTag("underground_bunker");
	public static final TagKey<Biome> HAS_CLOUD_ISLAND = createStructureTag("cloud_island");
	public static final TagKey<Biome> HAS_CAMPSITE = createStructureTag("campsite");
	public static final TagKey<Biome> HAS_WATER_TOWER = createStructureTag("water_tower");
	public static final TagKey<Biome> HAS_HANS_HUT = createStructureTag("hans_hut");
	public static final TagKey<Biome> HAS_DESTROYED_HOUSE = createStructureTag("destroyed_house");
	public static final TagKey<Biome> HAS_BATTLEFIELD_CAMP = createStructureTag("battlefield_camp");
	public static final TagKey<Biome> HAS_GRAVEYARD = createStructureTag("graveyard");
	public static final TagKey<Biome> HAS_BATTLEFIELD_TOWN = createStructureTag("battlefield_town");

	// Structure Tags

	/**
	 * Helper method for creating a biome tag for containing structures.
	 *
	 * @param tag a string to be used for the tag
	 */
	private static TagKey<Biome> createStructureTag(String tag) {
		return BiomeTags.create(ImmersiveWeapons.MOD_ID + ":has_structure/" + tag);
	}
}