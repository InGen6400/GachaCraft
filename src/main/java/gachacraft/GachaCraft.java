package gachacraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gachacraft.common.block.MagicStoneOre;
import gachacraft.common.world.MagicStoneOreGen;
import gachacraft.gacha.block.BlockGachaCore;
import gachacraft.gacha.item.ItemGachaTicket;
import gachacraft.gacha.item.ItemMagicStone;
import gachacraft.gacha.tileentity.TileEntityGachaCore;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "Gacha_Craft", name = "GachaCraft", version = "1.0b")
public class GachaCraft {

	public static Item GachaTicket;
	public static Item MagicStone;

	public static Block GachaCore;
	public static Block MagicStoneOre;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
	}

	@Mod.EventHandler
	public void Init(FMLPreInitializationEvent event){

		GachaTicket = new ItemGachaTicket();
		MagicStone = new ItemMagicStone();

		GameRegistry.registerItem(GachaTicket, "GachaTicket");
		GameRegistry.registerItem(MagicStone, "MagicStone");

		GachaCore = new BlockGachaCore();
		MagicStoneOre = new MagicStoneOre();

		GameRegistry.registerBlock(GachaCore, "GachaCore");
		GameRegistry.registerBlock(MagicStoneOre, "MagicStoneOre");
		GameRegistry.registerTileEntity(TileEntityGachaCore.class, "GachaCoreTile");

        GameRegistry.addRecipe(new ItemStack(GachaCraft.GachaCore),
				"GRG",
				"RMR",
				"GRG",
				'G',Blocks.glowstone,
				'M',GachaCraft.MagicStone,
				'R',Items.redstone
		);
		GameRegistry.registerWorldGenerator(new MagicStoneOreGen(), 0);
	}

}
