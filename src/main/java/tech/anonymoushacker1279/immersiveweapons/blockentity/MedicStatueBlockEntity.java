package tech.anonymoushacker1279.immersiveweapons.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.entity.neutral.FieldMedicEntity;
import tech.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import tech.anonymoushacker1279.immersiveweapons.world.level.levelgen.biomes.BiomesAndDimensions;

import javax.annotation.Nullable;
import java.util.List;

public class MedicStatueBlockEntity extends AbstractStatueBlockEntity {

	/**
	 * Constructor for MedicStatueBlockEntity.
	 */
	public MedicStatueBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(DeferredRegistryHandler.MEDIC_STATUE_BLOCK_ENTITY.get(), blockPos, blockState);
	}

	/**
	 * Runs once each tick. Handle scanning and spawning entities.
	 */
	public void tick(Level level, BlockPos blockPos) {
		if (level.getBiome(blockPos).is(BiomesAndDimensions.BATTLEFIELD) && cooldown == 0) {
			List<FieldMedicEntity> listOfMedicsInArea = level.getEntitiesOfClass(FieldMedicEntity.class,
					new AABB(getBlockPos().getX() - 48,
							getBlockPos().getY() - 16,
							getBlockPos().getZ() - 48,
							getBlockPos().getX() + 48,
							getBlockPos().getY() + 16,
							getBlockPos().getZ() + 48));

			scannedEntities = listOfMedicsInArea.size();

			if (scannedEntities <= 1) {
				FieldMedicEntity fieldMedicEntity = DeferredRegistryHandler.FIELD_MEDIC_ENTITY.get().create(level);
				if (fieldMedicEntity != null) {
					attemptSpawnEntity(fieldMedicEntity);
				}
			}
		} else if (cooldown > 0) {
			cooldown--;
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
		return new MedicStatueBlockEntity(blockPos, blockState);
	}
}