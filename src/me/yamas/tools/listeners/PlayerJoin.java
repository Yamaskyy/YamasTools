package me.yamas.tools.listeners;

import me.yamas.tools.objects.User;
import me.yamas.tools.utils.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		User user = User.get(player);
		User.get(player);
		user.setGod(false);
		for(Player all : Bukkit.getOnlinePlayers()){
			if(all.hasPermission("yamastools.spy")){
				Util.sendMessage(all, "&eAdministrator: " + player.getName() + " dolaczyl na serwer!");
			}
		}
		if(!player.hasPlayedBefore()){
			Util.sendAction(player, "&7Witaj na rcdrop.pl!");
		}
	}

}
