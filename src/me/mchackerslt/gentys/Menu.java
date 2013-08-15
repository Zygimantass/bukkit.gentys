package me.mchackerslt.gentys;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;

public class Menu implements Listener{
	private Inventory inv;
	private ItemStack i,b;
	
	public Menu(Plugin p)
	{
		inv = Bukkit.getServer().createInventory(null, 9, "Pasirink gentá");
	
		i = createItem(DyeColor.RED, "Indenai");
		b = createItem(DyeColor.WHITE, "Baltai");
		
		inv.setItem(2, i);
		inv.setItem(8, b);
		Bukkit.getServer().getPluginManager().registerEvents(this, p);
	}
	
	private ItemStack createItem(DyeColor dc, String name)
	{
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}
	
	public void show(Player p)
	{
		inv.clear();
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if(!e.getInventory().equals(inv)) return;
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Indenai"))
		{
			e.setCancelled(true);
			e.getWhoClicked().setGameMode(GameMode.CREATIVE);
			e.getWhoClicked().closeInventory();
		}
	}
}
