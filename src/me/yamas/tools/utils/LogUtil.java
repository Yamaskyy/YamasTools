package me.yamas.tools.utils;

import me.yamas.tools.Main;

import org.bukkit.Bukkit;

public class LogUtil {

	public static void update(String content){
		Bukkit.getLogger().info("[YamasTools][Updater] > " + content);
	}
	
	public static void parser(String content){
		Bukkit.getLogger().severe("[YamasTools][Parser] #> " + content);
	}
	
	public static void info(String content){
		Bukkit.getLogger().info("[YamasTools] " + content);
	}
	
	public static void warning(String content){
		Bukkit.getLogger().warning("[YamasTools] " + content);
	}
	
	public static void error(String content){
		Bukkit.getLogger().severe("[Server thread/ERROR] #!# " + content);
	}
	
	public static boolean exception(Throwable cause){
		if(cause == null) return true;
		return exception(cause.getMessage(), cause.getStackTrace());
	}
	
	public static boolean exception(String cause, StackTraceElement[] ste){
		error("");
		error("[YamasTools] Severe error:");
	    error("");
	    error("Server Information:");
	    error("  YamasTools: " + Main.getVersion());
	    error("  Bukkit: " + Bukkit.getBukkitVersion());
	    error("  Java: " + System.getProperty("java.version"));
	    error("  Thread: " + Thread.currentThread());
	    error("  Running CraftBukkit: " + Bukkit.getServer().getClass().getName().equals("org.bukkit.craftbukkit.CraftServer"));
	    error("");
	    if (cause == null || ste == null || ste.length < 1) {
	    	error("Stack trace: no/empty exception given, dumping current stack trace instead!");
	    	return true;
	    } else error("Stack trace: ");
        error("Caused by: " + cause);
        for (StackTraceElement st : ste) error("    at " + st.toString());
        error("");
	    error("End of Error.");
	    error("");
	    return false;
	}
	
}
