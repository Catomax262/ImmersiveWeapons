package com.anonymoushacker1279.immersiveweapons.tileentity;

import com.anonymoushacker1279.immersiveweapons.entity.passive.FieldMedicEntity;
import com.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import com.anonymoushacker1279.immersiveweapons.util.GeneralUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Objects;

public class MedicStatueTileEntity extends TileEntity implements ITickableTileEntity {

	private int cooldown;
	private int scannedMedics;

	public MedicStatueTileEntity() {
		super(DeferredRegistryHandler.MEDIC_STATUE_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		if (this.level != null && !this.level.isClientSide && Objects.equals(this.level.getBiome(this.getBlockPos()).getRegistryName(), DeferredRegistryHandler.BATTLEFIELD.get().getRegistryName()) && cooldown == 0) {
			List<FieldMedicEntity> listofMedicsInArea = this.level.getEntitiesOfClass(FieldMedicEntity.class, new AxisAlignedBB(this.getBlockPos().getX() - 48, this.getBlockPos().getY() - 16, this.getBlockPos().getZ() - 48, this.getBlockPos().getX() + 48, this.getBlockPos().getY() + 16, this.getBlockPos().getZ() + 48));
			scannedMedics = listofMedicsInArea.size();

			if (scannedMedics <= 1) {
				FieldMedicEntity fieldMedicEntity = DeferredRegistryHandler.FIELD_MEDIC_ENTITY.get().create(this.level);
				if (fieldMedicEntity != null) {
					while (true) {
						BlockPos blockPos = this.getRandomPositionInArea();
						if (this.level.getBlockState(blockPos) == Blocks.AIR.defaultBlockState()) {
							fieldMedicEntity.moveTo(blockPos, 0.0F, 0.0F);
							this.level.addFreshEntity(fieldMedicEntity);
							spawnParticles();
							cooldown = 400;
							break;
						}
					}
				}
			}
		} else if (cooldown > 0) {
			cooldown--;
		}
	}

	private void spawnParticles() {
		ServerWorld serverWorld = (ServerWorld) this.getLevel();
		if (serverWorld != null) {
			serverWorld.sendParticles(ParticleTypes.HAPPY_VILLAGER, this.getBlockPos().getX() + 0.5d, this.getBlockPos().getY(), this.getBlockPos().getZ() + 0.75d, 5, GeneralUtilities.getRandomNumber(-0.05d, 0.05d), GeneralUtilities.getRandomNumber(-0.25d, 0.25d), GeneralUtilities.getRandomNumber(-0.05d, 0.05d), GeneralUtilities.getRandomNumber(-0.15d, 0.15d));
		}
	}

	private BlockPos getRandomPositionInArea() {
		return new BlockPos(this.getBlockPos().getX() + GeneralUtilities.getRandomNumber(-15, 15), this.getBlockPos().getY(), this.getBlockPos().getZ() + GeneralUtilities.getRandomNumber(-15, 15));
	}

	@Override
	public CompoundNBT save(CompoundNBT tag) {
		super.save(tag);
		tag.putInt("scanCooldown", cooldown);
		tag.putInt("scannedMedics", scannedMedics);
		return tag;
	}

	@Override
	public void load(BlockState state, CompoundNBT tag) {
		super.load(state, tag);
		cooldown = tag.getInt("scanCooldown");
		scannedMedics = tag.getInt("scannedMedics");
	}
}