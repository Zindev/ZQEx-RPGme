package me.Zindev.zqexrpgm;

import me.Zindev.zqexrpgm.actions.RmAddExpAction;
import me.Zindev.zqexrpgm.actions.RmGiveTombAction;
import me.Zindev.zqexrpgm.conditions.RmCheckCombatCondition;
import me.Zindev.zqexrpgm.conditions.RmCheckExpCondition;
import me.Zindev.zqexrpgm.conditions.RmCheckLevelCondition;
import me.Zindev.zqexrpgm.objectives.RmGainExpObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		ZQuestAPI.registerExtension(RmAddExpAction.class, this);
		ZQuestAPI.registerExtension(RmGiveTombAction.class, this);
		ZQuestAPI.registerExtension(RmCheckExpCondition.class, this);
		ZQuestAPI.registerExtension(RmCheckLevelCondition.class, this);
		ZQuestAPI.registerExtension(RmCheckCombatCondition.class, this);
		ZQuestAPI.registerExtension(RmGainExpObjective.class, this);
		Bukkit.getPluginManager().registerEvents(new MyListener(), this);
	}
	@Override
	public void onDisable() {
		if(Bukkit.getPluginManager().isPluginEnabled("ZQuest")){
			ZQuestAPI.unregisterAll(this);
		}
	}


}
