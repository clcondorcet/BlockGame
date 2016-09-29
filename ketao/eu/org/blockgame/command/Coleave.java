package ketao.eu.org.blockgame.command;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ketao.eu.org.blockgame.Main;
import ketao.eu.org.blockgame.Transforme;

public class Coleave {

	public static void bgleave(CommandSender s, Command cmd, String labl, String[] args){
		if(Main.ingame.contains(Bukkit.getPlayer(s.getName()))){
			Player p = Bukkit.getPlayer(s.getName());
			if(Main.block.contains(p)){
				Main.isSolideblock.remove(p);
				Main.timerblock.remove(p);
				Main.blockmat.remove(p);
				Main.block.remove(p);
			}
			try{
				if(Main.chercheur.contains(p)){
					Main.chercheur.remove(p);
				}
			}catch(Exception ex){}
			Main.ingame.remove(p);
			Transforme.finish(p);	
			
			File fichierc = new File(Main.path + "/config.yml");
		    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
		    
		    Location loc = Bukkit.getPlayer(s.getName()).getLocation();
		    loc.setWorld(Bukkit.getWorld(configc.getString("exit.spawn.world")));
		    loc.setX(Integer.parseInt(configc.getString("exit.spawn.x")));
		    loc.setY(Integer.parseInt(configc.getString("exit.spawn.y")));
		    loc.setZ(Integer.parseInt(configc.getString("exit.spawn.z")));
			loc.setYaw(configc.getInt("exit.spawn.yaw"));
			loc.setPitch(configc.getInt("exit.spawn.pitch"));
		    
		    Bukkit.getPlayer(s.getName()).teleport(loc);
		    
			s.sendMessage("§aBlock§6Game §7>> §aVous n'êtes plus dans la partie !");
			
			for(Player pl : Main.ingame){
				pl.sendMessage("§aBlock§6Game §7>> §a" + s.getName() + "§1 a quiter la partie !");
			}
			return;
		}else{
			s.sendMessage("§aBlock§6Game §7>> §cVous n'êtes pas dans la partie !");
		}
		return;
	}
}
