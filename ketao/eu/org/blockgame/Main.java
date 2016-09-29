package ketao.eu.org.blockgame;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ketao.eu.org.blockgame.command.Maincommand;
import ketao.eu.org.blockgame.event.PlayerListener;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	public static String statusgame = "none";
	public static int statusTimer = 0;
	public static ArrayList<Player> ingame = new ArrayList<Player>();
	public static ArrayList<Player> chercheur = new ArrayList<Player>();
	public static ArrayList<Player> block = new ArrayList<Player>();
	public static File path;
	
	public static HashMap<Player, Material>	blockmat = new HashMap<Player, Material>();
	public static HashMap<Player, Integer> timerblock = new HashMap<Player, Integer>();
	public static ArrayList<Player> isSolideblock = new ArrayList<Player>();
	
	
	public static Main getInstance(){
		return instance;
	}
	
	public void onEnable(){
		new PlayerListener(this);
		
		getCommand("Bg").setExecutor(new Maincommand());
		
		saveDefaultConfig();
		path = getDataFolder();
		
	    if (getServer().getPluginManager().isPluginEnabled("LibsDisguises")){
	    	getServer().getConsoleSender().sendMessage("instaler LibsDisguises pour que le plugin fonctionne");
	    	getServer().getPluginManager().disablePlugin(this);
	    	return;
	    }
	    
///////////////////////////////////
//           jeux                //
///////////////////////////////////
	    Bukkit.getScheduler().runTaskTimer(this, new Runnable(){

			@Override
			public void run() {
				File fichierc = new File(Main.path + "/config.yml");
			    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
			    
		//Partie ne attente//
				if(statusgame.equals("none")){
					if(Main.ingame.size() >= Integer.parseInt(configc.getString("Start"))){
						statusgame = "Start";
						for(Player p : Main.ingame){
							p.sendMessage("§aBlock§6Game §7>> §aLa partie va commencer !");
						}
						statusTimer = 0;
						return;
					}
		//Partie timer commencement//
				}else if(statusgame.equals("Start")){
					if(Main.ingame.size() >= Integer.parseInt(configc.getString("Start"))){
						if(statusTimer < Integer.parseInt(configc.getString("Timer"))){
							statusTimer = statusTimer + 1;
							for(Player p : Main.ingame){
								p.setLevel(Integer.parseInt(configc.getString("Timer")) - statusTimer);
								p.playSound(p.getLocation(), Sound.ANVIL_LAND, 0.2F, 1.0F);
							}
						}else{
							for(Player p : Main.ingame){
								p.playSound(p.getLocation(), Sound.ANVIL_USE, 0.2F, 1.0F);
								p.sendMessage("§aBlock§6Game §7>> §aLa partie à commencé !");
								p.sendMessage("§aBlock§6Game §7>> §1Vous avez §65 Minutes §1!");
							}
							RandomPlayer.random();
							
				//equipe block start//
							for(Player p : Main.block){
								p.sendMessage("§aBlock§6Game §7>> §aVous avez rejoint l'équipe §cBlocks §a!");
								p.sendMessage("§aBlock§6Game §7>> §aVous avez §c15 secondes §apour vous cacher !");
								Transforme.Start(p, Material.STONE);
								Inventory.blocks(p);
						    
								Location loc = p.getLocation();
								loc.setWorld(Bukkit.getWorld(configc.getString("equipeblock.spawn.world")));
								loc.setX(Integer.parseInt(configc.getString("equipeblock.spawn.x")));
								loc.setY(Integer.parseInt(configc.getString("equipeblock.spawn.y")));
								loc.setZ(Integer.parseInt(configc.getString("equipeblock.spawn.z")));
								loc.setYaw(configc.getInt("equipeblock.spawn.yaw"));
								loc.setPitch(configc.getInt("equipeblock.spawn.pitch"));
						    
								p.teleport(loc);
								
								Main.timerblock.put(p, 5);
								
							}
							
				//equipe chercheur start//
							for(Player p : Main.chercheur){
								p.sendMessage("§aBlock§6Game §7>> §aVous avez rejoint l'équipe §cChercheur §a!");
								Inventory.chercheur(p);
								
								Location loc = p.getLocation();
								loc.setWorld(Bukkit.getWorld(configc.getString("equipechercheur.spawn.world")));
								loc.setX(Integer.parseInt(configc.getString("equipechercheur.spawn.x")));
								loc.setY(Integer.parseInt(configc.getString("equipechercheur.spawn.y")));
								loc.setZ(Integer.parseInt(configc.getString("equipechercheur.spawn.z")));
								loc.setYaw(configc.getInt("equipechercheur.spawn.yaw"));
								loc.setPitch(configc.getInt("equipechercheur.spawn.pitch"));
						    
								p.teleport(loc);
								
							}
							Main.statusgame = "Game";
							Main.statusTimer = 0;
							return;
						}
						return;
					}else{
						statusgame = "none";
						statusTimer = 0;
						return;
					}
					
		//Partie Commencé !//
				}else if(statusgame.equals("Game")){
					if(Main.chercheur.isEmpty() || Main.block.isEmpty()){
						Finish.finish();
						return;
					}
					if(statusTimer < 300){
						statusTimer = statusTimer + 1;
						for(Player p : Main.ingame){
							p.setLevel(300 - statusTimer);
						}
					}else{
						Finish.blockfinish();
					}
				}
			}
	    	
	    }, 0, 20);
	    
///////////////////////////////////
//           check               //
///////////////////////////////////
	    
	    Bukkit.getScheduler().runTaskTimer(this, new Runnable(){

			@Override
			public void run() {
				for(Player p : Main.block){
					if(Main.isSolideblock.contains(p)){
						return;
					}
					if(Main.timerblock.get(p) > 0){
						Main.timerblock.put(p, Main.timerblock.get(p) - 1);
						Inventory.blocktime(p);
						return;
					}else{
						Transforme.Solidblock(p);
						Inventory.blocktime(p);
					}
				}
			}
	    }, 0, 20);
	}

}
