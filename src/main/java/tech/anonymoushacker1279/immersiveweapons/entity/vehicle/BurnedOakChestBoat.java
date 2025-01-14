package tech.anonymoushacker1279.immersiveweapons.entity.vehicle;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;

import javax.annotation.Nullable;

public class BurnedOakChestBoat extends BurnedOakBoatEntity implements HasCustomInventoryScreen, ContainerEntity {

	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
	@Nullable
	private ResourceLocation lootTable;
	private long lootTableSeed;

	public BurnedOakChestBoat(EntityType<? extends Boat> type, Level level) {
		super(type, level);
	}

	public BurnedOakChestBoat(Level level, double x, double y, double z) {
		this(DeferredRegistryHandler.BURNED_OAK_CHEST_BOAT_ENTITY.get(), level);
		setPos(x, y, z);
		xo = x;
		yo = y;
		zo = z;
	}

	@Override
	protected float getSinglePassengerXOffset() {
		return 0.15F;
	}

	@Override
	protected int getMaxPassengers() {
		return 1;
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		addChestVehicleSaveData(compound);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		readChestVehicleSaveData(compound);
	}

	@Override
	public void destroy(@NotNull DamageSource damageSource) {
		super.destroy(damageSource);
		chestVehicleDestroyed(damageSource, level, this);
	}

	@Override
	public void remove(Entity.@NotNull RemovalReason removalReason) {
		if (!level.isClientSide && removalReason.shouldDestroy()) {
			Containers.dropContents(level, this, this);
		}

		super.remove(removalReason);
	}

	@Override
	public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand hand) {
		return canAddPassenger(player) && !player.isSecondaryUseActive() ? super.interact(player, hand) : interactWithChestVehicle(this::gameEvent, player);
	}

	@Override
	public void openCustomInventoryScreen(Player player) {
		player.openMenu(this);
		if (!player.level.isClientSide) {
			gameEvent(GameEvent.CONTAINER_OPEN, player);
			PiglinAi.angerNearbyPiglins(player, true);
		}

	}

	@Override
	public @NotNull Item getDropItem() {
		return DeferredRegistryHandler.BURNED_OAK_CHEST_BOAT.get();
	}

	@Override
	public void clearContent() {
		clearChestVehicleContent();
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getContainerSize() {
		return 27;
	}

	/**
	 * Returns the stack in the given slot.
	 */
	@Override
	public @NotNull ItemStack getItem(int index) {
		return getChestVehicleItem(index);
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
	 */
	@Override
	public @NotNull ItemStack removeItem(int index, int count) {
		return removeChestVehicleItem(index, count);
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	@Override
	public @NotNull ItemStack removeItemNoUpdate(int index) {
		return removeChestVehicleItemNoUpdate(index);
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	@Override
	public void setItem(int index, @NotNull ItemStack stack) {
		setChestVehicleItem(index, stack);
	}

	@Override
	public @NotNull SlotAccess getSlot(int slot) {
		return getChestVehicleSlot(slot);
	}

	@Override
	public void setChanged() {
	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	@Override
	public boolean stillValid(@NotNull Player player) {
		return isChestVehicleStillValid(player);
	}

	@Override
	@Nullable
	public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
		if (lootTable != null && player.isSpectator()) {
			return null;
		} else {
			unpackLootTable(inventory.player);
			return ChestMenu.threeRows(containerId, inventory, this);
		}
	}

	public void unpackLootTable(@Nullable Player player) {
		unpackChestVehicleLootTable(player);
	}

	@Override
	@Nullable
	public ResourceLocation getLootTable() {
		return lootTable;
	}

	@Override
	public void setLootTable(@Nullable ResourceLocation location) {
		lootTable = location;
	}

	@Override
	public long getLootTableSeed() {
		return lootTableSeed;
	}

	@Override
	public void setLootTableSeed(long seed) {
		lootTableSeed = seed;
	}

	@Override
	public @NotNull NonNullList<ItemStack> getItemStacks() {
		return itemStacks;
	}

	@Override
	public void clearItemStacks() {
		itemStacks = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
	}
}