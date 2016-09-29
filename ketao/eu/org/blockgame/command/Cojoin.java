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

public class Cojoin {

	public static void bgjoin(CommandSender s, Command cmd, String labl, String[] args){
		if(Main.ingame.contains(Bukkit.getPlayer(s.getName()))){
			s.sendMessage("§aBlock§6Game §7>> §cVous êtes déja dans la partie !");
			return;
		}else{
			if(Main.statusgame.equals("none") || Main.statusgame.equals("Start")){
				Main.ingame.add(Bukkit.getPlayer(s.getName()));
			
				File fichierc = new File(Main.path + "/config.yml");
				FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
		    
				Location loc = Bukkit.getPlayer(s.getName()).getLocation();
				loc.setWorld(Bukkit.getWorld(configc.getString("Lobby.spawn.world")));
				loc.setX(Integer.parseInt(configc.getString("Lobby.spawn.x")));
				loc.setY(Integer.parseInt(configc.getString("Lobby.spawn.y")));
				loc.setZ(Integer.parseInt(configc.getString("Lobby.spawn.z")));
				loc.setYaw(configc.getInt("Lobby.spawn.yaw"));
				loc.setPitch(configc.getInt("Lobby.spawn.pitch"));
		    
				Bukkit.getPlayer(s.getName()).teleport(loc);
		    
				s.sendMessage("§aBlock§6Game §7>> §aVous rejoigner la partie !");
				for(Player p : Main.ingame){
					p.sendMessage("§aBlock§6Game §7>> §a" + s.getName() + "§1 a rejoint la partie !");
				}
				return;
			}else{
				s.sendMessage("§aBlock§6Game §7>> §cLa partie est déja commencer");
			}
			
		}
		return;
	}
}
