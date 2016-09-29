package ketao.eu.org.blockgame.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Maincommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String labl, String[] args) {
		if(args.length == 0){
			s.sendMessage("§7<< §aBlock§6Game §7>>");
			s.sendMessage("§a/Bg Join §6[rejoigner la partie]");
			s.sendMessage("§a/Bg leave §6[quitter la partie]");
			if(s.isOp()){
				s.sendMessage("§a/Bg admin §6[aide pour les commandes admin]");
			}
			return true;
		}else{
			if((args[0].equalsIgnoreCase("admin") || (args[0].equalsIgnoreCase("a")) && s.isOp())){
				Cobgadmin.bgAdmin(s, cmd, labl, args);
				return true;
			}else if(args[0].equalsIgnoreCase("join") || (args[0].equalsIgnoreCase("j"))){
				Cojoin.bgjoin(s, cmd, labl, args);
				return true;
			}else if(args[0].equalsIgnoreCase("leave") || (args[0].equalsIgnoreCase("l"))){
				Coleave.bgleave(s, cmd, labl, args);
				return true;
			}else{
				s.sendMessage("§cIl n'y a pas de commande de ce nom : " + args[0]);
			}
		}
		return true;
	}

}
