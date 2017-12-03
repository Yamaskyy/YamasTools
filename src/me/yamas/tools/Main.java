package me.yamas.tools;

import me.yamas.tools.commands.Commands;
import me.yamas.tools.data.DataManager;
import me.yamas.tools.data.FileManager;
import me.yamas.tools.listeners.EntityDamage;
import me.yamas.tools.listeners.PlayerChat;
import me.yamas.tools.listeners.PlayerJoin;
import me.yamas.tools.listeners.PlayerLogin;
import me.yamas.tools.listeners.PlayerMove;
import me.yamas.tools.listeners.PlayerQuit;
import me.yamas.tools.objects.Chat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private static Main instance;
	private boolean disabling;
	
	@Override
	public void onLoad() {
		new Commands().register();
	}
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		instance = this;
		FileManager.check();
		DataManager.load();
		Chat.setChat(true);
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new PlayerLogin(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerQuit(), this);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				DataManager.save();
				
			}
		}, 5*20, 5*20);
	}
	
	public void onDisable(){
		disabling = true;
		DataManager.save();
	}
	public static String getVersion(){
		return instance.getDescription().getVersion();
	}

	public static String getPluginName(){
		return instance.getDescription().getName();
	}
	
	public boolean isDisabling(){
		return disabling;
	}
	
	public static Main getInstance(){
		if(instance == null) return new Main();
		return instance;
	}

}
