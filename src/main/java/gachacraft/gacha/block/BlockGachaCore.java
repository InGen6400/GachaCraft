package gachacraft.gacha.block;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gachacraft.GachaCraft;
import gachacraft.gacha.PrizeRarity;
import gachacraft.gacha.Prizes;
import gachacraft.gacha.tileentity.TileEntityGachaCore;
import gachacraft.helper.LogChatHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGachaCore extends BlockContainer{

    @SideOnly(Side.CLIENT)
    private IIcon Icon;

    private Prizes[] prizes;

	public BlockGachaCore() {
		super(Material.rock);
        setCreativeTab(CreativeTabs.tabBlock);/*クリエイティブタブの選択*/
        setBlockName("GachaCore");/*システム名の設定*/
        setBlockTextureName("GachaCraft:GachaCore");/*ブロックのテクスチャの指定(複数指定の場合は消してください)*/
        setHardness(1.5F);/*硬さ*/
        setResistance(1.0F);/*爆破耐性*/
        setStepSound(Block.soundTypeStone);/*ブロックの上を歩いた時の音*/
        setLightOpacity(1);/*ブロックの透過係数。デフォルト０（不透過）*/
        setLightLevel(1.0F);/*明るさ 1.0F = 15*/
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);/*当たり判定*/
        PrizeInit();
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

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ){
		TileEntityGachaCore tile = (TileEntityGachaCore)world.getTileEntity(x, y, z);
		LogChatHelper.player = player;
		if(!world.isRemote && tile.isPatternVaild(world)){
	        for (Object o : world.loadedEntityList) {
	            Entity e = (Entity) o;
	            if (!e.isDead && e instanceof EntityItem){
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

	            	}else if(itemStack.getItem() == GachaCraft.MagicStone && itemStack.stackSize >= 4){

	            		if(itemStack.stackSize >= 64){
	            			prizes[PrizeRarity.Scrap].setRateIncrease(-25);
		            		prizes[PrizeRarity.Common].setRateIncrease(-20);
		            		prizes[PrizeRarity.Basic].setRateIncrease(-70);
		            		prizes[PrizeRarity.Rare].setRateIncrease(15);
		            		prizes[PrizeRarity.Epic].setRateIncrease(2);
		            		itemStack.stackSize -= 4;
	            		}else if(itemStack.stackSize >= 4){
	            			prizes[PrizeRarity.Scrap].setRateIncrease(-25);
		            		prizes[PrizeRarity.Common].setRateIncrease(-20);
		            		prizes[PrizeRarity.Basic].setRateIncrease(-70);
		            		prizes[PrizeRarity.Rare].setRateIncrease(15);
		            		prizes[PrizeRarity.Epic].setRateIncrease(2);
		            		itemStack.stackSize -= 4;
	            		}

	            	}else{
	            		continue;
	            	}

	            	double dx = e.posX - x;
	            	double dy = e.posY - (y + 1);
	            	double dz = e.posZ - z;

	            	double dist = dx*dx + dy*dy + dz*dz;

	            	if(dist < 1){
	            		Prizes prize = dropPrizeDraw();
	            		if(prize != null){
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
	            			LogChatHelper.DebugLog("You got " + color + rarity +EnumChatFormatting.WHITE +" item : "+ PrizeItem.getDisplayName() + "x" + PrizeItem.stackSize);
	            			dropBlockAsItem(world, x, y + 1, z, PrizeItem);
	            			return true;
	            		}else{
	            			LogChatHelper.DebugLog("Gacha Core Error: no Items");
	            			return true;
	            		}
	            	}
	            }
	        }
		}
		return true;
    }

	private Prizes dropPrizeDraw(){

		int RateAll = 0;
		int max = 0;
		Random  rand = new Random();

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
		return null;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityGachaCore tile = (TileEntityGachaCore)world.getTileEntity(x, y, z);
		if(tile.isPatternVaild(world)){

	        for (int l = x - 5; l <= x + 5; ++l)
	        {
	            for (int i1 = z - 5; i1 <= z + 5; ++i1)
	            {
	                if (l > x - 5 && l < x + 5 && i1 == z - 1)
	                {
	                    i1 = z + 2;
	                }

	                if (random.nextInt(16) == 0)
	                {
	                    for (int j1 = y; j1 <= y + 1; ++j1)
	                    {
	                    	if(random.nextInt(3) == 0){
	                            world.spawnParticle("enchantmenttable",
	                            		(double)x + 0.5D, (double)y + 2.0D, (double)z + 0.5D,
	                            		(double)((float)(l - x) + random.nextFloat()) - 0.5D,
	                            		(double)((float)(j1 - y) - random.nextFloat() - 1.0F),
	                            		(double)((float)(i1 - z) + random.nextFloat()) - 0.5D);
                            }else{
	                            world.spawnParticle("portal",
	                            		(double)x + 0.5D, (double)y + 2.0D, (double)z + 0.5D,
	                            		(double)((float)(l - x) + random.nextFloat()) - 0.5D,
	                            		(double)((float)(j1 - y) - random.nextFloat() - 1.0F),
	                            		(double)((float)(i1 - z) + random.nextFloat()) - 0.5D);
                            }
	                    }
	                }
	            }
	        }
		}
	}

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
        //ブロックを左クリックした際の動作
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock){
        //周囲のブロックが更新された際の動作
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random){
        //ドロップするアイテムを返す
        return quantityDroppedWithBonus(fortune, random);
    }

    @Override
    public int quantityDropped(Random random){
        //ドロップさせる量を返す
        return 1;
    }

    private void LaunchFireworks(World world, EntityPlayer player, int x, int y, int z, int num){
    	ItemStack itemFireworks = new ItemStack(Items.fireworks);
    	ItemStack field_92102_a = new ItemStack(Items.firework_charge);
    	NBTTagCompound nbttagcompound = new NBTTagCompound();
    	NBTTagCompound nbttagcompound1 = new NBTTagCompound();

    	Random random = new Random();
        byte b0 = 0;
        ArrayList arraylist = new ArrayList();


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
                		(double)(x + random.nextInt(3) - 1.5f),
                		(double)(y + 4),
                		(double)(z + random.nextInt(3) - 1.5f),
                		itemFireworks);
                world.spawnEntityInWorld(entityfireworkrocket);
        	}
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.Icon = par1IconRegister.registerIcon("GachaCraft:GachaCore");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
    	return Icon;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGachaCore();
	}

}
