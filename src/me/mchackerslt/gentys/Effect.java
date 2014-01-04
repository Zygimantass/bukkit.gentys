package me.mchackerslt.gentys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Effect {
	private PotionEffect[] poteffs;
	private String name;
	public Effect(PotionEffectType[] peta, String name, int dur, int amplifier)
	{
		for (PotionEffectType pet : peta)
		{
			
		}
		this.name = name;
	}
	public static void addEffect(Spell spell, String p)
	{
		Player player = Bukkit.getServer().getPlayer(p);
		Effect eff = spell.getEffect();
	}
}
