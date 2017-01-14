package gachacraft.multiblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileEntityMultiblock extends TileEntity{

	protected MultiblockPattern pattern;

	public abstract void initPattern();

	public TileEntityMultiblock() {
		initPattern();
	}

	public boolean isPatternVaild(World world){

		int x,y,z;

		for(int offy=0;offy < pattern.getHeight();offy++){
			for(int offx=0;offx < pattern.getWidthX();offx++){
				for(int offz=0;offz < pattern.getWidthZ();offz++){

					y = offy + this.yCoord - pattern.offset[0];
					x = offx + this.xCoord - pattern.offset[1];
					z = offz + this.zCoord - pattern.offset[2];

					if(world.getBlock(x, y, z) != pattern.pattern[offy][offx][offz]){
						return false;
					}
				}
			}
		}
		return true;
	}
}
