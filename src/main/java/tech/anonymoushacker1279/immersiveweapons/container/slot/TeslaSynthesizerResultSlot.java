package tech.anonymoushacker1279.immersiveweapons.container.slot;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.api.events.TeslaSynthesizerCraftEvent;

public class TeslaSynthesizerResultSlot extends Slot {

	/**
	 * Constructor for TeslaSynthesizerResultSlot.
	 *
	 * @param inventoryIn the <code>IInventory</code> of the tile entity
	 * @param slotIndex   the slot index
	 * @param xPosition   the X position of the slot
	 * @param yPosition   the Y position of the slot
	 */
	public TeslaSynthesizerResultSlot(Container inventoryIn, int slotIndex, int xPosition, int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}

	/**
	 * Check if the stack is allowed to be placed in this slot.
	 *
	 * @param stack the <code>ItemStack</code> to be checked
	 * @return boolean
	 */
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
		return false;
	}

	/**
	 * Decrease the size of the stack in slot by the specified amount. Returns the new stack.
	 *
	 * @param amount the amount to reduce the stack by
	 * @return ItemStack
	 */
	@Override
	public @NotNull ItemStack remove(int amount) {
		return super.remove(amount);
	}

	/**
	 * Runs when the stack is taken from the tile entity.
	 *
	 * @param player the <code>PlayerEntity</code> instance
	 * @param stack  the <code>ItemStack</code> being taken
	 */
	@Override
	public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
		checkTakeAchievements(stack);
		MinecraftForge.EVENT_BUS.post(new TeslaSynthesizerCraftEvent(player, stack));
		super.onTake(player, stack);
	}
}