package gachacraft.gacha.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gachacraft.GachaCraft;
import gachacraft.gacha.tileentity.TileEntityGachaCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGachaCore extends BlockContainer{

    @SideOnly(Side.CLIENT)
    private IIcon Icon;

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
	}

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posX, float posY, float posZ){
		TileEntityGachaCore tile = (TileEntityGachaCore)world.getTileEntity(x, y, z);
		if(tile.isPatternVaild(world)){
	        for (Object o : world.loadedEntityList) {
	            Entity e = (Entity) o;
	            if (!e.isDead && e instanceof EntityItem && ((EntityItem)e).getDataWatcher().getWatchableObjectItemStack(10).getItem() == GachaCraft.GachaTicket){
	            	double dx = e.posX - x;
	            	double dy = e.posY - (y + 1);
	            	double dz = e.posZ - z;

	            	double dist = dx*dx + dy*dy + dz*dz;

	            	if(dist < 1){
	            		((EntityItem)e).lifespan = 0;
	            	}
	            }
	        }
		}
		return true;
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
