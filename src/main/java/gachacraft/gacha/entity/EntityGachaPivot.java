package gachacraft.gacha.entity;

import java.util.ArrayList;
import java.util.List;

import gachacraft.gacha.tileentity.TileEntityGachaCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGachaPivot extends Entity{

	private List<EntityItem> childItem = new ArrayList<EntityItem>();

	private double angle = 0;
	private double r = 0;
	private double r2 = 0;
	private int firstCount;
	private int prizeCount;
	private boolean radiusSin = false;
	private TileEntityGachaCore gacha;

	public EntityGachaPivot(World world, EntityPlayer player, EntityItem item, int x, int y, int z, TileEntityGachaCore gacha) {
		super(world);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.gacha = (TileEntityGachaCore)world.getTileEntity(x, y, z);
		setWorld(world);
		onGround = false;
		int itemCount = item.getEntityItem().stackSize;
		prizeCount = firstCount = itemCount/4;
		if(item.getEntityItem().stackSize >= 4 && item.getEntityItem().stackSize <= 64){
			for(int i=0; itemCount>=4; i++, itemCount-=4){

				if(world.isRemote){
					ItemStack tmpStack = item.getEntityItem().copy();
					tmpStack.setItemDamage(i);

					EntityItem tmpItem = this.entityDropItem(tmpStack, 0.0F);

					tmpItem.posX = x;
					tmpItem.posY = y + 1;
					tmpItem.posZ = z;
					tmpItem.noClip = true;
					tmpItem.delayBeforeCanPickup = 3000;

					tmpItem.getEntityItem().stackSize = 4;

					childItem.add(tmpItem);
					angle = 4.0D;
				}else{
					angle = 0.0D;

					item.getEntityItem().stackSize -= 4;
				}

			}
		}
	}

	public void onUpdate() {

		super.onUpdate();
		angle += 2.0D;

		if(worldObj.isRemote){
			r = Math.sin(angle * 2 * 2.0D * Math.PI / 360.0D)/2 + r2;
			if(!radiusSin){
				r2 += 0.075D;
				if(r2>=3){
					radiusSin = true;
				}
			}
			for(int i=0 ; i<prizeCount ; i++){

				double radian = (angle + i * 360.0D/firstCount) * 2.0D * Math.PI / 360.0D;

				double X = r * Math.cos(radian) + posX + 0.5D;
				double Y = posY + 1.5;
				double Z = r * Math.sin(radian) + posZ + 0.5D;
				childItem.get(i).setPosition(X, Y, Z);
			}
		}
		if(angle >= 2 * (60 * 1) && angle % (120*0.4) == 0){
			if(!worldObj.isRemote){
				gacha.getPrize();
			}
			if(prizeCount <= 1){
				gacha.chainDrawing = false;
				this.setDead();
			}
			if(prizeCount > 0){
				prizeCount--;
				if(worldObj.isRemote){
					for(int i=0; i<5; i++){
						worldObj.spawnParticle(
								"flame",
								childItem.get(prizeCount).posX + rand.nextDouble()/2 - 0.25D,
								childItem.get(prizeCount).posY + rand.nextDouble()/2 - 0.25D,
								childItem.get(prizeCount).posZ + rand.nextDouble()/2 - 0.25D,
								0, 0, 0);
					}
					childItem.get(prizeCount).setDead();
					childItem.remove(prizeCount);
				}
			}
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO 自動生成されたメソッド・スタブ

	}



}
