package me.yamas.tools.listeners;

import me.yamas.tools.objects.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLogin implements Listener{
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		User user = User.get(player);
	//	if(!user.getBan()){
			if(Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()){
				if(player.hasPermission("yamastools.joinfullserver")){
					event.allow();
					return;
				} else {
					event.disallow(Result.KICK_FULL, "&7Serwer jest pelen! Rangi premium moga wchodzic na pelne serwery");
					return;
				}
			}
	//	}
	}

}
