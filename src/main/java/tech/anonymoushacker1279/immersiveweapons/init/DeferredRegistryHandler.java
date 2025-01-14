package tech.anonymoushacker1279.immersiveweapons.init;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.block.*;
import tech.anonymoushacker1279.immersiveweapons.block.barbed_wire.BarbedWireBlock;
import tech.anonymoushacker1279.immersiveweapons.block.barbed_wire.BarbedWireFenceBlock;
import tech.anonymoushacker1279.immersiveweapons.block.core.BasicOrientableBlock;
import tech.anonymoushacker1279.immersiveweapons.block.core.StrippablePillarBlock;
import tech.anonymoushacker1279.immersiveweapons.block.corrugated.CorrugatedBlock;
import tech.anonymoushacker1279.immersiveweapons.block.corrugated.CorrugatedBlockFlat;
import tech.anonymoushacker1279.immersiveweapons.block.crafting.BarrelTapBlock;
import tech.anonymoushacker1279.immersiveweapons.block.crafting.TeslaSynthesizerBlock;
import tech.anonymoushacker1279.immersiveweapons.block.crafting.small_parts.SmallPartsTable;
import tech.anonymoushacker1279.immersiveweapons.block.decoration.*;
import tech.anonymoushacker1279.immersiveweapons.block.decoration.skull.CustomSkullBlocks.CustomSkullBlock;
import tech.anonymoushacker1279.immersiveweapons.block.decoration.skull.CustomSkullBlocks.CustomWallSkullBlock;
import tech.anonymoushacker1279.immersiveweapons.block.decoration.skull.CustomSkullTypes;
import tech.anonymoushacker1279.immersiveweapons.block.misc.MedicStatueBlock;
import tech.anonymoushacker1279.immersiveweapons.block.misc.MinutemanStatueBlock;
import tech.anonymoushacker1279.immersiveweapons.block.misc.warrior_statue.*;
import tech.anonymoushacker1279.immersiveweapons.block.mud.*;
import tech.anonymoushacker1279.immersiveweapons.block.properties.WoodTypes;
import tech.anonymoushacker1279.immersiveweapons.block.sign.BurnedOakStandingSignBlock;
import tech.anonymoushacker1279.immersiveweapons.block.sign.BurnedOakWallSignBlock;
import tech.anonymoushacker1279.immersiveweapons.blockentity.*;
import tech.anonymoushacker1279.immersiveweapons.client.particle.bullet_impact.BulletImpactParticleOptions;
import tech.anonymoushacker1279.immersiveweapons.client.particle.smoke_grenade.SmokeGrenadeParticleOptions;
import tech.anonymoushacker1279.immersiveweapons.container.SmallPartsContainer;
import tech.anonymoushacker1279.immersiveweapons.container.TeslaSynthesizerContainer;
import tech.anonymoushacker1279.immersiveweapons.data.tags.groups.forge.ForgeItemTagGroups;
import tech.anonymoushacker1279.immersiveweapons.entity.misc.ChairEntity;
import tech.anonymoushacker1279.immersiveweapons.entity.monster.*;
import tech.anonymoushacker1279.immersiveweapons.entity.monster.lava_revenant.LavaRevenantEntity;
import tech.anonymoushacker1279.immersiveweapons.entity.neutral.FieldMedicEntity;
import tech.anonymoushacker1279.immersiveweapons.entity.neutral.MinutemanEntity;
import tech.anonymoushacker1279.immersiveweapons.entity.projectile.ArrowEntities.*;
import tech.anonymoushacker1279.immersiveweapons.entity.projectile.BulletEntities.*;
import tech.anonymoushacker1279.immersiveweapons.entity.projectile.*;
import tech.anonymoushacker1279.immersiveweapons.entity.vehicle.*;
import tech.anonymoushacker1279.immersiveweapons.item.*;
import tech.anonymoushacker1279.immersiveweapons.item.armor.*;
import tech.anonymoushacker1279.immersiveweapons.item.crafting.*;
import tech.anonymoushacker1279.immersiveweapons.item.fortitude.*;
import tech.anonymoushacker1279.immersiveweapons.item.materials.CustomArmorMaterials;
import tech.anonymoushacker1279.immersiveweapons.item.materials.CustomItemMaterials;
import tech.anonymoushacker1279.immersiveweapons.item.potion.AlcoholItem;
import tech.anonymoushacker1279.immersiveweapons.item.potion.WineItem;
import tech.anonymoushacker1279.immersiveweapons.item.projectile.arrow.*;
import tech.anonymoushacker1279.immersiveweapons.item.projectile.bullet.*;
import tech.anonymoushacker1279.immersiveweapons.item.projectile.gun.*;
import tech.anonymoushacker1279.immersiveweapons.item.projectile.throwable.*;
import tech.anonymoushacker1279.immersiveweapons.item.tool.molten.*;
import tech.anonymoushacker1279.immersiveweapons.item.tool.tesla.*;
import tech.anonymoushacker1279.immersiveweapons.item.tool.ventus.*;
import tech.anonymoushacker1279.immersiveweapons.item.utility.*;
import tech.anonymoushacker1279.immersiveweapons.potion.*;
import tech.anonymoushacker1279.immersiveweapons.util.CreativeTab;
import tech.anonymoushacker1279.immersiveweapons.util.GeneralUtilities;
import tech.anonymoushacker1279.immersiveweapons.world.food.FoodItemProperties;
import tech.anonymoushacker1279.immersiveweapons.world.level.levelgen.feature.treedecorators.BurnedBranchDecorator;
import tech.anonymoushacker1279.immersiveweapons.world.level.loot.AzulKeystoneFragmentInChestsLootModifierHandler;
import tech.anonymoushacker1279.immersiveweapons.world.level.loot.LogShardsLootModifierHandler;

import java.util.function.Function;

@SuppressWarnings({"unused", "ConstantConditions"})
public class DeferredRegistryHandler {

