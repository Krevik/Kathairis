package mod.krevik.Recipes;

import net.minecraft.item.Item;

public class ChargerRecipe {

	private Item itemToUpgrade;
	private Item itemToBeConsumed;
	private int id;
	public ChargerRecipe(int id, Item ItemToUpgrade, Item ItemToBeConsumed) {
		this.id=id;
		this.itemToUpgrade=ItemToUpgrade;
		this.itemToBeConsumed=ItemToBeConsumed;
	}
	
	public Item getItemToUpgrade() {
		return itemToUpgrade;
	}
	
	public Item getItemToBeConsumed() {
		return itemToBeConsumed;
	}
	
	public int getRecipeId() {
		return id;
	}
	
}
