package me.mchackerslt.gentys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Who {
	public static ChatColor dred = ChatColor.DARK_RED;
	public static ChatColor dgreen = ChatColor.DARK_GREEN;
	public static ChatColor green = ChatColor.GREEN;
	public static ChatColor red = ChatColor.RED;
	public static void whoIndenai(Player player)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/usr/1.6/plugins/Gentys/list.txt"));
			String line;
			while ((line = br.readLine()) != null) {
			   String[] parts = line.split(":");
			   String nick = parts[0];
			   String gentis = parts[1];
			   if (gentis.equalsIgnoreCase("indenai"))
			   {
				   if (Bukkit.getServer().getOfflinePlayer(nick).isOnline())
				   {
					   if(Bukkit.getServer().getOfflinePlayer(nick).isOp())
					   {
						   player.sendMessage(ChatColor.GOLD + nick + ChatColor.DARK_RED + " yra " + ChatColor.GREEN + "prisijung�s(-us)");
					   }
					   else
					   {
						   player.sendMessage(ChatColor.DARK_GREEN + nick + ChatColor.DARK_RED + " yra " + ChatColor.GREEN + "prisijung�s(-us)");
					   }
				   }
				   else
				   {
					   if (Bukkit.getServer().getOfflinePlayer(nick).isOp())
					   {
						   player.sendMessage(ChatColor.GOLD + nick + " yra " + ChatColor.RED + "atsijung�s(-us)");
					   }
					   else
					   {
						   player.sendMessage(ChatColor.DARK_GREEN + nick + " yra " + ChatColor.RED + "atsijung�s(-us)");
					   }
			   }
			   }
			}
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void whoBaltai(Player player)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/usr/1.6/plugins/Gentys/list.txt"));
			String line;
			while ((line = br.readLine()) != null) {
			   String[] parts = line.split(":");
			   String nick = parts[0];
			   if (Bukkit.getServer().getOfflinePlayer(nick).isOnline())
			   {
				   if(Bukkit.getServer().getOfflinePlayer(nick).isOp())
				   {
					   player.sendMessage(ChatColor.GOLD + nick + dred + " yra " + green + "prisijung�s(-us)");
				   }
				   else
				   {
					   player.sendMessage(dgreen + nick + dred + " yra " + green + "prisijung�s(-us)");
				   }
			   }
			   else
			   {
				   if (Bukkit.getServer().getOfflinePlayer(nick).isOp())
				   {
					   player.sendMessage(ChatColor.GOLD + nick + " yra " + red + "atsijung�s(-us)");
				   }
				   else
				   {
					   player.sendMessage(dgreen + nick + " yra " + red + "atsijung�s(-us)");
				   }
			   }
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
