package gachacraft.gacha.tileentity;

import gachacraft.GachaCraft;
import gachacraft.multiblock.MultiblockPattern;
import gachacraft.multiblock.TileEntityMultiblock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class TileEntityGachaCore extends TileEntityMultiblock{


	@Override
	public void initPattern() {

		Block[][][] pattern = new Block[3][][];
		pattern[0] = new Block[][]{
			{Blocks.log, 				Blocks.brick_stairs, 	Blocks.log},
			{Blocks.brick_stairs, 	GachaCraft.GachaCore, 	Blocks.brick_stairs},
			{Blocks.log, 				Blocks.brick_stairs, 	Blocks.log}
		};

		pattern[1] = new Block[][]{
			{Blocks.glass_pane, 		Blocks.air, 	Blocks.glass_pane},
			{Blocks.air, 				Blocks.air, 	Blocks.air},
			{Blocks.glass_pane, 		Blocks.air, 	Blocks.glass_pane}
		};

		pattern[2] = new Block[][]{
			{Blocks.leaves, 				Blocks.stone_stairs, 	Blocks.leaves},
			{Blocks.stone_stairs, 		Blocks.glowstone, 		Blocks.stone_stairs},
			{Blocks.leaves, 				Blocks.stone_stairs, 	Blocks.leaves}
		};

		this.pattern = new MultiblockPattern(pattern, 0, 1, 1);
	}



}
