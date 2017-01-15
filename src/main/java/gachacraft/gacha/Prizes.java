package gachacraft.gacha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gachacraft.helper.LogChatHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Prizes {

	private List<ItemStack> items = new ArrayList<ItemStack>();
	private double rate;
	private int rarity;

	private Random random = new Random();

	public Prizes(double rate, int rarity) {
		this.rate = rate;
		this.rarity = rarity;
	}

	public void addItems(ItemStack item){
		items.add(item);
	}

	public void addItems(Item item){
		items.add(new ItemStack(item));
	}

	public void addItems(Item item, int num){
		items.add(new ItemStack(item, num));
	}

	public void addItems(Block item){
		items.add(new ItemStack(item));
	}

	public void addItems(Block item, int num){
		items.add(new ItemStack(item, num));
	}

	public double getRate(){
		return rate;
	}

	public int getRarity(){
		return rarity;
	}

	public int getItemAmount(){
		return items.size();
	}

	public ItemStack getPrize(){
		if(items.size() == 1){
			return items.get(random.nextInt(items.size()));
		}else{
			LogChatHelper.DebugLog("get0");
			return items.get(0);
		}
	}
}

