package com.dmonsters.items;

import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Harpoon extends Item {
	
	private int attackDamage = 1;
	
    public Harpoon(String harpoonType, int maxDamage, int _attackDamage) {
        setRegistryName("harpoon_" + harpoonType);
        setUnlocalizedName(MainMod.MODID + ".harpoon_" + harpoonType);
        GameRegistry.register(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
        this.maxStackSize = 1;
        attackDamage = _attackDamage;
        if (maxDamage > -1)
        	this.setMaxDamage(maxDamage);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	float damage = 1;
    	if (target instanceof EntityTopielec)
    		damage = attackDamage;
    	target.attackEntityFrom(DamageSource.GENERIC, damage);
    	stack.damageItem(1, attacker);
        return true;
    }
}