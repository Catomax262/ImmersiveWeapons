package com.anonymoushacker1279.immersiveweapons.item.crafting;

import com.anonymoushacker1279.immersiveweapons.init.DeferredRegistryHandler;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SmallPartsRecipe implements Recipe<Container> {

	private final Ingredient material;
	private final Ingredient blueprint;
	private final ItemStack result;
	private final ResourceLocation recipeId;

	/**
	 * Constructor for SmallPartsRecipe.
	 * @param recipeId the <code>ResourceLocation</code> for the recipe
	 * @param material the first <code>Ingredient</code>
	 * @param blueprint the second <code>Ingredient</code>
	 * @param result the result <code>ItemStack</code>
	 */
	SmallPartsRecipe(ResourceLocation recipeId, Ingredient material, Ingredient blueprint, ItemStack result) {
		this.recipeId = recipeId;
		this.material = material;
		this.blueprint = blueprint;
		this.result = result;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory.
	 * @param inv the <code>IInventory</code> instance
	 * @param worldIn the current <code>World</code>
	 * @return boolean
	 */
	@Override
	public boolean matches(Container inv, Level worldIn) {
		return material.test(inv.getItem(0)) && blueprint.test(inv.getItem(1));
	}

	/**
	 * Returns an Item that is the result of this recipe.
	 * @param inv the <code>IInventory</code> instance
	 * @return ItemStack
	 */
	@Override
	public ItemStack assemble(Container inv) {
		ItemStack itemstack = result.copy();
		CompoundTag compoundnbt = inv.getItem(0).getTag();
		if (compoundnbt != null) {
			itemstack.setTag(compoundnbt.copy());
		}

		return itemstack;
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height.
	 * @param width the width of the grid
	 * @param height the height of the grid
	 * @return boolean
	 */
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	/**
	 * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
	 * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
	 * @return ItemStack
	 */
	@Override
	public ItemStack getResultItem() {
		return result;
	}

	/**
	 * Check if the given ItemStack is a valid addition item.
	 * @param blueprint the <code>ItemStack</code> instance
	 * @return boolean
	 */
	public boolean isValidAdditionItem(ItemStack blueprint) {
		return this.blueprint.test(blueprint);
	}

	/**
	 * Get the toast symbol.
	 * @return ItemStack
	 */
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(DeferredRegistryHandler.SMALL_PARTS_TABLE.get());
	}

	/**
	 * Get the recipe ID.
	 * @return ResourceLocation
	 */
	@Override
	public ResourceLocation getId() {
		return recipeId;
	}

	/**
	 * Get the recipe serializer.
	 * @return IRecipeSerializer
	 */
	@Override
	public RecipeSerializer<?> getSerializer() {
		return DeferredRegistryHandler.SMALL_PARTS_RECIPE_SERIALIZER.get();
	}

	/**
	 * Get the recipe type.
	 * @return IRecipeType
	 */
	@Override
	public RecipeType<?> getType() {
		return ICustomRecipeType.SMALL_PARTS;
	}

	/**
	 * Get the recipe's ingredients.
	 * @return NonNullList extending Ingredient
	 */
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> defaultedList = NonNullList.create();
		defaultedList.add(material);
		defaultedList.add(blueprint);
		return defaultedList;
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<SmallPartsRecipe> {
		/**
		 * Serialize from JSON.
		 * @param recipeId the <code>ResourceLocation</code> for the recipe
		 * @param json the <code>JsonObject</code> instance
		 * @return SmallPartsRecipe
		 */
		@Override
		public SmallPartsRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "material"));
			Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "blueprint"));
			ItemStack itemstack = new ItemStack(ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "result")));
			return new SmallPartsRecipe(recipeId, ingredient, ingredient1, itemstack);
		}

		/**
		 * Serialize from JSON on the network.
		 * @param recipeId the <code>ResourceLocation</code> for the recipe
		 * @param buffer the <code>PacketBuffer</code> instance
		 * @return SmallPartsRecipe
		 */
		@Override
		public SmallPartsRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			Ingredient ingredient = Ingredient.fromNetwork(buffer);
			Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
			ItemStack itemstack = buffer.readItem();
			return new SmallPartsRecipe(recipeId, ingredient, ingredient1, itemstack);
		}

		/**
		 * Serialize to JSON on the network.
		 * @param buffer the <code>PacketBuffer</code> instance
		 * @param recipe the <code>SmallPartsRecipe</code> instance
		 */
		@Override
		public void toNetwork(FriendlyByteBuf buffer, SmallPartsRecipe recipe) {
			recipe.material.toNetwork(buffer);
			recipe.blueprint.toNetwork(buffer);
			buffer.writeItem(recipe.result);
		}
	}
}