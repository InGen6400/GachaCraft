package gachacraft.gacha.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGachaTicket extends Item{

	public ItemGachaTicket() {

		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("GachaTicket");
		setTextureName("gachacraft:GachaTicket");
		setMaxStackSize(1);

	}

}
