package gachacraft.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class LogChatHelper {

	public static Minecraft minecraft;
	public static EntityPlayer player;

	public LogChatHelper() {
		minecraft = Minecraft.getMinecraft();
	}

	public static void DebugLog(String str){
		player.addChatMessage(new ChatComponentTranslation(str));
	}

}
