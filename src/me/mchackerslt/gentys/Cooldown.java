package me.mchackerslt.gentys;

import java.text.DecimalFormat;
import java.util.HashMap;


import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Cooldown {
    public static double trim(double unrounded, int decimal) {
        String format = "#.#";
   
        for(int i = 1; i < decimal; i++) {
            format = format + "#";
        }
        DecimalFormat twoDec = new DecimalFormat(format);
        return Double.valueOf(twoDec.format(unrounded)).doubleValue();
    }
    public static enum TimeUnit {
        BEST,
        DAYS,
        HOURS,
        MINUTES,
        SECONDS,
    }
    public static double convert(long time, TimeUnit unit, int decPoint) {
        if(unit == TimeUnit.BEST) {
            if(time < 60000L) unit = TimeUnit.SECONDS;
            else if(time < 3600000L) unit = TimeUnit.MINUTES;
            else if(time < 86400000L) unit = TimeUnit.HOURS;
            else unit = TimeUnit.DAYS;
        }
        if(unit == TimeUnit.SECONDS) return trim(time / 1000.0D, decPoint);
        if(unit == TimeUnit.MINUTES) return trim(time / 60000.0D, decPoint);
        if(unit == TimeUnit.HOURS) return trim(time / 3600000.0D, decPoint);
        if(unit == TimeUnit.DAYS) return trim(time / 86400000.0D, decPoint);
        return trim(time, decPoint);
    }
    
    public static HashMap<String, AbilityCooldown> cooldownPlayers = new HashMap<String, AbilityCooldown>();
    
    public static void add(String player, String ability, long seconds, long systime) {
        if(!cooldownPlayers.containsKey(player)) cooldownPlayers.put(player, new AbilityCooldown(player));
        if(isCooling(player, ability)) return;
        cooldownPlayers.get(player).cooldownMap.put(ability, new AbilityCooldown(player, seconds * 1000, System.currentTimeMillis()));
    }
    public static boolean isCooling(String player, String ability) {
    	if(!cooldownPlayers.containsKey(player)) return false;
    	if(!cooldownPlayers.get(player).cooldownMap.containsKey(ability)) return false;
    	return true;
    }
    public static double getRemaining(String player, String ability) {
        if(!cooldownPlayers.containsKey(player)) return 0.0;
        if(!cooldownPlayers.get(player).cooldownMap.containsKey(ability)) return 0.0;
        return convert((cooldownPlayers.get(player).cooldownMap.get(ability).seconds + cooldownPlayers.get(player).cooldownMap.get(ability).systime) - System.currentTimeMillis(), TimeUnit.SECONDS, 1);
    }
    public static void coolDurMessage(Player player, String ability) {
        if(player == null) {
            return;
        }
        if(!isCooling(player.getName(), ability)) {
            return;
        }
        player.sendMessage(ChatColor.GRAY + ability + " Cooldown " + ChatColor.AQUA + getRemaining(player.getName(), ability) + " Seconds");
    }
    
    public static void removeCooldown(String player, String ability) {
        if(!cooldownPlayers.containsKey(player)) {
            return;
        }
        if(!cooldownPlayers.get(player).cooldownMap.containsKey(ability)) {
            return;
        }
        cooldownPlayers.get(player).cooldownMap.remove(ability);
        Player cPlayer = Bukkit.getPlayer(player);
        if(cPlayer != null) {
            cPlayer.sendMessage(ChatColor.GRAY + "You can now use " + ChatColor.AQUA + ability);
        }
    }
    public static void handleCooldowns() {
        if(cooldownPlayers.isEmpty()) {
            return;
        }
        for(Iterator<String> it = cooldownPlayers.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            for(Iterator<String> iter = cooldownPlayers.get(key).cooldownMap.keySet().iterator(); iter.hasNext();) {
                String name = iter.next();
                if(getRemaining(key, name) <= 0.0) {
                    removeCooldown(key, name);
                }
            }
        }
    }
    
}
