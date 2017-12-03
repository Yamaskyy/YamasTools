package me.yamas.tools.commands;

import org.bukkit.command.CommandSender;

import me.yamas.tools.commands.utils.Executor;
import me.yamas.tools.utils.Util;

public class ExcBroadcast implements Executor{
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("yamastools.cmd.broadcast")){
			Util.sendMessage(sender, "&cNie masz uprawnien do uzycia tej komendy &8(&7yamastools.cmd.broadcast&8)");
			return;
		}
		if(args.length == 0){
			Util.sendMessage(sender, "&8» &7Poprawne uzycie: &4/broadcast &8[&4wiadomosc&8]");
			return;
		} else {
			StringBuilder sb = new StringBuilder();
			for(String s : args){
				if(sb.length() > 0){
					sb.append(" ");
				}
				sb.append(s);
			}
			Util.broadcast("&8[&4→&8] &7" + sb.toString(), true);
		}
	}

}
