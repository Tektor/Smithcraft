package tektor.minecraft.smithcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreItem extends Item{

	public OreItem(int par1) {
		super(par1);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("ore");
		this.setHasSubtypes(true);
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        switch(par1ItemStack.getItemDamage())
        {
        case 0: return "ironOre";
        case 1: return "dullCopperOre";
        default: return "wtfOre";
        }
    }
	
	

}
