package me.mchackerslt.gentys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Spell {
	private static String spellName;
	private static int cool;
	private static Effect eff;
	@SuppressWarnings("static-access")
	public Spell(String name, int cool, Effect effect)
	{
		this.spellName = name;
		this.cool = cool;
		this.eff = effect;
	}
	public static void activateSpell(Spell spell, String player)
	{
		if (Cooldown.isCooling(player, spell.spellName))
		{
			return;
		}
		Effect.addEffect(spell, player);
		Bukkit.getServer().getPlayer(player).sendMessage(ChatColor.AQUA + "You casted " + spell.spellName + " spell");
	}
	public static String getSpellName(){
		return spellName;
	}
	public static int getCooldown()
	{
		return cool;
	}
	public static Effect getEffect()
	{
		return eff;
	}
}
