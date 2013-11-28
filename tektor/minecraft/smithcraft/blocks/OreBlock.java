package tektor.minecraft.smithcraft.blocks;

import java.util.ArrayList;
import java.util.Random;

import tektor.minecraft.smithcraft.SmithcraftBase;
import tektor.minecraft.smithcraft.player.SkillInformation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class OreBlock extends BlockOre {

	public OreBlock(int par1) {
		super(par1);
		this.setHardness(2.0f);
		setUnlocalizedName("OreBlock");
        setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	 public void harvestBlock(World par1World, EntityPlayer player, int par3, int par4, int par5, int par6)
    {
		if (!par1World.isRemote) {
			Float mining = ((SkillInformation) player
					.getExtendedProperties(SkillInformation.IDENTIFIER)).mining;
			player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
			player.addExhaustion(0.025F);
			Random rand = par1World.rand;
			if(rand.nextInt(100) > mining)
			{
				((SkillInformation) player
						.getExtendedProperties(SkillInformation.IDENTIFIER))
						.mined(0);
				player.addChatMessage("There was no ore to mine for you.");
				return;
			}
			
			float iron = (mining > 75) ? 75 : mining;
			float dullCopper = 0.0f;
			if(mining > 10)dullCopper = (mining > 75) ? 50 : ((mining-10) * 0.769230769f);
			float sum = iron + dullCopper;
			float fortune = rand.nextFloat() * sum;
			ArrayList<ItemStack> items;
			if (fortune > iron)
			{
				items = fortuneWheel(1, 15f, 6, mining, rand, player);
				
			}
			else {
				items = fortuneWheel(0, 10f, 6, mining, rand, player);
			}
			
			for (ItemStack is : items) {
				this.dropBlockAsItem_do(par1World, par3, par4, par5, is);
			}	
		}
		
		
    }
	private ArrayList<ItemStack> fortuneWheel(int meta, float divider, int random, float mining, Random rand, EntityPlayer player)
	{
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(SmithcraftBase.ore.itemID, 1, meta));
		for (int i = 0; i < (mining / divider); i++) {
			if ((rand.nextInt(random)) < 3) {
				items.add(new ItemStack(SmithcraftBase.ore.itemID, 1, meta));
			}

		}
		((SkillInformation) player
				.getExtendedProperties(SkillInformation.IDENTIFIER))
				.mined(meta+1);

		return items;
	}

}
