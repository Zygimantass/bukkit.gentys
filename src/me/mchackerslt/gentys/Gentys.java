package me.mchackerslt.gentys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Gentys extends JavaPlugin implements Listener{
	private final Logger logger = Logger.getLogger("Minecraft");
	public static Gentys plugin;
	private Menu menu;
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled.");
	}
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been enabled.");
		menu = new Menu(this);
		getServer().getPluginManager().registerEvents(this, this);
	}
	@SuppressWarnings("resource")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		PluginDescriptionFile pdfFile = this.getDescription();
		ChatColor darkred = ChatColor.DARK_RED;
		if(commandLabel.equalsIgnoreCase("gentys")){
			if(args.length < 1)
			{
				player.sendMessage(ChatColor.DARK_RED + "====" + ChatColor.DARK_GREEN + " Gentys version " + pdfFile.getVersion() + " " + ChatColor.DARK_RED + "====");
				player.sendMessage(ChatColor.DARK_RED + "/gentys: " + "Rodo sita teksta.");
			}
			else
			{
				if(args[0].equalsIgnoreCase("join"))
				{
					BufferedReader br;
					try {
						br = new BufferedReader(new FileReader("/usr/1.6/plugins/Gentys/list.txt"));
						String line;
						while ((line = br.readLine()) != null) {
						   String[] parts = line.split(":");
						   String nick = parts[0];
						   if (nick.equalsIgnoreCase(sender.toString().split("=")[1].split("}")[0]))
						   {
							   player.sendMessage("Tu jau esi genties narys.");
							   return false;
						   }
						}
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(args[1].equalsIgnoreCase("indenai"))
					{
						player.setHealth(20.0);
						player.sendMessage(ChatColor.DARK_RED + "[Gentys]: Tu tapai indënø genties nariu.");
					    File log = new File("/usr/1.6/plugins/Gentys/list.txt");
					    try{
					    	PrintWriter out = new PrintWriter(new FileWriter(log, true));
					    	out.append(sender.toString().split("=")[1].split("}")[0]+":indenai\n");
					    	out.close();
					    }catch(IOException e){
					        e.printStackTrace();
					    }
					}
					else if (args[1].equalsIgnoreCase("baltai"))
					{
					    File log = new File("/usr/1.6/plugins/Gentys/list.txt");
					    try{
					    	PrintWriter out = new PrintWriter(new FileWriter(log, true));
					    	out.append(sender.toString().split("=")[1].split("}")[0]+":baltai\n");
					    	out.close();
					    }catch(IOException e){
					        e.printStackTrace();
					    }
						player.sendMessage(ChatColor.GREEN + "[Gentys]: Tu tapai baltø genties nariu.");
					}
					else
					{
						player.sendMessage(ChatColor.DARK_RED + "[Gentys]: Kad paþiurëtum kokios gentys egzistuoja, paraðyk komanda: /gentys list");
					}
				}
				else if (args[0].equalsIgnoreCase("help"))
				{
					player.sendMessage(ChatColor.DARK_RED + "====" + ChatColor.DARK_GREEN + " Gentys version " + pdfFile.getVersion() + " " + ChatColor.DARK_RED + "====");
				}
				else if (args[0].equalsIgnoreCase("list"))
				{
					player.sendMessage(ChatColor.GOLD + "[Gentys]: Baltai, indenai, WIP.");
				}
				else
				{
					player.sendMessage(darkred + "====" + ChatColor.DARK_GREEN + " Gentys version " + pdfFile.getVersion() + "  " + ChatColor.DARK_RED + "====");
					player.sendMessage(ChatColor.DARK_RED + "/gentys: " + "Rodo sita teksta.");
				}
			}
		}
		return false;
	}

}
