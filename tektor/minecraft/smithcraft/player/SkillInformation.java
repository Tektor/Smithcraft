package tektor.minecraft.smithcraft.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillInformation implements IExtendedEntityProperties {

	public static final String IDENTIFIER = "smithcraft_skill";
	public float mining = 0.0f;
	public float blacksmithing = 0.0f;
	public float maxMining = 100.0f;
	public float maxBlacksmithing = 100.0f;
	
	private final EntityPlayer player;
	
	public SkillInformation(EntityPlayer player) {
        this.player = player;
    }
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setFloat("mining", mining);
		compound.setFloat("blacksmithing", blacksmithing);
		compound.setFloat("maxMining", maxMining);
		compound.setFloat("maxBlacksmithing", maxBlacksmithing);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		mining = compound.getFloat("mining");
		blacksmithing = compound.getFloat("blacksmithing");
		maxMining = compound.getFloat("maxMining");
		maxBlacksmithing = compound.getFloat("maxBlacksmithing");

	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub

	}

	public void mined(int level) {
		if(mining < maxMining)
		{
			mining = mining + 0.1f;
			if(mining > maxMining) mining = maxMining;
			player.addChatMessage("§3 Your Mining Skill has increased! It is now " + mining + " %");
		}
		
	}

}
