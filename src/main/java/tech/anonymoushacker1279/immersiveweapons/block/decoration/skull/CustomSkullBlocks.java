package tech.anonymoushacker1279.immersiveweapons.block.decoration.skull;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import tech.anonymoushacker1279.immersiveweapons.blockentity.CustomSkullBlockEntity;

public class CustomSkullBlocks {
	public static class CustomSkullBlock extends SkullBlock {

		public CustomSkullBlock(CustomSkullTypes type, Properties properties) {
			super(type, properties);
		}

		@Override
		public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
			return new CustomSkullBlockEntity(pos, state);
		}
	}

	public static class CustomWallSkullBlock extends WallSkullBlock {

		public CustomWallSkullBlock(CustomSkullTypes type, Properties properties) {
			super(type, properties);
		}

		@Override
		public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
			return new CustomSkullBlockEntity(pos, state);
		}
	}
}