package ketao.eu.org.blockgame;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Finish {

	public static void finish(){
		
		if(Main.chercheur.isEmpty()){
			Finish.blockfinish();
		}else{
			for(Player p : Main.ingame){
	      		p.sendMessage("§aBlock§6Game §7>> §aLa Partie est Terminé !!!");
	      		p.sendMessage("§aBlock§6Game §7>> §aLes §cchercheurs §aont gagnées !!");
	      		
	      		Transforme.finish(p);
	      		Inventory.clear(p);
	      		
	      		p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 0.2F, 1.0F);
	      		
	      		File fichierc = new File(Main.path + "/config.yml");
				FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
		    
				Location loc = p.getLocation();
				loc.setWorld(Bukkit.getWorld(configc.getString("exit.spawn.world")));
				loc.setX(Integer.parseInt(configc.getString("exit.spawn.x")));
				loc.setY(Integer.parseInt(configc.getString("exit.spawn.y")));
				loc.setZ(Integer.parseInt(configc.getString("exit.spawn.z")));
				loc.setYaw(configc.getInt("exit.spawn.yaw"));
				loc.setPitch(configc.getInt("exit.spawn.pitch"));
		    
				p.teleport(loc);
				
			}
			for(Player p : Main.chercheur){
				p.sendMessage("§aBlock§6Game §7>> §aVotre équipe a gagner !!!");
			}
			Main.block.clear();
			Main.blockmat.clear();
			Main.chercheur.clear();
			Main.ingame.clear();
			Main.isSolideblock.clear();
			Main.statusTimer = 0;
			Main.statusgame = "none";
		}
	}
	
	public static void blockfinish(){
		for(Player p : Main.ingame){
      		p.sendMessage("§aBlock§6Game §7>> §aLa Partie est Terminé !!!");
      		p.sendMessage("§aBlock§6Game §7>> §aLes §6blocks §aont gagnées !!");
      		Transforme.finish(p);
      		
      		Inventory.clear(p);
      		
      		p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 0.2F, 1.0F);
      		
      		File fichierc = new File(Main.path + "/config.yml");
			FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
	    
			Location loc = p.getLocation();
			loc.setWorld(Bukkit.getWorld(configc.getString("exit.spawn.world")));
			loc.setX(Integer.parseInt(configc.getString("exit.spawn.x")));
			loc.setY(Integer.parseInt(configc.getString("exit.spawn.y")));
			loc.setZ(Integer.parseInt(configc.getString("exit.spawn.z")));
			loc.setYaw(configc.getInt("exit.spawn.yaw"));
			loc.setPitch(configc.getInt("exit.spawn.pitch"));
	    
			p.teleport(loc);
			
		}
		for(Player p : Main.block){
			p.sendMessage("§aBlock§6Game §7>> §aVotre équipe a gagner !!!");
		}
		for(Player p : Main.chercheur){
			p.sendMessage("§aBlock§6Game §7>> §aVotre équipe a §cperdu §a!!!");
		}
		Main.block.clear();
		Main.blockmat.clear();
		Main.chercheur.clear();
		Main.ingame.clear();
		Main.isSolideblock.clear();
		Main.statusTimer = 0;
		Main.statusgame = "none";
	}
}
