package me.mchackerslt.gentys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

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
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Gentys extends JavaPlugin implements Listener{
	private final Logger logger = Logger.getLogger("Minecraft");
	public static Gentys plugin;
	private Menu menu;
	public static ChatColor dred = ChatColor.DARK_RED;
	public static ChatColor red = ChatColor.RED;
	public static ChatColor dgreen = ChatColor.DARK_GREEN;
	public static ChatColor green = ChatColor.GREEN;
	public static ChatColor white = ChatColor.WHITE;
	public static String version = "1.0";
	public Join join = new Join();
	public static Who who = new Who();
	public Permission perms;
	public PluginDescriptionFile pdfFile = this.getDescription();
	
	
	public void onEnable() {
		//this.logger.info(pdfFile.getName() + " has been enabled.");
		//menu = new Menu(this);
		getServer().getPluginManager().registerEvents(this, this);
		if(!setupPermissions()){
			this.logger.info("error");
		}
	}
	public void getGentys(Player p)
	{
		p.sendMessage(dred + "[Gentys]: " + dgreen + "Work In Progress.");
	}
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        boolean ret = perms != null;
        return ret;
    }
	public void getHelp(Player p)
	{
		p.sendMessage(dred + "====" + dgreen + " Gentys version " + version + dred + "====");
		p.sendMessage(dred + "/gentys: " + dgreen + "Rodo sita teksta");
		p.sendMessage(dred + "/gentys help: " + dgreen + "Rodo sita teksta");
		p.sendMessage(dred + "/gentys list: " + dgreen + "Parodo kokios gentys egzistuoja");
		p.sendMessage(dred + "/gentys join: " + dgreen + "Su sia komanda gali pasirinkti genti. (Atsimink, genties pakeisti nebegalesi.)");
	}
	public void noPerms(Player p)
	{
		p.sendMessage("Tu neturi teises ivykdyti sios komandos.");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		PluginDescriptionFile pdfFile = this.getDescription();
		if(commandLabel.equalsIgnoreCase("gentys")){
			if(args.length < 1)
			{
				getHelp(player);
			}
			else
			{
				if(args[0].equalsIgnoreCase("join"))
				{
					if(!join.check(sender.toString()))
					{
						return false;
					}
					if(args[1].equalsIgnoreCase("indenai"))
					{
						join.joinIndenai(player, perms, sender.toString());
					}
					else if (args[1].equalsIgnoreCase("baltai"))
					{
						join.joinBaltai(player, perms, sender.toString());
					}
					else
					{
						player.sendMessage(dred + "[Gentys]: Tinkamas komandos naudojimas: /gentys join gentis");
						player.sendMessage(dred + "[Gentys]: Kad paþiurëtum kokios gentys egzistuoja, paraðyk komanda: /gentys list");
					}
				}
				else if (args[0].equalsIgnoreCase("help"))
				{
					getHelp(player);
				}
				else if (args[0].equalsIgnoreCase("list"))
				{
					getGentys(player);
				}
				else if (args[0].equalsIgnoreCase("who"))
				{
					if(perms.has(player, "gentys.indenai"))
					{
						Who.whoIndenai(player);
					}
					else if (perms.has(player, "gentys.baltai"))
					{
						Who.whoBaltai(player);
					}
					else
					{
						noPerms(player);
					}
				}
			
				else
				{
					getHelp(player);
				}
		
			}	
		}
		return false;
	}
}


