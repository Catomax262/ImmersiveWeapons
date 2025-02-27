package tech.anonymoushacker1279.immersiveweapons.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;

import java.util.ArrayList;
import java.util.List;

public class MolotovEntity extends ThrowableItemProjectile {

	private static final byte VANILLA_IMPACT_STATUS_ID = 3;
	static final BlockState airState = Blocks.AIR.defaultBlockState();
	static final BlockState fireState = Blocks.FIRE.defaultBlockState();

	/**
	 * Constructor for MolotovEntity.
	 *
	 * @param entityType the <code>EntityType</code> instance; must extend MolotovEntity
	 * @param world      the <code>World</code> the entity is in
	 */
	public MolotovEntity(EntityType<? extends MolotovEntity> entityType, Level world) {
		super(entityType, world);
	}

	/**
	 * Constructor for MolotovEntity.
	 *
	 * @param world        the <code>World</code> the entity is in
	 * @param livingEntity the <code>LivingEntity</code> throwing the entity
	 */
	public MolotovEntity(Level world, LivingEntity livingEntity) {
		super(DeferredRegistryHandler.MOLOTOV_COCKTAIL_ENTITY.get(), livingEntity, world);
	}

	/**
	 * Constructor for MolotovEntity.
	 *
	 * @param world the <code>World</code> the entity is in
	 * @param x     the X position
	 * @param y     the Y position
	 * @param z     the Z position
	 */
	public MolotovEntity(Level world, double x, double y, double z) {
		super(DeferredRegistryHandler.MOLOTOV_COCKTAIL_ENTITY.get(), x, y, z, world);
	}

	/**
	 * Get the entity spawn packet.
	 *
	 * @return IPacket
	 */
	@Override
	public @NotNull Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	/**
	 * ProjectileItemEntity::setItem uses this to save storage space.
	 * It only stores the itemStack if the itemStack is not the default item.
	 *
	 * @return Item
	 */
	@Override
	protected @NotNull Item getDefaultItem() {
		return DeferredRegistryHandler.MOLOTOV_COCKTAIL.get();
	}

	/**
	 * Runs when an entity/block is hit.
	 *
	 * @param rayTraceResult the <code>RayTraceResult</code> instance
	 */
	@Override
	protected void onHit(@NotNull HitResult rayTraceResult) {
		super.onHit(rayTraceResult);
		if (!level.isClientSide) {
			level.broadcastEntityEvent(this, VANILLA_IMPACT_STATUS_ID);

			BlockPos currentPosition = blockPosition();

			// Build a list of blocks where the fire will be placed.
			List<BlockPos> firePositionList = new ArrayList<>(15);
			firePositionList.add(adjustFirePosition(currentPosition, 4));
			firePositionList.add(adjustFirePosition(currentPosition.east(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.east(2), 3));
			firePositionList.add(adjustFirePosition(currentPosition.east().north(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.east().south(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.west(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.west(2), 3));
			firePositionList.add(adjustFirePosition(currentPosition.west().north(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.west().south(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.north(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.north(2), 3));
			firePositionList.add(adjustFirePosition(currentPosition.south(), 3));
			firePositionList.add(adjustFirePosition(currentPosition.south(2), 3));

			for (BlockPos pos : firePositionList) {
				if (!level.getBlockState(pos.below()).isAir() && level.getBlockState(pos).isAir()) {
					level.setBlockAndUpdate(pos, fireState);
				}
			}

			kill();
		}
	}

	/**
	 * Move the position down until a solid block is under it.
	 *
	 * @param pos          the <code>BlockPos</code> being moved
	 * @param distanceDown the number of blocks to try moving down
	 * @return BlockPos
	 */
	private BlockPos adjustFirePosition(BlockPos pos, int distanceDown) {
		BlockPos movedPosition = pos;
		for (int i = 0; i <= distanceDown; i++) {
			if (level.getBlockState(movedPosition) != airState) {
				return movedPosition.above();
			}
			movedPosition = movedPosition.below();
		}
		return pos;
	}

	/**
	 * Handle entity events.
	 *
	 * @param statusID the <code>byte</code> containing status ID
	 */
	@Override
	public void handleEntityEvent(byte statusID) {
		if (statusID == VANILLA_IMPACT_STATUS_ID) {
			level.playLocalSound(getX(), getY(), getZ(), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 1f, 1f, false);
			kill();
		}
	}
}