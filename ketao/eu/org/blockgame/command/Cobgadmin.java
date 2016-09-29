package ketao.eu.org.blockgame.command;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ketao.eu.org.blockgame.Main;

public class Cobgadmin {

	public static void bgAdmin(CommandSender s, Command cmd, String labl, String[] args){
		if(args.length == 1){
			s.sendMessage("§7<< §aBlock§6Game §cAdmin §7>>");
			s.sendMessage("§a/Bg a setLobby §6[mettre le point de spawn lobby]");
			s.sendMessage("§a/Bg a setSpawnblock §6[mettre le point de spawn blocks]");
			s.sendMessage("§a/Bg a setSpawnch §6[mettre le point de spawn chercheur]");
			s.sendMessage("§a/Bg a setSpawnExit §6[mettre le point de spawn exit]");
			s.sendMessage("§a/Bg a setTimer <temps> §6[mettre le temps d'attente]");
			s.sendMessage("§a/Bg a setStart <nombre> §6[mettre le nombre de joueur minimum]");
			return;
			
		}else if(args[1].equalsIgnoreCase("setLobby") || (args[1].equalsIgnoreCase("setL"))){
			
			File fichierc = new File(Main.path + "/config.yml");
		    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
		    
		    Location loc = Bukkit.getPlayer(s.getName()).getLocation();
		    
		    configc.set("Lobby.spawn.x", loc.getBlockX());
		    configc.set("Lobby.spawn.y", loc.getBlockY());
		    configc.set("Lobby.spawn.z", loc.getBlockZ());
		    configc.set("Lobby.spawn.world", loc.getWorld().getName());
		    configc.set("Lobby.spawn.pitch", loc.getPitch());
		    configc.set("Lobby.spawn.yaw", loc.getYaw());
		    
		    try{
		    	
		    	configc.save(fichierc);
		    	
		    }catch(Exception ex){
		    	
		    	ex.printStackTrace();
		    	
		    	 s.sendMessage("§cUn problème est survenue lors de la sauvegarde");
		    	 s.sendMessage("§cRegarder la console pour plus d'information");
		    	 return;
		    }
		    s.sendMessage("§aSauvegarde effectuer !");
		    return;
		    
		}else if(args[1].equalsIgnoreCase("setSpawnblock") || (args[1].equalsIgnoreCase("setSB"))){
			
			File fichierc = new File(Main.path + "/config.yml");
		    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
		    
		    Location loc = Bukkit.getPlayer(s.getName()).getLocation();
		    
		    configc.set("equipeblock.spawn.x", loc.getBlockX());
		    configc.set("equipeblock.spawn.y", loc.getBlockY());
		    configc.set("equipeblock.spawn.z", loc.getBlockZ());
		    configc.set("equipeblock.spawn.world", loc.getWorld().getName());
		    configc.set("equipeblock.spawn.pitch", loc.getPitch());
		    configc.set("equipeblock.spawn.yaw", loc.getYaw());
		    
		    try{
		    	configc.save(fichierc);
		    }catch(Exception ex){
		    	
		    	ex.printStackTrace();
		    	
		    	 s.sendMessage("§cUn problème est survenue lors de la sauvegarde");
		    	 s.sendMessage("§cRegarder la console pour plus d'information");
		    	 return;
		    }
		    s.sendMessage("§aSauvegarde effectuer !");
		    return;
		
		}else if(args[1].equalsIgnoreCase("setSpawnch") || (args[1].equalsIgnoreCase("setSC"))){
			
			File fichierc = new File(Main.path + "/config.yml");
		    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
		    
		    Location loc = Bukkit.getPlayer(s.getName()).getLocation();
		    
		    configc.set("equipechercheur.spawn.x", loc.getBlockX());
		    configc.set("equipechercheur.spawn.y", loc.getBlockY());
		    configc.set("equipechercheur.spawn.z", loc.getBlockZ());
		    configc.set("equipechercheur.spawn.world", loc.getWorld().getName());
		    configc.set("equipechercheur.spawn.pitch", loc.getPitch());
		    configc.set("equipechercheur.spawn.yaw", loc.getYaw());
		    
		    try{
		    	configc.save(fichierc);
		    }catch(Exception ex){
		    	
		    	ex.printStackTrace();
		    	
		    	 s.sendMessage("§cUn problème est survenue lors de la sauvegarde");
		    	 s.sendMessage("§cRegarder la console pour plus d'information");
		    	 return;
		    }
		    s.sendMessage("§aSauvegarde effectuer !");
		    return;
		
		}else if(args[1].equalsIgnoreCase("setTimer") || (args[1].equalsIgnoreCase("setT"))){
			
			if(args.length == 2){
				
				s.sendMessage("§c/Bg SetTimer <nombre>");
				return;
			}else{
				
				File fichierc = new File(Main.path + "/config.yml");
			    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
			    
			    configc.set("Timer", args[2]);
			    
			    try{
			    	configc.save(fichierc);
			    }catch(Exception ex){
			    	
			    	ex.printStackTrace();
			    	
			    	 s.sendMessage("§cUn problème est survenue lors de la sauvegarde");
			    	 s.sendMessage("§cRegarder la console pour plus d'information");
			    	 return;
			    }
			    s.sendMessage("§aSauvegarde effectuer !");
			    return;
			}
			
		}else if(args[1].equalsIgnoreCase("setStart") || (args[1].equalsIgnoreCase("setS"))){
			
			if(args.length == 2){
				
				s.sendMessage("§c/Bg SetStart <nombre>");
				return;
			}else{
				
				File fichierc = new File(Main.path + "/config.yml");
			    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
			    
			    configc.set("Start", args[2]);
			    
			    try{
			    	configc.save(fichierc);
			    }catch(Exception ex){
			    	
			    	ex.printStackTrace();
			    	
			    	 s.sendMessage("§cUn problème est survenue lors de la sauvegarde");
			    	 s.sendMessage("§cRegarder la console pour plus d'information");
			    	 return;
			    }
			    s.sendMessage("§aSauvegarde effectuer !");
			    return;
			}
		}else if(args[1].equalsIgnoreCase("setSpawnExit") || (args[1].equalsIgnoreCase("setSE"))){
				
				File fichierc = new File(Main.path + "/config.yml");
			    FileConfiguration configc = YamlConfiguration.loadConfiguration(fichierc);
			    
			    Location loc = Bukkit.getPlayer(s.getName()).getLocation();
			    
			    configc.set("exit.spawn.x", loc.getBlockX());
			    configc.set("exit.spawn.y", loc.getBlockY());
			    configc.set("exit.spawn.z", loc.getBlockZ());
			    configc.set("exit.spawn.world", loc.getWorld().getName());
			    configc.set("exit.spawn.pitch", loc.getPitch());
			    configc.set("exit.spawn.yaw", loc.getYaw());
			    
			    try{
			    	configc.save(fichierc);
			    }catch(Exception ex){
			    	
			    	ex.printStackTrace();
			    	
			    	 s.sendMessage("§cUn problème est survenue lors de la sauvegarde");
			    	 s.sendMessage("§cRegarder la console pour plus d'information");
			    	 return;
			    }
			    s.sendMessage("§aSauvegarde effectuer !");
			    return;
		}else{
			s.sendMessage("§cCommande non répertorié !");
		}
	}
}
