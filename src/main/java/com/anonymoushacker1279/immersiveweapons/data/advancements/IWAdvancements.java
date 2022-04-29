package com.anonymoushacker1279.immersiveweapons.data.advancements;

import com.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import com.anonymoushacker1279.immersiveweapons.advancement.EntityDiscoveredTrigger;
import com.anonymoushacker1279.immersiveweapons.advancement.WarriorStatueActivatedTrigger;
import com.anonymoushacker1279.immersiveweapons.data.tags.groups.immersiveweapons.ImmersiveWeaponsItemTagGroups;
import com.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import com.anonymoushacker1279.immersiveweapons.world.level.levelgen.biomes.BiomesAndDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.*;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.critereon.*;
import net.minecraft.advancements.critereon.EntityPredicate.Composite;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class IWAdvancements implements Consumer<Consumer<Advancement>> {

	IWAdvancements() {
	}

	/**
	 * Build advancement trees.
	 *
	 * @param consumer the <code>Consumer</code> extending Advancement
	 */
	@Override
	public void accept(Consumer<Advancement> consumer) {
		// Root advancement
		Advancement root = Builder.advancement()
				.display(DeferredRegistryHandler.TESLA_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.root.title")
								.withStyle(ChatFormatting.RED),
						new TranslatableComponent("advancements.immersiveweapons.root.description"),
						new ResourceLocation(ImmersiveWeapons.MOD_ID, "textures/block/red_stained_bulletproof_glass.png"),
						FrameType.TASK, false, false, false)
				.addCriterion("exist",
						LocationTrigger.TriggerInstance.located(LocationPredicate.inDimension(Level.OVERWORLD)))
				.rewards(AdvancementRewards.Builder.loot(new ResourceLocation(ImmersiveWeapons.MOD_ID, "grant_encyclopedia_book_on_first_join")))
				.save(consumer, "immersiveweapons:root");

		// Molten advancements
		Advancement obtainMoltenShard = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.MOLTEN_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_shard.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_shard.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_SHARD.get()))
				.save(consumer, "immersiveweapons:molten_shard");

		Advancement smeltMoltenIngot = Builder.advancement().parent(obtainMoltenShard)
				.display(DeferredRegistryHandler.MOLTEN_INGOT.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_INGOT.get()))
				.save(consumer, "immersiveweapons:molten_ingot");

		Builder.advancement().parent(smeltMoltenIngot)
				.display(DeferredRegistryHandler.MOLTEN_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_sword.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_sword.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_SWORD.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:molten_sword");

		Builder.advancement().parent(smeltMoltenIngot)
				.display(DeferredRegistryHandler.MOLTEN_PICKAXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_pickaxe.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_pickaxe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_PICKAXE.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:molten_pickaxe");

		Builder.advancement().parent(smeltMoltenIngot)
				.display(DeferredRegistryHandler.MOLTEN_AXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_axe.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_axe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_AXE.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:molten_axe");

		Builder.advancement().parent(smeltMoltenIngot)
				.display(DeferredRegistryHandler.MOLTEN_SHOVEL.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_shovel.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_shovel.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_SHOVEL.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:molten_shovel");

		Builder.advancement().parent(smeltMoltenIngot)
				.display(DeferredRegistryHandler.MOLTEN_HOE.get(),
						new TranslatableComponent("advancements.immersiveweapons.molten_hoe.title"),
						new TranslatableComponent("advancements.immersiveweapons.molten_hoe.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_HOE.get()))
				.rewards(AdvancementRewards.Builder.experience(50))
				.save(consumer, "immersiveweapons:molten_hoe");

		Builder.advancement().parent(smeltMoltenIngot) // TODO: Tagged for advancement rename
				.display(DeferredRegistryHandler.MOLTEN_BLOCK_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.play_with_fire.title"),
						new TranslatableComponent("advancements.immersiveweapons.play_with_fire.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("have_advancements",
						LocationTrigger.TriggerInstance.located(
								EntityPredicate.Builder.entity().player(
										PlayerPredicate.Builder.player().checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "molten_sword"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "molten_pickaxe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "molten_axe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "molten_shovel"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "molten_hoe"), true
										).build()
								).build()
						)
				)
				.rewards(AdvancementRewards.Builder.experience(150))
				.save(consumer, "immersiveweapons:play_with_fire");

		Advancement warmAndToasty = Builder.advancement().parent(smeltMoltenIngot) // TODO: Tagged for advancement rename
				.display(DeferredRegistryHandler.MOLTEN_HELMET.get(),
						new TranslatableComponent("advancements.immersiveweapons.warm_and_toasty.title"),
						new TranslatableComponent("advancements.immersiveweapons.warm_and_toasty.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_HELMET.get(),
								DeferredRegistryHandler.MOLTEN_CHESTPLATE.get(), DeferredRegistryHandler.MOLTEN_LEGGINGS.get(),
								DeferredRegistryHandler.MOLTEN_BOOTS.get()))
				.rewards(AdvancementRewards.Builder.experience(100))
				.save(consumer, "immersiveweapons:warm_and_toasty");

		Builder.advancement().parent(warmAndToasty)
				.display(Items.LAVA_BUCKET,
						new TranslatableComponent("advancements.immersiveweapons.lava_bath.title"),
						new TranslatableComponent("advancements.immersiveweapons.lava_bath.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_HELMET.get(),
								DeferredRegistryHandler.MOLTEN_CHESTPLATE.get(), DeferredRegistryHandler.MOLTEN_LEGGINGS.get(),
								DeferredRegistryHandler.MOLTEN_BOOTS.get()))
				.addCriterion("swim", EnterBlockTrigger.TriggerInstance.entersBlock(Blocks.LAVA))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:lava_bath");

		// Tesla Advancements
		Advancement craftConductiveAlloy = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.CONDUCTIVE_ALLOY.get(),
						new TranslatableComponent("advancements.immersiveweapons.conductive_alloy.title"),
						new TranslatableComponent("advancements.immersiveweapons.conductive_alloy.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.CONDUCTIVE_ALLOY.get()))
				.save(consumer, "immersiveweapons:conductive_alloy");

		Advancement obtainElectricIngot = Builder.advancement().parent(craftConductiveAlloy)
				.display(DeferredRegistryHandler.ELECTRIC_INGOT.get(),
						new TranslatableComponent("advancements.immersiveweapons.electric_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.electric_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.ELECTRIC_INGOT.get()))
				.save(consumer, "immersiveweapons:electric_ingot");

		Advancement craftTeslaIngot = Builder.advancement().parent(obtainElectricIngot)
				.display(DeferredRegistryHandler.TESLA_INGOT.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_INGOT.get()))
				.save(consumer, "immersiveweapons:tesla_ingot");

		Builder.advancement().parent(craftTeslaIngot)
				.display(DeferredRegistryHandler.TESLA_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_sword.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_sword.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_SWORD.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:tesla_sword");

		Builder.advancement().parent(craftTeslaIngot)
				.display(DeferredRegistryHandler.TESLA_PICKAXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_pickaxe.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_pickaxe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_PICKAXE.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:tesla_pickaxe");

		Builder.advancement().parent(craftTeslaIngot)
				.display(DeferredRegistryHandler.TESLA_AXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_axe.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_axe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_AXE.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:tesla_axe");

		Builder.advancement().parent(craftTeslaIngot)
				.display(DeferredRegistryHandler.TESLA_SHOVEL.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_shovel.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_shovel.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_SHOVEL.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:tesla_shovel");

		Builder.advancement().parent(craftTeslaIngot)
				.display(DeferredRegistryHandler.TESLA_HOE.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_hoe.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_hoe.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_HOE.get()))
				.rewards(AdvancementRewards.Builder.experience(65))
				.save(consumer, "immersiveweapons:tesla_hoe");

		Builder.advancement().parent(craftTeslaIngot) // TODO: Tagged for advancement rename
				.display(DeferredRegistryHandler.TESLA_BLOCK_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.energized.title"),
						new TranslatableComponent("advancements.immersiveweapons.energized.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("have_advancements",
						LocationTrigger.TriggerInstance.located(
								EntityPredicate.Builder.entity().player(
										PlayerPredicate.Builder.player().checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_sword"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_pickaxe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_axe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_shovel"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "tesla_hoe"), true
										).build()
								).build()
						)
				)
				.rewards(AdvancementRewards.Builder.experience(150))
				.save(consumer, "immersiveweapons:energized");

		Builder.advancement().parent(craftTeslaIngot) // TODO: Tagged for advancement rename
				.display(DeferredRegistryHandler.TESLA_HELMET.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_coil.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_coil.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_HELMET.get(),
								DeferredRegistryHandler.TESLA_CHESTPLATE.get(), DeferredRegistryHandler.TESLA_LEGGINGS.get(),
								DeferredRegistryHandler.TESLA_BOOTS.get()))
				.rewards(AdvancementRewards.Builder.experience(100))
				.save(consumer, "immersiveweapons:tesla_coil");

		Builder.advancement().parent(craftTeslaIngot)
				.display(DeferredRegistryHandler.TESLA_SYNTHESIZER.get(),
						new TranslatableComponent("advancements.immersiveweapons.tesla_synthesizer.title"),
						new TranslatableComponent("advancements.immersiveweapons.tesla_synthesizer.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.TESLA_SYNTHESIZER.get()))
				.rewards(AdvancementRewards.Builder.experience(150))
				.save(consumer, "immersiveweapons:tesla_synthesizer");

		// Ventus Advancements
		Advancement obtainVentusShard = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.VENTUS_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_shard.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_shard.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_SHARD.get()))
				.save(consumer, "immersiveweapons:ventus_shard");

		Builder.advancement().parent(obtainVentusShard)
				.display(DeferredRegistryHandler.VENTUS_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_sword.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_sword.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_SWORD.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:ventus_sword");

		Builder.advancement().parent(obtainVentusShard)
				.display(DeferredRegistryHandler.VENTUS_PICKAXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_pickaxe.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_pickaxe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_PICKAXE.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:ventus_pickaxe");

		Builder.advancement().parent(obtainVentusShard)
				.display(DeferredRegistryHandler.VENTUS_AXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_axe.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_axe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_AXE.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:ventus_axe");

		Builder.advancement().parent(obtainVentusShard)
				.display(DeferredRegistryHandler.VENTUS_SHOVEL.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_shovel.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_shovel.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_SHOVEL.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:ventus_shovel");

		Builder.advancement().parent(obtainVentusShard)
				.display(DeferredRegistryHandler.VENTUS_HOE.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_hoe.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_hoe.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_HOE.get()))
				.rewards(AdvancementRewards.Builder.experience(50))
				.save(consumer, "immersiveweapons:ventus_hoe");

		Builder.advancement().parent(obtainVentusShard) // TODO: Tagged for advancement rename
				.display(DeferredRegistryHandler.VENTUS_ORE_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.pretty_windy.title"),
						new TranslatableComponent("advancements.immersiveweapons.pretty_windy.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("have_advancements",
						LocationTrigger.TriggerInstance.located(
								EntityPredicate.Builder.entity().player(
										PlayerPredicate.Builder.player().checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "ventus_sword"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "ventus_pickaxe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "ventus_axe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "ventus_shovel"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "ventus_hoe"), true
										).build()
								).build()
						)
				)
				.rewards(AdvancementRewards.Builder.experience(150))
				.save(consumer, "immersiveweapons:pretty_windy");

		Builder.advancement().parent(obtainVentusShard) // TODO: Tagged for advancement rename
				.display(DeferredRegistryHandler.VENTUS_HELMET.get(),
						new TranslatableComponent("advancements.immersiveweapons.almost_flying.title"),
						new TranslatableComponent("advancements.immersiveweapons.almost_flying.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_HELMET.get(),
								DeferredRegistryHandler.VENTUS_CHESTPLATE.get(), DeferredRegistryHandler.VENTUS_LEGGINGS.get(),
								DeferredRegistryHandler.VENTUS_BOOTS.get()))
				.rewards(AdvancementRewards.Builder.experience(100))
				.save(consumer, "immersiveweapons:almost_flying");

		Advancement craftVentusStaffCore = Builder.advancement().parent(obtainVentusShard)
				.display(DeferredRegistryHandler.VENTUS_STAFF_CORE.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_staff_core.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_staff_core.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_STAFF_CORE.get()))
				.save(consumer, "immersiveweapons:ventus_staff_core");

		Builder.advancement().parent(craftVentusStaffCore)
				.display(DeferredRegistryHandler.VENTUS_STAFF.get(),
						new TranslatableComponent("advancements.immersiveweapons.ventus_staff.title"),
						new TranslatableComponent("advancements.immersiveweapons.ventus_staff.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_STAFF.get()))
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:ventus_staff");

		// Tool advancements
		Advancement craftToolRod = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.WOOD_TOOL_ROD.get(),
						new TranslatableComponent("advancements.immersiveweapons.tool_rod.title"),
						new TranslatableComponent("advancements.immersiveweapons.tool_rod.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WOOD_TOOL_ROD.get()))
				.save(consumer, "immersiveweapons:tool_rod");

		Builder.advancement().parent(craftToolRod)
				.display(DeferredRegistryHandler.IRON_PIKE.get(),
						new TranslatableComponent("advancements.immersiveweapons.pike.title"),
						new TranslatableComponent("advancements.immersiveweapons.pike.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WOOD_PIKE.get()))
				.addCriterion("hold1",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.STONE_PIKE.get()))
				.addCriterion("hold2",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.IRON_PIKE.get()))
				.addCriterion("hold3",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_PIKE.get()))
				.addCriterion("hold4",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.GOLD_PIKE.get()))
				.addCriterion("hold5",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.DIAMOND_PIKE.get()))
				.addCriterion("hold6",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.NETHERITE_PIKE.get()))
				.addCriterion("hold7",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_PIKE.get()))
				.requirements(RequirementsStrategy.OR)
				.save(consumer, "immersiveweapons:pike");

		Advancement shards = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.STONE_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.shards.title"),
						new TranslatableComponent("advancements.immersiveweapons.shards.description"),
						null, FrameType.TASK, false, false, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLTEN_SHARD.get()))
				.addCriterion("hold1",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.OBSIDIAN_SHARD.get()))
				.addCriterion("hold2",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.STONE_SHARD.get()))
				.addCriterion("hold3",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WOOD_SHARD.get()))
				.addCriterion("hold4",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.DIAMOND_SHARD.get()))
				.addCriterion("hold5",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.VENTUS_SHARD.get()))
				.requirements(RequirementsStrategy.OR)
				.save(consumer, "immersiveweapons:shards");

		Builder.advancement().parent(shards)
				.display(DeferredRegistryHandler.WOOD_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.wood_shard.title"),
						new TranslatableComponent("advancements.immersiveweapons.wood_shard.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WOOD_SHARD.get()))
				.save(consumer, "immersiveweapons:wood_shard");

		Builder.advancement().parent(shards)
				.display(DeferredRegistryHandler.STONE_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.stone_shard.title"),
						new TranslatableComponent("advancements.immersiveweapons.stone_shard.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.STONE_SHARD.get()))
				.save(consumer, "immersiveweapons:stone_shard");

		Builder.advancement().parent(shards)
				.display(DeferredRegistryHandler.DIAMOND_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.diamond_shard.title"),
						new TranslatableComponent("advancements.immersiveweapons.diamond_shard.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.DIAMOND_SHARD.get()))
				.save(consumer, "immersiveweapons:diamond_shard");

		Builder.advancement().parent(shards)
				.display(DeferredRegistryHandler.OBSIDIAN_SHARD.get(),
						new TranslatableComponent("advancements.immersiveweapons.obsidian_shard.title"),
						new TranslatableComponent("advancements.immersiveweapons.obsidian_shard.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.OBSIDIAN_SHARD.get()))
				.save(consumer, "immersiveweapons:obsidian_shard");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.NETHERITE_ARROW.get(),
						new TranslatableComponent("advancements.immersiveweapons.netherite_projectile.title"),
						new TranslatableComponent("advancements.immersiveweapons.netherite_projectile.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.NETHERITE_ARROW.get()))
				.addCriterion("hold1",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.NETHERITE_MUSKET_BALL.get()))
				.rewards(AdvancementRewards.Builder.experience(50))
				.save(consumer, "immersiveweapons:netherite_projectile");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.GOLD_MUSKET_BALL.get(),
						new TranslatableComponent("advancements.immersiveweapons.musket_ball.title"),
						new TranslatableComponent("advancements.immersiveweapons.musket_ball.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ImmersiveWeaponsItemTagGroups.MUSKET_BALLS).build()))
				.save(consumer, "immersiveweapons:musket_ball");

		Advancement craftBlankBlueprint = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.BLANK_BLUEPRINT.get(),
						new TranslatableComponent("advancements.immersiveweapons.blueprint.title"),
						new TranslatableComponent("advancements.immersiveweapons.blueprint.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BLANK_BLUEPRINT.get()))
				.save(consumer, "immersiveweapons:blueprint");

		Builder.advancement().parent(craftBlankBlueprint)
				.display(DeferredRegistryHandler.FLINTLOCK_PISTOL.get(),
						new TranslatableComponent("advancements.immersiveweapons.flintlock_pistol.title"),
						new TranslatableComponent("advancements.immersiveweapons.flintlock_pistol.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.FLINTLOCK_PISTOL.get()))
				.save(consumer, "immersiveweapons:flintlock_pistol");

		Builder.advancement().parent(craftBlankBlueprint)
				.display(DeferredRegistryHandler.BLUNDERBUSS.get(),
						new TranslatableComponent("advancements.immersiveweapons.blunderbuss.title"),
						new TranslatableComponent("advancements.immersiveweapons.blunderbuss.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BLUNDERBUSS.get()))
				.save(consumer, "immersiveweapons:blunderbuss");

		Builder.advancement().parent(craftBlankBlueprint)
				.display(DeferredRegistryHandler.SMOKE_BOMB.get(),
						new TranslatableComponent("advancements.immersiveweapons.smoke_bomb.title"),
						new TranslatableComponent("advancements.immersiveweapons.smoke_bomb.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.SMOKE_BOMB.get()))
				.save(consumer, "immersiveweapons:smoke_bomb");


		Advancement craftAlcohol = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.BOTTLE_OF_ALCOHOL.get(),
						new TranslatableComponent("advancements.immersiveweapons.bottle_of_alcohol.title"),
						new TranslatableComponent("advancements.immersiveweapons.bottle_of_alcohol.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BOTTLE_OF_ALCOHOL.get()))
				.save(consumer, "immersiveweapons:bottle_of_alcohol");
		Builder.advancement().parent(craftAlcohol)
				.display(DeferredRegistryHandler.MOLOTOV_COCKTAIL.get(),
						new TranslatableComponent("advancements.immersiveweapons.molotov_cocktail.title"),
						new TranslatableComponent("advancements.immersiveweapons.molotov_cocktail.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.MOLOTOV_COCKTAIL.get()))
				.save(consumer, "immersiveweapons:molotov_cocktail");


		Advancement craftBandage = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.BANDAGE.get(),
						new TranslatableComponent("advancements.immersiveweapons.bandage.title"),
						new TranslatableComponent("advancements.immersiveweapons.bandage.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BANDAGE.get()))
				.save(consumer, "immersiveweapons:bandage");
		Builder.advancement().parent(craftBandage)
				.display(DeferredRegistryHandler.FIRST_AID_KIT.get(),
						new TranslatableComponent("advancements.immersiveweapons.first_aid_kit.title"),
						new TranslatableComponent("advancements.immersiveweapons.first_aid_kit.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.FIRST_AID_KIT.get()))
				.save(consumer, "immersiveweapons:first_aid_kit");


		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.IRON_GAUNTLET.get(),
						new TranslatableComponent("advancements.immersiveweapons.gauntlet.title"),
						new TranslatableComponent("advancements.immersiveweapons.gauntlet.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WOOD_GAUNTLET.get()))
				.addCriterion("hold1",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.STONE_GAUNTLET.get()))
				.addCriterion("hold2",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.GOLD_GAUNTLET.get()))
				.addCriterion("hold3",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_GAUNTLET.get()))
				.addCriterion("hold4",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.IRON_GAUNTLET.get()))
				.addCriterion("hold5",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.DIAMOND_GAUNTLET.get()))
				.addCriterion("hold6",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.NETHERITE_GAUNTLET.get()))
				.addCriterion("hold7",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_GAUNTLET.get()))
				.requirements(RequirementsStrategy.OR)
				.save(consumer, "immersiveweapons:gauntlet");

		// General ingot advancements
		Advancement ingots = Builder.advancement().parent(root)
				.display(Items.IRON_INGOT,
						new TranslatableComponent("advancements.immersiveweapons.ingots.title"),
						new TranslatableComponent("advancements.immersiveweapons.ingots.description"),
						null, FrameType.TASK, false, false, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(
								ItemPredicate.Builder.item().of(Tags.Items.INGOTS).build())
				)
				.save(consumer, "immersiveweapons:ingots");

		Builder.advancement().parent(root)
				.display(Items.GOLD_NUGGET,
						new TranslatableComponent("advancements.immersiveweapons.nuggets.title"),
						new TranslatableComponent("advancements.immersiveweapons.nuggets.description"),
						null, FrameType.TASK, false, false, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(
								ItemPredicate.Builder.item().of(Tags.Items.NUGGETS).build())
				)
				.save(consumer, "immersiveweapons:nuggets");

		// Copper advancements
		Advancement copperIngot = Builder.advancement().parent(ingots)
				.display(Items.COPPER_INGOT,
						new TranslatableComponent("advancements.immersiveweapons.copper_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
				.save(consumer, "immersiveweapons:copper_ingot");

		Builder.advancement().parent(copperIngot)
				.display(DeferredRegistryHandler.COPPER_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.copper_sword.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_sword.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_SWORD.get()))
				.save(consumer, "immersiveweapons:copper_sword");

		Builder.advancement().parent(copperIngot)
				.display(DeferredRegistryHandler.COPPER_PICKAXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.copper_pickaxe.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_pickaxe.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_PICKAXE.get()))
				.save(consumer, "immersiveweapons:copper_pickaxe");

		Builder.advancement().parent(copperIngot)
				.display(DeferredRegistryHandler.COPPER_AXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.copper_axe.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_axe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_AXE.get()))
				.save(consumer, "immersiveweapons:copper_axe");

		Builder.advancement().parent(copperIngot)
				.display(DeferredRegistryHandler.COPPER_SHOVEL.get(),
						new TranslatableComponent("advancements.immersiveweapons.copper_shovel.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_shovel.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_SHOVEL.get()))
				.save(consumer, "immersiveweapons:copper_shovel");

		Builder.advancement().parent(copperIngot)
				.display(DeferredRegistryHandler.COPPER_HOE.get(),
						new TranslatableComponent("advancements.immersiveweapons.copper_hoe.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_hoe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COPPER_HOE.get()))
				.save(consumer, "immersiveweapons:copper_hoe");

		Builder.advancement().parent(copperIngot)
				.display(Items.COPPER_BLOCK,
						new TranslatableComponent("advancements.immersiveweapons.copper_tools.title"),
						new TranslatableComponent("advancements.immersiveweapons.copper_tools.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("have_advancements",
						LocationTrigger.TriggerInstance.located(
								EntityPredicate.Builder.entity().player(
										PlayerPredicate.Builder.player().checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_sword"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_pickaxe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_axe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_shovel"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "copper_hoe"), true
										).build()
								).build()
						)
				)
				.rewards(AdvancementRewards.Builder.experience(25))
				.save(consumer, "immersiveweapons:copper_tools");

		// Cobalt advancements
		Advancement cobaltIngot = Builder.advancement().parent(ingots)
				.display(DeferredRegistryHandler.COBALT_INGOT.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_INGOT.get()))
				.save(consumer, "immersiveweapons:cobalt_ingot");

		Builder.advancement().parent(cobaltIngot)
				.display(DeferredRegistryHandler.COBALT_SWORD.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_sword.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_sword.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_SWORD.get()))
				.save(consumer, "immersiveweapons:cobalt_sword");

		Builder.advancement().parent(cobaltIngot)
				.display(DeferredRegistryHandler.COBALT_PICKAXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_pickaxe.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_pickaxe.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_PICKAXE.get()))
				.save(consumer, "immersiveweapons:cobalt_pickaxe");

		Builder.advancement().parent(cobaltIngot)
				.display(DeferredRegistryHandler.COBALT_AXE.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_axe.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_axe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_AXE.get()))
				.save(consumer, "immersiveweapons:cobalt_axe");

		Builder.advancement().parent(cobaltIngot)
				.display(DeferredRegistryHandler.COBALT_SHOVEL.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_shovel.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_shovel.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_SHOVEL.get()))
				.save(consumer, "immersiveweapons:cobalt_shovel");

		Builder.advancement().parent(cobaltIngot)
				.display(DeferredRegistryHandler.COBALT_HOE.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_hoe.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_hoe.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.COBALT_HOE.get()))
				.save(consumer, "immersiveweapons:cobalt_hoe");

		Builder.advancement().parent(cobaltIngot)
				.display(DeferredRegistryHandler.COBALT_BLOCK_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_tools.title"),
						new TranslatableComponent("advancements.immersiveweapons.cobalt_tools.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("have_advancements",
						LocationTrigger.TriggerInstance.located(
								EntityPredicate.Builder.entity().player(
										PlayerPredicate.Builder.player().checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_sword"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_pickaxe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_axe"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_shovel"), true
										).checkAdvancementDone(
												new ResourceLocation(ImmersiveWeapons.MOD_ID, "cobalt_hoe"), true
										).build()
								).build()
						)
				)
				.rewards(AdvancementRewards.Builder.experience(35))
				.save(consumer, "immersiveweapons:cobalt_tools");

		// Other ingots, without families
		Builder.advancement().parent(ingots)
				.display(Items.GOLD_INGOT,
						new TranslatableComponent("advancements.immersiveweapons.gold_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.gold_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
				.save(consumer, "immersiveweapons:gold_ingot");

		Builder.advancement().parent(ingots)
				.display(Items.NETHERITE_INGOT,
						new TranslatableComponent("advancements.immersiveweapons.netherite_ingot.title"),
						new TranslatableComponent("advancements.immersiveweapons.netherite_ingot.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
				.save(consumer, "immersiveweapons:netherite_ingot");

		// Entity discovery advancements
		Advancement entity_discovery = Builder.advancement().parent(root)
				.display(Items.CREEPER_HEAD,
						new TranslatableComponent("advancements.immersiveweapons.entity_discovery.title"),
						new TranslatableComponent("advancements.immersiveweapons.entity_discovery.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("discover_minuteman", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.MINUTEMAN_ENTITY.getId()))
				.addCriterion("discover_field_medic", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.FIELD_MEDIC_ENTITY.getId()))
				.addCriterion("discover_dying_soldier", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.DYING_SOLDIER_ENTITY.getId()))
				.addCriterion("discover_wandering_warrior", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.WANDERING_WARRIOR_ENTITY.getId()))
				.addCriterion("discover_hans", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.HANS_ENTITY.getId()))
				.addCriterion("discover_lava_revenant", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.LAVA_REVENANT_ENTITY.getId()))
				.addCriterion("discover_rock_spider", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.ROCK_SPIDER_ENTITY.getId()))
				.addCriterion("discover_celestial_tower", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.CELESTIAL_TOWER_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(150))
				.save(consumer, "immersiveweapons:entity_discovery");

		Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.MINUTEMAN_HEAD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_minuteman.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_minuteman.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.MINUTEMAN_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_minuteman");

		Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.FIELD_MEDIC_HEAD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_field_medic.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_field_medic.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.FIELD_MEDIC_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_field_medic");

		Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.DYING_SOLDIER_HEAD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_dying_soldier.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_dying_soldier.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.DYING_SOLDIER_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_dying_soldier");

		Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.WANDERING_WARRIOR_HEAD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_wandering_warrior.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_wandering_warrior.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.WANDERING_WARRIOR_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_wandering_warrior");

		Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.HANS_HEAD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_hans.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_hans.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.HANS_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_hans");

		Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.SULFUR.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_lava_revenant.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_lava_revenant.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.LAVA_REVENANT_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_lava_revenant");

		Builder.advancement().parent(entity_discovery)
				.display(Items.SPIDER_EYE,
						new TranslatableComponent("advancements.immersiveweapons.discover_rock_spider.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_rock_spider.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.ROCK_SPIDER_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_rock_spider");

		Advancement celestial_tower_discovery = Builder.advancement().parent(entity_discovery)
				.display(DeferredRegistryHandler.CELESTIAL_FRAGMENT.get(),
						new TranslatableComponent("advancements.immersiveweapons.discover_celestial_tower.title"),
						new TranslatableComponent("advancements.immersiveweapons.discover_celestial_tower.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("discover", new EntityDiscoveredTrigger.TriggerInstance(
						Composite.ANY,
						DeferredRegistryHandler.CELESTIAL_TOWER_ENTITY.getId()))
				.rewards(AdvancementRewards.Builder.experience(20))
				.save(consumer, "immersiveweapons:discover_celestial_tower");

		Builder.advancement().parent(celestial_tower_discovery)
				.display(DeferredRegistryHandler.CELESTIAL_LANTERN_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.celestial_lantern.title"),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.celestial_lantern.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.CELESTIAL_LANTERN_ITEM.get()))
				.rewards(AdvancementRewards.Builder.experience(50))
				.save(consumer, "immersiveweapons:celestial_lantern");

		// Other advancements
		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.USED_SYRINGE.get(),
						new TranslatableComponent("advancements.immersiveweapons.used_syringe.title"),
						new TranslatableComponent("advancements.immersiveweapons.used_syringe.description"),
						null, FrameType.CHALLENGE, true, true, true)
				.addCriterion("hold",
						KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.ANY,
								DamageSourcePredicate.Builder.damageType()
										.source(EntityPredicate.Builder.entity()
												.equipment(EntityEquipmentPredicate.Builder.equipment()
														.mainhand(ItemPredicate.Builder.item()
																.of(DeferredRegistryHandler.USED_SYRINGE.get())
																.build())
														.build()))))
				.save(consumer, "immersiveweapons:used_syringe");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.BEAR_TRAP.get(),
						new TranslatableComponent("advancements.immersiveweapons.traps.title"),
						new TranslatableComponent("advancements.immersiveweapons.traps.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BEAR_TRAP.get()))
				.addCriterion("hold1",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.PUNJI_STICKS.get()))
				.addCriterion("hold2",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.LANDMINE.get()))
				.addCriterion("hold3",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BEAR_TRAP.get()))
				.addCriterion("hold4",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.BARBED_WIRE.get()))
				.addCriterion("hold5",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.SPIKE_TRAP.get()))
				.addCriterion("hold6",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.PITFALL.get()))
				.addCriterion("hold7",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WOODEN_SPIKES.get()))
				.requirements(RequirementsStrategy.OR)
				.save(consumer, "immersiveweapons:traps");

		Builder.advancement().parent(root)
				.display(Items.OAK_PLANKS,
						new TranslatableComponent("advancements.immersiveweapons.planks.title"),
						new TranslatableComponent("advancements.immersiveweapons.planks.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
								.of(ItemTags.PLANKS).build()))
				.save(consumer, "immersiveweapons:planks");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.MUD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.mud.title"),
						new TranslatableComponent("advancements.immersiveweapons.mud.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
								.of(DeferredRegistryHandler.MUD_ITEM.get()).build()))
				.save(consumer, "immersiveweapons:mud");

		Builder.advancement().parent(root)
				.display(Items.BAMBOO,
						new TranslatableComponent("advancements.immersiveweapons.bamboo.title"),
						new TranslatableComponent("advancements.immersiveweapons.bamboo.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
								.of(Items.BAMBOO).build()))
				.save(consumer, "immersiveweapons:bamboo");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.CLOUD_MARBLE_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.cloud_marble.title"),
						new TranslatableComponent("advancements.immersiveweapons.cloud_marble.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
								.of(DeferredRegistryHandler.CLOUD_MARBLE_ITEM.get()).build()))
				.save(consumer, "immersiveweapons:cloud_marble");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.BIOHAZARD_BOX_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.biohazard_box.title"),
						new TranslatableComponent("advancements.immersiveweapons.biohazard_box.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
								.of(DeferredRegistryHandler.BIOHAZARD_BOX_ITEM.get()).build()))
				.save(consumer, "immersiveweapons:biohazard_box");

		Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.CLOUD_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.cloud.title"),
						new TranslatableComponent("advancements.immersiveweapons.cloud.description"),
						null, FrameType.CHALLENGE, true, true, false)
				.addCriterion("hold",
						LocationTrigger.TriggerInstance.walkOnBlockWithEquipment(DeferredRegistryHandler.CLOUD.get(),
								Items.AIR))
				.save(consumer, "immersiveweapons:cloud");

		// Battlefield advancements
		Advancement discover_battlefield = Builder.advancement().parent(root)
				.display(Blocks.SKELETON_SKULL,
						new TranslatableComponent("advancements.immersiveweapons.battlefield.title"),
						new TranslatableComponent("advancements.immersiveweapons.battlefield.description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("visit",
						LocationTrigger.TriggerInstance.located(
								LocationPredicate.inBiome(BiomesAndDimensions.BATTLEFIELD)))
				.rewards(AdvancementRewards.Builder.experience(50))
				.save(consumer, "immersiveweapons:battlefield");

		Builder.advancement().parent(discover_battlefield)
				.display(DeferredRegistryHandler.MINUTEMAN_STATUE_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.minuteman_statue.title"),
						new TranslatableComponent("advancements.immersiveweapons.minuteman_statue.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold", InventoryChangeTrigger.TriggerInstance.hasItems(
						DeferredRegistryHandler.MINUTEMAN_STATUE_ITEM.get()))
				.save(consumer, "immersiveweapons:minuteman_statue");

		Builder.advancement().parent(discover_battlefield)
				.display(DeferredRegistryHandler.MEDIC_STATUE_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.medic_statue.title"),
						new TranslatableComponent("advancements.immersiveweapons.medic_statue.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("hold", InventoryChangeTrigger.TriggerInstance.hasItems(
						DeferredRegistryHandler.MEDIC_STATUE_ITEM.get()))
				.save(consumer, "immersiveweapons:medic_statue");

		// Tiltros advancements
		Advancement warrior_statue = Builder.advancement().parent(root)
				.display(DeferredRegistryHandler.WARRIOR_STATUE_HEAD.get(),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.warrior_statue.title"),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.warrior_statue.description"),
						null, FrameType.TASK, true, true, true)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.WARRIOR_STATUE_BASE.get(),
								DeferredRegistryHandler.WARRIOR_STATUE_TORSO.get(),
								DeferredRegistryHandler.WARRIOR_STATUE_HEAD.get()))
				.requirements(RequirementsStrategy.AND)
				.save(consumer, "immersiveweapons:warrior_statue");

		Advancement azul_keystone = Builder.advancement().parent(warrior_statue)
				.display(DeferredRegistryHandler.AZUL_KEYSTONE_FRAGMENT.get(),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.azul_keystone.title"),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.azul_keystone.description"),
						null, FrameType.TASK, true, true, true)
				.addCriterion("hold",
						InventoryChangeTrigger.TriggerInstance.hasItems(DeferredRegistryHandler.AZUL_KEYSTONE.get()))
				.save(consumer, "immersiveweapons:azul_keystone");

		Advancement activated_warrior_statue = Builder.advancement().parent(azul_keystone)
				.display(DeferredRegistryHandler.AZUL_KEYSTONE.get(),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.activated_warrior_statue.title"),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.activated_warrior_statue.description"),
						null, FrameType.TASK, true, true, true)
				.addCriterion("activate",
						new WarriorStatueActivatedTrigger.TriggerInstance(Composite.ANY))
				.save(consumer, "immersiveweapons:activated_warrior_statue");

		Builder.advancement().parent(activated_warrior_statue)
				.display(DeferredRegistryHandler.AZUL_STAINED_ORCHID_ITEM.get(),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.biome.title"),
						new TranslatableComponent("advancements.immersiveweapons.tiltros.biome.description"),
						null, FrameType.GOAL, true, true, false)
				.addCriterion("visit",
						LocationTrigger.TriggerInstance.located(
								LocationPredicate.inBiome(BiomesAndDimensions.B_TILTROS)))
				.rewards(AdvancementRewards.Builder.experience(50))
				.save(consumer, "immersiveweapons:tiltros");
	}
}