package ketao.eu.org.blockgame;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventory {

	public static void chercheur(Player p){
		p.getInventory().clear();
		p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aDiamond§6Sword");
		item.setItemMeta(meta);
		p.getInventory().setItem(0, item);
		item.setType(Material.BOW);
		meta.setDisplayName("§aThe §6Bow");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		item.setItemMeta(meta);
		p.getInventory().setItem(1, item);
		item.setType(Material.BREAD);
		meta.setDisplayName("§aThe §6Bread");
		meta.removeEnchant(Enchantment.ARROW_INFINITE);
		item.setItemMeta(meta);
		item.setAmount(64);
		p.getInventory().setItem(8, item);
		item.setType(Material.ARROW);
		meta.setDisplayName("§aLes §6Flèches");
		item.setItemMeta(meta);
		item.setAmount(1);
		p.getInventory().setItem(17, item);
		p.updateInventory();
	}
	
	
	public static void blocks(Player p){
		p.getInventory().clear();
		ItemStack item = new ItemStack(Material.STONE_AXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aLa §6hache !");
		List<String> list = Arrays.asList(new String[] {"§1Clique droit pour s'enfuire ! §6 recharge: 10s"});
		meta.setLore(list);
		item.setItemMeta(meta);
		p.getInventory().setItem(0, item);
		item.setType(Material.STICK);
		meta.setDisplayName("§aThe §6Bow");
		List<String> list2 = Arrays.asList(new String[] {""});
		meta.setLore(list2);
		meta.setDisplayName("§aBatôn §6Magique");
		item.setItemMeta(meta);
		p.getInventory().setItem(2, item);
		item.setType(Material.BOW);
		meta.setDisplayName("§aThe §6Bow");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		item.setItemMeta(meta);
		p.getInventory().setItem(1, item);
		item.setType(Material.BREAD);
		meta.setDisplayName("§aThe §6Bread");
		meta.removeEnchant(Enchantment.ARROW_INFINITE);
		item.setItemMeta(meta);
		item.setAmount(64);
		p.getInventory().setItem(8, item);
		item.setType(Material.ARROW);
		meta.setDisplayName("§aLes §6Flèches");
		item.setItemMeta(meta);
		item.setAmount(1);
		p.getInventory().setItem(17, item);
		p.updateInventory();
	}
	
	public static void blocktime(Player p){
		ItemStack item = new ItemStack(Main.blockmat.get(p));
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§aTon §6Block");
		if(Main.timerblock.get(p) != 0){
			try{
				meta.removeEnchant(Enchantment.DURABILITY);
			}catch(Exception ex){}
			item.setAmount(Main.timerblock.get(p));
		}else{
			item.setAmount(1);
			meta.addEnchant(Enchantment.DURABILITY, 1, true);
		}
		item.setItemMeta(meta);
		p.getInventory().setItem(4, item);
		p.updateInventory();
	}
	
	public static void clear(Player p){
		ItemStack item = new ItemStack(Material.AIR);
		p.getInventory().clear();
		p.getInventory().setHelmet(item);
		p.getInventory().setChestplate(item);
		p.getInventory().setLeggings(item);
		p.getInventory().setBoots(item);
		p.updateInventory();
	}
}