	// Item Register
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ImmersiveWeapons.MOD_ID);
	// Block Register
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ImmersiveWeapons.MOD_ID);
	// Entity Register
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ImmersiveWeapons.MOD_ID);
	// Sound Register
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ImmersiveWeapons.MOD_ID);
	// Container Register
	public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ImmersiveWeapons.MOD_ID);
	// Recipe Register
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ImmersiveWeapons.MOD_ID);
	// Recipe Type Serializer
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ImmersiveWeapons.MOD_ID);
	// Particle Register
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ImmersiveWeapons.MOD_ID);
	// Global Loot Modifier Register
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ImmersiveWeapons.MOD_ID);
	// Tile Entity Register
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ImmersiveWeapons.MOD_ID);
	// Effect Register
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ImmersiveWeapons.MOD_ID);
	// Tree Decorators Register
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, ImmersiveWeapons.MOD_ID);

	/**
	 * Initialize deferred registers.
	 */
	public static void init() {
		ImmersiveWeapons.LOGGER.info("Initializing deferred registry");
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(modEventBus);
		BLOCKS.register(modEventBus);
		ENTITY_TYPES.register(modEventBus);
		SOUND_EVENTS.register(modEventBus);
		CONTAINER_TYPES.register(modEventBus);
		RECIPE_SERIALIZER.register(modEventBus);
		RECIPE_TYPES.register(modEventBus);
		PARTICLE_TYPES.register(modEventBus);
		GLOBAL_LOOT_MODIFIER_SERIALIZER.register(modEventBus);
		BLOCK_ENTITIES.register(modEventBus);
		EFFECTS.register(modEventBus);
		TREE_DECORATORS.register(modEventBus);
	}

	public static final CreativeModeTab ITEM_GROUP = new CreativeTab(ImmersiveWeapons.MOD_ID);

	// Tools
	public static final RegistryObject<MoltenSword> MOLTEN_SWORD = ITEMS.register("molten_sword", () -> new MoltenSword(CustomItemMaterials.MOLTEN, 4, -2.1f, new Properties().tab(DeferredRegistryHandler.ITEM_GROUP).fireResistant()));
	public static final RegistryObject<MoltenPickaxe> MOLTEN_PICKAXE = ITEMS.register("molten_pickaxe", () -> new MoltenPickaxe(CustomItemMaterials.MOLTEN, 2, -2.3F, new Properties().tab(DeferredRegistryHandler.ITEM_GROUP).fireResistant()));
	public static final RegistryObject<MoltenAxe> MOLTEN_AXE = ITEMS.register("molten_axe", () -> new MoltenAxe(CustomItemMaterials.MOLTEN, 6, -3.0f, new Properties().tab(DeferredRegistryHandler.ITEM_GROUP).fireResistant()));
	public static final RegistryObject<MoltenShovel> MOLTEN_SHOVEL = ITEMS.register("molten_shovel", () -> new MoltenShovel(CustomItemMaterials.MOLTEN, -1, -3.0f, new Properties().tab(DeferredRegistryHandler.ITEM_GROUP).fireResistant()));
	public static final RegistryObject<MoltenHoe> MOLTEN_HOE = ITEMS.register("molten_hoe", () -> new MoltenHoe(CustomItemMaterials.MOLTEN, -3, 1.0f, new Properties().tab(DeferredRegistryHandler.ITEM_GROUP).fireResistant()));
	public static final RegistryObject<SwordItem> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(CustomItemMaterials.COPPER, 2, -2.4f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(CustomItemMaterials.COPPER, 1, -2.8F, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<AxeItem> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(CustomItemMaterials.COPPER, 3, -3.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<ShovelItem> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ShovelItem(CustomItemMaterials.COPPER, -1, -2.7f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<HoeItem> COPPER_HOE = ITEMS.register("copper_hoe", () -> new HoeItem(CustomItemMaterials.COPPER, -2, -3.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<TeslaSword> TESLA_SWORD = ITEMS.register("tesla_sword", () -> new TeslaSword(CustomItemMaterials.TESLA, 5, -2.1f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<TeslaPickaxe> TESLA_PICKAXE = ITEMS.register("tesla_pickaxe", () -> new TeslaPickaxe(CustomItemMaterials.TESLA, 4, -2.3f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<TeslaAxe> TESLA_AXE = ITEMS.register("tesla_axe", () -> new TeslaAxe(CustomItemMaterials.TESLA, 7, -3.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<TeslaShovel> TESLA_SHOVEL = ITEMS.register("tesla_shovel", () -> new TeslaShovel(CustomItemMaterials.TESLA, 0, -3.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<TeslaHoe> TESLA_HOE = ITEMS.register("tesla_hoe", () -> new TeslaHoe(CustomItemMaterials.TESLA, -3, 1.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<SwordItem> COBALT_SWORD = ITEMS.register("cobalt_sword", () -> new SwordItem(CustomItemMaterials.COBALT, 2, -2.4f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<PickaxeItem> COBALT_PICKAXE = ITEMS.register("cobalt_pickaxe", () -> new PickaxeItem(CustomItemMaterials.COBALT, 1, -2.7F, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<AxeItem> COBALT_AXE = ITEMS.register("cobalt_axe", () -> new AxeItem(CustomItemMaterials.COBALT, 3, -3.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<ShovelItem> COBALT_SHOVEL = ITEMS.register("cobalt_shovel", () -> new ShovelItem(CustomItemMaterials.COBALT, -1, -2.7f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<HoeItem> COBALT_HOE = ITEMS.register("cobalt_hoe", () -> new HoeItem(CustomItemMaterials.COBALT, -3, -2.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<VentusSword> VENTUS_SWORD = ITEMS.register("ventus_sword", () -> new VentusSword(CustomItemMaterials.VENTUS, 4, -2.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<VentusPickaxe> VENTUS_PICKAXE = ITEMS.register("ventus_pickaxe", () -> new VentusPickaxe(CustomItemMaterials.VENTUS, 3, -2.2f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<VentusAxe> VENTUS_AXE = ITEMS.register("ventus_axe", () -> new VentusAxe(CustomItemMaterials.VENTUS, 6, -2.9f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<VentusShovel> VENTUS_SHOVEL = ITEMS.register("ventus_shovel", () -> new VentusShovel(CustomItemMaterials.VENTUS, 0, -2.9f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<VentusHoe> VENTUS_HOE = ITEMS.register("ventus_hoe", () -> new VentusHoe(CustomItemMaterials.VENTUS, -3, 1.0f, new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<VentusStaff> VENTUS_STAFF = ITEMS.register("ventus_staff", () -> new VentusStaff(new Properties().durability(300).tab(ITEM_GROUP)));
	public static final RegistryObject<PikeItem> WOODEN_PIKE = ITEMS.register("wooden_pike", () -> new PikeItem(new Properties().durability(59).tab(ITEM_GROUP), 4.0d, -2.6d, Ingredient.of(ItemTags.PLANKS)));
	public static final RegistryObject<PikeItem> STONE_PIKE = ITEMS.register("stone_pike", () -> new PikeItem(new Properties().durability(131).tab(ITEM_GROUP), 5.0d, -2.6d, Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)));
	public static final RegistryObject<PikeItem> GOLDEN_PIKE = ITEMS.register("golden_pike", () -> new PikeItem(new Properties().durability(32).tab(ITEM_GROUP), 4.0d, -2.6d, Ingredient.of(Tags.Items.INGOTS_GOLD)));
	public static final RegistryObject<PikeItem> COPPER_PIKE = ITEMS.register("copper_pike", () -> new PikeItem(new Properties().durability(180).tab(ITEM_GROUP), 5.0d, -2.6d, Ingredient.of(ForgeItemTagGroups.COPPER_INGOTS)));
	public static final RegistryObject<PikeItem> IRON_PIKE = ITEMS.register("iron_pike", () -> new PikeItem(new Properties().durability(250).tab(ITEM_GROUP), 6.0d, -2.6d, Ingredient.of(Tags.Items.INGOTS_IRON)));
	public static final RegistryObject<PikeItem> COBALT_PIKE = ITEMS.register("cobalt_pike", () -> new PikeItem(new Properties().durability(300).tab(ITEM_GROUP), 6.0d, -2.6d, Ingredient.of(ForgeItemTagGroups.COBALT_INGOTS)));
	public static final RegistryObject<PikeItem> DIAMOND_PIKE = ITEMS.register("diamond_pike", () -> new PikeItem(new Properties().durability(1561).tab(ITEM_GROUP), 7.0d, -2.6d, Ingredient.of(Tags.Items.GEMS_DIAMOND)));
	public static final RegistryObject<PikeItem> NETHERITE_PIKE = ITEMS.register("netherite_pike", () -> new PikeItem(new Properties().durability(2031).tab(ITEM_GROUP).fireResistant(), 8.0d, -2.6d, Ingredient.of(Tags.Items.INGOTS_NETHERITE)));
	public static final RegistryObject<SimplePistolItem> FLINTLOCK_PISTOL = ITEMS.register("flintlock_pistol", () -> new SimplePistolItem(new Properties().tab(ITEM_GROUP).durability(499)));
	public static final RegistryObject<SimpleShotgunItem> BLUNDERBUSS = ITEMS.register("blunderbuss", () -> new SimpleShotgunItem(new Properties().tab(ITEM_GROUP).durability(449)));
	public static final RegistryObject<MusketItem> MUSKET = ITEMS.register("musket", () -> new MusketItem(new Properties().tab(ITEM_GROUP).durability(499), false));
	public static final RegistryObject<MusketItem> MUSKET_SCOPE = ITEMS.register("musket_scope", () -> new MusketItem(new Properties().tab(ITEM_GROUP).durability(499), true));
	public static final RegistryObject<FlareGunItem> FLARE_GUN = ITEMS.register("flare_gun", () -> new FlareGunItem(new Properties().tab(ITEM_GROUP).durability(399)));
	public static final RegistryObject<GauntletItem> WOODEN_GAUNTLET = ITEMS.register("wooden_gauntlet", () -> new GauntletItem(Tiers.WOOD, 2, -2.3f, new Properties().tab(ITEM_GROUP), 0.15f, 0, Ingredient.of(ItemTags.PLANKS)));
	public static final RegistryObject<GauntletItem> STONE_GAUNTLET = ITEMS.register("stone_gauntlet", () -> new GauntletItem(Tiers.STONE, 2, -2.3f, new Properties().tab(ITEM_GROUP), 0.25f, 0, Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)));
	public static final RegistryObject<GauntletItem> GOLDEN_GAUNTLET = ITEMS.register("golden_gauntlet", () -> new GauntletItem(Tiers.GOLD, 2, -2.3f, new Properties().tab(ITEM_GROUP), 0.35f, 0, Ingredient.of(Tags.Items.INGOTS_GOLD)));
	public static final RegistryObject<GauntletItem> COPPER_GAUNTLET = ITEMS.register("copper_gauntlet", () -> new GauntletItem(CustomItemMaterials.COPPER, 1, -2.3f, new Properties().tab(ITEM_GROUP), 0.45f, 0, Ingredient.of(ForgeItemTagGroups.COPPER_INGOTS)));
	public static final RegistryObject<GauntletItem> IRON_GAUNTLET = ITEMS.register("iron_gauntlet", () -> new GauntletItem(Tiers.IRON, 2, -2.3f, new Properties().tab(ITEM_GROUP), 0.55f, 0, Ingredient.of(Tags.Items.INGOTS_IRON)));
	public static final RegistryObject<GauntletItem> COBALT_GAUNTLET = ITEMS.register("cobalt_gauntlet", () -> new GauntletItem(CustomItemMaterials.COBALT, 1, -2.3f, new Properties().tab(ITEM_GROUP), 0.60f, 0, Ingredient.of(ForgeItemTagGroups.COBALT_INGOTS)));
	public static final RegistryObject<GauntletItem> DIAMOND_GAUNTLET = ITEMS.register("diamond_gauntlet", () -> new GauntletItem(Tiers.DIAMOND, 2, -2.3f, new Properties().tab(ITEM_GROUP), 0.75f, 1, Ingredient.of(Tags.Items.GEMS_DIAMOND)));
	public static final RegistryObject<GauntletItem> NETHERITE_GAUNTLET = ITEMS.register("netherite_gauntlet", () -> new GauntletItem(Tiers.NETHERITE, 2, -2.3f, new Properties().tab(ITEM_GROUP), 0.85f, 1, Ingredient.of(Tags.Items.INGOTS_NETHERITE)));

	// Items
	public static final RegistryObject<Item> STONE_SHARD = ITEMS.register("stone_shard", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> DIAMOND_SHARD = ITEMS.register("diamond_shard", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> MOLTEN_SHARD = ITEMS.register("molten_shard", () -> new Item(new Properties().tab(ITEM_GROUP).fireResistant()));
	public static final RegistryObject<Item> VENTUS_SHARD = ITEMS.register("ventus_shard", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> WOODEN_SHARD = ITEMS.register("wooden_shard", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> COBALT_NUGGET = ITEMS.register("cobalt_nugget", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> WOODEN_TOOL_ROD = ITEMS.register("wooden_tool_rod", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> OBSIDIAN_ROD = ITEMS.register("obsidian_rod", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> MOLTEN_INGOT = ITEMS.register("molten_ingot", () -> new FuelItem(new Properties().tab(ITEM_GROUP).fireResistant(), 24000));
	public static final RegistryObject<Item> CONDUCTIVE_ALLOY = ITEMS.register("conductive_alloy", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> ELECTRIC_INGOT = ITEMS.register("electric_ingot", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> TESLA_INGOT = ITEMS.register("tesla_ingot", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> VENTUS_STAFF_CORE = ITEMS.register("ventus_staff_core", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> AZUL_KEYSTONE = ITEMS.register("azul_keystone", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> AZUL_KEYSTONE_FRAGMENT = ITEMS.register("azul_keystone_fragment", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> CELESTIAL_FRAGMENT = ITEMS.register("celestial_fragment", () -> new Item(new Properties().tab(ITEM_GROUP).fireResistant()));
	public static final RegistryObject<Item> WOODEN_PIKE_HEAD = ITEMS.register("wooden_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> STONE_PIKE_HEAD = ITEMS.register("stone_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> GOLDEN_PIKE_HEAD = ITEMS.register("golden_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> COPPER_PIKE_HEAD = ITEMS.register("copper_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> IRON_PIKE_HEAD = ITEMS.register("iron_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> COBALT_PIKE_HEAD = ITEMS.register("cobalt_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> DIAMOND_PIKE_HEAD = ITEMS.register("diamond_pike_head", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<WoodenArrowItem> WOODEN_ARROW = ITEMS.register("wooden_arrow", () -> new WoodenArrowItem(new Properties().tab(ITEM_GROUP), 1.65d));
	public static final RegistryObject<StoneArrowItem> STONE_ARROW = ITEMS.register("stone_arrow", () -> new StoneArrowItem(new Properties().tab(ITEM_GROUP), 1.85d));
	public static final RegistryObject<GoldenArrowItem> GOLDEN_ARROW = ITEMS.register("golden_arrow", () -> new GoldenArrowItem(new Properties().tab(ITEM_GROUP), 2.10d));
	public static final RegistryObject<CopperArrowItem> COPPER_ARROW = ITEMS.register("copper_arrow", () -> new CopperArrowItem(new Properties().tab(ITEM_GROUP), 2.15d));
	public static final RegistryObject<IronArrowItem> IRON_ARROW = ITEMS.register("iron_arrow", () -> new IronArrowItem(new Properties().tab(ITEM_GROUP), 2.35d));
	public static final RegistryObject<CobaltArrowItem> COBALT_ARROW = ITEMS.register("cobalt_arrow", () -> new CobaltArrowItem(new Properties().tab(ITEM_GROUP), 2.55d));
	public static final RegistryObject<DiamondArrowItem> DIAMOND_ARROW = ITEMS.register("diamond_arrow", () -> new DiamondArrowItem(new Properties().tab(ITEM_GROUP), 3.00d));
	public static final RegistryObject<NetheriteArrowItem> NETHERITE_ARROW = ITEMS.register("netherite_arrow", () -> new NetheriteArrowItem(new Properties().tab(ITEM_GROUP).fireResistant(), 5.75d));
	public static final RegistryObject<SmokeGrenadeArrowItem> SMOKE_GRENADE_ARROW = ITEMS.register("smoke_grenade_arrow", () -> new SmokeGrenadeArrowItem(new Properties().tab(ITEM_GROUP), 2.00d, 0));
	public static final RegistryObject<SmokeGrenadeArrowItem> SMOKE_GRENADE_ARROW_RED = ITEMS.register("smoke_grenade_arrow_red", () -> new SmokeGrenadeArrowItem(new Properties().tab(ITEM_GROUP), 2.00d, 1));
	public static final RegistryObject<SmokeGrenadeArrowItem> SMOKE_GRENADE_ARROW_GREEN = ITEMS.register("smoke_grenade_arrow_green", () -> new SmokeGrenadeArrowItem(new Properties().tab(ITEM_GROUP), 2.00d, 2));
	public static final RegistryObject<SmokeGrenadeArrowItem> SMOKE_GRENADE_ARROW_BLUE = ITEMS.register("smoke_grenade_arrow_blue", () -> new SmokeGrenadeArrowItem(new Properties().tab(ITEM_GROUP), 2.00d, 3));
	public static final RegistryObject<SmokeGrenadeArrowItem> SMOKE_GRENADE_ARROW_PURPLE = ITEMS.register("smoke_grenade_arrow_purple", () -> new SmokeGrenadeArrowItem(new Properties().tab(ITEM_GROUP), 2.00d, 4));
	public static final RegistryObject<SmokeGrenadeArrowItem> SMOKE_GRENADE_ARROW_YELLOW = ITEMS.register("smoke_grenade_arrow_yellow", () -> new SmokeGrenadeArrowItem(new Properties().tab(ITEM_GROUP), 2.00d, 5));
	public static final RegistryObject<WoodenMusketBallItem> WOODEN_MUSKET_BALL = ITEMS.register("wooden_musket_ball", () -> new WoodenMusketBallItem(new Properties().tab(ITEM_GROUP), 2.0d));
	public static final RegistryObject<StoneMusketBallItem> STONE_MUSKET_BALL = ITEMS.register("stone_musket_ball", () -> new StoneMusketBallItem(new Properties().tab(ITEM_GROUP), 2.20d));
	public static final RegistryObject<GoldenMusketBallItem> GOLDEN_MUSKET_BALL = ITEMS.register("golden_musket_ball", () -> new GoldenMusketBallItem(new Properties().tab(ITEM_GROUP), 2.30d));
	public static final RegistryObject<CopperMusketBallItem> COPPER_MUSKET_BALL = ITEMS.register("copper_musket_ball", () -> new CopperMusketBallItem(new Properties().tab(ITEM_GROUP), 2.40d));
	public static final RegistryObject<IronMusketBallItem> IRON_MUSKET_BALL = ITEMS.register("iron_musket_ball", () -> new IronMusketBallItem(new Properties().tab(ITEM_GROUP), 2.65d));
	public static final RegistryObject<CobaltMusketBallItem> COBALT_MUSKET_BALL = ITEMS.register("cobalt_musket_ball", () -> new CobaltMusketBallItem(new Properties().tab(ITEM_GROUP), 2.90d));
	public static final RegistryObject<DiamondMusketBallItem> DIAMOND_MUSKET_BALL = ITEMS.register("diamond_musket_ball", () -> new DiamondMusketBallItem(new Properties().tab(ITEM_GROUP), 3.35d));
	public static final RegistryObject<NetheriteMusketBallItem> NETHERITE_MUSKET_BALL = ITEMS.register("netherite_musket_ball", () -> new NetheriteMusketBallItem(new Properties().tab(ITEM_GROUP).fireResistant(), 6.50d));
	public static final RegistryObject<FlareItem> FLARE = ITEMS.register("flare", () -> new FlareItem(new Properties().tab(ITEM_GROUP), 0.1d));
	public static final RegistryObject<Item> MORTAR_SHELL = ITEMS.register("mortar_shell", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> GRENADE_ASSEMBLY = ITEMS.register("grenade_assembly", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> TOOL_JOINT = ITEMS.register("tool_joint", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> GAUNTLET_SCAFFOLDING = ITEMS.register("gauntlet_scaffolding", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> FLINTLOCK_ASSEMBLY = ITEMS.register("flintlock_assembly", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> TRIGGER_ASSEMBLY = ITEMS.register("trigger_assembly", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> HEAVY_WOODEN_STOCK = ITEMS.register("heavy_wooden_stock", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> WOODEN_PISTOL_HANDLE = ITEMS.register("wooden_pistol_handle", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> IRON_BARREL = ITEMS.register("iron_barrel", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> EXTENDED_IRON_BARREL = ITEMS.register("extended_iron_barrel", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> SHORT_IRON_BARREL = ITEMS.register("short_iron_barrel", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> WIDE_GOLDEN_BARREL = ITEMS.register("wide_golden_barrel", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> SCOPE_MOUNT = ITEMS.register("scope_mount", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<Item> SCOPE = ITEMS.register("scope", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<SmokeGrenadeItem> SMOKE_GRENADE = ITEMS.register("smoke_grenade", () -> new SmokeGrenadeItem(new Properties().tab(ITEM_GROUP).stacksTo(16), 0));
	public static final RegistryObject<SmokeGrenadeItem> SMOKE_GRENADE_RED = ITEMS.register("smoke_grenade_red", () -> new SmokeGrenadeItem(new Properties().tab(ITEM_GROUP).stacksTo(16), 1));
	public static final RegistryObject<SmokeGrenadeItem> SMOKE_GRENADE_GREEN = ITEMS.register("smoke_grenade_green", () -> new SmokeGrenadeItem(new Properties().tab(ITEM_GROUP).stacksTo(16), 2));
	public static final RegistryObject<SmokeGrenadeItem> SMOKE_GRENADE_BLUE = ITEMS.register("smoke_grenade_blue", () -> new SmokeGrenadeItem(new Properties().tab(ITEM_GROUP).stacksTo(16), 3));
	public static final RegistryObject<SmokeGrenadeItem> SMOKE_GRENADE_PURPLE = ITEMS.register("smoke_grenade_purple", () -> new SmokeGrenadeItem(new Properties().tab(ITEM_GROUP).stacksTo(16), 4));
	public static final RegistryObject<SmokeGrenadeItem> SMOKE_GRENADE_YELLOW = ITEMS.register("smoke_grenade_yellow", () -> new SmokeGrenadeItem(new Properties().tab(ITEM_GROUP).stacksTo(16), 5));
	public static final RegistryObject<MolotovItem> MOLOTOV_COCKTAIL = ITEMS.register("molotov_cocktail", () -> new MolotovItem(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<Item> SMOKE_POWDER = ITEMS.register("smoke_powder", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BasicContainerItem> MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle", () -> new BasicContainerItem(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<AlcoholItem> BOTTLE_OF_ALCOHOL = ITEMS.register("bottle_of_alcohol", () -> new AlcoholItem(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<WineItem> BOTTLE_OF_WINE = ITEMS.register("bottle_of_wine", () -> new WineItem(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<Item> PLIERS = ITEMS.register("pliers", () -> new Item(new Properties().tab(ITEM_GROUP).stacksTo(1)));
	public static final RegistryObject<ChocolateBarItem> CHOCOLATE_BAR = ITEMS.register("chocolate_bar", () -> new ChocolateBarItem(new Properties().tab(ITEM_GROUP).food(FoodItemProperties.CHOCOLATE_BAR), false));
	public static final RegistryObject<ChocolateBarItem> EXPLOSIVE_CHOCOLATE_BAR = ITEMS.register("explosive_chocolate_bar", () -> new ChocolateBarItem(new Properties().tab(ITEM_GROUP).food(FoodItemProperties.CHOCOLATE_BAR), true));
	public static final RegistryObject<BandageItem> BANDAGE = ITEMS.register("bandage", () -> new BandageItem(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<Item> MRE = ITEMS.register("mre", () -> new Item(new Properties().tab(ITEM_GROUP).food(FoodItemProperties.MRE)));
	public static final RegistryObject<PainkillerItem> PAINKILLERS = ITEMS.register("painkillers", () -> new PainkillerItem(new Properties().tab(ITEM_GROUP).stacksTo(24)));
	public static final RegistryObject<Item> SYRINGE = ITEMS.register("syringe", () -> new Item(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<MorphineItem> MORPHINE = ITEMS.register("morphine", () -> new MorphineItem(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<UsedSyringeItem> USED_SYRINGE = ITEMS.register("used_syringe", () -> new UsedSyringeItem(new Properties().tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<FirstAidKitItem> FIRST_AID_KIT = ITEMS.register("first_aid_kit", () -> new FirstAidKitItem(new Properties().tab(ITEM_GROUP).stacksTo(8)));
	public static final RegistryObject<Item> CLOTH_SCRAP = ITEMS.register("cloth_scrap", () -> new Item(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<MudBallItem> MUD_BALL = ITEMS.register("mud_ball", () -> new MudBallItem(new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<CustomBoatItem> BURNED_OAK_BOAT = ITEMS.register("burned_oak_boat", () -> new CustomBoatItem(CustomBoatType.BURNED_OAK, new Item.Properties().tab(ITEM_GROUP).stacksTo(1), false));
	public static final RegistryObject<CustomBoatItem> BURNED_OAK_CHEST_BOAT = ITEMS.register("burned_oak_chest_boat", () -> new CustomBoatItem(CustomBoatType.BURNED_OAK, new Item.Properties().tab(ITEM_GROUP).stacksTo(1), true));

	// Armor
	public static final RegistryObject<MoltenArmorItem> MOLTEN_HELMET = ITEMS.register("molten_helmet", () -> new MoltenArmorItem(CustomArmorMaterials.MOLTEN, EquipmentSlot.HEAD, new Item.Properties().tab(ITEM_GROUP).fireResistant(), false));
	public static final RegistryObject<MoltenArmorItem> MOLTEN_CHESTPLATE = ITEMS.register("molten_chestplate", () -> new MoltenArmorItem(CustomArmorMaterials.MOLTEN, EquipmentSlot.CHEST, new Item.Properties().tab(ITEM_GROUP).fireResistant(), false));
	public static final RegistryObject<MoltenArmorItem> MOLTEN_LEGGINGS = ITEMS.register("molten_leggings", () -> new MoltenArmorItem(CustomArmorMaterials.MOLTEN, EquipmentSlot.LEGS, new Item.Properties().tab(ITEM_GROUP).fireResistant(), true));
	public static final RegistryObject<MoltenArmorItem> MOLTEN_BOOTS = ITEMS.register("molten_boots", () -> new MoltenArmorItem(CustomArmorMaterials.MOLTEN, EquipmentSlot.FEET, new Item.Properties().tab(ITEM_GROUP).fireResistant(), false));
	public static final RegistryObject<CopperArmorItem> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new CopperArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.HEAD, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<CopperArmorItem> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new CopperArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.CHEST, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<CopperArmorItem> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new CopperArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.LEGS, new Item.Properties().tab(ITEM_GROUP), true));
	public static final RegistryObject<CopperArmorItem> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new CopperArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.FEET, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<TeslaArmorItem> TESLA_HELMET = ITEMS.register("tesla_helmet", () -> new TeslaArmorItem(CustomArmorMaterials.TESLA, EquipmentSlot.HEAD, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<TeslaArmorItem> TESLA_CHESTPLATE = ITEMS.register("tesla_chestplate", () -> new TeslaArmorItem(CustomArmorMaterials.TESLA, EquipmentSlot.CHEST, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<TeslaArmorItem> TESLA_LEGGINGS = ITEMS.register("tesla_leggings", () -> new TeslaArmorItem(CustomArmorMaterials.TESLA, EquipmentSlot.LEGS, new Item.Properties().tab(ITEM_GROUP), true));
	public static final RegistryObject<TeslaArmorItem> TESLA_BOOTS = ITEMS.register("tesla_boots", () -> new TeslaArmorItem(CustomArmorMaterials.TESLA, EquipmentSlot.FEET, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<CobaltArmorItem> COBALT_HELMET = ITEMS.register("cobalt_helmet", () -> new CobaltArmorItem(CustomArmorMaterials.COBALT, EquipmentSlot.HEAD, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<CobaltArmorItem> COBALT_CHESTPLATE = ITEMS.register("cobalt_chestplate", () -> new CobaltArmorItem(CustomArmorMaterials.COBALT, EquipmentSlot.CHEST, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<CobaltArmorItem> COBALT_LEGGINGS = ITEMS.register("cobalt_leggings", () -> new CobaltArmorItem(CustomArmorMaterials.COBALT, EquipmentSlot.LEGS, new Item.Properties().tab(ITEM_GROUP), true));
	public static final RegistryObject<CobaltArmorItem> COBALT_BOOTS = ITEMS.register("cobalt_boots", () -> new CobaltArmorItem(CustomArmorMaterials.COBALT, EquipmentSlot.FEET, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<VentusArmorItem> VENTUS_HELMET = ITEMS.register("ventus_helmet", () -> new VentusArmorItem(CustomArmorMaterials.VENTUS, EquipmentSlot.HEAD, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<VentusArmorItem> VENTUS_CHESTPLATE = ITEMS.register("ventus_chestplate", () -> new VentusArmorItem(CustomArmorMaterials.VENTUS, EquipmentSlot.CHEST, new Item.Properties().tab(ITEM_GROUP), false));
	public static final RegistryObject<VentusArmorItem> VENTUS_LEGGINGS = ITEMS.register("ventus_leggings", () -> new VentusArmorItem(CustomArmorMaterials.VENTUS, EquipmentSlot.LEGS, new Item.Properties().tab(ITEM_GROUP), true));
	public static final RegistryObject<VentusArmorItem> VENTUS_BOOTS = ITEMS.register("ventus_boots", () -> new VentusArmorItem(CustomArmorMaterials.VENTUS, EquipmentSlot.FEET, new Item.Properties().tab(ITEM_GROUP), false));

	// Spawn eggs
	public static final RegistryObject<ForgeSpawnEggItem> DYING_SOLDIER_SPAWN_EGG = ITEMS.register("dying_soldier_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.DYING_SOLDIER_ENTITY, 0x7a6851, 0x783d22, (new Item.Properties()).tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<ForgeSpawnEggItem> MINUTEMAN_SPAWN_EGG = ITEMS.register("minuteman_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.MINUTEMAN_ENTITY, 0x494522, 0x204b2a, (new Item.Properties()).tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<ForgeSpawnEggItem> FIELD_MEDIC_SPAWN_EGG = ITEMS.register("field_medic_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.FIELD_MEDIC_ENTITY, 0xde5451, 0xebe4d2, (new Item.Properties()).tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<ForgeSpawnEggItem> WANDERING_WARRIOR_SPAWN_EGG = ITEMS.register("wandering_warrior_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.WANDERING_WARRIOR_ENTITY, 0x614226, 0x2e6278, (new Item.Properties()).tab(ITEM_GROUP).stacksTo(16)));
	public static final RegistryObject<ForgeSpawnEggItem> HANS_SPAWN_EGG = ITEMS.register("hans_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.HANS_ENTITY, 0xd0a873, 0xafafaf, (new Item.Properties().tab(ITEM_GROUP).stacksTo(16))));
	public static final RegistryObject<ForgeSpawnEggItem> LAVA_REVENANT_SPAWN_EGG = ITEMS.register("lava_revenant_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.LAVA_REVENANT_ENTITY, 0x640000, 0x990000, (new Item.Properties().tab(ITEM_GROUP).stacksTo(16))));
	public static final RegistryObject<ForgeSpawnEggItem> ROCK_SPIDER_SPAWN_EGG = ITEMS.register("rock_spider_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.ROCK_SPIDER_ENTITY, 0x7f7f7f, 0xa80e0e, (new Item.Properties().tab(ITEM_GROUP).stacksTo(16))));
	public static final RegistryObject<ForgeSpawnEggItem> CELESTIAL_TOWER_SPAWN_EGG = ITEMS.register("celestial_tower_spawn_egg", () -> new ForgeSpawnEggItem(DeferredRegistryHandler.CELESTIAL_TOWER_ENTITY, 0x63353d, 0xb3754b, (new Item.Properties().tab(ITEM_GROUP).stacksTo(16))));

	// Blocks
	// Breakable via pickaxe
	// Wooden tier
	public static final RegistryObject<BarrelTapBlock> BARREL_TAP = BLOCKS.register("barrel_tap", () -> new BarrelTapBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f).sound(SoundType.METAL)));
	public static final RegistryObject<MortarBlock> MORTAR = BLOCKS.register("mortar", () -> new MortarBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<Block> CLOUD_MARBLE = BLOCKS.register("cloud_marble", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CLOUD_MARBLE_BRICKS = BLOCKS.register("cloud_marble_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<RotatedPillarBlock> CLOUD_MARBLE_PILLAR = BLOCKS.register("cloud_marble_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<StairBlock> CLOUD_MARBLE_BRICK_STAIRS = BLOCKS.register("cloud_marble_brick_stairs", () -> new StairBlock(() -> CLOUD_MARBLE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CLOUD_MARBLE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CLOUD_MARBLE_BRICK_SLAB = BLOCKS.register("cloud_marble_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<FlagPoleBlock> FLAG_POLE = BLOCKS.register("flag_pole", () -> new FlagPoleBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> AMERICAN_FLAG = BLOCKS.register("american_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> GADSDEN_FLAG = BLOCKS.register("gadsden_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> CANADIAN_FLAG = BLOCKS.register("canadian_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> MEXICAN_FLAG = BLOCKS.register("mexican_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> BRITISH_FLAG = BLOCKS.register("british_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> TROLL_FLAG = BLOCKS.register("troll_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<FlagBlock> IMMERSIVE_WEAPONS_FLAG = BLOCKS.register("immersive_weapons_flag", () -> new FlagBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f).sound(SoundType.METAL).noOcclusion()));
	// Stone tier
	public static final RegistryObject<SpotlightBlock> SPOTLIGHT = BLOCKS.register("spotlight", () -> new SpotlightBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion().lightLevel((state) -> 0)));
	public static final RegistryObject<CorrugatedBlock> CORRUGATED_IRON_PANEL = BLOCKS.register("corrugated_iron_panel", () -> new CorrugatedBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<CorrugatedBlock> CORRUGATED_IRON_PANEL_BARS = BLOCKS.register("corrugated_iron_panel_bars", () -> new CorrugatedBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<CorrugatedBlockFlat> CORRUGATED_IRON_PANEL_FLAT = BLOCKS.register("corrugated_iron_panel_flat", () -> new CorrugatedBlockFlat(BlockBehaviour.Properties.of(Material.METAL).strength(5.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<CorrugatedBlockFlat> CORRUGATED_IRON_PANEL_FLAT_BARS = BLOCKS.register("corrugated_iron_panel_flat_bars", () -> new CorrugatedBlockFlat(BlockBehaviour.Properties.of(Material.METAL).strength(5.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<WarriorStatueBase> WARRIOR_STATUE_BASE = BLOCKS.register("warrior_statue_base", () -> new WarriorStatueBase(BlockBehaviour.Properties.of(Material.STONE).strength(4.0f).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<WarriorStatueTorso> WARRIOR_STATUE_TORSO = BLOCKS.register("warrior_statue_torso", () -> new WarriorStatueTorso(BlockBehaviour.Properties.of(Material.STONE).strength(4.0f).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<WarriorStatueHead> WARRIOR_STATUE_HEAD = BLOCKS.register("warrior_statue_head", () -> new WarriorStatueHead(BlockBehaviour.Properties.of(Material.STONE).strength(4.0f).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<DropExperienceBlock> SULFUR_ORE = BLOCKS.register("sulfur_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<DropExperienceBlock> DEEPSLATE_SULFUR_ORE = BLOCKS.register("deepslate_sulfur_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4.5f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
	public static final RegistryObject<DropExperienceBlock> NETHER_SULFUR_ORE = BLOCKS.register("nether_sulfur_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.4f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_SULFUR_BLOCK = BLOCKS.register("raw_sulfur_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(0.4f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	// Iron tier
	public static final RegistryObject<BarbedWireFenceBlock> BARBED_WIRE_FENCE = BLOCKS.register("barbed_wire_fence", () -> new BarbedWireFenceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(7.0f, 8.0f).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<DropExperienceBlock> COBALT_ORE = BLOCKS.register("cobalt_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<DropExperienceBlock> DEEPSLATE_COBALT_ORE = BLOCKS.register("deepslate_cobalt_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4.5f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
	public static final RegistryObject<DropExperienceBlock> VENTUS_ORE = BLOCKS.register("ventus_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5f, 6.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> COBALT_BLOCK = BLOCKS.register("cobalt_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(6.0f).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_COBALT_BLOCK = BLOCKS.register("raw_cobalt_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(4.0f).requiresCorrectToolForDrops()));
	public static final RegistryObject<BearTrapBlock> BEAR_TRAP = BLOCKS.register("bear_trap", () -> new BearTrapBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion().strength(2.0f).sound(SoundType.METAL)));
	public static final RegistryObject<BarbedWireBlock> BARBED_WIRE = BLOCKS.register("barbed_wire", () -> new BarbedWireBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f).sound(SoundType.CHAIN).noOcclusion().noCollission()));
	public static final RegistryObject<SpikeTrapBlock> SPIKE_TRAP = BLOCKS.register("spike_trap", () -> new SpikeTrapBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.0f).sound(SoundType.METAL).noOcclusion().noCollission()));
	public static final RegistryObject<CelestialLanternBlock> CELESTIAL_LANTERN = BLOCKS.register("celestial_lantern", () -> new CelestialLanternBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.5f).sound(SoundType.LANTERN).requiresCorrectToolForDrops().lightLevel((blockState) -> 15).noOcclusion()));
	// Diamond tier
	public static final RegistryObject<DropExperienceBlock> MOLTEN_ORE = BLOCKS.register("molten_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6.0f, 8.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<DropExperienceBlock> ELECTRIC_ORE = BLOCKS.register("electric_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6.0f, 8.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MOLTEN_BLOCK = BLOCKS.register("molten_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(45.0f, 1100.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<BasicOrientableBlock> TESLA_BLOCK = BLOCKS.register("tesla_block", () -> new BasicOrientableBlock(BlockBehaviour.Properties.of(Material.METAL).strength(25.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<TeslaSynthesizerBlock> TESLA_SYNTHESIZER = BLOCKS.register("tesla_synthesizer", () -> new TeslaSynthesizerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

	// Breakable via axe
	// Wood tier
	public static final RegistryObject<SmallPartsTable> SMALL_PARTS_TABLE = BLOCKS.register("small_parts_table", () -> new SmallPartsTable(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_BURNED_OAK_WOOD = BLOCKS.register("stripped_burned_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_BURNED_OAK_LOG = BLOCKS.register("stripped_burned_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7F).sound(SoundType.WOOD)));
	public static final RegistryObject<RotatedPillarBlock> BURNED_OAK_WOOD = BLOCKS.register("burned_oak_wood", () -> new StrippablePillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f).sound(SoundType.WOOD), STRIPPED_BURNED_OAK_WOOD.get().defaultBlockState()));
	public static final RegistryObject<RotatedPillarBlock> BURNED_OAK_LOG = BLOCKS.register("burned_oak_log", () -> new StrippablePillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f).sound(SoundType.WOOD), STRIPPED_BURNED_OAK_LOG.get().defaultBlockState()));
	public static final RegistryObject<Block> BURNED_OAK_PLANKS = BLOCKS.register("burned_oak_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f, 2.7f).sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> BURNED_OAK_STAIRS = BLOCKS.register("burned_oak_stairs", () -> new StairBlock(() -> BURNED_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(BURNED_OAK_PLANKS.get())));
	public static final RegistryObject<SlabBlock> BURNED_OAK_SLAB = BLOCKS.register("burned_oak_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f, 2.7f).sound(SoundType.WOOD)));
	public static final RegistryObject<FenceBlock> BURNED_OAK_FENCE = BLOCKS.register("burned_oak_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f, 2.7f).sound(SoundType.WOOD)));
	public static final RegistryObject<FenceGateBlock> BURNED_OAK_FENCE_GATE = BLOCKS.register("burned_oak_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f, 2.7f).sound(SoundType.WOOD)));
	public static final RegistryObject<DoorBlock> BURNED_OAK_DOOR = BLOCKS.register("burned_oak_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f, 2.7f).sound(SoundType.WOOD)));
	public static final RegistryObject<TrapDoorBlock> BURNED_OAK_TRAPDOOR = BLOCKS.register("burned_oak_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.7f, 2.7f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<PressurePlateBlock> BURNED_OAK_PRESSURE_PLATE = BLOCKS.register("burned_oak_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).strength(0.4f).sound(SoundType.WOOD).noOcclusion().noCollission()));
	public static final RegistryObject<BurnedOakStandingSignBlock> BURNED_OAK_SIGN = BLOCKS.register("burned_oak_sign", () -> new BurnedOakStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WoodTypes.BURNED_OAK));
	public static final RegistryObject<BurnedOakWallSignBlock> BURNED_OAK_WALL_SIGN = BLOCKS.register("burned_oak_wall_sign", () -> new BurnedOakWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WoodTypes.BURNED_OAK));
	public static final RegistryObject<ButtonBlock> BURNED_OAK_BUTTON = BLOCKS.register("burned_oak_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.4f).sound(SoundType.WOOD)));
	// Stone tier
	public static final RegistryObject<WoodenSpikesBlock> WOODEN_SPIKES = BLOCKS.register("wooden_spikes", () -> new WoodenSpikesBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).requiresCorrectToolForDrops().noOcclusion()));

	// Breakable via shovel
	// Wood tier
	public static final RegistryObject<SandbagBlock> SANDBAG = BLOCKS.register("sandbag", () -> new SandbagBlock(BlockBehaviour.Properties.of(Material.SAND).strength(4.0f, 5.0f).sound(SoundType.SAND).noOcclusion()));
	public static final RegistryObject<Block> MUD = BLOCKS.register("mud", () -> new IWMudBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(0.8f, 0.3f).sound(SoundType.WET_GRASS).speedFactor(0.75f).randomTicks()));
	public static final RegistryObject<Block> DRIED_MUD = BLOCKS.register("dried_mud", () -> new DriedMudBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(1.0f, 0.7f).sound(SoundType.ROOTED_DIRT).randomTicks()));
	public static final RegistryObject<Block> HARDENED_MUD = BLOCKS.register("hardened_mud", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0f, 1.0f).sound(SoundType.ROOTED_DIRT)));
	public static final RegistryObject<StairBlock> HARDENED_MUD_STAIRS = BLOCKS.register("hardened_mud_stairs", () -> new StairBlock(() -> HARDENED_MUD.get().defaultBlockState(), BlockBehaviour.Properties.copy(BURNED_OAK_PLANKS.get())));
	public static final RegistryObject<SlabBlock> HARDENED_MUD_SLAB = BLOCKS.register("hardened_mud_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0f, 1.0f).sound(SoundType.ROOTED_DIRT)));
	public static final RegistryObject<Block> HARDENED_MUD_WINDOW = BLOCKS.register("hardened_mud_window", () -> new HardenedMudWindowBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2.0f, 1.0f).sound(SoundType.ROOTED_DIRT).noOcclusion()));
	// Stone tier
	public static final RegistryObject<PunjiSticksBlock> PUNJI_STICKS = BLOCKS.register("punji_sticks", () -> new PunjiSticksBlock(BlockBehaviour.Properties.of(Material.BAMBOO).strength(5.0f, 1.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()));

	// Breakable without tool
	public static final RegistryObject<GlassBlock> BULLETPROOF_GLASS = BLOCKS.register("bulletproof_glass", () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> WHITE_STAINED_BULLETPROOF_GLASS = BLOCKS.register("white_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.WHITE, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.WHITE).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> LIGHT_GRAY_STAINED_BULLETPROOF_GLASS = BLOCKS.register("light_gray_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.LIGHT_GRAY).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> GRAY_STAINED_BULLETPROOF_GLASS = BLOCKS.register("gray_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.GRAY, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.GRAY).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> BLACK_STAINED_BULLETPROOF_GLASS = BLOCKS.register("black_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.BLACK, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.BLACK).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> ORANGE_STAINED_BULLETPROOF_GLASS = BLOCKS.register("orange_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.ORANGE, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.ORANGE).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> MAGENTA_STAINED_BULLETPROOF_GLASS = BLOCKS.register("magenta_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.MAGENTA, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.MAGENTA).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> LIGHT_BLUE_STAINED_BULLETPROOF_GLASS = BLOCKS.register("light_blue_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.LIGHT_BLUE).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> YELLOW_STAINED_BULLETPROOF_GLASS = BLOCKS.register("yellow_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.YELLOW, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.YELLOW).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> LIME_STAINED_BULLETPROOF_GLASS = BLOCKS.register("lime_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.LIME, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.LIME).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> PINK_STAINED_BULLETPROOF_GLASS = BLOCKS.register("pink_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.PINK, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.PINK).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> CYAN_STAINED_BULLETPROOF_GLASS = BLOCKS.register("cyan_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.CYAN, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.CYAN).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> PURPLE_STAINED_BULLETPROOF_GLASS = BLOCKS.register("purple_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.PURPLE, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.PURPLE).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> BLUE_STAINED_BULLETPROOF_GLASS = BLOCKS.register("blue_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.BLUE, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.BLUE).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> BROWN_STAINED_BULLETPROOF_GLASS = BLOCKS.register("brown_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.BROWN, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.BROWN).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> GREEN_STAINED_BULLETPROOF_GLASS = BLOCKS.register("green_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.GREEN, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.GREEN).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<StainedGlassBlock> RED_STAINED_BULLETPROOF_GLASS = BLOCKS.register("red_stained_bulletproof_glass", () -> GeneralUtilities.createStainedGlassFromColor(DyeColor.RED, BlockBehaviour.Properties.of(Material.GLASS, DyeColor.RED).sound(SoundType.GLASS).noOcclusion().strength(0.5f)));
	public static final RegistryObject<PitfallBlock> PITFALL = BLOCKS.register("pitfall", () -> new PitfallBlock(BlockBehaviour.Properties.of(Material.GRASS).strength(0.2f, 1.0f).sound(SoundType.GRAVEL).randomTicks()));
	public static final RegistryObject<LandmineBlock> LANDMINE = BLOCKS.register("landmine", () -> new LandmineBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0F).sound(SoundType.METAL)));
	public static final RegistryObject<MinutemanStatueBlock> MINUTEMAN_STATUE = BLOCKS.register("minuteman_statue", () -> new MinutemanStatueBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5.0f).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<MedicStatueBlock> MEDIC_STATUE = BLOCKS.register("medic_statue", () -> new MedicStatueBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5.0f).sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<ShelfBlock> WALL_SHELF = BLOCKS.register("wall_shelf", () -> new ShelfBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.0f).sound(SoundType.WOOD).noOcclusion().noCollission()));
	public static final RegistryObject<PanicAlarmBlock> PANIC_ALARM = BLOCKS.register("panic_alarm", () -> new PanicAlarmBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(1.0f).sound(SoundType.WOOD).noOcclusion().randomTicks()));
	public static final RegistryObject<WoodenTableBlock> OAK_TABLE = BLOCKS.register("oak_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> SPRUCE_TABLE = BLOCKS.register("spruce_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> BIRCH_TABLE = BLOCKS.register("birch_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> JUNGLE_TABLE = BLOCKS.register("jungle_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> ACACIA_TABLE = BLOCKS.register("acacia_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> DARK_OAK_TABLE = BLOCKS.register("dark_oak_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> CRIMSON_TABLE = BLOCKS.register("crimson_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> WARPED_TABLE = BLOCKS.register("warped_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<WoodenTableBlock> BURNED_OAK_TABLE = BLOCKS.register("burned_oak_table", () -> new WoodenTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<BiohazardBoxBlock> BIOHAZARD_BOX = BLOCKS.register("biohazard_box", () -> new BiohazardBoxBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).sound(SoundType.LANTERN).noOcclusion()));
	public static final RegistryObject<HalfTransparentBlock> CLOUD = BLOCKS.register("cloud", () -> new HalfTransparentBlock(BlockBehaviour.Properties.of(Material.STRUCTURAL_AIR).strength(0.7f).sound(SoundType.SNOW).noOcclusion()));
	public static final RegistryObject<CampChairBlock> CAMP_CHAIR = BLOCKS.register("camp_chair", () -> new CampChairBlock(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).strength(1.0f).sound(SoundType.WOOL).noOcclusion()));
	public static final RegistryObject<BranchBlock> BURNED_OAK_BRANCH = BLOCKS.register("burned_oak_branch", () -> new BranchBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.1f).sound(SoundType.WOOD).noOcclusion().noCollission()));
	public static final RegistryObject<AzulStainedOrchidBlock> AZUL_STAINED_ORCHID = BLOCKS.register("azul_stained_orchid", () -> new AzulStainedOrchidBlock(MobEffects.LUCK, 30, BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().instabreak().offsetType(OffsetType.NONE)));
	public static final RegistryObject<SkullBlock> MINUTEMAN_HEAD = BLOCKS.register("minuteman_head", () -> new CustomSkullBlock(CustomSkullTypes.MINUTEMAN, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f)));
	public static final RegistryObject<CustomWallSkullBlock> MINUTEMAN_WALL_HEAD = BLOCKS.register("minuteman_wall_head", () -> new CustomWallSkullBlock(CustomSkullTypes.MINUTEMAN, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f).lootFrom(MINUTEMAN_HEAD)));
	public static final RegistryObject<SkullBlock> FIELD_MEDIC_HEAD = BLOCKS.register("field_medic_head", () -> new CustomSkullBlock(CustomSkullTypes.FIELD_MEDIC, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f)));
	public static final RegistryObject<CustomWallSkullBlock> FIELD_MEDIC_WALL_HEAD = BLOCKS.register("field_medic_wall_head", () -> new CustomWallSkullBlock(CustomSkullTypes.FIELD_MEDIC, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f).lootFrom(FIELD_MEDIC_HEAD)));
	public static final RegistryObject<SkullBlock> DYING_SOLDIER_HEAD = BLOCKS.register("dying_soldier_head", () -> new CustomSkullBlock(CustomSkullTypes.DYING_SOLDIER, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f)));
	public static final RegistryObject<CustomWallSkullBlock> DYING_SOLDIER_WALL_HEAD = BLOCKS.register("dying_soldier_wall_head", () -> new CustomWallSkullBlock(CustomSkullTypes.DYING_SOLDIER, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f).lootFrom(DYING_SOLDIER_HEAD)));
	public static final RegistryObject<SkullBlock> WANDERING_WARRIOR_HEAD = BLOCKS.register("wandering_warrior_head", () -> new CustomSkullBlock(CustomSkullTypes.WANDERING_WARRIOR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f)));
	public static final RegistryObject<CustomWallSkullBlock> WANDERING_WARRIOR_WALL_HEAD = BLOCKS.register("wandering_warrior_wall_head", () -> new CustomWallSkullBlock(CustomSkullTypes.WANDERING_WARRIOR, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f).lootFrom(WANDERING_WARRIOR_HEAD)));
	public static final RegistryObject<SkullBlock> HANS_HEAD = BLOCKS.register("hans_head", () -> new CustomSkullBlock(CustomSkullTypes.HANS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f)));
	public static final RegistryObject<CustomWallSkullBlock> HANS_WALL_HEAD = BLOCKS.register("hans_wall_head", () -> new CustomWallSkullBlock(CustomSkullTypes.HANS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f).lootFrom(HANS_HEAD)));

	// Entities
	public static final RegistryObject<EntityType<WoodenArrowEntity>> WOODEN_ARROW_ENTITY = ENTITY_TYPES.register("wooden_arrow", () -> EntityType.Builder.<WoodenArrowEntity> of(WoodenArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wood_arrow").toString()));
	public static final RegistryObject<EntityType<StoneArrowEntity>> STONE_ARROW_ENTITY = ENTITY_TYPES.register("stone_arrow", () -> EntityType.Builder.<StoneArrowEntity> of(StoneArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "stone_arrow").toString()));
	public static final RegistryObject<EntityType<GoldenArrowEntity>> GOLDEN_ARROW_ENTITY = ENTITY_TYPES.register("golden_arrow", () -> EntityType.Builder.<GoldenArrowEntity> of(GoldenArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "gold_arrow").toString()));
	public static final RegistryObject<EntityType<CopperArrowEntity>> COPPER_ARROW_ENTITY = ENTITY_TYPES.register("copper_arrow", () -> EntityType.Builder.<CopperArrowEntity> of(CopperArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_arrow").toString()));
	public static final RegistryObject<EntityType<IronArrowEntity>> IRON_ARROW_ENTITY = ENTITY_TYPES.register("iron_arrow", () -> EntityType.Builder.<IronArrowEntity> of(IronArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "iron_arrow").toString()));
	public static final RegistryObject<EntityType<CobaltArrowEntity>> COBALT_ARROW_ENTITY = ENTITY_TYPES.register("cobalt_arrow", () -> EntityType.Builder.<CobaltArrowEntity> of(CobaltArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_arrow").toString()));
	public static final RegistryObject<EntityType<DiamondArrowEntity>> DIAMOND_ARROW_ENTITY = ENTITY_TYPES.register("diamond_arrow", () -> EntityType.Builder.<DiamondArrowEntity> of(DiamondArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "diamond_arrow").toString()));
	public static final RegistryObject<EntityType<NetheriteArrowEntity>> NETHERITE_ARROW_ENTITY = ENTITY_TYPES.register("netherite_arrow", () -> EntityType.Builder.<NetheriteArrowEntity> of(NetheriteArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "netherite_arrow").toString()));
	public static final RegistryObject<EntityType<SmokeGrenadeArrowEntity>> SMOKE_GRENADE_ARROW_ENTITY = ENTITY_TYPES.register("smoke_grenade_arrow", () -> EntityType.Builder.<SmokeGrenadeArrowEntity> of(SmokeGrenadeArrowEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "smoke_grenade_arrow").toString()));
	public static final RegistryObject<EntityType<WoodenMusketBallEntity>> WOODEN_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("wooden_musket_ball", () -> EntityType.Builder.<WoodenMusketBallEntity> of((type, world) -> new WoodenMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wood_musket_ball").toString()));
	public static final RegistryObject<EntityType<StoneMusketBallEntity>> STONE_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("stone_musket_ball", () -> EntityType.Builder.<StoneMusketBallEntity> of((type, world) -> new StoneMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "stone_musket_ball").toString()));
	public static final RegistryObject<EntityType<GoldenMusketBallEntity>> GOLDEN_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("golden_musket_ball", () -> EntityType.Builder.<GoldenMusketBallEntity> of((type, world) -> new GoldenMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "gold_musket_ball").toString()));
	public static final RegistryObject<EntityType<CopperMusketBallEntity>> COPPER_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("copper_musket_ball", () -> EntityType.Builder.<CopperMusketBallEntity> of((type, world) -> new CopperMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_musket_ball").toString()));
	public static final RegistryObject<EntityType<IronMusketBallEntity>> IRON_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("iron_musket_ball", () -> EntityType.Builder.<IronMusketBallEntity> of((type, world) -> new IronMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "iron_musket_ball").toString()));
	public static final RegistryObject<EntityType<CobaltMusketBallEntity>> COBALT_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("cobalt_musket_ball", () -> EntityType.Builder.<CobaltMusketBallEntity> of((type, world) -> new CobaltMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_musket_ball").toString()));
	public static final RegistryObject<EntityType<DiamondMusketBallEntity>> DIAMOND_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("diamond_musket_ball", () -> EntityType.Builder.<DiamondMusketBallEntity> of((type, world) -> new DiamondMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "diamond_musket_ball").toString()));
	public static final RegistryObject<EntityType<NetheriteMusketBallEntity>> NETHERITE_MUSKET_BALL_ENTITY = ENTITY_TYPES.register("netherite_musket_ball", () -> EntityType.Builder.<NetheriteMusketBallEntity> of((type, world) -> new NetheriteMusketBallEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "netherite_musket_ball").toString()));
	public static final RegistryObject<EntityType<FlareEntity>> FLARE_ENTITY = ENTITY_TYPES.register("flare", () -> EntityType.Builder.<FlareEntity> of((type, world) -> new FlareEntity(type, world, 0), MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "flare").toString()));
	public static final RegistryObject<EntityType<SmokeGrenadeEntity>> SMOKE_GRENADE_ENTITY = ENTITY_TYPES.register("smoke_grenade", () -> EntityType.Builder.<SmokeGrenadeEntity> of(SmokeGrenadeEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "smoke_grenade").toString()));
	public static final RegistryObject<EntityType<MolotovEntity>> MOLOTOV_COCKTAIL_ENTITY = ENTITY_TYPES.register("molotov_cocktail", () -> EntityType.Builder.<MolotovEntity> of(MolotovEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "molotov_cocktail").toString()));
	public static final RegistryObject<EntityType<DyingSoldierEntity>> DYING_SOLDIER_ENTITY = ENTITY_TYPES.register("dying_soldier", () -> EntityType.Builder.of(DyingSoldierEntity::new, MobCategory.MONSTER).sized(0.6f, 1.99f).clientTrackingRange(8).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "dying_soldier").toString()));
	public static final RegistryObject<EntityType<MinutemanEntity>> MINUTEMAN_ENTITY = ENTITY_TYPES.register("minuteman", () -> EntityType.Builder.of(MinutemanEntity::new, MobCategory.CREATURE).sized(0.6f, 1.99f).clientTrackingRange(16).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "minuteman").toString()));
	public static final RegistryObject<EntityType<FieldMedicEntity>> FIELD_MEDIC_ENTITY = ENTITY_TYPES.register("field_medic", () -> EntityType.Builder.of(FieldMedicEntity::new, MobCategory.CREATURE).sized(0.6f, 1.99f).clientTrackingRange(16).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "field_medic").toString()));
	public static final RegistryObject<EntityType<ChairEntity>> CHAIR_ENTITY = ENTITY_TYPES.register("chair", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).sized(0.0f, 0.0f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "chair").toString()));
	public static final RegistryObject<EntityType<WanderingWarriorEntity>> WANDERING_WARRIOR_ENTITY = ENTITY_TYPES.register("wandering_warrior", () -> EntityType.Builder.of(WanderingWarriorEntity::new, MobCategory.MONSTER).sized(0.6f, 1.99f).clientTrackingRange(16).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wandering_warrior").toString()));
	public static final RegistryObject<EntityType<HansEntity>> HANS_ENTITY = ENTITY_TYPES.register("hans", () -> EntityType.Builder.of(HansEntity::new, MobCategory.MONSTER).sized(0.6f, 1.99f).clientTrackingRange(16).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "hans").toString()));
	public static final RegistryObject<EntityType<MortarShellEntity>> MORTAR_SHELL_ENTITY = ENTITY_TYPES.register("mortar_shell", () -> EntityType.Builder.of(MortarShellEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "mortar_shell").toString()));
	public static final RegistryObject<EntityType<BurnedOakBoatEntity>> BURNED_OAK_BOAT_ENTITY = ENTITY_TYPES.register("burned_oak_boat", () -> EntityType.Builder.<BurnedOakBoatEntity> of(BurnedOakBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "burned_oak_boat").toString()));
	public static final RegistryObject<EntityType<BurnedOakChestBoat>> BURNED_OAK_CHEST_BOAT_ENTITY = ENTITY_TYPES.register("burned_oak_chest_boat", () -> EntityType.Builder.<BurnedOakChestBoat> of(BurnedOakChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "burned_oak_chest_boat").toString()));
	public static final RegistryObject<EntityType<MudBallEntity>> MUD_BALL_ENTITY = ENTITY_TYPES.register("mud_ball", () -> EntityType.Builder.<MudBallEntity> of(MudBallEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "mud_ball").toString()));
	public static final RegistryObject<EntityType<LavaRevenantEntity>> LAVA_REVENANT_ENTITY = ENTITY_TYPES.register("lava_revenant", () -> EntityType.Builder.of(LavaRevenantEntity::new, MobCategory.MONSTER).sized(16.0f, 6.0f).clientTrackingRange(32).fireImmune().build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant").toString()));
	public static final RegistryObject<EntityType<RockSpiderEntity>> ROCK_SPIDER_ENTITY = ENTITY_TYPES.register("rock_spider", () -> EntityType.Builder.of(RockSpiderEntity::new, MobCategory.MONSTER).sized(0.30f, 0.30f).clientTrackingRange(16).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "rock_spider").toString()));
	public static final RegistryObject<EntityType<CelestialTowerEntity>> CELESTIAL_TOWER_ENTITY = ENTITY_TYPES.register("celestial_tower", () -> EntityType.Builder.of(CelestialTowerEntity::new, MobCategory.MONSTER).sized(8.0f, 9.0f).clientTrackingRange(32).build(new ResourceLocation(ImmersiveWeapons.MOD_ID, "celestial_tower").toString()));

	// Block Items
	public static final RegistryObject<BlockItem> MOLTEN_ORE_ITEM = ITEMS.register("molten_ore", () -> new BlockItem(MOLTEN_ORE.get(), new Properties().tab(ITEM_GROUP).fireResistant()));
	public static final RegistryObject<BlockItem> ELECTRIC_ORE_ITEM = ITEMS.register("electric_ore", () -> new BlockItem(ELECTRIC_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> COBALT_ORE_ITEM = ITEMS.register("cobalt_ore", () -> new BlockItem(COBALT_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> DEEPSLATE_COBALT_ORE_ITEM = ITEMS.register("deepslate_cobalt_ore", () -> new BlockItem(DEEPSLATE_COBALT_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> VENTUS_ORE_ITEM = ITEMS.register("ventus_ore", () -> new BlockItem(VENTUS_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> SULFUR_ORE_ITEM = ITEMS.register("sulfur_ore", () -> new BlockItem(SULFUR_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> DEEPSLATE_SULFUR_ORE_ITEM = ITEMS.register("deepslate_sulfur_ore", () -> new BlockItem(DEEPSLATE_SULFUR_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> NETHER_SULFUR_ORE_ITEM = ITEMS.register("nether_sulfur_ore", () -> new BlockItem(NETHER_SULFUR_ORE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> RAW_SULFUR_BLOCK_ITEM = ITEMS.register("raw_sulfur_block", () -> new BlockItem(RAW_SULFUR_BLOCK.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MOLTEN_BLOCK_ITEM = ITEMS.register("molten_block", () -> new BlockItem(MOLTEN_BLOCK.get(), new Properties().tab(ITEM_GROUP).fireResistant()));
	public static final RegistryObject<BlockItem> TESLA_BLOCK_ITEM = ITEMS.register("tesla_block", () -> new BlockItem(TESLA_BLOCK.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> COBALT_BLOCK_ITEM = ITEMS.register("cobalt_block", () -> new BlockItem(COBALT_BLOCK.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> RAW_COBALT_BLOCK_ITEM = ITEMS.register("raw_cobalt_block", () -> new BlockItem(RAW_COBALT_BLOCK.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> SMALL_PARTS_TABLE_ITEM = ITEMS.register("small_parts_table", () -> new BlockItem(SMALL_PARTS_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BARREL_TAP_ITEM = ITEMS.register("barrel_tap", () -> new BlockItem(BARREL_TAP.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BULLETPROOF_GLASS_ITEM = ITEMS.register("bulletproof_glass", () -> new BlockItem(BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WHITE_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("white_stained_bulletproof_glass", () -> new BlockItem(WHITE_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> LIGHT_GRAY_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("light_gray_stained_bulletproof_glass", () -> new BlockItem(LIGHT_GRAY_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> GRAY_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("gray_stained_bulletproof_glass", () -> new BlockItem(GRAY_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BLACK_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("black_stained_bulletproof_glass", () -> new BlockItem(BLACK_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> ORANGE_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("orange_stained_bulletproof_glass", () -> new BlockItem(ORANGE_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MAGENTA_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("magenta_stained_bulletproof_glass", () -> new BlockItem(MAGENTA_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> LIGHT_BLUE_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("light_blue_stained_bulletproof_glass", () -> new BlockItem(LIGHT_BLUE_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> YELLOW_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("yellow_stained_bulletproof_glass", () -> new BlockItem(YELLOW_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> LIME_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("lime_stained_bulletproof_glass", () -> new BlockItem(LIME_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> PINK_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("pink_stained_bulletproof_glass", () -> new BlockItem(PINK_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CYAN_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("cyan_stained_bulletproof_glass", () -> new BlockItem(CYAN_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> PURPLE_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("purple_stained_bulletproof_glass", () -> new BlockItem(PURPLE_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BLUE_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("blue_stained_bulletproof_glass", () -> new BlockItem(BLUE_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BROWN_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("brown_stained_bulletproof_glass", () -> new BlockItem(BROWN_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> GREEN_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("green_stained_bulletproof_glass", () -> new BlockItem(GREEN_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> RED_STAINED_BULLETPROOF_GLASS_ITEM = ITEMS.register("red_stained_bulletproof_glass", () -> new BlockItem(RED_STAINED_BULLETPROOF_GLASS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> PUNJI_STICKS_ITEM = ITEMS.register("punji_sticks", () -> new BlockItem(PUNJI_STICKS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> PITFALL_ITEM = ITEMS.register("pitfall", () -> new BlockItem(PITFALL.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BEAR_TRAP_ITEM = ITEMS.register("bear_trap", () -> new BlockItem(BEAR_TRAP.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BARBED_WIRE_ITEM = ITEMS.register("barbed_wire", () -> new BlockItem(BARBED_WIRE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> LANDMINE_ITEM = ITEMS.register("landmine", () -> new BlockItem(LANDMINE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> SPIKE_TRAP_ITEM = ITEMS.register("spike_trap", () -> new BlockItem(SPIKE_TRAP.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> SANDBAG_ITEM = ITEMS.register("sandbag", () -> new BlockItem(SANDBAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CORRUGATED_IRON_PANEL_ITEM = ITEMS.register("corrugated_iron_panel", () -> new BlockItem(CORRUGATED_IRON_PANEL.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CORRUGATED_IRON_PANEL_BARS_ITEM = ITEMS.register("corrugated_iron_panel_bars", () -> new BlockItem(CORRUGATED_IRON_PANEL_BARS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CORRUGATED_IRON_PANEL_FLAT_ITEM = ITEMS.register("corrugated_iron_panel_flat", () -> new BlockItem(CORRUGATED_IRON_PANEL_FLAT.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CORRUGATED_IRON_PANEL_FLAT_BARS_ITEM = ITEMS.register("corrugated_iron_panel_flat_bars", () -> new BlockItem(CORRUGATED_IRON_PANEL_FLAT_BARS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> SPOTLIGHT_ITEM = ITEMS.register("spotlight", () -> new BlockItem(SPOTLIGHT.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MORTAR_ITEM = ITEMS.register("mortar", () -> new BlockItem(MORTAR.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WALL_SHELF_ITEM = ITEMS.register("wall_shelf", () -> new BlockItem(WALL_SHELF.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> PANIC_ALARM_ITEM = ITEMS.register("panic_alarm", () -> new BlockItem(PANIC_ALARM.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> OAK_TABLE_ITEM = ITEMS.register("oak_table", () -> new BlockItem(OAK_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> SPRUCE_TABLE_ITEM = ITEMS.register("spruce_table", () -> new BlockItem(SPRUCE_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BIRCH_TABLE_ITEM = ITEMS.register("birch_table", () -> new BlockItem(BIRCH_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> JUNGLE_TABLE_ITEM = ITEMS.register("jungle_table", () -> new BlockItem(JUNGLE_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> ACACIA_TABLE_ITEM = ITEMS.register("acacia_table", () -> new BlockItem(ACACIA_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> DARK_OAK_TABLE_ITEM = ITEMS.register("dark_oak_table", () -> new BlockItem(DARK_OAK_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CRIMSON_TABLE_ITEM = ITEMS.register("crimson_table", () -> new BlockItem(CRIMSON_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WARPED_TABLE_ITEM = ITEMS.register("warped_table", () -> new BlockItem(WARPED_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_TABLE_ITEM = ITEMS.register("burned_oak_table", () -> new BlockItem(BURNED_OAK_TABLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CAMP_CHAIR_ITEM = ITEMS.register("camp_chair", () -> new BlockItem(CAMP_CHAIR.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BARBED_WIRE_FENCE_ITEM = ITEMS.register("barbed_wire_fence", () -> new BlockItem(BARBED_WIRE_FENCE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WOODEN_SPIKES_ITEM = ITEMS.register("wooden_spikes", () -> new BlockItem(WOODEN_SPIKES.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BIOHAZARD_BOX_ITEM = ITEMS.register("biohazard_box", () -> new BlockItem(BIOHAZARD_BOX.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MINUTEMAN_STATUE_ITEM = ITEMS.register("minuteman_statue", () -> new BlockItem(MINUTEMAN_STATUE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MEDIC_STATUE_ITEM = ITEMS.register("medic_statue", () -> new BlockItem(MEDIC_STATUE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> TESLA_SYNTHESIZER_ITEM = ITEMS.register("tesla_synthesizer", () -> new BlockItem(TESLA_SYNTHESIZER.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CLOUD_ITEM = ITEMS.register("cloud", () -> new BlockItem(CLOUD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CLOUD_MARBLE_ITEM = ITEMS.register("cloud_marble", () -> new BlockItem(CLOUD_MARBLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CLOUD_MARBLE_BRICKS_ITEM = ITEMS.register("cloud_marble_bricks", () -> new BlockItem(CLOUD_MARBLE_BRICKS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CLOUD_MARBLE_PILLAR_ITEM = ITEMS.register("cloud_marble_pillar", () -> new BlockItem(CLOUD_MARBLE_PILLAR.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CLOUD_MARBLE_BRICK_STAIRS_ITEM = ITEMS.register("cloud_marble_brick_stairs", () -> new BlockItem(CLOUD_MARBLE_BRICK_STAIRS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CLOUD_MARBLE_BRICK_SLAB_ITEM = ITEMS.register("cloud_marble_brick_slab", () -> new BlockItem(CLOUD_MARBLE_BRICK_SLAB.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_WOOD_ITEM = ITEMS.register("burned_oak_wood", () -> new BlockItem(BURNED_OAK_WOOD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_LOG_ITEM = ITEMS.register("burned_oak_log", () -> new BlockItem(BURNED_OAK_LOG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_BURNED_OAK_WOOD_ITEM = ITEMS.register("stripped_burned_oak_wood", () -> new BlockItem(STRIPPED_BURNED_OAK_WOOD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> STRIPPED_BURNED_OAK_LOG_ITEM = ITEMS.register("stripped_burned_oak_log", () -> new BlockItem(STRIPPED_BURNED_OAK_LOG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_PLANKS_ITEM = ITEMS.register("burned_oak_planks", () -> new BlockItem(BURNED_OAK_PLANKS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_STAIRS_ITEM = ITEMS.register("burned_oak_stairs", () -> new BlockItem(BURNED_OAK_STAIRS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_SLAB_ITEM = ITEMS.register("burned_oak_slab", () -> new BlockItem(BURNED_OAK_SLAB.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_FENCE_ITEM = ITEMS.register("burned_oak_fence", () -> new BlockItem(BURNED_OAK_FENCE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_FENCE_GATE_ITEM = ITEMS.register("burned_oak_fence_gate", () -> new BlockItem(BURNED_OAK_FENCE_GATE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_BRANCH_ITEM = ITEMS.register("burned_oak_branch", () -> new BlockItem(BURNED_OAK_BRANCH.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_DOOR_ITEM = ITEMS.register("burned_oak_door", () -> new BlockItem(BURNED_OAK_DOOR.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_TRAPDOOR_ITEM = ITEMS.register("burned_oak_trapdoor", () -> new BlockItem(BURNED_OAK_TRAPDOOR.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BURNED_OAK_PRESSURE_PLATE_ITEM = ITEMS.register("burned_oak_pressure_plate", () -> new BlockItem(BURNED_OAK_PRESSURE_PLATE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<SignItem> BURNED_OAK_SIGN_ITEM = ITEMS.register("burned_oak_sign", () -> new SignItem(new Properties().tab(ITEM_GROUP), BURNED_OAK_SIGN.get(), BURNED_OAK_WALL_SIGN.get()));
	public static final RegistryObject<BlockItem> BURNED_OAK_BUTTON_ITEM = ITEMS.register("burned_oak_button", () -> new BlockItem(BURNED_OAK_BUTTON.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> FLAG_POLE_ITEM = ITEMS.register("flag_pole", () -> new BlockItem(FLAG_POLE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> AMERICAN_FLAG_ITEM = ITEMS.register("american_flag", () -> new BlockItem(AMERICAN_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> GADSDEN_FLAG_ITEM = ITEMS.register("gadsden_flag", () -> new BlockItem(GADSDEN_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CANADIAN_FLAG_ITEM = ITEMS.register("canadian_flag", () -> new BlockItem(CANADIAN_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MEXICAN_FLAG_ITEM = ITEMS.register("mexican_flag", () -> new BlockItem(MEXICAN_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> BRITISH_FLAG_ITEM = ITEMS.register("british_flag", () -> new BlockItem(BRITISH_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> TROLL_FLAG_ITEM = ITEMS.register("troll_flag", () -> new BlockItem(TROLL_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> IMMERSIVE_WEAPONS_FLAG_ITEM = ITEMS.register("immersive_weapons_flag", () -> new BlockItem(IMMERSIVE_WEAPONS_FLAG.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MUD_ITEM = ITEMS.register("mud", () -> new BlockItem(MUD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> DRIED_MUD_ITEM = ITEMS.register("dried_mud", () -> new BlockItem(DRIED_MUD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> HARDENED_MUD_ITEM = ITEMS.register("hardened_mud", () -> new BlockItem(HARDENED_MUD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> HARDENED_MUD_STAIRS_ITEM = ITEMS.register("hardened_mud_stairs", () -> new BlockItem(HARDENED_MUD_STAIRS.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> HARDENED_MUD_SLAB_ITEM = ITEMS.register("hardened_mud_slab", () -> new BlockItem(HARDENED_MUD_SLAB.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> HARDENED_MUD_WINDOW_ITEM = ITEMS.register("hardened_mud_window", () -> new BlockItem(HARDENED_MUD_WINDOW.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WARRIOR_STATUE_BASE_ITEM = ITEMS.register("warrior_statue_base", () -> new BlockItem(WARRIOR_STATUE_BASE.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WARRIOR_STATUE_TORSO_ITEM = ITEMS.register("warrior_statue_torso", () -> new BlockItem(WARRIOR_STATUE_TORSO.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WARRIOR_STATUE_HEAD_ITEM = ITEMS.register("warrior_statue_head", () -> new BlockItem(WARRIOR_STATUE_HEAD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> AZUL_STAINED_ORCHID_ITEM = ITEMS.register("azul_stained_orchid", () -> new BlockItem(AZUL_STAINED_ORCHID.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> CELESTIAL_LANTERN_ITEM = ITEMS.register("celestial_lantern", () -> new BlockItem(CELESTIAL_LANTERN.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> MINUTEMAN_HEAD_ITEM = ITEMS.register("minuteman_head", () -> new StandingAndWallBlockItem(MINUTEMAN_HEAD.get(), MINUTEMAN_WALL_HEAD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> FIELD_MEDIC_HEAD_ITEM = ITEMS.register("field_medic_head", () -> new StandingAndWallBlockItem(FIELD_MEDIC_HEAD.get(), FIELD_MEDIC_WALL_HEAD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> DYING_SOLDIER_HEAD_ITEM = ITEMS.register("dying_soldier_head", () -> new StandingAndWallBlockItem(DYING_SOLDIER_HEAD.get(), DYING_SOLDIER_WALL_HEAD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> WANDERING_WARRIOR_HEAD_ITEM = ITEMS.register("wandering_warrior_head", () -> new StandingAndWallBlockItem(WANDERING_WARRIOR_HEAD.get(), WANDERING_WARRIOR_WALL_HEAD.get(), new Properties().tab(ITEM_GROUP)));
	public static final RegistryObject<BlockItem> HANS_HEAD_ITEM = ITEMS.register("hans_head", () -> new StandingAndWallBlockItem(HANS_HEAD.get(), HANS_WALL_HEAD.get(), new Properties().tab(ITEM_GROUP)));

	// Sounds
	public static final RegistryObject<SoundEvent> TESLA_ARMOR_EFFECT = SOUND_EVENTS.register("tesla_armor_effect", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_armor_effect")));
	public static final RegistryObject<SoundEvent> TESLA_ARMOR_POWER_DOWN = SOUND_EVENTS.register("tesla_armor_power_down", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_armor_power_down")));
	public static final RegistryObject<SoundEvent> TESLA_ARMOR_POWER_UP = SOUND_EVENTS.register("tesla_armor_power_up", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_armor_power_up")));
	public static final RegistryObject<SoundEvent> TESLA_ARMOR_EQUIP = SOUND_EVENTS.register("tesla_armor_equip", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_armor_equip")));
	public static final RegistryObject<SoundEvent> MOLTEN_ARMOR_EQUIP = SOUND_EVENTS.register("molten_armor_equip", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "molten_armor_equip")));
	public static final RegistryObject<SoundEvent> VENTUS_ARMOR_EQUIP = SOUND_EVENTS.register("ventus_armor_equip", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "ventus_armor_equip")));
	public static final RegistryObject<SoundEvent> COPPER_ARMOR_EQUIP = SOUND_EVENTS.register("copper_armor_equip", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_armor_equip")));
	public static final RegistryObject<SoundEvent> COBALT_ARMOR_EQUIP = SOUND_EVENTS.register("cobalt_armor_equip", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_armor_equip")));
	public static final RegistryObject<SoundEvent> FLINTLOCK_PISTOL_FIRE = SOUND_EVENTS.register("flintlock_pistol_fire", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "flintlock_pistol_fire")));
	public static final RegistryObject<SoundEvent> BULLET_WHIZZ = SOUND_EVENTS.register("bullet_whizz", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "bullet_whizz")));
	public static final RegistryObject<SoundEvent> FLINTLOCK_PISTOL_MISFIRE = SOUND_EVENTS.register("flintlock_pistol_misfire", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "flintlock_pistol_misfire")));
	public static final RegistryObject<SoundEvent> SMALL_PARTS_TABLE_USED = SOUND_EVENTS.register("small_parts_table_used", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "small_parts_table_used")));
	public static final RegistryObject<SoundEvent> SMOKE_GRENADE_HISS = SOUND_EVENTS.register("smoke_grenade_hiss", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "smoke_grenade_hiss")));
	public static final RegistryObject<SoundEvent> GENERIC_WHOOSH = SOUND_EVENTS.register("generic_whoosh", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "generic_whoosh")));
	public static final RegistryObject<SoundEvent> BLUNDERBUSS_FIRE = SOUND_EVENTS.register("blunderbuss_fire", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "blunderbuss_fire")));
	public static final RegistryObject<SoundEvent> BARBED_WIRE_RATTLE = SOUND_EVENTS.register("barbed_wire_rattle", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "barbed_wire_rattle")));
	public static final RegistryObject<SoundEvent> BEAR_TRAP_CLOSE = SOUND_EVENTS.register("bear_trap_close", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "bear_trap_close")));
	public static final RegistryObject<SoundEvent> SPIKE_TRAP_EXTEND = SOUND_EVENTS.register("spike_trap_extend", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "spike_trap_extend")));
	public static final RegistryObject<SoundEvent> SPIKE_TRAP_RETRACT = SOUND_EVENTS.register("spike_trap_retract", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "spike_trap_retract")));
	public static final RegistryObject<SoundEvent> PANIC_ALARM_SOUND = SOUND_EVENTS.register("panic_alarm", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "panic_alarm")));
	public static final RegistryObject<SoundEvent> DYING_SOLDIER_AMBIENT = SOUND_EVENTS.register("dying_soldier_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "dying_soldier_ambient")));
	public static final RegistryObject<SoundEvent> DYING_SOLDIER_STEP = SOUND_EVENTS.register("dying_soldier_step", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "dying_soldier_step")));
	public static final RegistryObject<SoundEvent> DYING_SOLDIER_DEATH = SOUND_EVENTS.register("dying_soldier_death", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "dying_soldier_death")));
	public static final RegistryObject<SoundEvent> DYING_SOLDIER_HURT = SOUND_EVENTS.register("dying_soldier_hurt", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "dying_soldier_hurt")));
	public static final RegistryObject<SoundEvent> BATTLEFIELD_AMBIENT = SOUND_EVENTS.register("battlefield_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "battlefield_ambient")));
	public static final RegistryObject<SoundEvent> FIELD_MEDIC_ATTACK = SOUND_EVENTS.register("field_medic_attack", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "field_medic_attack")));
	public static final RegistryObject<SoundEvent> FIELD_MEDIC_AMBIENT = SOUND_EVENTS.register("field_medic_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "field_medic_ambient")));
	public static final RegistryObject<SoundEvent> FIELD_MEDIC_HURT = SOUND_EVENTS.register("field_medic_hurt", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "field_medic_hurt")));
	public static final RegistryObject<SoundEvent> FIELD_MEDIC_DEATH = SOUND_EVENTS.register("field_medic_death", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "field_medic_death")));
	public static final RegistryObject<SoundEvent> FIELD_MEDIC_STEP = SOUND_EVENTS.register("field_medic_step", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "field_medic_step")));
	public static final RegistryObject<SoundEvent> FLARE_GUN_FIRE = SOUND_EVENTS.register("flare_gun_fire", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "flare_gun_fire")));
	public static final RegistryObject<SoundEvent> WANDERING_WARRIOR_AMBIENT = SOUND_EVENTS.register("wandering_warrior_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wandering_warrior_ambient")));
	public static final RegistryObject<SoundEvent> WANDERING_WARRIOR_HURT = SOUND_EVENTS.register("wandering_warrior_hurt", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wandering_warrior_hurt")));
	public static final RegistryObject<SoundEvent> WANDERING_WARRIOR_DEATH = SOUND_EVENTS.register("wandering_warrior_death", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wandering_warrior_death")));
	public static final RegistryObject<SoundEvent> WANDERING_WARRIOR_STEP = SOUND_EVENTS.register("wandering_warrior_step", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "wandering_warrior_step")));
	public static final RegistryObject<SoundEvent> TILTROS_AMBIENT = SOUND_EVENTS.register("tiltros_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "tiltros_ambient")));
	public static final RegistryObject<SoundEvent> LAVA_REVENANT_AMBIENT = SOUND_EVENTS.register("lava_revenant_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant_ambient")));
	public static final RegistryObject<SoundEvent> LAVA_REVENANT_HURT = SOUND_EVENTS.register("lava_revenant_hurt", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant_hurt")));
	public static final RegistryObject<SoundEvent> LAVA_REVENANT_DEATH = SOUND_EVENTS.register("lava_revenant_death", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant_death")));
	public static final RegistryObject<SoundEvent> LAVA_REVENANT_FLAP = SOUND_EVENTS.register("lava_revenant_flap", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant_flap")));
	public static final RegistryObject<SoundEvent> LAVA_REVENANT_SWOOP = SOUND_EVENTS.register("lava_revenant_swoop", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant_swoop")));
	public static final RegistryObject<SoundEvent> LAVA_REVENANT_BITE = SOUND_EVENTS.register("lava_revenant_bite", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "lava_revenant_bite")));
	public static final RegistryObject<SoundEvent> CELESTIAL_TOWER_AMBIENT = SOUND_EVENTS.register("celestial_tower_ambient", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "celestial_tower_ambient")));
	public static final RegistryObject<SoundEvent> CELESTIAL_TOWER_HURT = SOUND_EVENTS.register("celestial_tower_hurt", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "celestial_tower_hurt")));
	public static final RegistryObject<SoundEvent> CELESTIAL_TOWER_DEATH = SOUND_EVENTS.register("celestial_tower_death", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "celestial_tower_death")));
	public static final RegistryObject<SoundEvent> CELESTIAL_TOWER_SUMMON = SOUND_EVENTS.register("celestial_tower_summon", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "celestial_tower_summon")));
	public static final RegistryObject<SoundEvent> CELESTIAL_TOWER_VULNERABLE = SOUND_EVENTS.register("celestial_tower_vulnerable", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "celestial_tower_vulnerable")));
	public static final RegistryObject<SoundEvent> MORTAR_FIRE = SOUND_EVENTS.register("mortar_fire", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "mortar_fire")));
	public static final RegistryObject<SoundEvent> MUSKET_FIRE = SOUND_EVENTS.register("musket_fire", () -> new SoundEvent(new ResourceLocation(ImmersiveWeapons.MOD_ID, "musket_fire")));

	// Containers
	public static final RegistryObject<MenuType<SmallPartsContainer>> SMALL_PARTS_TABLE_CONTAINER = CONTAINER_TYPES.register("small_parts_table", () -> IForgeMenuType.create((id, inv, data) -> new SmallPartsContainer(id, inv)));
	public static final RegistryObject<MenuType<TeslaSynthesizerContainer>> TESLA_SYNTHESIZER_CONTAINER = CONTAINER_TYPES.register("tesla_synthesizer", () -> IForgeMenuType.create((id, inv, data) -> new TeslaSynthesizerContainer(id, inv)));

	// Recipes
	public static final RegistryObject<RecipeSerializer<SmallPartsRecipe>> SMALL_PARTS_RECIPE_SERIALIZER = RECIPE_SERIALIZER.register("small_parts", SmallPartsRecipe.Serializer::new);
	public static final RegistryObject<RecipeSerializer<TeslaSynthesizerRecipe>> TESLA_SYNTHESIZER_RECIPE_SERIALIZER = RECIPE_SERIALIZER.register("tesla_synthesizer", TeslaSynthesizerRecipe.Serializer::new);
	public static final RegistryObject<RecipeSerializer<BarrelTapRecipe>> BARREL_TAP_RECIPE_SERIALIZER = RECIPE_SERIALIZER.register("barrel_tap", BarrelTapRecipe.Serializer::new);

	// Recipe Types
	public static final RegistryObject<RecipeType<SmallPartsRecipe>> SMALL_PARTS_RECIPE_TYPE = RECIPE_TYPES.register("small_parts", () -> new RecipeType<>() {
		@Override
		public String toString() {
			return ImmersiveWeapons.MOD_ID + ":small_parts";
		}
	});
	public static final RegistryObject<RecipeType<TeslaSynthesizerRecipe>> TESLA_SYNTHESIZER_RECIPE_TYPE = RECIPE_TYPES.register("tesla_synthesizer", () -> new RecipeType<>() {
		@Override
		public String toString() {
			return ImmersiveWeapons.MOD_ID + ":tesla_synthesizer";
		}
	});
	public static final RegistryObject<RecipeType<BarrelTapRecipe>> BARREL_TAP_RECIPE_TYPE = RECIPE_TYPES.register("barrel_tap", () -> new RecipeType<>() {
		@Override
		public String toString() {
			return ImmersiveWeapons.MOD_ID + ":barrel_tap";
		}
	});

	// Loot Table Modifiers
	public static final RegistryObject<Codec<LogShardsLootModifierHandler>> WOOD_LOGS_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZER.register("log_shards", LogShardsLootModifierHandler.CODEC);
	public static final RegistryObject<Codec<AzulKeystoneFragmentInChestsLootModifierHandler>> AZUL_KEYSTONE_FRAGMENT_IN_CHESTS_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZER.register("azul_keystone_fragment_in_chests", AzulKeystoneFragmentInChestsLootModifierHandler.CODEC);

	// Particles
	public static final RegistryObject<ParticleType<SmokeGrenadeParticleOptions>> SMOKE_GRENADE_PARTICLE = PARTICLE_TYPES.register("smoke_grenade", () -> new ParticleType<>(false, SmokeGrenadeParticleOptions.DESERIALIZER) {
		final Function<ParticleType<SmokeGrenadeParticleOptions>, Codec<SmokeGrenadeParticleOptions>> codec = (type) -> SmokeGrenadeParticleOptions.CODEC;

		@Override
		public @NotNull Codec<SmokeGrenadeParticleOptions> codec() {
			return codec.apply(this);
		}
	});
	public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLE = PARTICLE_TYPES.register("blood", () -> new SimpleParticleType(false));
	public static final RegistryObject<ParticleType<BulletImpactParticleOptions>> BULLET_IMPACT_PARTICLE = PARTICLE_TYPES.register("bullet_impact", () -> new ParticleType<>(false, BulletImpactParticleOptions.DESERIALIZER) {
		final Function<ParticleType<BulletImpactParticleOptions>, Codec<BulletImpactParticleOptions>> codec = (type) -> BulletImpactParticleOptions.CODEC;

		@Override
		public @NotNull Codec<BulletImpactParticleOptions> codec() {
			return codec.apply(this);
		}
	});
	public static final RegistryObject<SimpleParticleType> MUZZLE_FLASH_PARTICLE = PARTICLE_TYPES.register("muzzle_flash", () -> new SimpleParticleType(false));

	// Block Entities
	public static final RegistryObject<BlockEntityType<BearTrapBlockEntity>> BEAR_TRAP_BLOCK_ENTITY = BLOCK_ENTITIES.register("bear_trap", () -> new BlockEntityType<>(BearTrapBlockEntity::new, Sets.newHashSet(BEAR_TRAP.get()), null));
	public static final RegistryObject<BlockEntityType<WallShelfBlockEntity>> WALL_SHELF_BLOCK_ENTITY = BLOCK_ENTITIES.register("wall_shelf", () -> new BlockEntityType<>(WallShelfBlockEntity::new, Sets.newHashSet(WALL_SHELF.get()), null));
	public static final RegistryObject<BlockEntityType<PanicAlarmBlockEntity>> PANIC_ALARM_BLOCK_ENTITY = BLOCK_ENTITIES.register("panic_alarm", () -> new BlockEntityType<>(PanicAlarmBlockEntity::new, Sets.newHashSet(PANIC_ALARM.get()), null));
	public static final RegistryObject<BlockEntityType<MinutemanStatueBlockEntity>> MINUTEMAN_STATUE_BLOCK_ENTITY = BLOCK_ENTITIES.register("minuteman_statue", () -> new BlockEntityType<>(MinutemanStatueBlockEntity::new, Sets.newHashSet(MINUTEMAN_STATUE.get()), null));
	public static final RegistryObject<BlockEntityType<MedicStatueBlockEntity>> MEDIC_STATUE_BLOCK_ENTITY = BLOCK_ENTITIES.register("medic_statue", () -> new BlockEntityType<>(MedicStatueBlockEntity::new, Sets.newHashSet(MEDIC_STATUE.get()), null));
	public static final RegistryObject<BlockEntityType<TeslaSynthesizerBlockEntity>> TESLA_SYNTHESIZER_BLOCK_ENTITY = BLOCK_ENTITIES.register("tesla_synthesizer", () -> new BlockEntityType<>(TeslaSynthesizerBlockEntity::new, Sets.newHashSet(TESLA_SYNTHESIZER.get()), null));
	public static final RegistryObject<BlockEntityType<BurnedOakSignEntity>> BURNED_OAK_SIGN_ENTITY = BLOCK_ENTITIES.register("custom_sign", () -> BlockEntityType.Builder.of(BurnedOakSignEntity::new, BURNED_OAK_SIGN.get(), BURNED_OAK_WALL_SIGN.get()).build(null));
	public static final RegistryObject<BlockEntityType<CelestialLanternBlockEntity>> CELESTIAL_LANTERN_BLOCK_ENTITY = BLOCK_ENTITIES.register("celestial_lantern", () -> new BlockEntityType<>(CelestialLanternBlockEntity::new, Sets.newHashSet(CELESTIAL_LANTERN.get()), null));
	public static final RegistryObject<BlockEntityType<CustomSkullBlockEntity>> CUSTOM_SKULL_BLOCK_ENTITY = BLOCK_ENTITIES.register("custom_skull", () -> BlockEntityType.Builder.of(CustomSkullBlockEntity::new, MINUTEMAN_HEAD.get(), MINUTEMAN_WALL_HEAD.get(), FIELD_MEDIC_HEAD.get(), FIELD_MEDIC_WALL_HEAD.get(), DYING_SOLDIER_HEAD.get(), DYING_SOLDIER_WALL_HEAD.get(), WANDERING_WARRIOR_HEAD.get(), WANDERING_WARRIOR_WALL_HEAD.get(), HANS_HEAD.get(), HANS_WALL_HEAD.get()).build(null));

	// Effects
	public static final RegistryObject<MorphineEffect> MORPHINE_EFFECT = EFFECTS.register("morphine", () -> new MorphineEffect(MobEffectCategory.NEUTRAL, 3484189));
	public static final RegistryObject<BleedingEffect> BLEEDING_EFFECT = EFFECTS.register("bleeding", () -> new BleedingEffect(MobEffectCategory.HARMFUL, 8392463));
	public static final RegistryObject<AlcoholEffect> ALCOHOL_EFFECT = EFFECTS.register("alcohol", () -> new AlcoholEffect(MobEffectCategory.NEUTRAL, 14465637));

	// Tree Decorators
	public static final RegistryObject<TreeDecoratorType<BurnedBranchDecorator>> BURNED_BRANCH_DECORATOR = TREE_DECORATORS.register("burned_branch", () -> new TreeDecoratorType<>(BurnedBranchDecorator.CODEC));
}