package mod.krevik.recipe;

import java.util.ArrayList;

import mod.krevik.KCore;

import net.minecraft.item.Item;

public class ChargerRecipes {

	int id=0;

	public ChargerRecipe shockWand = new ChargerRecipe(id++,KCore.ShockWand,KCore.JellyFishTentacle);
	public ChargerRecipe deathWand = new ChargerRecipe(id++,KCore.DeathWand,KCore.DarknessEssence);
	public ArrayList<ChargerRecipe> recipeList = new ArrayList();

	public ChargerRecipes() {
		recipeList.add(shockWand);
		recipeList.add(deathWand);
	}
	
	public int getRecipe(Item item) {
		for(int x=0;x<recipeList.size();x++) {
			if(recipeList.get(x).getItemToBeConsumed()==item||recipeList.get(x).getItemToUpgrade()==item) {
				return x;
			}
		}
		return -1;
	}
	

	
}
