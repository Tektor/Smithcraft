package tektor.minecraft.smithcraft;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import tektor.minecraft.smithcraft.blocks.OreBlock;
import tektor.minecraft.smithcraft.items.OreItem;
import tektor.minecraft.smithcraft.player.Tracker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="Smithcraft", name="Smithcraft", version="0.0.1")
@NetworkMod(clientSideRequired=true)
public class SmithcraftBase {

        @Instance(value = "Smithcraft")
        public static SmithcraftBase instance;
       
        @SidedProxy(clientSide="tektor.minecraft.smithcraft.client.ClientProxy", serverSide="tektor.minecraft.smithcraft.CommonProxy")
        public static CommonProxy proxy;

		public static int oreBlockID;
		public static int oreItemID;
		public static Block oreBlock;
		public static Item ore;
       
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	Configuration config = new Configuration(
    				event.getSuggestedConfigurationFile());
    		config.load();

    		oreBlockID = config.get(Configuration.CATEGORY_BLOCK, "oreBlockID", 900)
    				.getInt();
    		oreItemID = config.get(Configuration.CATEGORY_ITEM, "oreItemID", 7900)
    				.getInt();
    		config.save();
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
        		registerIDs();
        		registerBlocks();
        		registerItems();
                proxy.registerRenderers();
                
        }

		private void registerIDs() {
			oreBlock = new OreBlock(oreBlockID);
			ore = new OreItem(oreItemID);
			
		}
        private void registerBlocks() {
        	LanguageRegistry.addName(oreBlock, "Ore Block");
            MinecraftForge.setBlockHarvestLevel(oreBlock, "pickaxe", 0);
            GameRegistry.registerBlock(oreBlock, "oreBlock");
			
		}
        
        private void registerItems() {
        	GameRegistry.registerItem(ore, "ore");
        	LanguageRegistry.addName(ore, "Ore");
			
		}

		@EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        	Tracker handler = new Tracker();
            MinecraftForge.EVENT_BUS.register(handler);
            GameRegistry.registerPlayerTracker(handler);
        }
}
