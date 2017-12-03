package me.yamas.tools.commands;

import me.yamas.tools.Main;
import me.yamas.tools.commands.utils.Executor;
import me.yamas.tools.objects.User;
import me.yamas.tools.utils.HomeUtil;
import me.yamas.tools.utils.Util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExcHome implements Executor{
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)){
			Util.sendMessage(Bukkit.getConsoleSender(), "&4Blad: &cMusisz byc na serwerze aby wykonac ta komende!");
			return;
		}
		
		if(!sender.hasPermission("yamastools.cmd.home")){
			Util.sendMessage(sender, "&cNie masz uprawnien do uzycia tej komendy &8(&7yamastools.cmd.home&8)");
			return;
		}
		final Player p = (Player) sender;
		final User user = User.get(p);
		if(!user.hasHome()){
			Util.sendMessage(p, "&4Blad: &cNie posiadasz zadnego domu!");
			return;
		}
		if(!HomeUtil.getHomeTeleport().contains(p)){
			HomeUtil.addPlayerHomeTeleport(p);
			Util.sendMessage(p, "&8» &7Zostaniesz przeteleportowany za: &45 sekund &7Nie ruszaj sie!");
		}
		
		if(HomeUtil.getHomeTeleport().contains(p)){
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					HomeUtil.removePlayerHomeTeleport(p);
					p.teleport(user.getHome());
					Util.sendMessage(p, "&8» &7Zostales przeteleportowany do swojego domu!");
				}
			}, 5*20L);
		}
	}
}
