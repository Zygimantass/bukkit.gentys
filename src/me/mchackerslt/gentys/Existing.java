package me.mchackerslt.gentys;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Existing {
	public static void ExistingMaterial(Player p)
	{
		Text.Info(p, "Ivedei bloga item'a, stai visi itemai: "); 
		for (Material type : Material.values()) {
			Text.Info(p, type.toString() + "");
		}
	}
	
	public static void ExistingSkills(Player p)
	{
		Text.Info(p, "Ivedei bloga skill'a, stai visi skillai: ");
		Text.Info(p, "Fireball");
		Text.Info(p, "Work In PROGRESS!!!!");
	}
}
