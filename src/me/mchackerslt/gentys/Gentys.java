package me.mchackerslt.gentys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;

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
	public static Join join = new Join();
	public static Who who = new Who();
	public static UPlayer fac = new UPlayer();
	public Faction fact = new Faction();
	public Permission perms;
	public PluginDescriptionFile pdfFile = this.getDescription();
	
	
	public void onEnable() {
		//this.logger.info(pdfFile.getName() + " has been enabled.");
		//menu = new Menu(this);
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Skills(), Bukkit.getServer().getPluginManager().getPlugin("Gentys"));
		if(!setupPermissions()){
			this.logger.info("error");
		}
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            
            @Override
            public void run() {
                Cooldown.handleCooldowns();
            }
        }, 1L, 1L);
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
						Text.Error(player, "Tu nepriklausai jokiai genèiai :)");
					}
				}
				else
				{
					getHelp(player);
				}
		
			}

		}
		else if(commandLabel.equalsIgnoreCase("skills"))
		{
			if(args.length == 0)
			{
				getHelp(player);
			}
			else
			{
				if (args[0].equalsIgnoreCase("bindskill"))
				{
					if(args.length >= 2)
					{
						if (Material.getMaterial(args[1].toUpperCase()) == null) { Existing.ExistingMaterial(player); return false; }
						if (!args[2].equalsIgnoreCase("fireball") && !args[2].equalsIgnoreCase("blink")) { Existing.ExistingSkills(player); return false; }
						ifExists(player, args[1].toUpperCase());
						Failai.writeFile("/usr/1.6/plugins/Gentys/bindskill.txt", player.getName() + ":" + args[1] + ":" + args[2] + "_SKILL\n");
						
					}
					else
					{
						Text.Info(player, "Tinkamas komandos naudojimas: /skills bindskill <item'as> <skill'as>");
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

	public void ifExists(Player player, String mat)
	{
		String nick;
		String tool;
		String skill;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/usr/1.6/plugins/Gentys/bindskill.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				nick = parts[0];
				tool = parts[1];
				skill = parts[2];
				if (nick.equalsIgnoreCase(player.getName()) && tool.equalsIgnoreCase(mat))
				{
					File inputFile = new File("/usr/1.6/plugins/Gentys/bindskill.txt");
					File tempFile = new File("/usr/1.6/plugins/Gentys/bindskilltemp.txt");

					BufferedReader reader = new BufferedReader(new FileReader(inputFile));
					BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

					String lineToRemove = line;
					String currentLine;

					while((currentLine = reader.readLine()) != null) {
						// trim newline when comparing with lineToRemove
						String trimmedLine = currentLine.trim();
						if(trimmedLine.equals(lineToRemove)) continue;
						writer.write(currentLine);
					}
					writer.close();
					boolean successful = tempFile.renameTo(inputFile);
					reader.close();
				}
			}
			br.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UPlayer fplayer = UPlayer.get(player);
        for (Player fPlayer: fplayer.getFaction().getOnlinePlayers())
       {
            fPlayer.sendMessage(player.getName() + " has came online!");
       } 
    }
}


