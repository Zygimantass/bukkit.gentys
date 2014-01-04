package me.mchackerslt.gentys;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Skills implements Listener{
	public CommandSender pl;
	public HashMap<String, Boolean> fireballCool = new HashMap<String, Boolean>();
	public HashMap<String, Boolean> waterCool = new HashMap<String, Boolean>();
	public HashMap<String, Boolean> blinkCool = new HashMap<String, Boolean>();
	public HashMap<String, Boolean> freezeCool = new HashMap<String, Boolean>();
	public String pls;
	public String tool;
	public String nick;
	public Gentys plugin = Gentys.getInst();
	public String skill;
	final ConfigurationSection config = Bukkit.getServer().getPluginManager().getPlugin("Gentys").getConfig();
	public void FireballSkill(PlayerInteractEvent e, Action act)
	{
		Player player = e.getPlayer();
		String ps = player.getName();
		if (Cooldown.isCooling(e.getPlayer().toString(), "FireballSkill"))
		{
			fireballCool.put(ps, true);
		}
		else
		{
			fireballCool.put(ps, false);
		}
		if (act == Action.LEFT_CLICK_AIR)
		{
			if (!fireballCool.get(ps)) {Text.Info(player, "Tu jau gali leisti si skill'a"); fireballCool.remove(ps); return;}
			Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "FireballSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			fireballCool.remove(ps);
			return;
		}
		if (act == Action.RIGHT_CLICK_AIR)
		{
			if (fireballCool.get(ps))
			{
				Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "FireballSkill") + " sekundziu kol galesi paleisti si skill'a :)");
				fireballCool.remove(ps);
				return;
			}
		}
		fireballCool.remove(ps);
		Fireball f = player.launchProjectile(Fireball.class);
		f.setMetadata("FromItem", new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("Gentys"), String.valueOf("ironspade")));
		f.setIsIncendiary(true);
		f.setYield(0);
		Cooldown.add(e.getPlayer().toString(), "FireballSkill", 5L, System.currentTimeMillis());
	}
	public void WaterSkill(PlayerInteractEvent e, Action act)
	{
		Player player = e.getPlayer();
		String ps = player.getName();
		if(Cooldown.isCooling(ps, "WaterSkill"))
		{
			waterCool.put(ps, true);
		}
		else
		{
			waterCool.put(ps, false);
		}
		if (act == Action.LEFT_CLICK_AIR)
		{
			if (!waterCool.get(ps)) {Text.Info(player, "Tu jau gali leisti si skill'a"); waterCool.remove(ps); return;}
			Text.Info(player, "Tau liko " + Cooldown.getRemaining(ps, "WaterSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			waterCool.remove(ps);
			return;
		}
		if (act == Action.RIGHT_CLICK_AIR)
		{
			if (waterCool.get(ps))
			{
				Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "WaterSkill") + " sekundziu kol galesi paleisti si skill'a :)");
				waterCool.remove(ps);
				return;
			}
		}
		Cooldown.add(e.getPlayer().toString(), "WaterSkill", 120L, System.currentTimeMillis());
		player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1200, 1));
	}
	public void BlinkSkill(PlayerInteractEvent e, Action act)
	{
		Player player = e.getPlayer();
		String ps = player.getName();
		if (Cooldown.isCooling(ps, "BlinkSkill"))
		{	
			blinkCool.put(ps, true);
		}
		else
		{
			blinkCool.put(ps, false);
		}
		if (act == Action.LEFT_CLICK_AIR)
		{
			if (!blinkCool.get(ps)) {Text.Info(player, "Tu jau gali leisti si skill'a"); return;}
			Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "BlinkSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			return;
		}
		if (act == Action.RIGHT_CLICK_AIR)
		{
			if (!blinkCool.get(ps))
			{
				Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "BlinkSkill") + " sekundziu kol galesi paleisti si skill'a :)");
				blinkCool.remove(ps);
			}
		}
		player.teleport(player.getTargetBlock(null, 5).getLocation());
		Cooldown.add(e.getPlayer().toString(), "BlinkSkill", 15L, System.currentTimeMillis());
	}
	public void FreezeSkill(PlayerInteractEvent e, Action act)
	{
		Player player = e.getPlayer();
		String ps = player.getName();
		if(Cooldown.isCooling(ps, "FreezeSkill"))
		{
			freezeCool.put(ps, true);
		}
		else
		{
			freezeCool.put(ps, false);
		}
		if (act == Action.LEFT_CLICK_AIR)
		{
			if (!freezeCool.get(ps)) {Text.Info(player, "Tu jau gali leisti si skill'a"); freezeCool.remove(ps); return;}
			Text.Info(player, "Tau liko " + Cooldown.getRemaining(ps, "WaterSkill") + " sekundziu kol galesi paleisti si skill'a :)");
			freezeCool.remove(ps);
			return;
		}
		if (act == Action.RIGHT_CLICK_AIR)
		{
			if (freezeCool.get(ps))
			{
				Text.Info(player, "Tau liko " + Cooldown.getRemaining(player.toString(), "WaterSkill") + " sekundziu kol galesi paleisti si skill'a :)");
				freezeCool.remove(ps);
				return;
			}
		}
		Cooldown.add(e.getPlayer().toString(), "WaterSkill", 60L, System.currentTimeMillis());
		Snowball s = player.launchProjectile(Snowball.class);
		s.setMetadata("FromItem", new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("Gentys"), String.valueOf("snowball")));
		return;
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
	public void onSnowballDmg(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Snowball)
		{
			if(e.getEntity() instanceof Player){
				Snowball s = (Snowball) e.getDamager();
				if (s.getMetadata("FromItem").get(0).asString().equalsIgnoreCase("snowball"))
				{
					((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 10));
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) return;
		if (true)
		{
			e.getPlayer().sendMessage(config.toString());
			e.getPlayer().sendMessage(config.getString("users.TheHacker.Test"));
			if(config.getString("users."+e.getPlayer().getName()+"."+e.getMaterial().toString().toUpperCase()).equalsIgnoreCase("fireball"))
			{
				FireballSkill(e, e.getAction());
			}
			else if(config.getString("users."+e.getPlayer().getName()+"."+e.getMaterial().toString()).equalsIgnoreCase("blink"))
			{
				BlinkSkill(e, e.getAction());
			}
			else if(config.getString("users."+e.getPlayer().getName()+"."+e.getMaterial().toString()).equalsIgnoreCase("water"))
			{
				WaterSkill(e, e.getAction());
			}
			else if(config.getString("users."+e.getPlayer().getName()+"."+e.getMaterial()).toString().equalsIgnoreCase("freeze"))
			{
				FreezeSkill(e, e.getAction());
			}
			else
			{
				return;
			}
		}
		else
		{
			e.getPlayer().sendMessage(config.getString("users."+e.getPlayer().getName()+"."+e.getMaterial().toString()));
		}
	}
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Fireball) { onFireballDmg(e); }
		else if (e.getDamager() instanceof Snowball) {onSnowballDmg(e); }
		else
		{
			return;
		}
	}
}
