package tektor.minecraft.smithcraft.blocks;

import java.util.ArrayList;
import java.util.Random;

import tektor.minecraft.smithcraft.SmithcraftBase;
import tektor.minecraft.smithcraft.player.SkillInformation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			Random rand = par1World.rand;
			float fortune = rand.nextFloat();
			if(rand.nextInt(100) > mining)
			{
				((SkillInformation) player
						.getExtendedProperties(SkillInformation.IDENTIFIER))
						.mined(0);
				player.addChatMessage("There was no ore to mine for you.");
				return;
			}
			
			float value = fortune * mining;
			if (fortune > 0.5 && mining > 5)
			{
				items.add(new ItemStack(SmithcraftBase.ore.itemID, 1, 1));
				for (int i = 0; i < (mining / 15.0f); i++) {
					if ((rand.nextInt(6)) < 3) {
						items.add(new ItemStack(SmithcraftBase.ore.itemID, 1, 1));
					}

				}
				for (ItemStack is : items) {
					this.dropBlockAsItem_do(par1World, par3, par4, par5, is);
				}
				((SkillInformation) player
						.getExtendedProperties(SkillInformation.IDENTIFIER))
						.mined(2);
			}
			else {
				items.add(new ItemStack(SmithcraftBase.ore.itemID, 1, 0));
				for (int i = 0; i < (mining / 10.0f); i++) {
					if ((rand.nextInt(6)) < 3) {
						items.add(new ItemStack(SmithcraftBase.ore.itemID, 1, 0));
					}

				}
				for (ItemStack is : items) {
					this.dropBlockAsItem_do(par1World, par3, par4, par5, is);
				}
				((SkillInformation) player
						.getExtendedProperties(SkillInformation.IDENTIFIER))
						.mined(1);
			}
		}
    }

}
