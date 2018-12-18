package mod.krevik.util;

import java.util.ArrayList;

import mod.krevik.block.BaseBlock;
import mod.krevik.block.BlockMysticLeaf;
import mod.krevik.block.BlockMysticSlabBase;
import mod.krevik.item.BaseItem;
import mod.krevik.item.ItemBasicKathairisMusicDisc;
import mod.krevik.item.ItemMysticArmor;
import mod.krevik.item.ItemMysticFood;
import mod.krevik.item.ItemMysticSword;
import mod.krevik.item.MysticTool;
import mod.krevik.KCore;

public class RegistryHelper {
	
	public RegistryHelper() {
		
	}
	public ArrayList<BaseBlock> blocksList = new ArrayList();
	public ArrayList<BlockMysticLeaf> leavesBlocksList = new ArrayList();
	public ArrayList<BlockMysticSlabBase> slabList = new ArrayList();
	public ArrayList<BaseItem> itemList = new ArrayList();
	public ArrayList<MysticTool> toolList = new ArrayList();
	public ArrayList<ItemMysticArmor> armorList = new ArrayList();
	public ArrayList<ItemMysticFood> foodList = new ArrayList();
	public ArrayList<ItemMysticSword> swordList = new ArrayList();
	public ArrayList<ItemBasicKathairisMusicDisc> recordsList = new ArrayList();


	public void initModels() {
		for(int x=0;x<blocksList.size();x++) {
			blocksList.get(x).initModel();
		}
		for(int x=0;x<leavesBlocksList.size();x++) {
			leavesBlocksList.get(x).initModel();
		}
		for(int x=0;x<slabList.size();x++) {
			slabList.get(x).initModel();
		}
		KCore.MysticPortal.initModel();
		KCore.MythicStoneSign.initModel();
		KCore.Charger.initModel();
		for(int x=0;x<itemList.size();x++) {
			itemList.get(x).initModel();
		}
		for(int x=0;x<toolList.size();x++) {
			toolList.get(x).initModel();
		}
		for(int x=0;x<armorList.size();x++) {
			armorList.get(x).initModel();
		}
		for(int x=0;x<foodList.size();x++) {
			foodList.get(x).initModel();
		}
		for(int x=0;x<swordList.size();x++) {
			swordList.get(x).initModel();
		}
		for(int x=0;x<recordsList.size();x++) {
			recordsList.get(x).initModel();
		}
		KCore.katharian_arrow.initModel();
	}
}
