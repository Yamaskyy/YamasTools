package me.yamas.tools.commands;

import java.util.Arrays;

import me.yamas.tools.commands.utils.ExecutorCaller;


public class Commands {

	private static Commands instance;
	
	public Commands(){
		instance = this;
	}
	
	public void register(){
		new ExecutorCaller(new ExcBroadcast(), "broadcast", "yamastools.cmd.broadcast", Arrays.asList("bc"));
		new ExecutorCaller(new ExcGameMode(), "gamemode", "yamastools.cmd.gamemode", Arrays.asList("gm"));
		new ExecutorCaller(new ExcHelpop(), "helpop", "yamastools.cmd.helpop", Arrays.asList("zglos"));
		new ExecutorCaller(new ExcExp(), "exp", "yamastools.cmd.exp", Arrays.asList("xp"));
		new ExecutorCaller(new ExcHeal(), "heal", "yamastools.cmd.heal", null);
		new ExecutorCaller(new ExcFeed(), "feed", "yamastools.cmd.feed", null);
		new ExecutorCaller(new ExcFly(), "fly", "yamastools.cmd.fly", null);
		new ExecutorCaller(new ExcTp(), "teleport", "yamastools.cmd.tp", Arrays.asList("tp"));
		new ExecutorCaller(new ExcTpHere(), "tphere", "yamastools.cmd.tphere", null);
		new ExecutorCaller(new ExcHelp(), "help", null, null);
		new ExecutorCaller(new ExcClearInventory(), "clearinventory", "yamastools.cmd.clearinv", Arrays.asList("clearinv", "ci"));
		new ExecutorCaller(new ExcEnchant(), "enchant", "yamastools.cmd.enchant", Arrays.asList("ench"));
		new ExecutorCaller(new ExcTppos(), "tppos", "yamastools.cmd.tppos", null);
		new ExecutorCaller(new ExcChat(), "chat", "yamastools.cmd.chat", null);
		new ExecutorCaller(new ExcGod(), "god", "yamastools.cmd.god", null);
		new ExecutorCaller(new ExcSetHome(), "sethome", "yamastools.cmd.sethome", null);
		new ExecutorCaller(new ExcHome(), "home", "yamastools.cmd.home", null);
	}
	
	public static Commands getInstance(){
		if(instance == null) return new Commands();
		return instance;
	}
	
}
