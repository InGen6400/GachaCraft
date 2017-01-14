package gachacraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gachacraft.gacha.block.BlockGachaCore;
import gachacraft.gacha.item.ItemGachaTicket;
import gachacraft.gacha.item.ItemMagicStone;
import gachacraft.gacha.tileentity.TileEntityGachaCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@Mod(modid = "Gacha_Craft")
public class GachaCraft {

	public static Item GachaTicket;
	public static Item MagicStone;

	public static Block GachaCore;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		GachaTicket = new ItemGachaTicket();
		MagicStone = new ItemMagicStone();
		GachaCore = new BlockGachaCore();
		GameRegistry.registerItem(GachaTicket, "GachaTicket");
		GameRegistry.registerItem(MagicStone, "MagicStone");
		GameRegistry.registerBlock(GachaCore, "GachaCore");
		GameRegistry.registerTileEntity(TileEntityGachaCore.class, "GachaCoreTile");
	}

}
