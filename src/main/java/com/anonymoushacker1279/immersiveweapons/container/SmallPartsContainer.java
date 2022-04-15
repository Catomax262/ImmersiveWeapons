package com.anonymoushacker1279.immersiveweapons.container;

import com.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import com.anonymoushacker1279.immersiveweapons.item.crafting.CustomRecipeTypes;
import com.anonymoushacker1279.immersiveweapons.item.crafting.SmallPartsRecipe;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmallPartsContainer extends ItemCombinerMenu {

	private final Level world;
	private final List<SmallPartsRecipe> smallPartsRecipeList;
	private SmallPartsRecipe smallPartsRecipe;

	/**
	 * Constructor for SmallPartsContainer.
	 *
	 * @param id  the ID of the container
	 * @param inv the <code>Inventory</code> instance
	 */
	public SmallPartsContainer(int id, Inventory inv) {
		this(id, inv, ContainerLevelAccess.NULL);
	}

	/**
	 * Constructor for SmallPartsContainer.
	 *
	 * @param id     the ID of the container
	 * @param inv    the <code>Inventory</code> instance
	 * @param access the <code>ContainerLevelAccess</code> instance
	 */
	public SmallPartsContainer(int id, Inventory inv, ContainerLevelAccess access) {
		super(DeferredRegistryHandler.SMALL_PARTS_TABLE_CONTAINER.get(), id, inv, access);
		world = inv.player.level;
		smallPartsRecipeList = world.getRecipeManager().getAllRecipesFor(CustomRecipeTypes.SMALL_PARTS);
	}

	/**
	 * Check for a valid block.
	 *
	 * @param blockState the <code>BlockState</code> of the block
	 * @return boolean
	 */
	@Override
	protected boolean isValidBlock(BlockState blockState) {
		return blockState.is(DeferredRegistryHandler.SMALL_PARTS_TABLE.get());
	}

	/**
	 * Check if the player can pick up a recipe.
	 *
	 * @param player        the <code>Player</code> instance
	 * @param matchesRecipe set the recipe match
	 * @return boolean
	 */
	@Override
	protected boolean mayPickup(@NotNull Player player, boolean matchesRecipe) {
		return smallPartsRecipe != null && smallPartsRecipe.matches(inputSlots, world);
	}

	/**
	 * Runs when the result is taken from the container.
	 *
	 * @param player    the <code>Player</code> instance
	 * @param itemStack the <code>ItemStack</code> being taken
	 */
	@Override
	protected void onTake(@NotNull Player player, ItemStack itemStack) {
		itemStack.onCraftedBy(player.level, player, itemStack.getCount());
		resultSlots.awardUsedRecipes(player);
		// Normally we would destroy both items here. However, we don't want to destroy the blueprint item.
		ItemStack materialSlotItem = inputSlots.getItem(0);
		materialSlotItem.shrink(1);
		inputSlots.setItem(0, materialSlotItem);

		world.playSound(player, player.blockPosition(), DeferredRegistryHandler.SMALL_PARTS_TABLE_USED.get(),
				SoundSource.NEUTRAL, 1f, 1);
	}

	/**
	 * Create a result from a recipe.
	 */
	@Override
	public void createResult() {
		List<SmallPartsRecipe> recipes = world.getRecipeManager()
				.getRecipesFor(CustomRecipeTypes.SMALL_PARTS, inputSlots, world);

		if (recipes.isEmpty()) {
			resultSlots.setItem(0, ItemStack.EMPTY);
		} else {
			smallPartsRecipe = recipes.get(0);
			ItemStack assembledRecipe = smallPartsRecipe.assemble(inputSlots);
			resultSlots.setRecipeUsed(smallPartsRecipe);
			resultSlots.setItem(0, assembledRecipe);
		}

	}

	/**
	 * Check if a stack should be quick-moved.
	 *
	 * @param itemStack the <code>ItemStack</code> being checked
	 * @return boolean
	 */
	@Override
	protected boolean shouldQuickMoveToAdditionalSlot(@NotNull ItemStack itemStack) {
		return smallPartsRecipeList.stream().anyMatch((smallPartsRecipe) -> smallPartsRecipe.isValidAdditionItem(itemStack));
	}

	/**
	 * Check if the specified slot is valid for stack merging.
	 *
	 * @param stack  the <code>ItemStack</code> being merged
	 * @param slotIn the <code>Slot</code> instance
	 * @return boolean
	 */
	@Override
	public boolean canTakeItemForPickAll(@NotNull ItemStack stack, Slot slotIn) {
		return slotIn.container != resultSlots && super.canTakeItemForPickAll(stack, slotIn);
	}
}