package me.mchackerslt.gentys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Skills implements Listener{
	public CommandSender pl;
	public boolean cool = false;
	public boolean blinkCool = false;
	public String pls;
	public String tool;
	public String nick;
	public String skill;
	public void FireballSkill(PlayerInteractEvent e, Action act)
	{
		Player player = e.getPlayer();
		if (Cooldown.isCooling(e.getPlayer().toString(), "FireballSkill"))
		{
			cool = true;
		}
		else
		{
			cool = false;
		}
		if (act == Action.LEFT_CLICK_AIR)
		{
			if (!cool) {Text.Info(player, "Tu jau gali leisti si skill'a"); return;}
			Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "FireballSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			return;
		}
		if (act == Action.RIGHT_CLICK_AIR)
		{
			if (!cool)
			{
				cool = false;
			}
			else
			{
				cool = true;
				Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "FireballSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			}
		}
		if (cool){
			cool = false;
			return;
		}
		Fireball f = player.launchProjectile(Fireball.class);
		f.setMetadata("FromItem", new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("Gentys"), String.valueOf("ironspade")));
		f.setIsIncendiary(false);
		f.setYield(0);
		Cooldown.add(e.getPlayer().toString(), "FireballSkill", 5L, System.currentTimeMillis());
	}
	public void BlinkSkill(PlayerInteractEvent e, Action act)
	{
		Player player = e.getPlayer();
		String ps = player.toString();
		if (Cooldown.isCooling(ps, "BlinkSkill"))
		{	
			blinkCool = true;
		}
		else
		{
			blinkCool = false;
		}
		if (act == Action.LEFT_CLICK_AIR)
		{
			if (!blinkCool) {Text.Info(player, "Tu jau gali leisti si skill'a"); return;}
			Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "BlinkSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			return;
		}
		if (act == Action.RIGHT_CLICK_AIR)
		{
			if (!blinkCool)
			{
				blinkCool = false;
			}
			else
			{
				blinkCool = true;
				Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "BlinkSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			}
		}
		if (blinkCool){
			cool = false;
			return;
		}
		player.teleport(player.getTargetBlock(null, 5).getLocation());
		Cooldown.add(e.getPlayer().toString(), "BlinkSkill", 15L, System.currentTimeMillis());
	}
	public void onFireballDmg(EntityDamageByEntityEvent e)
	{
        if (e.getDamager() instanceof Fireball) {
            Fireball f = (Fireball) e.getDamager();
            if (f.getShooter() instanceof Player) {
                if (f.getMetadata("FromItem").get(0).asString() == "ironspade") 
                {
                	e.setDamage(15.0);
                }
            }
        }
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) return;
		if (e.getMaterial().toString().equalsIgnoreCase(read(e.getPlayer(), e.getMaterial().toString()).split(":")[0]))
		{
			if(read(e.getPlayer(), e.getMaterial().toString()).split(":")[1].equalsIgnoreCase("FIREBALL_SKILL"))
			{
				FireballSkill(e, e.getAction());
			}
			else if(read(e.getPlayer(), e.getMaterial().toString()).split(":")[1].equalsIgnoreCase("BLINK_SKILL"))
			{
				BlinkSkill(e, e.getAction());
			}
		}	
	}
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Fireball) { onFireballDmg(e); }
		else
		{
			return;
		}
	}
	public String read(Player player, String material)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/usr/1.6/plugins/Gentys/bindskill.txt"));
			String line;
			while ((line = br.readLine()) != null) {
			   String[] parts = line.split(":");
			   nick = parts[0];
			   tool = parts[1];
			   skill = parts[2];
			}
			br.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tool + ":" + skill;
	}
}
