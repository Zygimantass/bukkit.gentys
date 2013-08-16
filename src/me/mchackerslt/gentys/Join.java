package me.mchackerslt.gentys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Join { 
	@SuppressWarnings("resource")
	public boolean check(String s)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/usr/1.6/plugins/Gentys/list.txt"));
			String line;
			while ((line = br.readLine()) != null) {
			   String[] parts = line.split(":");
			   String nick = parts[0];
			   if (nick.equalsIgnoreCase(s.toString().split("=")[1].split("}")[0]))
			   {
				   return false;
			   }
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void joinIndenai(Player p, Permission perms, String s)
	{
		p.sendMessage(ChatColor.DARK_RED + "[Gentys]: Tu tapai ind�n� genties nariu.");
	    File log = new File("/usr/1.6/plugins/Gentys/list.txt");
	    try{
	    	PrintWriter out = new PrintWriter(new FileWriter(log, true));
	    	out.append(s.toString().split("=")[1].split("}")[0]+":indenai\n");
	    	out.close();
	    }catch(IOException e){
	        e.printStackTrace();
	    }
	    perms.playerAdd(p, "gentys.indenai");
	    perms.playerAdd(p, "gentys.skills.indenai");
	    perms.playerAddGroup(p, "indenai");		
	}
	public void joinBaltai(Player p, Permission perms, String s)
	{
	    File log = new File("/usr/1.6/plugins/Gentys/list.txt");
	    try{
	    	PrintWriter out = new PrintWriter(new FileWriter(log, true));
	    	out.append(s.toString().split("=")[1].split("}")[0]+":baltai\n");
	    	out.close();
	    }catch(IOException e){
	        e.printStackTrace();
	    }
		p.sendMessage(ChatColor.GREEN + "[Gentys]: Tu tapai balt� genties nariu.");
		perms.playerAdd(p, "gentys.baltai");
		perms.playerAdd(p, "gentys.skills.baltai");
		perms.playerAddGroup(p, "baltai");
	}
}
