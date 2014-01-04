package me.mchackerslt.gentys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SettingsManager extends Gentys{
	public enum Tribe 
	{
		INDENAI, BALTAI, ABORIGENAI;
		public String toStr()
		{
			switch (this)
			{
				case INDENAI: return "INDENAI";
				case BALTAI: return "BALTAI";
				case ABORIGENAI: return "ABORIGENAI";
				default: return null;
			}
		}
	};
	
	public int getLevel(Player p)
	{
		// Return a level.
		// Todo in next update maybe ?
		return -1;
	}
	public Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("Gentys");
	public void addToTribe(Tribe tribe, Player p) {
		if (tribe == Tribe.INDENAI)
		{
			getConfig().set(p.getName() + ".tribe", tribe.toStr());
		}
		if (tribe == Tribe.BALTAI)
		{
			getConfig().set(p.getName() + ".tribe", tribe.toStr());
		}
		if (tribe == Tribe.ABORIGENAI)
		{
			getConfig().set(p.getName() + ".tribe", tribe.toStr());
		}
	}
}