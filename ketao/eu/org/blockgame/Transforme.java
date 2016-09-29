package ketao.eu.org.blockgame;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;

public class Transforme {
	
	@SuppressWarnings("deprecation")
	public static void Start(Player p, Material mat){
		if(mat.getId() == 1 || mat.getId() == 17 || mat.getId() == 98 || mat.getId() == 2 || mat.getId() == 4 || mat.getId() == 5 || mat.getId() == 13 || mat.getId() == 61 ||mat.getId() == 54){
			p.sendMessage("§aBlock§6Game §7>> §aTu est maintenant déguisé en §6"+ mat.name());
			MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, true, mat.getId());
			DisguiseAPI.disguiseToAll(p, disguise);
			Main.blockmat.put(p, mat);
		}else{
			p.sendMessage("§aBlock§6Game §7>> §cCe block n'est pas compatible !");
		}
		
	}
	
	
    public static void Solidblock(Player p){
    	if(p.getLocation().getBlock().getType().equals(Material.AIR)){
    		p.playSound(p.getLocation(), Sound.ARROW_HIT, 0.2F, 1.0F);
    		Location loc = p.getLocation();
    		Location lob = loc.getBlock().getLocation();
    		Location newloc = new Location(lob.getWorld(), lob.getBlockX() + 0.5, loc.getBlockY(), lob.getBlockZ() + 0.5);
    		p.teleport(newloc);
    		Main.isSolideblock.add(p);
    		return;
    	}else{
    		Main.timerblock.put(p, 5);
    		p.sendMessage("§aBlock§6Game §7>> §cImpossible de ce mettre ici !");
    	}
    	
		
	}
    
    public static void finish(Player p){
    	if(Main.blockmat.containsKey(p)){
    		Main.blockmat.remove(p);
    		DisguiseAPI.undisguiseToAll(p);
    	}
		return;
	}
}
