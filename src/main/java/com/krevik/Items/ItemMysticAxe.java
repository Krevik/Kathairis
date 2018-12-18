package com.krevik.Items;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.krevik.Main.KCore;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMysticAxe extends MysticTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST,
    		Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE,
    		KCore.MysticLog,KCore.MysticPlanks, KCore.ShinyLog, KCore.ShinyPlanks,KCore.MysticWoodStairs,KCore.ShinyWoodStairs,
    		KCore.MysticWoodFence,KCore.ShinyWoodFence,KCore.MysticWoodFenceGate,KCore.ShinyWoodFenceGate,KCore.SoulLog,KCore.SoulPlanks,
    		KCore.ShinyLogBark,KCore.SoulWoodStairs,KCore.MysticWoodHalfSlab,KCore.MysticWoodDoubleSlab,KCore.ShinyWoodHalfSlab,
    		KCore.ShinyWoodDoubleSlab,KCore.SoulWoodHalfSlab,KCore.SoulWoodDoubleSlab,KCore.MysticLogBark,KCore.SoulLogBark,
    		KCore.Stripped_Mystic_Log,KCore.Stripped_Shiny_Log,KCore.Stripped_Soul_Log,KCore.Mystic_Wood_Trap_Door,KCore.Shiny_Wood_Trap_Door,
    		KCore.Soul_Wood_Trap_Door,KCore.Mystic_Wood_Door,KCore.Shiny_Wood_Door,KCore.Soul_Wood_Door,KCore.Redwood_log_full,
    		KCore.Redwood_planks,KCore.Redwood_log_size_6,KCore.Redwood_log_size_5,KCore.Redwood_log_size_4,KCore.Redwood_log_size_3
    		,KCore.Redwood_log_size_2,KCore.Redwood_log_size_1
    		);
    private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};

    public ItemMysticAxe(String Name,CreativeTabs tab,Item.ToolMaterial material)
    {
        super(Name,tab,1F,1F,material, EFFECTIVE_ON);
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiencyOnProperMaterial;
    }
    

    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Durability: " + (this.getMaxDamage()-this.getDamage(stack)));
    }
}