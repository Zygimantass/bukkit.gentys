package me.mchackerslt.gentys;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Text {
	public static ChatColor errorRed = ChatColor.DARK_RED;
	public static ChatColor infoGreen = ChatColor.GREEN;
	public static ChatColor epicGold = ChatColor.GOLD;
	public static void Error(Player player, String text)
	{
		player.sendMessage(errorRed + " " + text);
	}
	public static void Info(Player player, String text)
	{
		player.sendMessage(infoGreen + " " + text);
	}
	public static void Epic(Player player, String text)
	{
		player.sendMessage(epicGold + " " + text);
	}
}
