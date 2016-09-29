package ketao.eu.org.blockgame;

import java.util.Random;

import org.bukkit.entity.Player;

public class RandomPlayer {
	
	public static void random(){
		int random = new Random().nextInt(Main.ingame.size());
		Player p = Main.ingame.get(random);
		Main.chercheur.add(p);
		for(Player pl : Main.ingame){
			Main.block.add(pl);
		}
		Main.block.remove(p);
		return;
	}
}
