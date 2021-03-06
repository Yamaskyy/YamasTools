package me.yamas.tools.data;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import me.yamas.tools.objects.Ban;
import me.yamas.tools.objects.User;
import me.yamas.tools.objects.utils.BanUtils;
import me.yamas.tools.objects.utils.UserUtils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataManager {
	
	public static void save(){
		saveUsers();
		saveBans();
	}
	
	public static void load(){
		loadUsers();
		loadBans();
		checkObjects();
	}
	
	private static void checkObjects(){
		int removed = 0;
		for(Ban ban : BanUtils.getBans()){
			if(ban.getVictim() == null || ban.getPunisher() == null){
				BanUtils.removeBan(ban);
				removed++;
			}
		}
		for(User user : UserUtils.getUsers()){
			if(user.getName() == null || user.getUUID() == null){
				UserUtils.removeUser(user);
				removed++;
			}
		}
	}
	
	private static void loadUsers(){
		int loaded = 0;
		for(File file : FileManager.getUsersFolder().listFiles()){
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			User user = User.get(yml.getString("name"));
			user.setUUID(UUID.fromString(yml.getString("uuid")));
			user.setGod(yml.getBoolean("god"));
			user.setMute(yml.getLong("mute"));
			user.setHasHome(yml.getBoolean("hasHome"));user.setHome(new Location(Bukkit.getWorld(yml.getString("homeWorld")), yml.getInt("homeX"), yml.getInt("homeY"), yml.getInt("homeZ")));
			loaded++;
		}
	}
	
	private static void loadBans(){
		int loaded = 0;
		for(File file : FileManager.getBansFolder().listFiles()){
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Ban user = new Ban(yml.getString("punisher"), yml.getString("victim"));
			user.setReason(yml.getString("reason"));
			user.setBanTime(yml.getLong("banTime"));
			user.setValidateTill(yml.getLong("validateTill"));
			loaded++;
		}
	}
	
	private static void saveUsers(){
		int saved = 0;
		for(User user : UserUtils.getUsers()){
			File file = new File(FileManager.getUsersFolder(), user.getName() + ".yml");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			yml.set("name", user.getName());
			yml.set("uuid", user.getUUID().toString());
			yml.set("god", user.isGod());
			if(user.getMute() > 0){
				yml.set("mute", user.getMute());
			} else {
				yml.set("mute", null);
			}
			yml.set("hasHome", user.hasHome());
			if(user.hasHome()){
				yml.set("homeWorld", user.getHome().getWorld().toString());
				yml.set("homeX", user.getHome().getBlockX());
				yml.set("homeY", user.getHome().getBlockY());
				yml.set("homeZ", user.getHome().getBlockZ());
			} else {
				yml.set("homeWorld", null);
				yml.set("homeX", null);
				yml.set("homeY", null);
				yml.set("homeZ", null);
			}
			
			try {
				yml.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saved++;
		}
	}
	
	private static void saveBans(){
		int saved = 0;
		for(Ban user : BanUtils.getBans()){
			File file = new File(FileManager.getBansFolder(), user.getVictim() + ".yml");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			yml.set("victim", user.getVictim());
			yml.set("punisher", user.getPunisher());
			yml.set("reason", user.getReason());
			yml.set("banTime", user.getBanTime());
			if(user.getValidateTill() > 0){
				yml.set("validateTill", user.getValidateTill());
			} else {
				yml.set("validateTill", null);
			}
			try {
				yml.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saved++;
		}
	}

}