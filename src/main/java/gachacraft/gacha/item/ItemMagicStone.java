package gachacraft.gacha.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMagicStone extends Item{

	public ItemMagicStone() {

		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("MagicStone");
		setTextureName("gachacraft:MagicStone");
		this.setHasSubtypes(true);

	}
}
