package tech.anonymoushacker1279.immersiveweapons.client.renderer.entity.mob;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.ImmersiveWeapons;
import tech.anonymoushacker1279.immersiveweapons.entity.monster.DyingSoldierEntity;

public class DyingSoldierRenderer extends HumanoidMobRenderer<DyingSoldierEntity, PlayerModel<DyingSoldierEntity>> {
	private static final ResourceLocation DYING_SOLDIER_RENDERER = new ResourceLocation(ImmersiveWeapons.MOD_ID, "textures/entity/dying_soldier/dying_soldier.png");

	/**
	 * Constructor for DyingSoldierRenderer.
	 *
	 * @param context a <code>Context</code> instance
	 */
	public DyingSoldierRenderer(Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
		addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
	}

	/**
	 * Get the texture location.
	 *
	 * @param entity the <code>DyingSoldierEntity</code> instance
	 * @return ResourceLocation
	 */
	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull DyingSoldierEntity entity) {
		return DYING_SOLDIER_RENDERER;
	}
}