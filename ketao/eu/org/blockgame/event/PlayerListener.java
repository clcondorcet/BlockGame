package ketao.eu.org.blockgame.event;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import ketao.eu.org.blockgame.Inventory;
import ketao.eu.org.blockgame.Main;
import ketao.eu.org.blockgame.Transforme;
import ketao.eu.org.blockgame.command.Coleave;

public class PlayerListener implements Listener {
	

	public PlayerListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onquit(PlayerQuitEvent e){
		if(Main.ingame.contains(e.getPlayer())){
			Coleave.bgleave(e.getPlayer(), null, null, null);
		}
	}
	
	@EventHandler
	public void onrespawn(PlayerRespawnEvent e){
		if(Main.ingame.contains(e.getPlayer())){
			File fichierc = new File(Main.path + "/config.yml");
			FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
			    
			Location loc = e.getPlayer().getLocation();
			loc.setWorld(Bukkit.getWorld(configc.getString("equipechercheur.spawn.world")));
			loc.setX(Integer.parseInt(configc.getString("equipechercheur.spawn.x")));
			loc.setY(Integer.parseInt(configc.getString("equipechercheur.spawn.y")));
			loc.setZ(Integer.parseInt(configc.getString("equipechercheur.spawn.z")));
			loc.setYaw(configc.getInt("equipechercheur.spawn.yaw"));
			loc.setPitch(configc.getInt("equipechercheur.spawn.pitch"));
		    
			e.getPlayer().teleport(loc);
			
			Inventory.chercheur(e.getPlayer());
		}
	}
	@EventHandler
	public void ondie(PlayerDeathEvent e){
		if(Main.ingame.contains(e.getEntity())){
			e.getDrops().clear();
			if(Main.block.contains(e.getEntity())){
				Main.block.remove(e.getEntity());
				Main.chercheur.add(e.getEntity());
				e.getEntity().sendMessage("§aBlock§6Game §7>> §aVous avez rejoint l'équipe §cChercheur §a!");
			}
		}
	}
	
	@EventHandler
	public void onmove(PlayerMoveEvent e){
		if(Main.chercheur.contains(e.getPlayer())){
			if(Main.statusgame == "Game"){
				if(Main.statusTimer < 15){
					e.setCancelled(true);
				}
			}
		}else if(Main.block.contains(e.getPlayer())){
			Main.timerblock.put(e.getPlayer(), 5);
			if(Main.isSolideblock.contains(e.getPlayer())){
				Main.isSolideblock.remove(e.getPlayer());
				Main.timerblock.put(e.getPlayer(), 5);
			}
		}
	}
	
	
	@EventHandler
	public void ondropitem(PlayerDropItemEvent e){
		if(Main.ingame.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void oninventotryclick(InventoryClickEvent e){
		if(Main.ingame.contains(e.getWhoClicked())){
			if(Main.statusgame == "Game"){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onclickwithstick(PlayerInteractEvent e){
		if(Main.ingame.contains(e.getPlayer())){
			if(Main.block.contains(e.getPlayer())){	
			}else{return;}
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
				ItemStack item = e.getItem();
			    Player p = e.getPlayer();
			    if(Main.block.contains(e.getPlayer())){
			    	try{
		    			if (!item.getType().equals(Material.STICK)) {
		    				return;
		    			}
		    			e.setCancelled(true);
		    		}catch (Exception ex) {
		    			return;
		    		}
		    		Transforme.Start(p, e.getClickedBlock().getType());
		    		return;
			    }
			}
		}
	}
	
}
