package com.anonymoushacker1279.immersiveweapons.entity.projectile;

import com.anonymoushacker1279.immersiveweapons.client.particle.SmokeBombParticleData;
import com.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import com.anonymoushacker1279.immersiveweapons.util.GeneralUtilities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.awt.*;

public class MolotovEntity extends ProjectileItemEntity {

	private static final byte VANILLA_IMPACT_STATUS_ID = 3;

	/**
	 * Constructor for MolotovEntity.
	 * @param entityType the <code>EntityType</code> instance; must extend MolotovEntity
	 * @param world the <code>World</code> the entity is in
	 */
	public MolotovEntity(EntityType<? extends MolotovEntity> entityType, World world) {
		super(entityType, world);
	}

	/**
	 * Constructor for MolotovEntity.
	 * @param world the <code>World</code> the entity is in
	 * @param livingEntity the <code>LivingEntity</code> throwing the entity
	 */
	public MolotovEntity(World world, LivingEntity livingEntity) {
		super(DeferredRegistryHandler.MOLOTOV_COCKTAIL_ENTITY.get(), livingEntity, world);
	}

	/**
	 * Constructor for MolotovEntity.
	 * @param world the <code>World</code> the entity is in
	 * @param x the X position
	 * @param y the Y position
	 * @param z the Z position
	 */
	public MolotovEntity(World world, double x, double y, double z) {
		super(DeferredRegistryHandler.MOLOTOV_COCKTAIL_ENTITY.get(), x, y, z, world);
	}

	/**
	 * Get the entity spawn packet.
	 * @return IPacket
	 */
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	/**
	 * ProjectileItemEntity::setItem uses this to save storage space.
	 * It only stores the itemStack if the itemStack is not the default item.
	 * @return Item
	 */
	@Override
	protected Item getDefaultItem() {
		return DeferredRegistryHandler.MOLOTOV_COCKTAIL.get();
	}

	/**
	 * Runs when an entity/block is hit.
	 * @param rayTraceResult the <code>RayTraceResult</code> instance
	 */
	@Override
	protected void onHit(RayTraceResult rayTraceResult) {
		super.onHit(rayTraceResult);
		if (!level.isClientSide) {
			level.broadcastEntityEvent(this, VANILLA_IMPACT_STATUS_ID);  // calls handleStatusUpdate which tells the client to render particles
			if (level.getBlockState(blockPosition()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().above()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().above()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().above(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().east()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().east()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().east(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().east(2)) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().east(2)) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().east(2), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().east().north()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().east().north()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().east().north(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().east().south()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().east().south()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().east().south(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().west()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().west()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().west(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().west(2)) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().west(2)) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().west(2), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().west().south()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().west().south()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().west().south(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().west().north()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().west().north()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().west().north(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().north()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().north()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().north(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().north(2)) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().north(2)) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().north(2), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().south()) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().south()) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().south(), Blocks.FIRE.defaultBlockState());
			if (level.getBlockState(blockPosition().south(2)) == Blocks.AIR.defaultBlockState() || level.getBlockState(blockPosition().south(2)) == Blocks.CAVE_AIR.defaultBlockState())
				level.setBlockAndUpdate(blockPosition().south(2), Blocks.FIRE.defaultBlockState());
			kill();
		}
	}

	/**
	 * Handle entity events.
	 * @param statusID the <code>byte</code> containing status ID
	 */
	@Override
	public void handleEntityEvent(byte statusID) {
		if (statusID == VANILLA_IMPACT_STATUS_ID) {
			IParticleData particleData = makeParticle();

			for (int i = 0; i < 2; ++i) { // Create a few smoke particles, like the smoke bomb
				level.addParticle(particleData, true, getX(), getY(), getZ(), GeneralUtilities.getRandomNumber(-0.02, 0.02d), GeneralUtilities.getRandomNumber(-0.02d, 0.02d), GeneralUtilities.getRandomNumber(-0.02d, 0.02d));
			}
			level.playLocalSound(getX(), getY(), getZ(), SoundEvents.GLASS_BREAK, SoundCategory.NEUTRAL, 1f, 1f, false);
			remove();
		}
	}

	/**
	 * Create a particle.
	 * @return IParticleData
	 */
	private IParticleData makeParticle() {
		Color tint = getTint(GeneralUtilities.getRandomNumber(0, 2));
		double diameter = getDiameter(GeneralUtilities.getRandomNumber(0.2d, 0.4d));

		return new SmokeBombParticleData(tint, diameter);
	}

	/**
	 * Tint a particle.
	 * @param random a random number
	 * @return Color
	 */
	private Color getTint(int random) {
		Color[] tints = {
				new Color(1.00f, 1.00f, 1.00f),  // no tint (white)
				new Color(1.00f, 0.97f, 1.00f),  // off white
				new Color(1.00f, 1.00f, 0.97f),  // off white 2: electric boogaloo
		};

		return tints[random];
	}

	/**
	 * Get the particle diameter.
	 * @param random a random number
	 * @return double
	 */
	private double getDiameter(double random) {
		final double MIN_DIAMETER = 0.01;
		final double MAX_DIAMETER = 5.5;
		return MIN_DIAMETER + (MAX_DIAMETER - MIN_DIAMETER) * random;
	}
}