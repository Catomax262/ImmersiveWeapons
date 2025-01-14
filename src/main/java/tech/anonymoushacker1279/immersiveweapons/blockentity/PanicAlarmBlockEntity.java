package tech.anonymoushacker1279.immersiveweapons.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.config.ClientConfig;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;

import javax.annotation.Nullable;

public class PanicAlarmBlockEntity extends BlockEntity implements EntityBlock {

	private boolean isPowered = false;
	private int cooldown = 0;

	/**
	 * Constructor for PanicAlarmBlockEntity.
	 */
	public PanicAlarmBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(DeferredRegistryHandler.PANIC_ALARM_BLOCK_ENTITY.get(), blockPos, blockState);
	}

	/**
	 * Runs once each tick. Handle playing alarm sounds.
	 */
	public void tick(Level level, BlockPos blockPos) {

		if (isPowered && cooldown-- <= 0) {
			for (ServerPlayer serverPlayer : ((ServerLevel) level).getPlayers(player -> player.blockPosition()
					.distSqr(blockPos) <= Math.pow(ClientConfig.PANIC_ALARM_RANGE.get(), 2))) {

				serverPlayer.playNotifySound(DeferredRegistryHandler.PANIC_ALARM_SOUND.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			}

			setCooldown(40);
		}
	}

	/**
	 * Create a block entity for the block.
	 *
	 * @param blockPos   the <code>BlockPos</code> the block is at
	 * @param blockState the <code>BlockState</code> of the block
	 * @return BlockEntity
	 */
	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new PanicAlarmBlockEntity(blockPos, blockState);
	}

	/**
	 * Get the power status.
	 *
	 * @return boolean
	 */
	public boolean isPowered() {
		return isPowered;
	}

	/**
	 * Set the power status.
	 */
	public void setPowered(boolean state) {
		isPowered = state;
		setChanged();
	}

	public void setCooldown(int delay) {
		cooldown = delay;
		setChanged();
	}

	/**
	 * Save NBT data.
	 *
	 * @param pTag the <code>CompoundNBT</code> to save
	 */
	@Override
	protected void saveAdditional(@NotNull CompoundTag pTag) {
		super.saveAdditional(pTag);

		pTag.putInt("cooldown", cooldown);
		pTag.putBoolean("isPowered", isPowered);
	}

	/**
	 * Load NBT data.
	 *
	 * @param nbt the <code>CompoundNBT</code> to load
	 */
	@Override
	public void load(@NotNull CompoundTag nbt) {
		super.load(nbt);

		cooldown = nbt.getInt("cooldown");
		isPowered = nbt.getBoolean("isPowered");
	}
}