package gachacraft.multiblock;

import net.minecraft.block.Block;

public class MultiblockPattern {

	public Block[][][] pattern;
	public final int[] offset;

	public MultiblockPattern(Block[][][] pattern) {
		this.pattern = pattern;
		offset = new int[]{1,1,1};
	}

	public MultiblockPattern(Block[][][] pattern, int offY, int offX, int offZ) {
		this.pattern = pattern;
		offset = new int[]{offY,offX,offZ};
	}

	public MultiblockPattern(Block[][][] pattern, int[] offset) {
		this.pattern = pattern;
		this.offset = offset;
	}

	public int[] getOffset(){
		return offset;
	}

	public int getHeight(){
		return pattern.length;
	}

	public int getWidthX(){
		return pattern[0].length;
	}

	public int getWidthZ(){
		return pattern[0][0].length;
	}
}
