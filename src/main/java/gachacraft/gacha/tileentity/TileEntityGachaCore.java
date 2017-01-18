package gachacraft.gacha.tileentity;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;

import gachacraft.GachaCraft;
import gachacraft.gacha.PrizeRarity;
import gachacraft.gacha.Prizes;
import gachacraft.gacha.entity.EntityGachaPivot;
import gachacraft.helper.LogChatHelper;
import gachacraft.multiblock.MultiblockPatterns;
import gachacraft.multiblock.TileEntityMultiblock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class TileEntityGachaCore extends TileEntityMultiblock{

    private Prizes[] prizes;

    public boolean chainDrawing = false;

    private World world;
    private EntityPlayer player;
    private int x,y,z;

	public TileEntityGachaCore() {
		PrizeInit();
		initPattern();
	}

	@Override
	public void initPattern() {

		Block[][][] pattern = new Block[4][][];
		pattern[0] = new Block[][]{
			{Blocks.log, 				Blocks.brick_stairs, 	Blocks.log},
			{Blocks.brick_stairs, 	GachaCraft.GachaCore, 	Blocks.brick_stairs},
			{Blocks.log, 				Blocks.brick_stairs, 	Blocks.log}
		};

		pattern[1] = new Block[][]{
			{Blocks.glass_pane, 		Blocks.air, 		Blocks.glass_pane},
			{Blocks.air, 				Blocks.air, 		Blocks.air},
			{Blocks.glass_pane, 		Blocks.air, 		Blocks.glass_pane}
		};

		pattern[2] = new Block[][]{
			{Blocks.glass_pane, 		Blocks.air, 		Blocks.glass_pane},
			{Blocks.air, 				Blocks.air, 		Blocks.air},
			{Blocks.glass_pane, 		Blocks.air, 		Blocks.glass_pane}
		};

		pattern[3] = new Block[][]{
			{Blocks.leaves, 			Blocks.bookshelf, 		Blocks.leaves},
			{Blocks.bookshelf, 		Blocks.glowstone, 		Blocks.bookshelf},
			{Blocks.leaves, 			Blocks.bookshelf, 		Blocks.leaves}
		};

		this.pattern = new MultiblockPatterns(pattern, 0, 1, 1);
	}

	public void PrizeInit(){

		prizes = new Prizes[PrizeRarity.all];

		prizes[PrizeRarity.Secret] 	= new Prizes(0, PrizeRarity.Secret);
		prizes[PrizeRarity.Legend] 	= new Prizes(1, PrizeRarity.Legend);//1
		prizes[PrizeRarity.Epic] 		= new Prizes(10, PrizeRarity.Epic);//11
		prizes[PrizeRarity.Rare] 		= new Prizes(25, PrizeRarity.Rare);//36
		prizes[PrizeRarity.Common] 	= new Prizes(50, PrizeRarity.Common);//86
		prizes[PrizeRarity.Basic] 	= new Prizes(100, PrizeRarity.Basic);
		prizes[PrizeRarity.Scrap] 	= new Prizes(30, PrizeRarity.Scrap);

		prizes[PrizeRarity.Secret].addItems(Blocks.mob_spawner);

		prizes[PrizeRarity.Legend].addItems(Items.nether_star);
		prizes[PrizeRarity.Legend].addItems(Items.diamond, 64);
		prizes[PrizeRarity.Legend].addItems(Blocks.dragon_egg);

		prizes[PrizeRarity.Epic].addItems(Items.diamond, 4);
		prizes[PrizeRarity.Epic].addItems(Items.diamond_pickaxe);
		prizes[PrizeRarity.Epic].addItems(Items.diamond_axe);
		prizes[PrizeRarity.Epic].addItems(Items.diamond_sword);
		prizes[PrizeRarity.Epic].addItems(Items.iron_ingot, 64);
		prizes[PrizeRarity.Epic].addItems(Items.gold_ingot, 32);
		prizes[PrizeRarity.Epic].addItems(Blocks.glowstone, 32);
		prizes[PrizeRarity.Epic].addItems(Items.skull, 1, 1);

		prizes[PrizeRarity.Rare].addItems(Items.gold_ingot, 8);
		prizes[PrizeRarity.Rare].addItems(Items.nether_wart, 4);
		prizes[PrizeRarity.Rare].addItems(Items.blaze_rod, 8);
		prizes[PrizeRarity.Rare].addItems(Items.iron_axe, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_pickaxe, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_shovel, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_sword, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_chestplate, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_helmet, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_boots, 1);
		prizes[PrizeRarity.Rare].addItems(Items.iron_leggings, 1);
		prizes[PrizeRarity.Rare].addItems(Items.ghast_tear, 1);
		prizes[PrizeRarity.Rare].addItems(Blocks.obsidian, 2);
		prizes[PrizeRarity.Rare].addItems(Blocks.brick_block, 32);

		prizes[PrizeRarity.Common].addItems(Items.iron_ingot, 16);
		prizes[PrizeRarity.Common].addItems(Items.fishing_rod, 1);
		prizes[PrizeRarity.Common].addItems(Items.cooked_beef, 8);
		prizes[PrizeRarity.Common].addItems(Items.cooked_porkchop, 8);
		prizes[PrizeRarity.Common].addItems(Items.redstone, 16);
		prizes[PrizeRarity.Common].addItems(Blocks.lapis_block, 4);
		prizes[PrizeRarity.Common].addItems(Blocks.log, 16);
		prizes[PrizeRarity.Common].addItems(Blocks.glass, 16);
		prizes[PrizeRarity.Common].addItems(Blocks.vine, 16);
		prizes[PrizeRarity.Common].addItems(Blocks.waterlily, 8);

		prizes[PrizeRarity.Basic].addItems(Items.coal, 4);
		prizes[PrizeRarity.Basic].addItems(Items.apple, 4);
		prizes[PrizeRarity.Basic].addItems(Items.stone_axe, 1);
		prizes[PrizeRarity.Basic].addItems(Items.stone_pickaxe, 1);
		prizes[PrizeRarity.Basic].addItems(Items.stone_sword, 1);
		prizes[PrizeRarity.Basic].addItems(Items.baked_potato, 8);
		prizes[PrizeRarity.Basic].addItems(Items.stone_sword, 1);
		prizes[PrizeRarity.Basic].addItems(Blocks.stonebrick, 16);
		prizes[PrizeRarity.Common].addItems(Blocks.sapling, 8);

		prizes[PrizeRarity.Scrap].addItems(Items.wheat_seeds, 8);
		prizes[PrizeRarity.Scrap].addItems(Items.wooden_hoe, 1);
		prizes[PrizeRarity.Scrap].addItems(Items.leather_boots, 1);
		prizes[PrizeRarity.Scrap].addItems(Blocks.cobblestone, 16);

	}

	public void Gachaling(World world, EntityPlayer player, int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
		LogChatHelper.player = player;
		if(!chainDrawing){
			this.world = world;
			this.player = player;
			if(setUpGacha(x, y, z) && !chainDrawing && !world.isRemote){
				getPrize();
			}
		}
	}

	public void getPrize(){
		Prizes prize = dropPrizeDraw();
		DoResult(prize, x, y, z);
	}

	public boolean setUpGacha(int x, int y, int z){
		if(!chainDrawing){
			if(isPatternVaild(world)){
		        for (Object o : world.loadedEntityList) {
		            Entity e = (Entity) o;

	            	double dx = e.posX - x;
	            	double dy = e.posY - (y + 1);
	            	double dz = e.posZ - z;

	            	double dist = dx*dx + dy*dy + dz*dz;

		            if (dist < 1 && !e.isDead && e instanceof EntityItem){
		            	EntityItem entityItem = (EntityItem)e;
		            	ItemStack itemStack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);
		            	if(itemStack.getItem() == GachaCraft.GachaTicket){

		            		prizes[PrizeRarity.Secret].rateReset();
		            		prizes[PrizeRarity.Legend].rateReset();
		            		prizes[PrizeRarity.Epic].rateReset();
		            		prizes[PrizeRarity.Rare].rateReset();
		            		prizes[PrizeRarity.Common].rateReset();
		            		prizes[PrizeRarity.Basic].rateReset();
		            		prizes[PrizeRarity.Scrap].rateReset();
		            		entityItem.lifespan = 0;
		            		return true;

		            	}else if(itemStack.getItem() == GachaCraft.MagicStone && itemStack.stackSize >= 4){

		            		if(itemStack.stackSize >= 16){
		            			world.spawnEntityInWorld(new EntityGachaPivot(world, player, entityItem, x, y, z, this));
		            			prizes[PrizeRarity.Scrap].setRateIncrease(-30);
			            		prizes[PrizeRarity.Common].setRateIncrease(-20);
			            		prizes[PrizeRarity.Basic].setRateIncrease(-70);
			            		prizes[PrizeRarity.Rare].setRateIncrease(15);
			            		prizes[PrizeRarity.Epic].setRateIncrease(5);
				            	chainDrawing = true;
			            		return false;
		            		}else if(itemStack.stackSize >= 4){
		            			prizes[PrizeRarity.Scrap].setRateIncrease(-30);
			            		prizes[PrizeRarity.Common].setRateIncrease(-20);
			            		prizes[PrizeRarity.Basic].setRateIncrease(-70);
			            		prizes[PrizeRarity.Rare].setRateIncrease(15);
			            		prizes[PrizeRarity.Epic].setRateIncrease(5);
			            		if(!world.isRemote){
			            			itemStack.stackSize -= 4;
			            		}
			            		return true;
		            		}
		            	}
		            }
		        }
			}
		}
		return false;
	}

	private Prizes dropPrizeDraw(){

		int RateAll = 0;
		int max = 0;
		Random  rand = new Random();

		if(!world.isRemote){
			for(int i=0; i<prizes.length; i++){
				RateAll += prizes[i].getRate();
			}
			int selector = rand.nextInt(RateAll);

			for(int i=0; i<prizes.length; i++){
				max += prizes[i].getRate();
				if(selector <= max){
					return prizes[i];
				}
			}
		}
		return null;
	}

	private void DoResult(Prizes prize, int x, int y, int z){
			ItemStack PrizeItem = prize.getPrize().copy();
			EnumChatFormatting color;
			String rarity;
			switch(prize.getRarity()){
			case PrizeRarity.Secret:
				color = EnumChatFormatting.AQUA;
				rarity = "Secret Rare";
				break;
			case PrizeRarity.Legend:
				color = EnumChatFormatting.GOLD;
				rarity = "Legendary";
				world.playSoundAtEntity(player, "mob.enderdragon.growl", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "ambient.weather.thunder", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "random.levelup", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "random.orb", 1.0F, 1.0F);
				LaunchFireworks(world, player, x, y, z, 4);
				break;
			case PrizeRarity.Epic:
				color = EnumChatFormatting.LIGHT_PURPLE;
				rarity = "Epic";
				world.playSoundAtEntity(player, "ambient.weather.thunder", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "random.levelup", 1.0F, 1.0F);
				world.playSoundAtEntity(player, "random.orb", 1.0F, 1.0F);
				LaunchFireworks(world, player, x, y, z, 1);
				break;
			case PrizeRarity.Rare:
				color = EnumChatFormatting.AQUA;
				rarity = "Rare";
				world.playSoundAtEntity(player, "random.levelup", 0.4F, 1.0F);
				break;
			case PrizeRarity.Common:
				color = EnumChatFormatting.GREEN;
				rarity = "Common";
				world.playSoundAtEntity(player, "random.orb", 0.5F, 1.0F);
				break;
			case PrizeRarity.Basic:
				color = EnumChatFormatting.RED;
				rarity = "Basic";
				world.playSoundAtEntity(player, "mob.chicken.plop", 1.0F, 1.0F);
				break;
			case PrizeRarity.Scrap:
				color = EnumChatFormatting.DARK_RED;
				rarity = "Scrap";
				world.playSoundAtEntity(player, "mob.silverfish.say", 1.0F, 1.0F);
				break;
				default:
					color = EnumChatFormatting.WHITE;
					rarity = null;
			}
			if(!world.isRemote){
			LogChatHelper.DebugLog("You got " + color + rarity +EnumChatFormatting.WHITE +" item : "+ PrizeItem.getDisplayName() + "x" + PrizeItem.stackSize);
			EntityItem entityItem = new EntityItem(world, x+0.5D, y + 1.5D, z+0.5D, PrizeItem);
			entityItem.motionX = 0;
			entityItem.motionY = 0.3;
			entityItem.motionZ = 0;
			entityItem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityItem);
			return;
		}else{
			LogChatHelper.DebugLog("Gacha Core Error: no Items");
			return;
		}
	}

	private void LaunchFireworks(World world, EntityPlayer player, int x, int y, int z, int num){
    	ItemStack itemFireworks = new ItemStack(Items.fireworks);
    	ItemStack field_92102_a = new ItemStack(Items.firework_charge);
    	NBTTagCompound nbttagcompound = new NBTTagCompound();
    	NBTTagCompound nbttagcompound1 = new NBTTagCompound();

    	Random random = new Random();
        byte b0 = 0;
        ArrayList<Integer> arraylist = new ArrayList<Integer>();


        arraylist.add(Integer.valueOf(ItemDye.field_150922_c[random.nextInt(16)]));
        arraylist.add(Integer.valueOf(ItemDye.field_150922_c[random.nextInt(16)]));
        arraylist.add(Integer.valueOf(ItemDye.field_150922_c[random.nextInt(16)]));

        nbttagcompound1.setBoolean("Trail", true);
        b0 = ByteBuffer.allocate(8).putInt(random.nextInt(4)+1).get();

        int[] aint1 = new int[arraylist.size()];

        for (int l2 = 0; l2 < aint1.length; ++l2)
        {
            aint1[l2] = ((Integer)arraylist.get(l2)).intValue();
        }

        nbttagcompound1.setIntArray("Colors", aint1);
        nbttagcompound1.setByte("Type", b0);
        nbttagcompound.setTag("Explosion", nbttagcompound1);
        field_92102_a.setTagCompound(nbttagcompound);

        NBTTagCompound nbttagcompound4 = new NBTTagCompound();
        NBTTagCompound nbttagcompound5 = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();


        nbttaglist.appendTag(nbttagcompound.getCompoundTag("Explosion"));

        nbttagcompound4.setTag("Explosions", nbttaglist);
        nbttagcompound4.setByte("Flight", (byte)0);
        nbttagcompound5.setTag("Fireworks", nbttagcompound4);

        itemFireworks.setTagCompound(nbttagcompound5);
        if (!world.isRemote)
        {
        	for(int i=0; i<num; i++){
                EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(
                		world,
                		(double)(x + random.nextInt(3) - 1.0D + 0.5D),
                		(double)(y + 4),
                		(double)(z + random.nextInt(3) - 1.0D + 0.5D),
                		itemFireworks);
                world.spawnEntityInWorld(entityfireworkrocket);
        	}
        }
    }

}
